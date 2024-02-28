package com.example.demo;

import Services.ApprenantServices;
import Services.NiveauServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.Apprenant;
import models.Niveau;
import org.controlsfx.validation.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import org.controlsfx.validation.ValidationResult;

public class AddNiveauController implements Initializable {

    NiveauServices ns = new NiveauServices();

    @FXML
    private TextField NameTF;

    @FXML
    private Button ajouter_niveau_btn;

    @FXML
    private TextField certificatTF;

    @FXML
    private TextField competencesTF;

    @FXML
    private TextField dureeTF;

    @FXML
    private AnchorPane img_learner;

    @FXML
    private TextField nbformationTF;

    @FXML
    private TextField prerequisTF;

    @FXML
    private TextField suiviTF;

    private ValidationSupport validationSupport;

    @FXML
    void Ajouter(ActionEvent event) {
        ValidationResult validationResult = validationSupport.getValidationResult();
        if (validationResult.getErrors().isEmpty()) {
            try {
                int nbFormation = Integer.parseInt(nbformationTF.getText());
                ns.add(new Niveau(NameTF.getText(), prerequisTF.getText(), dureeTF.getText(), nbFormation, competencesTF.getText(), suiviTF.getText(), certificatTF.getText()));

                // Afficher une alerte de succès
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Niveau ajouté avec succès.");
                successAlert.showAndWait();
            } catch (NumberFormatException e) {
                System.out.println("Le nombre de formations doit être un entier valide.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Affiche une alerte avec les messages d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de validation");
            alert.setHeaderText("Le formulaire contient des erreurs");
            alert.setContentText(validationResult.getMessages().toString());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        validationSupport = new ValidationSupport();

        // Validator pour s'assurer que nbformationTF contient un entier valide
        validationSupport.registerValidator(nbformationTF, Validator.createRegexValidator(
                "Le nombre de formations doit être un entier valide.",
                "\\d+",
                Severity.ERROR
        ));

        // Validator pour s'assurer que tous les champs obligatoires sont remplis
        validationSupport.registerValidator(NameTF, Validator.createEmptyValidator("Veuillez saisir un nom."));
        validationSupport.registerValidator(prerequisTF, Validator.createEmptyValidator("Veuillez saisir des prérequis."));
        validationSupport.registerValidator(dureeTF, Validator.createEmptyValidator("Veuillez saisir une durée."));
        validationSupport.registerValidator(competencesTF, Validator.createEmptyValidator("Veuillez saisir des compétences."));
        validationSupport.registerValidator(suiviTF, Validator.createEmptyValidator("Veuillez saisir des informations de suivi."));
        validationSupport.registerValidator(certificatTF, Validator.createEmptyValidator("Veuillez saisir des informations de certificat."));

    }
}

