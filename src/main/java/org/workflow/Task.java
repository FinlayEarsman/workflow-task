package org.workflow;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private final String name;
    private Status status;
    private final List<String> dependencies;

    public Task(String name, List<String> dependencies) {
        this.name = name;
        this.status = Status.PENDING;
        this.dependencies = new ArrayList<>(dependencies);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
