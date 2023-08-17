package com.cedacri.service;

import com.cedacri.DAO.generic.GenericDAO;
import com.cedacri.model.Student;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
  @Autowired
  private GenericDAO<Student> dao;

  public Student save(final Student entity) {
    return dao.save(entity).orElseThrow(() -> new RuntimeException("saveStudent -"));
  }

  public Student getById(final Long id){
    return dao.getById(id).orElseThrow(() -> new RuntimeException("getById(Student) not found"));
  };

  public List<Student> getAll(){
    return dao.getAll();
  };

  public Student update(final Student entity){
    Optional<Student> updatedTom = dao.update(entity);
    return updatedTom.orElseThrow(() -> new RuntimeException("update(Student) not possible"));
  };

  void delete(final Student entity){
    dao.delete(entity);
  };

  void deleteById(final Long entityId){
    dao.deleteById(entityId);
  };
}
