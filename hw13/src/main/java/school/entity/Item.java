package school.entity;

import java.util.Objects;

public class Item {

    private Student student;
    private Course course;
    private Integer grade;

    public Item(Student student, Course course, Integer grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return student.equals(item.student) && course.equals(item.course) && Objects.equals(grade, item.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, grade);
    }

    @Override
    public String toString() {
        return "Item{" +
                "student=" + student +
                ", course=" + course +
                ", grade=" + grade +
                '}';
    }
    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public static class ItemBuilder {
        private Student student;
        private Course course;
        private Integer grade;

        public ItemBuilder student(Student student) {
            this.student= student;
            return this;
        }

        public ItemBuilder course(Course course) {
            this.course = course;
            return this;
        }
        public ItemBuilder grade(Integer grade) {
            this.grade = grade;
            return this;
        }
        public Item build() {
            return new Item(student, course, grade);
        }
    }
}
