package com.example.springsecurityapplication.models;


import com.example.springsecurityapplication.enumm.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_person")
public class OrderPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Person person;
    private float price;
    private Status status;
    private LocalDateTime dateTime;
    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

    public OrderPerson(String number, Person person, float price, Status status) {
        this.number = number;
        this.person = person;
        this.price = price;
        this.status = status;
    }

    public OrderPerson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
