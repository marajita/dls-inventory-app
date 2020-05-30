package com.duke.dls.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentRequest {

    private Long id;

    private String netId;

    private String firstName;

    private String lastName;

    private String preferredName;

    private String dukeEmail;

    private String altEmail;

    private String programYear;

    private String laptopSn;

    private String powerAdapterSn;
}
