package practice;

public class Contour {
	static Node head = null;

	public static void main(String[] args) {
		// Node n1 = new Node(20);
		// head = n1;
		// head.left = new Node(8);
		// head.right = new Node(22);
		// head.left.left = new Node(4);
		// head.left.right = new Node(12);
		// head.left.right.left = new Node(10);
		// head.left.right.right = new Node(14);
		// head.right.right = new Node(25);
		insert(20);
		insert(8);
		insert(22);
		insert(4);
		insert(12);
		insert(10);
		insert(14);
		insert(25);

		inOrder(head);
		System.out.println("\n boundry , Anti clock wise: ");
		printBoundryAntiClock(head);
	}

	static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {

			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	static void printLeftBoundry(Node root) {
		if (root != null) {
			if (root.left != null) {
				System.out.print("-->" + root.data);
				printLeftBoundry(root.left);
			} else if (root.right != null) {
				System.out.println("-->" + root.data);
				printLeftBoundry(root.right);
			}
		}
	}

	static void printRightBoundry(Node root) {
		if (root != null) {
			if (root.right != null) {
				System.out.print("-->" + root.data);
				printRightBoundry(root.right);
			} else if (root.left != null) {
				System.out.println("-->" + root.data);
				printRightBoundry(root.left);
			}

		}
	}

	static void printLeaves(Node root) {
		if (root != null) {
			printLeaves(root.left);
			if (root.left == null && root.right == null) {
				System.out.print("-->" + root.data);
			}
			printLeaves(root.right);
		}
	}

	static void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print("-->" + root.data);
			inOrder(root.right);
		}
	}

	static void insert(int data) {
		if (head == null) {
			head = new Node(data);
			return;
		}
		// otherwise
		Node cur = head;
		Node prev = head;
		Node temp = new Node(data);
		while (cur != null) {
			prev = cur;
			if (data < cur.data) {
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}
		if (data < prev.data) {
			prev.left = temp;
		} else {
			prev.right = temp;
		}
	}

	public static void printBoundryAntiClock(Node head) {
		printLeftBoundry(head);
		printLeaves(head);
		printRightBoundry(head.right);
	}
}
