package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Paiement;
import models.Reservation;
import services.servicePaiement;
import services.serviceReservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;

public class ShowBack {

    @FXML
    private GridPane gridB;

    private final serviceReservation sr = new serviceReservation();
    private final servicePaiement sp = new servicePaiement();
    @FXML
    private TextField searchF;

    @FXML
    public void initialize() {
        populateGridPane();
    }

    private void populateGridPane() {
        // Fetch data from your service or database
        List<Reservation> reservationList = fetchDataFromDatabase();
        int row = 0;
        int col = 0;

        for (Reservation reservation : reservationList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardBack.fxml"));

                Parent root = loader.load();

                CardBack card = loader.getController();
                Paiement paiement = sp.getPaiement(reservation.getId());

                card.setData(reservation, paiement);

                gridB.add(root, col, row);

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

    private List<Reservation> fetchDataFromDatabase() {
        return sr.getlistBack();
    }

    @FXML
    public void showEvents(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent1.fxml"));
            Scene scene;
            Stage primaryStage;
            Parent root;

            root = loader.load();

            scene = new Scene(root);
            primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void resBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowBack.fxml"));
            Scene scene;
            Stage primaryStage;
            Parent root;

            root = loader.load();

            scene = new Scene(root);
            primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void showPartners(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner1.fxml"));
            Scene scene;
            Stage primaryStage;
            Parent root;

            root = loader.load();

            scene = new Scene(root);
            primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void stat(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stat.fxml"));
            Scene scene;
            Stage primaryStage;
            Parent root;

            root = loader.load();

            scene = new Scene(root);
            primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        String eventName = searchF.getText().trim(); // Get the event name from the TextField
        List<Reservation> filteredList = filterList(eventName); // Filter the list of reservations

        gridB.getChildren().clear(); // Clear the GridPane

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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardBack.fxml"));
                    Parent root = loader.load();
                    CardBack card = loader.getController();
                    Paiement paiement = sp.getPaiement(reservation.getId());
                    card.setData(reservation, paiement);
                    gridB.add(root, col, row);
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
                populateGridPane();
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
}

