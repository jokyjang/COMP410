package assignment2;

import java.util.Random;

/**
 * Simulates a tape drive
 */
public class TapeDrive {

    private int[] tape;
    private int currentPos = 0;

    public TapeDrive(int capacity) {
        tape = new int[capacity];
    }

    public void write(int i) {
        tape[currentPos] = i;
        currentPos = (currentPos + 1) % tape.length;
    }

    public int read() {
        int i = tape[currentPos];
        currentPos = (currentPos + 1) % tape.length;
        return i;
    }

    public void reset() {
        currentPos = 0;
    }

    /**
     * Create a new TapeDrive that can hold `capacity` numbers, fill it with random numbers, and return it.
     * The numbers must be random in the full integer range.
     */
    public static TapeDrive generateRandomTape(int capacity) {
        // TODO: Implement me for 10 points
    	Random random = new Random();
    	TapeDrive tapeDrive = new TapeDrive(capacity);
    	tapeDrive.reset();
    	for(int i = 0; i < capacity; ++i) {
    		tapeDrive.write(random.nextInt());
    	}
    	tapeDrive.reset();
        return tapeDrive;
    }
    
    public void print() {
    	
    	for(int i = 0; i < this.tape.length; ++i) {
    		System.out.printf("%11d", this.tape[i]);
    	}
    	System.out.println();
    	
    }
}

