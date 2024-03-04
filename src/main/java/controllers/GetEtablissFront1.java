package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Etablissement;
import services.EtablissementServices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GetEtablissFront1 implements Initializable {


    @FXML
    private com.gluonhq.charm.glisten.control.TextField searchByName;


    @FXML
    private GridPane event_gridPane;
    @FXML
    private ImageView notFound;

    @FXML
    private AnchorPane panee;


    private final EtablissementServices es = new EtablissementServices();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchByName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                rechercherEtablissements(newValue);
            }
        });
        try {
            populateGrid();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateGrid() throws SQLException {
        List<Etablissement> etablissementList = fetchDataFromDatabase();
        afficherEtablissements(etablissementList);
    }

    private void afficherEtablissements(List<Etablissement> etablissementList) {
        event_gridPane.getChildren().clear();

        if (etablissementList.isEmpty()) {
            afficherNotFound();
        } else {
            int row = 0;
            int col = 0;
            for (Etablissement etablissement : etablissementList) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemsfront.fxml"));
                    loader.load();
                    itemsfront card = loader.getController();
                    card.setGetEtablissFront1Controller(this);
                    card.setData(etablissement);

                    event_gridPane.add(loader.getRoot(), col, row);
                    col++;
                    if (col == 3) {
                        col = 0;
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }        }
    }



    private List<Etablissement> fetchDataFromDatabase() throws SQLException {
        return es.getAll();
    }

    private void rechercherEtablissements(String query) {
        try {
            List<Etablissement> resultatsRecherche = es.getByPartialName(query);
            if (resultatsRecherche.isEmpty()) {
                afficherNotFound();
            } else {
                afficherEtablissements(resultatsRecherche);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void afficherNotFound() {
        event_gridPane.getChildren().clear();
        event_gridPane.add(notFound, 0, 0);
    }


    public void Schools(ActionEvent actionEvent) {
    }

    public void SchoolsHyper(ActionEvent actionEvent) {
    }

    public void Home(ActionEvent actionEvent) {
    }

    @FXML
    void back(ActionEvent event) {
        loadFXML("/GetCertif1.fxml", event);
    }

    private void loadFXML(String path, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
