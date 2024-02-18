package tn.esprit.pidev;
import tn.esprit.pidev.entities.Etablissement;
import tn.esprit.pidev.services.EtablissementServices;
import tn.esprit.pidev.entities.Certificat;
import tn.esprit.pidev.services.CertficatServices;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {
    public static void main(String[] args) throws ParseException {

        // Supposons que la date est stockée sous forme de String au format "yyyy-MM-dd"
        String dateStr = "2000-12-12"; // Par exemple

// Conversion de la date String en un objet java.sql.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateUtil = sdf.parse(dateStr);
        java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());

// Créez une instance de Date pour la date d'obtention du certificat
        Date dateObtentionCertificat = Date.valueOf("2023-11-10");

// Créez une instance de Certificat
        Certificat c1 = new Certificat(1, "Nom du certificat", "Domaine du certificat", "Niveau du certificat", dateObtentionCertificat, 1);
        Certificat c2 = new Certificat(1, "Nom du certificat2", "Domaine du certificat", "Niveau du certificat", dateObtentionCertificat, 2);
        Certificat c3 = new Certificat(1, "Nom du certificat3", "Domaine du certificat", "Niveau du certificat", dateObtentionCertificat, 1);
        Certificat c4 = new Certificat(15, "CCNA", "Réseau", "A+", dateObtentionCertificat, 3);

//Création de l'objet Etablissement avec la date correcte
     /*   Etablissement e1 = new Etablissement(1, "esprit", "Tunis", "universite", 5596845, "belakhdher", dateSql, "test");
        Etablissement e2 = new Etablissement(2, "esprit", "Tunis", "universite", 5596845, "belakhdher", dateSql, "test");*/
        Etablissement e3 = new Etablissement(15, "sesame", "ariana", "universite", 5596845, "idk", dateSql, 2);



        EtablissementServices es = new EtablissementServices();
        CertficatServices cs = new CertficatServices();


       /* try {
            es.addSchool(e1);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            es.addSchool(e2);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/
       /* try {
            es.addSchool(e3);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/

      /* try {
            cs.addCertificate(c4);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/
       /* try {
            cs.addCertificate(c2);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            cs.addCertificate(c3);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/
      /*  try {
            es.updateSchool( new Etablissement(1, "NouveauNom", "NouvelleAdresse",
                    "NouveauType", 123456789, "NouveauDirecteur",
                    Date.valueOf("2023-01-01"), "NouveauxCertificats"));

            // Appelez la méthode update avec le nouvel objet Etablissement

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

       /* try {
                    // Créer une instance de Certificat avec les nouvelles valeurs
            Certificat certificat = new Certificat(12, "NouveauNomCertificat", "NouveauDomaine", "NouveauNiveau", dateObtentionCertificat, 1);

            // Appeler la méthode updateCertificate avec le nouvel objet Certificat
            cs.updateCertificate(certificat);

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

       /* try {
            System.out.println(es.getAll());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/

       /* try {
            System.out.println(cs.getAll());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/

        /*try {
            System.out.println(cs.getById(c4.getID_Certificat()));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/
       /* try {
            System.out.println(es.getById(e3.getID_Etablissement()));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/

       /* try {
            es.deleteSchool(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

       /* try {
            cs.deleteCertificate(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }}