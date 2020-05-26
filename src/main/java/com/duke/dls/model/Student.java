package com.duke.dls.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "netId")
    private String netId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "altEmail")
    private String altEmail;

    @Column(name = "programYear")
    private String programYear;

    @Column(name = "laptopSn")
    private String laptopSn;

    @Column(name = "powerAdapterSn")
    private String powerAdapterSn;

}
