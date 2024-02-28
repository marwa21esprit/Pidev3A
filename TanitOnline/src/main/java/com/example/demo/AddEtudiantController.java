package com.example.demo;

import Services.ApprenantServices;
import Services.NiveauServices;
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
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import models.Niveau;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class AddEtudiantController implements Initializable  {

        ObservableList<Niveau> niveauList;
        ApprenantServices as = new ApprenantServices();
        NiveauServices ns = new NiveauServices();

        @FXML
        private TextField FormationTF;

        @FXML
        private TextField NameTF;

        @FXML
        private TextField StatutTF;
        @FXML
        private CheckBox ch_acc_box;

        @FXML
        private PasswordField passwordTF;

        @FXML
        private Button create_learner_btnButton;

        @FXML
        private TextField emailTF;

        @FXML
        private AnchorPane img_learner;

        @FXML
        private TextField niveauTF;
        @FXML
        private TextField idNiveauTF;

        private ValidationSupport validationSupport;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {


                // Charge la liste des niveaux depuis la base de données
                try {
                        niveauList = FXCollections.observableArrayList(ns.getAll());
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }







                // Initialise le support de validation
                validationSupport = new ValidationSupport();


                validationSupport.registerValidator(NameTF, Validator.createEmptyValidator("Nom est obligatoire"));


                validationSupport.registerValidator(emailTF, Validator.createEmptyValidator("Email est obligatoire"));
                validationSupport.registerValidator(passwordTF, Validator.createEmptyValidator("Password est obligatoire"));
                validationSupport.registerValidator(StatutTF, Validator.createEmptyValidator("Statut est obligatoire"));
                validationSupport.registerValidator(FormationTF, Validator.createEmptyValidator("Formation est obligatoire"));
                validationSupport.registerValidator(niveauTF, Validator.createEmptyValidator("Niveau est obligatoire"));
                validationSupport.registerValidator(idNiveauTF, Validator.createEmptyValidator("id est obligatoire"));


        }

        private void afficherAlerteErreur(String erreurSql, String s) {
        }

        @FXML
        void Insert(ActionEvent event) {
                // Vérifie la validité du formulaire avant de procéder
                ValidationResult validationResult = validationSupport.getValidationResult();
                if (validationResult.getErrors().isEmpty()) {
                        try {
                                // Récupérer la valeur de idNiveau depuis le TextField
                                int idNiveau = Integer.parseInt(idNiveauTF.getText());
                                as.add(new Apprenant(
                                        NameTF.getText(),
                                        emailTF.getText(),
                                        passwordTF.getText(),
                                        StatutTF.getText(),
                                        FormationTF.getText(),
                                        niveauTF.getText(),
                                        idNiveau
                                ));

                                // Afficher une alerte de succès
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Success");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Successfully added!");
                                successAlert.showAndWait();

                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        } catch (NumberFormatException e) {
                                // Gérer le cas où la conversion en entier échoue (par exemple, si le texte n'est pas un nombre)
                                e.printStackTrace(); // Ajoutez une logique appropriée ici
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


}
