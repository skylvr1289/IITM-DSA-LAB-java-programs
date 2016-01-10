package practice;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DetectCycle_DFS {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int N = sc.nextInt();
		int E = sc.nextInt();
		int adjMat[][] = new int[N][N];
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMat[from][to] = 1;
			adjMat[to][from] = 1;
		}
		Set<Integer> visited = new HashSet<Integer>();
		boolean cycle = false;
		for (int i = 0; i < N; i++) {
			if (!visited.contains(i)) {
				if (isCycle(adjMat, i, visited, -1)) {
					cycle = true;
					break;
				}
			}
		}

		System.out.println(cycle ? "YES" : "NO Cycle");
	}

	private static boolean isCycle(int[][] adjMat, int u, Set<Integer> visited, int parent) {
		visited.add(u);
		int neigh[] = adjMat[u];

		for (int v = 0; v < neigh.length; v++) {
			if (adjMat[u][v] == 1 && !visited.contains(v)) {
				if (isCycle(adjMat, v, visited, u)) {
					return true;
				}
			} else if (adjMat[u][v] == 1 && parent != v) {
				return true;
			}
		}
		return false;
	}

}


 