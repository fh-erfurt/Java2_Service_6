package com.AppointmentsService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name="LOCATION")
public class Location {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int ID;
    private String Building;
    private String Room;

    Location(String _Building, String _Room){
        this.ID = count.incrementAndGet();
        if (_Building != null){ this.Building = _Building; }
        if (_Room != null){ this.Room = _Room; }
    }

    //---------------------------------------------------------

    void setRoom(String _Room){
        if (_Room != null){ this.Room = _Room; }
    }
    void setBuilding(String _Building){
        if (_Building != null){ this.Building = _Building; }
    }

    //---------------------------------------------------------

    int getID(){ return this.ID; }
    String getBuilding(){ return this.Building; }
    String getRoom(){ return this.Room; }
}
