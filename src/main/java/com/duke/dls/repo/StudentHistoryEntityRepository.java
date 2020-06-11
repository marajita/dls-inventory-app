package com.duke.dls.repo;

import com.duke.dls.model.entity.StudentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentHistoryEntityRepository extends JpaRepository<StudentHistory, Long> {

    @Query("select u from StudentHistory u where u.studentId = :studentId order by u.createdDate")
    List<StudentHistory> findAllStudentHistoryOrdered(@Param("studentId") Long studentId);
}
