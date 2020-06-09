package com.duke.dls.repo;

import com.duke.dls.model.entity.InventoryHistory;
import com.duke.dls.model.entity.StudentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentHistoryEntityRepository extends JpaRepository<StudentHistory, Long> {

}
