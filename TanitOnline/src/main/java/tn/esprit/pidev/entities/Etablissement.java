package tn.esprit.pidev.entities;

import java.util.Date;

public class Etablissement {

    int ID_Etablissement;
    String Nom_Etablissement;
    String Adresse_Etablissement;
    String Type_Etablissement;
    int Tel_Etablissement;
    String Directeur_Etablissement;
    Date Date_Fondation;
    int ID_Certificat;

    public Etablissement(int ID_Etablissement, String nom_Etablissement, String adresse_Etablissement, String type_Etablissement, int tel_Etablissement, String directeur_Etablissement, Date date_Fondation, int ID_Certificat) {
        this.ID_Etablissement = ID_Etablissement;
        this.Nom_Etablissement = nom_Etablissement;
        this.Adresse_Etablissement = adresse_Etablissement;
        this.Type_Etablissement = type_Etablissement;
        this.Tel_Etablissement = tel_Etablissement;
        this.Directeur_Etablissement = directeur_Etablissement;
        this.Date_Fondation = date_Fondation;
        this.ID_Certificat = ID_Certificat;
    }

    public Etablissement() {
    }

    public int getID_Etablissement() {
        return ID_Etablissement;
    }

    public void setID_Etablissement(int ID_Etablissement) {
        this.ID_Etablissement = ID_Etablissement;
    }

    public String getNom_Etablissement() {
        return Nom_Etablissement;
    }

    public void setNom_Etablissement(String nom_Etablissement) {
        Nom_Etablissement = nom_Etablissement;
    }

    public String getAdresse_Etablissement() {
        return Adresse_Etablissement;
    }

    public void setAdresse_Etablissement(String adresse_Etablissement) {
        Adresse_Etablissement = adresse_Etablissement;
    }

    public String getType_Etablissement() {
        return Type_Etablissement;
    }

    public void setType_Etablissement(String type_Etablissement) {
        Type_Etablissement = type_Etablissement;
    }

    public int getTel_Etablissement() {
        return Tel_Etablissement;
    }

    public void setTel_Etablissement(int tel_Etablissement) {
        Tel_Etablissement = tel_Etablissement;
    }

    public String getDirecteur_Etablissement() {
        return Directeur_Etablissement;
    }

    public void setDirecteur_Etablissement(String directeur_Etablissement) {
        Directeur_Etablissement = directeur_Etablissement;
    }


    public java.sql.Date getDate_Fondation() {
        // Conversion de java.util.Date en java.sql.Date
        if (Date_Fondation != null) {
            return new java.sql.Date(Date_Fondation.getTime());
        } else {
            return null;
        }
    }

    public void setDate_Fondation(Date date_Fondation) {
        Date_Fondation = date_Fondation;
    }

    public int getID_Certificat() {
        return ID_Certificat;
    }

    public void setID_Certificat(int ID_Certificat) {
        ID_Certificat = ID_Certificat;
    }


    @Override
    public String toString() {
        return "Etablissement{" +
                "ID_Etablissement=" + ID_Etablissement +
                ", Nom_Etablissement='" + Nom_Etablissement + '\'' +
                ", Adresse_Etablissement='" + Adresse_Etablissement + '\'' +
                ", Type_Etablissement='" + Type_Etablissement + '\'' +
                ", Tel_Etablissement=" + Tel_Etablissement +
                ", Directeur_Etablissement='" + Directeur_Etablissement + '\'' +
                ", Date_Fondation=" + Date_Fondation +
                ", Certificats_Delivres='" + ID_Certificat + '\'' +
                '}';
    }


}
