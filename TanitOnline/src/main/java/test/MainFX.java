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
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddEtabliss1.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/DeleteEtabliss.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetEtabliss1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddCertif1.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetCertif1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/items.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/DeleteCertif.fxml"));
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateEtabliss.fxml"));
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCertif.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GetCertif.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/items1.fxml"));






        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Gestion Etablissements");
        primaryStage.setScene(scene);
        primaryStage.show();


    }






}
