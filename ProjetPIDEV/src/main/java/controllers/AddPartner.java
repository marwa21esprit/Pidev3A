package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Event;
import models.Partner;
import services.PartnerService;
import test.MainFX;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AddPartner {

    private final PartnerService ps = new PartnerService();
    ObservableList<String> types = FXCollections.observableArrayList("etastablishment","NGO","EdTech");


    @FXML
    private Button add;

    @FXML
    private TextArea descTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField namePartnerTF;

    @FXML
    private TextField telTF;

    @FXML
    private ChoiceBox<String> typePartnerCB;

    @FXML
    public void initialize() {
        typePartnerCB.setValue("default");
        typePartnerCB.setItems(types);

    }
    @FXML
    void addPartner(ActionEvent event) {
        try{
            ps.add(new Partner(
                    namePartnerTF.getText(),
                    typePartnerCB.getValue(),
                    descTF.getText(),
                    emailTF.getText(),
                    Integer.parseInt(telTF.getText())
            ));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    @FXML
    void retourPartner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
