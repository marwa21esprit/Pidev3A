package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Certificat;

import services.CertficatServices;
import services.EtablissementServices;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class AddCertif1 {

    private final CertficatServices cs = new CertficatServices();
    private final EtablissementServices es = new EtablissementServices();

    @FXML
    private DatePicker Date_Obtention_Certificat;

    @FXML
    private ChoiceBox<String> ID_Etablissement;

    @FXML
    private ChoiceBox<String> Domaine_Certificat;

    @FXML
    private TextField Nom_Certificat;

    @FXML
    private ChoiceBox<String> Niveau;

    @FXML
    private Label nomCertificatErrorLabel;

    @FXML
    private Label dateObtentionErrorLabel;
    @FXML
    private ImageView captchaImageView;
    @FXML
    private TextField solutionTextField;
    @FXML
    private  Label resultLabel;
    private String captchaSolution;

    @FXML
    void initialize() {

        // Generate and display initial CAPTCHA
        generateCaptcha();

        // Charger les noms des établissements dans le ChoiceBox ID_Etablissement
        try {
            List<String> nomsEtablissements = es.getNoms();
            ObservableList<String> observableNoms = FXCollections.observableArrayList(nomsEtablissements);
            ID_Etablissement.setItems(observableNoms);
        } catch (SQLException e) {
            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors du chargement des établissements.");
            e.printStackTrace();
        }

        // Écouteur
        Nom_Certificat.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidString(newValue)) {
                afficherErreurNomCertificat("Veuillez entrer un nom valide pour le certificat (sans chiffres).");
            } else {
                nomCertificatErrorLabel.setVisible(false);
            }
        });

        // Écouteur
        Date_Obtention_Certificat.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isAfter(LocalDate.now())) {
                afficherErreurDateObtention("La date d'obtention ne peut pas être postérieure à aujourd'hui.");
            } else {
                dateObtentionErrorLabel.setVisible(false);
            }
        });
    }

    @FXML
    void affi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetCertif1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            afficherAlerteErreur("Erreur de chargement", "Une erreur est survenue lors du chargement de la vue.");
        }
    }

    @FXML
    void ajouter(ActionEvent event) {
        String userInput = solutionTextField.getText();
        if (!userInput.equals(captchaSolution)) {
            resultLabel.setText("CAPTCHA verification failed. Try again.");
        } else {
            try {
                LocalDate dateObtention = Date_Obtention_Certificat.getValue();

                if (!isValidString(Nom_Certificat.getText())) {
                    afficherErreurNomCertificat("Veuillez entrer un nom valide pour le certificat (sans chiffres).");
                    return;
                }

                if (Domaine_Certificat.getValue() == null || Domaine_Certificat.getValue().isEmpty()) {
                    afficherAlerteErreur("Erreur de saisie", "Veuillez sélectionner un domaine pour le certificat.");
                    return;
                }

                if (Niveau.getValue() == null || Niveau.getValue().isEmpty()) {
                    afficherAlerteErreur("Erreur de saisie", "Veuillez sélectionner un niveau pour le certificat.");
                    return;
                }

                if (dateObtention == null) {
                    afficherAlerteErreur("Erreur de saisie", "Veuillez sélectionner une date d'obtention pour le certificat.");
                    return;
                }

                if (dateObtention.isAfter(LocalDate.now())) {
                    afficherErreurDateObtention("La date d'obtention ne peut pas être postérieure à aujourd'hui.");
                    return;
                }

                // recuperer le nom de l'établissement sélectionné
                String nomEtablissement = ID_Etablissement.getValue();

                // Vérifier l'unicité du certificat
                if (cs.isUniqueCertificate(Nom_Certificat.getText(), Domaine_Certificat.getValue(), Niveau.getValue(), Date.valueOf(dateObtention), es.getIDByNom(nomEtablissement))) {
                    // l'ajout
                    cs.addCertificate(new Certificat(
                            Nom_Certificat.getText(),
                            Domaine_Certificat.getValue(),
                            Niveau.getValue(),
                            Date.valueOf(dateObtention),
                            es.getIDByNom(nomEtablissement) // Utiliser la méthode de EtablissementServices pour obtenir l'ID par le nom
                    ));
                    TrayNotification tray = new TrayNotification();
                    tray.setTitle("Ajout réussi");
                    tray.setMessage("Le certificat a été ajouté avec succès.");
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndWait();

                    // afficherAlerteInformation("Ajout réussi", "Le certificat a été ajouté avec succès.");
                } else {
                    afficherAlerteErreur("Erreur d'ajout", "Un certificat avec les mêmes données existe déjà.");
                }
            } catch (SQLException e) {
                afficherAlerteErreur("Erreur lors de l'ajout", "Une erreur est survenue lors de l'ajout du certificat. Veuillez réessayer.");
                e.printStackTrace();
            }
        }
    }

    private boolean isValidString(String input) {
        return !input.matches(".*\\d.*");
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

    @FXML
    private void afficherErreurNomCertificat(String message) {
        nomCertificatErrorLabel.setText(message);
        nomCertificatErrorLabel.setVisible(true);
    }

    @FXML
    private void afficherErreurDateObtention(String message) {
        dateObtentionErrorLabel.setText(message);
        dateObtentionErrorLabel.setVisible(true);
    }

    //BOUTTON CLEAR
    public void clear(ActionEvent event) {

        Nom_Certificat.setText("");
        Domaine_Certificat.setValue("choose a domain");
        ID_Etablissement.setValue("choose school");
        Niveau.setValue("choose level");
        Date_Obtention_Certificat.setValue(null);

    }

    @FXML
    void regenerateCaptcha(ActionEvent event) {
        generateCaptcha();
        resultLabel.setText("");
    }

    private void generateCaptcha() {
        // Generate a random CAPTCHA solution (e.g., a 4-digit number)
        Random random = new Random();
        captchaSolution = String.format("%04d", random.nextInt(10000));

        // Create an image representing the CAPTCHA
        // For simplicity, we'll just use a placeholder image here
        Image captchaImage = new Image("https://via.placeholder.com/200x100?text=" + captchaSolution);
        captchaImageView.setImage(captchaImage);
    }


}
