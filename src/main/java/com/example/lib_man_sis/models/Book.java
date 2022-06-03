package com.example.lib_man_sis.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {
    //ids
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //book information
    private String title;
    private String author;
    private String publisher;
    private int edition;
    private String category;
    private double price;
    private int copies;
}
