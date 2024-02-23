package models;

import java.sql.Date;

public class Event {

    private int idEvent;
    private int idEstab;
    private String nameEvent;

    private Date dateEvent;
    private int nbrMax;
    private String description;

    private String image;

    public Event() {
    }

    public Event(int idEvent, int idEstab, String nameEvent, Date dateEvent, int nbrMax, String description , String image) {
        this.idEvent = idEvent;
        this.idEstab = idEstab;
        this.nameEvent = nameEvent;
        this.dateEvent = dateEvent;
        this.nbrMax = nbrMax;
        this.description = description;
        this.image = image;
    }

    public Event(int idEstab, String nameEvent, Date dateEvent, int nbrMax, String description , String image) {
        this.idEstab = idEstab;
        this.nameEvent = nameEvent;
        this.dateEvent = dateEvent;
        this.nbrMax = nbrMax;
        this.description = description;
        this.image = image;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdEstab() {
        return idEstab;
    }

    public void setIdEstab(int idEstab) {
        this.idEstab = idEstab;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", idEstab=" + idEstab +
                ", nameEvent='" + nameEvent + '\'' +
                ", dateEvent=" + dateEvent +
                ", nbrMax=" + nbrMax +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
