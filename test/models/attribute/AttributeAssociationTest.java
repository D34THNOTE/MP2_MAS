package models.attribute;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AttributeAssociationTest {

    Student student1;
    Student student2;

    Course course1;
    Course course2;

    @Before
    public void setup() {
        student1 = new Student("Patric", "Edge");
        student2 = new Student("Mark", "Benz");

        course1 = new Course("Calculus");
        course2 = new Course("Statistics");
    }

    @Test
    public void everythingTest() {
        Enrollment testEnrollment = new Enrollment(student1, course1);
        assertTrue(Enrollment.getExtent().contains(testEnrollment));
        assertTrue(student1.getEnrollments().contains(testEnrollment));
        assertTrue(course1.getEnrollments().contains(testEnrollment));

        student1.removeEnrollmentStudent(testEnrollment);
        assertFalse(Enrollment.getExtent().contains(testEnrollment));
        assertFalse(student1.getEnrollments().contains(testEnrollment));
        assertFalse(course1.getEnrollments().contains(testEnrollment));

        student1.addEnrollmentStudent(testEnrollment);
        assertTrue(Enrollment.getExtent().contains(testEnrollment));
        assertTrue(course1.getEnrollments().contains(testEnrollment));
        assertTrue(student1.getEnrollments().contains(testEnrollment));

        student1.removeEnrollmentStudent(testEnrollment);
        assertFalse(Enrollment.getExtent().contains(testEnrollment));
        assertFalse(student1.getEnrollments().contains(testEnrollment));
        assertFalse(course1.getEnrollments().contains(testEnrollment));

        course1.addEnrollmentCourse(testEnrollment);
        assertTrue(Enrollment.getExtent().contains(testEnrollment));
        assertTrue(course1.getEnrollments().contains(testEnrollment));
        assertTrue(student1.getEnrollments().contains(testEnrollment));

        course1.removeEnrollmentCourse(testEnrollment);
        assertFalse(Enrollment.getExtent().contains(testEnrollment));
        assertFalse(student1.getEnrollments().contains(testEnrollment));
        assertFalse(course1.getEnrollments().contains(testEnrollment));


        Enrollment.addEnrollment(testEnrollment);
        assertTrue(Enrollment.getExtent().contains(testEnrollment));
        assertTrue(course1.getEnrollments().contains(testEnrollment));
        assertTrue(student1.getEnrollments().contains(testEnrollment));

        assertThrows(IllegalArgumentException.class, () -> student1.addEnrollmentStudent(testEnrollment));
        assertThrows(IllegalArgumentException.class, () -> course1.addEnrollmentCourse(testEnrollment));
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(student1, course1));

        Enrollment testEnrollment2 =  new Enrollment(student2, course1);
        assertTrue(Enrollment.getExtent().contains(testEnrollment2));
        assertTrue(course1.getEnrollments().contains(testEnrollment2));
        assertTrue(student2.getEnrollments().contains(testEnrollment2));

        assertThrows(IllegalArgumentException.class, () -> student2.addEnrollmentStudent(testEnrollment2));
        assertThrows(IllegalArgumentException.class, () -> course1.addEnrollmentCourse(testEnrollment2));

        // there's no error throwing in Enrollment's "add" method but the same enrollment won't be added
        assertEquals(2, Enrollment.getExtent().size());
        Enrollment.addEnrollment(testEnrollment2);
        assertEquals(2, Enrollment.getExtent().size());
        assertEquals(1, student1.getEnrollments().size());
        assertEquals(1, student2.getEnrollments().size());
        assertEquals(2, course1.getEnrollments().size());

        // trying to pass an enrollment not belonging to student to the remove methods
        assertThrows(IllegalArgumentException.class, () -> student1.removeEnrollmentStudent(testEnrollment2));
        assertThrows(IllegalArgumentException.class, () -> course2.removeEnrollmentCourse(testEnrollment2));

        assertThrows(IllegalArgumentException.class, () -> new Enrollment(null, course1));
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(student2, null));
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(null, null));

        // trying to pass an Enrollment that doesn't belong to the student
        assertThrows(IllegalArgumentException.class, () -> student1.addEnrollmentStudent(testEnrollment2));
        assertThrows(IllegalArgumentException.class, () -> course2.addEnrollmentCourse(testEnrollment2));

        assertThrows(IllegalArgumentException.class, () -> course2.removeEnrollmentCourse(testEnrollment2));
        assertThrows(IllegalArgumentException.class, () -> student1.removeEnrollmentStudent(testEnrollment2));

        // Creating an Enrollment like this will throw an error
        assertThrows(IllegalArgumentException.class, () -> student2.addEnrollmentStudent(new Enrollment(student2, new Course("hey"))));
    }
}
