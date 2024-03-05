package controller;

import entities.Formation;
import Services.ServiceFormation;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

public class showFormation1 implements Initializable {

    private final ServiceFormation ft = new ServiceFormation();


    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    private TextField recherche;

    @FXML
    private ChoiceBox<String> triChoiceBox;

    @FXML
    private GridPane formation_gridPane;

    @FXML
    private ScrollPane formation_scrollPane;

    @FXML
    private AnchorPane formation_AP;

    private Formation formation;

    private ObservableList<Formation> formationList;

    public void formationDisplay() throws SQLException {
        formationList = FXCollections.observableList(ft.getAll());
        int row = 0;
        int column = 0;
        formation_gridPane.getRowConstraints().clear();
        formation_gridPane.getColumnConstraints().clear();
        formation_gridPane.getChildren().clear();
        for(int i=0;i<formationList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items1.fxml"));
                AnchorPane pane = load.load();
                Items1 items = load.getController();
                items.setData(formationList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 1)
                {
                    column=0;
                    row+=1;
                }

                formation_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    private void afficherFormations(List<Formation> formations) {
        int row = 0;
        int column = 0;
        formation_gridPane.getRowConstraints().clear();
        formation_gridPane.getColumnConstraints().clear();
        formation_gridPane.getChildren().clear();
        for (int i = 0; i < formations.size(); i++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items1.fxml"));
                AnchorPane pane = load.load();
                Items1 items = load.getController();
                items.setData(formations.get(i));

                // Mise à jour de l'affichage
                pane.getProperties().put("controller", this);
                if (column == 1) {
                    column = 0;
                    row += 1;
                }
                formation_gridPane.add(pane, column++, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            formationDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        triChoiceBox.setItems(FXCollections.observableArrayList("default","Trier par categorie", "Trier par niveau", "Trier par prix"));


        triChoiceBox.setValue("default"); // Notez que je laisse une chaîne vide ici, mais vous pouvez choisir une valeur par défaut différente si nécessaire


        if (triChoiceBox.getValue() == "default") {
            afficherFormationsNonTries();
        }
    }
    @FXML
    void ajouterFormation(ActionEvent event) {

        loadAddFormationInterface();

    }

    private void loadAddFormationInterface() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addFormation1.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);

            // Récupérer la fenêtre actuelle et la modifier pour afficher la nouvelle scène
            Stage stage = (Stage) formation_scrollPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
        String searchText = recherche.getText().trim().toLowerCase();

        ObservableList<Formation> formationsTrouves = FXCollections.observableArrayList();

        for (Formation formation : formationList) {

            if (String.valueOf(formation.getId_tuteur()).contains(searchText) ||
                    formation.getCategorie().toLowerCase().contains(searchText) ||
                    formation.getDescription().toLowerCase().contains(searchText) ||
                    formation.getTitre().toLowerCase().contains(searchText))
                   {
                formationsTrouves.add(formation);
            }
        }

        afficherFormationsTrouves(formationsTrouves);
    }
    private void afficherFormationsTrouves(ObservableList<Formation> formationsTrouves) {
        int row = 0;
        int column = 0;
        formation_gridPane.getRowConstraints().clear();
        formation_gridPane.getColumnConstraints().clear();
        formation_gridPane.getChildren().clear();
        for (int i = 0; i < formationsTrouves.size(); i++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items1.fxml"));
                AnchorPane pane = load.load();
                Items1 items = load.getController();
                items.setData(formationsTrouves.get(i));

                pane.getProperties().put("controller", this);
                if (column == 1) {
                    column = 0;
                    row += 1;
                }
                formation_gridPane.add(pane, column++, row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void triFormations(ActionEvent event) {

        String choixTri = triChoiceBox.getValue();
        if (choixTri != null) {
            switch (choixTri) {
                case "Trier par categorie":
                    trierParCategorie();
                    break;
                case "Trier par niveau":
                    trierParNiveau();
                    break;
                case "Trier par matière":
                    trierParPrix();
                    break;
                case "default":
                    afficherFormationsNonTries();
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

    private void trierParCategorie() {
        try {
            List<Formation> formations = ft.trierParCategorie();
            System.out.println(formations.size());
           afficherFormations(formations);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par categorie.");
        }
    }

    private void trierParNiveau() {
        try {
            List<Formation> formations = ft.trierParNiveau();
            System.out.println(formations.size());
            afficherFormations(formations);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par niveau.");
        }
    }

    private void trierParPrix() {
        try {
            List<Formation> formations = ft.trierParPrix();
            System.out.println(formations.size());
            afficherFormations(formations);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par prix.");
        }
    }


    private void afficherFormationsNonTries() {
        try {
            List<Formation> formations= ft.getAll();
            afficherFormations(formations);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors de l'affichage des formations.");
        }
    }






}
