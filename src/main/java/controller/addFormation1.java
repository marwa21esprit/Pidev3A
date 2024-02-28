package controller;

import entities.Formation;
import Services.ServiceFormation;
import Services.ServiceTuteur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class addFormation1 {

    private final ServiceFormation ft = new ServiceFormation();
    private final ServiceTuteur st = new ServiceTuteur();

    @FXML
    private Button AddFormation;

    @FXML
    private ChoiceBox<String> categorie;

    @FXML
    private DatePicker date_d;

    @FXML
    private DatePicker date_f;

    @FXML
    private TextArea description;

    @FXML
    private TextField id_formation;

    @FXML
    private TextField id_niveau;

    @FXML
    private ChoiceBox<Integer> id_tuteur;

    @FXML
    private TextField lien;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField prix;

    @FXML
    private TextField titre;



    @FXML
    void initialize() {
        try {
            List<Integer> idsTuteurs = st.getId_tuteur();
            ObservableList<Integer> observableIds = FXCollections.observableArrayList(idsTuteurs);
            id_tuteur.setItems(observableIds);
        } catch (SQLException e) {
            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors du chargement des  formations.");
            e.printStackTrace();
        }

    }

    @FXML
    void ajouter(ActionEvent event) {
        try {
            String selectedCategorie = categorie.getValue();
            if (selectedCategorie == null) {
                showAlert("Erreur", "Catégorie non sélectionnée", "Veuillez sélectionner une catégorie.");
                return;
            }


            if (titre.getText().isEmpty() || description.getText().isEmpty() || date_d.getValue() == null ||
                    date_f.getValue() == null || id_niveau.getText().isEmpty() || id_tuteur.getValue() == null ||
                    lien.getText().isEmpty() || prix.getText().isEmpty()) {
                showAlert("Erreur", "Champs requis", "Veuillez remplir tous les champs.");
                return;
            }


            java.sql.Date selectedDate_d = java.sql.Date.valueOf(date_d.getValue());
            java.sql.Date selectedDate_f = java.sql.Date.valueOf(date_f.getValue());

            Integer idTuteur = Integer.parseInt(String.valueOf(id_tuteur.getValue()));


            ft.addFormation(new Formation(
                    Integer.parseInt(id_niveau.getText()),
                    selectedCategorie,
                    titre.getText(),
                    description.getText(),
                    Date.valueOf(selectedDate_d.toLocalDate()),
                    Date.valueOf(selectedDate_f.toLocalDate()),
                    Float.parseFloat(prix.getText()),
                    lien.getText(),
                    st.getID(id_tuteur.getValue())
            ));


            afficherAlerteInformation("Succès", "Opération réussie", "La formation a été ajoutée avec succès.");
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Erreur de format", "Assurez-vous que les champs numériques sont au bon format.");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur SQL", e.getMessage());
        } catch (Exception e) {
            showAlert("Erreur", "Erreur inattendue", e.getMessage());
        }

    }


    private void afficherAlerteInformation(String succès, String opérationRéussie, String s) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(succès);
        alert.setHeaderText(opérationRéussie);
        alert.setContentText(s);
        alert.showAndWait();
    }
    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    void clear(ActionEvent event) {

        id_formation.setText("");
        id_tuteur.setValue(null);
        id_niveau.setText("");
        categorie.setValue("");
        titre.setText("");
        description.setText("");
        date_d.setValue(null);
        date_f.setValue(null);
        prix.setText("");
        lien.setText("");

    }

    @FXML
    void retour(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            afficherAlerteErreur("Erreur de chargement", "Une erreur est survenue lors du chargement de la vue.");
        }

    }

    private void afficherAlerteErreur(String titre, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();


    }

}
