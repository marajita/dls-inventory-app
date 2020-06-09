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
public class Inventory extends DomainBase {

    @Id
    @Column(name = "inventoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @Column(name = "laptopSn")
    private String laptopSn;

    @Column(name = "powerAdapterSn")
    private String powerAdapterSn;

    @Column(name = "status")
    private String status;
    //SPARE IN_USE IN_REPAIR

    @Column(name = "isCheckedout")
    private String ischeckedout;

    @Column(name = "isActive", length = 1)
    private String isActive;

}
