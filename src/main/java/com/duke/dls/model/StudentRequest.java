package com.duke.dls.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentRequest {

    private Long studentId;

    private String netId;

    private String firstName;

    private String lastName;

    private String preferredName;

    private String dukeEmail;

    private String altEmail;

    private String programYear;

    private String laptopSn;

    private String powerAdapterSn;

    private Long inventoryId;
}
