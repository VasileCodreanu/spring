package com.cedacri.DAO.generic;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractGenericDAO<T extends Serializable> implements GenericDAO<T> {

  private Class<T> clazz;

  //    @PersistenceContext(unitName = "myEmf")
  protected EntityManager entityManager; //Spring injects a proper instance of EntityManager according to the .xml configuration.

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }

  public final void setClazz(final Class<T> clazzToSet) {
    this.clazz = clazzToSet;
  }

  public Optional<T> getById(final Long id) {
    //In the event that we need to detach an entity from the persistence context, we can use the detach() method.
    // We pass the object to be detached as the parameter to the method: em.detach(movie);( it'll be in the detached state)
    return Optional.of( entityManager.find(clazz, id) );
  }

  public List<T> getAll() {
    return entityManager.createQuery(
        "from " + clazz.getName()).getResultList();
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
