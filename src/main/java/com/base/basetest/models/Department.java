package com.base.basetest.models;

import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Department {

    @Id
    @GeneratedValue
    private Integer id;
    private String departmentName;

}
