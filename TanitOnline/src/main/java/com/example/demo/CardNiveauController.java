package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.Niveau;

import java.net.URL;
import java.util.ResourceBundle;

public class CardNiveauController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private ImageView imageview1;

    @FXML
    private ImageView imageview2;

    @FXML
    private ImageView imageview3;

    @FXML
    private ImageView imageview4;

    @FXML
    private Label niv_certificat;

    @FXML
    private Label niv_duree;

    @FXML
    private Button niv_editbtn;

    @FXML
    private Label niv_name;

    @FXML
    private Label niv_prerequis;

    @FXML
    private Button niv_show_btn;
    private Niveau niveau;
    private Image image;
    public void setNiveau(Niveau niveau){
        this.niveau=niveau;
        niv_name.setText(niveau.getName());
        niv_prerequis.setText(niveau.getPrerequis());
        niv_duree.setText(niveau.getDuree());
        niv_certificat.setText(niveau.getCertificat());



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
