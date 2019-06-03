import java.util.ArrayList;

public class NonTerminal {
	ArrayList<String> NonTerminalList = new ArrayList<String>();
	ArrayList<String> getNonTerminalList(){
		NonTerminalList.add("Program");
		NonTerminalList.add("Stmt-sequence");
		NonTerminalList.add("Statement'");
		NonTerminalList.add("Statement");
		NonTerminalList.add("If-stmt");
		NonTerminalList.add("Else-part");
		NonTerminalList.add("Assign-stmt");
		NonTerminalList.add("Assign-stmt'");
		NonTerminalList.add("Exp");
		NonTerminalList.add("Exp'");
		NonTerminalList.add("Double-op");
		NonTerminalList.add("Comparison-op");
		NonTerminalList.add("Simple-exp");
		NonTerminalList.add("Simple-exp'");
		NonTerminalList.add("Add-op");
		NonTerminalList.add("Term");
		NonTerminalList.add("Term'");
		NonTerminalList.add("Multi-op");
		NonTerminalList.add("Factor");
		NonTerminalList.add("Define-stmt");
		NonTerminalList.add("Assign-sequence");
		NonTerminalList.add("Assign-sequence'");
		NonTerminalList.add("Assign-nocolon");
		NonTerminalList.add("Assign-nocolon'");
		NonTerminalList.add("Function-stmt");
		NonTerminalList.add("Function-parameter");
		NonTerminalList.add("While-stmt");
		NonTerminalList.add("For-stmt");
		NonTerminalList.add("Switch-stmt");
		NonTerminalList.add("Case-sequence");
		NonTerminalList.add("Case-sequence'");
		NonTerminalList.add("Case-stmt");
		NonTerminalList.add("Default-stmt");
		NonTerminalList.add("Comment-stmt");
		
		return NonTerminalList;
	}
	
}
