package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.CertficatServices;


import java.sql.SQLException;

public class DeleteCertif {

    private final CertficatServices es = new CertficatServices();


    @FXML
    private Label ID_Certificat;

    @FXML
    private TextField ID_Certificatsupp;



    @FXML
    void supprimerID(ActionEvent event) {
        String idText = ID_Certificatsupp.getText().trim(); // Supprime les espaces inutiles

        if (idText.isEmpty()) {
            // Si l'ID est vide, afficher un message d'erreur
            afficherAlerteErreur("ID manquant", "Veuillez entrer un ID pour supprimer le certificat.");
            return; // Sortie de la méthode
        }

        try {
            int id = Integer.parseInt(idText);
            es.deleteCertificate(id);
            // Afficher une confirmation après la suppression
            afficherAlerteInformation("Suppression réussie", "le certificat a été supprimé avec succès.");
            // Effacer le champ de texte pour inviter l'utilisateur à effectuer une autre action
            ID_Certificatsupp.clear();
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            afficherAlerteErreur("Erreur lors de la suppression", "Une erreur est survenue lors de la suppression de certificat. Veuillez réessayer.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Gérer les erreurs de format du numéro d'ID
            afficherAlerteErreur("Erreur de format", "Veuillez entrer un ID valide pour supprimer le certificat.");
            e.printStackTrace();
        }
    }

    private void afficherAlerteErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherAlerteInformation(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}
