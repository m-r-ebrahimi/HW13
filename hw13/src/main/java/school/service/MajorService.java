package school.service;

import school.dao.MajorDao;
import school.entity.Major;
import school.service.core.AbstractCrudService;

public class MajorService extends AbstractCrudService<Major, Integer> {
    public MajorService() {
        setBaseDao(new MajorDao());
    }

    public MajorDao getBaseDao() {
        return (MajorDao) super.getBaseDao();
    }
}
