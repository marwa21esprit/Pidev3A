package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Apprenant;
import utils.MyDatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ApprenantSearch implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    @FXML
    private TableColumn<Apprenant, String> emailTableColumn;

    @FXML
    private TableColumn<Apprenant, String> formationTableColumn;

    @FXML
    private TableColumn<Apprenant, Integer> idNiveauTableColumn;

    @FXML
    private TableColumn<Apprenant, Integer> idTableColumn;

    @FXML
    private TableColumn<Apprenant, String> imageTableColumn;

    @FXML
    private TextField keyWordTextField;

    @FXML
    private TableView<Apprenant> learnerTableView;

    @FXML
    private TableColumn<Apprenant, String> nameTableColumn;

    @FXML
    private TableColumn<Apprenant, String> niveauTableColumn;

    @FXML
    private TableColumn<Apprenant, String> statutTableColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
