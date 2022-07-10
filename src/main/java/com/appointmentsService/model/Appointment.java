package com.appointmentsService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name="APPOINTMENTS")
public class Appointment {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int ID;
    private String Title;
    private boolean isPublic;
    private int Semester;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private com.appointmentsService.model.Course Course;
    private com.appointmentsService.model.Faculty Faculty;
    private com.appointmentsService.model.Location Location;
    private Person Organizer;


    Appointment(String _Title, boolean _isPublic, int _Semester, LocalDateTime _Begin, LocalDateTime _End){
        this.ID = count.incrementAndGet();
        this.Title = _Title;
        this.isPublic = _isPublic;
        this.Semester = _Semester;
        this.startDateTime = _Begin;
        this.endDateTime = _End;
    }
    /*  Notes:
        - class objects should be added seperately and/or null checked with long constructor paras
     */

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
    /*  Notes:
        - scan for existent Course/Faculty/Person/Loc and insert ref or Construct new one
        - need scanning algorithm and perhaps datastore
        - add/set methods for replacement/construction > where to decide which to use?
        - class attributes automatically referenced by ID's or to do manually?
     */

    //---------------------------------------------------------

    int getID(){ return this.ID; }
    String getTitle(){ return this.Title; }
    boolean getIfPublic(){ return this.isPublic; }
    int getSemester(){ return this.Semester; }
    LocalDateTime getStartDateTime(){ return this.startDateTime; }
    LocalDateTime getEndDateTime(){ return this.endDateTime; }

    //---------------------------------------------------------
}
