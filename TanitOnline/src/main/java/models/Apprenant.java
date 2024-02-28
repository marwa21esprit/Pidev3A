package models;

public class Apprenant {
    private int id;
    private String name;
    private String email;
    private String password;
    private String statutNiveau;
    private String formationEtudies;
    private String niveauName;

    private int idNiveau;

    public Apprenant(){}

    public Apprenant(int id, String name, String email, String password, String statutNiveau, String formationEtudies, String niveauName, int idNiveau) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.statutNiveau = statutNiveau;
        this.formationEtudies = formationEtudies;
        this.niveauName = niveauName;
        this.idNiveau = idNiveau;
    }
    public Apprenant( String name, String email, String password, String statutNiveau, String formationEtudies, String niveauName, int idNiveau) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.statutNiveau = statutNiveau;
        this.formationEtudies = formationEtudies;
        this.niveauName = niveauName;
        this.idNiveau = idNiveau;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatutNiveau() {
        return statutNiveau;
    }

    public void setStatutNiveau(String statutNiveau) {
        this.statutNiveau = statutNiveau;
    }

    public String getFormationEtudies() {
        return formationEtudies;
    }

    public void setFormationEtudies(String formationEtudies) {
        this.formationEtudies = formationEtudies;
    }

    public String getNiveauName() {
        return niveauName;
    }

    public void setNiveauName(String niveauName) {
        this.niveauName = niveauName;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    @Override
    public String toString() {
        return "Apprenant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", statutNiveau='" + statutNiveau + '\'' +
                ", formationEtudies='" + formationEtudies + '\'' +
                ", niveauName='" + niveauName + '\'' +
                ", idNiveau=" + idNiveau +
                '}';
    }
}
