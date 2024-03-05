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
<<<<<<< HEAD
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/showTuteurFront.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowTuteur1Front.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowFormation1Front.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/tuteurs.fxml"));

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/ItemsFront.fxml"));

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
=======
<<<<<<< HEAD
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
=======
<<<<<<< HEAD
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation1.fxml"));
>>>>>>> e4a64c996fa0a287801d8671a2d22f8dfb2f992c
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Items.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Items1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/addTuteur.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/addFormation1.fxml"));
<<<<<<< HEAD
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/addFormationFront.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateTuteur.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateFormation.fxml"));
=======
=======
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Items.fxml"));
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowTuteur.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/showFormation.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/addTuteur1.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addFormation.fxml"));
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateTuteur1.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateFormation1.fxml"));
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
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
