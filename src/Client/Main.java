package Client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private static Stage primaryStage;
    protected static Parent menu;
    protected static Parent chat;
    protected static Parent image;

    protected Scene menuScene;
    protected Scene chatScene;
    protected Scene imageScene;

    static Controller controllerHandler;
    public static List<String> argsList;
    public static List<String> getArgs(){
        return argsList;
    }

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        chat = FXMLLoader.load(getClass().getResource("chat.fxml"));
        menu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        image = FXMLLoader.load(getClass().getResource("image.fxml"));
        menuScene = new Scene(menu, 300, 300);
        chatScene = new Scene(chat, 400, 400);
        imageScene = new Scene(image, 400, 400);
        primaryStage.setTitle("Sharing Server");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
