package com.quotecollector.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes") 
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000) 
    private String text;


    public Quote() {}

    public Quote(String text) {
        this.text = text;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}