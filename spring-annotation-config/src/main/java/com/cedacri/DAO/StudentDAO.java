package com.cedacri.DAO;

import com.cedacri.DAO.generic.AbstractGenericDAO;
import com.cedacri.model.Student;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class StudentDAO  extends AbstractGenericDAO<Student> {

  public StudentDAO() {
    setClazz(Student.class);
  }

  @Override
  public void delete(Student entity) {
    super.delete(entity.getId());
  }

  @Override
  public void deleteById(Long entityId) {
    super.delete(entityId);
  }
}
