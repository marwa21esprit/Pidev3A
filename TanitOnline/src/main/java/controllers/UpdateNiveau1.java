package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Niveau;
import services.NiveauServices;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

public class UpdateNiveau1 {
    private NiveauServices ns = new NiveauServices();
    @FXML
    private ImageView LevelIV1;

    @FXML
    private TextField certificatTF;

    @FXML
    private Label descP_label;
    @FXML
    private Label certif_label;


    @FXML
    private TextArea descTF;

    @FXML
    private Label dur_label;

    @FXML
    private TextField durationTF;

    @FXML
    private TextField idLevelTF;

    @FXML
    private Button importerBT;
    @FXML
    private TextField emailTF;


    @FXML
    private ImageView levelIV;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField nameLevelTF;

    @FXML
    private Label nameP_label;

    @FXML
    private Label nbTrain_label;
    @FXML
    private TextField nameFormationTF;

    @FXML
    private TextField nbformationsTF;

    @FXML
    private Label prere_label;

    @FXML
    private TextField prerequisTF1;
    private Niveau niveau;
    private Image image;

    @FXML
    void clear(ActionEvent event) {
        idLevelTF.setText("");
        prerequisTF1.setText("");
        nameLevelTF.setText("");
        certificatTF.setText("");
        durationTF.setText("");
        nbformationsTF.setText("");
        descTF.setText("");

    }
    public void setData(Niveau niveau)
    {
        this.niveau = niveau;

        nameP_label.setText(String.valueOf(niveau.getName()));
        prere_label.setText(String.valueOf(niveau.getPrerequis()));
        certif_label.setText(String.valueOf(niveau.getCertificat()));
        descP_label.setText(niveau.getDescription());
        dur_label.setText(String.valueOf(niveau.getDuree()));
        nbTrain_label.setText(String.valueOf(niveau.getNbformation()));

        String path = "File:"+niveau.getImage();
        image = new Image(path,125,130,false,true);
        levelIV.setImage(image);
    }
    public void setNiveauData(Niveau niveau) {
        idLevelTF.setText(String.valueOf(niveau.getId()));
        nameLevelTF.setText(niveau.getName());
        prerequisTF1.setText(niveau.getPrerequis());
        descTF.setText(niveau.getDescription());
        certificatTF.setText(niveau.getCertificat());
        durationTF.setText(niveau.getDuree());
        nbformationsTF.setText(String.valueOf(niveau.getNbformation()));


        String path = "File:"+niveau.getImage();
        image = new Image(path,125,130,false,true);
        LevelIV1.setImage(image);
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void editLevel(ActionEvent event) {
        try {
            // Validation des champs
            String niveauName = nameLevelTF.getText().trim();
            if (niveauName.isEmpty()) {
                showAlert("Erreur", "Le champ Nom du niveau ne peut pas être vide.");
                return;
            }
            String niveauPrerequis = prerequisTF1.getText().trim();
            if (niveauPrerequis.isEmpty()) {
                showAlert("Erreur", "Le champ Prérequis ne peut pas être vide.");
                return;
            }
            String duration = durationTF.getText().trim();
            if (duration.isEmpty()) {
                showAlert("Erreur", "Le champ duree ne peut pas être vide.");
                return;
            }
            int nbformation = Integer.parseInt(nbformationsTF.getText());

            String nbformationText = nbformationsTF.getText().trim();
            if (nbformationText.isEmpty()) {
                showAlert("Erreur", "Le champ nbformations ne peut pas être vide .");
                return;
            }
            String certificat = certificatTF.getText().trim();
            if (certificat.isEmpty()) {
                showAlert("Erreur", "Le champ certificat ne peut pas être vide.");
                return;
            }
            String description = descTF.getText().trim();
            if (description.isEmpty()) {
                showAlert("Erreur", "Le champ description ne peut pas être vide.");
                return;
            }
            if (data.path1 == null || data.path1.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une image.");
                return;
            }
            int idNiveau = Integer.parseInt(idLevelTF.getText());
            Niveau niveau = new Niveau(
                    idNiveau,
                    niveauName,
                    niveauPrerequis,
                    duration,
                    nbformation,
                    certificat,
                    description,
                    data.path1
            );
            // Appeler la méthode d'update de votre service
            ns.update(niveau, idNiveau);

            System.out.println("Niveau updated successfully");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void importer(ActionEvent event) {
        Niveau niveau = new Niveau();
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File","*png","*jpg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());
        if(file != null)
        {
            data.path1 = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 125,130,false , true);
            levelIV.setImage(image);
        }

    }
    private Scene scene;
    private Stage primaryStage;
    private Parent root;


    @FXML
    void retourLevel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getNiveau1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void showLearners(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getApprenant1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void showLevels(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getNiveau1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}
