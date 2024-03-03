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
import models.Event;
import services.EventService;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class UpdateEvent {

    private final EventService es = new EventService();
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
    private AnchorPane mainForm1;
    private Image image;

    @FXML
    private ImageView importIV;


    public void setEventData(Event event) {
        idEventTF.setText(String.valueOf(event.getIdEvent()));
        idEstabTF.setText(String.valueOf(event.getIdEstab()));
        nameEventTF.setText(event.getNameEvent());
        dateEventDP.setValue(event.getDateEvent().toLocalDate());
        if (nbrMaxS.getValueFactory() == null) {
            nbrMaxS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, event.getNbrMax()));
        } else {
            nbrMaxS.getValueFactory().setValue(event.getNbrMax());
        }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent.fxml"));
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


            int id = Integer.parseInt(idEventTF.getText());
            Event updatedEvent = new Event(
                    Integer.parseInt(idEstabTF.getText()),
                    eventName,
                    dateEventC,
                    maxParticipants,
                    eventDescription,
                    data.path
            );
            System.out.println("Updated Event: " + updatedEvent);

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

}