package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import entities.Tuteur;
import Services.ServiceTuteur;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowTuteurFront implements Initializable {
    private final ServiceTuteur st = new ServiceTuteur();


    @FXML
    private AnchorPane event_AP;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane event_scrollPane;
    private Tuteur tuteur;

    private ObservableList<Tuteur> tuteursList;



    public void tuteurDisplay() throws SQLException {
        tuteursList = FXCollections.observableList(st.getAll());
        int row = 0;
        int column = 0;
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.getChildren().clear();
        for(int i=0;i<tuteursList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/tuteurs.fxml"));
                AnchorPane pane = load.load();
                Tuteurs tuteursCard = load.getController();
                tuteursCard.setData(tuteursList.get(i));

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
            tuteurDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
