import java.util.ArrayList;

public class AutoRuleGenerator {
	static String[] BNFList = {
			"S -> Program",
			"Program -> <script_start> Stmt-sequence <script_end>",
			"Stmt-sequence -> Stmt-sequence Statement",
			"Stmt-sequence -> Statement",
			"Statement -> If-stmt",
			"Statement -> While-stmt",
			"Statement -> For-stmt",
			"Statement -> Assign-stmt",
			"Statement -> Function-stmt",
			"Statement -> Define-stmt",
			"Statement -> Switch-stmt",
			"Statement -> Comment-stmt",
			"If-stmt -> if ( Exp ) { Stmt-sequence } Else-part",
			"Else-part -> else { Stmt-sequence }",
			"Else-part -> epsilon",
			"Assign-stmt -> id Assign-stmt",
			"Assign-stmt' -> = Exp ;",
			"Assign-stmt' -> Double-op ;",
			"Exp -> Simple-exp Exp'",
			"Exp' -> Comparison-op Simple-exp",
			"Exp' -> Double-op",
			"Exp' -> epsilon",
			"Double-op -> ++",
			"Double-op -> --",
			"Comparison-op -> ==",
			"Comparison-op -> !=",
			"Comparison-op -> <",
			"Comparison-op -> >",
			"Comparison-op -> <=",
			"Comparison-op -> >=",
			"Simple-exp -> Simple-exp Add-op Term", 
			"Simple-exp -> Term",
			"Add-op -> +",
			"Add-op -> -",
			"Term -> Term Multi-op Factor", 
			"Term -> Factor",
			"Multi-op -> *",
			"Multi-op -> /",
			"Factor -> ( Exp )",
			"Factor -> number",
			"Factor -> id",
			"Define-stmt -> var Assign-sequence ;",
			"Assign-sequence -> Assign-sequence , Assign-nocolon",
			"Assign-sequence -> Assign-nocolon",
			"Assign-nocolon -> id Assign-nocolon'",
			"Assign-nocolon' -> = Exp",
			"Assign-nocolon' -> epsilon",
			"Function-stmt -> function_name ( Function-parameter ) ;",
			"Function-parameter -> id",
			"Function-parameter -> literal",
			"Function-parameter -> null",
			"While-stmt -> while ( Exp ) { Stmt-sequence }",
			"For-stmt -> for ( Assign-stmt Exp ; Exp ) { Stmt-sequence }",
			"Switch-stmt -> switch ( id ) { Case-sequence Default-stmt }",
			"Case-sequence -> Case-sequence Case-stmt ",
			"Case-sequence -> Case-stmt",
			"Case-stmt -> case literal : Stmt-sequence break ;",
			"Default-stmt -> default : Stmt-sequence",
			"Comment-stmt -> comment_token"
	};
	
    static ArrayList<ArrayList<String>> ruleList = new ArrayList<ArrayList<String>>();
    
    void makeRuleList() {
    		AutoRuleGenerator autoRuleGenerator = new AutoRuleGenerator();
		for(String bnf : BNFList) {
			autoRuleGenerator.BNFPrinter(bnf);
		}
    }
    
	void printRuleList(ArrayList<ArrayList<String>> ruleList) {
		int idx = 0;
		System.out.println("==============printRuleList================");
		for(ArrayList<String> each_rule : ruleList) {
			System.out.println("ruleList.get(" + idx + ") is "+ each_rule);
			idx++;	
		}
	}
	
    public void BNFPrinter(String bnf){
        String[] stringBNF;
    		ArrayList<String> tokens = new ArrayList<String>();
    		stringBNF = bnf.split(" ");
        for(int i=0; i< stringBNF.length ;i++){
        		stringBNF[i] = stringBNF[i].trim();
        		tokens.add(stringBNF[i]);
        		
        }
        ruleList.add(tokens);
    }
    
    ArrayList<ArrayList<String>> getBNFList() {
    		return ruleList;
    }

}
