package controllers;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Apprenant;
import services.ApprenantServices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Items implements Initializable {
    private  ApprenantServices as = new ApprenantServices();

    @FXML
    private Label email_label;

    @FXML
    private Label idlevel_label;

    @FXML
    private ImageView learnerIV;

    @FXML
    private AnchorPane learner_aff;

    @FXML
    private Label levelname_label;

    @FXML
    private Button modifierBT;

    @FXML
    private Label nameLearner_label;

    @FXML
    private Label satus_label;

    @FXML
    private Button supprimerBT;

    @FXML
    private Label trainingName_label;
    private Image image;
    private Apprenant apprenant;

    public Items(){}
    public Items(ApprenantServices apprenantServices) {
        this.as = apprenantServices;
    }


    public void setData(Apprenant apprenant)
    {
        this.apprenant = apprenant;

        levelname_label.setText(apprenant.getName());
        email_label.setText(apprenant.getEmail());
        trainingName_label.setText(apprenant.getFormationEtudies());
        levelname_label.setText(apprenant.getNiveauName());
        idlevel_label.setText(String.valueOf(apprenant.getIdNiveau()));
        satus_label.setText(apprenant.getStatutNiveau());
        String path = "File:"+apprenant.getImage();
        image = new Image(path,200,170,false,true);
        learnerIV.setImage(image);
    }
    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void modifier(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateApprenant1.fxml"));
        root = loader.load();
        UpdateApprenant1 updateApprenant1Controller = loader.getController();
        updateApprenant1Controller.setApprenantData(apprenant);
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void supprimer(ActionEvent apprenantAction) throws SQLException {
        as.delete(apprenant.getId());
        //mise a jour de l'affichage
        GetApprenant1 getApprenant1Controller = (GetApprenant1) learner_aff.getProperties().get("controller");
        getApprenant1Controller.eventDisplay();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
