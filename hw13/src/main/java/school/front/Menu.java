package school.front;

import school.dao.CourseStudentsDao;
import school.entity.Course;
import school.entity.Item;
import school.entity.Major;
import school.entity.Student;
import school.service.CourseService;
import school.service.CourseStudentsService;
import school.service.MajorService;
import school.service.StudentService;

import java.util.Scanner;

public class Menu {
    private Integer select;
    private Scanner input = new Scanner(System.in);

    public void option() {
        while (true) {
            System.out.println("1.student, 2.major, 3.course, 4.grade, 5.exit");
            select = input.nextInt();
            input.nextLine();
            if (select == 1) {
                System.out.println("1.add, 2.del, 3.readById, 4.readAll");
                select = input.nextInt();
                input.nextLine();
                if (select == 1) {
                    System.out.println("Enter student name: ");
                    String name = input.nextLine();
                    System.out.println("Enter family name: ");
                    String familyName = input.nextLine();
                    System.out.println("Enter major id: ");
                    int majorId = input.nextInt();
                    input.nextLine();
                    Major major = new MajorService().loadById(majorId);
                    new StudentService().saveOrUpdate(Student.builder().name(name).familyName(familyName).major(major).build());
                } else if (select == 2) {
                    System.out.println("Enter student id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    new StudentService().delete(id);
                } else if (select == 3) {
                    System.out.println("Enter student id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.println(new StudentService().loadById(id));
                } else if (select == 4) {
                    System.out.println(new StudentService().loadAll());
                }
            } else if (select == 2) {
                System.out.println("1.add, 2.del, 3.readById, 4.readAll");
                select = input.nextInt();
                input.nextLine();
                if (select == 1) {
                    System.out.println("Enter major name: ");
                    String name = input.nextLine();
                    new MajorService().saveOrUpdate(Major.builder().name(name).build());
                } else if (select == 2) {
                    System.out.println("Enter major id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    new MajorService().delete(id);
                } else if (select == 3) {
                    System.out.println("Enter major id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.println(new MajorService().loadById(id));
                } else if (select == 4) {
                    System.out.println(new MajorService().loadAll());
                }
            } else if (select == 3) {
                System.out.println("1.add, 2.del, 3.readById, 4.readAll");
                select = input.nextInt();
                input.nextLine();
                if (select == 1) {
                    System.out.println("Enter course name: ");
                    String name = input.nextLine();
                    System.out.println("Enter course unit: ");
                    int unit = input.nextInt();
                    input.nextLine();
                    new CourseService().saveOrUpdate(Course.builder().name(name).unit(unit).build());
                } else if (select == 2) {
                    System.out.println("Enter course id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    new CourseService().delete(id);
                } else if (select == 3) {
                    System.out.println("Enter course id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.println(new CourseService().loadById(id));
                } else if (select == 4) {
                    System.out.println(new CourseService().loadAll());
                }
            } else if (select == 4) {
                System.out.println("1.add, 2.del, 3.readById, 4.readAll");
                select = input.nextInt();
                input.nextLine();
                if (select == 1) {
                    System.out.println("Enter student id: ");
                    int studentId = input.nextInt();
                    input.nextLine();
                    Student student = new StudentService().loadById(studentId);
                    System.out.println("Enter course id: ");
                    int courseId = input.nextInt();
                    input.nextLine();
                    Course course = new CourseService().loadById(courseId);
                    System.out.println("Enter grade: ");
                    int grade = input.nextInt();
                    input.nextLine();
                    new CourseStudentsService().setCourseStudentsDao(new CourseStudentsDao()).saveOrUpdate(new Item(student, course, grade));
                } else if (select == 2) {
                    System.out.println("Enter student id: ");
                    int studentId = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter course id: ");
                    int courseId = input.nextInt();
                    input.nextLine();
                    new CourseStudentsService().setCourseStudentsDao(new CourseStudentsDao()).delete(studentId, courseId);
                } else if (select == 3) {
                    System.out.println("Enter student id: ");
                    int studentId = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter course id: ");
                    int courseId = input.nextInt();
                    input.nextLine();
                    System.out.println(new CourseStudentsService().setCourseStudentsDao(new CourseStudentsDao()).loadById(studentId, courseId));
                } else if (select == 4) {
                    System.out.println(new CourseStudentsService().setCourseStudentsDao(new CourseStudentsDao()).loadAll());
                }
            } else if (select == 5) {
                break;
            } else {
                System.out.println("Try again");
            }
        }
    }
}

