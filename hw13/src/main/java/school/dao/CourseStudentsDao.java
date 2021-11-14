package school.dao;

import school.config.DataSourceConfig;
import school.entity.*;
import school.exception.DataNotFoundException;
import school.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseStudentsDao {
    private final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();
    private Connection connection;

    public void save(Item entity) {
        try {
            connection = dataSourceConfig.createDataSource().getConnection();
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO maktab.course_students (s_id, c_id, grade) VALUES(?, ?, ?);");) {
                ps.setInt(1, entity.getStudent().getId());
                ps.setInt(2, entity.getCourse().getId());
                ps.setInt(3, entity.getGrade());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not insert data to db");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Integer id, Integer id2, Item newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE maktab.course_students SET grade = ? WHERE s_id = " + id + " AND c_id = " + id2);) {
            ps.setInt(1, newEntity.getGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    public void delete(Integer id, Integer id2) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM maktab.course_students WHERE s_id = ? AND c_id = ?;")) {
            ps.setInt(1, id);
            ps.setInt(2, id2);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    public Item loadById(Integer id, Integer id2) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM maktab.course_students WHERE s_id = ? AND c_id = ?;")) {
            ps.setInt(1, id);
            ps.setInt(2, id2);
            try (ResultSet resultSet = ps.executeQuery();) {
                Item item = null;
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("s_id");
                    Student student = new StudentDao().loadById(studentId);
                    int courseId = resultSet.getInt("c_id");
                    Course course = new CourseDao().loadById(courseId);
                    int grade = resultSet.getInt("grade");
                    item = Item.builder().student(student).course(course).grade(grade).build();
                }
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    public CourseStudents loadAll() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM maktab.course_students");
             ResultSet resultSet = ps.executeQuery();) {
            CourseStudents courseStudents = new CourseStudents();
            Set<Item> items = new HashSet<>();
            Item item = null;
            while (resultSet.next()) {
                int studentId = resultSet.getInt("s_id");
                Student student = new StudentDao().loadById(studentId);
                int courseId = resultSet.getInt("c_id");
                Course course = new CourseDao().loadById(courseId);
                int grade = resultSet.getInt("grade");
                item = Item.builder().student(student).course(course).grade(grade).build();
                items.add(item);
            }
            courseStudents.setItems(items);
            return courseStudents;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connection.commit();
    }
}
