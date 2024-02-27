package controller;
import Services.ServiceFormation;
import Services.ServiceTuteur;
import entities.Formation;
import entities.Tuteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class showFormation {

    private final ServiceFormation ft = new ServiceFormation();

    @FXML
    private TableView<Formation> afficher;

    @FXML
    private TableColumn<Formation,String> col_categorie;

    @FXML
    private TableColumn<Formation, Date> col_date_d;

    @FXML
    private TableColumn<Formation,Date> col_date_f;

    @FXML
    private TableColumn<Formation,String> col_description;

    @FXML
    private TableColumn<Formation,Integer> col_id_formation;

    @FXML
    private TableColumn<Formation,Integer> col_id_niveau;

    @FXML
    private TableColumn<Formation,Integer> col_id_tuteur;

    @FXML
    private TableColumn<Formation,String> col_lien;

    @FXML
    private TableColumn<Formation,Float> col_prix;

    @FXML
    private TableColumn<Formation,String> col_titre;

    @FXML
    public void initialize() {
        try {
            // Associer les propriétés des objets Tuteur aux colonnes de la TableView
            col_id_formation.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
            col_id_tuteur.setCellValueFactory(new PropertyValueFactory<>("id_tuteur"));
            col_id_niveau.setCellValueFactory(new PropertyValueFactory<>("id_niveau"));
            col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_date_d.setCellValueFactory(new PropertyValueFactory<>("date_d"));
            col_date_f.setCellValueFactory(new PropertyValueFactory<>("date_f"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_lien.setCellValueFactory(new PropertyValueFactory<>("lien"));

            // Charger les données dans la TableView
            loadTableData();

        } catch (Exception e) {
            // Afficher une alerte en cas d'erreur lors de l'initialisation
            showAlert("Erreur", "Erreur lors de l'initialisation", e.getMessage());
        }

        configureTableColumns();
    }

    private void configureTableColumns() {

        // Créer une colonne d'action avec des boutons pour la modification et la suppression
        TableColumn<Formation, Void> actionCol = new TableColumn<>("Action");

        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button modifyButton = new Button("Modifier");
            private final Button deleteButton = new Button("Supprimer");
            private final HBox buttons = new HBox(modifyButton, deleteButton);

            {
                // Action à effectuer lors du clic sur le bouton "Modifier"
                modifyButton.setOnAction(event -> {
                    Formation formation = getTableView().getItems().get(getIndex());
                    openUpdateFormation(formation);
                });

                // Action à effectuer lors du clic sur le bouton "Supprimer"
                deleteButton.setOnAction(event -> {
                    Formation formation = getTableView().getItems().get(getIndex());
                    DeleteFormation(formation);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Afficher les boutons de modification et de suppression
                    setGraphic(buttons);
                }
            }
        });

        // Ajouter la colonne d'action à la TableView
        afficher.getColumns().add(actionCol);
    }

    private void openUpdateFormation(Formation formation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateFormation.fxml"));
            Parent root = loader.load();

            // Passer le tuteur sélectionné au contrôleur de l'interface de modification
            UpdateFormation controller = loader.getController();
            controller.initData(formation);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur, par exemple, afficher une alerte à l'utilisateur
        }
    }


    private void showAlert(String erreur, String s, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(erreur);
        alert.setHeaderText(s);
        alert.setContentText(message);
        alert.showAndWait();
        }


    private void loadTableData() {

        try {
            // Récupérer la liste des tuteurs depuis le service
            ObservableList<Formation> formations = FXCollections.observableArrayList(ft.getAll());
            // Ajouter les tuteurs à la TableView
            afficher.setItems(formations);
        } catch (SQLException e) {
            // Afficher une alerte en cas d'erreur lors du chargement des données
            showAlert("Erreur", "Erreur lors du chargement des données", e.getMessage());
        }
    }

    private void DeleteFormation(Formation formation) {
        try {
            // Supprimer le tuteur à l'aide du service
            ft.deleteFormation(formation.getId_formation());
            // Recharger les données après la suppression
            loadTableData();
        } catch (SQLException e) {
            // Afficher une alerte en cas d'erreur lors de la suppression
            showAlert("Erreur", "Erreur lors de la suppression du tuteur", e.getMessage());
        }
    }

    public void retourVersAddFormation(javafx.event.ActionEvent actionEvent) {
        // Appeler une méthode pour charger l'interface AddFormation
        loadAddTuteurInterface();
    }
    private void loadAddTuteurInterface() {
        try {
            // Charger l'interface AddFormation.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddFormation.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène à partir de l'interface chargée
            Scene scene = new Scene(root);

            // Récupérer la fenêtre actuelle et la modifier pour afficher la nouvelle scène
            Stage stage = (Stage) afficher.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur, par exemple, afficher une alerte à l'utilisateur
        }
    }

}
