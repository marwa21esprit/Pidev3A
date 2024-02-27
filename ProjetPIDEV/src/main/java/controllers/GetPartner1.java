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
import models.Event;
import models.Partner;
import services.EventService;
import services.PartnerService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GetPartner1 implements Initializable {
    private final PartnerService ps = new PartnerService();

    private Partner parter;
    private ObservableList<Partner> partnerList;
    @FXML
    private AnchorPane mainForm;

    @FXML
    private GridPane partner_gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            partnerDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void partnerDisplay() throws SQLException {
        partnerList = FXCollections.observableList(ps.getAll());
        int row = 0;
        int column = 0;
        partner_gridPane.getRowConstraints().clear();
        partner_gridPane.getColumnConstraints().clear();
        partner_gridPane.getChildren().clear();
        partner_gridPane.setHgap(20);
        partner_gridPane.setVgap(20);
        for(int i=0;i<partnerList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/partners.fxml"));
                AnchorPane pane = load.load();
                Partners partners = load.getController();
                partners.setData(partnerList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 4)
                {
                    column=0;
                    row+=1;
                }

                partner_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    public void showPartners(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showEvents(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void ajouterPartner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addPartner1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
