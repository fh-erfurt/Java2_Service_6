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
    private int CourseID;
    @Column(name="FACULTY_ID")
    private int FacultyID;
    @Column(name="LOCATION_ID")
    private int LocationID;
    @Column(name="PERSON_ID")
    private int PersonID;

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

    public void setTitle(String _Title){
        if (_Title != null){ this.Title = _Title; }
    }
    public void setNewStart(LocalDateTime _startDateTime){
        this.startDateTime = _startDateTime;
    }
    public void setNewEnd(LocalDateTime _endDateTime){
        this.endDateTime = _endDateTime;
    }
    public void setPublicMode(boolean _Mode){ this.isPublic = _Mode; }
    public void setSemester(int _Semester){ this.Semester = _Semester; }

    public void setCourseID(int _CourseID) { this.CourseID = _CourseID; }
    public void setFacultyID(int _FacultyID) { this.FacultyID = _FacultyID; }
    public void setLocationID(int _LocID) { this.LocationID = _LocID; }
    public void setPersonID(int _PersonID) { this.PersonID = _PersonID; }

    //---------------------------------------------------------

    public int getID(){ return this.ID; }
    public  String getTitle(){ return this.Title; }
    public boolean getIfPublic(){ return this.isPublic; }
    public int getSemester(){ return this.Semester; }
    public LocalDateTime getStartDateTime(){ return this.startDateTime; }
    public LocalDateTime getEndDateTime(){ return this.endDateTime; }

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