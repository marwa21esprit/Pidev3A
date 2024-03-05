/*package controller;

import Services.ServiceTuteur;
import entities.Tuteur;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ItemsFront implements Initializable {

    private ServiceTuteur st = new ServiceTuteur();

    @FXML
    private Label Nom_label;

    @FXML
    private Label date_naisc_label;

    @FXML
    private AnchorPane tuteur_aff;

    @FXML
    private Label tlf_label;


    @FXML
    private Label prenom_label;

    @FXML
    private Label profession_label;

    @FXML
    private Label email_label;

    @FXML
    private ImageView tuteurIV;

    @FXML
    private Label tuteur_label;

    private Tuteur tuteur;
    private Image image;


    public ItemsFront() {
    }

    public ItemsFront(ServiceTuteur serviceTuteur) {
        this.st = serviceTuteur;
    }
    private ShowTuteurFront ShowTuteurFrontController;





    public void setShowTuteurFrontController(ShowTuteurFront controller) {

        this.ShowTuteurFrontController = controller;
    }

    public void setData(Tuteur tuteur) {
        this.tuteur = tuteur;

        tuteur_label.setText(String.valueOf(tuteur.getId_tuteur()));
        Nom_label.setText(tuteur.getNom());
        prenom_label.setText(tuteur.getPrenom());
        tlf_label.setText(String.valueOf(tuteur.getTlf()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(tuteur.getDate_naisc());
        date_naisc_label.setText(formattedDate);

        profession_label.setText(String.valueOf(tuteur.getProfession()));
        email_label.setText(String.valueOf(tuteur.getEmail()));

        String path = "File:" + tuteur.getImage();
        image = new Image(path, 125, 130, false, true);
        tuteurIV.setImage(image);


    }


    private Scene scene;
    private Stage primaryStage;
    private Parent root;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            List<Tuteur> tuteurs = st.getAll();
            if (!tuteurs.isEmpty()) {
                Tuteur pe = tuteurs.get(0);
                tuteur_label.setText(String.valueOf(pe.getId_tuteur()));
                Nom_label.setText(pe.getNom());
                prenom_label.setText(pe.getPrenom());
                tlf_label.setText(String.valueOf(pe.getTlf()));
                date_naisc_label.setText(pe.getDate_naisc().toString());
                profession_label.setText(pe.getProfession());
                email_label.setText(pe.getEmail());

                File imageFile = new File(pe.getImage());
                Image image = new Image(imageFile.toURI().toString());
                tuteurIV.setImage(image);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public  Parent getRoot() {
        return root;
    }

}*/