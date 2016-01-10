package apl;

import java.util.Scanner;
import java.util.TreeMap;

import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.BasicMatrix.Factory;
import org.ojalgo.matrix.PrimitiveMatrix;

public class PageRank {
	static long[][] adj;
	static double[][] probab;

	static int NO_OF_NODES = 9000;
	static int NO_OF_EDGES = 10;// 103689;

	// top N nodes to be printed
	static int TOP_N = 10;
	// upto 5 decimal points we have to check the difference
	static double DIFF_CONST = 0.000001;

	static long[] degree;
	static double initialProbVector[][];// 1xn
	static Scanner sc = new Scanner(System.in);
	static final Factory<BasicMatrix> tmpFactory = PrimitiveMatrix.FACTORY;
	static BasicMatrix matrix;//
	static BasicMatrix vector;

	public static void main(String[] args) {
		// read_matrix();

		BasicMatrix m = tmpFactory.makeZero(2, 2);
		m=m.replace(0, 0, 1);
		System.out.println(m);

		BasicMatrix matrix = tmpFactory.rows(probab);
		BasicMatrix vector = tmpFactory.rows(initialProbVector);

		// v*A--> v*(v*A)
		BasicMatrix result = vector;
		BasicMatrix result_prev = vector;
		int count = 0;
		for (count = 0; count < 10000;) {
			result_prev = result;
			result = result.multiplyRight(matrix);
			// find the difference of the vectors. upto decimal 5 places.
			if (isDiffAlmostZero(result_prev.subtract(result))) {
				break;
			}
			count++;
		}

		TreeMap<Double, Integer> treeMap = new TreeMap<Double, Integer>();
		for (int i = 0; i < NO_OF_NODES; i++) {
			treeMap.put(result.doubleValue(0, i), i);
		}

		// RUN THIS UPTO 10
		for (int i = 0; i < TOP_N && i < treeMap.size(); i++) {
			double key = treeMap.lastKey();
			System.out.println(" " + treeMap.get(key));
			treeMap.remove(key);
		}
	}

	private static boolean isDiffAlmostZero(BasicMatrix subtract) {

		for (int i = 0; i < subtract.size(); i++) {
			if (subtract.doubleValue(0, i) > DIFF_CONST) {
				return false;
			}
		}
		return true;
	}

	public static void read_matrix() {
		adj = new long[NO_OF_NODES][NO_OF_NODES];
		probab = new double[NO_OF_NODES][NO_OF_NODES];
		initialProbVector = new double[1][NO_OF_NODES];
		for (int i = 0; i < NO_OF_NODES; i++) {
			initialProbVector[0][i] = 1 / (double) 2;// (double) NO_OF_NODES;
		}
		degree = new long[NO_OF_NODES];

		double initialProb = 1 / (double) NO_OF_NODES;
		for (int i = 0; i < NO_OF_NODES; i++) {
			initialProbVector[0][i] = initialProb;
		}
		for (int i = 0; i < NO_OF_EDGES; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adj[a][b]++;

			// adj[a][b]++;
			degree[a]++;
		}

		for (int i = 0; i < NO_OF_NODES; i++) {
			for (int j = 0; j < NO_OF_NODES; j++) {
				double p = 0;
				if (degree[i] != 0) {
					p = (double) adj[i][j] / degree[i];
				}

				probab[i][j] = p;
			}
		}
	}

}