package client.image;


import client.ClientConnectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class imageController {

    @FXML
    private TextField imageReader;

    @FXML
    private FileChooser fileChooser;
    @FXML
    private Canvas canvas;
    @FXML
    private Image image;

    ClientConnectionHandler clientConnectionHandler = null;
    static private String name;
    static private final int x = 5;
    static private int y = 15;
    static private final int dim = 50;

    /**
     * Initialize new client upon startup
     */
    public void initialize() {
//        clientConnectionHandler = new ClientConnectionHandler();
    }

    //Returns the user to the main menu
    @FXML
    private void menuAction(ActionEvent actionEvent) throws IOException {
        Parent mainParent = FXMLLoader.load(getClass().getResource("../menu/menu.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    //Allows the user to select which image they want to share
    @FXML
    private void imageSelect(ActionEvent actionEvent) throws MalformedURLException {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./localImages"));
        fileChooser.setTitle("Load Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png")
                , new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"));
        File file = fileChooser.showOpenDialog(null);
        String currentFile = file.getName();
        imageReader.setText(currentFile);
        image = new Image(getClass().getClassLoader().getResource(file.getName()).toString());

    }

    @FXML
    private void imageSent(ActionEvent actionEvent) {
        if (null != imageReader.getText() && !imageReader.getText().equalsIgnoreCase("Please Select an Image")) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.strokeText(name + ":", x, y);
            gc.drawImage(image, x + 40, y, dim, dim);
            y += 80;

            imageReader.setText(null);
        } else {
            imageReader.setText("Please Select an Image");
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
