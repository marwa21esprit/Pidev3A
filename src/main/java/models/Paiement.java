package models;


public class Paiement {
    private int id;
    private int id_res;
    private float montant_t;


    private String heure_P;



    public Paiement() {
    }

    public Paiement(float montant_t,  String heure_P) {
        this.montant_t = montant_t;

        this.heure_P = heure_P;
    }

    public Paiement(int id, int id_res, float montant_t) {
        this.id = id;
        this.id_res = id_res;
        this.montant_t = montant_t;


    }

    public Paiement(float montant_t) {
        this.montant_t = montant_t;


    }

    public Paiement(int id, int id_res, float montantT, String heureP) {
        this.id=id;
        this.id_res=id_res;
        this.montant_t=montantT;
        this.heure_P=heureP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public float getMontant_t() {
        return montant_t;
    }

    public void setMontant_t(float montant_t) {
        this.montant_t = montant_t;
    }



    public String getHeure_P() {
        return heure_P;
    }

    public void setHeure_P(String heure_P) {
        this.heure_P = heure_P;
    }
}
