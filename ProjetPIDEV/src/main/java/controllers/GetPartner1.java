package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Partner;
import services.PartnerService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GetPartner1 implements Initializable {
    private final PartnerService ps = new PartnerService();

    private Partner parter;
    private ObservableList<Partner> partnerList;
    @FXML
    private AnchorPane mainForm;

    @FXML
    private GridPane partner_gridPane;
    @FXML
    private ChoiceBox<String> triCB;

    @FXML
    private ImageView notFound;

    @FXML
    private com.gluonhq.charm.glisten.control.TextField searchByName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        triCB.getItems().addAll("default","Croissant","Decroissant");

        triCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if ("Decroissant".equals(newValue)) {
                    partnerDisplayDESC();
                } else if ("Croissant".equals(newValue)) {
                    // Ajoutez le code correspondant pour le tri croissant
                    partnerDisplayASC();
                } else if ("default".equals(newValue)) {
                    // Ajoutez le code correspondant pour le tri croissant
                    partnerDisplay();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Initialisez le tri par défaut (par exemple, tri croissant)
        triCB.getSelectionModel().select("default");

        notFound.setVisible(false);
        notFound.setManaged(false);
        searchByName.setOnKeyReleased(event -> {
            try {
                // Obtenez le texte saisi dans le champ de recherche
                String partialName = searchByName.getText();

                // Appelez la méthode de recherche avancée avec le texte saisi
                List<Partner> resultatsRecherche = ps.getByPartialName(partialName);
                System.out.println("Number of results: " + resultatsRecherche.size());


                // Affichez les résultats dans le GridPane
                afficherPartners(resultatsRecherche);
                if(partialName == ""){
                    partnerDisplay();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            partnerDisplay();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void partnerDisplay() throws SQLException {
        partnerList = FXCollections.observableList(ps.getAll());
        int row = 0;
        int column = 0;
        partner_gridPane.getRowConstraints().clear();
        partner_gridPane.getColumnConstraints().clear();
        partner_gridPane.getChildren().clear();
        partner_gridPane.setHgap(20);
        partner_gridPane.setVgap(20);
        for(int i=0;i<partnerList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/partners.fxml"));
                AnchorPane pane = load.load();
                Partners partners = load.getController();
                partners.setData(partnerList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 2)
                {
                    column=0;
                    row+=1;
                }

                partner_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void partnerDisplayDESC() throws SQLException {
        partnerList = FXCollections.observableList(ps.getAllDESC());
        int row = 0;
        int column = 0;
        partner_gridPane.getRowConstraints().clear();
        partner_gridPane.getColumnConstraints().clear();
        partner_gridPane.getChildren().clear();
        partner_gridPane.setHgap(20);
        partner_gridPane.setVgap(20);
        for(int i=0;i<partnerList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/partners.fxml"));
                AnchorPane pane = load.load();
                Partners partners = load.getController();
                partners.setData(partnerList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 2)
                {
                    column=0;
                    row+=1;
                }

                partner_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void partnerDisplayASC() throws SQLException {
        partnerList = FXCollections.observableList(ps.getAllASC());
        int row = 0;
        int column = 0;
        partner_gridPane.getRowConstraints().clear();
        partner_gridPane.getColumnConstraints().clear();
        partner_gridPane.getChildren().clear();
        partner_gridPane.setHgap(20);
        partner_gridPane.setVgap(20);
        for(int i=0;i<partnerList.size();i++)
        {
            try{
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/partners.fxml"));
                AnchorPane pane = load.load();
                Partners partners = load.getController();
                partners.setData(partnerList.get(i));

                //mise a jour de l'affichage
                pane.getProperties().put("controller", this);

                if(column == 2)
                {
                    column=0;
                    row+=1;
                }

                partner_gridPane.add(pane,column++,row);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    public void afficherPartners(List<Partner> partnerList) throws SQLException {
        partner_gridPane.getChildren().clear(); // Nettoyez le contenu existant

        if (partnerList.isEmpty()) {
            // Affichez un message indiquant que aucun résultat n'a été trouvé
            notFound.setVisible(true);
            notFound.setManaged(true);
            partner_gridPane.getChildren().add(notFound);
        } else {
            // Cachez le message "notFound"
            notFound.setVisible(false);
            notFound.setManaged(false);


            int row = 0;
            int column = 0;
            partner_gridPane.getRowConstraints().clear();
            partner_gridPane.getColumnConstraints().clear();
            partner_gridPane.getChildren().clear();
            partner_gridPane.setHgap(20);
            partner_gridPane.setVgap(20);
            for (int i = 0; i < partnerList.size(); i++) {
                try {
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("/partners.fxml"));
                    AnchorPane pane = load.load();
                    Partners partners = load.getController();
                    partners.setData(partnerList.get(i));

                    //mise a jour de l'affichage
                    pane.getProperties().put("controller", this);

                    if (column == 2) {
                        column = 0;
                        row += partnerList.size();
                    }

                    partner_gridPane.add(pane, column++, row);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    public void showPartners(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
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

    public void affi(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEtabliss1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void gestioncertif(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getCertif1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    void ajouterPartner(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addPartner1.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
