package Client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Controller {

    @FXML
    private TextField userName;
    @FXML
    private Text noName;
    @FXML
    private TextField messageReader;
    @FXML
    private TextField imageReader;
    @FXML
    private TextArea textArea;
    @FXML
    private FileChooser fileChooser;
    @FXML
    private Canvas canvas;
    @FXML
    private Image image;

    Client chatServerClient = null;
    static private String name;
    static private int x = 5;
    static private int y = 15;
    static private int dim = 50;


    //initializes a new client
    public void initialize(){
        chatServerClient = new Client();
    }

    //When clicked, user will join the chat server
    @FXML
    private void enterChatAction(ActionEvent event){
        name = userName.getText();
        if(!name.equalsIgnoreCase("")){
            Stage primaryStage = Main.getPrimaryStage();
            primaryStage.setScene(Main.chat.getScene());
        }else{
            noName.setText("Enter Your Name");
        }
    }

    //When clicked, user will join the image server
    @FXML
    private void enterImageAction(ActionEvent event){
        name = userName.getText();
        if(!name.equalsIgnoreCase("")){
            Stage primaryStage = Main.getPrimaryStage();
            primaryStage.setScene(Main.image.getScene());
        }else{
            noName.setText("Enter Your Name");
        }
    }

    //Adds the new message to the UI
    //Sends the new text to the write function which will send it to the server to be added to the database
    //Resets the message reader text
    @FXML
    private void messageSent(ActionEvent event){
        if(null != messageReader.getText()) {
            textArea.setText(textArea.getText() + "\n" + name + ": " + messageReader.getText());
            chatServerClient.write(name + ": " + messageReader.getText());
            messageReader.setText(null);
        }
    }

    //Returns the user to the main menu
    @FXML
    private void menuAction(ActionEvent event){
        Stage primaryStage = Main.getPrimaryStage();
        primaryStage.setScene(Main.menu.getScene());
    }

    //Allows the user to select which image they want to share
    @FXML
    private void imageSelect(ActionEvent event) throws MalformedURLException {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./localImages"));
        fileChooser.setTitle("Load Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png")
                ,new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"));
        File file = fileChooser.showOpenDialog(null);
        String currentFile = file.getName();
        imageReader.setText(currentFile);
        image = new Image(getClass().getClassLoader().getResource(file.getName()).toString());

    }

    @FXML
    private void imageSent(ActionEvent event){
        if(null != imageReader.getText() && !imageReader.getText().equalsIgnoreCase("Please Select an Image")) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.BLACK);
            gc.strokeText(name + ":", x, y);
            gc.drawImage(image, x + 40, y, dim, dim);
            y += 80;

            imageReader.setText(null);
        }else{
            imageReader.setText("Please Select an Image");
        }
    }


    //Exits the program
    //Sends message to client to disconnect from server
    @FXML
    private void exitAction(ActionEvent event){
//TODO: Disconnect client from server
        exit(1);
    }

}
