package com.epam.rd.autocode.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class SingleLinkedListImpl implements List {

    private Node head;

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + data + ']';
        }
    }

    public SingleLinkedListImpl() {
        head = new Node(0, null);
    }

    @Override
    public void clear() {

        head.next = null;

        // head.data stores the size
        head.data = 0;
    }

    @Override
    public int size() {

        // No calculation!
        // Just return the value stored in head.data
        return (Integer) head.data;
    }

    /**
     * Inserts the specified element at the <b>front</b> of this list
     *
     * @param el the element to add
     * @return {@code true} if this list was changed {@code false} otherwise
     */
    @Override
    public boolean add(Object el) {

        // Null values are not allowed
        Objects.requireNonNull(el);

        Node newNode = new Node(el);

        // New node points to old first node
        newNode.next = head.next;

        // Head now points to new node
        head.next = newNode;

        // Increase size
        head.data = (Integer) head.data + 1;

        return true;
    }

    @Override
    public Optional<Object> remove(Object el) {

        Node previous = head;
        Node current = head.next;

        while (current != null) {

            if (Objects.equals(current.data, el)) {

                // Skip current node
                previous.next = current.next;

                // Decrease size
                head.data = (Integer) head.data - 1;

                return Optional.of(current.data);
            }

            previous = current;
            current = current.next;
        }

        return Optional.empty();
    }

    @Override
    public Object get(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int currentIndex = 0;
        Node current = head.next;

        while (current != null) {

            if (currentIndex == index) {
                return current.data;
            }

            currentIndex++;
            current = current.next;
        }

        throw new IndexOutOfBoundsException();
    }

    /**
     * Makes a string representation of this list.
     * The elements ordering must be coordinated with the
     * {@code Iterator} of this list.
     *
     * @return a string representation of this list.
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("[");

        Node current = head.next;

        while (current != null) {

            result.append(current.data);

            if (current.next != null) {
                result.append(", ");
            }

            current = current.next;
        }

        result.append("]");

        return result.toString();
    }

    /**
     * Returns an iterator over elements.
     * Iterator must implement the remove method.
     *
     * <pre>
     * list.add("A");
     * list.add("B");
     * list.add("C");
     * for(Object obj : list) { System.out.print(obj) } // prints: CBA
     * </pre>
     *
     * @return an iterator
     */
    @Override
    public Iterator<Object> iterator() {

        return new Iterator<Object>() {

            private Node current = head.next;

            private Node previous = head;

            private Node lastReturned = null;

            private Node lastReturnedPrevious = null;

            @Override
            public boolean hasNext() {

                return current != null;
            }

            @Override
            public Object next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                lastReturnedPrevious = previous;
                lastReturned = current;

                Object value = current.data;

                previous = current;
                current = current.next;

                return value;
            }

            @Override
            public void remove() {

                if (lastReturned == null) {
                    throw new IllegalStateException();
                }

                // Skip the node returned by the previous next()
                lastReturnedPrevious.next = lastReturned.next;

                previous = lastReturnedPrevious;

                // Decrease size
                head.data = (Integer) head.data - 1;

                // Cannot call remove twice without next()
                lastReturned = null;
                lastReturnedPrevious = null;
            }
        };
    }
}