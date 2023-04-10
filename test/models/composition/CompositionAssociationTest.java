package models.composition;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CompositionAssociationTest {

    Project project;
    Project project2;

    @Before
    public void setup() {
        project = new Project("Fun times");
        project2 = new Project("Funnier timez");
    }

    @Test
    public void testAssociation() {
        project.addTask("Task1");
        assertEquals(1, project.getTasks().size());
        assertEquals("Task1", project.getTasks().get(0).getTaskName());
        project.removeTask(project.getTasks().get(0));
        assertEquals(0, project.getTasks().size());

        project.addTask("CanAddSameTask");
        project.addTask("CanAddSameTask");
        assertEquals(2, project.getTasks().size());

        project.removeTask(project.getTasks().get(0));
        assertEquals(1, project.getTasks().size());

        project2.addTask("CanAddSameTask");
        assertNotEquals(project.getTasks().get(0), project2.getTasks().get(0));
    }
}
