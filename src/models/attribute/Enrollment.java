package models.attribute;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Enrollment {

    private static Set<Enrollment> extent = new HashSet<>();
    private Student student;

    private Course course;



    public Enrollment(Student student, Course course) {

        if(isUnique(student, course)) {
            this.student = student;
            this.course = course;
//            setStartDate(startDate);
//            setEndDate(endDate);

            addEnrollment(this);
            extent.add(this);
        } else {
            throw new IllegalArgumentException("Pair isn't unique");
        }
    }

    public static Set<Enrollment> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    public static void addEnrollment(Enrollment enrollment) {
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
        boolean unique = true;

        for(Enrollment possibleDuplicate : extent) {
            if (possibleDuplicate.student == student && possibleDuplicate.course == course) {
                unique = false;
                break;
            }
        }

        return unique;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        if (startDate == null) throw new IllegalArgumentException("Start date is required");
//        if (startDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("Start date cannot be a date before the current moment");
//
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        if (endDate == null) throw new IllegalArgumentException("End date is required");
//        if (endDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("End date cannot be a date before the current moment");
//
//        this.endDate = endDate;
//    }
}
