package controller;

import Services.ServiceFormation;
import Services.ServiceTuteur;
import entities.Formation;
import entities.Tuteur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
<<<<<<< HEAD
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
=======
import javafx.stage.Stage;

>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class updateFormation1 {

    private final ServiceFormation ft = new ServiceFormation();
    private final ServiceTuteur st = new ServiceTuteur();

    @FXML
    private ChoiceBox<String> categorie1;

    @FXML
    private DatePicker date_d1;

    @FXML
    private DatePicker date_f1;

    @FXML
    private TextArea description1;

    @FXML
    private TextField id_formation1;

    @FXML
<<<<<<< HEAD
    private TextField nom_niveau1;

    @FXML
    private ChoiceBox<String> nom_prenom_tuteur1;
=======
    private TextField id_niveau1;

    @FXML
    private ChoiceBox<Integer> id_tuteur1;
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55

    @FXML
    private TextField lien1;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField prix1;

    @FXML
    private TextField titre1;

    @FXML
    private Button updateFormation;

<<<<<<< HEAD
    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void initialize() {
=======
    @FXML
    void initialize() {
        try {
            List<Integer> idsTuteurs = st.getId_tuteur();
            ObservableList<Integer> observableIds = FXCollections.observableArrayList(idsTuteurs);
            id_tuteur1.setItems(observableIds);
        } catch (SQLException e) {
            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors du chargement des  formations.");
            e.printStackTrace();
        }

>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
    }


    @FXML
    void modifier(ActionEvent event) {

        try {
<<<<<<< HEAD
            Date selectedStartDate = java.sql.Date.valueOf(date_d1.getValue());
            Date selectedEndDate = java.sql.Date.valueOf(date_f1.getValue());


=======

            LocalDate selectedStartDate = date_d1.getValue();
            LocalDate selectedEndDate = date_f1.getValue();
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55

            String selectedCategorie = categorie1.getValue();
            if (selectedCategorie == null) {
                showAlert("Erreur", "Catégorie non sélectionnée", "Veuillez sélectionner une catégorie.");
                return;
            }

<<<<<<< HEAD
            String selectedTuteur = nom_prenom_tuteur1.getValue();
            if (selectedTuteur == null) {
=======
            Integer selectedId = id_tuteur1.getValue();
            if (selectedId == null) {
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
                showAlert("Erreur", "ID non sélectionnée", "Veuillez sélectionner l'id.");
                return;
            }

<<<<<<< HEAD
            int id = Integer.parseInt(id_formation1.getText());
            String[] parts = selectedTuteur.split(" "); // Divise la chaîne à chaque espace
            String nom = parts[0]; // Le premier élément du tableau est le nom
            String prenom = parts[1];
            int idTuteur = st.getIDByNom(nom,prenom);



            Formation updatedFormation = new Formation(
                    idTuteur,
                    nom_niveau1.getText(),
=======
            int id_formation = Integer.parseInt(id_formation1.getText());
            int idTuteur = st.getID(id_tuteur1.getValue());
            int id_niveau = Integer.parseInt(id_niveau1.getText());

            Formation updatedFormation = new Formation(
                    id_niveau,
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
                    selectedCategorie,
                    titre1.getText(),
                    description1.getText(),
                    selectedStartDate,
                    selectedEndDate,
                    Float.parseFloat(prix1.getText()),
                    lien1.getText()
<<<<<<< HEAD
                                );
            System.out.println(updatedFormation);

ft.updateFormation(updatedFormation,id);

            afficherAlerteInformation("Mise à jour réussie", "La formation a été mis à jour avec succès.");
=======
            );

            ft.updateFormation(updatedFormation, id_formation1);


            afficherAlerteInformation("Mise à jour réussie", "Le tuteur a été mis à jour avec succès.");
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
        } catch (NumberFormatException e) {

            afficherAlerteErreur("Erreur de format", "Veuillez entrer des valeurs valides.");
            e.printStackTrace();
        } catch (SQLException e) {

            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors de la mise à jour de formation. Veuillez réessayer.");
            e.printStackTrace();
        } catch (Exception e) {

            afficherAlerteErreur("Erreur", "Une erreur est survenue lors de la mise à jour de formation. Veuillez réessayer.");
            e.printStackTrace();
        }
    }

    private void afficherAlerteInformation(String succès, String opérationRéussie) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(succès);
        alert.setHeaderText(opérationRéussie);
        alert.showAndWait();
    }

    private void afficherAlerteErreur(String titre, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();


    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    void clear(ActionEvent event) {

        id_formation1.setText("");
<<<<<<< HEAD
        nom_prenom_tuteur1.setValue("tuteur name");
        nom_niveau1.setText("");
=======
        id_tuteur1.setValue(null);
        id_niveau1.setText("");
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
        categorie1.setValue("");
        titre1.setText("");
        description1.setText("");
        date_d1.setValue(null);
        date_f1.setValue(null);
        prix1.setText("");
        lien1.setText("");

    }


    @FXML
    void retour(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            afficherAlerteErreur("Erreur de chargement", "Une erreur est survenue lors du chargement de la vue.");
        }

    }

    public void setData(Formation formation) {

        id_formation1.setText(String.valueOf(formation.getId_formation()));
<<<<<<< HEAD
        try {
            List<String> nomsTuteurs = st.getNoms();
            ObservableList<String> observableNoms = FXCollections.observableArrayList(nomsTuteurs);
            nom_prenom_tuteur1.setItems(observableNoms);
            nom_prenom_tuteur1.setValue(st.getNameByID(formation.getId_tuteur()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        nom_niveau1.setText(formation.getNom_niveau());
=======
        id_tuteur1.setValue(formation.getId_tuteur());
        id_niveau1.setText(String.valueOf(formation.getId_niveau()));
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55

        categorie1.setValue(String.valueOf(formation.getCategorie()));


        if (formation.getDate_d() != null) {
            date_d1.setValue(formation.getDate_d().toLocalDate());
        } else {

            date_d1.setValue(null);
        }

        if (formation.getDate_f() != null) {
            date_f1.setValue(formation.getDate_f().toLocalDate());
        } else {

            date_f1.setValue(null);
        }

        prix1.setText(String.valueOf(formation.getPrix()));
        titre1.setText(formation.getTitre());
        description1.setText(formation.getDescription());
        lien1.setText(formation.getLien());

    }
<<<<<<< HEAD

    public void showTuteur1(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showTuteur1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showFormation1(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void importerPDF(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer un fichier PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf")
        );

        // Afficher la boîte de dialogue de sélection de fichiers
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        // Vérifier si un fichier a été sélectionné
        if (selectedFile != null) {
            // Traiter le fichier PDF sélectionné (par exemple, stocker son chemin ou son contenu)
            System.out.println("Fichier PDF sélectionné : " + selectedFile.getAbsolutePath());
            SharedData.setCheminPDF(selectedFile.getAbsolutePath()); // Stocker le chemin du fichier PDF dans SharedData
        } else {
            // Gérer le cas où aucun fichier n'a été sélectionné
            System.out.println("Aucun fichier sélectionné.");
        }
    }
=======
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
}