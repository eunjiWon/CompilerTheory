import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class parser1 {
	ArrayList<String> userIdentifierTable = new ArrayList<String>();
	static ArrayList<String[]> tokenList = new ArrayList<String[]>();
	int tokenIndex = 0;
	String[] token;
	
	public static void main(String[] args) throws Exception {
		scanner Scanner = new scanner();
		parser1 RecursiveDescentParser = new parser1();
		Scanner.initailizeMap();
		Scanner.run(args[0]);
		tokenList = Scanner.getTokenList();
//		RecursiveDescentParser.printToken();
		RecursiveDescentParser.parse();
	}
	
	String[] getToken() {
		return tokenList.get(tokenIndex++);
	}
	
	void printToken() {
		System.out.println("-------------print token-------------");
		for(String[] temp : tokenList) {
			System.out.println(temp[0] + " : " + temp[1]); 
		}
		System.out.println("-------------------------------------");
	}
	
	void parse() {
		token = getToken();
		program();
		if(!token[1].equals("EOF")) {
			System.out.println("Code ends before file");
		}
		else {
			System.out.println("Parsing OK~~");
		}
	}
	
	void match(String expected) {
		if(token[1].equals(expected)) {
			token = getToken();
		}
		else {
			System.out.println("Syntax error");
			System.out.println("match - unexpected token -> " + token[0]);
			System.exit(0);
		}
	}
	
	void program() {
		// 프로그램이 시작하기 전에 주석이 올 수 있다. 
		if(token[1].equals("comment_token")) {
			token = getToken();
		}
		match("<script_start>");
		stmt_sequence();
		match("<script_end>");
	}
	
	void stmt_sequence() {
		statement();
		while(!token[1].equals("EOF") && !token[1].equals("}") && !token[1].equals("<script_end>") && !token[1].equals("break")) {
			statement();
		}
	}
	
	void statement() {
		switch(token[1]) {
			case "if" : if_stmt(); break;
			case "while" : while_stmt(); break;
			case "for" : for_stmt(); break;
			case "id" : assign_stmt(); break;
			case "var" : define_stmt(); break;
			case "function_name" : function_stmt(); break;
			case "switch" : switch_stmt(); break;
			case "comment_token" : comment_stmt(); break;
			default : 
					System.out.println("statement - unexpected token -> " + token[0]); 
					System.exit(0); 
					break;
		}
	}
	
	void if_stmt() {
		match("if");
		match("(");
		exp();
		match(")");
		match("{");
		stmt_sequence();
		match("}");
		match("else");
		match("{");
		stmt_sequence();
		match("}");
	}
	
	void assign_stmt() {
		if(!userIdentifierTable.contains(token[0])) {
			System.out.println(token[0] + " undefined! "); 
			System.exit(0); 
		}
		match("id");
		if(token[1].equals("=")) {
			match("=");
			exp();
			match(";");
		}
		else if (token[1].equals("++") || token[1].equals("--")) {
			match(token[1]);
			match(";");
			
		}

	}
	
	void exp() {
		simple_exp();
		if(token[1].equals("==") || token[1].equals("!=") || token[1].equals("<") || token[1].equals(">") || token[1].equals("<=") || token[1].equals(">=")) {
			match(token[1]); 
			simple_exp();
		}
		else if (token[1].equals("++") || token[1].equals("--")) {
			match(token[1]);
		}
	}
	
	void simple_exp() {
		term();
		while(token[1].equals("+") || token[1].equals("-")) {
			match(token[1]);
			term();
		}
	}
	
	void term() {
		factor();
		while(token[1].equals("*") || token[1].equals("/")) {
			match(token[1]);
			factor();
		}
	}
	
	void factor() {
		switch(token[1]) {
			case "number" : match(token[1]); break;
			case "id" : 
					if(!userIdentifierTable.contains(token[0])) {
						System.out.println(token[0] + " undefined! "); 
						System.exit(0); 
					}
					match(token[1]); 
					break;
			case "(" : 
					match(token[1]);
					exp();
					match(")");
					break;
			default : 
					System.out.println("factor - unexpected token -> " + token[0]); 
					System.exit(0); 
					break;
		}
	}
	
	void define_stmt() {
		match("var");
		assign_sequence();
		match(";");
	}
	
	void assign_sequence() {
		assign_no_colon();
		while(token[1].equals(",")) {
			match(token[1]);
			assign_no_colon();
		}
		
	}
	
	void assign_no_colon() {
		if(token[1].equals("id")) {
			userIdentifierTable.add(token[0]);
			match("id");
			if(token[1].equals("=")) {
				match("=");
				exp();
			}
		}
		else {
			System.out.println("assign_no_colon - unexpected token -> " + token[0]); 
			System.exit(0);
		}
	}
	
	void function_stmt() {
		match("function_name");
		match("(");
		function_parameter();
		match(")");
		match(";");
	}
	
	void function_parameter() {
		switch(token[1]) {
			case "id" : 
				if(!userIdentifierTable.contains(token[0])) {
					System.out.println(token[0] + " undefined! "); 
					System.exit(0); 
				}
				match(token[1]); 
				break;
			case "literal" : match(token[1]); break;
			case "null" : match(token[1]); break;
			default : 
				System.out.println("function_parameter - unexpected token -> " + token[0]); 
				System.exit(0); 
				break;
		}
	}
	
	void while_stmt() {
		match("while");
		match("(");
		exp();
		match(")");
		match("{");
		stmt_sequence();
		match("}");
	}
	
	void for_stmt() {
		match("for");
		match("(");
		assign_stmt();
		exp();
		match(";");
		exp();
		match(")");
		match("{");
		stmt_sequence();
		match("}");
	}
	
	void switch_stmt() {
		match("switch");
		match("(");
		if(!userIdentifierTable.contains(token[0])) {
			System.out.println(token[0] + " undefined! "); 
			System.exit(0); 
		}
		match("id");
		match(")");
		match("{");
		case_sequence();
		default_stmt();
		match("}");
	}
	
	void case_sequence() {
		case_stmt();
		while(token[1].equals("case")) {
			case_stmt();
		}
	}
	
	void case_stmt() {
		match("case");
		match("literal");
		match(":");
		stmt_sequence();
		match("break");
		match(";");
	}
	
	void default_stmt() {
		match("default");
		match(":");
		stmt_sequence();
	}
	
	void comment_stmt() {
		match("comment_token");
	}
}

