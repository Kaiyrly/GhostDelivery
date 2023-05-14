package com.milestone1.app.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User1 {
    @Id
    private String id1;
    private String firstName;
    private String lastName;
    private String email;
    private String phone_number;
    private String password;
    private Address address;
    private String delivery_preference;
    private int raiting1;

}
