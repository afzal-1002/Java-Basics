package com.collections.iterable_collection_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.Stack;
import  java.util.Iterator;

public class ListInterface {
	
	// 1. Using ArrayList (Dynamic array - best for fast random access)
	List<Integer> list = new ArrayList<>();

	// 2. Using LinkedList (Doubly-linked list - best for frequent insertions/deletions)
	List<Integer> linkedList = new LinkedList<>();

	// 3. Using Vector (Synchronized dynamic array)
	List<Integer> vectorList = new Vector<>();

	// 4. Using Stack ()
	List<Integer> stackList = new Stack<>();


	public void ListOperations() {
		// 1. Initialization
		List<Integer> list = new ArrayList<>(Arrays.asList(4, 2, 1, 3, 6, 5, 9, 7, 8, 5));
		System.out.println("Initial List = " + list);

		// 2. Basic Modifications
		list.add(100);                  // Appends to end
		list.add(2, 50);                // Inserts 50 at index 2
		list.remove(0);                 // Removes element at index 0
		list.remove(Integer.valueOf(9));// Removes first occurrence of object 9
		list.set(0, 200);               // Replaces element at index 0 with 200
		System.out.println("After add/remove/set = " + list);

		// 3. Retrieval & Inspection
		System.out.println("get(0) = " + list.get(0));
		System.out.println("size() = " + list.size());
		System.out.println("isEmpty() = " + list.isEmpty());
		System.out.println("contains(5) = " + list.contains(5));

		// 4. Searching
		System.out.println("indexOf(5) = " + list.indexOf(5));
		System.out.println("lastIndexOf(5) = " + list.lastIndexOf(5));

		// 5. Bulk Operations
		list.addAll(0, Arrays.asList(4, 5, 6, 8)); // Inserts collection at index 0
		System.out.println("After addAll() = " + list);

		System.out.println("containsAll([5, 6]) = " + list.containsAll(Arrays.asList(5, 6)));

		list.removeAll(Arrays.asList(4, 8)); // Removes all 4s and 8s
		System.out.println("After removeAll([4, 8]) = " + list);

		list.retainAll(Arrays.asList(200, 50, 5, 6, 100)); // Retains only matching elements
		System.out.println("After retainAll() = " + list);

		// 6. Sublists View
		List<Integer> subList = list.subList(1, 4); // Indices 1 to 3
		System.out.println("SubList (1 to 4) = " + subList);

		// 7. Sorting & Functional Modifications
		list.sort(Comparator.naturalOrder()); // Ascending sort
		System.out.println("Sorted = " + list);

		list.replaceAll(n -> n * 2); // Doubles every value
		System.out.println("After replaceAll (*2) = " + list);

		list.removeIf(n -> n > 200); // Removes elements > 200
		System.out.println("After removeIf (>200) = " + list);

		// 8. Conversion to Array
		Integer[] array = list.toArray(new Integer[0]);
		System.out.println("Converted to Array = " + Arrays.toString(array));

		// 9. Clearing
		list.clear();
		System.out.println("After clear() = " + list);
		System.out.println("isEmpty() after clear = " + list.isEmpty());
	}


	public void LinkedListOperation() {
		System.out.println("------------- Linked List Operations ------------------------");

		// 1. Initialization
		LinkedList<Integer> list = new LinkedList<>(Arrays.asList(10, 20, 30, 40, 50));
		System.out.println("Initial LinkedList = " + list);

		// 2. First / Last Operations (Deque interface)
		list.addFirst(5);                   // Inserts at head
		list.addLast(60);                   // Inserts at tail
		System.out.println("After addFirst(5) & addLast(60) = " + list);

		System.out.println("getFirst() = " + list.getFirst());
		System.out.println("getLast() = " + list.getLast());

		System.out.println("removeFirst() = " + list.removeFirst());
		System.out.println("removeLast() = " + list.removeLast());
		System.out.println("After removeFirst/removeLast = " + list);

		// 3. Queue Operations (FIFO - First In First Out)
		list.offer(70);                     // Appends to end (returns boolean)
		list.offerFirst(1);                 // Adds to front
		list.offerLast(80);                 // Adds to end
		System.out.println("After offer operations = " + list);

		System.out.println("peek() = " + list.peek());             // Retrieves head without removing (null if empty)
		System.out.println("peekFirst() = " + list.peekFirst());   // Retrieves first element
		System.out.println("peekLast() = " + list.peekLast());     // Retrieves last element

		System.out.println("poll() = " + list.poll());             // Retrieves & removes head (null if empty)
		System.out.println("pollFirst() = " + list.pollFirst());   // Retrieves & removes first element
		System.out.println("pollLast() = " + list.pollLast());     // Retrieves & removes last element
		System.out.println("After poll operations = " + list);

		// 4. Stack Operations (LIFO - Last In First Out)
		list.push(100);                     // Pushes onto front of list (stack top)
		System.out.println("After push(100) = " + list);
		System.out.println("pop() = " + list.pop());               // Pops from front of list
		System.out.println("After pop() = " + list);

		// 5. Positional Access & Removal by Value/Occurrence
		list.addAll(Arrays.asList(20, 99, 20, 88));
		System.out.println("Before occurrence removals = " + list);

		list.removeFirstOccurrence(20);     // Removes first '20'
		list.removeLastOccurrence(20);      // Removes last '20'
		System.out.println("After removing occurrences of 20 = " + list);

		// 6. Searching & Inspection
		System.out.println("contains(30) = " + list.contains(30));
		System.out.println("indexOf(40) = " + list.indexOf(40));
		System.out.println("size() = " + list.size());

		// 7. Functional Modifications & Sorting
		list.removeIf(n -> n > 50);         // Removes elements > 50
		System.out.println("After removeIf (>50) = " + list);

		list.sort(Comparator.naturalOrder()); // Ascending sort
		System.out.println("Sorted = " + list);

		// 8. Reverse Iteration
		System.out.print("Descending order iteration: ");
		Iterator<Integer> descIterator = list.descendingIterator();
		while (descIterator.hasNext()) {
			System.out.print(descIterator.next() + " ");
		}
		System.out.println();

		// 9. Clearing
		list.clear();
		System.out.println("After clear() = " + list);
		System.out.println("isEmpty() = " + list.isEmpty());
	}


