package com.epam.rd.autocode.queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.epam.rd.autocode.queue.CashBox.State;

public class Shop {

    private int cashBoxCount;

    private List<CashBox> cashBoxes;

    public Shop(int count) {

        cashBoxCount = count;
        cashBoxes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cashBoxes.add(new CashBox(i));
        }
    }

    public int getCashBoxCount() {
        return cashBoxCount;
    }

    private static int getTotalBuyersCount(List<CashBox> cashBoxes) {

        int total = 0;

        for (CashBox cb : cashBoxes) {
            total += cb.getQueue().size();
        }

        return total;
    }

    public void addBuyer(Buyer buyer) {

        CashBox minCb = null;

        for (CashBox cb : cashBoxes) {

            if (cb.inState(State.ENABLED)) {

                if (minCb == null || cb.getQueue().size() < minCb.getQueue().size()) {
                    minCb = cb;
                }
            }
        }

        if (minCb != null) {
            minCb.addLast(buyer);
        }
    }

    public void tact() {

        for (CashBox cb : cashBoxes) {

            if (cb.notInState(State.DISABLED) && !cb.getQueue().isEmpty()) {
                cb.serveBuyer();
            }
        }

        balance();
    }

    public static int[] getMinMaxSize(List<CashBox> cashBoxes) {

        int enabledCount = 0;
        int total = 0;

        for (CashBox cb : cashBoxes) {

            if (cb.inState(State.ENABLED)) {
                enabledCount++;
                total += cb.getQueue().size();
            }
        }

        if (enabledCount == 0) {
            return new int[]{0, 0};
        }

        int min = total / enabledCount;
        int max = min;

        if (total % enabledCount != 0) {
            max++;
        }

        return new int[]{min, max};
    }

    public void setCashBoxState(int cashBoxNumber, State state) {
        cashBoxes.get(cashBoxNumber).setState(state);
    }

    public CashBox getCashBox(int cashBoxNumber) {
        return cashBoxes.get(cashBoxNumber);
    }

    public void print() {

        for (CashBox cb : cashBoxes) {
            System.out.println(cb);
        }
    }

    private void balance() {

        List<CashBox> enabled = new ArrayList<>();

        for (CashBox cb : cashBoxes) {

            if (cb.inState(State.ENABLED)) {
                enabled.add(cb);
            }
        }

        if (enabled.isEmpty()) {
            return;
        }

        int total = getTotalBuyersCount(enabled);

        int min = total / enabled.size();
        int extra = total % enabled.size();

        int[] target = new int[enabled.size()];

        // Lower-numbered cashboxes receive the larger target first
        for (int i = 0; i < enabled.size(); i++) {

            target[i] = min;

            if (extra > 0) {
                target[i]++;
                extra--;
            }
        }

        Deque<Buyer> defectors = new LinkedList<>();

        // Remove extra buyers from the END of queues
        for (int i = 0; i < enabled.size(); i++) {

            CashBox cb = enabled.get(i);

            while (cb.getQueue().size() > target[i]) {
                defectors.addLast(cb.removeLast());
            }
        }

        // Add defectors to short queues
        for (int i = 0; i < enabled.size(); i++) {

            CashBox cb = enabled.get(i);

            while (cb.getQueue().size() < target[i] && !defectors.isEmpty()) {
                cb.addLast(defectors.pollFirst());
            }
        }

        // Closing cash boxes shed their excess buyers to the shortest open queue
        for (CashBox cb : cashBoxes) {

            if (cb.inState(State.IS_CLOSING)) {

                CashBox shortest = getShortestCashBox(enabled);

                while (cb.getQueue().size() > shortest.getQueue().size() + 1) {
                    shortest.addLast(cb.removeLast());
                    shortest = getShortestCashBox(enabled);
                }
            }
        }
    }

    private static CashBox getShortestCashBox(List<CashBox> cashBoxes) {

        CashBox shortest = null;

        for (CashBox cb : cashBoxes) {

            if (shortest == null || cb.getQueue().size() < shortest.getQueue().size()) {
                shortest = cb;
            }
        }

        return shortest;
    }
}