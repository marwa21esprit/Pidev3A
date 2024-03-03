package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Certificat;
import services.CertficatServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetCertif1 {

    @FXML
    private AnchorPane event_AP;

    @FXML
    private GridPane event_gridPane;

    @FXML
    private ScrollPane event_scrollPane;

    @FXML
    private TextField CertifRecherche;

    @FXML
    private ChoiceBox<String> triChoiceBox;

    private CertficatServices cs = new CertficatServices();

    @FXML
    void rechercherCertificate(ActionEvent event) {
        String nomCertificat = CertifRecherche.getText();
        if (!nomCertificat.isEmpty()) {
            try {
                List<Certificat> certificats = cs.getByPartialName(nomCertificat);
                if (!certificats.isEmpty()) {
                    afficherCertificats(certificats);
                } else {
                    showAlert("Information", "Aucun certificat trouvé avec ce nom.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "Une erreur est survenue lors de la recherche des certificats.");
            }
        } else {
            showAlert("Error", "Veuillez saisir un nom de certificat pour effectuer la recherche.");
        }
    }

    @FXML
    void triCertificats(ActionEvent event) {
        String choixTri = triChoiceBox.getValue(); // Récupérer le choix de tri depuis le ChoiceBox
        if (choixTri != null) {
            switch (choixTri) {
                case "Trier par nom":
                    trierParNom();
                    break;
                case "Trier par domaine":
                    trierParDomaine();
                    break;
                case "Trier par niveau":
                    trierParNiveau();
                    break;
            }
        } else {
            showAlert("Erreur", "Veuillez sélectionner un critère de tri.");
        }
    }








    @FXML
    public void initialize() {
        // Ajoutez les options de tri au ChoiceBox
        triChoiceBox.setItems(FXCollections.observableArrayList("Trier par nom", "Trier par domaine", "Trier par niveau"));

        // Définissez une option par défaut si nécessaire
        triChoiceBox.setValue(" "); // Notez que je laisse une chaîne vide ici, mais vous pouvez choisir une valeur par défaut différente si nécessaire

        // Vérifiez si une valeur est sélectionnée dans le ChoiceBox avant d'afficher les certificats non triés
        if (triChoiceBox.getValue() != null) {
            afficherCertificatsNonTries();
        }
    }

    private void afficherCertificatsNonTries() {
        try {
            List<Certificat> certificats = cs.getAll(); // Obtenir tous les certificats
            afficherCertificats(certificats); // Afficher les certificats
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors de l'affichage des certificats.");
        }
    }




    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void afficherCertificats(List<Certificat> certificats) {
        event_gridPane.getChildren().clear();
        int row = 0;
        for (Certificat certificat : certificats) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemsE1.fxml"));
            try {
                AnchorPane pane = loader.load();
                itemsE1 itemsEController = loader.getController();
                itemsEController.setData(certificat, e -> {
                    event_gridPane.getChildren().remove(pane);
                    try {
                        cs.deleteCertificate(certificat.getID_Certificat());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        showAlert("Error", "Failed to delete the certificate from the database.");
                    }
                });
                event_gridPane.add(pane, 0, row++);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load certificate item view.");
            }
        }
    }

    //  tri par nom, domaine et niveau
    private void trierParNom() {
        try {
            List<Certificat> certificats = cs.trierParNom();
            afficherCertificats(certificats);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par nom.");
        }
    }

    private void trierParDomaine() {
        try {
            List<Certificat> certificats = cs.trierParDomaine();
            afficherCertificats(certificats);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par domaine.");
        }
    }

    private void trierParNiveau() {
        try {
            List<Certificat> certificats = cs.trierParNiveau();
            afficherCertificats(certificats);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par niveau.");
        }
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
            showAlert("Error", "Failed to load the view.");
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
    public void ajouterCertificat(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddCertif1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
