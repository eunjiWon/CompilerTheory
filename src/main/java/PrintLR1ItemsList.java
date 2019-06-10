import java.util.ArrayList;

public class PrintLR1ItemsList {
	public static void main(String[] args) {
		AutoRuleGenerator autoRuleGenerator = new AutoRuleGenerator();
		autoRuleGenerator.makeRuleList();
		ArrayList<ArrayList<String>> BNFSplitToTokenList = new ArrayList<ArrayList<String>>();
		BNFSplitToTokenList = autoRuleGenerator.getBNFList();
		autoRuleGenerator.printRuleList(BNFSplitToTokenList);
		MakeFollowSetList makeFollowSetList = new MakeFollowSetList();
		makeFollowSetList.makefollowSetToTokenList();
		ArrayList<ArrayList<String>> FollowSetToTokenList = new ArrayList<ArrayList<String>>();
		FollowSetToTokenList = makeFollowSetList.getfollowSetToTokenList();
		makeFollowSetList.printRuleList(FollowSetToTokenList);

	
		
		// Add period (.) 
		for(int a = 0; a < BNFSplitToTokenList.size(); a++) {
			int cnt = BNFSplitToTokenList.get(a).size() - 1;
			for(int i = 0; i < cnt; i++) {
				ArrayList<String> temp = new ArrayList<>();
				temp.addAll(BNFSplitToTokenList.get(a));
				temp.add(i + 2, ".");
				for(int k = 0; k < FollowSetToTokenList.get(a).size(); k++) {
					// print
					System.out.print("[ " + temp.get(0) + " " + temp.get(1) + " ");
					for(int j = 0; j < temp.size() - 2; j++) {
						System.out.print(temp.get(j + 2));
						System.out.print(" ");
					}
					System.out.print(", " + FollowSetToTokenList.get(a).get(k) + " ]");
					System.out.print("\n");
					// 
				}
				
			
			}
			
		}
		
		
	}

}
