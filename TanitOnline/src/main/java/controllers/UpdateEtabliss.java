package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class UpdateEtabliss {

    private final EtablissementServices es = new EtablissementServices();

    @FXML
    private DatePicker Date_Fondationmodif;

    @FXML
    private TextField Adresse_Etablissementmodif;

    @FXML
    private TextField Directeur_Etablissementmodif;

    @FXML
    private TextField ID_Certificatmodif;

    @FXML
    private TextField ID_Etablissementmodif;

    @FXML
    private TextField Nom_Etablissementmodif;

    @FXML
    private TextField Tel_Etablissementmodif;

    @FXML
    private TextField Type_Etablissementmodif;

    @FXML
    private Stage primaryStage;

    @FXML
    void affi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetEtabliss.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec le fichier FXML chargé
            Scene scene = new Scene(root);

            // Obtenir la fenêtre principale
            Stage stage = (Stage) Date_Fondationmodif.getScene().getWindow();

            // Afficher la nouvelle scène dans la fenêtre principale
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setSchoolData(Etablissement school) {
        ID_Etablissementmodif.setText(String.valueOf(school.getID_Etablissement()));
        Nom_Etablissementmodif.setText(school.getNom_Etablissement());
        Adresse_Etablissementmodif.setText(school.getAdresse_Etablissement());
        Type_Etablissementmodif.setText(school.getType_Etablissement());
        Tel_Etablissementmodif.setText(String.valueOf(school.getTel_Etablissement()));
        Directeur_Etablissementmodif.setText(school.getDirecteur_Etablissement());
        Date_Fondationmodif.setValue(school.getDate_Fondation().toLocalDate());
        ID_Certificatmodif.setText(String.valueOf(school.getID_Certificat()));
    }

    @FXML
    void modifier(ActionEvent event) {
        try {
            // Convertir les valeurs nécessaires en types appropriés
            Date dateFondation = Date.valueOf(Date_Fondationmodif.getValue());

            if (!isValidString(Nom_Etablissementmodif.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un nom valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidString(Adresse_Etablissementmodif.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer une adresse valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidString(Type_Etablissementmodif.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un type valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidPhoneNumber(Tel_Etablissementmodif.getText())) {
                return;
            }

            if (!isValidString(Directeur_Etablissementmodif.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un nom de directeur valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidID(ID_Certificatmodif.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un ID valide pour le certificat.");
                return;
            }

            // Vérifier si la date de fondation est ultérieure à aujourd'hui
            if (!isValidDate(dateFondation)) {
                afficherAlerteErreur("Erreur de saisie", "La date de fondation ne peut pas être ultérieure à aujourd'hui.");
                return;
            }

            // Vérifier si les champs contiennent des valeurs valides
            if (Nom_Etablissementmodif.getText().isEmpty()) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un nom pour l'établissement.");
                return;
            }

            // Récupérer l'ID de l'établissement
            int idEtablissement = Integer.parseInt(ID_Etablissementmodif.getText());

            // Créer un nouvel établissement avec les valeurs des champs de modification
            Etablissement updatedSchool = new Etablissement(
                    Nom_Etablissementmodif.getText(),
                    Adresse_Etablissementmodif.getText(),
                    Type_Etablissementmodif.getText(),
                    Integer.parseInt(Tel_Etablissementmodif.getText()),
                    Directeur_Etablissementmodif.getText(),
                    dateFondation,
                    idEtablissement
            );

            // Appeler la méthode updateSchool pour mettre à jour l'établissement
            es.updateSchool(updatedSchool,idEtablissement);

            // Afficher une alerte de succès
            afficherAlerteInformation("Mise à jour réussie", "L'établissement a été mis à jour avec succès.");
        } catch (NumberFormatException e) {
            // Gérer les erreurs de format des nombres
            afficherAlerteErreur("Erreur de format", "Veuillez entrer des valeurs valides.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors de la mise à jour de l'établissement. Veuillez réessayer.");
            e.printStackTrace();
        } catch (Exception e) {
            // Gérer les autres erreurs
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
    }
