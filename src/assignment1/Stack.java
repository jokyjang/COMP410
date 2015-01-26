package assignment1;

public class Stack<T> {
	private Node<T> top;
	public Stack() {
		this.top = null;
	}
    public boolean isEmpty() {
    	return top == null;
    }
    public T peek() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	return top.data;
    }
    public T pop() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	T data = top.data;
    	this.top = top.next;
    	return data;
    }
    public void push(T element) {
    	Node<T> node = new Node<T>(element, top);
    	top = node;
    }
    public void print() {
    	if (this.isEmpty()) {
    		return;
    	}
    	System.out.print(top.data);
    	Node<T> node = top.next;
    	while (node != null) {
    		System.out.print(" -> "+node.data);
    		node = node.next;
    	}
    }
}
