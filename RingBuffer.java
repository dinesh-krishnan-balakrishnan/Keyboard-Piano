import java.util.NoSuchElementException;

public class RingBuffer {

	//ringBuffer stores the ring buffer, while size stores the number of elements in it.
	private double[] ringBuffer;
	private int size;

	//Creates the empty ring buffer with the provided capacity.
	public RingBuffer(int capacity) {
		ringBuffer = new double[capacity];
	}
	
	//Returns the current number of elements in the ring buffer.
	public int size() {
		return size;
	}
	
	//Returns true if there are no elements in the ring buffer.
	public boolean isEmpty() {
		return size == 0;
	}
	
	//Returns true if the ring buffer is full.
	public boolean isFull() {
		return size == ringBuffer.length;
	}
	
	//Adds an element to the ring buffer, with the size also increasing.
	public void enqueue(double x) {
		//If the array is full, an error is prevented from occurring.
		if (isFull()) throw new IllegalStateException();
		ringBuffer[size] = x;
		size++;
	}
	
	//Returns the first element in the array.
	public double peek() {
		//If the array is empty, an error is prevented from occurring.
		if (isEmpty()) throw new NoSuchElementException();
		return ringBuffer[0];
	}
	
	//Returns the first element in the array and shifts the array accordingly.
	public double dequeue() {
		//Receives the empty array from the peek method.
		double returnDouble = peek();
		size--;
		//If shifting is needed, the array elements shift to the previous index.
		for (int index = 1; index <= size; index++) {
			ringBuffer[index - 1] = ringBuffer[index]; 
		}
		return returnDouble;
	}
	
	//Returns the array in a string format.
	public String toString() {
		String returnString = "[";
		for (int index = 0; index < size - 1; index++) {
			returnString += ringBuffer[index] + ", ";
		}
		returnString += isEmpty()? "]" : ringBuffer[size - 1] + "]";
		return returnString;
	}
}