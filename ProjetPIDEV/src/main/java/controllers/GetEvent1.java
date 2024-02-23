package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GetEvent1 implements Initializable {

    @FXML
    private VBox pnItems = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Node [] nodes = new Node[10];
        for(int i=0;i<nodes.length;i++)
        {
            try {
                nodes[i] = (Node) FXMLLoader.load(getClass().getResource("/Items.fxml"));
                pnItems.getChildren().add(nodes[i]);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
