package com.cedacri.service;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cedacri.ContextProvider;
import com.cedacri.model.Gender;
import com.cedacri.model.Student;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

class StudentServiceTest {
  private final static ApplicationContext ctx = ContextProvider.provideContext();
  private static StudentService service;
  private Student firstStudent;
  private  Student secondStudent;

  @BeforeAll
  static void beforeAll() {
    service = ctx.getBean("studentService", StudentService.class);
  }

  @BeforeEach
  void setUp() {
    firstStudent  = new Student("firstStudent", "smith", LocalDate.now().toString(), Gender.MALE);
    secondStudent = new Student("secondStudent", "chan", LocalDate.now().toString(), Gender.FEMALE);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void save() {
    Student savedStudent = service.save(firstStudent);

    assertAll(
        () -> assertNotNull(savedStudent, "should return a valid entity(is returning NULL);"),
        () -> assertTrue(savedStudent.getId() > 0,
            "entity should return a valid DB id(id > 0);")
    );
    service.delete(savedStudent);
  }


  @Test
  void getById() {
    Student savedStudent = service.save(firstStudent);
    Student studentById = service.getById(savedStudent.getId());

    assertAll(
        () -> assertNotNull(studentById, "should return a valid entity(is returning NULL);"),
        () -> assertTrue(studentById.getId() > 0,
            "entity should return a valid DB id(id > 0);"),
        () -> assertEquals(studentById.getFirstName(), firstStudent.getFirstName(),
            "Incorrect client firstName;"),
        () -> assertEquals(studentById.getLastName(), firstStudent.getLastName(),
            "Incorrect client lastName;")
    );
  }

  @Test
  void getAll() {
    service.save(firstStudent);
    service.save(secondStudent);

    List<Student> students = service.getAll();
    assertAll(
        () -> assertNotNull(students, "List of teachers should not be empty;"),
        () -> assertEquals(2, students.size(), "list size() should be equal 2"),
        () -> assertTrue(students.stream().findFirst().get().getId() > 0)
    );
  }

  @Test
  void update() {
    Student savedStudent= service.save(firstStudent);
    savedStudent.setFirstName("updatedName");
    Student updatedStudent = service.update(savedStudent);

    assertAll(
        () -> assertNotNull(updatedStudent, "Should return updated Student, but entity is Null"),
        () -> assertEquals(updatedStudent.getFirstName(), "updatedName",
            "Entities should have updated lastName"),
        () -> assertEquals(updatedStudent.getId(), updatedStudent.getId(),
            "DB should return same entity.")
    );
  }

  @Test
  void delete() {
    Student savedStudent = service.save(secondStudent);
    int sizeBeforeDelete = service.getAll().size();
    service.delete(savedStudent);
    int sizeAfterDelete = service.getAll().size();
    assertAll(
        () -> assertEquals(sizeAfterDelete, sizeBeforeDelete - 1,
            "after delete(), initial Set.size() should be greater by one, than Set.size() after delete();")
    );
  }

  @Test
  void deleteById() {
  }
}