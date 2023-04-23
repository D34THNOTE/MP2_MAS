package models.composition;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Task {
    private String taskName, taskDescription;

    private Project project;

    public Task(String taskName, Project project) {
        setTaskName(taskName);
        setProject(project);
    }

    public Task(String taskName, String taskDescription, Project project) {
        setTaskName(taskName);
        setTaskDescription(taskDescription);
        setProject(project);
    }

    public void remove() {
        if(project.getTasks().contains(this)) project.removeTask(this);

        project = null;
    }

    private void setProject(Project project) {
        if(project == null) throw new IllegalArgumentException("Project is required to create a task");
        if(!Project.getExtent().contains(project)) throw new IllegalArgumentException("Selected project is deactivated");

        this.project = project;
    }

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

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                (taskDescription == null ? "" : ", taskDescription='" + taskDescription + '\'')  +
                '}';
    }
}
