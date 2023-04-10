package models.composition;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Task {
    //private static Set<Task> extent = new HashSet<>();

    private String taskName, taskDescription;

    Project project;

    public Task(String taskName, Project project) {
        setTaskName(taskName);
        this.project = project;
    }

    public Task(String taskName, String taskDescription, Project project) {
        setTaskName(taskName);
        setTaskDescription(taskDescription);
        this.project = project;
    }

    public void remove() {
        project = null;
    }

//    public static Set<Task> getExtent() {
//        return Collections.unmodifiableSet(extent);
//    }

    public Project getProject() {
        return project;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        if(taskName == null || taskName.isBlank()) throw new IllegalArgumentException("Task's name is required");

        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        if(taskDescription != null && taskDescription.isBlank()) throw new IllegalArgumentException("Entered task description is empty");

        this.taskDescription = taskDescription;
    }
}
