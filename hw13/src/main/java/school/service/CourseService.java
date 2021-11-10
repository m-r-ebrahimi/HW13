package school.service;

import school.dao.CourseDao;
import school.entity.Course;
import school.service.core.AbstractCrudService;

public class CourseService extends AbstractCrudService<Course, Integer> {
    public CourseService() {
        setBaseDao(new CourseDao());
    }

    public CourseDao getBaseDao() {
        return (CourseDao) super.getBaseDao();
    }
}
