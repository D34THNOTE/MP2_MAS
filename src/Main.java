import models.attribute.Course;
import models.attribute.Enrollment;
import models.attribute.Student;
import models.basic.Gallery;
import models.basic.Painting;

public class Main {
    public static void main(String[] args) {

        // Basic association
        Gallery gallery1 = new Gallery("Fantastic Art", "Krawiecka 74 Warszawa 06-965");
        Gallery gallery2 = new Gallery("Horrible Art", "Norblina 23a Warszawa 03-684");
        Painting painting1 = new Painting("P1", "Pinokio" ,34.00);
        Painting painting2 = new Painting("P2", "Fistashio" ,344.00);

        gallery1.addPainting(painting1);
        painting2.setGallery(gallery1);

        System.out.println(gallery1.getPaintings());

        gallery2.addPainting(painting1);
        System.out.println(gallery1.getPaintings());
        System.out.println(gallery2.getPaintings());





        System.out.println();
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

        System.out.println();
    }
}