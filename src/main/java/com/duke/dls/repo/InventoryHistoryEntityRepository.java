package com.duke.dls.repo;

import com.duke.dls.model.entity.Inventory;
import com.duke.dls.model.entity.InventoryHistory;
import com.duke.dls.model.entity.StudentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryHistoryEntityRepository extends JpaRepository<InventoryHistory, Long> {

    @Query("select u from InventoryHistory u where u.inventoryId = :inventoryId order by u.createdDate")
    List<InventoryHistory> findAllInventoryHistoryOrdered(@Param("inventoryId") Long inventoryId);
}
