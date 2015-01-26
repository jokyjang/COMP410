package assignment1;

public class Queue<T> {
	private Node<T> bottom;
	private Node<T> top;
	
	public Queue() {
		this.bottom = null;
		this.top = null;
	}
    public boolean isEmpty() {
    	return bottom == null && top == null;
    }
    public T peek() {
    	if (isEmpty()) {
    		return null;
    	}
    	return bottom.data;
    }
    public T dequeue() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	T data = bottom.data;
    	bottom = bottom.next;
    	if (bottom == null) {
    		top = null;
    	}
    	return data;
    }
    public void enqueue(T element) {
    	Node<T> node = new Node<T>(element, null);
    	if (top != null) {
    		top.next = node;
    	}
    	this.top = node;
    	if (bottom == null) {
    		bottom = node;
    	}
    }
    public void print() {
    	if (this.isEmpty()) {
    		return;
    	}
    	System.out.print(this.bottom.data);
    	Node<T> i = this.bottom.next;
    	while (i != null) {
    		System.out.print(" -> " + i.data);
    		i = i.next;
    	}
    }
    public static void main(String []args) {
    	Queue<Integer> q = new Queue<Integer>();
    	for(int i = 1; i < 6; i++) {
    		q.enqueue(i);
    	}
    	q.print();
    }
}
