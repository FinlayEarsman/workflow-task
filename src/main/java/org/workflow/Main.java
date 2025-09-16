package org.workflow;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Task t1 = new Task("Fetch Data", List.of());
        Task t2 = new Task("Validate Data", List.of("Fetch Data"));
        Task t3 = new Task("Generate Report", List.of("Validate Data"));

        Workflow workflow = new Workflow();
        workflow.addTask(t1);
        workflow.addTask(t2);
        workflow.addTask(t3);

        System.out.println("Ready Tasks: ");
        System.out.println(workflow.getReadyTasks());

        workflow.updateStatus("Fetch Data", Status.COMPLETED);
        System.out.println("Ready Tasks after Fetch Data completed: ");
        System.out.println(workflow.getReadyTasks());

        workflow.updateStatus("Validate Data", Status.IN_PROGRESS);
        System.out.println("Ready Tasks after Validate Data in progress: ");
        System.out.println(workflow.getReadyTasks());
    }
}