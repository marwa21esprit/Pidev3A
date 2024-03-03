package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Event;
import models.Partner;
import services.PartnerService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class UpdatePartner {


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
    private TextField idPartnerTF;

    @FXML
    private TextField telTF;

    @FXML
    private ChoiceBox<String> typePartnerCB;

    @FXML
    public void initialize() {
        typePartnerCB.setValue("default");
        typePartnerCB.setItems(types);

    }
    public void setPartnerData(Partner partner) {
        idPartnerTF.setText(String.valueOf(partner.getIdPartner()));
        namePartnerTF.setText(partner.getNamePartner());
        typePartnerCB.setValue(partner.getTypePartner());
        descTF.setText(partner.getDescription());
        emailTF.setText(partner.getEmail());
        telTF.setText(String.valueOf(partner.getTel()));
    }

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void retourEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    void updateEvent(ActionEvent event) {
        try{
            int id = Integer.parseInt(idPartnerTF.getText());
            Partner updatedPartner = new Partner(
                    namePartnerTF.getText(),
                    typePartnerCB.getValue(),
                    descTF.getText(),
                    emailTF.getText(),
                    Integer.parseInt(telTF.getText())
            );

            System.out.println("Updated Event: " + updatedPartner);

            // Appeler la méthode d'update de votre service
            ps.update(updatedPartner,id);

            System.out.println("Event updated successfully");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
