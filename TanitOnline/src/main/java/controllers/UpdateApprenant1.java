package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Apprenant;
import services.ApprenantServices;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateApprenant1 implements Initializable {
    private final ApprenantServices as = new ApprenantServices();


    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    @FXML
    private TextField emailTF;

    @FXML
    private TextField idLearnerTF;

    @FXML
    private TextField idLevelTF;

    @FXML
    private ImageView importIV;

    @FXML
    private Button importerBT;

    @FXML
    private TextField levelNameTF;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField nameFormationTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField statutTF;
    private Image image;

    @FXML
    void clear(ActionEvent event) {
        idLevelTF.setText("");
        nameTF.setText("");
        emailTF.setText("");
        nameFormationTF.setText("");
        statutTF.setText("");
        levelNameTF.setText("");
        data.path = "";
        importIV.setImage(null);

    }
    public void setApprenantData(Apprenant apprenant) {
        idLearnerTF.setText(String.valueOf(apprenant.getId()));
        nameTF.setText(apprenant.getName());
        emailTF.setText(apprenant.getEmail());
        nameFormationTF.setText(apprenant.getFormationEtudies());
        statutTF.setText(apprenant.getStatutNiveau());
        levelNameTF.setText(apprenant.getNiveauName());
        idLevelTF.setText(String.valueOf(apprenant.getIdNiveau()));

        /*String path = event.getImage();
        image = new Image(path, 125, 130, false, true);
        importIV.setImage(image);*/

        String path = apprenant.getImage();
        File file = new File(path);
        image = new Image(file.toURI().toString(), 125, 130, false, true);
        importIV.setImage(image);
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void editLearner(ActionEvent event) {
        try {
            // Validation des champs
            String learnername = nameTF.getText().trim();
            if (learnername.isEmpty()) {
                showAlert("Erreur", "Le champ Nom de l'apprenant ne peut pas être vide.");
                return;
            }

            String email = emailTF.getText().trim();
            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                showAlert("Erreur", "Le champ Email n'est pas dans un format valide.");
                return;
            }
            String trainingname = nameFormationTF.getText().trim();
            if (trainingname.isEmpty()) {
                showAlert("Erreur", "Le champ Nom Formation ne peut pas être vide.");
                return;
            }
            String statut = statutTF.getText().trim();
            if (statut.isEmpty()) {
                showAlert("Erreur", "Le champ statut ne peut pas être vide.");
                return;
            }
            String levelname = levelNameTF.getText().trim();
            if (levelname.isEmpty()) {
                showAlert("Erreur", "Le champ levelName ne peut pas être vide.");
                return;
            }
            String idlevel = idLevelTF.getText().trim();
            if (idlevel.isEmpty()) {
                showAlert("Erreur", "Le champ idlevel ne peut pas être vide.");
                return;
            }

            if (data.path == null || data.path.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une image.");
                return;
            }
            int id = Integer.parseInt(idLearnerTF.getText());

            Apprenant apprenant =new Apprenant( levelname, email,trainingname, statut, levelname,data.path,Integer.parseInt(idLevelTF.getText()));
            System.out.println(apprenant);
           // Appeler la méthode d'update de votre service
            as.update(apprenant, id);
            System.out.println("Apprenant updated successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void importer(ActionEvent event) {
        Apprenant apprenant = new Apprenant();
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File","*png","*jpg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());
        if(file != null)
        {
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 125,130,false , true);
            importIV.setImage(image);
            System.out.println("Importer method called. Path: " + data.path);
        }

    }

    @FXML
    void retourEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getApprenant1.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    }
