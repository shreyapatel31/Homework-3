package homework3;


public class DaryHeap {
	
	private int length = 8;
	private int child;
	private int heapSize = 0;
	private int [] a = new int [length];
	
	public DaryHeap(int d) {
		child = d;
	}
	
	
	public int parent(int k) {
		int parent = k / child;
		int rem = k % child;
		if(rem == 0) {
			parent -= parent;
		}
		return parent;
	}
	
	private void swim(int k){
	 if (k != 0) {
		 int parent = parent(k);
		 if(a[parent] < a[k]) {
			 exch(a, parent, k);
			 swim(parent);
		 }
	 }
	 
	}
	
	public void exch(int [] q, int r, int s) {
		int k = q[r];
		q[r] = q[s];
		q[s] = k;
	}
	
	public void insert(int k) {
		a[heapSize] = k;
		swim(heapSize);
		heapSize++;
		if(heapSize == length) {
			a = doubleArray();
		}
	}
	
	private void sink(int k) {
		
		int childSet = (k * child) + 1;
		if(childSet < heapSize) {
			int max = childSet;
			int counter = childSet + child;
			if(counter > heapSize) {
				counter = heapSize - 1;
			}
			for(int i = childSet; i < counter; i++) {
				if (a[i] > a[max]) {
					max = i;
				}
			}
			exch(a, max, k);
			sink(max);
		}
		
	}
		
	public int delMax() {
		int max = 0;
		int maxKey = 0;
		int last = heapSize - 1;
		for(int i = 0; i < last; i++) {
			if(a[i] > max) {
				max = a[i];
				maxKey = i;
			}
		}
		exch(a, last, maxKey);
		heapSize--;
		sink(maxKey);
		a[last] = 0;
		return max;
	}
	
	public int [] daryHeapsort() {
		int [] arraySort = new int [length];
		for(int i = 0; i < length; i++) {
			arraySort[i] = a[i];
		}
		int size = heapSize - 1;
		
		while(size > 2) {
			exch(arraySort, 0, size--);
			print(arraySort);
			sinkSorted(arraySort, 0, size);
		}
		
		for (int i = 1; i < child; i++) {
			if (arraySort[0] > arraySort[i]) {
				exch(arraySort, 0, i);
			}
		}
		
		return arraySort;
	}
	
	public int sinkSorted(int [] b, int k, int x) {
		int childSet = (k * child) + 1;
		int value = 0;
		if(childSet < x) {
			int large = childSet;
			int counter = childSet + child;
			if(counter > x) {
				counter = x;
			}
			for(int i = childSet; i < counter; i++) {
				if(b[i] > b[large]) {
					large = i;
				}
				System.out.println(b[i] + " ");
			}
			
			System.out.println(b[large]);
			if(b[k] < b[large]) {
				exch(b, large, k);
				print(b);
				sinkSorted(b, large, x);
				value = 1;
			}
		}
		return value;
	}
	
	public int [] doubleArray() {
		int y = length;
		length += length;
		int [] c = new int [length];
		for (int i = 0; i < y; i++) {
			c[i] = a[i];
		}
		return c;
	}
	
	public void print(int [] b) {
		for (int i = 0; i < heapSize + 1; i++) {
			System.out.println(b[i] + " ");
		}
		System.out.println();
	}
	
	public void print() {
		for (int i = 0; i < heapSize + 1; i++) {
			System.out.println(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void main (String [] args) {
		DaryHeap d = new DaryHeap(4);
		d.insert(3);
		d.insert(7);
		d.insert(15);
		d.insert(6);
		d.insert(4);
		d.insert(10);
		d.insert(11);
		System.out.println("Max:" + d.delMax());
		d.insert(20);
		d.insert(22);
		d.insert(2);
		d.insert(18);
		d.insert(9);
		d.insert(16);
		
		int [] a = new int [10];
		a = d.daryHeapsort();
		for(int i = 0; i < a.length; i++) {
			System.out.println(a[i] + " ");
		}
	}
}
