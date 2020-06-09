package com.duke.dls.model;

import com.duke.dls.model.entity.Student;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentResponse {

    List<Student> studentList;
}
