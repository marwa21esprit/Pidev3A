package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/showTuteur1.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Items.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Items1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/addTuteur1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/addFormation1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateTuteur1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateFormation.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/deleteTuteur.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/deleteFormation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Tanit Online");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
