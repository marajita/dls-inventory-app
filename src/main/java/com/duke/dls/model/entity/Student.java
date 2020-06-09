package com.duke.dls.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student extends DomainBase{

    @Id
    @Column(name = "studentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "netId")
    private String netId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dukeEmail")
    private String dukeEmail;

    @Column(name = "altEmail")
    private String altEmail;

    @Column(name = "programYear")
    private String programYear;

    @Column(name = "preferredName")
    private String preferredName;

    @Column(name = "isActive", length = 1)
    private String isActive;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "inventoryId")
    private Inventory inventory;


}
