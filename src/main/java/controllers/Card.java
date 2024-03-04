package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Event;
import models.Reservation;
import services.servicePaiement;
import services.serviceReservation;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Card implements Initializable {


    @FXML
    private Label nameEvent;

    @FXML
    private ImageView image;

    @FXML
    private Button apply;

    @FXML
    private Spinner<Integer> nb_pl;


    private serviceReservation rs = new serviceReservation();


    private ShowReservation showReservationController;

    private Reservation reservation;
    private int reservationId;
    private int originalNumberOfPlaces;
    private int currentNumberOfPlaces;

    private Parent root;

    private int eventId;
    private double eventPrice;
    private int nbrM;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apply.setVisible(false);

        nb_pl.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentNumberOfPlaces = newValue;
            apply.setVisible(currentNumberOfPlaces != originalNumberOfPlaces);

        });
    }
    public void setEventPrice(double eventPrice , int nbrM) {
        this.eventPrice = eventPrice;
        this.nbrM = nbrM;


    }
    public void setData(Reservation reservation ) {
        this.reservation = reservation;
        this.reservationId = reservation.getId();

        nameEvent.setText(reservation.getNameE());

        String path = "File:"+reservation.getImageSrc();
        Image im = new Image(path, 200, 170, false, true);
        image.setImage(im);

        originalNumberOfPlaces = reservation.getNb_places();

        if (nb_pl.getValueFactory() != null) {
            nb_pl.getValueFactory().setValue(originalNumberOfPlaces);
        } else {
            nb_pl.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, originalNumberOfPlaces));
        }
    }



    public void setShowReservationController(ShowReservation controller) {
        this.showReservationController = controller;
    }


    @FXML
    
    public void apply(ActionEvent actionEvent) {
        try {

            float amount = (float) (currentNumberOfPlaces * eventPrice);

            // Load the Paiement2.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Paiement2.fxml"));
            Parent root = loader.load();
            Paiement2 paiementController = loader.getController();

            // Pass the necessary data to the Paiement2 controller
            paiementController.setData(currentNumberOfPlaces, reservationId, amount);
            paiementController.setCardController(this); // Pass reference to the Card controller

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error occurred while loading Paiement2.fxml: " + e.getMessage());
        }
    }




    @FXML
    public void delete(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Reservation");
        alert.setContentText("Are you sure you want to delete this reservation?");

        ButtonType yesButton = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(yesButton, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            try {

                // Delete the reservation
                rs.delete(reservationId);


                // Remove the grid item
                if (showReservationController != null) {
                    showReservationController.removeGridItem(this);
                }

                // Show success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Delete Reservation successful.");
                successAlert.showAndWait();
            } catch (Exception e) {
                System.err.println("Error occurred while deleting reservation: " + e.getMessage());
            }
        }
    }



    public void setRoot(Parent root) {
        this.root = root;
    }

    public Parent getRoot() {
        return root;
    }



}
