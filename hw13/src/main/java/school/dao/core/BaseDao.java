package school.dao.core;

import school.entity.base.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity<ID>, ID extends Number> {
    void save(T entity);

    void update(ID id, T newEntity);

    void delete(ID id);

    T loadById(ID id);

    List<T> loadAll();
}