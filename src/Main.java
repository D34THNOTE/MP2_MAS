import models.attribute.Course;
import models.attribute.Enrollment;
import models.attribute.Student;

public class Main {
    public static void main(String[] args) {

        // Association with attribute
        Student student1 = new Student("Mark", "Twain");
        Course course1 = new Course("Computers");

        Enrollment enrollment1 = new Enrollment(student1, course1);

        Student student2 = new Student("Moose", "Doose");

        // I don't pass the anonymous object to .addEnrollmentStudent() because it would first add the object using "addEnrollment" method called from
        // the constructor and then try to add it again because of the .addEnrollmentStudent() method resulting in an error, since the enrollment was already
        // added and I check for uniqueness inside .addEnrollmentStudent()
        new Enrollment(student2, course1);

        student1.removeEnrollmentStudent(enrollment1);
        System.out.println(student1.getEnrollments());
        System.out.println(student2.getEnrollments());
    }
}