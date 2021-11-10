package school.service;

import school.dao.StudentDao;
import school.entity.Student;
import school.service.core.AbstractCrudService;

public class StudentService extends AbstractCrudService<Student, Integer> {
    public StudentService() {
        setBaseDao(new StudentDao());
    }

    public StudentDao getBaseDao() {
        return (StudentDao) super.getBaseDao();
    }
}