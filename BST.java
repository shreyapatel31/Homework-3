package homework3;

public class BST {
	
	private static final boolean RED = true;
	private Node root;
	private Node child;
	private int search = 0;
	private int size = 0;
	int max = 10;
	int [] arr = new int [max];
 	
	 class Node {
		int key;
		Node next;
		private Node left;
		private Node right;
		boolean color;
		
		public Node (int val) {
			key = val;
			left = null;
			right = null;
		}
	}
	
	public BST() {
		root = null;
	}
	
	public void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
	}
	
	public int [] doubleArray() {
		max += max;
		int [] d = new int [max];
		for(int i = 0; i < max; i++) {
			d[i] = arr[i];
		}
		return d;
	}
	
	public void set(int n, Node node) {
		
		if(n > node.key) {
			if(node.right != null) {
				set(n, node.right);
			}
			else {
				node.right = new Node (n);
			}
		}
		else if(n < node.key) {
			if(node.left != null) {
				set(n, node.left);
			}
			else {
				node.left = new Node (n);
			}
		}
	}
	
	public void put (int key) {
		
		if(root == null) {
			root = new Node (key);
			arr[0] = root.key;
		}
		else {
			Node node = root;
			set(key, node);
			sort(key);
		}
		size++;
		if(size == max) {
			arr = doubleArray();
		}
	}
	
	public void sort(int n) {
		for (int i = 0; i < max; i++) {
			if(n < arr[i]) {
				for(int j = max - 1; j > i; j++) {
					arr[j] = arr[j - 1];
				}
				arr[i] = n;
				break;
			}
			else if(arr[i] == 0) {
				arr[i] = n;
				break;
			}
		}
	}
	
	Node arrayToBst (int [] a, int start, int end) {
		if(start > end) {
			return null;
		}
		int mid = a.length / 2;
		Node node = new Node (a[mid]);
		
		node.left = arrayToBst(a, start, mid - 1);
		node.right = arrayToBst(a, mid + 1, end);
		
		return node;
	}
	
	public void put(int [] a) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] > a[i+1]) {
				swap(a[i], a[i+1]);
			}
		}
		arrayToBst(a, 0, a.length);
	}
	
	public int search (int key, Node node) {
		int num = 0;
		if(key == node.key) {
			num = node.key;
		}
		if(key < node.key) {
			if(node.left != null) {
				search(key, node.left);
			}
		}
		else if (key > node.key) {
			if(node.right != null) {
				search(key, node.right);
			}
		}
		
		search++;
		return num;
	}
	public int search (int key) {
		
		int num = search(key, root);
		if (num == 0) {
			System.out.println("Value cannot be found!");
		}
		System.out.println("Number of comparisons made:" + search);
		return num;
	}
	
	public int returnSize() {
		return size;
	}
	
	public  int[] sortedTree() {
		return arr;
	}
	
	public BST.Node balanceTreeOne() {
		sortedTree();
		root = arrayToBst(arr, 0, arr.length);
		
		return root;
	}
	
	Node rotateRight(Node h) {
		Node x = h.left;
		
		if(x.right != null) {
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		}
		return x;
	}
	
	Node rotateLeft(Node h) {
		Node x = h.right;
		
		if(x.left != null) {
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		}
		return x;
	}
	
	public void transformToList() {
		Node current = root;
		
		while(current != null) {
			if(current.left != null) {
				rotateRight(child);
				current = current.next;
			}
			else {
				current = current.next;
			}
		}
	}
	
	public void balanceTreeTwo() {	
		transformToList();
		Node node = root;
		int N = size;
		double x = Math.log(N)/Math.log(2);
		double M = (N+1) - Math.pow(2,floor(x,root));
		
		for (int i = 0; i < M; i++) {
			node = rotateLeft(node);
			node = node.right;
		}
		
		node = root;
		double K = x - 1;
		while(K > 1) {
			node = node.right;
			node = rotateLeft(node);
			if(K == 1) {
				node = rotateLeft(root);
			}
			K--;
		}
		
	}
	
	public int floor(double key, Node node) {
		int value = node.key;
		if(key == node.key) {
			value = node.key;
		}
		else if (key > node.key) {
			if(node.right != null) {
				value = floor(key, node.right);
			}
		}
		else if(key < node.key) {
			if(node.left != null) {
				value = floor(key, node.left);
			}
		}
		
		return value;
	}
	
	public static void main (String [] args) {
		BST tree = new BST ();
		tree.put(4);
		int [] a = new int [] {5, 15, 3, 4, 7, 11, 10, 1, 6, 18};
		tree.put(a);
	}
}
