package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import models.Paiement;
import models.Reservation;
import services.servicePaiement;
import services.serviceReservation;

import java.net.URL;
import java.util.ResourceBundle;

public class


CardBack implements Initializable {


    @FXML
    private Label nameE;

    @FXML
    private Label email;

    @FXML
    private Label nb;

    @FXML
    private Label username;



    private Reservation reservation;

    @FXML
    private Label montant;
    @FXML
    private Label heure;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(Reservation reservation, Paiement paiement)
       {this.reservation = reservation;
        nameE.setText(reservation.getNameE());
         nb.setText(String.valueOf(reservation.getNb_places()));
        email.setText(reservation.getEmail());
        username.setText(reservation.getName());
         montant.setText(String.valueOf(paiement.getMontant_t()));
         heure.setText(paiement.getHeure_P());

    }
}





