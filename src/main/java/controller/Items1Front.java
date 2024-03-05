package controller;

import Services.ServiceFormation;
import Services.ServiceTuteur;
import entities.Formation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Items1Front implements Initializable {

    private ServiceFormation ft = new ServiceFormation();
    private ServiceTuteur st = new ServiceTuteur();

    @FXML
    private Label categorie_label;

    @FXML
    private Label date_d_label;

    @FXML
    private Label date_f_label;

    @FXML
    private Label description_label;

    @FXML
    private AnchorPane formation_aff;

    @FXML
    private Label id_formation_label;

    @FXML
    private Label nom_niveau_label;

    @FXML
    private Label id_tuteur_label;

    @FXML
    private Label lien_label;

    @FXML
    private Label prix_label;

    @FXML
    private Label titre_label;
    private Formation formation;

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    public Items1Front() {
    }

    public Items1Front(ServiceFormation serviceFormation) {
        this.ft = serviceFormation;
    }
    public Items1Front(ServiceTuteur serviceTuteur) {
        this.st = serviceTuteur;
    }




    public void setData(Formation formation) {
        this.formation = formation;

        id_formation_label.setText(String.valueOf(formation.getId_formation()));
        id_tuteur_label.setText(String.valueOf(formation.getId_tuteur()));
        nom_niveau_label.setText(String.valueOf(formation.getDescription()));

        categorie_label.setText(formation.getCategorie());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(formation.getDate_d());
        date_d_label.setText(formattedDate);

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate1 = dateFormat1.format(formation.getDate_f());
        date_f_label.setText(formattedDate1);

        prix_label.setText(String.valueOf(formation.getPrix()));

        titre_label.setText(String.valueOf(formation.getTitre()));
        description_label.setText(String.valueOf(formation.getDescription()));



    }




    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}