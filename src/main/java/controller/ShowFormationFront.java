package controller;

import Services.ServiceFormation;
import entities.Formation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import entities.Tuteur;
import Services.ServiceTuteur;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowFormationFront implements Initializable {

    private ServiceFormation ft = new ServiceFormation();
    private final ServiceTuteur st = new ServiceTuteur();



    @FXML
    private AnchorPane event_AP;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane formation_scrollPane;
    private Formation formation;

    private ObservableList<Formation> formationsList;



    public void formationDisplay() throws SQLException {
        formationsList = FXCollections.observableList(ft.getAll());
        int row = 0;
        int column = 0;
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.getChildren().clear();
        for(int i=0;i<formationsList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/formations.fxml"));
                AnchorPane pane = load.load();
                Formations formationsCard = load.getController();
                formationsCard.setData(formationsList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 1)
                {
                    column=0;
                    row+=1;
                }

                grid.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            formationDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ajouterFormation(ActionEvent event) {



    }


}

