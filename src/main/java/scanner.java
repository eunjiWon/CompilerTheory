import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class scanner {
	ArrayList<String> fileLines;
	HashMap<String, String> keywordsMap = new HashMap<>();
	HashMap<String, String> delimitersMap = new HashMap<>();
	HashMap<String, String> operatorsMap = new HashMap<>();
	ArrayList<String[]> tokenList = new ArrayList<String[]>();
	
	public void run(String filePath) throws Exception {
		fileLines = readFile(filePath);
		tokenize();
		tokenList.add(new String[]{"EOF", "EOF"});
	}
	
	ArrayList<String[]> getTokenList(){
		return tokenList;
	}
	
	ArrayList<String> readFile(String path) throws Exception{
		ArrayList<String> lines = new ArrayList<String>();

		File inputFile = new File(path);
		FileReader inputFileReader = new FileReader(inputFile);
		BufferedReader inputBufReader = new BufferedReader(inputFileReader);
		String line = "";
		while((line = inputBufReader.readLine()) != null){
			lines.add(line);
		}            
		inputBufReader.close();
		return lines;
	}
	
	void tokenize() {
		String line = "";
		// read each one line from file
		for(int i = 0; i < fileLines.size(); i++) {
			line = fileLines.get(i);
			String ch;
			if(line.equals("<script_start>")) {
				tokenList.add(new String[] {line, "<script_start>"});
			}
			else if(line.equals("<script_end>")) {
				tokenList.add(new String[] {line, "<script_end>"});
			}
			else { 
				int state = 0; 
				String temp = "";
				String delimiter = "";
				String operator = "";
				String tag = "";
				boolean tagPrintFlag = false;
				boolean functionParamterStartFlag = false;
				for(int j = 0; j < line.length(); j++) { 
					ch = String.valueOf(line.charAt(j));
					switch(state){
						case 0:
							if(ch.equals("/")) {
								state = 1;
								temp = ch;
								operator = ch;  // consider the case of subtraction
							}
							else if(isLetterOrDot(ch)) {
								state = 3;
								temp = ch;
							}
							else if(isDigit(ch)) {
								state = 5;
								temp = ch;
							}
							else if(isDelimiter(ch)) {
								delimiter = ch;
								if(!delimitersMap.get(delimiter).equals("null")) {
									tokenList.add(new String[] {delimiter, delimitersMap.get(delimiter)});
								}
								temp = "";
								delimiter = "";
								state = 0;
							}
							else if(isFirstOp(ch)) {
								state = 6;
								operator = ch;
							}
							else if(ch.equals("\"")) {
								state = 7;
								temp = ch;
							}
							else state = 12; // error state 
							break;
						case 1:
							if(ch.equals("/")) state = 2;
							else if((isDelimiter(ch) || isDigit(ch) || isLetterOrDot(ch))) {
								state = 6;
							}
							else {
								state = 12;
							}
							temp += ch;
							break;
						case 2: 
							state = 2;
							temp += ch;
							break;
						case 3:
							if(isLetterOrDot(ch)) {
								state = 3;
								temp += ch;
							}
							else if(isDigit(ch)) {
								state = 3;
								
								temp += ch;
							} 
							else if(isDelimiter(ch)) {
								delimiter = ch;
								if(isNoKeyword(temp)) { // user-defined id
									tokenList.add(new String[] {temp, "id"});
									if(!delimitersMap.get(delimiter).equals("null")) {
										tokenList.add(new String[] {delimiter, delimitersMap.get(delimiter)});
									}
									temp = "";
									delimiter = "";
									state = 0;
								}
								else if(isKeyword(temp)) { // keyword
									tokenList.add(new String[] {temp, keywordsMap.get(temp)});
									if(!delimitersMap.get(delimiter).equals("null")) {
										tokenList.add(new String[] {delimiter, delimitersMap.get(delimiter)});
									}
									if(isFunctionName(temp)) { // function name
										state = 4;
										temp = "";
									}
									else {
										temp = "";
										delimiter = "";
										state = 0;
									}
								}
							} 
							else if(isFirstOp(ch)) {
								operator = ch;
								tokenList.add(new String[] {temp, "id"});
								state = 6;
							}
							else state = 12;
							break;
						case 4: 
							if(ch.equals("\"")) {
								state = 7;
								temp += ch; 
							}
							else {
								if(ch.equals(")")) {
									if(temp.equals("")) tokenList.add(new String[] {temp, "null"});
									else tokenList.add(new String[] {temp, "id"}); //
									tokenList.add(new String[] {")", ")"});
									temp = "";
									delimiter = "";
									state = 0;
								}
								else {
									state = 4;
									temp += ch;
								}
							}
							break;
						case 5:
							if(isDigit(ch)) {
								state = 5;
								temp += ch;
							}
							else if(isDelimiter(ch)) {
								delimiter = ch;
								tokenList.add(new String[] {temp, "number"});
								if(!delimitersMap.get(delimiter).equals("null"))
									tokenList.add(new String[]{delimiter, delimitersMap.get(delimiter)});
								temp = "";
								delimiter = "";
								state = 0;
							}
							else state = 12; // error state 
							break;
						case 6:
							if(isDelimiter(ch)) {
								tokenList.add(new String[] {operator, operatorsMap.get(operator)});
								operator = "";
								state = 0;
							}
							else if(isDigit(ch)) {
								tokenList.add(new String[] {operator, operatorsMap.get(operator)});
								operator = "";
								temp = ch;
								state = 5;
							}
							else if(isLetterOrDot(ch)) {
								tokenList.add(new String[] {operator, operatorsMap.get(operator)});
								operator = "";
								temp = ch;
								state = 3;
							}
							else if(isSecondOp(ch)) {
								operator += ch;
								tokenList.add(new String[] {operator, operatorsMap.get(operator)});
								operator = "";
								state = 0;
							}
							else state = 12; // error state 
							break;
						case 7:
							if(ch.equals("\"")) {
								temp += ch;
								tokenList.add(new String[] {temp, "literal"});		
								temp = "";
								state = 0;
							}
							else {
								if(ch.equals("<")) {
									tag = ch; 
									state = 8;
								}
								else {
									state = 7;
									temp += ch;
								}
							}
							break;
						case 8:
							if(ch.equals(">")) {
								tag += ch;
								state = 9;
							}
							else {
								tag += ch;
								state = 8;
							}
							break;
						case 9: // There is no tag type in sample files 
							if(!tagPrintFlag) {
								System.out.println(tag + " " + keywordsMap.get(tag));
								tag = "";
							}
							if(ch.equals("\"")) {
								state = 0;
							}
							else if(ch.equals("<")) {
								System.out.println(temp + "\"" + " literal");
								temp = "";
								tag = ch;
								state = 10;
								tagPrintFlag = false;
							}
							else { 
								tagPrintFlag = true;
								temp += ch;
								state = 9;
							}
							break;
						case 10:
							if(ch.equals("/")) {
								tag += ch;
								state = 11;
							}
							else state = 12; // error state 
							break;
						case 11:
							if(ch.equals(">")) {
								tag += ch;
								state = 9;
							}
							else {
								tag += ch;
								state = 11;
							}
							break;
						case 12: 
							state = 12;
							System.out.println("Scanning Error : error state!");
							break;
					} 
				}
				if(state == 2) {
					tokenList.add(new String[]{temp, "comment_token"});
				}
				if(state == 3) {
					tokenList.add(new String[] {temp, keywordsMap.get(temp)});
				}
			} 
		}
	}

	void initailizeMap() { 
		keywordsMap.put("var", "var");
		keywordsMap.put("while", "while");
		keywordsMap.put("if", "if");
		keywordsMap.put("else", "else");
		keywordsMap.put("for", "for");
		keywordsMap.put("switch", "switch");
		keywordsMap.put("case", "case");
		keywordsMap.put("break", "break");
		keywordsMap.put("default", "default");
		keywordsMap.put("do", "do");
		keywordsMap.put("false", "false");
		keywordsMap.put("return", "return");
		keywordsMap.put("window.prompt", "function_name");
		keywordsMap.put("parseFloat", "function_name");
		keywordsMap.put("document.write", "function_name");
		keywordsMap.put("document.writeln", "function_name");
		keywordsMap.put("function", "function_name");
		
		delimitersMap.put(" ", "null");
		delimitersMap.put("(", "("); 
		delimitersMap.put(")", ")");
		delimitersMap.put("{", "{"); 
		delimitersMap.put("}", "}"); 
		delimitersMap.put(";", ";");
		delimitersMap.put(":", ":");
		delimitersMap.put(",", ",");
		
		operatorsMap.put("<=", "<=");
		operatorsMap.put(">=", ">=");
		operatorsMap.put("==", "==");
		operatorsMap.put("!=", "!=");
		operatorsMap.put("=", "=");
		operatorsMap.put("<", "<");
		operatorsMap.put(">", ">");
		operatorsMap.put("+", "+"); 
		operatorsMap.put("-", "-"); 
		operatorsMap.put("++", "++");
		operatorsMap.put("--", "--");
		operatorsMap.put("+=", "+=");
		operatorsMap.put("*", "*"); 
		operatorsMap.put("/", "/");
	}

	boolean isNoFunctionName(String c) {
		if(!isFunctionName(c)) return true;
		else return false;
	}
	
	boolean isFunctionName(String c) {
		if(c.equals("window.prompt")||c.equals("parseFloat")||c.equals("document.write")||c.equals("document.writeln")) return true;
		else return false;
	}
	
	boolean isNoKeyword(String c) {
		if(!isKeyword(c)) return true;
		else return false;
	}
	
	boolean isKeyword(String c) {
		if(c.equals("var")||c.equals("window.prompt")||c.equals("parseFloat")||c.equals("while")||c.equals("if")||c.equals("else")||c.equals("document.write")||c.equals("document.writeln")||c.equals("for")||c.equals("switch")||c.equals("case")||c.equals("break")||c.equals("default")||c.equals("do")||c.equals("false")||c.equals("function")||c.equals("return")) return true;
		else return false;
	}
	
	boolean isDelimiter(String c) {
		if(c.equals(" ")||c.equals(";")||c.equals(",")||c.equals("(")||c.equals(")")||c.equals("{")||c.equals("}")||c.equals(":")) return true;
		else return false;
	}
	
	boolean isSecondOp(String c) {
		if(c.equals("=")||c.equals("+")||c.equals("-")) return true;
		else return false;
	}
	
	boolean isFirstOp(String c) {
		if(c.equals("<")||c.equals(">")||c.equals("=")||c.equals("+")||c.equals("*")||c.equals("-")||c.equals("/")) return true;
		else return false;
	}
	
	boolean isLetterOrDot(String c) {
		if(Character.isLetter(c.charAt(0)) || c.equals(".")) return true;
		else return false;
	}
	
	boolean isDigit(String c) {
		if(Character.isDigit(c.charAt(0))) return true;
		else return false;
	}	
}

