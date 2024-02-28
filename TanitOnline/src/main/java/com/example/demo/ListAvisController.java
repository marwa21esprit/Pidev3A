package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import models.Niveau;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ListAvisController implements Initializable {


    @FXML
    private javafx.scene.layout.BorderPane BorderPane;
    @FXML
    private javafx.scene.layout.VBox VBox;

    @FXML
    private Button Addbtn;


    @FXML
    private Button StudentsListbtn;


    @FXML
    private Button addLevelbtn;

    @FXML
    private HBox cardLayout;

    @FXML
    private Button feedbackbtn;

    @FXML
    private GridPane menu_gridPane;
    @FXML
    private BorderPane Container;


    @FXML
    private Button ourlevelsbtn;

    @FXML
    private Button showbtn;

    @FXML
    void NavigateToaddEtudiant(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(OperationTable.class.getResource("AddEtudiant.fxml"));
            AnchorPane root = loader.load();




            Container.getChildren().setAll(root);



        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    private ObservableList<Niveau> cardListNiveau = FXCollections.observableArrayList();
    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    public ObservableList<Niveau> ListNiveaux() throws SQLException {
        String sql = "Select * FROM niveau";

        ObservableList<Niveau> listData = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        try {
            ResultSet rs = statement.executeQuery(sql);
            Niveau niveau;
            while (rs.next()){
                niveau = new Niveau(rs.getInt("id"),rs.getString("name"),
                        rs.getString("prerequis"),rs.getString("duree"),rs.getString("certificat"));

                listData.add(niveau);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listData;
    }
    public void niveauDisplayCard() throws SQLException {
        cardListNiveau.clear();
        cardListNiveau.addAll(ListNiveaux());
        int row = 0;
        int column = 0;
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();
        for (int q=0; q < cardListNiveau.size(); q++){
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("CardNiveau.fxml"));
                load.getClass().getResource("CardNiveau.fxml");
                BorderPane pane = load.load();
               CardNiveauController cardN = load.getController();
               cardN.setNiveau(cardListNiveau.get(q));

               if (column == 3){
                   column = 0;
                   row+=1;
               }
                menu_gridPane.add(pane, column++, row);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public void NavigateCardFeedBack(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(OperationTable.class.getResource("CardAvis.fxml"));
            AnchorPane root = loader.load();

            HBox cardBox = loader.load();


            Container.getChildren().setAll(root);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NavigateToAddNiveau(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(OperationTable.class.getResource("AddNiveau.fxml"));
            AnchorPane root = loader.load();




            Container.getChildren().setAll(root);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NavigateToCardNiveau(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(OperationTable.class.getResource("CardNiveau.fxml"));
            AnchorPane root = loader.load();




            Container.getChildren().setAll(root);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
