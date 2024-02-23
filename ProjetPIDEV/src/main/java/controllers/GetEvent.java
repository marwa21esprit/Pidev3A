package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import models.Event;
import services.EventService;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class GetEvent {

    private final EventService es = new EventService();

    @FXML
    private TableColumn<Event, Integer> idEventA;

    @FXML
    private TableColumn<Event, Integer> idEstabA;

    @FXML
    private TableColumn<Event, String> nameEventA;

    @FXML
    private TableColumn<Event, Date> dateEventA;

    @FXML
    private TableColumn<Event, Integer> nbrMax;

    @FXML
    private TableColumn<Event, String> descA;

    @FXML
    private TableView<Event> tableA;

    @FXML
    private Button addEvent;

    @FXML
    private TableColumn<Event, Void> supprimer;

    @FXML
    private TableColumn<Event, Void> modifier;

    @FXML
    private TableColumn<Event, String> imageA;

    public void initialize() {

        //idEventA.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
        idEstabA.setCellValueFactory(new PropertyValueFactory<>("idEstab"));
        nameEventA.setCellValueFactory(new PropertyValueFactory<>("nameEvent"));
        dateEventA.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
        nbrMax.setCellValueFactory(new PropertyValueFactory<>("nbrMax"));
        descA.setCellValueFactory(new PropertyValueFactory<>("description"));
        imageA.setCellValueFactory(new PropertyValueFactory<>("image"));
        supprimer.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    Event selectedEvent = getTableView().getItems().get(getIndex());
                    int eventId = selectedEvent.getIdEvent();

                    try {
                        es.delete(eventId);
                        System.out.println("Evenement supprime avec l'ID : " + eventId);
                        tableA.getItems().remove(selectedEvent);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(deleteButton);
                    setText(null);
                }
            }

        });

        modifier.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Modifier");


            {
                updateButton.setOnAction(event -> {
                    Event selectedEvent = getTableView().getItems().get(getIndex());
                    try {
                        es.update(selectedEvent,selectedEvent.getIdEvent());
                        //System.out.println("Evenement modifie avec succes : " + selectedEvent.getIdEvent());
                        openUpdateEventView(event,selectedEvent);  // Appeler la méthode pour ouvrir la vue addEvent.fxml
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(updateButton);
                    setText(null);
                }
            }

        });

        loadTableData();
    }

    private void loadTableData() {
        try {
            ObservableList<Event> events = FXCollections.observableArrayList(es.getAll());
            tableA.setItems(events);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void ajouterEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addEvent.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void openAddEventView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addEvent.fxml"));
            root = loader.load();
            scene = new Scene(root);
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdateEventView(ActionEvent event,Event selectedEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateEvent.fxml"));
            root = loader.load();
            UpdateEvent updateEventController = loader.getController();
            updateEventController.setEventData(selectedEvent);
            scene = new Scene(root);
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
