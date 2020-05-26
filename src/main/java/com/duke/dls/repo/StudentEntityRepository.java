package com.duke.dls.repo;

import com.duke.dls.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEntityRepository extends JpaRepository<Student, Long> {
}
