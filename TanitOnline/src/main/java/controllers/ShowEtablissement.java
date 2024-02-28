package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import models.Etablissement;
import services.EtablissementServices;

import java.io.IOException;
import java.util.List;

public class ShowEtablissement {

    @FXML
    private GridPane grid;

    private final EtablissementServices es = new EtablissementServices();

    @FXML
    public void initialize() {
        populateGrid();
    }

    private void populateGrid() {
        List<Etablissement> etablissementList = fetchDataFromDatabase();

        int row = 0;
        int col = 0;

        for (Etablissement etablissement : etablissementList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemsfront.fxml"));
                loader.load();
                itemsfront card = loader.getController();
                card.setShowEtablissementController(this);
                card.setData(etablissement);

                grid.add(loader.getRoot(), col, row);

                col++;
                if (col == 4) {
                    col = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Etablissement> fetchDataFromDatabase() {
        return es.getEtablissementList();
    }
}
