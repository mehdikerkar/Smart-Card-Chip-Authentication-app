package interfaces;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.animation.*;

public class HomeController {
	
	
	
	@FXML private TextField text;
	@FXML private Button button;
	@FXML private ImageView img;
	@FXML private ComboBox<String> carteType;
	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private TextField pin;
	@FXML private TextField CN;
	
	@FXML
	private void initialize() {
		carteType.getSelectionModel().select(0);
		System.out.println("aaaaaaaaaa");
		button.setOnAction((event)->{
			name.setText("hay!!");
			translate(200, 100, img);
		});
	}
	
	public static void zoom(int x ,int y,Node s) {
		ScaleTransition transition = new ScaleTransition();
		transition.setNode(s);
		transition.setToX(x);
		transition.setToY(y);
		transition.play();
	}
	
	public static void translate (int x ,int y,Node s) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(s);
		transition.setToX(x);
		transition.setToY(y);
		transition.play();
	}
	
}
