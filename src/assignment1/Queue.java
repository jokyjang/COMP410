package assignment1;

public class Queue<T> {
	private Node<T> bottom;
	private Node<T> top;
	private int length;
	private int capacity;
	
	public Queue(int capacity) {
		this.capacity = capacity;
		this.length = 0;
		this.bottom = null;
		this.top = null;
	}
    public boolean isEmpty() {
    	return this.length == 0;
    }
    public boolean isFull() {
    	return this.length == this.capacity;
    }
    public T peek() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	return this.bottom.data;
    }
    public T dequeue() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	T data = this.bottom.data;
    	this.bottom = this.bottom.next;
    	if (this.bottom == null) {
    		this.top = null;
    	}
    	this.length--;
    	return data;
    }
    public void enqueue(T element) {
    	if (this.isFull()) {
    		return;
    	}
    	Node<T> node = new Node<T>(element, null);
    	if (this.top != null) {
    		this.top.next = node;
    	}
    	this.top = node;
    	if (this.bottom == null) {
    		this.bottom = node;
    	}
    	this.length++;
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
    	Queue<Integer> q = new Queue<Integer>(10);
    	for(int i = 1; i < 6; i++) {
    		q.enqueue(i);
    	}
    	q.print();
    }
}
