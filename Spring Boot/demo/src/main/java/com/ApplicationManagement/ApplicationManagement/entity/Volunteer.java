package com.ApplicationManagement.ApplicationManagement.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "volunteers")
@Data
public class Volunteer {

    @Id
    private String id;
    private String name;
    private String email;
    private String skills;
    private String location;
}
