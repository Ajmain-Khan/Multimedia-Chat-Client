package client.menu;

import client.ClientConnectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    public TextField userName;
    ClientConnectionHandler clientConnectionHandler = null;

    /**
     * Opens new scene for chat client
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    private void handleChatAction(ActionEvent actionEvent) throws IOException {
        if (!userName.getText().isBlank()) {
            Parent chatParent = FXMLLoader.load(getClass().getResource("../chat/chatUI.fxml"));
            Scene chatScene = new Scene(chatParent);
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(chatScene);
            primaryStage.show();
        } else {
            userName.setPromptText("Please enter a valid username!");
        }
    }

    //When clicked, user will join the image server
    @FXML
    private void handleImageAction(ActionEvent actionEvent) throws IOException {
        if (!userName.getText().isBlank()) {
            Parent imageParent = FXMLLoader.load(getClass().getResource("../image/image.fxml"));
            Scene imageScene = new Scene(imageParent);
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(imageScene);
            primaryStage.show();
        } else {
            userName.setPromptText("Please enter a valid username!");
        }
    }

    /**
     * Terminate connection with server and exit program
     * @param actionEvent
     */
    @FXML
    private void exitAction(ActionEvent actionEvent){
    //TODO: Disconnect client from server
        System.exit(0);
    }

}
