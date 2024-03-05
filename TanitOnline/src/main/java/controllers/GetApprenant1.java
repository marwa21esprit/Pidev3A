package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Apprenant;
import services.ApprenantServices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GetApprenant1 implements Initializable {
    private final ApprenantServices as = new ApprenantServices();
    @FXML
    private AnchorPane learner_AP;

    @FXML
    private GridPane learner_gridPane;

    @FXML
    private ScrollPane learner_scrollPane;
    private Apprenant apprenant;

    private ObservableList<Apprenant> apprenantList;

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void ajouterApprenant(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddApprenant1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void showLearners(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getApprenant1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void showLevels(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getNiveau1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void eventDisplay() throws SQLException {
        apprenantList = FXCollections.observableList(as.getAll());
        int row = 0;
        int column = 0;
        learner_gridPane.getRowConstraints().clear();
        learner_gridPane.getColumnConstraints().clear();
        learner_gridPane.getChildren().clear();
        for(int i=0;i<apprenantList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Items.fxml"));
                AnchorPane pane = load.load();
                Items items = load.getController();
                items.setData(apprenantList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 1)
                {
                    column=0;
                    row+=1;
                }

                learner_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            eventDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
