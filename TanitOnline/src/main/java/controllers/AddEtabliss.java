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
import models.Etablissement;
import services.EtablissementServices;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AddEtabliss {

    private final EtablissementServices es = new EtablissementServices();

    @FXML
    private TextField Adresse_Etablissement;

    @FXML
    private DatePicker Date_Fondation;

    @FXML
    private TextField Directeur_Etablissement;

    @FXML
    private TextField ID_Certificat;

    @FXML
    private TextField ID_Etablissement;

    @FXML
    private TextField Nom_Etablissement;

    @FXML
    private TextField Tel_Etablissement;

    @FXML
    private TextField Type_Etablissement1;

    @FXML
    void affi(ActionEvent event) {
        try {
            // Charger le fichier FXML de la vue GetEtabliss.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetEtabliss.fxml"));
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
            java.sql.Date dateFondation = java.sql.Date.valueOf(Date_Fondation.getValue());



            if (!isValidString(Nom_Etablissement.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un nom valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidString(Adresse_Etablissement.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer une adresse valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidString(Type_Etablissement1.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un type valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidPhoneNumber(Tel_Etablissement.getText())) {
                return ;
            }

            if (!isValidString(Directeur_Etablissement.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un nom de directeur valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidID(ID_Certificat.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un ID valide pour le certificat.");
                return;
            }

            // Vérifier si la date de fondation est ultérieure à aujourd'hui
            if (!isValidDate(dateFondation)) {
                afficherAlerteErreur("Erreur de saisie", "La date de fondation ne peut pas être ultérieure à aujourd'hui.");
                return;
            }

            // Ajouter l'établissement
            es.addSchool(new Etablissement(
                    Nom_Etablissement.getText(),
                    Adresse_Etablissement.getText(),
                    Type_Etablissement1.getText(),
                    Integer.parseInt(Tel_Etablissement.getText()),
                    Directeur_Etablissement.getText(),
                    dateFondation,
                    Integer.parseInt(ID_Certificat.getText())
            ));

            afficherAlerteInformation("Ajout réussi", "L'établissement a été ajouté avec succès.");
        } catch (NumberFormatException e) {
            // Gérer les erreurs de format du numéro d'ID
            afficherAlerteErreur("Erreur de format", "Veuillez entrer des valeurs valides pour l'établissement.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            afficherAlerteErreur("Erreur lors de l'ajout", "Une erreur est survenue lors de l'ajout de l'établissement. Veuillez réessayer.");
            e.printStackTrace();
        }
    }

    // Méthode pour vérifier si une chaîne ne contient que des lettres et des espaces
    private boolean isValidString(String str) {
        return str.matches("[a-zA-Z\\s]+");
    }

    // Méthode pour vérifier si une chaîne est un numéro de téléphone valide
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Vérifier si la chaîne ne contient que des chiffres et a une longueur de 8 caractères
        if (!phoneNumber.matches("\\d{8}")) {
            afficherAlerteErreur("Erreur de saisie", "Veuillez saisir un numéro de téléphone valide composé de 8 chiffres.");
            return false;
        }
        return true;
    }

    // Méthode pour vérifier si une chaîne est un ID valide
    private boolean isValidID(String id) {
        return id.matches("\\d+");
    }

    // Méthode pour vérifier si la date est ultérieure à aujourd'hui
    private boolean isValidDate(Date date) {
        return date.toLocalDate().
                isBefore(java.time.LocalDate.now());
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
