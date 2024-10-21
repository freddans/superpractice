package com.example.demotestsuperpractice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "responsibilities")
public class Responsibilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer responsibility_id;

    private String responsibility;

    public Responsibilities() {
    }

    public Responsibilities(String responsibility) {
        this.responsibility = responsibility;
    }

    public Integer getResponsibility_id() {
        return responsibility_id;
    }

    public void setResponsibility_id(Integer responsibility_id) {
        this.responsibility_id = responsibility_id;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }
}