package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Apprenant;
import org.controlsfx.control.Notifications;
import services.ApprenantServices;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddApprenant1 implements Initializable {
    ApprenantServices as = new ApprenantServices();
    @FXML
    private TextField emailTF;

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
    void addEvent(ActionEvent event) {
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
            if (data.path == null || data.path.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une image.");
                return;
            }


            int idNiveau = Integer.parseInt(idLevelTF.getText());
            as.add(new Apprenant(
                    nameTF.getText(),
                    emailTF.getText(),
                    nameFormationTF.getText(),
                    statutTF.getText(),
                    levelNameTF.getText(),
                   data.path,
                    idNiveau
            ));

            btnNotifcationOnAction(event);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors de l'ajout de l'apprenant à la base de données.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur inattendue s'est produite.");
        }

    }

    private void btnNotifcationOnAction(ActionEvent event) {
        Notifications notificationsBuilder = Notifications.create()
                .title("Notifications")
                .text("Learner added successfully")
                .graphic(null)
                .hideAfter(Duration.seconds(6))
                .position(Pos.TOP_RIGHT)
                .onAction(events -> onNotificationAction(event));

        notificationsBuilder.show();
    }

    private void onNotificationAction(ActionEvent event) {
        System.out.println("Notification clicked!");
        // Ajoutez le code que vous souhaitez exécuter lorsqu'une notification est cliquée
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    //BOUTTON BACK (NAVIGATION)
    private Scene scene;
    private Stage primaryStage;
    private Parent root;

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
    void showLevels(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
