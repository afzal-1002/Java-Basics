package com.collections.iterable_collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class collections {

    // Using the Collection interface reference initialized with an ArrayList
    Collection<String> collection = new ArrayList<>();

    public void testCollectionFunctions() {
        System.out.println("=== 1. ADDING ELEMENTS ===");
        // add(E e)
        collection.add("Java");
        collection.add("Spring Boot");
        collection.add("Angular");
        System.out.println("Collection after add: " + collection);

        // addAll(Collection<? extends E> c)
        Collection<String> newItems = List.of("Docker", "Kubernetes");
        collection.addAll(newItems);
        System.out.println("Collection after addAll: " + collection);

        System.out.println("\n=== 2. INSPECTING COLLECTION ===");
        // size()
        System.out.println("Size: " + collection.size());

        // isEmpty()
        System.out.println("Is Empty? " + collection.isEmpty());

        // contains(Object o)
        System.out.println("Contains 'Java'? " + collection.contains("Java"));
        System.out.println("Contains 'Python'? " + collection.contains("Python"));

        // containsAll(Collection<?> c)
        System.out.println("Contains all [Java, Docker]? " + collection.containsAll(List.of("Java", "Docker")));

        System.out.println("\n=== 3. REMOVING ELEMENTS ===");
        // remove(Object o)
        collection.remove("Angular");
        System.out.println("After removing 'Angular': " + collection);

        // removeAll(Collection<?> c)
        collection.removeAll(List.of("Docker", "Kubernetes"));
        System.out.println("After removeAll [Docker, Kubernetes]: " + collection);

        // removeIf(Predicate<? super E> filter) - Java 8+
        collection.add("JavaScript");
        collection.removeIf(item -> item.startsWith("Java"));
        System.out.println("After removeIf (starts with 'Java'): " + collection);

        System.out.println("\n=== 4. CONVERTING TO ARRAY & CLEARING ===");
        // Re-populate for testing
        collection.add("Java");
        collection.add("Spring Boot");

        // toArray()
        Object[] array = collection.toArray();
        System.out.println("Array length: " + array.length);

        // clear()
        collection.clear();
        System.out.println("After clear(), size is: " + collection.size());
        System.out.println("Is Empty now? " + collection.isEmpty());
    }

    public static void main(String[] args) {
        collections tester = new collections();
        tester.testCollectionFunctions();
    }
}