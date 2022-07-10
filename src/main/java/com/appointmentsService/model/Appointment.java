package com.appointmentsService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name="APPOINTMENTS")
public class Appointment {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(name = "PERSON_SEQ", allocationSize = 1)
    private int ID;

    @Column(name="TITLE")
    private String Title;
    @Column(name="ISPUBLIC")
    private boolean isPublic;
    @Column(name="SEMESTER")
    private int Semester;
    @Column(name="STARTDATETIME")
    private LocalDateTime startDateTime;
    @Column(name="ENDDATETIME")
    private LocalDateTime endDateTime;
    @Column(name="COURSE_ID")
    private com.appointmentsService.model.Course Course;
    @Column(name="FACULTY_ID")
    private com.appointmentsService.model.Faculty Faculty;
    @Column(name="LOCATION_ID")
    private com.appointmentsService.model.Location Location;
    @Column(name="PERSON_ID")
    private com.appointmentsService.model.Person Person;

    public Appointment(){}

    Appointment(String _Title, boolean _isPublic, int _Semester, LocalDateTime _Begin, LocalDateTime _End){
        this.ID = count.incrementAndGet();
        this.Title = _Title;
        this.isPublic = _isPublic;
        this.Semester = _Semester;
        this.startDateTime = _Begin;
        this.endDateTime = _End;
    }

    //---------------------------------------------------------

    void setTitle(String _Title){
        if (_Title != null){ this.Title = _Title; }
    }
    void setNewStart(LocalDateTime _startDateTime){
        this.startDateTime = _startDateTime;
    }
    void setNewEnd(LocalDateTime _endDateTime){
        this.endDateTime = _endDateTime;
    }

    //---------------------------------------------------------

    int getID(){ return this.ID; }
    String getTitle(){ return this.Title; }
    boolean getIfPublic(){ return this.isPublic; }
    int getSemester(){ return this.Semester; }
    LocalDateTime getStartDateTime(){ return this.startDateTime; }
    LocalDateTime getEndDateTime(){ return this.endDateTime; }

    //---------------------------------------------------------

    @Override
    public String toString(){
        return "Appointment{" +
                "ID = " + ID +
                "Title = " + Title +
                "isPublic = " + isPublic +
                "Semester = " + Semester +
                "Start = " + startDateTime +
                "End = " + endDateTime + "}";
    }
}
