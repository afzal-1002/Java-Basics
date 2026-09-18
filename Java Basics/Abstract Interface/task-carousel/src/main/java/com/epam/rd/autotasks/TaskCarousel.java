package com.epam.rd.autotasks;

public class TaskCarousel {

    private final Task[] tasks;
    private int size;
    private int index;

    public TaskCarousel(int capacity) {
        tasks = new Task[capacity];
    }

    public boolean addTask(Task task) {

        if (task == null || isFull()) {  return false; }

        if (task.isFinished()) {
            return false;
        }

        tasks[size] = task;
        size++;

        return true;
    }

    public boolean execute() {

        if (isEmpty()) {  return false; }
        if (index >= size) {  index = 0; }
        Task task = tasks[index];

        task.execute();

        if (task.isFinished()) {
            removeTask(index);
        } else {
            index++;
        }

        if (index >= size) { index = 0; }

        return true;
    }

    private void removeTask(int index) {

        for (int i = index; i < size - 1; i++) {
            tasks[i] = tasks[i + 1];
        }

        tasks[size - 1] = null;
        size--;
    }

    public boolean isFull() {
        return size == tasks.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}