package practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BFS_bipartiteCheck {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int N = sc.nextInt();
		int E = sc.nextInt();
		String source = sc.next();
		Map<String, Set<String>> adjList = new HashMap<String, Set<String>>();

		for (int i = 0; i < E; i++) {
			String from = sc.next();
			String to = sc.next();
			if (adjList.get(from) == null) {
				adjList.put(from, new HashSet<String>());
			}
			adjList.get(from).add(to);
			if (adjList.get(to) == null) {
				adjList.put(to, new HashSet<String>());
			}
			adjList.get(to).add(from);

		}
		//System.out.println(adjList);
		bfs(adjList, source);
		System.out.println("\n" + ((boolean) isBiartite(adjList, source) ? "BI-partite" : "Not Bipartite"));
	}

	static void bfs(Map<String, Set<String>> adjList, String vertex) {
		Queue<String> queue = new LinkedList<String>();
		queue.add(vertex);
		Set<String> visited = new HashSet<String>();
		visited.add(vertex);
		while (!queue.isEmpty()) {
			String item = queue.poll();
			visited.add(item);
			System.out.print("-->" + item);
			Set<String> neigh = adjList.get(item);
			for (String v : neigh) {
				if (!visited.contains(v)) {
					queue.add(v);
				}
			}
		}
	}

	static boolean isBiartite(Map<String, Set<String>> adjList, String source) {
		boolean isBipartite = true;

		Queue<String> queue = new LinkedList<String>();
		queue.add(source);
		Map<String, Integer> colorMap = new HashMap<String, Integer>();
		colorMap.put(source, 1);
		while (!queue.isEmpty()) {
			String u = queue.poll();

			System.out.print("-->" + u);
			Set<String> neigh = adjList.get(u);
			for (String v : neigh) {
				if (colorMap.get(v) == null) {
					colorMap.put(v, 1-colorMap.get(u)  );
					queue.add(v);
				} else if (colorMap.get(v) == colorMap.get(u)) {
					return false;
				}
			}
		}
		System.out.println(colorMap);
		return true;
	}
}