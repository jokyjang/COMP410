package assignment3;

import java.util.Random;

public class MinMaxHeap {
	private int currentSize;
	private int[] arr;
	
	public MinMaxHeap(int capacity) {
		arr = new int[capacity+1];
		currentSize = 0;
	}
	
	public void print() {
		String str = "[";
		for(int i = 1; i <= currentSize; ++i) {
			str += arr[i]+",";
		}
		str += "]";
		System.out.println(str);
	}
	
	public boolean isFull() {
		return currentSize == arr.length - 1;
	}
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	public void insert(int x) {
		if(isFull()) return;
		arr[++currentSize] = x;
		bubbleUp(currentSize);
	}
	
	private void bubbleUp(int i) {
		int level = (int)((Math.log(i)/Math.log(2)));
		if(level % 2 == 0) {	//	in min level
			if(i / 2 != 0 && arr[i] > arr[i/2]) {
				int temp = arr[i];
				arr[i] = arr[i/2];
				arr[i/2] = temp;
				bubbleUpMax(i/2);
			} else {
				bubbleUpMin(i);
			}
		} else {
			if(i / 2 != 0 && arr[i] < arr[i/2]) {
				int temp = arr[i];
				arr[i] = arr[i/2];
				arr[i/2] = temp;
				bubbleUpMin(i/2);
			} else {
				bubbleUpMax(i);
			}
		}
	}
	
	private void bubbleUpMax(int i) {
		if(i/4 == 0) return;
		if(arr[i/4] > arr[i]) return;
		int temp = arr[i/4];
		arr[i/4] = arr[i];
		arr[i] = temp;
		bubbleUpMax(i/4);
	}
	
	private void bubbleUpMin(int i) {
		if(i/4 == 0) return;
		if(arr[i/4] < arr[i]) return;
		int temp = arr[i/4];
		arr[i/4] = arr[i];
		arr[i] = temp;
		bubbleUpMin(i/4);
	}
	
	public int deleteMin() {
		if(isEmpty()) return Integer.MIN_VALUE;
		int min = min();
		arr[1] = arr[currentSize--];
		trickleDownMin(1);
		return min;
	}
	
	public int deleteMax() {
		if(isEmpty()) return Integer.MAX_VALUE;
		int max = max();
		int index;
		if (currentSize == 1) index = 1;
		else {
			index = 2;
			int maxValue = arr[2];
			if(currentSize > 2 && maxValue < arr[3]) {
				maxValue = arr[3];
				index = 3;
			}
		}
		arr[index] = arr[currentSize--];
		trickleDownMax(index);
		return max;
	}
	
	private void trickleDownMin(int i) {
		if(currentSize < 2*i) return;
		int smallest = smallestOffsprings(i);
		if(arr[i] < arr[smallest]) return;
		int temp = arr[i];
		arr[i] = arr[smallest];
		arr[smallest] = temp;
		if(smallest >= 4*i) {
			int parent = smallest / 2;
			if(arr[parent] < arr[smallest]) {
				temp = arr[parent];
				arr[parent] = arr[smallest];
				arr[smallest] = temp;
			}
			trickleDownMin(smallest);
		}
	}
	
	private void trickleDownMax(int i) {
		if(currentSize < 2*i) return;
		int biggest = biggestOffsprings(i);
		if(arr[i] > arr[biggest]) return;
		int temp = arr[i];
		arr[i] = arr[biggest];
		arr[biggest] = temp;
		if(biggest >= 4*i) {
			int parent = biggest / 2;
			if(arr[parent] > arr[biggest]) {
				temp = arr[parent];
				arr[parent] = arr[biggest];
				arr[biggest] = temp;
			}
			trickleDownMax(biggest);
		}
	}
	
	/**
	 * We assume node i has at least one child node
	 * @param i
	 * @return
	 */
	private int smallestOffsprings(int i) {
		int firstChild = 2 * i, lastChild = 2 * i + 1;
		int firstGrand = 4 * i, lastGrand = 4 * i + 3;
		int smallest = arr[firstChild], smallestIndex = firstChild;
		int bound = currentSize < lastChild ? currentSize : lastChild;
		for(int j = firstChild; j <= bound; ++j) {
			if(arr[j] < smallest) {
				smallest = arr[j];
				smallestIndex = j;
			}
		}
		
		if(this.currentSize < firstGrand) return smallestIndex;
		bound = currentSize < lastGrand ? currentSize : lastGrand;
		for (int j = firstGrand; j <= bound; ++j) {
			if (arr[j] < smallest) {
				smallest = arr[j];
				smallestIndex = j;
			}
		}
		return smallestIndex;
	}
	
	/**
	 * We assume node i has at least one child node
	 * @param i
	 * @return
	 */
	private int biggestOffsprings(int i) {
		int firstChild = 2 * i, lastChild = 2 * i + 1;
		int firstGrand = 4 * i, lastGrand = 4 * i + 3;
		int biggest = arr[firstChild], biggestIndex = firstChild;
		int bound = currentSize < lastChild ? currentSize : lastChild;
		for(int j = firstChild; j <= bound; ++j) {
			if(arr[j] > biggest) {
				biggest = arr[j];
				biggestIndex = j;
			}
		}
		
		if(this.currentSize < firstGrand) return biggestIndex;
		bound = currentSize < lastGrand ? currentSize : lastGrand;
		for (int j = firstGrand; j <= bound; ++j) {
			if (arr[j] >  biggest) {
				biggest = arr[j];
				biggestIndex = j;
			}
		}
		return biggestIndex;
	}
	
	public int min() {
		assert !isEmpty();
		return arr[1];
	}
	
	public int max() {
		assert !isEmpty();
		if (currentSize == 1) return arr[1];
		int maxValue = arr[2];
		if(currentSize > 2 && maxValue < arr[3]) maxValue = arr[3];
		return maxValue;
	}
	
	public static void main(String[] args) {
		MinMaxHeap maxMMH = new MinMaxHeap(100);
		MinMaxHeap minMMH = new MinMaxHeap(100);
		int[] numbers = new int[101];
		for(int i = 0; i < 100; ++i) {
			numbers[i+1] = (new Random()).nextInt(100);
		}
		//int[] numbers = {71, 11, 41, 10, 46, 31, 13, 8, 16, 31, 51, 21};
		//int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    	for(int i = 1; i < 101; i++) {
    		int n = numbers[i];
			maxMMH.insert(n);
			minMMH.insert(n);
			System.out.print(maxMMH.min() + "," + maxMMH.max() + " ");
			maxMMH.print();
		}
    	String minS = "";
    	String maxS = "";
		while(!maxMMH.isEmpty()) {
			maxS += maxMMH.deleteMax()+",";
			minS += minMMH.deleteMin()+",";
		}
		System.out.println(minS);
		System.out.println(maxS);
	}
}
