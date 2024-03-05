package controller;


import Services.ServiceTuteur;
import entities.Tuteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class showTuteur1 implements Initializable {

    private final ServiceTuteur st = new ServiceTuteur();

    @FXML
    private AnchorPane tuteur_AP;

    @FXML
    public GridPane tuteur_gridPane;

    @FXML
    private ScrollPane tuteur_scrollPane;

    @FXML
    private ChoiceBox<String> triChoiceBox;

    @FXML
    private TextField recherche;

    private Tuteur tuteur;

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    private ObservableList<Tuteur> tuteurList;

    public void tuteurDisplay() throws SQLException {
        tuteurList = FXCollections.observableList(st.getAll());
        int row = 0;
        int column = 0;
        tuteur_gridPane.getRowConstraints().clear();
        tuteur_gridPane.getColumnConstraints().clear();
        tuteur_gridPane.getChildren().clear();
        for (int i = 0; i < tuteurList.size(); i++)
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items.fxml"));
                AnchorPane pane = load.load();
                Items items = load.getController();
                items.setData(tuteurList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if (column == 1) {
                    column = 0;
                    row += 1;
                }

                tuteur_gridPane.add(pane, column++, row);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tuteurDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Ajoutez les options de tri au ChoiceBox
        triChoiceBox.setItems(FXCollections.observableArrayList("default","Trier par nom", "Trier par prénom", "Trier par matière"));

        // Définissez une option par défaut si nécessaire
        triChoiceBox.setValue("default"); // Notez que je laisse une chaîne vide ici, mais vous pouvez choisir une valeur par défaut différente si nécessaire

        // Vérifiez si une valeur est sélectionnée dans le ChoiceBox avant d'afficher les certificats non triés
        if (triChoiceBox.getValue() == "default") {
            afficherTuteursNonTries();
        }
    }

    @FXML
    void ajouterTuteur(ActionEvent event) {

        loadAddTuteurInterface();

    }

    private void loadAddTuteurInterface() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addTuteur.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);

            // Récupérer la fenêtre actuelle et la modifier pour afficher la nouvelle scène
            Stage stage = (Stage) tuteur_scrollPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }




    private void afficherTuteurs(List<Tuteur> tuteurs) {
        int row = 0;
        int column = 0;
        tuteur_gridPane.getRowConstraints().clear();
        tuteur_gridPane.getColumnConstraints().clear();
        tuteur_gridPane.getChildren().clear();
        for (int i = 0; i < tuteurs.size(); i++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items.fxml"));
                AnchorPane pane = load.load();
                Items items = load.getController();
                items.setData(tuteurs.get(i));

                // Mise à jour de l'affichage
                pane.getProperties().put("controller", this);
                if (column == 1) {
                    column = 0;
                    row += 1;
                }
                tuteur_gridPane.add(pane, column++, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void showTuteur1(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showTuteur1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showFormation1(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    void recherche(ActionEvent event) {
        String searchText = recherche.getText().trim().toLowerCase(); // Récupérer le texte entré dans le champ de recherche

        ObservableList<Tuteur> tuteursTrouves = FXCollections.observableArrayList();

        for (Tuteur tuteur : tuteurList) {

            if (tuteur.getNom().toLowerCase().contains(searchText) ||
                    tuteur.getPrenom().toLowerCase().contains(searchText) ||
                    tuteur.getProfession().toLowerCase().contains(searchText) ||
                    tuteur.getEmail().toLowerCase().contains(searchText) ||
                    String.valueOf(tuteur.getTlf()).contains(searchText) ||
                    formatDate(tuteur.getDate_naisc()).contains(searchText)) {
                tuteursTrouves.add(tuteur);
            }
        }

        afficherTuteursTrouves(tuteursTrouves);
    }

    // Méthode pour formater la date dans un format de chaîne spécifique
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }


    private void afficherTuteursTrouves(ObservableList<Tuteur> tuteursTrouves) {
        int row = 0;
        int column = 0;
        tuteur_gridPane.getRowConstraints().clear();
        tuteur_gridPane.getColumnConstraints().clear();
        tuteur_gridPane.getChildren().clear();
        for (int i = 0; i < tuteursTrouves.size(); i++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items.fxml"));
                AnchorPane pane = load.load();
                Items items = load.getController();
                items.setData(tuteursTrouves.get(i));

                pane.getProperties().put("controller", this);
                if (column == 1) {
                    column = 0;
                    row += 1;
                }
                tuteur_gridPane.add(pane, column++, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void triTuteurs(ActionEvent event) {
        String choixTri = triChoiceBox.getValue();
        if (choixTri != null) {
            switch (choixTri) {
                case "Trier par nom":
                    trierParNom();
                    break;
                case "Trier par prénom":
                    trierParPrenom();
                    break;
                case "Trier par matière":
                    trierParProfession();
                    break;
                case "default":
                    afficherTuteursNonTries();
                    break;
                default:
                    showAlert("Erreur", "Choix de tri non valide.");
            }
        } else {
            showAlert("Erreur", "Veuillez sélectionner un critère de tri.");
        }
    }


    private void showAlert(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    private void trierParNom() {
        try {
            List<Tuteur> tuteurs = st.trierParNom();
            System.out.println(tuteurs.size());
            afficherTuteurs(tuteurs);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par nom.");
        }
    }

    private void trierParPrenom() {
        try {
            List<Tuteur> tuteurs = st.trierParPrenom();
            System.out.println(tuteurs.size());
            afficherTuteurs(tuteurs);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par prénom.");
        }
    }

    private void trierParProfession() {
        try {
            List<Tuteur> tuteurs = st.trierParProfession();
            System.out.println(tuteurs.size());
            afficherTuteurs(tuteurs);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par matière.");
        }
    }


    private void afficherTuteursNonTries() {
        try {
            List<Tuteur> tuteurs= st.getAll();
            afficherTuteurs(tuteurs);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors de l'affichage des formations.");
        }
    }


}