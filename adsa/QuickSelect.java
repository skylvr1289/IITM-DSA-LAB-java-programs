 
package adsa;

public class QuickSelect {
	static int a[] = { 3, 1, 2, 4, 5, 2, 7, 6, 9 };

	public static void main(String[] args) {
		System.out.println(quickSelect(0, a.length - 1, 1));

	}

	private static int quickSelect(int low, int high, int k) {
		if (low == high || low > high) {
			return a[k - 1];
		}
		if (low < high) {
			int pivotId = partition(low, high);
			
			
			int l = pivotId - low - 1;
			int r = high - pivotId - 1;
			if (l < k) {
				return quickSelect(pivotId + 1, high - 1, k);
			} else if (l > k) {
				return quickSelect(low, pivotId - 1, k);
			}

		}
		return a[k - 1];
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
}