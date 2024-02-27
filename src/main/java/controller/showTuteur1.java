package controller;


import Services.ServiceTuteur;
import entities.Tuteur;
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

public class showTuteur1 implements Initializable {

    private final ServiceTuteur st = new ServiceTuteur();

    @FXML
    private AnchorPane tuteur_AP;

    @FXML
    private GridPane tuteur_gridPane;

    @FXML
    private ScrollPane tuteur_scrollPane;

    private Tuteur tuteur;

    private ObservableList<Tuteur> tuteurList;

    public void tuteurDisplay() throws SQLException {
        tuteurList = FXCollections.observableList(st.getAll());
        int row = 0;
        int column = 0;
        tuteur_gridPane.getRowConstraints().clear();
        tuteur_gridPane.getColumnConstraints().clear();
        tuteur_gridPane.getChildren().clear();
        for(int i=0;i<tuteurList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items.fxml"));
                AnchorPane pane = load.load();
                Items items = load.getController();
                items.setData(tuteurList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 1)
                {
                    column=0;
                    row+=1;
                }

                tuteur_gridPane.add(pane,column++,row);

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
    @FXML
    void ajouterTuteur(ActionEvent event) {

        loadAddTuteurInterface();

    }

    private void loadAddTuteurInterface() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addTuteur1.fxml"));
            Parent root = loader.load();


            Scene scene = new Scene(root);

            // Récupérer la fenêtre actuelle et la modifier pour afficher la nouvelle scène
            Stage stage = (Stage) tuteur_scrollPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
