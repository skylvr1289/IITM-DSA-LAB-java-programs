package adsa;

public class LongestIncreasingSequence {
	public static void main(String[] args) {
		int a[] = { 1, 12, 7, 0, 23, 11, 52, 31, 61, 69, 70, 2 };

		int L[] = getLongestISlength(a);
		System.out.println("Lnthg= : " + L[a.length - 1]);
		printArray(a);
		printArray(L);

		int cur =  8;
		for (int i = L.length - 1; i >= 0; i--) {
			if (L[i] ==cur-1) {
				System.out.print(a[i] + " ");
				cur = L[i];
			}

		}
	}

	static int[] getLongestISlength(int a[]) {
		int L[] = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			L[i] = 1;
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i] && L[i] <= L[j]) {
					L[i] = L[j] + 1;
				}
			}
		}
		return L;
	}

	public static void printArray(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
