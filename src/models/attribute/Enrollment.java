package models.attribute;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Enrollment {

    private static Set<Enrollment> extent = new HashSet<>();
    private Student student;

    private Course course;


    public Enrollment(Student student, Course course) {
        if(student == null || course == null) throw new IllegalArgumentException("At least one of the arguments for Enrollment was null");

        if(isUnique(student, course)) {
            this.student = student;
            this.course = course;

            addEnrollment(this);
        } else {
            throw new IllegalArgumentException("Pair isn't unique");
        }
    }

    public static Set<Enrollment> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    public static void addEnrollment(Enrollment enrollment) {
        if(enrollment == null) throw new IllegalArgumentException("Enrollment to be added cannot be empty");

        if(enrollment.isUnique(enrollment.student, enrollment.course)) {
            extent.add(enrollment);
        }
        if(!enrollment.student.getEnrollments().contains(enrollment)) {
            enrollment.student.addEnrollmentStudent(enrollment);
        }
        if(!enrollment.course.getEnrollments().contains(enrollment)) {
            enrollment.course.addEnrollmentCourse(enrollment);
        }
    }

    public void remove() {
        // removing references
        if(student.getEnrollments().contains(this)) student.removeEnrollmentStudent(this);
        if(course.getEnrollments().contains(this)) course.removeEnrollmentCourse(this);

        // removing from extent if references have been removed
        if(!student.getEnrollments().contains(this) && !course.getEnrollments().contains(this)) {
            extent.remove(this);
        } else {
            throw new RuntimeException("There was an error while removing enrollment from extent!");
        }
    }

    private boolean isUnique(Student student, Course course) {
        if(student == null || course == null) throw new IllegalArgumentException("At least one of the arguments for Enrollment was null");

        for(Enrollment possibleDuplicate : extent) {
            if (possibleDuplicate.student == student && possibleDuplicate.course == course) {
                return false;
            }
        }

        return true;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}
