package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

public class Sprint {

    private final int capacity;
    private final int ticketsLimit;

    private final Ticket[] tickets;
    private int size;

    public Sprint(int capacity, int ticketsLimit) {
        this.capacity = capacity;
        this.ticketsLimit = ticketsLimit;
        tickets = new Ticket[ticketsLimit];
        size = 0;
    }


    public boolean addUserStory(UserStory userStory) {

        if (userStory == null || userStory.isCompleted()) {  return false; }
        int newTotalEstimate =
                getTotalEstimate() + userStory.getEstimate();
        if (newTotalEstimate > capacity || size >= ticketsLimit) {
            return false;
        }
        UserStory[] dependencies = userStory.getDependencies();
        for (UserStory dependency : dependencies) {
            if (dependency == null) {
                return false;
            }
            if (!dependency.isCompleted()
                    && !contains(dependency)) {
                return false;
            }
        }

        tickets[size] = userStory;
        size++;

        return true;
    }


    public boolean addBug(Bug bugReport) {

        if (bugReport == null || bugReport.isCompleted()) { return false; }

        int newTotalEstimate =
                getTotalEstimate() + bugReport.getEstimate();

        if (newTotalEstimate > capacity || size >= ticketsLimit) {
            return false;
        }

        tickets[size] = bugReport;
        size++;

        return true;
    }


    public Ticket[] getTickets() {

        Ticket[] result = new Ticket[size];

        for (int i = 0; i < size; i++) {
            result[i] = tickets[i];
        }

        return result;
    }


    public int getTotalEstimate() {

        int totalEstimate = 0;

        for (int i = 0; i < size; i++) {
            totalEstimate += tickets[i].getEstimate();
        }

        return totalEstimate;
    }


    private boolean contains(UserStory userStory) {

        for (int i = 0; i < size; i++) {

            if (tickets[i] == userStory) {
                return true;
            }
        }

        return false;
    }
}