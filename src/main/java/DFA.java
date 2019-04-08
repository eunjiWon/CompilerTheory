import java.util.HashMap;
import java.util.Scanner;

public class DFA {
	Scanner scan = new Scanner(System.in);
	HashMap<Character, Integer> map = new HashMap<>();
	boolean[] accept;
	int[][] T;
	
	public void setTrasitionTable(HashMap<Character, Integer> map, boolean[] accept, int[][] T) {
		this.map = map;
		this.accept = accept;
		this.T = T;
	}
	
	public void checkDFA() {
		System.out.println("Type E or e to exit.");
		while(true) {
			System.out.print("Input: ");
			String input = scan.nextLine();
			int currentState = 0;
			int currentInput = 0;
			if(input.equals("E") || input.equals("e")) System.exit(0);
			if(input.equals(" ")) {
				System.out.println("Accepted");
				continue;
			}
			for(int i = 0; i < input.length(); i++) {
				if(null == map.get(input.charAt(i))){
					System.out.println("Terminate the program with character that is not in the alphabet.");
					System.exit(0);
				}
				else{
					currentInput = map.get(input.charAt(i));
					currentState = T[currentState][currentInput];
				}
			}
			if(accept[currentState]) System.out.println("Accepted");
			else System.out.println("Rejected");
		}
	
	}
	
}