	public void VectorOperation() {	
		System.out.println("------------- Vector Operations ------------------------");

		// 1. Initialization
		Vector<Integer> vector = new Vector<>();
		System.out.println("Initial capacity: " + vector.capacity()); // Default capacity is 10

		// 2. Adding Elements
		vector.add(4);
		vector.addElement(10);                             // Legacy Vector method
		vector.addAll(Arrays.asList(4, 5, 6, 9, 11));
		vector.addAll(new Vector<>(List.of(12, 13, 14)));
		vector.add(95);
		System.out.println("After additions = " + vector);

		// 3. Retrieval & Inspection
		System.out.println("get(0) = " + vector.get(0));
		System.out.println("elementAt(5) = " + vector.elementAt(5));     // Legacy access
		System.out.println("firstElement() = " + vector.firstElement()); // First item
		System.out.println("lastElement() = " + vector.lastElement());   // Last item
		System.out.println("size() = " + vector.size());
		System.out.println("capacity() = " + vector.capacity());

		// 4. Searching
		System.out.println("contains(95) = " + vector.contains(95));
		System.out.println("indexOf(5) = " + vector.indexOf(5));
		System.out.println("lastIndexOf(4) = " + vector.lastIndexOf(4));

		// 5. Modifications & Removals
		vector.set(0, 100);                                 // Set at index
		vector.setElementAt(200, 1);                        // Legacy set
		vector.remove(2);                                   // Remove at index
		vector.removeElement(Integer.valueOf(95));          // Legacy remove object
		vector.removeIf(n -> n > 150);                      // Conditional removal
		System.out.println("After modifications = " + vector);

		// 6. Legacy Enumeration Traversal
		System.out.print("Enumeration loop: ");
		Enumeration<Integer> enumElements = vector.elements();
		while (enumElements.hasMoreElements()) {
			System.out.print(enumElements.nextElement() + " ");
		}
		System.out.println();

		// 7. Capacity Management & Cleanup
		vector.trimToSize();                                // Trims capacity to current size
		System.out.println("Capacity after trimToSize() = " + vector.capacity());

		vector.clear();
		System.out.println("After clear() = " + vector);
	}

	public void StackOperation() {
		System.out.println("------------- Stack Operations ------------------------");

		// 1. Initialization & Core LIFO Stack Methods
		Stack<Integer> stack = new Stack<>();

		// push() - Adds item onto the top of the stack
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		System.out.println("Initial Stack = " + stack);

		// peek() - Looks at top item without removing it
		System.out.println("peek() [Top Element] = " + stack.peek());

		// pop() - Removes and returns the top item
		System.out.println("pop() = " + stack.pop());
		System.out.println("After pop() = " + stack);

		// search() - 1-based distance from top of stack (-1 if not found)
		System.out.println("search(20) [1-based index from top] = " + stack.search(20));

		// 2. Inherited Vector Methods (Stack extends Vector)
		stack.addElement(15);
		stack.addAll(Arrays.asList(4, 5, 6, 9, 11));
		stack.add(95);
		System.out.println("After bulk additions = " + stack);

		System.out.println("get(0) = " + stack.get(0));
		System.out.println("elementAt(5) = " + stack.elementAt(5));
		System.out.println("firstElement() = " + stack.firstElement());
		System.out.println("lastElement() = " + stack.lastElement());

		// 3. In-Place Sorting (Inherited from List/Vector)
		stack.sort(Comparator.reverseOrder());
		System.out.println("Sorted Reverse = " + stack);

		stack.sort(Comparator.naturalOrder());
		System.out.println("Sorted Natural = " + stack);

		// 4. Inspection & Cleanup
		System.out.println("empty() = " + stack.empty()); // Stack's LIFO equivalent of isEmpty()
		stack.clear();
		System.out.println("After clear(), empty() = " + stack.empty());
	}



	public static void main(String[] args) {
		
		ListInterface listInterface = new ListInterface();

		listInterface.ListOperations();
		listInterface.LinkedListOperation();
		listInterface.StackOperation();
	}

}
