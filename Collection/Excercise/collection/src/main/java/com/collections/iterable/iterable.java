package com.collections.iterable;

import  java.util.*;

public class iterable {

	public void IterableFunction()
	{
		List<Integer> list = new ArrayList<>();

		list.add(4);
		list.add(8);
		list.add(12);
		list.add(16);
		list.add(20);
		list.add(24);
		list.add(2);

		System.out.println("--- 1. Testing Iterator Manual Traversal ---");
		Iterator<Integer> iterator = list.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
			// if(iterator.next() == 2)
			// {
			// 	iterator.remove();
			// }			
		}

		// iterator.forEachRemaining();


		Iterable<Integer> iterable = new ArrayList<>(Arrays.asList(4, 8, 12, 16, 20, 24));

		System.out.println("\n--- 2. Testing Iterable.forEach() ---");
        iterable.forEach(item -> System.out.println("Item: " + item));





	}

	public static void main(String[] args) {
		iterable it = new iterable();

		it.IterableFunction();
	}
	
}
