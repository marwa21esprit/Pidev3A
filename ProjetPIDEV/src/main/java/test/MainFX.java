package test;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*import controllers.Calendar;
import controllers.FullCalendarView;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Random;*/

/*import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
*/
public class MainFX extends Application {

    private double x,y;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/getEvent1.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
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
