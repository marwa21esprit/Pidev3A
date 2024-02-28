package controller;

import entities.Formation;
import Services.ServiceFormation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class showFormation1 implements Initializable {

    private final ServiceFormation ft = new ServiceFormation();


    @FXML
    private GridPane formation_gridPane;

    @FXML
    private ScrollPane formation_scrollPane;

    @FXML
    private AnchorPane formation_AP;

    private Formation formation;

    private ObservableList<Formation> formationList;

    public void formationDisplay() throws SQLException {
        formationList = FXCollections.observableList(ft.getAll());
        int row = 0;
        int column = 0;
        formation_gridPane.getRowConstraints().clear();
        formation_gridPane.getColumnConstraints().clear();
        formation_gridPane.getChildren().clear();
        for(int i=0;i<formationList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items1.fxml"));
                AnchorPane pane = load.load();
                Items1 items = load.getController();
                items.setData(formationList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 1)
                {
                    column=0;
                    row+=1;
                }

                formation_gridPane.add(pane,column++,row);

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

        loadAddFormationInterface();

    }

    private void loadAddFormationInterface() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addFormation1.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);

            // Récupérer la fenêtre actuelle et la modifier pour afficher la nouvelle scène
            Stage stage = (Stage) formation_scrollPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
