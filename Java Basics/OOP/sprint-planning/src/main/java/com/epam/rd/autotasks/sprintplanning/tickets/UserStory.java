package com.epam.rd.autotasks.sprintplanning.tickets;

import java.util.Arrays;

public class UserStory extends Ticket {

    private final UserStory[] dependencies;

    public UserStory(int id, String name, int estimate, UserStory ... dependencies) {
        super(id, name, estimate);
        this.dependencies = dependencies == null ? new UserStory[0] : Arrays.copyOf(dependencies, dependencies.length);
    }

    @Override
    public void complete() {
        for (UserStory dependency : dependencies) {
            if (dependency != null && !dependency.isCompleted()) {
                return;
            }
        }
        super.complete();
    }

    public UserStory[] getDependencies() {
        return Arrays.copyOf(dependencies, dependencies.length);
    }

    @Override
    public String toString() {
        return String.format("[US %d] %s", getId(), getName());
    }
}
