package models;

import java.util.Date;

public class Partner {
    private int idPartner;
    private String namePartner;
    private String typePartner;
    private String description;
    private String email;
    private int tel;

    public Partner() {
    }

    public Partner(int idPartner, String namePartner, String typePartner, String description, String email, int tel) {
        this.idPartner = idPartner;
        this.namePartner = namePartner;
        this.typePartner = typePartner;
        this.description = description;
        this.email = email;
        this.tel = tel;
    }

    public Partner( String namePartner, String typePartner, String description, String email, int tel) {
        this.namePartner = namePartner;
        this.typePartner = typePartner;
        this.description = description;
        this.email = email;
        this.tel = tel;
    }

    public int getIdPartner() {
        return idPartner;
    }


    public String getNamePartner() {
        return namePartner;
    }

    public String getTypePartner() {
        return typePartner;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public int getTel() {
        return tel;
    }

    public void setIdPartner(int idPartner) {
        this.idPartner = idPartner;
    }


    public void setNamePartner(String namePartner) {
        this.namePartner = namePartner;
    }

    public void setTypePartner(String typePartner) {
        this.typePartner = typePartner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "idPartner=" + idPartner +
                ", namePartner='" + namePartner + '\'' +
                ", typePartner='" + typePartner + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", tel=" + tel +
                '}';
    }
}