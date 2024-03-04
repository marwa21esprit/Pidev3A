package controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Paiement;
import models.Reservation;
import services.serviceReservation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;


public class




ShowReservation {


    @FXML
    private GridPane grid;

    private final serviceReservation sr = new serviceReservation();
    @FXML
    private Hyperlink hyperlink;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TextField searchF;


    @FXML
    public void initialize() {
        populateGrid();
    }



    private void populateGrid() {
        List<Reservation> reservationList = fetchDataFromDatabase();

        int row = 0;
        int col = 0;

        for (Reservation reservation : reservationList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Card.fxml"));
                Parent root = loader.load();
                Card card = loader.getController();
                card.setRoot(root);
                card.setShowReservationController(this);
                card.setData(reservation);
card.setEventPrice(eventPrice,nbrM);
           // card.setEventDataCard(eventId, eventPrice,nbrM);
                // Pass event data to the Card

                grid.add(root, col, row);

                col++;
                if (col == 3) {
                    col = 0;
                    row++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


private List<Reservation> fetchDataFromDatabase() {
        return sr.getCardlist();
        }

public void removeGridItem(Card card) {
        grid.getChildren().remove(card.getRoot());

        // Adjust the row and column indices of the remaining nodes
        int rowIndex = GridPane.getRowIndex(card.getRoot());
        int colIndex = GridPane.getColumnIndex(card.getRoot());

        for (Node node : grid.getChildren()) {
        Integer nodeRowIndex = GridPane.getRowIndex(node);
        Integer nodeColIndex = GridPane.getColumnIndex(node);

        if (nodeRowIndex != null && nodeColIndex != null && nodeRowIndex > rowIndex) {
        // Decrement row index of nodes below the removed item
        GridPane.setRowIndex(node, nodeRowIndex - 1);
        }

        if (nodeColIndex != null && nodeRowIndex == rowIndex && nodeColIndex > colIndex) {
        // Decrement column index of nodes in the same row to the right of the removed item
        GridPane.setColumnIndex(node, nodeColIndex - 1);
        }
        }


        }



    @FXML
    public void event(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetEventFront1.fxml"));
            Scene scene;
            Stage primaryStage;
            Parent root;

            root = loader.load();

            scene = new Scene(root);
            primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void res(ActionEvent actionEvent) {
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        String eventName = searchF.getText().trim(); // Get the event name from the TextField
        List<Reservation> filteredList = filterList(eventName); // Filter the list of reservations

        grid.getChildren().clear(); // Clear the GridPane

        if (filteredList.isEmpty()) {
            // Display an error alert if no events are found
            showAlert(Alert.AlertType.ERROR, "Event Not Found", "No event found with the name: " + eventName);
            searchF.clear();
        } else {
            // Populate the GridPane with the filtered list of reservations
            int row = 0;
            int col = 0;
            for (Reservation reservation : filteredList) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Card.fxml"));
                    Parent root = loader.load();
                    Card card = loader.getController();
                    card.setData(reservation);
                    grid.add(root, col, row);
                    col++;
                    if (col == 1) {
                        col = 0;
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Add event handler for the OK button
        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                // Call a method to repopulate the GridPane
                populateGrid();
            }
        });

        alert.showAndWait();
    }



    private List<Reservation> filterList(String eventName) {
        List<Reservation> reservations = fetchDataFromDatabase();
        List<Reservation> filteredList = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getNameE().equalsIgnoreCase(eventName)) { // Check if the event name matches
                filteredList.add(reservation);
            }
        }

        return filteredList;
    }


    private int eventId;

    private double eventPrice;

    private int nbrM;


    public void setEvent(int eventId,double eventPrice, int nbrM) {
        this.eventId = eventId;
        this.eventPrice = eventPrice;
        this.nbrM=nbrM;
    }

}