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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Event;
import models.Partner;
import services.PartnerService;

import java.io.IOException;
import java.sql.SQLException;

public class GetPartner {

    private final PartnerService ps = new PartnerService();

    @FXML
    private TableColumn<Partner, Integer> idPartnerA;

    @FXML
    private TableColumn<Partner, String> namePartnerA;

    @FXML
    private TableColumn<Partner, String> typePartnerA;

    @FXML
    private TableColumn<Partner, String> descA;

    @FXML
    private TableColumn<Partner, String> emailA;

    @FXML
    private TableColumn<Partner, Integer> telA;
    @FXML
    private TableView<Partner> tablePA;

    @FXML
    private TableColumn<Partner, Void> supprimer;

    @FXML
    private TableColumn<Partner, Void> modifier;

    public void initialize() {

        idPartnerA.setCellValueFactory(new PropertyValueFactory<>("idPartner"));
        namePartnerA.setCellValueFactory(new PropertyValueFactory<>("namePartner"));
        typePartnerA.setCellValueFactory(new PropertyValueFactory<>("typePartner"));
        descA.setCellValueFactory(new PropertyValueFactory<>("description"));
        emailA.setCellValueFactory(new PropertyValueFactory<>("email"));
        telA.setCellValueFactory(new PropertyValueFactory<>("tel"));
        supprimer.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    Partner selectedPartner = getTableView().getItems().get(getIndex());
                    int partnerId = selectedPartner.getIdPartner();

                    try {
                        ps.delete(partnerId);
                        System.out.println("Partenaire supprime avec l'ID : " + partnerId);
                        tablePA.getItems().remove(selectedPartner);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }


            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(deleteButton);
                    setText(null);
                }
            }

        });
        modifier.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Modifier");


            {
                updateButton.setOnAction(event -> {
                    Partner selectedPartner = getTableView().getItems().get(getIndex());
                    try {
                        ps.update(selectedPartner,selectedPartner.getIdPartner());
                        //System.out.println("Evenement modifie avec succes : " + selectedEvent.getIdEvent());
                        openUpdatePartnerView(event,selectedPartner);  // Appeler la méthode pour ouvrir la vue addEvent.fxml
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(updateButton);
                    setText(null);
                }
            }

        });

        loadTableData();
    }
    private void loadTableData() {
        try {
            ObservableList<Partner> partners = FXCollections.observableArrayList(ps.getAll());
            tablePA.setItems(partners);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    @FXML
    void ajouterPartner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addPartner.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openUpdatePartnerView(ActionEvent event,Partner selectedPartner) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/updatePartner.fxml"));
            root = loader.load();
            UpdatePartner updatePartnerController = loader.getController();
            updatePartnerController.setPartnerData(selectedPartner);
            scene = new Scene(root);
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
