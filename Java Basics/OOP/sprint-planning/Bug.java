package com.epam.rd.autotasks.sprintplanning.tickets;

public class Bug extends Ticket {

    private int id;
    private String name;
    private int estimate;
    private UserStory userStory;


    public static Bug createBug(int id, String name, int estimate, UserStory userStory) {
        if (userStory == null || !userStory.isCompleted()) {
            return null;
        }
        String userBug = String.format("%s: %s", userStory.getName(), name);
        return new Bug(id, userBug, 1230,userStory);
    }

    private Bug(int id, String name, int estimate, UserStory userStory) {
        super(id, name, estimate);
        this.id = id;
        this.name = name;
        this.userStory = userStory;
        this.estimate = estimate;
    }

    @Override
    public String toString() {
        return String.format("[Bug %d] %s", id, name);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getEstimate() {
        return estimate;
    }

    @Override
    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }
}
