package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Certificat;
import models.Etablissement;
import services.EtablissementServices;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateEtabliss1 {

    private final EtablissementServices es = new EtablissementServices();

    @FXML
    private DatePicker Date_Fondationmodif;

    @FXML
    private TextField Adresse_Etablissementmodif;

    @FXML
    private TextField Directeur_Etablissementmodif;

    @FXML
    private TextField ID_Etablissementmodif;

    @FXML
    private TextField Nom_Etablissementmodif;

    @FXML
    private TextField Tel_Etablissementmodif;

    @FXML
    private ChoiceBox<String> Type_Etablissementmodif;

    @FXML
    private Stage primaryStage;

    @FXML
    private Button importBT;

    @FXML
    private ImageView importIV;

    @FXML
    private AnchorPane mainForm;
    private Image image;


    @FXML
    private Label addressLabelError;

    @FXML
    private Label datefondLabelError;

    @FXML
    private Label directorLabelError;

    @FXML
    private Label nomLabelError;

    @FXML
    private Label telLabelError;

    @FXML
    private void initialize() {
        // Écouteur pour le champ Nom_Etablissement
        Nom_Etablissementmodif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidString(newValue)) {
                afficherErreur(nomLabelError, "Veuillez entrer un nom valide pour l'établissement (sans chiffres).");
            } else {
                clearError(nomLabelError);
            }
        });

        // Écouteur pour le DatePicker Date_Fondation
        Date_Fondationmodif.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isAfter(LocalDate.now())) {
                afficherErreur(datefondLabelError, "La date de fondation ne peut pas être postérieure à aujourd'hui.");
            } else {
                clearError(datefondLabelError);
            }
        });
        // Écouteur pour le champ Directeur_Etablissement
        Directeur_Etablissementmodif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidString(newValue)) {
                afficherErreur(directorLabelError, "Veuillez entrer un nom de directeur valide pour l'établissement (sans chiffres).");
            } else {
                clearError(directorLabelError);
            }
        });

        // Écouteur pour le champ Tel_Etablissement
        Tel_Etablissementmodif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidPhoneNumber(newValue)) {
                afficherErreur(telLabelError, "Veuillez saisir un numéro de téléphone valide composé de 8 chiffres.");
            } else {
                clearError(telLabelError);
            }
        });

        // Écouteur pour le champ Adresse_Etablissement
        Adresse_Etablissementmodif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidString(newValue)) {
                afficherErreur(addressLabelError, "Veuillez entrer une adresse valide pour l'établissement (sans chiffres).");
            } else {
                clearError(addressLabelError);
            }
        });
    }

    public void importer(ActionEvent event) {
        Etablissement es = new Etablissement();
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File","*png","*jpg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());
        if(file != null)
        {
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 125,130,false , true);
            importIV.setImage(image);
        }
    }

    @FXML
    void affi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetEtabliss1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Date_Fondationmodif.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSchoolData(Etablissement school) {
        ID_Etablissementmodif.setText(String.valueOf(school.getID_Etablissement()));
        data.path = school.getImg_Etablissement(); // Utiliser une affectation simple pour définir le chemin de l'image
        Nom_Etablissementmodif.setText(school.getNom_Etablissement());
        Adresse_Etablissementmodif.setText(school.getAdresse_Etablissement());
        Type_Etablissementmodif.setValue(school.getType_Etablissement());
        Tel_Etablissementmodif.setText(String.valueOf(school.getTel_Etablissement()));
        Directeur_Etablissementmodif.setText(school.getDirecteur_Etablissement());
        Date_Fondationmodif.setValue(school.getDate_Fondation().toLocalDate());

        String imagePath = school.getImg_Etablissement();

        try {
            File imageFile = new File(imagePath);
            Image image = new Image(imageFile.toURI().toString());
            importIV.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void modifier(ActionEvent event) {
        try {
            Date dateFondation = Date.valueOf(Date_Fondationmodif.getValue());
            String typeEtablissement = Type_Etablissementmodif.getValue();

            if (Nom_Etablissementmodif.getText().isEmpty()) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer un nom pour l'établissement.");
                return;
            }

            if (!isValidString(Adresse_Etablissementmodif.getText())) {
                afficherAlerteErreur("Erreur de saisie", "Veuillez entrer une adresse valide pour l'établissement (sans chiffres).");
                return;
            }

            if (!isValidString(Type_Etablissementmodif.getValue())) {
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

            if (!isValidDate(dateFondation)) {
                afficherAlerteErreur("Erreur de saisie", "La date de fondation ne peut pas être ultérieure à aujourd'hui.");
                return;
            }

            if (!es.isUniqueEtablissement(Nom_Etablissementmodif.getText(), Adresse_Etablissementmodif.getText(), typeEtablissement, Integer.parseInt(Tel_Etablissementmodif.getText()), Directeur_Etablissementmodif.getText(), dateFondation)) {
                afficherAlerteErreur("Erreur d'ajout", "Un établissement avec les mêmes données existe déjà.");
                return;
            }

            int idEtablissement = Integer.parseInt(ID_Etablissementmodif.getText());

            // Créer un nouvel établissement avec les valeurs des champs de modification
            Etablissement updatedSchool = new Etablissement(
                    data.path,
                    Nom_Etablissementmodif.getText(),
                    Adresse_Etablissementmodif.getText(),
                    typeEtablissement,
                    Integer.parseInt(Tel_Etablissementmodif.getText()),
                    Directeur_Etablissementmodif.getText(),
                    dateFondation,
                    idEtablissement
            );
            updatedSchool.setImg_Etablissement(data.path);

            es.updateSchool(updatedSchool, idEtablissement);

            afficherAlerteInformation("Mise à jour réussie", "L'établissement a été mis à jour avec succès.");
        } catch (NumberFormatException e) {
            afficherAlerteErreur("Erreur de format", "Veuillez entrer des valeurs valides.");
            e.printStackTrace();
        } catch (SQLException e) {
            afficherAlerteErreur("Erreur SQL", "Une erreur SQL est survenue lors de la mise à jour de l'établissement. Veuillez réessayer.");
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

    private boolean isValidString(String str) {
        return str.matches("[\\p{L}.\\s]+");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\d{8}")) {
            return false;
        }
        return true;
    }
    private boolean isValidDate(Date date) {
        return date.toLocalDate().isBefore(java.time.LocalDate.now());
    }

    public void clear(ActionEvent event) {
        data.path = "";
        importIV.setImage(null);
        Nom_Etablissementmodif.setText("");
        Adresse_Etablissementmodif.setText("");
        Type_Etablissementmodif.setValue("choisir un type");
        Tel_Etablissementmodif.setText("");
        Directeur_Etablissementmodif.setText("");
        Date_Fondationmodif.setValue(null);


    }

    private void afficherErreur(Label label, String message) {
        label.setText(message);
        label.setVisible(true);
    }

    private void clearError(Label label) {
        label.setVisible(false);
    }


}
