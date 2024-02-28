package entities;
import java.sql.Date;
import java.time.LocalDate;

public class Formation {

    private int id_formation;
    private int id_tuteur;

    private int id_niveau;

    private String categorie;

    private String titre;

    private String description;

    private Date date_d;

    private Date date_f;

    private float prix;

    private String lien;

    public Formation()
    {

    }

    public Formation(int id_formation, int id_tuteur, int id_niveau, String categorie, String titre, String description, Date date_d, Date date_f, float prix, String lien) {
        this.id_tuteur = id_tuteur;
        this.id_niveau = id_niveau;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.date_d = date_d;
        this.date_f = date_f;
        this.prix = prix;
        this.lien = lien;
    }

    public Formation(int id_tuteur, int id_niveau, String categorie, String titre, String description, Date date_d, Date date_f, float prix, String lien) {
    }

    public Formation(int idNiveau, String categorie, String titre, String description, Date dateDebut, Date dateFin, float prix, String lien, int idTuteur) {
        this.id_niveau= idNiveau;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.date_d = dateDebut;
        this.date_f = dateFin;
        this.prix = prix;
        this.lien = lien;
        this.id_tuteur = idTuteur;
    }

    public Formation(int i, String selectedCategorie1, String text, String text1, LocalDate selectedStartDate, LocalDate selectedEndDate, float v, String text2) {
    }


    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public int getId_tuteur() {
        return id_tuteur;
    }

    public void setId_tuteur(int id_tuteur) {
        this.id_tuteur = id_tuteur;
    }

    public int getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(int id_niveau) {
        this.id_niveau = id_niveau;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_d() {
        return date_d;
    }

    public void setDate_d(Date date_d) {
        this.date_d = date_d;
    }

    public Date getDate_f() {
        return date_f;
    }

    public void setDate_f(Date date_f) {
        this.date_f = date_f;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id_formation=" + id_formation +
                ", id_tuteur=" + id_tuteur +
                ", id_niveau=" + id_niveau +
                ", categorie='" + categorie + '\'' +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", date_d=" + date_d +
                ", date_f=" + date_f +
                ", prix=" + prix +
                ", lien='" + lien + '\'' +
                '}';
    }


}
