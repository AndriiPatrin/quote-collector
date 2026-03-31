package com.quotecollector.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quotes") // Назва таблиці в базі даних
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматичний ID (1, 2, 3...)
    private Long id;

    @Column(length = 2000) // Щоб довгі цитати помістилися (стандартно 255 символів)
    private String text;

    // Порожній конструктор обов'язковий для Hibernate (Spring Data)
    public Quote() {}

    public Quote(String text) {
        this.text = text;
    }

    // Геттери та сеттери (щоб Spring міг читати та записувати дані)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}