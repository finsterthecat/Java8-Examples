package io.navan.example;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

public class Apples {
		
	public static void main(String[] args) {
		List<Apple> apples = Apple.generateBasketOfApples(1000);
		System.out.println("All apples: " + apples);
		
		//Set of all apple colours in the basket
		Set<AppleColour> colours = apples.stream()
				.map(Apple::getColour)
				.collect(toSet());
		System.out.println("All colours: " + colours);
		
		//List of all small apples (with weight < 13g)
		List<Apple> smallApples = apples.stream()
				.filter(apple -> apple.getWeightInGrams() < 13)
				.collect(toList());
		System.out.println("Small apples: " + smallApples);
		
		//Total weight of all small apples
		System.out.println("Total weight is " + smallApples
				.stream()
				.collect(summingInt(Apple::getWeightInGrams)));
		
		//Sum function on IntStream is a shortcut for reduce(0, Integer::sum
		System.out.println("Total weight(2) is " + smallApples
				.stream()
				.mapToInt(Apple::getWeightInGrams)
				.sum());
		
		//reduce to get sum of apple weights
		int xxxx = smallApples
				.stream()
				.map(Apple::getWeightInGrams)
				.reduce(0, Integer::sum );
		System.out.println("total weight (3): " + xxxx);
		
		//number of small apples
		System.out.println("Total number of small apples is " + smallApples
				.stream()
				.collect(counting()));
		
		//All apple types in the basket
		List<AppleType> at = smallApples.stream()
				.map(Apple::getType)
				.distinct()
				.collect(toList());
		System.out.println("Apple types: " + at);
		
		//All together now with IntSummaryStatistics
		IntSummaryStatistics iss = smallApples.stream().collect(summarizingInt(Apple::getWeightInGrams));
		System.out.println(String.format("Min: %d, Max: %d, Count: %d, Sum: %d, Average: %1.2f", iss.getMin(), iss.getMax(), iss.getCount(), iss.getSum(), iss.getAverage()));
		
		//Counts by weight, sorted by weights
		List<Map.Entry<Integer,Integer>> counts = apples.stream()
				.collect(toMap(Apple::getWeightInGrams, a -> 1, (a,b) -> a+1))
				.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toList());
		System.out.printf("Counts by weight: %s\n", counts);
		
		//Counts by weight
		Map<Integer, Integer> counts2 = smallApples.stream().collect(toMap(Apple::getWeightInGrams, a -> 1, Integer::sum));
		System.out.printf("Counts by weight2: %s\n", counts2);
		
		//Weight totals by colours
		Map<AppleColour, Integer> xx = smallApples.stream()
				.collect(toMap(Apple::getColour, Apple::getWeightInGrams, (a,b) -> a+b));
		System.out.println("Total weights by colours: " + xx);

		//Colour counts of first 300 apples
		Map<AppleColour, Integer> countsByColour = smallApples.stream().limit(300)
				.collect(toMap(Apple::getColour, a -> 1, (a,b) -> a+1));
		System.out.println("Counts by colours: " + countsByColour);
		
		//Group apples by colour
		Map<AppleColour, Set<Apple>> xx2 = smallApples.stream()
				.limit(300)
				.sorted(comparing(Apple::getColour))
				.collect(groupingBy(Apple::getColour, toSet()));
		System.out.println("Apples by colours: " + xx2);
		
		//Bit of a dog's breakfast...
		List<String> xx21 = smallApples.stream().limit(300)
				.collect(groupingBy(Apple::getColour, toSet()))
				.keySet()
				.stream()
				.map(AppleColour::toString)
				.sorted(reverseOrder())
				.collect(toList());
		System.out.println("Apples by colours: " + xx21);
		
		//Are all small apples in fact small? 
		boolean allSmall = smallApples.stream().allMatch(a -> a.getWeightInGrams() < 12);
		System.out.println("All small? " + (allSmall ? "yes!" : "no!"));
	}

}
