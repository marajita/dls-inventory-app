package com.duke.dls.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventoryHistory extends DomainBase {


    @Id
    @Column(name = "inventoryHistoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryHistoryId;

    @Column(name = "inventoryId")
    private Long inventoryId;

    @Column(name = "status")
    private String status;
    //SPARE IN_USE IN_REPAIR

    @Column(name = "comments")
    private String comments;
}
