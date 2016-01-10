package adsa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Kruskal {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		/// Enter no oif NOdes...///
		int NO_OF_NODES = sc.nextInt();
		int NO_OF_EDGES = sc.nextInt();

		// initialize union find set
		UF uf = new UF(NO_OF_NODES);

		final Map<Edge, Integer> edgeWeightMap = new HashMap<Edge, Integer>();

		System.out.println("enter the edges and weights");
		for (int i = 0; i < NO_OF_EDGES; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			Edge eForw = new Edge(from, to);
			edgeWeightMap.put(eForw, weight);

		}

		PriorityQueue<Edge> priorityQ = new PriorityQueue<Edge>(NO_OF_EDGES, new Comparator<Edge>() {

			@Override
			public int compare(Edge e1, Edge e2) {
				if (edgeWeightMap.get(e1) > edgeWeightMap.get(e2)) {
					return 1;
				} else if (edgeWeightMap.get(e1) < edgeWeightMap.get(e2)) {
					return -1;
				}
				return 0;
			}
		});
		// KRUSKAL Begins
		for (Edge e : edgeWeightMap.keySet()) {
			priorityQ.add(e);
		}
		int source = 1;
		List<Edge> mst = new LinkedList<Edge>();
		while (uf.sizeOfNetwork(source) <= NO_OF_NODES - 1) {

			Edge e = priorityQ.remove();
			int u = e.from;
			int v = e.to;
			if (uf.find(u) == uf.find(v)) {
				continue;
			}
			// otherwise
			uf.union(u, v);
			mst.add(e);
		}

		// KRUSKAL Ends

		System.out.println(mst);
		int weightMST = 0;
		for (Edge edge : mst) {
			weightMST += edgeWeightMap.get(edge);
		}
		System.out.println("weight of the MST : " + weightMST);
	}

	private static class Edge {
		int from;
		int to;

		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + from;
			result = prime * result + to;
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
			if (from != other.from)
				return false;
			if (to != other.to)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return this.from + "  -->" + this.to;
		}
	}

	private static class UF {
		int parent[];
		int rank[];

		public UF(int size) {
			parent = new int[size + 1];
			rank = new int[size + 1];
			for (int i = 1; i <= size; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
		}

		public int find(int x) {
			if (parent[x] == x) {
				return x;
			} else {
				int result = find(parent[x]);
				parent[x] = result;
				return result;
			}
		}

		public void union(int u, int v) {
			int h1 = find(u);
			int h2 = find(v);
			if (h1 == h2) {
				return;
			}
			if (rank[h1] > rank[h2]) {
				parent[h2] = h1;
				rank[h1] += rank[h2];

			} else {
				parent[h1] = h2;
				rank[h2] += rank[h1];
			}
		}

		public int sizeOfNetwork(int x) {
			int h = find(x);
			return rank[h];
		}

		public void print(int x) {
			int h = find(x);
			System.out.print("Network:  ");
			for (int i = 0; i < parent.length; i++) {
				if (find(i) == h) {
					System.out.print(i + " ");
				}
			}
			System.out.println();

		}
	}
}