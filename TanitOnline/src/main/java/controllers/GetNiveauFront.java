package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Niveau;
import services.NiveauServices;

import java.net.URL;
import java.util.ResourceBundle;

public class GetNiveauFront implements Initializable {
    private NiveauServices ns = new NiveauServices();
    @FXML
    private Label certificat_label;

    @FXML
    private Label descP_label;

    @FXML
    private Label dur_label;

    @FXML
    private Label namelevel_label;

    @FXML
    private Label nbformations_label;

    @FXML
    private ImageView niveauIV;

    @FXML
    private Label prer_label;
    private Image image;
    private Niveau niveau;
    public void setData(Niveau niveau)
    {
        this.niveau = niveau;

        namelevel_label.setText(String.valueOf(niveau.getName()));
        prer_label.setText(String.valueOf(niveau.getPrerequis()));
        dur_label.setText(niveau.getDuree());
        descP_label.setText(niveau.getDescription());
        nbformations_label.setText(String.valueOf(niveau.getNbformation()));
        certificat_label.setText(niveau.getCertificat());

        String path = "File:"+niveau.getImage();
        image = new Image(path,125,130,false,true);
        niveauIV.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
