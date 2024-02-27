package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import models.Event;
import models.Partner;
import services.PartnerService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class AddPartner1 {

    private  PartnerService ps = new PartnerService();
    ObservableList<String> types = FXCollections.observableArrayList("etastablishment","NGO","EdTech");


    @FXML
    private TextArea descTF;

    @FXML
    private TextField emailTF;

    @FXML
    private ImageView importIV;

    @FXML
    private Button importerBT;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField namePartnerTF;

    @FXML
    private TextField telTF;

    private Image image;

    @FXML
    private ComboBox<String> typePartnerCB;
    @FXML
    public void initialize() {
        typePartnerCB.setValue("default");
        typePartnerCB.setItems(types);

    }

    @FXML
    void addPartner(ActionEvent event) {
        try{
            // Validation des champs
            String eventName = namePartnerTF.getText().trim();
            if (eventName.isEmpty()) {
                showAlert("Erreur", "Le champ Nom du partnaire ne peut pas être vide.");
                return;
            }

            String eventType = typePartnerCB.getValue();
            if (eventType == null || eventType.isEmpty() || eventType.equals("default")) {
                showAlert("Erreur", "Veuillez sélectionner un type de partenaire valide.");
                return;
            }


            String email = emailTF.getText().trim();
            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                showAlert("Erreur", "Le champ Email n'est pas dans un format valide.");
                return;
            }
            if (email.isEmpty()) {
                showAlert("Erreur", "Le champ Description ne peut pas être vide.");
                return;
            }

            int tel = Integer.parseInt(telTF.getText());

            String telText = telTF.getText().trim();
            if (!isValidPhoneNumber(telText)) {
                showAlert("Erreur", "Le champ Téléphone n'est pas dans un format valide.");
                return;
            }


            if (data.path1 == null || data.path1.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une image.");
                return;
            }

            String partnerDescription = descTF.getText().trim();
            if (partnerDescription.isEmpty()) {
                showAlert("Erreur", "Le champ Description ne peut pas être vide.");
                return;
            }
            Partner partner = new Partner(
                    eventName,
                    eventType,
                    partnerDescription,
                    email,
                    tel,
                    data.path1
            );


            // Appeler la méthode d'update de votre service
            ps.add(partner);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur de format du numéro d'établissement.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors de la mise à jour de l'événement dans la base de données.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur inattendue s'est produite.");
        }

    }


    private boolean isValidPhoneNumber(String phoneNumber) {
        // Expression régulière pour un numéro de téléphone avec exactement 8 chiffres
        String phoneRegex = "^[0-9]{8}$";
        return phoneNumber.matches(phoneRegex);
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void clear(ActionEvent event) {
        namePartnerTF.setText("");
        typePartnerCB.setValue("default");
        emailTF.setText("");
        telTF.setText("");
        descTF.setText("");
        data.path1 = "";
        importIV.setImage(null);
    }

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void importer(ActionEvent event) {
        Event e = new Event();
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File","*png","*jpg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());
        if(file != null)
        {
            data.path1 = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 125,130,false , true);
            importIV.setImage(image);
        }

    }

    @FXML
    void retourEvent(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showEvents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showPartners(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
