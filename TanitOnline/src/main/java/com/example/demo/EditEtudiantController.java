package com.example.demo;

import Services.ApprenantServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.Apprenant;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditEtudiantController implements Initializable {


    ApprenantServices as = new ApprenantServices();

    @FXML
    private TextField FormationTF;

    @FXML
    private TextField NameTF;

    @FXML
    private TextField StatutTF;

    @FXML
    private CheckBox ch_acc_box;

    @FXML
    private TextField emailTF;

    @FXML
    private AnchorPane img_learner;

    @FXML
    private TextField niveauTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private TextField idNiveauTF;


    @FXML
    private Button update_learner_btn;

    @FXML
    private Button delete_learner_btn;

    private ValidationSupport validationSupport;

    @FXML
    void Delete(ActionEvent event) {
        try {
            String emailToDelete = emailTF.getText();

            // Check if the email field is not empty
            if (!emailToDelete.isEmpty()) {
                // Perform the deletion in the database
                as.delete(emailToDelete);

                // Show a success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Successfully deleted.");
                alert.showAndWait();
            } else {
                // Show an error alert if the email field is empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Email field is empty. Cannot delete.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Update(ActionEvent event) {
        try {
            // Retrieve the Apprenant based on the unique criteria (in this case, using email)
            Apprenant existingApprenant = as.getByEmail(emailTF.getText());

            // Check if an Apprenant with the given criteria exists
            if (existingApprenant != null) {
                // Set the current values in the form fields
                NameTF.setText(existingApprenant.getName());
                passwordTF.setText(existingApprenant.getPassword());
                StatutTF.setText(existingApprenant.getStatutNiveau());
                FormationTF.setText(existingApprenant.getFormationEtudies());
                niveauTF.setText(existingApprenant.getNiveauName());

                // Check for mandatory fields
                ValidationResult validationResult = validationSupport.getValidationResult();
                if (validationResult.getErrors().isEmpty()) {
                    // Perform the update in the database
                    existingApprenant.setName(NameTF.getText());
                    existingApprenant.setPassword(passwordTF.getText());
                    existingApprenant.setStatutNiveau(StatutTF.getText());
                    existingApprenant.setFormationEtudies(FormationTF.getText());
                    existingApprenant.setNiveauName(niveauTF.getText());

                    as.update(existingApprenant);

                    // Show a success alert for update
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated.");
                    alert.showAndWait();
                } else {
                    // Display an alert with validation error messages
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Validation Error");
                    alert.setHeaderText("Form contains errors");
                    alert.setContentText(validationResult.getMessages().toString());
                    alert.showAndWait();
                }
            } else {
                // Handle the case where no Apprenant with the given criteria is found
                System.out.println("Apprenant not found based on the given criteria.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // Initialize the validation support
        validationSupport = new ValidationSupport();

        // Add validators for mandatory fields
        validationSupport.registerValidator(NameTF, Validator.createEmptyValidator("Name is required"));
        validationSupport.registerValidator(emailTF, Validator.createEmptyValidator("Email is required"));
        validationSupport.registerValidator(passwordTF, Validator.createEmptyValidator("Password est obligatoire"));
        validationSupport.registerValidator(StatutTF, Validator.createEmptyValidator("Statut est obligatoire"));
        validationSupport.registerValidator(FormationTF, Validator.createEmptyValidator("Formation est obligatoire"));
        validationSupport.registerValidator(niveauTF, Validator.createEmptyValidator("Niveau est obligatoire"));
        validationSupport.registerValidator(idNiveauTF, Validator.createEmptyValidator("id est obligatoire"));

    }
}

