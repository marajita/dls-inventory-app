package com.duke.dls.repo;

import com.duke.dls.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentEntityRepository extends JpaRepository<Student, Long> {

    @Query("select u from Student u where u.isActive = 'Y'")
    List<Student> findAllActive();
}
