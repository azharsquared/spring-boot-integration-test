package com.azhar.springbootintegrationtest.repository;

import com.azhar.springbootintegrationtest.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
