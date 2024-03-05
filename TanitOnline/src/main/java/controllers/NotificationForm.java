package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationForm implements Initializable {

    public void btnNotifcationOnAction(ActionEvent actionEvent) {
        Notifications notificationsBuilder = Notifications.create()
                .title("Download complete")
                .text("hdhdhdhd")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.TOP_RIGHT)
                .onAction(event -> onNotificationAction(event));

        notificationsBuilder.show();
    }

    private void onNotificationAction(ActionEvent event) {
        System.out.println("Notification clicked!");
        // Ajoutez le code que vous souhaitez exécuter lorsqu'une notification est cliquée
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation, si nécessaire
    }
}
