package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Paiement;
import models.Reservation;
import services.servicePaiement;
import services.serviceReservation;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.function.UnaryOperator;

public class Paiement2 {

    private Card cardController; // Reference to Card controller

    private int reservationId;
    private int currentNumberOfPlaces;

    private float montantValue;
    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane paiement;


    @FXML
    private TextField numCard;

    @FXML
    private Label errorCard;

    @FXML
    private Label montant;

    @FXML
    private TextField CVV;

    @FXML
    private Label errorCvv;

    @FXML
    private TextField name;

    @FXML
    private Label errorName;

    @FXML
    private DatePicker date;

    @FXML
    private Label errorDate;

    @FXML
    private Label confi;


    private final serviceReservation sr = new serviceReservation();
    private final servicePaiement sp = new servicePaiement();


    @FXML
    public void initialize(){
        UnaryOperator<TextFormatter.Change> filter1 = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            else {
                // Display error message in error label
                errorCard.setText("You should enter a number");

                return null;
            }
        };

        numCard.setTextFormatter(new TextFormatter<>(filter1));

        UnaryOperator<TextFormatter.Change> filter2 = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            else {
                // Display error message in error label
                errorCvv.setText("You should enter a number");

                return null;
            }
        };

        CVV.setTextFormatter(new TextFormatter<>(filter2));

        UnaryOperator<TextFormatter.Change> Filter3 = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z\\s]*")) { // Allow alphabetic characters and spaces
                return change;
            } else {
                // Display error message if the input contains invalid characters
                errorName.setText("Only alphabetic characters and spaces are allowed");
                return null;
            }
        };

        name.setTextFormatter(new TextFormatter<>(Filter3));
    }

    @FXML
    void payer(ActionEvent event) {


        if (numCard.getText().isEmpty() && CVV.getText().isEmpty() && name.getText().isEmpty() && date.getValue() == null) {
            confi.setText("You should fill all labels");
        } else {
            confi.setText("");

            // Handle other validations for individual fields
            if (numCard.getText().isEmpty()) {
                errorCard.setText("You should fill the label");
                // Reset other error messages
                errorCvv.setText("");
                errorName.setText("");
                errorDate.setText("");
            } else if (CVV.getText().isEmpty()) {
                errorCvv.setText("You should fill the label");
                errorName.setText("");
                errorDate.setText("");
                errorCard.setText("");
            } else if (name.getText().isEmpty()) {
                errorName.setText("You should fill the label");
                errorCvv.setText("");
                errorDate.setText("");
                errorCard.setText("");
            } else if (date.getValue() == null) {
                errorDate.setText("You should fill the label");
                errorCvv.setText("");
                errorName.setText("");
                errorCard.setText("");
            } else {
                try {

                    Reservation updatedReservation = new Reservation();
                    updatedReservation.setId(reservationId);
                    updatedReservation.setNb_places(currentNumberOfPlaces);

                        sr.update(updatedReservation);

                        sp.updatePaiement(reservationId, montantValue);

                    Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

                    sp.updateHeure(reservationId, timestamp);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowReservation.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Your Reservation List");
                    stage.show();

                    } catch (Exception e) {
                        System.err.println("Error occurred while updating reservation: " + e.getMessage());
                    }
                }}
    }


        public void setData(int currentNumberOfPlaces, int reservationId , float v) {
            this.currentNumberOfPlaces = currentNumberOfPlaces;
            this.reservationId = reservationId;
            this.montantValue = v;
            montant.setText(String.valueOf(v));
        }


    public void setCardController(Card card) {
        this.cardController = card;

    }
}
