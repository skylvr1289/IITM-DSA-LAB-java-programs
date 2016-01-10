package practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/*
 * 6 6
 5 2
 5 0
 4 0
 4 1
 2 3
 3 1
 */
public class DFS_Topological {
	static Scanner sc = new Scanner(System.in);
	static Map<Integer, Set<Integer>> adjMatrix = new HashMap<Integer, Set<Integer>>();
	static Set<Integer> visited = new HashSet<Integer>();

	public static void main(String[] args) {

		int NO_OF_NODES = sc.nextInt();
		int NO_OF_EDGES = sc.nextInt();

		for (int i = 0; i <= NO_OF_NODES; i++) {
			adjMatrix.put(i, new HashSet<Integer>());
		}
		for (int i = 0; i < NO_OF_EDGES; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			if (adjMatrix.get(from) == null) {
				adjMatrix.put(from, new HashSet<Integer>());
			} else {
				adjMatrix.get(from).add(to);
			}
		}
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i <= NO_OF_NODES; i++) {

			if (!visited.contains(i)) {
				System.out.print("new trversal: ");
				dfs_recursive(i, stack);
				System.out.println();
			}

		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}

	}

	/*
	 * 10 9 1 2 1 4 2 3 3 4 3 5 2 6 6 7 5 7 5 8 8 9
	 */
	public static void dfs_recursive(int i, Stack<Integer> stack) {
		int j;

		visited.add(i); // Mark node as "visited"

		// System.out.print(" ->" + i);
		Set<Integer> NNodes = adjMatrix.get(i);
		if (NNodes.isEmpty()) {
			// return;
		}
		for (Integer node : NNodes) {
			if (!visited.contains(node)) {
				dfs_recursive(node, stack); // Visit node
			}
		}
		stack.push(i);
	}
}