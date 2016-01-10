package practice;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class CstronglyConnectedComps {
	/*
5 5
1 0
2 1
0 2
0 3
3 4
	 * 
	 */
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int N = sc.nextInt();
		int E = sc.nextInt();
		int adjMatrix[][] = new int[N][N];

		// take the Edges inputs
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[from][to] = 1;
		}
		// printMatrix(adjMatrix);

		Set<Integer> visited = new HashSet<Integer>();
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < N; i++) {
			if (!visited.contains(i)) {
				DFS_post(i, visited, adjMatrix, stack);
			}
		}
		// System.out.println(stack);

		// reverse the graph.
		int revAdjMatrix[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				revAdjMatrix[i][j] = adjMatrix[j][i];
			}
		}
		Set<Integer> revVisited = new HashSet<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		int count = 0;
		while (!stack.isEmpty()) {
			count++;
			int num = stack.peek();
			DFS(num, revVisited, revAdjMatrix, stack2);
			System.out.println("comp:" + count + ": " + stack2);
			stack.removeAll(stack2);
			stack2.clear();

		}
		System.out.println("Num of St COnnected COmps: " + count);
	}

	public static void DFS_post(int node, Set<Integer> visited, int[][] adjMatrix, Stack<Integer> stack) {
		if (visited.contains(node)) {
			return;
		}
		// otehrwise proceed.
		visited.add(node);
		int neigh[] = adjMatrix[node];
		for (int v = 0; v < neigh.length; v++) {
			if (adjMatrix[node][v] == 1) {
				DFS_post(v, visited, adjMatrix, stack);
			}
		}
		stack.push(node);
	}

	public static void DFS(int node, Set<Integer> visited, int[][] adjMatrix, Stack<Integer> stack) {
		if (visited.contains(node)) {
			return;
		}
		// otehrwise proceed.
		visited.add(node);
		stack.push(node);
		// System.out.print("-->" + node);
		int neigh[] = adjMatrix[node];
		for (int v = 0; v < neigh.length; v++) {
			if (adjMatrix[node][v] == 1) {
				DFS(v, visited, adjMatrix, stack);
			}
		}

	}

	public static void printMatrix(int a[][]) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
	}
}