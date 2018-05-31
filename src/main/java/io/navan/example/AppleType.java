package io.navan.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum AppleType {
	PINK_LADY("Pink Lady"), RED_DELICIOUS("Red Delicious"), GOLDEN_DELICIOUS("Golden Delicious"), MACINTOSH("Macintosh"), JAZZ("Jazz"),
	HONEY_CRISP("Honey Crisp"), ROYAL_GALA("Royal Gala");

	private static final List<AppleType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	private String name;
	
	private AppleType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public static AppleType randomAppleType() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
