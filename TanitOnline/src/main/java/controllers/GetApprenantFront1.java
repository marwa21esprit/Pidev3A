package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Apprenant;
import services.ApprenantServices;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GetApprenantFront1 implements Initializable {
    private final ApprenantServices as = new ApprenantServices();

    @FXML
    private GridPane Learner_gridPane;
    private Apprenant apprenant;


     private ObservableList<Apprenant> apprenantList;
   public void apprenantDisplay() throws SQLException {
        apprenantList = FXCollections.observableList(as.getAll());
        int row = 0;
        int column = 0;
        Learner_gridPane.getRowConstraints().clear();
        Learner_gridPane.getColumnConstraints().clear();
        Learner_gridPane.getChildren().clear();
        for(int i=0;i<apprenantList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/apprenants.fxml"));
                AnchorPane pane = load.load();
                Apprenants apprenantsCard = load.getController();
                apprenantsCard.setData(apprenantList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 1)
                {
                    column=0;
                    row+=1;
                }

                Learner_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            apprenantDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
