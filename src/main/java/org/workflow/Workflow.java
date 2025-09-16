package org.workflow;


import java.util.*;

public class Workflow {
    private final Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (tasks.containsKey(task.getName())) {
            throw new IllegalArgumentException("Task already exists: " + task.getName());
        }
        tasks.put(task.getName(), task);
    }

    public void updateStatus(String task, Status status) {
        Task found = tasks.get(task);
        if (Objects.isNull(found)) {
            throw new IllegalArgumentException("Task not found");
        }

        if (!isTaskReady(found) && (status == Status.COMPLETED || status == Status.IN_PROGRESS) ) {
            throw new IllegalStateException("Dependencies are not completed");
        }

        found.setStatus(status);
    }

    public List<Task> getReadyTasks() {
        List<Task> readyTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (isTaskReady(task) && task.getStatus() == Status.PENDING) {
                readyTasks.add(task);
            }
        }
        return readyTasks;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    private boolean isTaskReady(Task task) {
        for (String t : task.getDependencies()) {
            Task dependency = this.tasks.get(t);
            if (Objects.isNull(dependency) || dependency.getStatus() != Status.COMPLETED) {
                return false;
            }
        }
        return true;
    }

//    public String toJson() throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tasks);
//    }
//
//    public static Workflow fromJson(String json) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Task> taskMap = mapper.readValue(json,
//                mapper.getTypeFactory().constructMapType(Map.class, String.class, Task.class));
//        Workflow wf = new Workflow();
//        taskMap.values().forEach(wf::addTask);
//        return wf;
//    }
}
