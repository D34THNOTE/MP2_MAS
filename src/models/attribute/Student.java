package models.attribute;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Student {
    private String firstName, lastName;

    private Set<Enrollment> enrollments = new HashSet<>();

    public Student(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void addEnrollmentStudent(Enrollment enrollment) {
        if(enrollment == null) throw new IllegalArgumentException("Passed enrollment cannot be null");
        if(enrollment.getStudent() != this) throw new IllegalArgumentException("Chosen Enrollment doesn't contain this student");
        if(enrollments.contains(enrollment)) throw new IllegalArgumentException("Passed enrollment already exists for this student");

        enrollments.add(enrollment);
        Enrollment.addEnrollment(enrollment);
    }

    public void removeEnrollmentStudent(Enrollment enrollment) {
        if(enrollment == null) throw new IllegalArgumentException("Passed enrollment cannot be null");
        if(enrollment.getStudent() != this) throw new IllegalArgumentException("Chosen Enrollment doesn't contain this student");
        // check if the enrollment belongs to this student
        if(!enrollments.contains(enrollment)) throw new IllegalArgumentException("Chosen enrollment doesn't belong to this student");

        // we don't check if this enrollment is in "enrollments" because if it isn't simply nothing will happen
        enrollments.remove(enrollment);
        if(Enrollment.getExtent().contains(enrollment)) {
            enrollment.remove();
        }
    }

    public Set<Enrollment> getEnrollments() {
        return Collections.unmodifiableSet(enrollments);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.isBlank()) throw new IllegalArgumentException("First name is required");

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Last name is required");

        this.lastName = lastName;
    }
}
