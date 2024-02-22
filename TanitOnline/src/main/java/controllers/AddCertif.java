package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Certificat;
import services.CertficatServices;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AddCertif {

    private final CertficatServices cs = new CertficatServices();

    @FXML
    private DatePicker Date_Obtention_Certificat;

    @FXML
    private TextField Domaine_Certificat;

    @FXML
    private TextField ID_Etablissement;

    @FXML
    private TextField Niveau;

    @FXML
    private TextField Nom_Certificat;

    @FXML
    void affi(ActionEvent event) {
        try {
            // Charger le fichier FXML de la vue GetEtabliss.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetCertif.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec le contenu chargé à partir du fichier FXML
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void ajouter(ActionEvent event) {
        try {
            // Convertir les valeurs nécessaires en types appropriés
            java.sql.Date dateObtention = java.sql.Date.valueOf(Date_Obtention_Certificat.getValue());

            // Vérifier si les champs contiennent des valeurs valides
            if (!isValidString(Nom_Certificat.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un nom valide pour le certificat (sans chiffres).");
                return;
            }

            if (!isValidString(Domaine_Certificat.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un domaine valide pour le certificat (sans chiffres).");
                return;
            }

            if (!isValidString(Niveau.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un niveau valide pour le certificat (sans chiffres).");
                return;
            }

            if (!isValidID(ID_Etablissement.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un ID valide pour l'établissement.");
                return;
            }

            if (!isValidDate(dateObtention)) {
                afficherAlerteErreur("Erreur de saisie", "La date d'obtention du certificat ne peut pas être ultérieure à la date actuelle.");
                return;
            }

            // Ajouter le certificat
            cs.addCertificate(new Certificat(
                    Nom_Certificat.getText(),
                    Domaine_Certificat.getText(),
                    Niveau.getText(),
                    dateObtention,
                    Integer.parseInt(ID_Etablissement.getText())
            ));
            afficherAlerteInformation("Ajout réussi", "Le certificat a été ajouté avec succès.");
        } catch (NumberFormatException e) {
            // Gérer les erreurs de format du numéro d'ID
            afficherAlerteErreur("Erreur de format", "Veuillez entrer des valeurs valides pour le certificat.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            afficherAlerteErreur("Erreur lors de l'ajout", "Une erreur est survenue lors de l'ajout du certificat. Veuillez réessayer.");
            e.printStackTrace();
        }
    }


    // Méthode pour valider les identifiants (entiers)
    private boolean isValidID(String input) {
        return input.matches("\\d+");
    }

    // Méthode pour valider les chaînes de caractères (ne contient pas de chiffres)
    private boolean isValidString(String input) {
        return !input.matches(".*\\d.*");
    }

    // Méthode pour valider la date (antérieure ou égale à la date actuelle)
    private boolean isValidDate(Date date) {
        return !date.toLocalDate().isAfter(java.time.LocalDate.now());
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
