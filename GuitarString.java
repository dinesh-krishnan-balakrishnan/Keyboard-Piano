/**
 * CS312 Assignment 12.
 *
 * On MY honor, Dinesh, this programming assignment is MY own work
 * and I have not provided this code to any other student.
 *
 * Student name: Dinesh Krishnan Balakrishnan
 * UTEID: db42659
 * email address: dinesh.k.balakrishnan@utexas.edu
 * Grader name: Mejia, Fatima
 */

public class GuitarString {
	
	//The private variables keep track of the status of the ringBuffer and the number of tics that have occurred.
	private RingBuffer ringBuffer;
	private int ticCount;
	
	//Initializes the guitar string if a frequency value has been provided.
	public GuitarString(double frequency) {
		final int SAMPLING_RATE = 44_100;
		//Capacity of the ring buffer is equal to the sampling rate divided by the frequency.
		int capacity = (int) Math.ceil(SAMPLING_RATE / frequency);
		ringBuffer = new RingBuffer(capacity);
		//All of the ringBuffer values are set to 0.
		for (int content = 1; content <= capacity; content++) {
			ringBuffer.enqueue(0.0);	
		}
	}
	
	//Initializes the guitar string if an array of default values has been provided.
	public GuitarString(double[] init) {
		int capacity = init.length;
		ringBuffer = new RingBuffer(capacity);
		for (int index = 0; index < capacity; index++) {
			ringBuffer.enqueue(init[index]);
		}
	}
	
	//Simulates the plucking of a guitar string.
	public void pluck() {
		int length = ringBuffer.size();
		//Replaces all values in the ringBuffer with a random value between -0.5 and 0.5
		for (int index = 0; index < length; index++) {
			ringBuffer.dequeue();
			ringBuffer.enqueue(Math.random() - 0.49);
		}
	}

	//Simulates the traveling of energy across a string with the Karplus-Strong update.
	public void tic() {
			final double ENERGY_DECAY_FACTOR = 0.994;
			ringBuffer.enqueue(ENERGY_DECAY_FACTOR * (ringBuffer.dequeue() + ringBuffer.peek()) / 2);
			ticCount++;
	}
	
	//Looks at the first value in the ringBuffer array.
	public double sample() {
		return ringBuffer.peek();
	}
	
	//Returns how many times the tic method has been called.
	public int time() {
		return ticCount;
	}
}
