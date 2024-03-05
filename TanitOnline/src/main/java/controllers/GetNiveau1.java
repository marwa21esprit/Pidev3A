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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Niveau;
import services.NiveauServices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GetNiveau1 implements Initializable {
    private final NiveauServices ns = new NiveauServices();

    private Niveau niveau;
    private ObservableList<Niveau> niveauList;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private GridPane niveau_gridPane;
    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    public void niveauDisplay() throws SQLException {
        niveauList = FXCollections.observableList(ns.getAll());
        int row = 0;
        int column = 0;
        niveau_gridPane.getRowConstraints().clear();
        niveau_gridPane.getColumnConstraints().clear();
        niveau_gridPane.getChildren().clear();
        niveau_gridPane.setHgap(20);
        niveau_gridPane.setVgap(20);
        for(int i=0;i<niveauList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/niveau.fxml"));
                AnchorPane pane = load.load();
                Niveaux niveaux = load.getController();
                niveaux.setData(niveauList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 2)
                {
                    column=0;
                    row+=1;
                }

                niveau_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void ajouterNiveau(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddNiveau1.fxml"));
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

    @FXML
    void showLevels(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getNiveau1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            niveauDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
