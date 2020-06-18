package com.duke.dls.repo;

import com.duke.dls.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentEntityRepository extends JpaRepository<Student, Long> {

    @Query("select u from Student u where u.isActive = 'Y'")
    List<Student> findAllActive();

    @Query("select u from Student u where u.inventory.inventoryId = :inventoryId")
    Student getStudentByInventoryId(@Param("inventoryId") Long inventoryId);

    Student findStudentByNetIdAndIsActive(String netId, String isActive);
}
