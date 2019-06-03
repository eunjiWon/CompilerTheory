import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Collections;

public class LL1 {
	static ArrayList<String> userIdentifierTable = new ArrayList<String>();
	static ArrayList<String[]> tokenList = new ArrayList<String[]>();
	static ArrayList<ArrayList<String>> ruleList = new ArrayList<ArrayList<String>>();
	static HashMap<String, HashMap<String, Integer>> hm_parsing_table = new HashMap<String, HashMap<String, Integer>>();
	static ArrayList<String> NonTerminalList = new ArrayList<String>();
	
	static Stack<String[]> inputStack = new Stack<String[]>();
	static Stack<String> parsingStack = new Stack<String>();
	
	public static void main(String[] args) throws Exception {
		LL1 llOneParser = new LL1();
		// get token list
		scanner Scanner = new scanner();
		Scanner.initailizeMap();
		Scanner.run(args[0]);
		tokenList = Scanner.getTokenList();
		
		// get non terminal list
		NonTerminal nonTerminal = new NonTerminal();
		NonTerminalList = nonTerminal.getNonTerminalList();
		// get rule list
		RuleGenerator ruleGenerator = new RuleGenerator();
		ruleList = ruleGenerator.getRuleList();
		// get parsing table
		ParsingTable parsingTable = new ParsingTable();
		hm_parsing_table = parsingTable.getParsingTable();
		// push input stream to Input Stack
		llOneParser.pushInputTokenListToInputStack();
	
		parsingStack.push("EOF");
		parsingStack.push("Program");
		// Start Parsing!
		llOneParser.parsing();
	}

	boolean isNonTerminalList() {
		if(NonTerminalList.contains(parsingStack.peek())) {
			return true;
		}
		else return false;
	}
	
	void action(String nonTerminal, String terminal_lexeme, String terminal) {
		// find rule number
		int ruleNumber = 0;
		if(hm_parsing_table.get(nonTerminal).get(terminal) != null) {
			ruleNumber = hm_parsing_table.get(nonTerminal).get(terminal);
		}
		else {
			System.out.println("Unexpected token -> " + terminal_lexeme);
			System.exit(0);
		}
		// push the reversed rule tokens
		ArrayList<String> ruleTokens = ruleList.get(ruleNumber - 1);
		Collections.reverse(ruleTokens);
		for(String temp : ruleTokens) {
			if(temp.equals("epsilon")) continue;
			parsingStack.push(temp);
		}
		Collections.reverse(ruleTokens);
	}
	
	void match(String popPS_T, String popIS_lexeme, String popIS_tokenType) {
		if(popIS_tokenType.equals("id")) {
			if(!userIdentifierTable.contains(popIS_lexeme)) {
				System.out.println("Undefined identifier -> " + popIS_lexeme); 
				System.exit(0); 
			}
		}

		if(!popPS_T.equals(popIS_tokenType)) {
			System.out.println("Syntax error");
			System.out.println("Match Fail (unexpected token) -> " + popIS_lexeme);
			System.exit(0);
		}
//		else {
//			System.out.println("Match Success -> " + popPS_T);
//		}
	}
	
	void parsing() {
		while(!parsingStack.peek().equals("EOF")) {
			if(isNonTerminalList()) {
				// nonterminal 
				String popPS_nonT = parsingStack.pop();
				if(popPS_nonT.equals("Assign-nocolon")) {
					String userDefinedID_lexeme = inputStack.peek()[0];
					userIdentifierTable.add(userDefinedID_lexeme);
				}
				String[] peekIS = inputStack.peek();
				action(popPS_nonT, peekIS[0], peekIS[1]);
				
			}
			else {
				// terminal 
				String popPS_T = parsingStack.pop();
				String[] popIS = inputStack.pop();
				match(popPS_T, popIS[0], popIS[1]);
			}
		}
		if(!inputStack.peek()[1].equals("EOF")) {
			System.out.println("Code ends before file");
			System.exit(0);
		}
		else {
			System.out.println("Accept (Parsing Complete)");
		}
	
	}
	
	void pushInputTokenListToInputStack() {
		Collections.reverse(tokenList);
		for(String[] temp : tokenList) {
			inputStack.push(new String[] {temp[0], temp[1]});

		}
	}
	
	void printToken() {
		System.out.println("--------------print token-------------");
		System.out.println("********[lexeme] : [tokenType]********");
		for(String[] temp : tokenList) {
			System.out.println(temp[0] + " : " + temp[1]); 
		}
		System.out.println("-------------------------------------");
	}
}
