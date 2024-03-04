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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Event;
import services.EventService;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.PartnerService;

import java.io.IOException;
import java.util.List;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;

public class




AddEvent1 {
    private final EventService es = new EventService();
    private final PartnerService ps = new PartnerService();

    @FXML
    private Button ajouter;

    @FXML
    private TextField idEventTF;
    @FXML
    private TextField idEstabTF;
    @FXML
    private TextField nameEventTF;
    @FXML
    private DatePicker dateEventDP;
    @FXML
    private Spinner<Integer> nbrMaxS;
    @FXML
    private TextField prixTF;
    @FXML
    private TextArea descTF;
    @FXML
    private Button importerBT;
    @FXML
    private AnchorPane mainForm;
    @FXML
    private ComboBox<String> partnerCB;

    @FXML
    private ImageView importIV;
    private Image image;

    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0);
        nbrMaxS.setValueFactory(valueFactory);

        // Charger les noms des partenaires dans le ComboBox partnerCB
        try {
            List<String> nomsPartners = ps.getName();
            ObservableList<String> observableNoms = FXCollections.observableArrayList(nomsPartners);
            partnerCB.setItems(observableNoms);
        } catch (SQLException e) {
            showAlert("Erreur SQL", "Une erreur est survenue lors du chargement des établissements.");
            e.printStackTrace();

    }}
    @FXML
    void addEvent(ActionEvent event) throws SQLException {
        try {
            // Validation des champs
            String eventName = nameEventTF.getText().trim();
            if (eventName.isEmpty()) {
                showAlert("Erreur", "Le champ Nom de l'événement ne peut pas être vide.");
                return;
            }

            Date currentDate = new Date(System.currentTimeMillis());
            Date selectedDate = Date.valueOf(dateEventDP.getValue());
            if (selectedDate.before(currentDate)) {
                showAlert("Erreur", "La date de l'événement doit être supérieure à la date actuelle.");
                return;
            }

            int maxParticipants = nbrMaxS.getValue();
            if (maxParticipants <= 0) {
                showAlert("Erreur", "Le nombre maximal de participants ne peut pas être négatif.");
                return;
            }

            String eventDescription = descTF.getText().trim();
            if (eventDescription.isEmpty()) {
                showAlert("Erreur", "Le champ Description ne peut pas être vide.");
                return;
            }



            String selectedPartner = partnerCB.getValue();
             // Utiliser la méthode de EtablissementServices pour obtenir l'ID par le nom

            if (data.path == null || data.path.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une image.");
                return;
            }
            double prix = Double.parseDouble(prixTF.getText());
            System.out.println(ps.getIDByNom(selectedPartner));

            // Ajouter l'événement si toutes les validations sont réussies
            Date dateEventC = Date.valueOf(dateEventDP.getValue());

            Event e =new Event(ps.getIDByNom(selectedPartner),Integer.parseInt(idEstabTF.getText()), eventName, dateEventC, maxParticipants,prix, eventDescription,data.path);
            System.out.println(e);
            es.add(e);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur de format du numéro d'établissement.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors de l'ajout de l'événement à la base de données.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur inattendue s'est produite.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    //BOUTTON BACK (NAVIGATION)
    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    void retourEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    //BOUTTON IMPORT
    public void importer(ActionEvent event) {
        Event e = new Event();
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File","*png","*jpg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());
        if(file != null)
        {
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 125,130,false , true);
            importIV.setImage(image);
        }

    }
    //BOUTTON CLEAR
    public void clear(ActionEvent event) {
        idEstabTF.setText("");
        nameEventTF.setText("");
        dateEventDP.setValue(null);
        nbrMaxS.getValueFactory().setValue(0);
        descTF.setText("");
        data.path = "";
        importIV.setImage(null);
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

    public void showPartners(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    public void resBack(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowBack.fxml"));
            Scene scene;
            Stage primaryStage;
            Parent root;

            root = loader.load();

            scene = new Scene(root);
            primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            primaryStage.setTitle("TANIT ONLINE");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}