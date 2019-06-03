import java.util.ArrayList;
import java.util.HashMap;

public class ParsingTable {
	HashMap<String, HashMap<String, Integer>> hm_parsing_table = new HashMap<String, HashMap<String, Integer>>();

	HashMap<String, HashMap<String, Integer>> getParsingTable(){
		initializeParsingTable();
		return hm_parsing_table;
	}
	
	void print_hm_parsing_table() {
		System.out.println("==============printParsingTable================");
		for(String key : hm_parsing_table.keySet()) {
			System.out.println("Non-terminal: " + key + " ====> " + hm_parsing_table.get(key));
			for(String k : hm_parsing_table.get(key).keySet()) {
				System.out.println("Terminal: " + k);
				System.out.println("rule number: " + hm_parsing_table.get(key).get(k));
			}
		}
	}
	
	void initializeParsingTable() {
		HashMap<String, Integer> terminal_rule_map = new HashMap<String, Integer>();
		// Program
		terminal_rule_map.put("<script_start>", 1); /////////////// 
		hm_parsing_table.put("Program", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Stmt-sequence
		terminal_rule_map.put("if", 2);
		terminal_rule_map.put("id", 2);
		terminal_rule_map.put("var", 2);
		terminal_rule_map.put("function_name", 2);
		terminal_rule_map.put("while", 2);
		terminal_rule_map.put("for", 2);
		terminal_rule_map.put("switch", 2);
		terminal_rule_map.put("comment_token", 2);
		hm_parsing_table.put("Stmt-sequence", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Statement' 
		terminal_rule_map.put("if", 3);
		terminal_rule_map.put("id", 3);
		terminal_rule_map.put("var", 3);
		terminal_rule_map.put("function_name", 3);
		terminal_rule_map.put("while", 3);
		terminal_rule_map.put("for", 3);
		terminal_rule_map.put("switch", 3);
		terminal_rule_map.put("comment_token", 3);
		terminal_rule_map.put("break", 4);
		terminal_rule_map.put("}", 4);
		terminal_rule_map.put("<script_end>", 4);
		hm_parsing_table.put("Statement'", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Statement
		terminal_rule_map.put("if", 5);
		terminal_rule_map.put("id", 8);
		terminal_rule_map.put("var", 10);
		terminal_rule_map.put("function_name", 9);
		terminal_rule_map.put("while",6);
		terminal_rule_map.put("for", 7);
		terminal_rule_map.put("switch", 11);
		terminal_rule_map.put("comment_token", 12);
		hm_parsing_table.put("Statement", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// If-stmt
		terminal_rule_map.put("if", 13);
		hm_parsing_table.put("If-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Else-part
		terminal_rule_map.put("break", 15);
		terminal_rule_map.put("}", 15);
		terminal_rule_map.put("<script_end>", 15);
		terminal_rule_map.put("else", 14);
		hm_parsing_table.put("Else-part", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Assign-stmt
		terminal_rule_map.put("id", 16);
		hm_parsing_table.put("Assign-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Assign-stmt'
		terminal_rule_map.put("=", 17);
		terminal_rule_map.put("++", 18);
		terminal_rule_map.put("--", 18);
		hm_parsing_table.put("Assign-stmt'", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Exp
		terminal_rule_map.put("(", 19);
		terminal_rule_map.put("id", 19);
		terminal_rule_map.put("number", 19);
		hm_parsing_table.put("Exp", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Exp'
		terminal_rule_map.put(")", 22);
		terminal_rule_map.put(";", 22);
		terminal_rule_map.put(",", 22);
		terminal_rule_map.put("++", 21);
		terminal_rule_map.put("--", 21);
		terminal_rule_map.put("==", 20);
		terminal_rule_map.put("!=", 20);
		terminal_rule_map.put("<", 20);
		terminal_rule_map.put(">", 20);
		terminal_rule_map.put("<=", 20);
		terminal_rule_map.put(">=", 20);
		hm_parsing_table.put("Exp'", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Double-op
		terminal_rule_map.put("++", 23);
		terminal_rule_map.put("--", 24);
		hm_parsing_table.put("Double-op", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Comparison-op
		terminal_rule_map.put("==", 25);
		terminal_rule_map.put("!=", 26);
		terminal_rule_map.put("<", 27);
		terminal_rule_map.put(">", 28);
		terminal_rule_map.put("<=", 29);
		terminal_rule_map.put(">=", 30);
		hm_parsing_table.put("Comparison-op", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Simple-exp
		terminal_rule_map.put("(", 31);
		terminal_rule_map.put("id", 31);
		terminal_rule_map.put("number", 31);
		hm_parsing_table.put("Simple-exp", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Simple-exp'
		terminal_rule_map.put(")", 33);
		terminal_rule_map.put(";", 33);
		terminal_rule_map.put(",", 33);
		terminal_rule_map.put("++", 33);
		terminal_rule_map.put("--", 33);
		terminal_rule_map.put("==", 33);
		terminal_rule_map.put("!=", 33);
		terminal_rule_map.put("<", 33);
		terminal_rule_map.put(">", 33);
		terminal_rule_map.put("<=", 33);
		terminal_rule_map.put(">=", 33);
		terminal_rule_map.put("+", 32);
		terminal_rule_map.put("-", 32);
		hm_parsing_table.put("Simple-exp'", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Add-op
		terminal_rule_map.put("+", 34);
		terminal_rule_map.put("-", 35);
		hm_parsing_table.put("Add-op", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Term
		terminal_rule_map.put("(", 36);
		terminal_rule_map.put("id", 36);
		terminal_rule_map.put("number", 36);
		hm_parsing_table.put("Term", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Term'
		terminal_rule_map.put(")", 38);
		terminal_rule_map.put(";", 38);
		terminal_rule_map.put(",", 38);
		terminal_rule_map.put("++", 38);
		terminal_rule_map.put("--", 38);
		terminal_rule_map.put("==", 38);
		terminal_rule_map.put("!=", 38);
		terminal_rule_map.put("<", 38);
		terminal_rule_map.put(">", 38);
		terminal_rule_map.put("<=", 38);
		terminal_rule_map.put(">=", 38);
		terminal_rule_map.put("+", 38);
		terminal_rule_map.put("-", 38);
		terminal_rule_map.put("*", 37);
		terminal_rule_map.put("/", 37);
		hm_parsing_table.put("Term'", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Multi-op
		terminal_rule_map.put("*", 39);
		terminal_rule_map.put("/", 40);
		hm_parsing_table.put("Multi-op", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Factor
		terminal_rule_map.put("(", 41);
		terminal_rule_map.put("id", 43);
		terminal_rule_map.put("number", 42);
		hm_parsing_table.put("Factor", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Define-stmt
		terminal_rule_map.put("var", 44);
		hm_parsing_table.put("Define-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Assign-sequence
		terminal_rule_map.put("id", 45);
		hm_parsing_table.put("Assign-sequence", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Assign-sequence'
		terminal_rule_map.put(";", 47);
		terminal_rule_map.put(",", 46);
		hm_parsing_table.put("Assign-sequence'", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Assign-nocolon
		terminal_rule_map.put("id", 48);
		hm_parsing_table.put("Assign-nocolon", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Assign-nocolon'
		terminal_rule_map.put("=", 49);
		terminal_rule_map.put(";", 50);
		terminal_rule_map.put(",", 50);
		hm_parsing_table.put("Assign-nocolon'", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Function-stmt
		terminal_rule_map.put("function_name", 51);
		hm_parsing_table.put("Function-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Function-parameter
		terminal_rule_map.put("id", 52);
		terminal_rule_map.put("literal", 53);
		terminal_rule_map.put("null", 54);
		hm_parsing_table.put("Function-parameter", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// While-stmt
		terminal_rule_map.put("while", 55);
		hm_parsing_table.put("While-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// For-stmt
		terminal_rule_map.put("for", 56);
		hm_parsing_table.put("For-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Switch-stmt
		terminal_rule_map.put("switch", 57);
		hm_parsing_table.put("Switch-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Case-sequence
		terminal_rule_map.put("case", 58);
		hm_parsing_table.put("Case-sequence", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Case-sequence'
		terminal_rule_map.put("case", 59);
		terminal_rule_map.put("default", 60);
		hm_parsing_table.put("Case-sequence'", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Case-stmt
		terminal_rule_map.put("case", 61);
		hm_parsing_table.put("Case-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Default-stmt
		terminal_rule_map.put("default", 62);
		hm_parsing_table.put("Default-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		// Comment-stmt
		terminal_rule_map.put("comment_token", 63);
		hm_parsing_table.put("Comment-stmt", terminal_rule_map);
		terminal_rule_map = new HashMap<String, Integer>();
		
//		print_hm_parsing_table();
		
	}
}
