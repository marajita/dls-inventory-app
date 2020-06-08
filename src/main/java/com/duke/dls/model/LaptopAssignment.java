package com.duke.dls.model;

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
public class LaptopAssignment extends DomainBase{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "STUDENT_ID")
//    private Student student;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "LAPTOP_ID")
//    private Laptop laptop;
//
//    @Column(name = "COMMENTS")
//    private String comments;



}
