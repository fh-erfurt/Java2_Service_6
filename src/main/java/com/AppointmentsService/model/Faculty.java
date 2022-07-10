package com.AppointmentsService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name="FACULTY")
public class Faculty {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int ID;
    private String Title;

    Faculty(String _Title){
        this.ID = count.incrementAndGet();
        this.Title = _Title;
    }

    //---------------------------------------------------------

    void setTitle(String _Title){
        this.Title = _Title;
    }

    //---------------------------------------------------------

    String getName(){  return this.Title; }
    int getID(){ return this.ID; }
}
