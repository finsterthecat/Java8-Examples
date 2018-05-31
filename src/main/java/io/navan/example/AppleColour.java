package io.navan.example;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum AppleColour {
	RED("red"), YELLOW("yellow"), GREEN("green"), GOLD("gold"), GRASS("grass"), BROWN("brown");

	private static final List<AppleColour> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	private String name;
	
	private AppleColour(String name) {
		this.name= name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public static AppleColour randomAppleColour() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

}
