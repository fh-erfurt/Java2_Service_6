package com.appointmentsService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name="PERSON")
public class Person {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(name = "PERSON_SEQ", allocationSize = 1)
    private int ID;
    @Column(name="NAME")
    private String name;

    public Person() {
    }

    Person(String _Name){
        this.ID = count.incrementAndGet();
        this.name = _Name;
    }

    //---------------------------------------------------------

    public void setName(String _Name){
        this.name = _Name;
    }

    //---------------------------------------------------------

    public String getName(){  return this.name; }
    public int getID(){ return this.ID; }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", Name='" + name + '\'' +
                '}';
    }
}