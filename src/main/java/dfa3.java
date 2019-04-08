import java.util.HashMap;
import java.util.Scanner;

public class dfa3 extends DFA{
	public static void main(String[] args) {
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('a', 0);
		map.put('b', 1);
		map.put('c', 2);
		map.put('x', 3);
		map.put('y', 4);
		map.put('z', 5);
		boolean[] accept = {false, false, true, false, false, true, false, true, false};
		int[][] T = {
				{1,8,8,3,8,8},
				{8,1,2,8,8,8},
				{8,8,8,8,8,8},
				{8,8,8,8,4,5},
				{6,8,8,3,8,8},
				{8,8,8,8,8,8},
				{8,6,7,8,8,8},
				{8,8,8,8,8,8},
				{8,8,8,8,8,8}
		};
		dfa3 dfa_3 = new dfa3();
		dfa_3.print();
		dfa_3.setTrasitionTable(map, accept, T);
		dfa_3.checkDFA();	
	}
	
	public void print() {
		System.out.println("This is a dfa3");
	}
	
}
