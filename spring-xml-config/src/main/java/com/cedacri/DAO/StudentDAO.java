package com.cedacri.DAO;

import com.cedacri.DAO.generic.AbstractGenericDAO;
import com.cedacri.model.Student;
//@Repository
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
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
