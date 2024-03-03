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
import services.EventService;
import services.PartnerService;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class UpdateEvent1 {

    private final EventService es = new EventService();
    private final PartnerService ps = new PartnerService();
    @FXML
    private Button backE;

    @FXML
    private DatePicker dateEventDP;

    @FXML
    private TextArea descTF;

    @FXML
    private Button edit;

    @FXML
    private TextField idEstabTF;

    @FXML
    private TextField idEventTF;

    @FXML
    private TextField nameEventTF;

    @FXML
    private Spinner<Integer> nbrMaxS;

    @FXML
    private TextField prixTF;

    @FXML
    private ComboBox<String> partnerCB;
    @FXML
    private AnchorPane mainForm1;
    private Image image;

    @FXML
    private ImageView importIV;

    private int selectedPartnerId;

    public void setEventData(Event event) {
        idEventTF.setText(String.valueOf(event.getIdEvent()));
        try {
            List<String> nomsPartners = ps.getName();
            ObservableList<String> observableNoms = FXCollections.observableArrayList(nomsPartners);
            partnerCB.setItems(observableNoms);
            partnerCB.setValue(ps.getNameByID(event.getIdPartner()));
        } catch (SQLException e) {
            showAlert("Erreur SQL", "Une erreur est survenue lors du chargement des établissements.");
            e.printStackTrace();

        }
        partnerCB.setOnAction(actionEvent -> {
            String selectedPartner = partnerCB.getSelectionModel().getSelectedItem();
            if (selectedPartner != null) {
                // Extraire l'id du partenaire à partir de la chaîne (par exemple, en utilisant une expression régulière)
                String[] parts = selectedPartner.split(" - ");
                if (parts.length == 2) {
                    String idString = parts[0].replace("ID: ", "");
                    selectedPartnerId = Integer.parseInt(idString);
                    // Faire quelque chose avec l'id du partenaire
                    System.out.println("ID du partenaire sélectionné : " + selectedPartnerId);
                }
            }
        });


        idEstabTF.setText(String.valueOf(event.getIdEstab()));
        nameEventTF.setText(event.getNameEvent());
        dateEventDP.setValue(event.getDateEvent().toLocalDate());
        if (nbrMaxS.getValueFactory() == null) {
            nbrMaxS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, event.getNbrMax()));
        } else {
            nbrMaxS.getValueFactory().setValue(event.getNbrMax());
        }
        prixTF.setText(String.valueOf(event.getPrix()));
        descTF.setText(event.getDescription());

        /*String path = event.getImage();
        image = new Image(path, 125, 130, false, true);
        importIV.setImage(image);*/

        String path = event.getImage();
        File file = new File(path);
        image = new Image(file.toURI().toString(), 125, 130, false, true);
        importIV.setImage(image);
    }

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void retourEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void updateEvent(ActionEvent event) {
        try {
            // Validation des champs
            String eventName = nameEventTF.getText().trim();
            if (eventName.isEmpty()) {
                showAlert("Erreur", "Le champ Nom de l'événement ne peut pas être vide.");
                return;
            }

            Date currentDate = new Date(System.currentTimeMillis());
            Date selectedDate = Date.valueOf(dateEventDP.getValue());
            if (selectedDate.before(currentDate)) {
                showAlert("Erreur", "La date de l'événement doit être supérieure à la date actuelle.");
                return;
            }

            int maxParticipants = nbrMaxS.getValue();
            if (maxParticipants <= 0) {
                showAlert("Erreur", "Le nombre maximal de participants doit être supérieur à zéro.");
                return;
            }

            String eventDescription = descTF.getText().trim();
            if (eventDescription.isEmpty()) {
                showAlert("Erreur", "Le champ Description ne peut pas être vide.");
                return;
            }

            // Continuer avec la mise à jour si toutes les validations sont réussies
            Date dateEventC = Date.valueOf(dateEventDP.getValue());


            if (data.path == null || data.path.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une image.");
                return;
            }


            String selectedPartner = partnerCB.getValue();
            double prix = Double.parseDouble(prixTF.getText());

            int id = Integer.parseInt(idEventTF.getText());
            Event updatedEvent = new Event(
                    ps.getIDByNom(selectedPartner),
                    Integer.parseInt(idEstabTF.getText()),
                    eventName,
                    dateEventC,
                    maxParticipants,
                    prix,
                    eventDescription,
                    data.path
            );
            System.out.println("Updated Event: " + updatedEvent);
            System.out.println(data.path);
            // Appeler la méthode d'update de votre service
            es.update(updatedEvent, id);

            System.out.println("Event updated successfully");
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    //BOUTTON IMPORT
    public void importer(ActionEvent event) {
        Event e = new Event();
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File","*png","*jpg"));
        File file = openFile.showOpenDialog(mainForm1.getScene().getWindow());
        if(file != null)
        {
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 125,130,false , true);
            importIV.setImage(image);
            System.out.println("Importer method called. Path: " + data.path);
        }
    }

    public void clear(ActionEvent event) {
        idEstabTF.setText("");
        nameEventTF.setText("");
        dateEventDP.setValue(null);
        nbrMaxS.getValueFactory().setValue(0);
        prixTF.setText("");
        descTF.setText("");
        data.path = "";
        importIV.setImage(null);
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