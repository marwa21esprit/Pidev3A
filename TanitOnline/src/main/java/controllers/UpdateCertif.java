package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Certificat;

import services.CertficatServices;

import java.sql.Date;
import java.sql.SQLException;


public class UpdateCertif {

    private final CertficatServices cs = new CertficatServices();

    @FXML
    private DatePicker Date_Obtention_Certificatmodif;

    @FXML
    private TextField Domaine_Certificatmodif;

    @FXML
    private TextField ID_Certificatmodif;

    @FXML
    private TextField ID_Etablissementmodif;

    @FXML
    private TextField Niveaumodif;

    @FXML
    private TextField Nom_Certificatmodif;

    public void setCertificateData(Certificat certificate) {
        ID_Certificatmodif.setText(String.valueOf(certificate.getID_Certificat()));
        ID_Etablissementmodif.setText(String.valueOf(certificate.getID_Etablissement()));
        Nom_Certificatmodif.setText(certificate.getNom_Certificat());
        Domaine_Certificatmodif.setText(certificate.getDomaine_Certificat());
        Niveaumodif.setText(certificate.getNiveau());
        Date_Obtention_Certificatmodif.setValue(certificate.getDate_Obtention_Certificat().toLocalDate());
    }

    @FXML
    void modifier(ActionEvent event) {
        try {
            Date dateObtention = Date.valueOf(Date_Obtention_Certificatmodif.getValue());
            cs.updateCertificate(new Certificat(

                    Nom_Certificatmodif.getText(),
                    Domaine_Certificatmodif.getText(),
                    Niveaumodif.getText(),
                    dateObtention,
                    Integer.parseInt(ID_Etablissementmodif.getText())
            ));

            // Call the service method to update the certificate
            // Afficher une alerte de succès
            afficherAlerteInformation("Mise à jour réussie", "L'établissement a été mis à jour avec succès.");
        } catch (NumberFormatException e) {
            afficherAlerteErreur("Erreur de format", "Veuillez entrer des valeurs valides.");
            e.printStackTrace();
        } catch (SQLException e) {
            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors de la mise à jour de l'établissement. Veuillez réessayer.");
            e.printStackTrace();
        } catch (Exception e) {
            afficherAlerteErreur("Erreur", "Une erreur est survenue lors de la mise à jour de l'établissement. Veuillez réessayer.");
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
