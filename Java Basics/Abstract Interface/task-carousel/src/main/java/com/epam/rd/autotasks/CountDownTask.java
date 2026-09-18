package com.epam.rd.autotasks;

public class CountDownTask implements Task {

    private int value;

    public CountDownTask(int value) {
        if (value < 0) {
            this.value = 0;
        } else {
            this.value = Math.max(value, 0);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int num) {
         this.value = num;
    }

    @Override
    public void execute() {
        if (value > 0) {
            value--;
        }
    }

    @Override
    public boolean isFinished() {
        return value == 0;
    }
}