package school.dao;

import school.config.DataSourceConfig;
import school.dao.core.BaseDao;
import school.entity.Course;
import school.exception.DataNotFoundException;
import school.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements BaseDao<Course, Integer> {
    private final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();
    private Connection connection;

    @Override
    public void save(Course entity) {
        try {
            connection = dataSourceConfig.createDataSource().getConnection();
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO maktab.course (name, unit) VALUES(?, ?);");) {
                ps.setString(1, entity.getName());
                ps.setInt(2, entity.getUnit());
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

    @Override
    public void update(Integer id, Course newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE maktab.course SET name = ?, unit = ? WHERE c_id =;" + id);) {
            ps.setString(1, newEntity.getName());
            ps.setInt(2, newEntity.getUnit());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM maktab.course WHERE c_id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public Course loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM maktab.course WHERE c_id = ?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery();) {
                Course course = null;
                while (resultSet.next()) {
                    int courseId = resultSet.getInt("c_id");
                    String name = resultSet.getString("name");
                    int unit = resultSet.getInt("unit");
                    course = Course.builder().id(courseId).name(name).unit(unit).build();
                }
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<Course> loadAll() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM maktab.course");
             ResultSet resultSet = ps.executeQuery();) {
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                int courseId = resultSet.getInt("c_id");
                String name = resultSet.getString("name");
                int unit = resultSet.getInt("unit");
                Course course = Course.builder().id(courseId).name(name).unit(unit).build();
                courses.add(course);
            }
            return courses;
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