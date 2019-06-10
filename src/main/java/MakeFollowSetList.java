import java.util.ArrayList;

public class MakeFollowSetList {
	static String[] followSetList = {
			"$", // 0
			"$", // 1 
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 2
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 3
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 4
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 5
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 6
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 7
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 8
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 9
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 10
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 11
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 12
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 13
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 14
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break, (, number, id", // 15
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break, (, number, id", // 16
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break, (, number, id", // 17
			"), ;, `", // 18
			"), ;, `", // 19
			"), ;, `", // 20
			"), ;, `", // 21
			"), ;, `", // 22
			"), ;, `", // 23
			"(, number, id", // 24
			"(, number, id", // 25
			"(, number, id", // 26
			"(, number, id", // 27
			"(, number, id", // 28
			"(, number, id", // 29
			"), ;, `, +, -, ==, !=, <, >, <=, >=, ++, --", // 30
			"), ;, `, +, -, ==, !=, <, >, <=, >=, ++, --", // 31
			"(, number, id", // 32
			"(, number, id", // 33
			"), ;, `, +, -, ==, !=, <, >, <=, >=, ++, --, *, /", // 34
			"), ;, `, +, -, ==, !=, <, >, <=, >=, ++, --, *, /", // 35
			"(, number, id", // 36
			"(, number, id", // 37
			"), ;, `, +, -, ==, !=, <, >, <=, >=, ++, --, *, /", // 38
			"), ;, `, +, -, ==, !=, <, >, <=, >=, ++, --, *, /", // 39
			"), ;, `, +, -, ==, !=, <, >, <=, >=, ++, --, *, /", // 40
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 41
			";, `", // 42
			";, `", // 43
			";, `", // 44
			";, `", // 45
			";, `", // 46
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 47
			")", // 48
			")", // 49
			")", // 50
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", // 51
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", //52
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break", //53
			"default, case", // 54
			"default, case", // 55
			"default, case", // 56
			"}", // 57
			"<script_end>, if, while, for, id, var, function_name, switch, comment_token, }, break" // 58
	};
	
    static ArrayList<ArrayList<String>> followSetToTokenList = new ArrayList<ArrayList<String>>();
    
    void makefollowSetToTokenList() {
    		MakeFollowSetList makeFollowSetList = new MakeFollowSetList();
		for(String followset : followSetList) {
			makeFollowSetList.FollowsetPrinter(followset);
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
	
    public void FollowsetPrinter(String bnf){
        String[] stringBNF;
    		ArrayList<String> tokens = new ArrayList<String>();
    		stringBNF = bnf.split(",");
        for(int i=0; i< stringBNF.length ;i++){
        		stringBNF[i] = stringBNF[i].trim();
        		tokens.add(stringBNF[i]);
        		
        }
        followSetToTokenList.add(tokens);
    }
    
    ArrayList<ArrayList<String>> getfollowSetToTokenList() {
    		return followSetToTokenList;
    }

}
