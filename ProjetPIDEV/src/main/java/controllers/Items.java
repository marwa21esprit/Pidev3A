package controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.EventService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Items implements Initializable {
    private  EventService es = new EventService();

    @FXML
    private Label desc_label;

    @FXML
    private Label estab_label;

    @FXML
    private Label eventDate_label;

    @FXML
    private ImageView eventIV;

    @FXML
    private Label eventName_label;

    @FXML
    private AnchorPane event_aff;

    @FXML
    private Button modifierBT;

    @FXML
    private Label nbrMax_label;

    @FXML
    private Button supprimerBT;

    private Event event;
    private Image image;

    public Items(){}
    public Items(EventService eventService) {
        this.es = eventService;
    }

    public void setData(Event event)
    {
        this.event = event;

        estab_label.setText(String.valueOf(event.getIdEstab()));
        eventName_label.setText(event.getNameEvent());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(event.getDateEvent());
        eventDate_label.setText(formattedDate);

        nbrMax_label.setText(String.valueOf(event.getNbrMax()));
        desc_label.setText(event.getDescription());

        String path = "File:"+event.getImage();
        image = new Image(path,125,130,false,true);
        eventIV.setImage(image);
    }

    private Scene scene;
    private Stage primaryStage;
    private Parent root;


    @FXML
    void modifier(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateEvent1.fxml"));
        root = loader.load();
        UpdateEvent1 updateEventController = loader.getController();
        updateEventController.setEventData(event);
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @FXML
    void supprimer(ActionEvent eventAction) throws SQLException {
        es.delete(event.getIdEvent());
        //mise a jour de l'affichage
        GetEvent1 getEvent1Controller = (GetEvent1) event_aff.getProperties().get("controller");
        getEvent1Controller.eventDisplay();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
