package com.example.demo;

import Services.ApprenantServices;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Apprenant;
import util.DbConnect;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ApprenantController {

    ApprenantServices as=new ApprenantServices();


    @FXML
    private TableView<Apprenant> table_view;
    @FXML
    private TableColumn<Apprenant, String> col_certificats;

    @FXML
    private TableColumn<Apprenant, String> col_email;

    @FXML
    private TableColumn<Apprenant, String> col_formation;

    @FXML
    private TableColumn<Apprenant, Integer> col_id;

    @FXML
    private TableColumn<Apprenant, String> col_name;

    @FXML
    private TableColumn<Apprenant, String> col_statut;

    private String[] comboStatut = {"Payé", "Non Payé"};
    Apprenant apprenant;
    public void comboBox(){
        List<Apprenant> list = new ArrayList<>();

        for(String data: comboStatut){

            list.add(apprenant);

        }

        ObservableList apprenantList = FXCollections.observableArrayList(list);

        statutTF.setItems(apprenantList);

    }
    public void textfieldDesign(){

        if(idTF.isFocused()){

            idTF.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            nameTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            emailTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            statutTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            formationTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            certificatsTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");

        }else if(nameTF.isFocused()){

            idTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            nameTF.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            emailTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            statutTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            formationTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            certificatsTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");

        }else if(emailTF.isFocused()){

            idTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            nameTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            emailTF.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            statutTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            formationTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            certificatsTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");

        }else if(statutTF.isFocused()){

            idTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            nameTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            emailTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            statutTF.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            formationTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            certificatsTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");

        }else if(formationTF.isFocused()){

            idTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            nameTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            emailTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            statutTF.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            formationTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            certificatsTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
        }else if(certificatsTF.isFocused()){

            idTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            nameTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            emailTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            statutTF.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            formationTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            certificatsTF.setStyle("-fx-border-width:1px; -fx-background-color:transparent");

        }

    }



    public void defaultId(){

        idTF.setStyle("-fx-border-width:2px; -fx-background-color:#fff");

    }

    @FXML
    private Button delete;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField formationTF;

    @FXML
    private TextField idTF;

    @FXML
    private TextField nameTF;
    @FXML
    private TextField certificatsTF;

    @FXML
    private ComboBox<?> statutTF;


    @FXML
    private Button insert;



    @FXML
    private Button update;
    @FXML
    void Delete(ActionEvent event) throws SQLException {
        try {
            as.delete(String.valueOf(3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table_view.setItems((ObservableList<Apprenant>) apprenant);}}




     /*
    }
    @FXML
    void Update(ActionEvent event) throws SQLException {
        as.update(new Apprenant(nameTF.getText(),emailTF.getText(),statutTF.getTypeSelector(),formationTF.getText(),certificatsTF.getText()));
        table_view.setItems((ObservableList<Apprenant>) apprenant);
    @FXML
    void Insert(ActionEvent event) throws SQLException {
        as.add(new Apprenant(nameTF.getText(),emailTF.getText(),statutTF.getTypeSelector(),formationTF.getText(),certificatsTF.getText()));
        table_view.setItems((ObservableList<Apprenant>) apprenant);
    }

    @FXML
    void comboStatut(ActionEvent event) {

    }
private ObservableList<Apprenant> ListApprenant;

    public void initialize()  {

        try {
            ListApprenant = FXCollections.observableArrayList(as.getAll());

            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

            col_formation.setCellValueFactory(new PropertyValueFactory<>("formationEtudies"));

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

            col_certificats.setCellValueFactory(new PropertyValueFactory<>("certificats"));

            col_statut.setCellValueFactory(new PropertyValueFactory<>("statutNiveau"));

            showApprenant();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void showApprenant() throws SQLException {
        ObservableList<Apprenant> showList = FXCollections.observableArrayList(as.getAll());

        table_view.setItems(showList);



}}

      */

