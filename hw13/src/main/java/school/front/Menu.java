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
                    Major major = new MajorService().getBaseDao().loadById(majorId);
                    new StudentService().saveOrUpdate(Student.builder().name(name).familyName(familyName).major(major).build());
                } else if (select == 2) {
                    System.out.println("Enter id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    new StudentService().delete(id);
                } else if (select == 3) {
                    System.out.println("Enter id: ");
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
                    System.out.println("Enter student name: ");
                    String name = input.nextLine();
                    new MajorService().saveOrUpdate(Major.builder().name(name).build());
                } else if (select == 2) {
                    System.out.println("Enter id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    new MajorService().delete(id);
                } else if (select == 3) {
                    System.out.println("Enter id: ");
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
                    System.out.println("Enter name: ");
                    String name = input.nextLine();
                    System.out.println("Enter unit: ");
                    int unit = input.nextInt();
                    input.nextLine();
                    new CourseService().saveOrUpdate(Course.builder().name(name).unit(unit).build());
                } else if (select == 2) {
                    System.out.println("Enter id: ");
                    int id = input.nextInt();
                    input.nextLine();
                    new CourseService().delete(id);
                } else if (select == 3) {
                    System.out.println("Enter id: ");
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
                    System.out.println("Enter student's id: ");
                    int num = input.nextInt();
                    input.nextLine();
                    Student student = new StudentService().loadById(num);
                    System.out.println("Enter course's id: ");
                    num = input.nextInt();
                    input.nextLine();
                    Course course = new CourseService().loadById(num);
                    System.out.println("Enter grade: ");
                    num = input.nextInt();
                    input.nextLine();
                    new CourseStudentsService().saveOrUpdate(Item.builder().student(student).course(course).grade(num).build());
                } else if (select == 2) {
                    System.out.println("Enter student's id: ");
                    int num1 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter course's id: ");
                    int num2 = input.nextInt();
                    input.nextLine();
                    new CourseStudentsService().delete(num1, num2);
                } else if (select == 3) {
                    System.out.println("Enter student's id: ");
                    int num1 = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter course's id: ");
                    int num2 = input.nextInt();
                    input.nextLine();
                    System.out.println(new CourseStudentsService().loadById(num1, num2));
                } else if (select == 4) {
                    System.out.println(new CourseStudentsService().loadAll());
                }
            } else if (select == 5) {
                break;
            }
        }
    }
}

