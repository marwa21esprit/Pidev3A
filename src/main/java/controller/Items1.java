package controller;

import Services.ServiceFormation;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Items1 implements Initializable {

    private ServiceFormation ft = new ServiceFormation();

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
    private Label id_niveau_label;

    @FXML
    private Label id_tuteur_label;

    @FXML
    private Label lien_label;

    @FXML
    private Button modifierBT;

    @FXML
    private Label prix_label;

    @FXML
    private Button supprimerBT;

    @FXML
    private Label titre_label;
    private Formation formation;

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    public Items1() {
    }

    public Items1(ServiceFormation serviceFormation) {
        this.ft = serviceFormation;
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateFormation1.fxml"));
        root = loader.load();
        updateFormation1 updateFormation1Controller = loader.getController();
        updateFormation1Controller.setData(formation);
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    @FXML
    void supprimer(ActionEvent event) throws SQLException {

        ft.deleteFormation(formation.getId_formation());
        //mise a jour de l'affichage
        showFormation1 showFormation1Controller = (showFormation1) formation_aff.getProperties().get("controller");
        showFormation1Controller.formationDisplay();


    }

    public void setData(Formation formation) {
        this.formation = formation;

        id_formation_label.setText(String.valueOf(formation.getId_formation()));
        id_tuteur_label.setText(String.valueOf(formation.getId_tuteur()));
        id_niveau_label.setText(String.valueOf(formation.getId_niveau()));

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