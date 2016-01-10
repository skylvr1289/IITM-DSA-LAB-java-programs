package adsa;

public class FindLargestInRotatedSortedArray {
	static int a[] = { 7,8,9,10,0,2,3,4,5};

	public static void main(String[] args) {
		System.out.println(findLargest(0, a.length - 1));
	}

	private static int findLargest(int low, int high) {
		if (low == high) {
			return a[low];
		}
		if ((high - low) == 1) {
			return a[low] > a[high] ? a[low] : a[high];
		}
		if (low < high) {
			int mid = (low + high) / 2;
			if (a[mid] > a[low]) {
				return findLargest(mid, high);
			} else {
				return findLargest(low, mid);
			}
		}
		return a[low];
	}
}
