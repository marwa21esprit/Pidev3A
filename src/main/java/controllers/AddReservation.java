package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Paiement;
import models.Reservation;
import services.servicePaiement;
import services.serviceReservation;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class AddReservation {
    private final serviceReservation sr = new serviceReservation();
    private final servicePaiement sp = new servicePaiement();

    @FXML
    private TextField CVV;

    @FXML
    private TextField name;


    @FXML
    private DatePicker date;


    @FXML
    private TextField numCard;

    @FXML
    private Label errorCard;

    @FXML
    private Label montant;

    @FXML
    private Label errorName;

    @FXML
    private Label errorCvv;

    @FXML
    private Label errorDate;

    @FXML
    private Label confi;
    @FXML
    private AnchorPane res;

    @FXML
    private Label error;
    @FXML
    private TextField nb_pl;



    private int eventId;
    private String eventName;
    private double eventPrice;
    private String imageSrc;

    private int nbrM;


    public void setEventData(int eventId, String eventName, double eventPrice, String imageSrc, int nbrM) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventPrice = eventPrice;
        this.imageSrc = imageSrc;
        this.nbrM=nbrM;
    }

    public void initialize() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            else {
                // Display error message in error label
                error.setText("You should enter a number");

                return null;
            }
        };
        nb_pl.setTextFormatter(new TextFormatter<>(filter));













        UnaryOperator<TextFormatter.Change> filter1 = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            else {
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
        res.setDisable(false);
    }


    @FXML
    public void payer(ActionEvent actionEvent) {
        if (numCard.getText().isEmpty() && CVV.getText().isEmpty() && name.getText().isEmpty() && date.getValue() == null) {
            confi.setText("You should fill all labels");
        } else {
            confi.setText("");

            if (numCard.getText().isEmpty()) {
                errorCard.setText("You should fill the label");
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

                    float amount = Float.parseFloat(montant.getText());
                    Reservation reservation = sr.add(new Reservation(Integer.parseInt(nb_pl.getText())), eventId,eventName,imageSrc);
                    int reservationId = reservation.getId();
                    Paiement paiement = new Paiement(amount);
                    sp.add(paiement, amount, reservationId);
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowReservation.fxml"));
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
        }
    }
            @FXML
            public void pay(ActionEvent actionEvent) {
                if (nb_pl.getText().isEmpty()) {
                    error.setText("You should fill the label");
                } else {
                    int enteredPlaces = Integer.parseInt(nb_pl.getText());
                    int availablePlaces = nbrM; // Assume nbrM holds the total available places
                    int reservedPlaces = sr.getTotalReservedPlacesForEvent(eventId); // Get the total reserved places for the event

                    int remainingPlaces = availablePlaces - reservedPlaces;

                    if (enteredPlaces <= 0) {
                        error.setText("You should reserve at least 1 place");
                    } else if (enteredPlaces > remainingPlaces) {
                        error.setText("The number of places exceeds the available places");
                    } else {
                        // Calculate the total amount
                        float montant_t = (float) (eventPrice * enteredPlaces);
                        montant.setText(String.valueOf(montant_t));
                        error.setVisible(false);
                        res.setDisable(true);
                    }
                }
            }



}
