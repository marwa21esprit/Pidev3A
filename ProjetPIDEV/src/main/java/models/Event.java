package models;

import java.sql.Date;
import java.util.Objects;

public class Event {

    private int idEvent;
    private int idPartner;
    private int idEstab;
    private String nameEvent;

    private Date dateEvent;
    private int nbrMax;
    private String description;

    private String image;

    public Event() {
    }

    public Event(int idEvent,int idPartner, int idEstab, String nameEvent, Date dateEvent, int nbrMax, String description , String image) {
        this.idEvent = idEvent;
        this.idPartner = idPartner;
        this.idEstab = idEstab;
        this.nameEvent = nameEvent;
        this.dateEvent = dateEvent;
        this.nbrMax = nbrMax;
        this.description = description;
        this.image = image;
    }

    public Event(int idPartner, int idEstab,String nameEvent, Date dateEvent, int nbrMax, String description , String image) {
        this.idPartner = idPartner;
        this.idEstab = idEstab;
        this.nameEvent = nameEvent;
        this.dateEvent = dateEvent;
        this.nbrMax = nbrMax;
        this.description = description;
        this.image = image;
    }


    public Event(int idEstab, String nameEvent, Date dateEvent, int nbrMax, String description, String image) {
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

    public int getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(int idPartner) {
        this.idPartner = idPartner;
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
                ", idPartner=" + idPartner +
                ", idEstab=" + idEstab +
                ", nameEvent='" + nameEvent + '\'' +
                ", dateEvent=" + dateEvent +
                ", nbrMax=" + nbrMax +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return idEvent == event.idEvent &&
                idPartner == event.idPartner &&
                idEstab == event.idEstab &&
                nbrMax == event.nbrMax &&
                Objects.equals(nameEvent, event.nameEvent) &&
                Objects.equals(dateEvent, event.dateEvent) &&
                Objects.equals(description, event.description) &&
                Objects.equals(image, event.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvent, idPartner, idEstab, nameEvent, dateEvent, nbrMax, description, image);
    }
}
