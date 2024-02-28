package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import models.Avis;

public class CardAvisController  {
    @FXML
    private Label AvisStudent;

    @FXML
    private Label NameStudent;

    @FXML
    private HBox box;

    @FXML
    private ImageView studentimage;
    private String [] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};

    public void setData(Avis avis){
        Image image = new Image(getClass().getResourceAsStream(avis.getImagesrc()));
        studentimage.setImage(image);
        NameStudent.setText(avis.getName());
        AvisStudent.setText(avis.getAvis());
        box.setStyle("-fx-background-color: "+ Color.web(colors[(int)(Math.random()*colors.length)]));
    }

}
