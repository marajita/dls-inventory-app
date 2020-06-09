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
public class StudentHistory extends DomainBase {

    @Id
    @Column(name = "studentHistoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentHistoryId;

    @Column(name = "studentId")
    private Long studentId;

    @Column(name = "netId")
    private String netId;

    @Column(name = "comments")
    private String comments;

}
