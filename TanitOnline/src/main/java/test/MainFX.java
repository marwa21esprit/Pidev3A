package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
      // FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddEtabliss.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/DeleteEtabliss.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetEtabliss.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddCertif.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetCertif.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/DeleteCertif.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateEtabliss.fxml"));
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCertif.fxml"));




        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Gestion Etablissements");
        primaryStage.setScene(scene);
        primaryStage.show();


    }






}
