package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import models.Certificat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import services.*;

public class GetCertif1 {

    @FXML
    private AnchorPane event_AP;

    @FXML
    private GridPane event_gridPane;

    @FXML
    private ScrollPane event_scrollPane;

    @FXML
    private TextField CertifRecherche;

    @FXML
    private ChoiceBox<String> triChoiceBox;
    @FXML
    private BarChart<String, Number> barChart;
    private CertficatServices cs = new CertficatServices();

    @FXML
    void rechercherCertificate(ActionEvent event) {
        String nomCertificat = CertifRecherche.getText();
        if (!nomCertificat.isEmpty()) {
            try {
                List<Certificat> certificats = cs.getByPartialName(nomCertificat);
                if (!certificats.isEmpty()) {
                    afficherCertificats(certificats);
                } else {
                    showAlert("Information", "Aucun certificat trouvé avec ce nom.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "Une erreur est survenue lors de la recherche des certificats.");
            }
        } else {
            showAlert("Error", "Veuillez saisir un nom de certificat pour effectuer la recherche.");
        }
    }

    @FXML
    void triCertificats(ActionEvent event) {
        String choixTri = triChoiceBox.getValue(); // Récupérer le choix de tri depuis le ChoiceBox
        if (choixTri != null) {
            switch (choixTri) {
                case "Trier par nom":
                    trierParNom();
                    break;
                case "Trier par domaine":
                    trierParDomaine();
                    break;
                case "Trier par niveau":
                    trierParNiveau();
                    break;
            }
        } else {
            showAlert("Erreur", "Veuillez sélectionner un critère de tri.");
        }
    }

    @FXML
    void affi(ActionEvent event) {
        loadFXML("/GetEtabliss1.fxml", event);
    }

    @FXML
    void ajouterCertificat(ActionEvent event) {
        loadFXML("/AddCertif1.fxml", event);
    }

    @FXML
    void gestionSchool(ActionEvent event) {
        loadFXML("/GetEtabliss1.fxml", event);    }

    @FXML
    void gestionCertificat(ActionEvent event) {
        loadFXML("/GetCertif1.fxml", event);    }


    @FXML
    public void initialize() {
        try {
            Stat();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        triChoiceBox.setItems(FXCollections.observableArrayList("Trier par nom", "Trier par domaine", "Trier par niveau"));

        // Définissez une option par défaut si nécessaire
        triChoiceBox.setValue(" "); // Notez que je laisse une chaîne vide ici, mais vous pouvez choisir une valeur par défaut différente si nécessaire

        // Vérifiez si une valeur est sélectionnée dans le ChoiceBox avant d'afficher les certificats non triés
        if (triChoiceBox.getValue() != null) {
            afficherCertificatsNonTries();
        }
    }

    private void afficherCertificatsNonTries() {
        try {
            List<Certificat> certificats = cs.getAll(); // Obtenir tous les certificats
            afficherCertificats(certificats); // Afficher les certificats
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors de l'affichage des certificats.");
        }
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
            showAlert("Error", "Failed to load the view.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void afficherCertificats(List<Certificat> certificats) {
        event_gridPane.getChildren().clear();
        int row = 0;
        for (Certificat certificat : certificats) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/items1.fxml"));
            try {
                AnchorPane pane = loader.load();
                items1 itemsController = loader.getController();
                itemsController.setData(certificat, e -> {
                    event_gridPane.getChildren().remove(pane);
                    try {
                        cs.deleteCertificate(certificat.getID_Certificat());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        showAlert("Error", "Failed to delete the certificate from the database.");
                    }
                });
                event_gridPane.add(pane, 0, row++);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load certificate item view.");
            }
        }
    }

    //  tri par nom, domaine et niveau
    private void trierParNom() {
        try {
            List<Certificat> certificats = cs.trierParNom();
            afficherCertificats(certificats);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par nom.");
        }
    }

    private void trierParDomaine() {
        try {
            List<Certificat> certificats = cs.trierParDomaine();
            afficherCertificats(certificats);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par domaine.");
        }
    }

    private void trierParNiveau() {
        try {
            List<Certificat> certificats = cs.trierParNiveau();
            afficherCertificats(certificats);
        } catch (SQLException e) {
            showAlert("Erreur", "Une erreur est survenue lors du tri par niveau.");
        }
    }

    public void Stat() throws SQLException {
        barChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition des Types");

        Map<String, Integer> domaineStats = cs.getDomaineStatistics();

        for (Map.Entry<String, Integer> entry : domaineStats.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().addAll(series);
    }

    @FXML
    void ExportExcel(ActionEvent event) {
        List<Certificat> certificats;
        try {
            certificats = cs.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("sample");

        // Create header row
        Row headerRow = spreadsheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nom");
        headerRow.createCell(2).setCellValue("Domaine");
        headerRow.createCell(3).setCellValue("Niveau");
        headerRow.createCell(4).setCellValue("Date d'obtention");
        headerRow.createCell(5).setCellValue("ID de l'établissement");

        // Populate data rows
        int rowNum = 1;
        for (Certificat certificat : certificats) {
            Row dataRow = spreadsheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(certificat.getID_Certificat());
            dataRow.createCell(1).setCellValue(certificat.getNom_Certificat());
            dataRow.createCell(2).setCellValue(certificat.getDomaine_Certificat());
            dataRow.createCell(3).setCellValue(certificat.getNiveau());
            dataRow.createCell(4).setCellValue(certificat.getDate_Obtention_Certificat().toString()); // Adjust as needed
            dataRow.createCell(5).setCellValue(certificat.getID_Etablissement());
        }

        try (FileOutputStream fileOut = new FileOutputStream("workbook.xls")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException("Error writing Excel file", e);
        }
    }


}
