package com.cedacri.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String dob;
  private Gender gender;

  public Student(String firstName, String lastName, String dob, Gender gender) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.gender = gender;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Student student = (Student) o;

    if (!Objects.equals(id, student.id)) {
      return false;
    }
    if (!Objects.equals(firstName, student.firstName)) {
      return false;
    }
    if (!Objects.equals(lastName, student.lastName)) {
      return false;
    }
    if (!Objects.equals(dob, student.dob)) {
      return false;
    }
    return gender == student.gender;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (dob != null ? dob.hashCode() : 0);
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    return result;
  }
}
