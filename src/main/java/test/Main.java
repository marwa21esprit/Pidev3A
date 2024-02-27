/*package test;

import Services.ServiceFormation;
import Services.ServiceTuteur;
import entities.Formation;
import entities.Tuteur;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args)throws IOException {
        // Créer une instance du service Tuteur

       /* ServiceTuteur serviceTuteur = new ServiceTuteur();
        ServiceFormation serviceFormation = new ServiceFormation();*/

        // Creer un objet Tuteur
       // Tuteur tuteur = new Tuteur(1, "Elaa", "Soua", new Date(System.currentTimeMillis()), 53621299, "English Teacher", "elaa.soua@esprit.com","logo.png");


        // Ajouter le tuteur en base de données
    /*   try {
            serviceTuteur.addTuteur(tuteur);
            System.out.println("Tuteur ajouté avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du tuteur : " + e.getMessage());
        }*/

        // Mettre à jour le tuteur
       /* try {
            // Créer un objet Tuteur avec les données mises à jour
            Tuteur updatedTuteur = new Tuteur(1, "Marwa", "Mannai", "2000-12-27", 987654321, "Teacher", "marwa.mannai@esprit.com");
            // Mettre à jour le tuteur en base de données
            serviceTuteur.updateTuteur(updatedTuteur);
            System.out.println("Tuteur mis à jour avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du tuteur : " + e.getMessage());
        }*/

        // Supprimer le tuteur
       /* try {
            // Supprimer le tuteur avec l'ID spécifié
            serviceTuteur.deleteTuteur(1);
            System.out.println("Tuteur supprimé avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du tuteur : " + e.getMessage());
        }

        // Afficher tous les tuteurs
       try {
            // Récupérer tous les tuteurs depuis la base de données
            List<Tuteur> tuteurs = serviceTuteur.getAll();
            // Afficher tous les tuteurs
            for (Tuteur t : tuteurs) {
                System.out.println(t);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des tuteurs : " + e.getMessage());
        }

       /* Calendar calDebut = Calendar.getInstance();
        calDebut.set(2022, Calendar.JANUARY, 1);
        Date date_d = new Date(calDebut.getTimeInMillis());

        Calendar calFin = Calendar.getInstance();
        calFin.set(2023, Calendar.DECEMBER, 31);
        Date date_f = new Date(calFin.getTimeInMillis());*/

        // Creating a sample Formation object
       /* Formation formation = new Formation();
        formation.setId_tuteur(tuteur.getId_tuteur());
        formation.setId_niveau(1);
        formation.setCategorie("Languages");
        formation.setTitre("Art");
        formation.setDescription("Description");
        formation.setDate_d(date_d);
        formation.setDate_f(date_f);
        formation.setPrix(30.5);
        formation.setLien("https://meet.com");*/

//        // Adding the formation using ServiceFormation
     /*  try {
         serviceFormation.addFormation(formation);
           System.out.println("Formation ajoutée avec sucsée.");
      } catch (SQLException e) {
          System.err.println("Error adding formation: " + e.getMessage());
        }*/
// Define an updated Formation object with new values
      /*  Formation updatedFormation = new Formation();
        updatedFormation.setId_formation(1);
        updatedFormation.setId_tuteur(tuteur.getId_tuteur());
        updatedFormation.setId_niveau(2); // Updated niveau ID
        updatedFormation.setCategorie("Science");
        updatedFormation.setTitre("Science de la vie");
        updatedFormation.setDescription("Description");
        updatedFormation.setDate_d(date_d);
        updatedFormation.setDate_f(date_f);
        updatedFormation.setPrix(20.5);
        updatedFormation.setLien("https://meet.com");

    /*    try {
            serviceFormation.updateFormation(updatedFormation);
            System.out.println("Formation modifiée avec sucsée.");
        } catch (SQLException e) {
            System.err.println("Error updating formation: " + e.getMessage());
        }*/


      // int formationIdToDelete = 1;

       /* try {
            // Deleting the Formation
            serviceFormation.deleteFormation(formationIdToDelete);
            System.out.println("Formation suprimée avec sucsée.");
        } catch (SQLException e) {
            System.err.println("Error deleting formation: " + e.getMessage());
        }*/

      /*  try {
            // Get all formations
            List<Formation> formations = serviceFormation.getAll();
            // Display all formations
            for (Formation formation1 : formations) {
                System.out.println(formation);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching formations: " + e.getMessage());
        }

    }
    }*/
