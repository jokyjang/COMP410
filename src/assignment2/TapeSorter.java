package assignment2;

import java.util.Random;

/**
 * Represents a machine with limited memory that can sort tape drives.
 */
public class TapeSorter {

    private int memorySize;
    private int tapeSize;
    public int[] memory;

    public TapeSorter(int memorySize, int tapeSize) {
        this.memorySize = memorySize;
        this.tapeSize = tapeSize;
        this.memory = new int[memorySize];
    }

    /**
     * Sorts the first `size` items in memory via quicksort
     */
    public void quicksort(int size) {
        // TODO: Implement me for 10 points
    	myQuicksort(0, size-1);
    }
    private void myQuicksort(int left, int right) {
    	if(left < right) {
    		int p = patition(left, right);
    		myQuicksort(left, p-1);
    		myQuicksort(p+1, right);
    	}
    }
    
    /**
     * swap value in memory[a] and memory[b]
     * @param a - index one in memory to be swapped
     * @param b - index two in memory to be swapped
     */
    private void swap(int a, int b) {
    	int tmp = memory[a];
    	memory[a] = memory[b];
    	memory[b] = tmp;
    }
    
    private int patition(int left, int right) {
    	Random random = new Random();
    	int pivot = random.nextInt(right-left+1) + left;
    	swap(pivot, right);
    	int currentIndex = left;
    	for(int i = left; i < right; ++i) {
    		if(memory[i] < memory[right]) {
    			swap(i, currentIndex);
    			++currentIndex;
    		}
    	}
    	swap(currentIndex, right);
    	return currentIndex;
    }

    /**
     * Reads in numbers from drive `in` into memory (a chunk), sorts it, then writes it out to a different drive.
     * It writes chunks alternatively to drives `out1` and `out2`.
     *
     * If there are not enough numbers left on drive `in` to fill memory, then it should read numbers until the end of
     * the drive is reached.
     *
     * Example 1: Tape size = 8, memory size = 2
     * ------------------------------------------
     *   BEFORE:
     * in: 4 7 8 6 1 3 5 7
     *
     *   AFTER:
     * out1: 4 7 1 3 _ _ _ _
     * out2: 6 8 5 7 _ _ _ _
     *
     *
     * Example 2: Tape size = 10, memory size = 3
     * ------------------------------------------
     *   BEFORE:
     * in: 6 3 8 9 3 1 0 7 3 5
     *
     *   AFTER:
     * out1: 3 6 8 0 3 7 _ _ _ _
     * out2: 1 3 9 5 _ _ _ _ _ _
     *
     *
     * Example 3: Tape size = 13, memory size = 4
     * ------------------------------------------
     *   BEFORE:
     * in: 6 3 8 9 3 1 0 7 3 5 9 2 4
     *
     *   AFTER:
     * out1: 3 6 8 9 2 3 5 9 _ _ _ _ _
     * out2: 0 1 3 7 4 _ _ _ _ _ _ _ _
     */
    public void initialPass(TapeDrive in, TapeDrive out1, TapeDrive out2) {
        // TODO: Implement me for 15 points!
    	for(int i = 0; i < this.tapeSize; i += this.memorySize*2) {
    		int size1 = memorySize > tapeSize-i ? tapeSize-i : memorySize;
    		for(int j = 0; j < size1; ++j) {
    			memory[j] = in.read();
    		}
    		this.quicksort(size1);
    		for(int j = 0; j < size1; ++j) {
    			out1.write(memory[j]);
    		}
    		int size2 = memorySize > tapeSize-i-size1 ? tapeSize-i-size1 : memorySize;
    		for(int j = 0; j < size2; ++j) {
    			memory[j] = in.read();
    		}
    		this.quicksort(size2);
    		for(int j = 0; j < size2; ++j) {
    			out2.write(memory[j]);
    		}
    	}
    	in.reset();
    	out1.reset();
    	out2.reset();
    }

    /**
     * Merges the first chunk on drives `in1` and `in2` and writes the sorted, merged data to drive `out`.
     * The size of the chunk on drive `in1` is `size1`.
     * The size of the chunk on drive `in2` is `size2`.
     *
     *          Example
     *       =============
     *
     *  (BEFORE)
     * in1:  [ ... 1 3 6 8 9 ... ]
     *             ^
     * in2:  [ ... 2 4 5 7 8 ... ]
     *             ^
     * out:  [ ... _ _ _ _ _ ... ]
     *             ^
     * size1: 4, size2: 4
     *
     *   (AFTER)
     * in1:  [ ... 1 3 6 8 9 ... ]
     *                     ^
     * in2:  [ ... 2 4 5 7 8 ... ]
     *                     ^
     * out:  [ ... 1 2 3 4 5 6 7 8 _ _ _ ... ]
     *                             ^
     */
    public void mergeChunks(TapeDrive in1, TapeDrive in2, TapeDrive out, int size1, int size2) {
        // TODO: Implement me for 10 points
    	int i = 0, j = 0;
    	if(size1 == 0) {
    		for(; i < size2; ++i) {
    			out.write(in2.read());
    		}
    	} else if(size2 == 0) {
    		for(; i < size1; ++i) {
    			out.write(in1.read());
    		}
    	} else {
    		int value1 = 0, value2 = 0;
    		boolean read1 = true, read2 = true;
    		while(i < size1 && j < size2) {
    			if(read1) {
    				value1 = in1.read();
    			}
    			if(read2) {
    				value2 = in2.read();
    			}
        		if(value1 < value2) {
        			out.write(value1);
        			read1 = true;
        			read2 = false;
        			++i;
        		} else {
        			out.write(value2);
        			read2 = true;
        			read1 = false;
        			++j;
        		}
        	}
    		if(i < size1) {
    			out.write(value1);
    			++i;
    		}
    		if(j < size2) {
    			out.write(value2);
    			++j;
    		}
    		while(i++ < size1) {
    			out.write(in1.read());
    		} 
    		
    		while(j++ < size2) {
    			out.write(in2.read());
    		}
    	}
    }

    /**
     * Merges chunks from drives `in1` and `in2` and writes the resulting merged chunks alternatively to drives `out1`
     * and `out2`.
     *
     * The `runNumber` argument denotes which run this is, where 0 is the first run.
     *
     * -- Math Help --
     * The chunk size on each drive prior to merging will be: memorySize * (2 ^ runNumber)
     * The number of full chunks on each drive is: floor(tapeSize / (chunk size * 2))
     *   Note: If the number of full chunks is 0, that means that there is a full chunk on drive `in1` and a partial
     *   chunk on drive `in2`.
     * The number of leftovers is: tapeSize - 2 * chunk size * number of full chunks
     *
     * To help you better understand what should be happening, here are some examples of corner cases (chunks are
     * denoted within curly braces {}):
     *
     * -- Even number of chunks --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 } { 3 5 6 9 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 3 5 5 6 7 8 9 9 }
     *
     * -- Odd number of chunks --
     * in1 ->   { 1 3 5 } { 6 7 9 } { 3 4 8 }
     * in2 ->   { 2 4 6 } { 2 7 8 } { 0 3 9 }
     * out1 ->  { 1 2 3 4 5 6 } { 0 3 3 4 8 9 }
     * out2 ->  { 2 6 7 7 8 9 }
     *
     * -- Number of leftovers <= the chunk size --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 5 7 8 9 }
     *
     * -- Number of leftovers > the chunk size --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 } { 3 5 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 3 5 5 7 8 9 }
     *
     * -- Number of chunks is 0 --
     * in1 ->   { 2 4 5 8 9 }
     * in2 ->   { 1 5 7 }
     * out1 ->  { 1 2 4 5 5 7 8 9 }
     * out2 ->
     */
    public void doRun(TapeDrive in1, TapeDrive in2, TapeDrive out1, TapeDrive out2, int runNumber) {
        // TODO: Implement me for 15 points
    	int chunckSize = (int) (memorySize * (Math.pow(2, runNumber)));
    	int fullChuncks = tapeSize / (chunckSize * 2);
    	int leftOver = tapeSize - fullChuncks * chunckSize * 2;
    	TapeDrive dest = out1;
    	for(int i = 0; i < fullChuncks; ++i) {
    		this.mergeChunks(in1, in2, dest, chunckSize, chunckSize);
    		dest = dest == out1 ? out2 : out1;
    	}
    	int size1, size2;
    	if(leftOver > chunckSize) {
    		size1 = chunckSize;
    		size2 = leftOver - chunckSize;
    	} else {
    		size1 = leftOver;
    		size2 = 0;
    	}
    	this.mergeChunks(in1, in2, dest, size1, size2);
    	in1.reset();
    	in2.reset();
    	out1.reset();
    	out2.reset();
    }

    /**
     * Sorts the data on drive `t1` using the external sort algorithm. The sorted data should end up on drive `t1`.
     *
     * Initially, drive `t1` is filled to capacity with unsorted numbers.
     * Drives `t2`, `t3`, and `t4` are empty and are to be used in the sorting process.
     */
    public void sort(TapeDrive t1, TapeDrive t2, TapeDrive t3, TapeDrive t4) {
        // TODO: Implement me for 15 points
    	this.initialPass(t1, t3, t4);
    	/*
    	t1.print();
    	t2.print();
    	t3.print();
    	t4.print();
    	System.out.println("+----------+");
    	*/
    	int runs = (int)Math.ceil((Math.log((double)tapeSize/memorySize)/Math.log(2)));
    	TapeDrive src1 = t3, src2 = t4, dest1 = t1, dest2 = t2;
    	for(int i = 0; i < runs; ++i) {
    		this.doRun(src1, src2, dest1, dest2, i);
    		/*
    		t1.print();
        	t2.print();
        	t3.print();
        	t4.print();
        	System.out.println("+----------+");
    		*/
    		TapeDrive tmp = src1;
    		src1 = dest1;
    		dest1 = tmp;
    		tmp = src2;
    		src2 = dest2;
    		dest2 = tmp;
    	}
    	if(src1 != t1) {
    		for(int i = 0; i < tapeSize; ++i) {
    			t1.write(src1.read());
    		}
    	}
    }

    public static void main(String[] args) {
        // Example of how to test
    	int memSize = 10;
    	int tapeSize = 80;
        TapeSorter tapeSorter = new TapeSorter(memSize, tapeSize);
        TapeDrive t1 = TapeDrive.generateRandomTape(tapeSize);
        TapeDrive t2 = new TapeDrive(tapeSize);
        TapeDrive t3 = new TapeDrive(tapeSize);
        TapeDrive t4 = new TapeDrive(tapeSize);

        tapeSorter.sort(t1, t2, t3, t4);
        int last = Integer.MIN_VALUE;
        boolean sorted = true;
        for (int i = 0; i < tapeSize; i++) {
            int val = t1.read();
            sorted &= last <= val;
            last = val;
        }
        if (sorted)
            System.out.println("Sorted!");
        else
            System.out.println("Not sorted!");
    }

}
