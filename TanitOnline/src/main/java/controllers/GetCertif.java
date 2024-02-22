package controllers;

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
import models.Certificat;
import services.CertficatServices;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class GetCertif {

    private final CertficatServices cs = new CertficatServices();

    @FXML
    private TableColumn<Certificat, LocalDate> Date_Obtentionafficher;

    @FXML
    private TableColumn<Certificat, String> Domaineafficher;

    @FXML
    private TableColumn<Certificat, Integer> ID_Certificatafficher;

    @FXML
    private TableColumn<Certificat, Integer> ID_Etablissementafficher;

    @FXML
    private TableColumn<Certificat, String> Niveauafiicher;

    @FXML
    private TableColumn<Certificat, String> Nom_Certificatafficher;

    @FXML
    private TableView<Certificat> table;

    @FXML
    private void initialize() {
        // Configuration des colonnes de la table
        configureTableColumns();

        // Charger les données dans la table
        loadData();
    }

    private void configureTableColumns() {
        // Créer une colonne d'action avec des boutons
        TableColumn<Certificat, Void> actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");
            private final Button editButton = new Button("Modifier");

            {
                // Action à effectuer lors du clic sur le bouton Supprimer
                deleteButton.setOnAction(event -> {
                    Certificat certificat = getTableView().getItems().get(getIndex());
                    // Supprimer le certificat sélectionné de la base de données
                    try {
                        cs.deleteCertificate(certificat.getID_Certificat());
                        // Mettre à jour la TableView pour refléter le changement dans les données
                        getTableView().getItems().remove(certificat);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Gérer l'erreur, par exemple, afficher un message d'erreur à l'utilisateur
                    }
                });

                // Action à effectuer lors du clic sur le bouton Modifier
                editButton.setOnAction(event -> {
                    Certificat certificat = getTableView().getItems().get(getIndex());
                    // Appeler une méthode pour gérer l'édition du certificat
                    handleEditCertificat(certificat);
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
        ID_Certificatafficher.setCellValueFactory(new PropertyValueFactory<>("ID_Certificat"));
        Nom_Certificatafficher.setCellValueFactory(new PropertyValueFactory<>("Nom_Certificat"));
        Domaineafficher.setCellValueFactory(new PropertyValueFactory<>("Domaine_Certificat"));
        Niveauafiicher.setCellValueFactory(new PropertyValueFactory<>("Niveau"));
        Date_Obtentionafficher.setCellValueFactory(new PropertyValueFactory<>("Date_Obtention_Certificat"));
        ID_Etablissementafficher.setCellValueFactory(new PropertyValueFactory<>("ID_Etablissement"));
    }

    private void loadData() {
        try {
            List<Certificat> certificats = cs.getAll();
            table.getItems().setAll(certificats);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour gérer l'édition d'un certificat
    private void handleEditCertificat(Certificat certificat) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCertif.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la vue chargée
            UpdateCertif controller = loader.getController();

            // Passer les données du certificat sélectionné au contrôleur
            controller.setCertificateData(certificat);

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
