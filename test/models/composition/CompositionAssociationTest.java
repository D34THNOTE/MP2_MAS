package models.composition;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CompositionAssociationTest {

    Project project1;
    Project project2;

    @Before
    public void setup() {
        project1 = new Project("Fun times");
        project2 = new Project("Funnier timez");
    }

    @After
    public void nuke() {
        List<Project> toRemove = new ArrayList<>(Project.getExtent());

        for(Project project : toRemove) Project.removeProject(project);
    }

    @Test
    public void testAssociation() {
        project1.addTask("Task1");
        assertEquals(1, project1.getTasks().size());
        assertEquals("Task1", project1.getTasks().get(0).getTaskName());
        project1.removeTask(project1.getTasks().get(0));
        assertEquals(0, project1.getTasks().size());

        project1.addTask("CanAddSameTask");
        project1.addTask("CanAddSameTask");
        assertEquals(2, project1.getTasks().size());

        project1.removeTask(project1.getTasks().get(0));
        assertEquals(1, project1.getTasks().size());

        project2.addTask("CanAddSameTask");
        assertNotEquals(project1.getTasks().get(0), project2.getTasks().get(0));
    }

    @Test
    public void TaskRemoveTest() { // Task's .remove() method
        project1.addTask("SomeTask");
        assertEquals(1, project1.getTasks().size());

        Task toDelete = project1.getTasks().get(0);
        toDelete.remove();
        assertEquals(0, project1.getTasks().size());
        assertFalse(project1.getTasks().contains(toDelete));
        assertNull(toDelete.getProject());
    }

    @Test
    public void addTaskTest() {
        assertEquals(0, project1.getTasks().size());
        project1.addTask("Task 1");
        project1.addTask("Task 1", "With description");

        assertEquals(2, project1.getTasks().size());

        project2.addTask("Task 1");
        assertEquals(1, project2.getTasks().size());
        assertEquals(2, project1.getTasks().size());

        Project.removeProject(project1);
        assertThrows(IllegalStateException.class, () -> project1.addTask("Throws exception because project deactivated"));
        assertThrows(IllegalStateException.class, () -> project1.addTask("Throws exception because project deactivated", "With description too"));
    }

    @Test
    public void removeProjectTest() {
        assertThrows(IllegalArgumentException.class, () -> Project.removeProject(null));

        assertTrue(Project.getExtent().contains(project1));
        project1.addTask("A task");
        project1.addTask("B task");
        project1.addTask("C task");
        project1.removeTask(project1.getTasks().get(0));
        Project.removeProject(project1);
        assertFalse(Project.getExtent().contains(project1));
        assertEquals(0, project1.getTasks().size());

        assertThrows(IllegalArgumentException.class, () -> Project.removeProject(project1));

        new Project("Anonymous");
        Project toRemove = Project.getExtent().stream().filter(project -> (project.getProjectName().equals("Anonymous"))).findFirst().orElse(null);
        assertTrue(Project.getExtent().contains(toRemove));
        Project.removeProject(toRemove);
        assertFalse(Project.getExtent().contains(toRemove));
    }

    @Test
    public void removeTaskTest() {
        assertThrows(IllegalArgumentException.class, () -> project1.removeTask(null));

        assertEquals(0, project1.getTasks().size());
        project1.addTask("Task 1");
        project1.addTask("Task 1", "With description");

        assertEquals(2, project1.getTasks().size());

        project2.addTask("Task 1");
        assertEquals(1, project2.getTasks().size());
        assertEquals(2, project1.getTasks().size());

        Task proj2sTask = project2.getTasks().get(0);
        assertThrows(IllegalArgumentException.class, () -> project1.removeTask(proj2sTask));
        assertEquals(2, project1.getTasks().size());
    }
}
