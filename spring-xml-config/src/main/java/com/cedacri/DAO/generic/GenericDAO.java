package com.cedacri.DAO.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T extends Serializable> {

    void setClazz(Class< T > clazzToSet);

    Optional<T> getById(final Long id);

    List<T> getAll();

    Optional<T> save(final T entity);

    Optional<T> update(final T entity);

    void delete(final T entity);

    void deleteById(final Long entityId);
}
