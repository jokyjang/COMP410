package assignment1;

public class VipQueue<T> {
	private Stack<T> stack;
	private Queue<T> queue;
	
	public VipQueue() {
		this.stack = new Stack<T>();
		this.queue = new Queue<T>();
	}
    public boolean isEmpty() {
    	return stack.isEmpty() && queue.isEmpty();
    }
    public T peek() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	return stack.isEmpty() ? queue.peek() : stack.peek();
    }
    public T dequeue() {
    	if (this.isEmpty()) {
    		return null;
    	}
    	return stack.isEmpty() ? queue.dequeue() : stack.pop();
    }
    public void enqueue(T element) {
    	
    	queue.enqueue(element);
    }
    public void vipEnqueue(T element) {
    	stack.push(element);
    }
    public void print() {
    	this.stack.print();
    	System.out.print(" -> ");
    	this.queue.print();
    }
    public static void main(String []args) {
    	VipQueue<Integer> vipQueue = new VipQueue<Integer>();
    	for(int i = 1; i < 6; i++) {
    		vipQueue.enqueue(i);
    		vipQueue.vipEnqueue(i*i);
    	}
    	vipQueue.print();
    }
}
