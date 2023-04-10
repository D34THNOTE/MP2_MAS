package models.composition;

import java.util.*;

public class Project {
    private static List<Project> extent = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private String projectName;

    public Project(String projectName) {
        setProjectName(projectName);
        extent.add(this);
    }

    public void addTask(String taskName) {
        tasks.add(new Task(taskName, this));
    }

    // overloading because taskDescription is optional
    public void addTask(String taskName, String taskDescription) {
        tasks.add(new Task(taskName, taskDescription, this));
    }

    public void removeTask(Task task) {
        if(task == null) throw new IllegalArgumentException("Chosen task is null");
        if(!tasks.contains(task)) throw new IllegalArgumentException("Chosen task doesn't exist in this project");

        task.remove();
        tasks.remove(task);
    }

    public static void removeProject(Project project) {
        if(project == null) throw new IllegalArgumentException("Chosen project is null");
        if(!extent.contains(project)) throw new IllegalArgumentException("Chosen project doesn't exist in the extent");

        project.tasks.forEach(Task::remove);
        project.tasks.clear();

        extent.remove(project);
    }

    public static List<Project> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        if(projectName == null || projectName.isBlank()) throw new IllegalArgumentException("Project's name is required");

        this.projectName = projectName;
    }
}
