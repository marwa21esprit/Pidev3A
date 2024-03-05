package controller;

import entities.Formation;
import entities.Tuteur;
import Services.ServiceFormation;
import Services.ServiceTuteur;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

//import static Services.ServiceTuteur.getIdTuteurByNomPrenom;

public class addFormation1 {

    private final ServiceFormation ft = new ServiceFormation();
    private final ServiceTuteur st = new ServiceTuteur();

    private String nomFichierPDF;
    private String cheminFichierPDF;

    @FXML
    private Button AddFormation;

    @FXML
    private ChoiceBox<String> categorie;

    @FXML
    private DatePicker date_d;

    @FXML
    private DatePicker date_f;

    @FXML
    private TextArea description;

    @FXML
    private TextField id_formation;

    @FXML
<<<<<<< HEAD
    private ChoiceBox<String> id_tuteur;
=======
    private TextField id_niveau;

    @FXML
    private ChoiceBox<Integer> id_tuteur;
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55

    @FXML
    private TextField lien;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField nom_niveau;

    @FXML
    private TextField prix;

    @FXML
    private TextField titre;
    private String nom;
    private String prenom;
    private Tuteur tuteursList;

    private Scene scene;
    private Stage primaryStage;
    private Parent root;

   @FXML
    private void initialize() {
       try {
           /*// Remplacez "nom" et "prenom" par les valeurs réelles que vous souhaitez rechercher
           String nom = "nom";
           String prenom = "prenom";

           // Obtenez une liste d'objets Tuteur correspondant au nom et prénom spécifiés
          // List<Tuteur> tuteursList = ServiceTuteur.getIdTuteurByNomPrenom(nom, prenom);

           // Ajoutez les objets Tuteur au ChoiceBox
           id_tuteur.setItems(FXCollections.observableArrayList(tuteursList));*/

               List<String> nomsTuteurs = st.getNoms();
               ObservableList<String> observableNoms = FXCollections.observableArrayList(nomsTuteurs);
           id_tuteur.setItems(observableNoms);


<<<<<<< HEAD
       } catch (SQLException e) {
           e.printStackTrace();
           // Gérer l'exception
       }
=======

    @FXML
    void initialize() {
        try {
            List<Integer> idsTuteurs = st.getId_tuteur();
            ObservableList<Integer> observableIds = FXCollections.observableArrayList(idsTuteurs);
            id_tuteur.setItems(observableIds);
        } catch (SQLException e) {
            afficherAlerteErreur("Erreur SQL", "Une erreur est survenue lors du chargement des  formations.");
            e.printStackTrace();
        }

>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
    }




    @FXML
    private void ajouter(ActionEvent event) {
        try {
            String selectedCategorie = categorie.getValue();
            if (selectedCategorie == null) {
                showAlert("Erreur", "Catégorie non sélectionnée", "Veuillez sélectionner une catégorie.");
                return;
            }

            String selectedTuteur = id_tuteur.getValue();
            if (selectedTuteur == null) {
                showAlert("Erreur", "Tuteur non sélectionné", "Veuillez sélectionner un tuteur.");
                return;
            }

            String selectedNomNiveau = nom_niveau.getText();
            if (selectedNomNiveau.isEmpty()) {
                showAlert("Erreur", "Champ requis", "Veuillez remplir le champ niveau.");
                return;
            }

            if (titre.getText().isEmpty() || description.getText().isEmpty() || date_d.getValue() == null ||
                    date_f.getValue() == null || lien.getText().isEmpty() || prix.getText().isEmpty()) {
                showAlert("Erreur", "Champs requis", "Veuillez remplir tous les champs.");
                return;
            }

            Date selectedDate_d = Date.valueOf(date_d.getValue());
            Date selectedDate_f = Date.valueOf(date_f.getValue());

<<<<<<< HEAD
            String[] parts = selectedTuteur.split(" "); // Divise la chaîne à chaque espace
            String nom = parts[0]; // Le premier élément du tableau est le nom
            String prenom = parts[1];
=======
            java.sql.Date selectedDate_d = java.sql.Date.valueOf(date_d.getValue());
            java.sql.Date selectedDate_f = java.sql.Date.valueOf(date_f.getValue());

            Integer idTuteur = Integer.parseInt(String.valueOf(id_tuteur.getValue()));

>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55

            ft.addFormation(new Formation(
                    st.getIDByNom(nom,prenom), // Utilisez l'ID du tuteur sélectionné
                    selectedNomNiveau,
                    selectedCategorie,
                    titre.getText(),
                    description.getText(),
                    selectedDate_d,
                    selectedDate_f,
                    Float.parseFloat(prix.getText()),
<<<<<<< HEAD
                    lien.getText()
=======
                    lien.getText(),
                    st.getID(id_tuteur.getValue())
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
            ));



            afficherAlerteInformation("Succès", "Opération réussie", "La formation a été ajoutée avec succès.");
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Erreur de format", "Assurez-vous que les champs numériques sont au bon format.");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur SQL", e.getMessage());
        } catch (Exception e) {
            showAlert("Erreur", "Erreur inattendue", "Une erreur inattendue s'est produite. Veuillez réessayer.");
            e.printStackTrace(); // Imprimez la trace complète de l'exception pour un débogage supplémentaire.
        }


    }

    @FXML
    private void afficherAlerteInformation(String succès, String opérationRéussie, String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(succès);
        alert.setHeaderText(opérationRéussie);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    private void clear(ActionEvent event) {
        id_formation.setText("");
        id_tuteur.setValue(null);
<<<<<<< HEAD
        nom_niveau.setText("");
=======
        id_niveau.setText("");
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
        categorie.setValue("");
        titre.setText("");
        description.setText("");
        date_d.setValue(null);
        date_f.setValue(null);
        prix.setText("");
        lien.setText("");
    }

    @FXML
    private void retour(ActionEvent event) {
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

    @FXML
    private void afficherAlerteErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }





    public void showFormation1(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void showTuteur1(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowTuteur1Front.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void importerPDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer un fichier PDF");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf")
        );


        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {

            System.out.println("Fichier PDF sélectionné : " + selectedFile.getAbsolutePath());
            SharedData.setCheminPDF(selectedFile.getAbsolutePath()); // Stocker le chemin du fichier PDF dans SharedData
        } else {

            System.out.println("Aucun fichier sélectionné.");
        }
    }

}
