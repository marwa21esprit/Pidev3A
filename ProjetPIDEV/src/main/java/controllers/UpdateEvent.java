package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Event;
import services.EventService;

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
        try{
            Date dateEventC = Date.valueOf(dateEventDP.getValue());
            int id = Integer.parseInt(idEventTF.getText());
            Event updatedEvent = new Event(
                    Integer.parseInt(idEstabTF.getText()),
                    nameEventTF.getText(),
                    dateEventC,
                    nbrMaxS.getValue(),
                    descTF.getText()
            );

            System.out.println("Updated Event: " + updatedEvent);

            // Appeler la méthode d'update de votre service
            es.update(updatedEvent,id);

            System.out.println("Event updated successfully");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
