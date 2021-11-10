package school.service.core;

import school.dao.core.BaseDao;
import school.entity.base.BaseEntity;
import school.exception.ModificationDataException;

import java.util.List;

public class AbstractCrudService<T extends BaseEntity<ID>, ID extends Number> {
    private BaseDao<T, ID> baseDao;

    public void saveOrUpdate(T entity) {
        if (entity.getId() == null) {
            baseDao.save(entity);
        } else {
            baseDao.update(entity.getId(), entity);
        }
    }

    public void delete(ID id) {
        if (id == null) {
            throw new ModificationDataException("This entity NOT exist!");
        } else {
            baseDao.delete(id);
        }
    }

    public T loadById(ID id) {
        if (id == null) {
            throw new ModificationDataException("This entity NOT exist!");
        } else {
            return baseDao.loadById(id);
        }
    }

    public List<T> loadAll() {
        return baseDao.loadAll();
    }

    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseDao<T, ID> getBaseDao() {
        return baseDao;
    }
}