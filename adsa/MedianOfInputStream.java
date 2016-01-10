/* Find median of the input stream.
// take two heaps, maxx and min heaps
// if num<maxHeap ... addd to maxHeap 
else add to minHeap

// now balance the heaps if size diff is >1
remove the root element .. and add to other..

now find the median.
*/

package adsa;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MedianOfInputStream {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return -1;
				} else if (o1 < o2) {
					return 1;
				}
				return 0;
			}
		});
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return 1;
				} else if (o1 < o2) {
					return -1;
				}
				return 0;
			}
		});

		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int number = sc.nextInt();
			int median = 0;
			if (minHeap.size() == 0 && maxHeap.size() == 0) {
				maxHeap.add(number);
				System.out.println("Median:" + number);
				continue;
			}
			int max = maxHeap.peek();
			if (number < max) {
				maxHeap.add(number);
			} else {
				minHeap.add(number);
			}

			int minHSize = minHeap.size();
			int maxHSize = maxHeap.size();
			// balance the heaps
			if ((maxHSize - minHSize) > 1) {
				minHeap.add(maxHeap.remove());
			} else if ((minHSize - maxHSize) > 1) {
				maxHeap.add(minHeap.remove());
			}
			// calculate median
			if (minHeap.size() == maxHeap.size()) {
				median = (minHeap.peek() + maxHeap.peek()) / 2;
			} else if (maxHSize > minHSize) {
				median = maxHeap.peek();
			} else {
				median = minHeap.peek();
			}
			System.out.println("Median: " + median);
		}
	}
}