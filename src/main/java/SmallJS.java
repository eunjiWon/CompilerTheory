import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class SmallJS {
	ArrayList<String> fileLines;
	HashMap<String, String> keywordsMap = new HashMap<>();
	HashMap<String, String> delimitersMap = new HashMap<>();
	HashMap<String, String> operatorsMap = new HashMap<>();

	public static void main(String[] args) throws Exception {
		SmallJS smallJS = new SmallJS();
		// initialize keywordsMap, delimitersMap, operatorsMap 
		smallJS.initailizeMap();
		smallJS.run(args[0]);
		
	}

	public void run(String filePath) throws Exception {
		// read input file
		fileLines = readFile(filePath);
		executeDFA();
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
	
	void executeDFA() {
		String line = "";
		// read each one line from file
		for(int i = 0; i < fileLines.size(); i++) {
			line = fileLines.get(i);
			String ch;
			if(line.equals("<script_start>") | line.equals("<script_end>"))
				System.out.println(line + " keyword id");
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
							if(isWhiteSpace(ch)) {
								state = 0;
							}
							else if(ch.equals("/")) {
								state = 1;
								temp = ch;
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
								if(!delimitersMap.get(delimiter).equals("null"))
									System.out.println(delimiter + " " + delimitersMap.get(delimiter));
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
							else state = 12;
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
									System.out.println(temp + " user-defined id");
									if(!delimitersMap.get(delimiter).equals("null"))
										System.out.println(delimiter + " " + delimitersMap.get(delimiter));
									temp = "";
									delimiter = "";
									state = 0;
								}
								else if(isKeyword(temp)) { // keyword
									System.out.println(temp + " " + keywordsMap.get(temp));
									if(!delimitersMap.get(delimiter).equals("null"))
										System.out.println(delimiter + " " + delimitersMap.get(delimiter));
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
								System.out.println(temp + " user-defined id");
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
								if(!functionParamterStartFlag) temp = "(";
								functionParamterStartFlag = true;
								if(ch.equals(")")) {
									temp += ch;
									System.out.println(temp + " function parameter");
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
								System.out.println(temp + " number");
								if(!delimitersMap.get(delimiter).equals("null"))
									System.out.println(delimiter + " " + delimitersMap.get(delimiter));
								temp = "";
								delimiter = "";
								state = 0;
							}
							else state = 12; // error state 
							break;
						case 6:
							if(isWhiteSpace(ch)) {
								System.out.println(operator + " " + operatorsMap.get(operator));
								operator = "";
								state = 0;
							}
							else if(isDigit(ch)) {
								System.out.println(operator + " " + operatorsMap.get(operator));
								operator = "";
								temp = ch;
								state = 5;
							}
							else if(isLetterOrDot(ch)) {
								System.out.println(operator + " " + operatorsMap.get(operator));
								operator = "";
								temp = ch;
								state = 3;
							}
							else if(isSecondOp(ch)) {
								operator += ch;
								System.out.println(operator + " " + operatorsMap.get(operator));
								operator = "";
								state = 0;
							}
							else state = 12; // error state 
							break;
						case 7:
							if(ch.equals("\"")) {
								temp += ch;
								System.out.println(temp + "  literal");
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
						case 9:
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
							System.out.println("error state!");
							break;
					} 
				}
				if(state == 2) {
					System.out.println(temp + " comment");
				}
				if(state == 3) {
					System.out.println(temp + " " + keywordsMap.get(temp));
				}
			} 
		}
	}

	void initailizeMap() { 
		keywordsMap.put("var", "keyword id");
		keywordsMap.put("while", "keyword id");
		keywordsMap.put("if", "keyword id");
		keywordsMap.put("else", "keyword id");
		keywordsMap.put("for", "keyword id");
		keywordsMap.put("switch", "keyword id");
		keywordsMap.put("case", "keyword id");
		keywordsMap.put("break", "keyword id");
		keywordsMap.put("default", "keyword id");
		keywordsMap.put("do", "keyword id");
		keywordsMap.put("false", "keyword id");
		keywordsMap.put("return", "keyword id");
		keywordsMap.put("window.prompt", "keyword function name");
		keywordsMap.put("parseFloat", "keyword function name");
		keywordsMap.put("document.write", "keyword function name");
		keywordsMap.put("document.writeln", "keyword function name");
		keywordsMap.put("function", "keyword function name");
		keywordsMap.put("<h1>", "keyword tag name");
		keywordsMap.put("</h1>", "keyword tag name");
		keywordsMap.put("<h2>", "keyword tag name");
		keywordsMap.put("</h2>", "keyword tag name");
		keywordsMap.put("<h3>", "keyword tag name");
		keywordsMap.put("</h3>", "keyword tag name");
		keywordsMap.put("<ul>", "keyword tag name");
		keywordsMap.put("</ul>", "keyword tag name");
		keywordsMap.put("<ol>", "keyword tag name");
		keywordsMap.put("</ol>", "keyword tag name");
		keywordsMap.put("<br/>", "keyword tag name");
		
		delimitersMap.put(" ", "null");
		delimitersMap.put("(", "null"); 
		delimitersMap.put(")", "null");
		delimitersMap.put("{", "null"); 
		delimitersMap.put("}", "null"); 
		delimitersMap.put(";", "semicolon charcter");
		delimitersMap.put(":", "colon charcter");
		delimitersMap.put(",", "punctuation charcter");
		
		operatorsMap.put("<=", "less than or equal comparison operator");
		operatorsMap.put(">=", "more than or equal comparison operator");
		operatorsMap.put("==", "equal comparison operator");
		operatorsMap.put("=", "assignment operator");
		operatorsMap.put("<", "less than operator");
		operatorsMap.put(">", "more than operator");
		operatorsMap.put("+", "plus operator"); 
		operatorsMap.put("++", "increment operator");
		operatorsMap.put("+=", "assignment operator");
		operatorsMap.put("*", "multiply operator"); 
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
		if(c.equals("=")||c.equals("+")) return true;
		else return false;
	}
	
	boolean isFirstOp(String c) {
		if(c.equals("<")||c.equals(">")||c.equals("=")||c.equals("+")||c.equals("*")) return true;
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
	
	boolean isWhiteSpace(String c) {
		if(Character.isWhitespace(c.charAt(0))) return true;
		else return false;
	}	
}
