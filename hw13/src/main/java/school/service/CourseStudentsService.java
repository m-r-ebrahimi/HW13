package school.service;

import school.dao.CourseStudentsDao;
import school.entity.CourseStudents;
import school.entity.Item;
import school.exception.ModificationDataException;

public class CourseStudentsService {
    private CourseStudentsDao courseStudentsDao = new CourseStudentsDao();

    public void saveOrUpdate(Item item) {
        if (new CourseStudentsService().loadById(item.getStudent().getId(), item.getCourse().getId()) == null) {
            courseStudentsDao.save(item);
        } else {
            courseStudentsDao.update(item.getStudent().getId(), item.getCourse().getId(), item);
        }
    }

    public void delete(Integer id, Integer id2) {
        if ((id == null) || (id2 == null)) {
            throw new ModificationDataException("This entity NOT exist!");
        } else {
            courseStudentsDao.delete(id, id2);
        }
    }

    public Item loadById(Integer id, Integer id2) {
        if ((id == null) || (id2 == null)) {
            throw new ModificationDataException("This entity NOT exist!");
        } else {
            return courseStudentsDao.loadById(id, id2);
        }
    }

    public CourseStudents loadAll() {
        return courseStudentsDao.loadAll();
    }

    public void setCourseStudentsDao(CourseStudentsDao courseStudentsDao) {
        this.courseStudentsDao = courseStudentsDao;
    }

    public CourseStudentsDao getCourseStudentsDao() {
        return courseStudentsDao;
    }
}
