package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import models.Certificat;
import services.CertficatServices;

public class GetCertif1 {

    @FXML
    private AnchorPane event_AP;

    @FXML
    private GridPane event_gridPane;

    @FXML
    private ScrollPane event_scrollPane;

    private CertficatServices cs = new CertficatServices();

    @FXML
    public void initialize() {
        certifDisplay();
    }

    private void loadFXML(String path, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void certifDisplay() {
        try {
            List<Certificat> certificatList = cs.getAll();
            event_gridPane.getChildren().clear();

            int row = 0;

            for (Certificat certificat : certificatList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/items1.fxml"));
                AnchorPane pane = loader.load();
                items1 itemsController = loader.getController();
                itemsController.setData(certificat, event -> {
                    // Supprimer le nœud de l'affichage lorsque l'utilisateur supprime le certificat
                    event_gridPane.getChildren().remove(pane);
                    // Supprimer également le certificat de la base de données
                    try {
                        cs.deleteCertificate(certificat.getID_Certificat()); // Supprimer le certificat de la base de données
                    } catch (SQLException e) {
                        e.printStackTrace();
                        showAlert("Error", "Failed to delete the certificate from the database.");
                    }
                });
                event_gridPane.add(pane, 0, row++);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to display certificates.");
        }
    }


    @FXML
    void affi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetEtabliss1.fxml"));
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
    void ajouterCertificat(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddCertif1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gestionSchool(ActionEvent event) {
        loadFXML("/GetEtabliss1.fxml", event);
    }

    @FXML
    void gestionCertificat(ActionEvent event) {

        showAlert("Certificates Management", "You are already in Certificates Management.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void afficherAlerteErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
