package interfaces;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Home extends Application{

	@Override
	public void start(Stage primaryStage) {
        try {
        	AnchorPane page = (AnchorPane) FXMLLoader.load(Home.class.getResource("cryptoPuceInterface.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home Puce Cryptographie ");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        Application.launch(Home.class, (java.lang.String[])null);
  }

}
