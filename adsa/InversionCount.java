package adsa;

public class InversionCount {
	public static void main(String[] args) {
		int a[] = { 2, 4, 1, 3, 5 };
		int count = 0;
		for (int i = 0; i < a.length - 1; i++) {
			int pId = partition(i, a.length - 1, a);
			System.out.println(pId);
		}
		System.out.println(count);
	}

	private static int partition(int low, int high, int a[]) {
		int pivot = low;
		int pItem = a[low];
		for (int i = low + 1; i <= high; i++) {
			if (a[i] < pItem) {
				a[pivot] = a[i];
				for (int j = i; j > pivot; j--) {
					a[j] = a[j - 1];
				}
				pivot = pivot + 1;
				a[pivot] = pItem;
			}
		}
		return pivot;
	}
}
