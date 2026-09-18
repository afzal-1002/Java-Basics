package com.epam.rd.autocode.dllist;

import java.util.Optional;

public class DoublyLinkedListImpl implements DoublyLinkedList {

    private Node head;
    private Node tail;

    private static class Node {

        Object data;
        Node next;
        Node prev;

        Node(Object data, Node prev, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public boolean addFirst(Object element) {

        if (element == null) {
            return false;
        }

        Node newNode = new Node(element, null, head);

        // Empty list
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            head = newNode;
        }

        return true;
    }

    @Override
    public boolean addLast(Object element) {

        if (element == null) {
            return false;
        }

        Node newNode = new Node(element, tail, null);

        // Empty list
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        return true;
    }

    @Override
    public void delete(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        // Removing first node
        if (current.prev == null) {
            head = current.next;
        } else {
            current.prev.next = current.next;
        }

        // Removing last node
        if (current.next == null) {
            tail = current.prev;
        } else {
            current.next.prev = current.prev;
        }
    }

    @Override
    public Optional<Object> remove(Object element) {

        Node current = head;

        while (current != null) {

            if ((element == null && current.data == null)
                    || (element != null && element.equals(current.data))) {

                Object removedElement = current.data;

                if (current.prev == null) {
                    head = current.next;
                } else {
                    current.prev.next = current.next;
                }

                if (current.next == null) {
                    tail = current.prev;
                } else {
                    current.next.prev = current.prev;
                }

                return Optional.ofNullable(removedElement);
            }

            current = current.next;
        }

        return Optional.empty();
    }

    @Override
    public boolean set(int index, Object data) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (data == null) {
            return false;
        }

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        current.data = data;

        return true;
    }

    @Override
    public int size() {

        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    @Override
    public Object[] toArray() {

        Object[] array = new Object[size()];

        Node current = head;
        int index = 0;

        while (current != null) {
            array[index] = current.data;

            index++;
            current = current.next;
        }

        return array;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        Node current = head;

        while (current != null) {

            result.append(current.data);

            if (current.next != null) {
                result.append(" ");
            }

            current = current.next;
        }

        return result.toString();
    }
}