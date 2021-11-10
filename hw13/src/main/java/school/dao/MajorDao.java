package school.dao;

import school.config.DataSourceConfig;
import school.dao.core.BaseDao;
import school.entity.Major;
import school.exception.DataNotFoundException;
import school.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MajorDao implements BaseDao<Major, Integer> {
    private final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();
    private Connection connection;

    @Override
    public void save(Major entity) {
        try {
            connection = dataSourceConfig.createDataSource().getConnection();
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO maktab.major (name) VALUES(?);");) {
                ps.setString(1, entity.getName());
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
    public void update(Integer id, Major newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE maktab.major SET name = ? WHERE m_id =;" + id);) {
            ps.setString(1, newEntity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM maktab.major WHERE m_id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public Major loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM maktab.major WHERE m_id = ?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery();) {
                Major major = null;
                while (resultSet.next()) {
                    int majorId = resultSet.getInt("m_id");
                    String name = resultSet.getString("name");
                    major = Major.builder().id(majorId).name(name).build();
                }
                return major;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<Major> loadAll() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM maktab.major");
             ResultSet resultSet = ps.executeQuery();) {
            List<Major> majors = new ArrayList<>();
            while (resultSet.next()) {
                int majorId = resultSet.getInt("m_id");
                String name = resultSet.getString("name");
                Major major = Major.builder().id(majorId).name(name).build();
                majors.add(major);
            }
            return majors;
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