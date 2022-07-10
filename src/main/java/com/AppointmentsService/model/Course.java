package com.AppointmentsService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name="COURSE")
public class Course {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int ID;
    private String Title;
    private int forSemester;

    Course(String _Title, int _forSemester){
        this.ID = count.incrementAndGet();
        this.Title = _Title;
        this.forSemester = _forSemester;
    }

    //---------------------------------------------------------

    void setTitle(String _Title){
        if (_Title!= null){ this.Title = _Title; }
    }
    void setForSemester(int _forSemester){
        this.forSemester = _forSemester;
    }

    //---------------------------------------------------------

    int getID(){ return this.ID; }
    String getTitle(){ return this.Title; }
    int getForWhatSemester(){ return this.forSemester; }
}
