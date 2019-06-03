import java.util.ArrayList;

public class RuleGenerator {
	ArrayList<ArrayList<String>> ruleList = new ArrayList<ArrayList<String>>();
	
	ArrayList<ArrayList<String>> getRuleList(){
		initializeRuleList();
		return ruleList;
	}
	
	void printRuleList() {
		int idx = 0;
		System.out.println("==============printRuleList================");
		for(ArrayList<String> each_rule : ruleList) {
			System.out.println("ruleList.get(" + idx + ") is "+ each_rule);
			idx++;	
		}
	}
	
	void initializeRuleList() {
		ArrayList<String> tokens = new ArrayList<String>();
		// 0. Program -> <script_start> Stmt-sequence <script_end>
		tokens.add("<script_start>");
		tokens.add("Stmt-sequence");
		tokens.add("<script_end>");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 1. Stmt-sequence -> Statement Statement'
		tokens.add("Statement");
		tokens.add("Statement'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 2. Statement' -> Statement Statement' 
		tokens.add("Statement");
		tokens.add("Statement'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 3. Statement' -> epsilon
		tokens.add("epsilon");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 4. Statement -> If-stmt 
		tokens.add("If-stmt");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 5. Statement -> While-stmt 
		tokens.add("While-stmt");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 6. Statement -> For-stmt 
		tokens.add("For-stmt");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 7. Statement -> Assign-stmt
		tokens.add("Assign-stmt");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 8. Statement -> Function-stmt
		tokens.add("Function-stmt");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 9. Statement -> Define-stmt  
		tokens.add("Define-stmt");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 10. Statement -> Switch-stmt
		tokens.add("Switch-stmt");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 11. Statement -> Comment-stmt  
		tokens.add("Comment-stmt");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 12. If-stmt -> if ( Exp ) { Stmt-sequence } Else-part
		tokens.add("if");
		tokens.add("(");
		tokens.add("Exp");
		tokens.add(")");
		tokens.add("{");
		tokens.add("Stmt-sequence");
		tokens.add("}");
		tokens.add("Else-part");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 13. Else-part -> else { Stmt-sequence }
		tokens.add("else");
		tokens.add("{");
		tokens.add("Stmt-sequence");
		tokens.add("}");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 14. Else-part -> epsilon
		tokens.add("epsilon");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 15. Assign-stmt -> id Assign-stmt'
		tokens.add("id");
		tokens.add("Assign-stmt'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 16. Assign-stmt' -> = Exp ;
		tokens.add("=");
		tokens.add("Exp");
		tokens.add(";");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 17. Assign-stmt' -> Double-op ;
		tokens.add("Double-op");
		tokens.add(";");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 18. Exp -> Simple-exp Exp'
		tokens.add("Simple-exp");
		tokens.add("Exp'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 19. Exp' -> Comparison-op Simple-exp 
		tokens.add("Comparison-op");
		tokens.add("Simple-exp");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 20. Exp' -> Double-op 
		tokens.add("Double-op");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 21. Exp' ->  epsilon
		tokens.add("epsilon");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 22. Double-op -> ++
		tokens.add("++");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 23. Double-op -> --
		tokens.add("--");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 24. Comparison-op -> == 
		tokens.add("==");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 25. Comparison-op -> !=
		tokens.add("!=");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 26. Comparison-op -> <
		tokens.add("<");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();;
		// 27. Comparison-op -> >
		tokens.add(">");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 28. Comparison-op -> <=
		tokens.add("<=");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 29. Comparison-op -> >=
		tokens.add(">=");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 30. Simple-exp -> Term Simple-exp'
		tokens.add("Term");
		tokens.add("Simple-exp'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 31. Simple-exp' -> Add-op Term Simple-exp' 
		tokens.add("Add-op");
		tokens.add("Term");
		tokens.add("Simple-exp'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 32. Simple-exp' -> epsilon
		tokens.add("epsilon");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 33. Add-op -> + 
		tokens.add("+");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 34. Add-op -> -
		tokens.add("-");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 35. Term -> Factor Term'
		tokens.add("Factor");
		tokens.add("Term'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 36. Term' -> Multi-op Factor Term' 
		tokens.add("Multi-op");
		tokens.add("Factor");
		tokens.add("Term'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 37. Term' -> epsilon
		tokens.add("epsilon");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 38. Multi-op -> * 
		tokens.add("*");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 39. Multi-op -> /
		tokens.add("/");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 40. Factor -> ( Exp )
		tokens.add("(");
		tokens.add("Exp");
		tokens.add(")");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 41. Factor -> number
		tokens.add("number");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 42. Factor -> id
		tokens.add("id");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 43. Define-stmt -> var Assign-sequence ;
		tokens.add("var");
		tokens.add("Assign-sequence");
		tokens.add(";");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 44. Assign-sequence -> Assign-nocolon Assign-sequence'
		tokens.add("Assign-nocolon");
		tokens.add("Assign-sequence'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 45. Assign-sequence' -> , Assign-nocolon Assign-sequence' 
		tokens.add(",");
		tokens.add("Assign-nocolon");
		tokens.add("Assign-sequence'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 46. Assign-sequence' -> epsilon
		tokens.add("epsilon");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 47. Assign-nocolon -> id Assign-nocolon'
		tokens.add("id");
		tokens.add("Assign-nocolon'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 48. Assign-nocolon' -> = Exp 
		tokens.add("=");
		tokens.add("Exp");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 49. Assign-nocolon' -> epsilon
		tokens.add("epsilon");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 50. Function-stmt -> function_name ( Function-parameter ) ;
		tokens.add("function_name");
		tokens.add("(");
		tokens.add("Function-parameter");
		tokens.add(")");
		tokens.add(";");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 51. Function-parameter -> id 
		tokens.add("id");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 52. Function-parameter -> literal 
		tokens.add("literal");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 53. Function-parameter -> null
		tokens.add("null");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 54. While-stmt -> while ( Exp ) { Stmt-sequence } 
		tokens.add("while");
		tokens.add("(");
		tokens.add("Exp");
		tokens.add(")");
		tokens.add("{");
		tokens.add("Stmt-sequence");
		tokens.add("}");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 55. For-stmt -> for ( Assign-stmt Exp ; Exp ) { Stmt-sequence }
		tokens.add("for");
		tokens.add("(");
		tokens.add("Assign-stmt");
		tokens.add("Exp");
		tokens.add(";");
		tokens.add("Exp");
		tokens.add(")");
		tokens.add("{");
		tokens.add("Stmt-sequence");
		tokens.add("}");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 56. Switch-stmt -> switch ( id ) { Case-sequence Default-stmt }
		tokens.add("switch");
		tokens.add("(");
		tokens.add("id");
		tokens.add(")");
		tokens.add("{");
		tokens.add("Case-sequence");
		tokens.add("Default-stmt");
		tokens.add("}");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 57. Case-sequence -> Case-stmt Case-sequence'
		tokens.add("Case-stmt");
		tokens.add("Case-sequence'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 58. Case-sequence' -> Case-stmt Case-sequence'
		tokens.add("Case-stmt");
		tokens.add("Case-sequence'");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 59. Case-sequence' -> epsilon
		tokens.add("epsilon");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 60. Case-stmt -> case literal : Stmt-sequence break ;
		tokens.add("case");
		tokens.add("literal");
		tokens.add(":");
		tokens.add("Stmt-sequence");
		tokens.add("break");
		tokens.add(";");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 61. Default-stmt -> default : Stmt-sequence
		tokens.add("default");
		tokens.add(":");
		tokens.add("Stmt-sequence");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		// 62. Comment-stmt -> comment_token
		tokens.add("comment_token");
		ruleList.add(tokens);
		tokens = new ArrayList<String>();
		
//		printRuleList();
	}
}





















