package com.duke.dls.repo;

import com.duke.dls.model.entity.Inventory;
import com.duke.dls.model.entity.InventoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryHistoryEntityRepository extends JpaRepository<InventoryHistory, Long> {

}
