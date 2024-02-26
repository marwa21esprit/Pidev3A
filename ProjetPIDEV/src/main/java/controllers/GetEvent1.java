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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Event;
import services.EventService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GetEvent1 implements Initializable {
    private final EventService es = new EventService();


    @FXML
    private AnchorPane event_AP;

    @FXML
    private GridPane event_gridPane;

    @FXML
    private ScrollPane event_scrollPane;
    private Event event;

    private ObservableList<Event> eventList;



    public void eventDisplay() throws SQLException {
        eventList = FXCollections.observableList(es.getAll());
        int row = 0;
        int column = 0;
        event_gridPane.getRowConstraints().clear();
        event_gridPane.getColumnConstraints().clear();
        event_gridPane.getChildren().clear();
        for(int i=0;i<eventList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/items.fxml"));
                AnchorPane pane = load.load();
                Items items = load.getController();
                items.setData(eventList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 1)
                {
                    column=0;
                    row+=1;
                }

                event_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            eventDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void ajouterEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addEvent1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
