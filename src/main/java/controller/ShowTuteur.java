package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import entities.Tuteur;
import Services.ServiceTuteur;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ShowTuteur {

    private final ServiceTuteur st = new ServiceTuteur();

    @FXML
    private TableView<Tuteur> afficher;

    @FXML
    private TableColumn<Tuteur, Integer> col_id_tuteur;
    @FXML
    private TableColumn<Tuteur, String> col_nom;
    @FXML
    private TableColumn<Tuteur, String> col_prenom;
    @FXML
    private TableColumn<Tuteur, Date> col_date_naisc;
    @FXML
    private TableColumn<Tuteur, Integer> col_tlf;
    @FXML
    private TableColumn<Tuteur, String> col_profession;
    @FXML
    private TableColumn<Tuteur, String> col_email;

    @FXML
    private Button retour;

    @FXML
    private void initialize() {

        configureTableColumns();


        loadData();
    }

    private void configureTableColumns() {

        TableColumn<Tuteur, Void> actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");
            private final Button editButton = new Button("Modifier");

            {

                deleteButton.setOnAction(event -> {
                    Tuteur tuteur = getTableView().getItems().get(getIndex());
                    // Supprimer le certificat sélectionné de la base de données
                    try {
                        st.deleteTuteur(tuteur.getId_tuteur());
                        // Mettre à jour la TableView pour refléter le changement dans les données
                        getTableView().getItems().remove(tuteur);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Gérer l'erreur, par exemple, afficher un message d'erreur à l'utilisateur
                    }
                });

                // Action à effectuer lors du clic sur le bouton Modifier
                editButton.setOnAction(event -> {
                    Tuteur tuteur = getTableView().getItems().get(getIndex());
                    // Appeler une méthode pour gérer l'édition du certificat
                    handleEditTuteur(tuteur);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Créer un conteneur pour les boutons
                    HBox buttonsContainer = new HBox(deleteButton, editButton);
                    setGraphic(buttonsContainer);
                }
            }
        });

        // Ajouter la colonne d'action à la TableView
        afficher.getColumns().add(actionCol);
        // Associer les propriétés des objets Tuteur aux colonnes de la TableView
        col_id_tuteur.setCellValueFactory(new PropertyValueFactory<>("id_tuteur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_date_naisc.setCellValueFactory(new PropertyValueFactory<>("date_naisc"));
        col_tlf.setCellValueFactory(new PropertyValueFactory<>("tlf"));
        col_profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void handleEditTuteur(Tuteur tuteur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateTuteur1.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la vue chargée
            updateTuteur1 controller = loader.getController();

            // Passer les données du certificat sélectionné au contrôleur
            controller.setTuteurData(tuteur);

            // Créer une nouvelle scène avec le fichier FXML chargé
            Scene scene = new Scene(root);

            // Obtenir la fenêtre principale
            Stage stage = (Stage) afficher.getScene().getWindow();

            // Afficher la nouvelle scène dans la fenêtre principale
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            List<Tuteur> tuteurs= st.getAll();
            afficher.getItems().setAll(tuteurs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retournerVersAddTuteur(javafx.event.ActionEvent actionEvent) {
        // Appeler une méthode pour charger l'interface AddTuteur
        loadAddTuteurInterface();
    }
    private void loadAddTuteurInterface() {
        try {
            // Charger l'interface AddTuteur.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addTuteur1.fxml"));
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
