package io.navan.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Apple {
	private String name;
	private AppleColour colour;
	private int weightInGrams;
	private AppleType type;
	
	private Apple(String name, AppleColour colour, int weightInGrams, AppleType type) {
		this.name = name;
		this.colour = colour;
		this.weightInGrams = weightInGrams;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AppleColour getColour() {
		return colour;
	}
	public void setColour(AppleColour colour) {
		this.colour = colour;
	}
	public int getWeightInGrams() {
		return weightInGrams;
	}
	public void setWeightInGrams(int weightInGrams) {
		this.weightInGrams = weightInGrams;
	}
	public AppleType getType() {
		return type;
	}
	public void setType(AppleType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("%s is a %s %s and weighs %d grams", name, colour, type, weightInGrams);
	}
	
	public static List<Apple> generateBasketOfApples(int size) {
		if (size == 0) size = 1000;
		List<Apple> apples = new ArrayList<>();
		for (int i=0; i<size; i++) {
			apples.add(new Apple("Apple " + (i+1),
					AppleColour.randomAppleColour(),
					ThreadLocalRandom.current().nextInt(10, 21),
					AppleType.randomAppleType()));
		}
		return apples;
	}
	
}
