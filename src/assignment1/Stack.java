package assignment1;

public class Stack<T> {
	private Node<T> top;
	private int length;
	private int capacity;
	public Stack(int capacity) {
		this.capacity = capacity;
		this.top = null;
		this.length = 0;
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
    	return this.top.data;
    }
    public T pop() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	T data = this.top.data;
    	this.top = this.top.next;
    	this.length--;
    	return data;
    }
    public void push(T element) {
    	if (this.isFull()) {
    		return;
    	}
    	Node<T> node = new Node<T>(element, this.top);
    	this.top = node;
    	this.length++;
    }
    public void print() {
    	if (this.isEmpty()) {
    		return;
    	}
    	System.out.print(this.top.data);
    	Node<T> i = this.top.next;
    	while (i != null) {
    		System.out.print(" -> "+i.data);
    		i = i.next;
    	}
    }
}
