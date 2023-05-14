package com.milestone1.app.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
//@Document()
public class Address {
    private String location;

}
