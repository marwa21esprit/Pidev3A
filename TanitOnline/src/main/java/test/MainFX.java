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
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemsfront.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowEtablissement.fxml"));







        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Gestion Etablissements");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

        // Cette méthode est appelée lors du démarrage de l'application JavaFX.
        // Elle est utilisée pour initialiser et configurer la scène principale de l'application.

        // Création d'un objet FXMLLoader pour charger le fichier FXML qui décrit l'interface utilisateur.
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddEtabliss1.fxml"));

        // Chargement de la hiérarchie d'objets de l'interface utilisateur depuis le fichier FXML spécifié.
        // Parent root = loader.load();

        // Création d'une nouvelle scène avec la racine (la hiérarchie d'objets de l'interface utilisateur) chargée à partir du fichier FXML.
        // Scene scene = new Scene(root);

        // Définition du titre de la fenêtre principale de l'application.
        // primaryStage.setTitle("Gestion Etablissements");

        // Attribution de la scène créée à la fenêtre principale de l'application.
        // primaryStage.setScene(scene);

        // Affichage de la fenêtre principale de l'application.
        // primaryStage.show();
    }




