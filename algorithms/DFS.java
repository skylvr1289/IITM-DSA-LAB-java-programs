package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DFS {
	static Scanner sc = new Scanner(System.in);
	static Map<Integer, Set<Integer>> adjMatrix = new HashMap<Integer, Set<Integer>>();
	static Set<Integer> visited = new HashSet<Integer>();

	public static void main(String[] args) {
		int NO_OF_EDGES = sc.nextInt();
		int NO_OF_NODES = sc.nextInt();

		for (int i = 0; i <= NO_OF_NODES; i++) {
			adjMatrix.put(i, new HashSet<Integer>());
		}
		for (int i = 0; i < NO_OF_EDGES; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			if (adjMatrix.get(from) == null) {
				adjMatrix.put(from, new HashSet<Integer>());
			}
			adjMatrix.get(from).add(to);
			if (adjMatrix.get(to) == null) {
				adjMatrix.put(to, new HashSet<Integer>());
			}
			adjMatrix.get(to).add(from);
		}

		for (int i = 1; i <= NO_OF_NODES; i++) {

			if (!visited.contains(i)) {
				System.out.print("new trversal: ");
				dfs_recursive(i);
				System.out.println();
			}

		}
	}

	/*
	 * 10 9
	 * 1 2
	 * 1 4
	 * 2 3
	 * 3 4
	 * 3 5
	 * 2 6
	 * 6 7
	 * 5 7
	 * 5 8
	 * 8 9
	 */
	public static void dfs_recursive(int i) {
		int j;

		visited.add(i); // Mark node as "visited"

		System.out.print(" ->" + i);
		Set<Integer> NNodes = adjMatrix.get(i);
		if (NNodes.isEmpty()) {
			return;
		}
		for (Integer node : NNodes) {
			if (!visited.contains(node)) {
				dfs_recursive(node); // Visit node
			}
		}
	}
}

 