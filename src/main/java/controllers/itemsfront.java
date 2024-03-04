package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Etablissement;
import services.EtablissementServices;

public class itemsfront {



    @FXML
    private Hyperlink Adresse_Etablissementafficher;

    @FXML
    private Label Date_Fondationafficher;

    @FXML
    private Label Nb_Certifafficher;

    @FXML
    private Label Directeur_Etablissementafficher;

    @FXML
    private Label Nom_Etablissafficher;

    @FXML
    private ImageView SchoolIV;

    @FXML
    private Label Tel_Etablissementafficher;

    @FXML
    private Label Type_Etablissementafiicher;

    @FXML
    private AnchorPane school_aff;

    private Etablissement etablissement;
    private Image image;

    private Parent root;
    private final EtablissementServices es = new EtablissementServices(); // Initialisation du service
    private GetEtablissFront1 GetEtablissFront1Controller;
    private GetEtablissFront1 getEtablissFront1Controller;


    public void setGetEtablissFront1Controller(GetEtablissFront1 getEtablissFront1Controller) {
        this.getEtablissFront1Controller = getEtablissFront1Controller;
    }



    public void initialize() {
        try {
            List<Etablissement> etablissements = es.getAll();
            if (!etablissements.isEmpty()) {
                Etablissement pe = etablissements.get(0); // Choisissez un établissement pour afficher ses détails
                Adresse_Etablissementafficher.setText(pe.getAdresse_Etablissement());
                Date_Fondationafficher.setText(pe.getDate_Fondation().toString());
                Directeur_Etablissementafficher.setText(pe.getDirecteur_Etablissement());
                Nom_Etablissafficher.setText(pe.getNom_Etablissement());
                Tel_Etablissementafficher.setText(String.valueOf(pe.getTel_Etablissement()));
                Type_Etablissementafiicher.setText(pe.getType_Etablissement());

                File imageFile = new File(pe.getImg_Etablissement());
                Image image = new Image(imageFile.toURI().toString());
                SchoolIV.setImage(image);

                Map<String, Integer> nombreCertificatsParEtablissement = es.getNombreCertificatsParEtablissement();
                int nombreCertificats = nombreCertificatsParEtablissement.getOrDefault(pe.getNom_Etablissement(), 0);
                Nb_Certifafficher.setText(String.valueOf(nombreCertificats));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setData(Etablissement etablissement) {
        this.etablissement = etablissement;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (etablissement.getDate_Fondation() != null) {
            String formattedDate = dateFormat.format(etablissement.getDate_Fondation());
            Date_Fondationafficher.setText(formattedDate);
        } else {
            Date_Fondationafficher.setText("N/A");
        }
        Directeur_Etablissementafficher.setText(etablissement.getDirecteur_Etablissement());
        Adresse_Etablissementafficher.setText(etablissement.getAdresse_Etablissement());
        Nom_Etablissafficher.setText(etablissement.getNom_Etablissement());
        Tel_Etablissementafficher.setText(String.valueOf(etablissement.getTel_Etablissement()));
        Type_Etablissementafiicher.setText(etablissement.getType_Etablissement());

        String path = "File:" + etablissement.getImg_Etablissement();
        image = new Image(path, 125, 130, false, true);
        SchoolIV.setImage(image);

        // afficher le nb de certif dans le label
        try {
            Map<String, Integer> nombreCertificatsParEtablissement = es.getNombreCertificatsParEtablissement();
            int nombreCertificats = nombreCertificatsParEtablissement.getOrDefault(etablissement.getNom_Etablissement(), 0);
            Nb_Certifafficher.setText(String.valueOf(nombreCertificats));
        } catch (SQLException e) {
            e.printStackTrace();
            Nb_Certifafficher.setText("N/A");
        }
    }

    @FXML
    void Adresse_Etabliss(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Map.fxml"));
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

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent getRoot() {
        return root;
    }
}
