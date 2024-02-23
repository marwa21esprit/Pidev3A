package test;

import controllers.GetEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainFX extends Application {

    private double x,y;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/getPartner.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("style.css");
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);

        //set stage borderless
        primaryStage.initStyle(StageStyle.DECORATED);

        //drag it here
        root.setOnMousePressed(event->{
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event->{
            primaryStage.setX(event.getSceneX()-x);
            primaryStage.setY(event.getSceneY()-y);

        });
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }


}
