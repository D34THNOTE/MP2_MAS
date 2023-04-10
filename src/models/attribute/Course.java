package models.attribute;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Course {
    private String courseName;

    private Set<Enrollment> enrollments = new HashSet<>();

    public Course(String courseName) {
        setCourseName(courseName);
    }

    public void addEnrollmentCourse(Enrollment enrollment) {
        if(enrollment == null) throw new IllegalArgumentException("Passed enrollment cannot be null");
        if(enrollments.contains(enrollment)) throw new IllegalArgumentException("Passed enrollment already exists for this course");

        enrollments.add(enrollment);
        Enrollment.addEnrollment(enrollment);
    }

    public void removeEnrollmentCourse(Enrollment enrollment) {
        if(enrollment == null) throw new IllegalArgumentException("Passed enrollment cannot be null");

        if(enrollments.contains(enrollment)) {
            enrollments.remove(enrollment);
        }
        if(Enrollment.getExtent().contains(enrollment)) {
            enrollment.remove();
        }
    }

    public Set<Enrollment> getEnrollments() {
        return Collections.unmodifiableSet(enrollments);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if(courseName == null || courseName.isBlank()) throw new IllegalArgumentException("Course's name is required");

        this.courseName = courseName;
    }
}
