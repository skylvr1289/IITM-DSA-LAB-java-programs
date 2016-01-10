package adsa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class DijsktraShortestPath {
	static final int INFINITY = Integer.MAX_VALUE;
	static int NO_OF_EDGES;
	static int NO_OF_VERTICES;
	static Map<Edge, Integer> edges = new HashMap<Edge, Integer>();
	static Map<String, Integer> verticesMap = new HashMap<String, Integer>();
	static Map<String, Set<String>> adjMatrix = new HashMap<String, Set<String>>();

	public static void main(String[] args) {
		PriorityQueue<String> pQueue = new PriorityQueue<String>(1000, new Comparator<String>() {
			@Override
			public int compare(String v1, String v2) {
				if (verticesMap.get(v1) > verticesMap.get(v2)) {
					return 1;
				}
				if (verticesMap.get(v1) < verticesMap.get(v2)) {
					return -1;
				}
				return 0;
			}
		});
		// Inputs
		Scanner sc = new Scanner(System.in);
		NO_OF_EDGES = sc.nextInt();
		for (int i = 0; i < NO_OF_EDGES; i++) {
			String f = sc.next();
			String t = sc.next();
			int pathWeight = sc.nextInt();
			// for undirected graph
			Edge eForw = new Edge(f, t);
			Edge eBack = new Edge(t, f);
			if (!f.equals(t)) {
				edges.put(eForw, pathWeight);
				edges.put(eBack, pathWeight);
			}
		}

		// input source Vertex
		String source = sc.next();

		/* Dijsktra Algorithm */

		// initialize PQueue
		initialize(edges);
		initializePqueue(source, verticesMap, pQueue);
		Set<String> visited = new HashSet<String>();
		Set<String> unVisited = new HashSet<String>();
		for (String v : verticesMap.keySet()) {
			unVisited.add(v);
		}

		while (!pQueue.isEmpty()) {
			String u = pQueue.remove();
			visited.add(u);

			Set<String> neighbours = adjMatrix.get(u);
			for (String v : neighbours) {
				if (visited.contains(v)) {
					continue;
				}
				// otherwise
				Edge e1 = new Edge(u, v);
				int w = verticesMap.get(u) + edges.get(e1);// +e.pathWeight

				if (w < verticesMap.get(v)) {
					pQueue.remove(v);
					verticesMap.put(v, w);
					pQueue.add(v);
				}
			}
		}
		System.out.println("final Vertices:" + verticesMap);
	}

	private static void initializePqueue(String source, Map<String, Integer> vertices, PriorityQueue<String> pQueue) {
		pQueue.addAll(verticesMap.keySet());
		pQueue.remove(source);
		verticesMap.put(source, 0);
		pQueue.add(source);
	}

	private static void initialize(Map<Edge, Integer> edges) {
		for (Edge e : edges.keySet()) {
			String f = e.from;
			String t = e.to;
			verticesMap.put(f, INFINITY);
			verticesMap.put(t, INFINITY);

			if (adjMatrix.get(f) == null) {
				adjMatrix.put(f, new HashSet<String>());
				adjMatrix.get(f).add(t);
			} else {
				adjMatrix.get(f).add(t);
			}
			if (adjMatrix.get(t) == null) {
				adjMatrix.put(t, new HashSet<String>());
				adjMatrix.get(t).add(f);
			} else {
				adjMatrix.get(t).add(f);
			}
		}
	}

	private static class Edge {
		String from;
		String to;

		Edge(String from, String to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((from == null) ? 0 : from.hashCode());
			result = prime * result + ((to == null) ? 0 : to.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (from == null) {
				if (other.from != null)
					return false;
			} else if (!from.equals(other.from))
				return false;
			if (to == null) {
				if (other.to != null)
					return false;
			} else if (!to.equals(other.to))
				return false;
			return true;
		}
	}
}