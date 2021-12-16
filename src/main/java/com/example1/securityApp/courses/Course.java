package com.example1.securityApp.courses;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Course {
    @SequenceGenerator(name = "sequence_course",
    sequenceName = "sequence_course",
    allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
    generator = "sequence_course")
  private Long id;
  private String CourseName;
  private String Sylubus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getSylubus() {
        return Sylubus;
    }

    public void setSylubus(String sylubus) {
        Sylubus = sylubus;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", CourseName='" + CourseName + '\'' +
                ", Sylubus='" + Sylubus + '\'' +
                '}';
    }
}
