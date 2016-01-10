package apl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class GrahamScan {
	static Scanner sc = new Scanner(System.in);
	private static final String ZZZ = "zzz";
	static Point p0;

	public static void main(String[] args) {

		int NO_OF_POINTS = sc.nextInt();
		List<Point> points = new ArrayList<Point>();
		for (int i = 0; i < NO_OF_POINTS; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			Point p = new Point(x, y);
			points.add(p);
		}
		/*
		 * The key Idea is, Get the outer convex hull from the points & remove
		 * them. again get the outer convex hull & then remove the points.
		 * until all points are printed.
		 */
		while (!points.isEmpty()) {
			Stack<Point> stack = grahamScanConvexHull(points);

			List<Point> list = new ArrayList<Point>(stack);

			for (int j = 0; j < list.size(); j++) {
				System.out.println(list.get(j));
			}
			System.out.println(ZZZ);
			// remove the outer convex hull points
			while (!stack.isEmpty()) {
				points.remove(stack.pop());
			}
		}

	}

	public static Stack<Point> grahamScanConvexHull(List<Point> points) {

		int n = points.size();
		int yBottom = points.get(0).y, bottomMostPointIndex = 0;
		// find the most bottom left point
		for (int i = 1; i < n; i++) {
			int y = points.get(i).y;
			if ((y < yBottom) || (yBottom == y && points.get(i).x < points.get(bottomMostPointIndex).x)) {
				yBottom = points.get(i).y;
				bottomMostPointIndex = i;
			}
		}

		// bring the bottom most point to the 0 index of the list
		Point temp = points.get(bottomMostPointIndex);
		Point idxPoint = points.get(0);
		points.remove(bottomMostPointIndex);
		points.add(bottomMostPointIndex, idxPoint);

		points.remove(0);
		points.add(0, temp);

		p0 = points.get(0);
		// sort the points based on Polar angles. wrt point p0.
		Collections.sort(points, new PolarAngleComparator());

		if (n <= 3) {
			// only 3 points will always be on a convex hull
			Stack<Point> stack = new Stack<Point>();
			for (int j = 0; j < n; j++) {
				stack.add(points.get(j));
			}
			return stack;
		}
		// otherwise proceed
		Stack<Point> stack = new Stack<Point>();
		stack.push(points.get(0));
		stack.push(points.get(1));
		stack.push(points.get(2));

		for (int i = 3; i < n; i++) {
			while (isLeft(getSecondTop(stack), stack.peek(), points.get(i)) < 0) {
				stack.pop();
			}
			stack.push(points.get(i));
		}
		return stack;
	}

	static Point getSecondTop(Stack<Point> stack) {
		Point p = stack.pop();
		Point secTop = stack.peek();
		stack.push(p);
		return secTop;
	}

	/*
	 * to check if the point p2 is on the left of the segment p0-->p1
	 * =0 ---> p2 is on the line segment p0<-->p1
	 * >0 ---> p2 is on the left(CCW) of segment p0<-->p1
	 * <0 ---> p2 is on the right(CW) of segment p0<-->p1
	 */
	public static int isLeft(Point p0, Point p1, Point p2) {
		return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
	}

	public static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
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
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return x + " " + y;
		}
	}

	public static class PolarAngleComparator implements Comparator<Point> {

		@Override
		public int compare(Point p1, Point p2) {
			int isL = isLeft(p0, p1, p2);
			if (isL > 0) {
				return -1;
			} else if (isL < 0) {
				return 1;
			}
			return 0;
		}
	}
}
