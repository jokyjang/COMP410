package assignment1;

public class VipQueue<T> {
	private Stack<T> stack;
	private Queue<T> queue;
	private int capacity;
	private int length;
	
	public VipQueue(int capacity) {
		this.capacity = capacity;
		this.length = 0;
		this.stack = new Stack<T>(capacity);
		this.queue = new Queue<T>(capacity);
	}
    public boolean isEmpty() {
    	//return this.stack.isEmpty() && this.queue.isEmpty();
    	return this.length == 0;
    }
    public boolean isFull() {
    	return this.length == this.capacity;
    }
    public T peek() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	return this.stack.isEmpty() ? this.queue.peek() : this.stack.peek();
    }
    public T dequeue() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	return this.stack.isEmpty() ? this.queue.dequeue() : this.stack.pop();
    }
    public void enqueue(T element) {
    	if (this.isFull()) {
    		return;
    	}
    	this.queue.enqueue(element);
    }
    public void vipEnqueue(T element) {
    	if (this.isFull()) {
    		return;
    	}
    	this.stack.push(element);
    }
    public void print() {
    	this.stack.print();
    	System.out.print(" -> ");
    	this.queue.print();
    }
    public static void main(String []args) {
    	VipQueue<Integer> vipQueue = new VipQueue<Integer>(10);
    	for(int i = 1; i < 6; i++) {
    		vipQueue.enqueue(i);
    		vipQueue.vipEnqueue(i*i);
    	}
    	vipQueue.print();
    }
}
