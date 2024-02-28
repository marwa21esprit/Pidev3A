package models;

public class Niveau {
    private int id;
    private String name;
    private String prerequis;
    private String duree;
    private int nbformation;
    private String suivi;
    private String certificat;
    private String competences;

    public Niveau(){}

    public Niveau(int id, String name, String prerequis, String duree, int nbformation, String suivi, String certificat, String competences) {
        this.id = id;
        this.name = name;
        this.prerequis = prerequis;
        this.duree = duree;
        this.nbformation = nbformation;
        this.suivi = suivi;
        this.certificat = certificat;
        this.competences = competences;

    }
    public Niveau(String name, String prerequis, String duree, int nbformation, String suivi, String certificat, String competences) {
        this.name = name;
        this.prerequis = prerequis;
        this.duree = duree;
        this.nbformation = nbformation;
        this.suivi = suivi;
        this.certificat = certificat;
        this.competences = competences;

    }
    public Niveau(int id, String prerequis,String name,String duree,String certificat) {
        this.id = id;
        this.name = name;
        this.duree = duree;
        this.prerequis = prerequis;
        this.certificat = certificat;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrerequis() {
        return prerequis;
    }

    public void setPrerequis(String prerequis) {
        this.prerequis = prerequis;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public int getNbformation() {
        return nbformation;
    }

    public void setNbformation(int nbformation) {
        this.nbformation = nbformation;
    }

    public String getSuivi() {
        return suivi;
    }

    public void setSuivi(String suivi) {
        this.suivi = suivi;
    }

    public String getCertificat() {
        return certificat;
    }

    public void setCertificat(String certificat) {
        this.certificat = certificat;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }


}
