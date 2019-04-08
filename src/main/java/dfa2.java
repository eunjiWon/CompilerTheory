import java.util.HashMap;
import java.util.Scanner;

public class dfa2 extends DFA{
	public static void main(String[] args) {
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('#', 0);
		map.put('$', 1);
		map.put('a', 2);
		map.put('b', 3);
		map.put('x', 4);
		map.put('y', 5);
		map.put('0', 6);
		map.put('1', 7);
		map.put('2', 8);
		map.put('3', 9);
		map.put('4', 10);
		map.put('5', 11);
		map.put('6', 12);
		map.put('7', 13);
		map.put('8', 14);
		map.put('9', 15);
		boolean[] accept = {false, false, false, true, false, false, true, false};
		int[][] T = {
				{1,4,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
				{7,7,2,2,7,7,7,7,7,7,7,7,7,7,7,7},
				{7,7,7,7,7,7,3,3,3,3,3,3,3,3,3,3},
				{7,7,7,7,7,7,3,3,3,3,3,3,3,3,3,3},
				{7,7,7,7,5,5,7,7,7,7,7,7,7,7,7,7},
				{7,7,7,7,7,7,6,6,6,6,6,6,6,6,6,6},
				{7,7,7,7,7,7,6,6,6,6,6,6,6,6,6,6},
				{7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7}
		};
		dfa2 dfa_2 = new dfa2();
		dfa_2.print();
		dfa_2.setTrasitionTable(map, accept, T);
		dfa_2.checkDFA();	
	}
	
	public void print() {
		System.out.println("This is a dfa2");
	}
	
}
