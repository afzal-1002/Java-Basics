package com.epam.rd.autocode.queue;

import java.util.Deque;
import java.util.LinkedList;

public class CashBox {

    private int number;

    private Deque<Buyer> byers;

    private State state;

    public enum State {
        ENABLED, DISABLED, IS_CLOSING
    }

    public CashBox(int number) {
        this.number = number;
        this.byers = new LinkedList<>();
        this.state = State.DISABLED;
    }

    public Deque<Buyer> getQueue() {
        return new LinkedList<>(byers);
    }

    public Buyer serveBuyer() {

        Buyer buyer = byers.pollFirst();

        if (state == State.IS_CLOSING && byers.isEmpty()) {
            state = State.DISABLED;
        }

        return buyer;
    }

    public boolean inState(State state) {
        return this.state == state;
    }

    public boolean notInState(State state) {
        return this.state != state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void addLast(Buyer byer) {
        byers.addLast(byer);
    }

    public Buyer removeLast() {
        return byers.pollLast();
    }

    @Override
    public String toString() {

        String sign;

        if (state == State.ENABLED) {
            sign = "+";
        } else if (state == State.DISABLED) {
            sign = "-";
        } else {
            sign = "|";
        }

        String result = "#" + number + "[" + sign + "]";

        for (Buyer buyer : byers) {
            result += buyer;
        }

        return result;
    }
}