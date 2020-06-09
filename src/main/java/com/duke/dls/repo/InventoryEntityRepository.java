package com.duke.dls.repo;

import com.duke.dls.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryEntityRepository extends JpaRepository<Inventory, Long> {

    @Query("select u from Inventory u where u.isActive = 'Y'")
    List<Inventory> findAllActive();

    @Query("select u from Inventory u where u.isActive = 'Y' and u.status = 'SPARE'")
    List<Inventory> findAllSpare();
}
