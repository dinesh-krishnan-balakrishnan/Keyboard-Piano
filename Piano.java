public class Piano {

	//The default key values that will be producing sounds in order of low to high pitch.
	private static final String keys = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

	//Creates a visual guide and simulates a piano keyboard.
	public static void main(String[] args) {
		//Adds the image to the program that serves as a visual guide.
		StdDraw.picture(0.5, 0.5, "./keyboard.png", 0.75, 1);
		//Creates the notes based on the keys provided.
		GuitarString[] notes = createSounds();
		//Simulation occurs in the play method.
		play(notes);
	}

	public static GuitarString[] createSounds() {
		final int NOTE_COUNT = keys.length();
		final int STATIC_FREQUENCY = 440;
		final double FREQUENCY_CHANGE = 1.05956;
		final int INDEX_ADJUSTMENT = 24;
		GuitarString[] notes = new GuitarString[NOTE_COUNT];
		//Frequency of a key depends on its location in the keys string.
		for (int index = 0; index < NOTE_COUNT; index++) {
			// The equation for frequency is 440 x 1.05956 ^ (1 - 24), where i is the index of the key.
			double frequency = Math.round(STATIC_FREQUENCY * Math.pow(FREQUENCY_CHANGE, index - INDEX_ADJUSTMENT));
			//A guitar string is being created for each key and its respective frequency.
			notes[index] = new GuitarString(frequency);
		}
		return notes;
	}

	public static void play(GuitarString[] notes) {
		//The program loops indefinitely.
		while (true) {
			//Checks to see whether the user has created input.
			if (StdDraw.hasNextKeyTyped()) {
				//Finds the respective GuitarString variable and simulates the act of plucking it.
				int index = keys.indexOf(StdDraw.nextKeyTyped());
				if (index != -1) {
					notes[index].pluck();
				}
			}
			//Plays the sample created from looking at the first element in each ringBuffer class.
			double sample = 0;
			for (GuitarString note : notes) {
				sample += note.sample();
				//Each note then runs the tic method to simulate sound transfer.
				note.tic();
			}
			StdAudio.play(sample);
		}
	}
}