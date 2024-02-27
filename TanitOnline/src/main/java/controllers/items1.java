package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EtablissementServices;
import models.Etablissement;
import services.CertficatServices;
import models.Certificat;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class items1 {

    @FXML
    private Label Date_Obtentionafficher;

    @FXML
    private Label Domaineafficher;

    @FXML
    private Label ID_Etablissementafficher;

    @FXML
    private Label Niveauafiicher;

    @FXML
    private Label Nom_Certificatafficher;

    @FXML
    private Button modifierBT;

    @FXML
    private AnchorPane school_aff;

    @FXML
    private Button supprimerBT;

    private Certificat certificat;
    private CertficatServices cs = new CertficatServices(); // Initialisation du service

    public void initialize() {
        try {
            List<Certificat> certificats = cs.getAll();

            if (!certificats.isEmpty()) {
                certificat = certificats.get(0);
                Domaineafficher.setText(certificat.getDomaine_Certificat());
                Date_Obtentionafficher.setText(certificat.getDate_Obtention_Certificat().toString());
                Nom_Certificatafficher.setText(certificat.getNom_Certificat());
                Niveauafiicher.setText(certificat.getNiveau());
                ID_Etablissementafficher.setText(String.valueOf(certificat.getID_Etablissement()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void modifier(ActionEvent event) {
        handleEdit();
    }

    @FXML
    void supprimer(ActionEvent event) {
        handleDelete();
    }

    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer ce certificat ?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    cs.deleteCertificate(certificat.getID_Certificat());
                    school_aff.setVisible(false); // Supprime visuellement le certificat de l'interface utilisateur
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erreur");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Une erreur s'est produite lors de la suppression du certificat.");
                    errorAlert.showAndWait();
                }
            }
        });
    }

    private void handleEdit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCertif1.fxml"));
            Parent root = loader.load();
            UpdateCertif1 controller = loader.getController();
            controller.setCertificateData(certificat);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Une erreur s'est produite lors du chargement de la vue d'édition.");
            errorAlert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(Certificat certificat) {
        this.certificat = certificat;
        Nom_Certificatafficher.setText(certificat.getNom_Certificat());
        Domaineafficher.setText(certificat.getDomaine_Certificat());
        Niveauafiicher.setText(certificat.getNiveau());
        Date_Obtentionafficher.setText(certificat.getDate_Obtention_Certificat().toString());

        // récupérer l'ID etabliss depuis  certif
        int etablissementId = certificat.getID_Etablissement();
        try {
            // Récupérer le nom de l'établissement correspondant à l'ID
            String nomEtablissement = cs.getEtablissementName(etablissementId);
            // afficher le nom etabliss dans la vue
            ID_Etablissementafficher.setText(nomEtablissement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    }

