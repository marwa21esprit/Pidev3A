package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Etablissement;
import services.EtablissementServices;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class GetEtabliss {

    private final EtablissementServices es = new EtablissementServices();

    @FXML
    private TableColumn<Etablissement, String> Adresse_Etablissementafficher;

    @FXML
    private TableColumn<Etablissement, String> Directeur_Etablissementafficher;

    @FXML
    private TableColumn<Etablissement, Integer> ID_Certificatafficher;

    @FXML
    private TableColumn<Etablissement, Integer> ID_Etablissementafficher;

    @FXML
    private TableColumn<Etablissement, String> Nom_Etablissafficher;

    @FXML
    private TableColumn<Etablissement, Integer> Tel_Etablissementafficher;

    @FXML
    private TableColumn<Etablissement, String> Type_Etablissementafiicher;

    @FXML
    private TableColumn<Etablissement, LocalDate> Date_Fondationafficher;

    @FXML
    private TableView<Etablissement> table;

    @FXML
    private void initialize() {
        // Configuration des colonnes de la table
        configureTableColumns();

        // Charger les données dans la table
        loadData();
    }


    private void configureTableColumns() {
        // Créer une colonne d'action avec des boutons
        TableColumn<Etablissement, Void> actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");
            private final Button editButton = new Button("Modifier");

            {
                // Action à effectuer lors du clic sur le bouton Supprimer
                deleteButton.setOnAction(event -> {
                    Etablissement etablissement = getTableView().getItems().get(getIndex());
                    // Supprimer l'établissement sélectionné de la base de données
                    try {
                        es.deleteSchool(etablissement.getID_Etablissement());
                        // Mettre à jour la TableView pour refléter le changement dans les données
                        getTableView().getItems().remove(etablissement);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Gérer l'erreur, par exemple, afficher un message d'erreur à l'utilisateur
                    }
                });

                // Action à effectuer lors du clic sur le bouton Modifier
                editButton.setOnAction(event -> {
                    Etablissement etablissement = getTableView().getItems().get(getIndex());
                    // Appeler une méthode pour gérer la modification de l'établissement
                    handleEditEtablissement(etablissement);
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
        table.getColumns().add(actionCol);

        // Configurer les autres colonnes
        ID_Etablissementafficher.setCellValueFactory(new PropertyValueFactory<>("ID_Etablissement"));
        Nom_Etablissafficher.setCellValueFactory(new PropertyValueFactory<>("Nom_Etablissement"));
        Adresse_Etablissementafficher.setCellValueFactory(new PropertyValueFactory<>("Adresse_Etablissement"));
        Type_Etablissementafiicher.setCellValueFactory(new PropertyValueFactory<>("Type_Etablissement"));
        Tel_Etablissementafficher.setCellValueFactory(new PropertyValueFactory<>("Tel_Etablissement"));
        Directeur_Etablissementafficher.setCellValueFactory(new PropertyValueFactory<>("Directeur_Etablissement"));
        Date_Fondationafficher.setCellValueFactory(new PropertyValueFactory<>("Date_Fondation"));
        ID_Certificatafficher.setCellValueFactory(new PropertyValueFactory<>("ID_Certificat"));
    }

    private void loadData() {
        try {
            List<Etablissement> etablissements = es.getAll();
            table.getItems().setAll(etablissements);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour gérer la modification d'un établissement
    private void handleEditEtablissement(Etablissement etablissement) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateEtabliss.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la vue chargée
            UpdateEtabliss controller = loader.getController();

            // Passer les données de l'établissement sélectionné au contrôleur
            controller.setSchoolData(etablissement);

            // Créer une nouvelle scène avec le fichier FXML chargé
            Scene scene = new Scene(root);

            // Obtenir la fenêtre principale
            Stage stage = (Stage) table.getScene().getWindow();

            // Afficher la nouvelle scène dans la fenêtre principale
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
