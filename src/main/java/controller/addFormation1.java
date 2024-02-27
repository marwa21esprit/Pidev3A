package controller;

import entities.Formation;
import Services.ServiceFormation;
import Services.ServiceTuteur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.sql.SQLException;

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
    private TextField description;

    @FXML
    private TextField id_formation;

    @FXML
    private TextField id_niveau;

    @FXML
    private ChoiceBox<String> id_tuteur;

    @FXML
    private TextField lien;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField prix;

    @FXML
    private TextField titre;

    @FXML
    void ajouter(ActionEvent event) {

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
        id_tuteur.setValue("");
        id_niveau.setText("");
       // categorie.setText("");
        titre.setText("");
        description.setText("");
        date_d.setValue(null);
        date_f.setValue(null);
        prix.setText("");
        lien.setText("");

    }

    @FXML
    void retour(ActionEvent event) {

    }

}
