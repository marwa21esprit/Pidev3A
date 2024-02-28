package controllers;

import controllers.Items;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Etablissement;
import services.EtablissementServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetEtabliss1 {

    @FXML
    private GridPane event_gridPane;

    @FXML
    public void initialize() throws IOException {
        schoolDisplay();
    }

    private final EtablissementServices es = new EtablissementServices();

    @FXML
    void ajouterSchool(ActionEvent event) {
        loadFXML("/AddEtabliss1.fxml", event);
    }

    @FXML
    void gestionSchool(ActionEvent event) {
        loadFXML("/GetEtabliss1.fxml", event);
        afficherAlerteInformation("Schools Management", "You are already in Schools Management.");
    }

    @FXML
    void gestioncertif(ActionEvent event) {
        loadFXML("/GetCertif1.fxml", event);
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
    public void schoolDisplay() throws IOException {
        try {
            List<Etablissement> eventList = es.getAll();
            event_gridPane.getChildren().clear();

            int row = 0;

            for (Etablissement etablissement : eventList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/items.fxml"));
                AnchorPane pane = loader.load();
                Items itemsController = loader.getController();
                itemsController.setData(etablissement, event -> {
                    event_gridPane.getChildren().remove(pane);
                    try {
                        es.deleteSchool(etablissement.getID_Etablissement()); // Supprimer l'établissement de la base de données
                    } catch (SQLException e) {
                        e.printStackTrace();
                        afficherAlerteInformation("Error", "Failed to delete the establishment from the database.");
                    }
                });
                event_gridPane.add(pane, 0, row++);
            }

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void afficherAlerteInformation(String title, String message) {
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
