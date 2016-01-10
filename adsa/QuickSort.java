package adsa;

public class QuickSort {
	static int a[] = { 1212, 32, 3, 33, 2323, 3232, 323, 3, 2, 0, 321, 12, 3, 3, 1, 312, 1 };

	public static void main(String[] args) {
		quickSort(0, a.length - 1);
		printArray(a);
	}

	private static void quickSort(int low, int high) {
		if (low == high || low > high) {
			return;
		}
		if (low < high) {
			int pivotId = partition(low, high);
			quickSort(low, pivotId - 1);
			quickSort(pivotId + 1, high);
		}
	}

	private static int partition(int low, int high) {
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

	public static void printArray(int a[]) {
		for (int i : a) {
			System.out.print(i + " ");
		}
	}
}