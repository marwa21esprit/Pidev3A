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
import test.MainFX;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AddEvent {
    private final EventService es = new EventService();

    @FXML
    private Button ajouter;

    @FXML
    private TextField idEventTF;
    @FXML
    private TextField idEstabTF;
    @FXML
    private TextField nameEventTF;
    @FXML
    private DatePicker dateEventDP;
    @FXML
    private Spinner<Integer> nbrMaxS;
    @FXML
    private TextArea descTF;

    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        nbrMaxS.setValueFactory(valueFactory);
    }
        @FXML
    void addEvent(ActionEvent event) {
        try{
            Date dateEventC = Date.valueOf(dateEventDP.getValue());
            es.add(new Event(
                Integer.parseInt(idEstabTF.getText()),
                nameEventTF.getText(),
                dateEventC,
                nbrMaxS.getValue(),
                descTF.getText()));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
