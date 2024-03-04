package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Reservation;
import services.serviceReservation;

public class Stat {
    private final serviceReservation rs = new serviceReservation();


    @FXML
    private PieChart pie;


    @FXML
    public void initialize() {
        pie.getData().clear();

        // Get all reservations
        List<Reservation> reservations = rs.getCardlist();

        // Create a map to store the total number of places for each event name
        Map<String, Integer> eventPlacesMap = new HashMap<>();

        // Iterate over each reservation to aggregate the number of places for each event name
        for (Reservation reservation : reservations) {
            String eventName = reservation.getNameE();
            int places = reservation.getNb_places();

            // Update the total number of places for the event name
            eventPlacesMap.put(eventName, eventPlacesMap.getOrDefault(eventName, 0) + places);
        }

        // Create an observable list to hold PieChart.Data objects
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Iterate over the aggregated event names and places to add data to the pieChartData list
        for (Map.Entry<String, Integer> entry : eventPlacesMap.entrySet()) {
            String eventName = entry.getKey();
            int totalPlaces = entry.getValue();

            // Create PieChart.Data object with the event name as the label and total number of places as the value
            PieChart.Data data = new PieChart.Data(eventName, totalPlaces);

            // Add data to pieChartData list
            pieChartData.add(data);
        }

        // Set the data to the PieChart
        pie.setData(pieChartData);

        // Set the label format to display the actual value of each slice
        for (PieChart.Data data : pieChartData) {
            // Customize the label text to display the value of the slice
            data.setName(data.getName()+"::"+(int)data.getPieValue()); // Append value to the existing label
        }
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
}
