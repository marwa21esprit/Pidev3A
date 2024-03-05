package controller;

import Services.ServiceFormation;
import entities.Formation;
import Services.ServiceTuteur;
import entities.Tuteur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


public class addFormation{

    private final ServiceFormation ft = new ServiceFormation();

    @FXML
    private Button addFormation;

    @FXML
    private ChoiceBox<String> categorie;

    @FXML
    private DatePicker date_d;

    @FXML
    private DatePicker date_f;

    @FXML
    private TextField description;

    @FXML
    private TextField id_formation;

    @FXML
    private TextField id_niveau;


    @FXML
    private ChoiceBox<Integer> id_tuteur;


    @FXML
    private TextField lien;

    @FXML
    private TextField prix;

    @FXML
    private TextField titre;

    public void ajouter(ActionEvent actionEvent) {
        try {

            String selectedCategorie = categorie.getValue();
            if (selectedCategorie == null) {
                showAlert("Erreur", "Catégorie non sélectionnée", "Veuillez sélectionner une catégorie.");
                return;
            }

            // Validation des champs requis
            if (titre.getText().isEmpty() || description.getText().isEmpty() || date_d.getValue() == null ||
                    date_f.getValue() == null || id_niveau.getText().isEmpty() || id_tuteur.getValue()==null ||
                    lien.getText().isEmpty() || prix.getText().isEmpty() || categorie.getValue() == null) {
                showAlert("Erreur", "Champs requis", "Veuillez remplir tous les champs.");
                return;
            }

            // Convertir les valeurs nécessaires en types appropriés
            Date date = Date.valueOf(date_d.getValue());
            Date date1 = Date.valueOf(date_f.getValue());

            // Vous pouvez maintenant appeler la méthode de service pour ajouter une formation
            ft.addFormation(new Formation(
                    id_tuteur.getValue(),
                    Integer.parseInt(id_niveau.getText()),
                    categorie.getValue(),
                    titre.getText(),
                    description.getText(),
                    java.sql.Date.valueOf(date_d.getValue()),
                    java.sql.Date.valueOf(date_f.getValue()),
                    Float.parseFloat(prix.getText()),
                    lien.getText()
            ));

            showAlert("Succès", "Opération réussie", "La formation a été ajoutée avec succès.");
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Erreur de format", "Assurez-vous que les champs numériques sont au bon format.");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur SQL", e.getMessage());
        } catch (Exception e) {
            showAlert("Erreur", "Erreur inattendue", e.getMessage());
        }
    }



    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void afficher(ActionEvent actionEvent) {
        loadShowFormation();
    }

    private void loadShowFormation() {
        try {
            // Charger l'interface ShowTuteur.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène à partir de l'interface chargée
            Scene scene = new Scene(root);

            // Récupérer la fenêtre actuelle et la modifier pour afficher la nouvelle scène
            Stage stage = (Stage) addFormation.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Erreur", "Erreur lors du chargement", e.getMessage());
        }
    }



}

//   Date.valueOf(selectedDate),
