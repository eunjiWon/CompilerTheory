import java.util.HashMap;
import java.util.Scanner;

public class dfa1 extends DFA{
	public static void main(String[] args) {
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('a', 0);
		map.put('b', 1);
		boolean[] accept = {false, true, false, true, false};
		int[][] T = {
				{1, 4},
				{1, 2},
				{3, 4},
				{4, 2},
				{4, 4}
		};
		dfa1 dfa_1 = new dfa1();
		dfa_1.print();
		dfa_1.setTrasitionTable(map, accept, T);
		dfa_1.checkDFA();	
	}
	
	public void print() {
		System.out.println("This is a dfa1");
	}
}
