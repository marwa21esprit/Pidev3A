package controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GetApprenantFront implements Initializable {
    @FXML
    private Label num;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane pane3;
    public void transplateAnimation(double duration, Node node , double width){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration) , node);
        translateTransition.setByX(width);
        translateTransition.play();
    }
    @FXML
    void back(ActionEvent event) {
        if(show==1)
        {
            transplateAnimation(0.5,pane2,1000);
            show--;
            num.setText("1/3");
        }else if(show==2){
            transplateAnimation(0.5,pane3,1000);
            show--;
            num.setText("2/3");
        }

    }
    int show = 0;
    @FXML
    void next(ActionEvent event) {
        if(show==0)
        {
            transplateAnimation(0.5,pane2,-1000);
            show++;
            num.setText("2/3");
        }else if(show==1){
            transplateAnimation(0.5,pane3,-1000);
            show++;
            num.setText("3/3");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transplateAnimation(0.5,pane2,1000);
        transplateAnimation(0.5,pane3,1000);

    }
}
