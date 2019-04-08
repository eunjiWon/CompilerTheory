import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class SmallJS {
	ArrayList<String> fileLines;
	HashMap<String, String> keywordsMap = new HashMap<>();
	HashMap<String, String> delimitersMap = new HashMap<>();
	HashMap<String, String> operatorsMap = new HashMap<>();

	public static void main(String[] args) throws Exception {
		SmallJS smallJS = new SmallJS();
		// initialize HashMap 
		smallJS.initailizeMap();
		smallJS.run(args[0]);
		
	}

	public void run(String filePath) throws Exception {
		// read file
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
		// read each line from file
		for(int i = 0; i < fileLines.size(); i++) {
			line = fileLines.get(i);
			String ch;
//			System.out.println(line);
			// <script_start> or <script_end> tag 면 출력시키기 
			if(line.equals("<script_start>") | line.equals("<script_end>"))
				System.out.println(line + " keyword id");
			else { /////////////////////////////////////////////////////////////////////
				int state = 0; // start state
				String temp = "";
				String delimiter = "";
				String operator = "";
				for(int j = 0; j < line.length(); j++) { //한 라인 시작 
					ch = String.valueOf(line.charAt(j));
//					System.out.print(ch);
					switch(state){
						case 0:
							if(isWhiteSpace(ch)) {
//								System.out.println(ch + " : WS(case0)");
								state = 0;
							}
//							if(ch.equals(" ")) state = 0;
							else if(ch.equals("/")) {
//								System.out.println(ch + " : /(case0)");
								state = 1;
								temp = ch;
							}
							else if(isLetterOrDot(ch)) {
//								System.out.println(ch + " : Letter(case0)");
								state = 3;
								temp = ch;
							}
							else if(isDigit(ch)) {
//								System.out.println(ch + " : Digit(case0)");
								state = 10;
								temp = ch;
							}
							else if(isDelimiter(ch)) {
//								System.out.println(ch + " : Deli(case0)");
//								state = 12;
								delimiter = ch;
//								System.out.println(ch + " : case12");
								if(!delimitersMap.get(delimiter).equals("null"))
									System.out.println(delimiter + " " + delimitersMap.get(delimiter));
								temp = "";
								delimiter = "";
								state = 0;
							}
							else if(isFirstOp(ch)) {
//								System.out.println(ch + " : FirstOp(case0)");
								state = 13;
								operator = ch;
							}
							else if(ch.equals("\"")) {
//								System.out.println(ch + " : \"(case0)");
								state = 16;
								temp = ch;
							}
							else state = 19;
							break;
						case 1:
//							System.out.println(ch + " : case1");
							if(ch.equals("/")) state = 2;
							else state = 19;
							temp += ch;
							break;
						case 2: // accept state -> 한 라인 끝나고 따로 처리 
//							System.out.println(ch + " : case2");
							state = 2;
							temp += ch;
							break;
						case 3:
//							System.out.println(ch + " : case3"); // 이게 여기 있으면 정확히 디버깅이안된다 
							if(isLetterOrDot(ch)) {
								state = 3;
								temp += ch;
							}
							else if(isDigit(ch)) {
								state = 3;
								temp += ch;
							} 
							else if(isDelimiter(ch)) {
//								state = 4;
								delimiter = ch;
								if(isNoKeyword(temp)) { // user-defined id
									System.out.println(temp + " user-defined id");
									if(!delimitersMap.get(delimiter).equals("null"))
										System.out.println(delimiter + " " + delimitersMap.get(delimiter));
									temp = "";
									delimiter = "";
									state = 0;
								}
								else if(isKeyword(temp)) { // keyword id
									System.out.println(temp + " " + keywordsMap.get(temp));
									if(!delimitersMap.get(delimiter).equals("null"))
										System.out.println(delimiter + " " + delimitersMap.get(delimiter));
									if(isFunctionName(temp)) {
										state = 7;
										temp = "(";
									}
									else {
										temp = "";
										delimiter = "";
										state = 0;
									}
								}
							} 
							else if(isFirstOp(ch)) {
//								state = 20;
								operator = ch;
								System.out.println(temp + " user-defined id");
//								System.out.println(ch + " : case13");
								if(isWhiteSpace(ch)) {
//									state = 14;
//									System.out.println(ch + " : case14");
									System.out.println(operator + " " + operatorsMap.get(operator));
									operator = "";
									state = 0;
								}
								else if(isSecondOp(ch)) {
									operator += ch;
//									state = 15;
//									System.out.println(ch + " : case15");
									System.out.println(operator + " " + operatorsMap.get(operator));
									operator = "";
									state = 0;
								}
							}
							else state = 19;
							break;
//						case 4:
//							System.out.println(ch + " : case4");
//							if(isNoKeyword(temp)) { // user-defined id
//								System.out.println(temp + " user-defined id");
//								if(!delimitersMap.get(delimiter).equals("null"))
//									System.out.println(delimiter + " " + delimitersMap.get(delimiter));
//								temp = "";
//								delimiter = "";
//								state = 0;
//							}
//							else if(isKeyword(temp)) { // keyword id
//								System.out.println(temp + " " + keywordsMap.get(temp));
//								if(!delimitersMap.get(delimiter).equals("null"))
//									System.out.println(delimiter + " " + delimitersMap.get(delimiter));
//								if(isFunctionName(temp)) {
//									state = 7;
//									temp = "(";
//								}
//								else {
//									temp = "";
//									delimiter = "";
//									state = 0;
//								}
//							}
//							else state = 19; 
//							break;
						case 7: // function 이니까 
//							System.out.println(ch + " : case7");
							if(ch.equals(")")) {
								temp += ch;
								System.out.println(temp + " function parameter");
								temp = "";
								delimiter = "";
								state = 0;
							}
							else {
								state = 7;
								temp += ch;
							}
//							temp += ch;
							// 여기서도 19 로 에러 처리 못했
							break;
						case 10:
//							System.out.println(ch + " : case10");
							if(isDigit(ch)) {
								state = 10;
								temp += ch;
							}
							else if(isDelimiter(ch)) {
//								state = 11;
								delimiter = ch;
								System.out.println(temp + " number");
								if(!delimitersMap.get(delimiter).equals("null"))
									System.out.println(delimiter + " " + delimitersMap.get(delimiter));
								temp = "";
								delimiter = "";
								state = 0;
							}
							break;
//						case 11:
//							System.out.println(ch + " : case11");
//							System.out.println(temp + " number");
//							if(!delimitersMap.get(delimiter).equals("null"))
//								System.out.println(delimiter + " " + delimitersMap.get(delimiter));
//							temp = "";
//							delimiter = "";
//							state = 0;
//							break;
//						case 12:
//							System.out.println(ch + " : case12");
//							if(!delimitersMap.get(delimiter).equals("null"))
//								System.out.println(delimiter + " " + delimitersMap.get(delimiter));
//							temp = "";
//							delimiter = "";
//							state = 0;
//							break;
						case 13:
//							System.out.println(ch + " : case13");
							if(isWhiteSpace(ch)) {
//								state = 14;
//								System.out.println(ch + " : case14");
								System.out.println(operator + " " + operatorsMap.get(operator));
								operator = "";
								state = 0;
							}
							else if(isSecondOp(ch)) {
								operator += ch;
//								state = 15;
//								System.out.println(ch + " : case15");
								System.out.println(operator + " " + operatorsMap.get(operator));
								operator = "";
								state = 0;
							}
							break;
//						case 14: // single operator
//							System.out.println(ch + " : case14");
//							System.out.println(operator + " " + operatorsMap.get(operator));
//							operator = "";
//							state = 0;
//							break;
//						case 15:
//							System.out.println(ch + " : case15");
//							System.out.println(operator + " " + operatorsMap.get(operator));
//							operator = "";
//							state = 0;
//							break;
						case 16:
//							System.out.println(ch + " : case16");
							if(ch.equals("\"")) {
//								state = 17;
								temp += ch;
//								System.out.println(ch + " : case17");
								System.out.println(temp + "  literal");
								temp = "";
								state = 0;
							}
							else {
								state = 16;
								temp += ch;
							}
							// 19 error 못 가게 함 
							break;
//						case 17:
//							System.out.println(ch + " : case17");
//							System.out.println(temp + "  literal");
//							temp = "";
//							state = 0;
//							break;
//						case 18:
//							System.out.println(temp + "  literal");
//							temp = "";
//							state = 0;
//							break;
						case 19:
//							System.out.println(ch + " : case19");
							// error state 여기로 한 번 들어오면 한 라인 끝날 때 까지 못 나가게 
							state = 19;
							System.out.println("error state!");
							break;
//						case 20:
//							System.out.println(ch + " : case20");
//							System.out.println(temp + " user-defined id");
//							state = 13;
//							break;
					} // switch end! 
				
				} // 한 라인 끝!
				
				// state2만 따로 처리 해주기 
				if(state == 2) {
					// print comment
					System.out.println(temp + " comment");
					temp = ""; // 사실 안해도
				}
				// keyword 뒤에 delimiter 없을 때 (test1.jss의 14번째 줄 같이...)
				if(state == 3) {
					System.out.println(temp + " " + keywordsMap.get(temp));
					temp = ""; // 사실 안해도됨 
				}
				

			} ///////////////////////////////////////////////////////////////////

		} // 파일 끝!

	}

	void initailizeMap() { // 이건 캐릭터랑 비교하는게 아니라 스트링으로 탐색해야함 
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
		
		delimitersMap.put(" ", "null"); // is that right?
		delimitersMap.put("(", "null"); // is that right?
		delimitersMap.put(")", "null"); // is that right?
		delimitersMap.put("{", "null"); // is that right?
		delimitersMap.put("}", "null"); // is that right?
		delimitersMap.put(";", "semicolon charcter");
		delimitersMap.put(":", "colon charcter");
		delimitersMap.put(",", "punctuation charcter");
		
		operatorsMap.put("<=", "less than or equal comparison operator");
		operatorsMap.put(">=", "more than or equal comparison operator");
		operatorsMap.put("==", "equal comparison operator");
		operatorsMap.put("=", "assignment operator");
		operatorsMap.put("<", "less than operator");
		operatorsMap.put(">", "more than operator");
		operatorsMap.put("+", "plus operator"); // is that right? 
		operatorsMap.put("++", "increment operator");
		operatorsMap.put("+=", "assignment operator");
		operatorsMap.put("*", "multiply operator"); // is that right?
	}

	boolean isNoFunctionName(String c) {
		if(!isFunctionName(c)) {
			return true;
		}
		else return false;
	}
	
	boolean isFunctionName(String c) {
		if(Pattern.matches("window.prompt|parseFloat|document.write|document.writeln",c)) {
			return true;
		}
		else return false;
	}
	
	boolean isNoKeyword(String c) {
		if(!isKeyword(c)) {
			return true;
		}
		else return false;
	}
	
	boolean isKeyword(String c) {
		if(Pattern.matches("var|window.prompt|parseFloat|while|if|else|document.write|document.writeln|for|switch|case|break|default|do|false|function|return",c)) {
			return true;
		}
		else return false;
	}
	
	boolean isDelimiter(String c) {
		if(Pattern.matches("\\s|;|,|\\(|\\)|\\{|\\}|:",c)) {
			return true;
		}
		else return false;
	}
	
	boolean isAny(String c) {
		if(Pattern.matches("\\w",c)) {
			return true;
		}
		else return false;
	}
	
	boolean isSecondOp(String c) {
		if(Pattern.matches("=|\\+",c)) {
			return true;
		}
		else return false;
	}
	
	boolean isFirstOp(String c) {
		if(Pattern.matches("<|>|=|\\+|\\*",c)) {
			return true;
		}
		else return false;
	}
	
	boolean isLetterOrDot(String c) {
		if(Pattern.matches("[a-zA-Z]|\\.",c)) {
			return true;
		}
		else return false;
	}
	
	boolean isDigit(String c) {
		if(Pattern.matches("[0-9]",c)) {
			return true;
		}
		else return false;
	}
	
	boolean isWhiteSpace(String c) {
		if(Pattern.matches("\\s",c)) {
			return true;
		}
		else return false;
	}	

}
