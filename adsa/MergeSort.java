package adsa;

public class MergeSort {
	public static void main(String[] args) {
		int a[] = { 900 };
		int b[] = { 5, 8, 10, 20 };
		// order N
		int c = getMedianByMerge(a, b);
		System.out.println(c);
	}

	private static int getMedianByMerge(int[] a, int[] b) {
		int c[] = new int[a.length + b.length];
		int n1 = a.length;
		int n2 = b.length;
		int n = 0;
		n = (n1 + n2) / 2;
		boolean isEven = ((n1 + n2) % 2 == 0);

		int median = -1;
		int i = 0, j = 0, k = 0;
		while (i < a.length && j < b.length) {
			if (a[i] <= b[j]) {
				c[k] = a[i++];
			} else {
				c[k] = b[j++];
			}
			if (k == n) {
				median = getMedian(c, k, isEven);
				return median;
			}
			k++;

		}
		// copy the rest elements
		if (j < b.length) {
			for (int k2 = j; k2 < b.length; k2++) {
				c[k] = b[k2];
				if (k == n) {
					median = getMedian(c, k, isEven);
					return median;
				}
				k++;

			}
		}

		if (i < a.length) {
			for (int k2 = i; k2 < a.length; k2++) {
				c[k] = b[k2];
				if (k == n) {
					median = getMedian(c, k, isEven);
					return median;
				}
				k++;
			}
		}

		return median;
	}

	public static void print(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i]);
		}
	}

	static int getMedian(int arr[], int k, boolean isEven) {
		int median = -1;
		if (isEven) {
			median = (arr[k] + arr[k - 1]) / 2;
			return median;
		} else {
			median = arr[k];
			return median;
		}
	}

	static int getMedianByDivideAndConquer(int a[],int l1,int h1, int b[],int l2,int h2) {
		int median = -1;
		// lets supppose both arre odd
		int n1 = a.length;
		int n2 = b.length;

		int m1 = a[n1 / 2];
		int m2 = b[n2 / 2];
		if (m1 == m2) {
			return m1;
		}
		if (n1 == 2 && n2 == 2) {
			median = (Math.max(a[0], b[0] + Math.min(a[1], b[1]))) / 2;
			return median;
		}

		if(m1>m2){
			return getMedianByDivideAndConquer(a, b);
		}
	}
}