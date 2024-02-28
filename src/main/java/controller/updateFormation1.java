package controller;

import Services.ServiceFormation;
import Services.ServiceTuteur;
import entities.Formation;
import entities.Tuteur;
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
import java.time.LocalDate;
import java.util.List;

public class updateFormation1 {

    private final ServiceFormation ft = new ServiceFormation();
    private final ServiceTuteur st = new ServiceTuteur();

    @FXML
    private ChoiceBox<String> categorie1;

    @FXML
    private DatePicker date_d1;

    @FXML
    private DatePicker date_f1;

    @FXML
    private TextArea description1;

    @FXML
    private TextField id_formation1;

    @FXML
    private TextField id_niveau1;

    @FXML
    private ChoiceBox<Integer> id_tuteur1;

    @FXML
    private TextField lien1;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField prix1;

    @FXML
    private TextField titre1;

    @FXML
    private Button updateFormation;

    @FXML
    void initialize() {
        try {
            List<Integer> idsTuteurs = st.getId_tuteur();
            ObservableList<Integer> observableIds = FXCollections.observableArrayList(idsTuteurs);
            id_tuteur1.setItems(observableIds);
        } catch (SQLException e) {
            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors du chargement des  formations.");
            e.printStackTrace();
        }

    }


    @FXML
    void modifier(ActionEvent event) {

        try {

            LocalDate selectedStartDate = date_d1.getValue();
            LocalDate selectedEndDate = date_f1.getValue();

            String selectedCategorie = categorie1.getValue();
            if (selectedCategorie == null) {
                showAlert("Erreur", "Catégorie non sélectionnée", "Veuillez sélectionner une catégorie.");
                return;
            }

            Integer selectedId = id_tuteur1.getValue();
            if (selectedId == null) {
                showAlert("Erreur", "ID non sélectionnée", "Veuillez sélectionner l'id.");
                return;
            }

            int id_formation = Integer.parseInt(id_formation1.getText());
            int idTuteur = st.getID(id_tuteur1.getValue());
            int id_niveau = Integer.parseInt(id_niveau1.getText());

            Formation updatedFormation = new Formation(
                    id_niveau,
                    selectedCategorie,
                    titre1.getText(),
                    description1.getText(),
                    selectedStartDate,
                    selectedEndDate,
                    Float.parseFloat(prix1.getText()),
                    lien1.getText()
            );

            ft.updateFormation(updatedFormation, id_formation1);


            afficherAlerteInformation("Mise à jour réussie", "Le tuteur a été mis à jour avec succès.");
        } catch (NumberFormatException e) {

            afficherAlerteErreur("Erreur de format", "Veuillez entrer des valeurs valides.");
            e.printStackTrace();
        } catch (SQLException e) {

            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors de la mise à jour de formation. Veuillez réessayer.");
            e.printStackTrace();
        } catch (Exception e) {

            afficherAlerteErreur("Erreur", "Une erreur est survenue lors de la mise à jour de formation. Veuillez réessayer.");
            e.printStackTrace();
        }
    }

    private void afficherAlerteInformation(String succès, String opérationRéussie) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(succès);
        alert.setHeaderText(opérationRéussie);
        alert.showAndWait();
    }

    private void afficherAlerteErreur(String titre, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
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

        id_formation1.setText("");
        id_tuteur1.setValue(null);
        id_niveau1.setText("");
        categorie1.setValue("");
        titre1.setText("");
        description1.setText("");
        date_d1.setValue(null);
        date_f1.setValue(null);
        prix1.setText("");
        lien1.setText("");

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

    public void setData(Formation formation) {

        id_formation1.setText(String.valueOf(formation.getId_formation()));
        id_tuteur1.setValue(formation.getId_tuteur());
        id_niveau1.setText(String.valueOf(formation.getId_niveau()));

        categorie1.setValue(String.valueOf(formation.getCategorie()));


        if (formation.getDate_d() != null) {
            date_d1.setValue(formation.getDate_d().toLocalDate());
        } else {

            date_d1.setValue(null);
        }

        if (formation.getDate_f() != null) {
            date_f1.setValue(formation.getDate_f().toLocalDate());
        } else {

            date_f1.setValue(null);
        }

        prix1.setText(String.valueOf(formation.getPrix()));
        titre1.setText(formation.getTitre());
        description1.setText(formation.getDescription());
        lien1.setText(formation.getLien());

    }
}