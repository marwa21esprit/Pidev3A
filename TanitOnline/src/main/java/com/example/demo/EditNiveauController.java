package com.example.demo;

import Services.ApprenantServices;
import Services.NiveauServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Apprenant;
import models.Niveau;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditNiveauController implements Initializable {
    NiveauServices ns = new NiveauServices();
    @FXML
    private TextField NameTF;

    @FXML
    private TextField certificatTF;

    @FXML
    private TextField competencesTF;

    @FXML
    private TextField dureeTF;

    @FXML
    private AnchorPane img_learner;

    @FXML
    private Button modifier_niveau_btn;

    @FXML
    private TextField nbformationTF;

    @FXML
    private TextField prerequisTF;

    @FXML
    private TextField suiviTF;

    @FXML
    private Button supprimer_niveau_btn;
    private ValidationSupport validationSupport;


    @FXML
    void Modifier(ActionEvent event) {
        try {
            Niveau existingNiveau = ns.getByName(NameTF.getText());
            int nbFormation = Integer.parseInt(nbformationTF.getText());

            if (existingNiveau != null) {
                // Remplir les champs du formulaire avec les données actuelles du Niveau
                NameTF.setText(existingNiveau.getName());
                prerequisTF.setText(existingNiveau.getPrerequis());
                dureeTF.setText(existingNiveau.getDuree());
                nbformationTF.setText(String.valueOf(existingNiveau.getNbformation()));
                suiviTF.setText(existingNiveau.getSuivi());
                certificatTF.setText(existingNiveau.getCertificat());
                competencesTF.setText(existingNiveau.getCompetences());

                // Le reste du code pour la validation et la mise à jour reste inchangé...
                ValidationResult validationResult = validationSupport.getValidationResult();
                if (validationResult.getErrors().isEmpty()) {
                    existingNiveau.setName(NameTF.getText());
                    existingNiveau.setPrerequis(prerequisTF.getText());
                    existingNiveau.setDuree(dureeTF.getText());
                    existingNiveau.setNbformation(nbFormation);
                    existingNiveau.setSuivi(suiviTF.getText());
                    existingNiveau.setCertificat(certificatTF.getText());
                    existingNiveau.setCompetences(competencesTF.getText());

                    // Perform the update in the database
                    ns.update(existingNiveau);

                    // Show a success alert for update
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Succès");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Niveau modifié avec succès.");
                    successAlert.showAndWait();
                } else {
                    // Display an alert with validation error messages
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Validation Error");
                    alert.setHeaderText("Le formulaire contient des erreurs");
                    alert.setContentText(validationResult.getMessages().toString());
                    alert.showAndWait();
                }
            } else {
                System.out.println("Niveau not found based on the given criteria.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Supprimer(ActionEvent event) {
        try {
            String nameToDelete = NameTF.getText();

            if (!nameToDelete.isEmpty()) {
                ns.delete(nameToDelete);

                // Show a success alert for deletion
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Succès");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Niveau supprimé avec succès.");
                successAlert.showAndWait();
            } else {
                // Show an error alert if the name field is empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le champ Nom est vide. Impossible de supprimer.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validationSupport = new ValidationSupport();

        // Add validators for mandatory fields
        validationSupport.registerValidator(NameTF, Validator.createEmptyValidator("Name is required"));
        validationSupport.registerValidator(prerequisTF, Validator.createEmptyValidator("Prérequis is required"));
        validationSupport.registerValidator(dureeTF, Validator.createEmptyValidator("Duree est obligatoire"));
        validationSupport.registerValidator(nbformationTF, Validator.createEmptyValidator("Nombres de formations est obligatoire"));
        validationSupport.registerValidator(suiviTF, Validator.createEmptyValidator("Suivi est obligatoire"));
        validationSupport.registerValidator(competencesTF, Validator.createEmptyValidator("Compétences est obligatoire"));
        validationSupport.registerValidator(certificatTF, Validator.createEmptyValidator("Certificat est obligatoire"));

    }


}
