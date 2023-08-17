package com.cedacri.DAO.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractGenericDAO<T extends Serializable> implements GenericDAO<T> {

  private Class<T> clazz;
  //Spring injects a proxy that is Thread-safe(will be container-managed entity manager), EXTENDED keeps the persistence context for the whole lifecycle of the bean
  //If we used the default PersistenceContextType.TRANSACTION type, the returned object would become detached at the end of a transaction’s execu- tion. Passing it to the delete method would result in an “IllegalArgumentException: Removing a detached instance” exception.
  @PersistenceContext(unitName = "myEmf", type = PersistenceContextType.EXTENDED)
  protected EntityManager entityManager;

  public final void setClazz(final Class<T> clazzToSet) {
    this.clazz = clazzToSet;
  }

  public Optional<T> getById(final Long id) {
    return Optional.of( entityManager.find(clazz, id) );
  }

  public List<T> getAll() {
    return entityManager.createQuery(
        "from " + clazz.getName(), clazz).getResultList();
  }

  public Optional<T> save(final T entity) {
    entityManager.persist(entity);
    return Optional.of(entity);
  }

  public Optional<T> update(final T entity) {
    return Optional.of(entityManager.merge(entity));
  }

  public void delete( final Long id) {
    T t = entityManager.find(clazz, id);
    entityManager.remove(t);
  }
}
