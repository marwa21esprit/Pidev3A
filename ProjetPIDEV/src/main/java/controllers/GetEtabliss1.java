package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Etablissement;
import services.EtablissementServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetEtabliss1 {

    @FXML
    private GridPane event_gridPane;
    @FXML
    private TextField EtablissRecherche;

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

    public void schoolDisplay() throws IOException {
        try {
            List<Etablissement> eventList = es.getAll();
            event_gridPane.getChildren().clear();

            int row = 0;

            for (Etablissement etablissement : eventList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemsE.fxml"));
                AnchorPane pane = loader.load();
                ItemsE itemsEController = loader.getController();
                itemsEController.setData(etablissement, event -> {
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

    @FXML
    void rechercherSchool(ActionEvent event) {
        String nomEtablissement = EtablissRecherche.getText();
        if (!nomEtablissement.isEmpty()) {
            try {
                List<Etablissement> etablissements = es.getByPartialName(nomEtablissement);
                if (!etablissements.isEmpty()) {
                    event_gridPane.getChildren().clear(); // Effacer tous les enfants actuels du GridPane
                    int row = 0;
                    for (Etablissement etablissement : etablissements) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/items.fxml"));
                        AnchorPane pane = loader.load();
                        ItemsE itemsE = loader.getController();
                        itemsE.setSchoolData(etablissement);
                        event_gridPane.add(pane, 0, row++); // Ajouter l'établissement au GridPane
                    }
                } else {
                    afficherAlerteInformation("Information", "Aucun établissement trouvé avec ce nom.");
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                afficherAlerteErreur("Erreur", "Une erreur est survenue lors de la recherche de l'établissement.");
            }
        } else {
            afficherAlerteErreur("Erreur", "Veuillez saisir un nom d'établissement pour effectuer la recherche.");
        }
    }


    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    public void showPartners(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showEvents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void affi(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetEtabliss1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void gestioncertif(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetCertif1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }




}
