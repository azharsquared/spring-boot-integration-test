package com.azhar.springbootintegrationtest.entity;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
public class Student {
    //more on generated : https://thorben-janssen.com/jpa-generate-primary-keys/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="student_generator")
    @SequenceGenerator(name="student_generator", sequenceName = "student_seq", allocationSize=50)
    private Long id;
    private String name;
    private String className;

    public Student(String name, String className) {
        this.name= name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Student() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
