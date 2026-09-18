package com.epam.rd.autocode.collection.array;

import java.util.Optional;

public class SimpleArrayListImpl implements SimpleArrayList {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int FACTOR_MULTIPLIER = 2;
    private static final double INCREASE_LOAD_FACTOR = 0.75;
    private static final double DECREASE_LOAD_FACTOR = 0.4;

    private Object[] elements;
    private int size;

    /**
     * Creates a list with the default capacity = 10.
     */
    public SimpleArrayListImpl() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(Object element) {

        if (element == null) {
            throw new NullPointerException();
        }

        elements[size] = element;
        size++;

        if (size >= elements.length * INCREASE_LOAD_FACTOR) {
            increaseCapacity();
        }

        return true;
    }

    private void increaseCapacity() {

        int newCapacity;

        if (elements.length == 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = (int) (elements.length * FACTOR_MULTIPLIER * INCREASE_LOAD_FACTOR);
        }

        Object[] newElements = new Object[newCapacity];

        System.arraycopy( elements, 0, newElements, 0, size);

        elements = newElements;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean decreaseCapacity() {

        if (size <= elements.length * DECREASE_LOAD_FACTOR) {
            int newCapacity = size * FACTOR_MULTIPLIER;
            Object[] newElements = new Object[newCapacity];
            System.arraycopy( elements, 0, newElements, 0, size);
            elements = newElements;
            return true;
        }

        return false;
    }

    @Override
    public Object get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return elements[index];
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Optional<Object> remove(Object el) {

        if (el == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < size; i++) {

            if (elements[i].equals(el)) {

                Object removedElement = elements[i];

                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }

                elements[size - 1] = null;
                size--;

                return Optional.of(removedElement);
            }
        }

        return Optional.empty();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {

        if (isEmpty()) {
            return "[]";
        }

        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {

            result.append(elements[i]);

            if (i < size - 1) {
                result.append(", ");
            }
        }

        result.append("]");

        return result.toString();
    }
}