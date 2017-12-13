package interfaces;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HomeController {
	
	
	
	@FXML private TextField text;
	@FXML private Button button;
	
	@FXML
	private void initialize() {
		System.out.println("aaaaaaaaaa");
		button.setOnAction((event)->{
			System.out.println("hay!!");
		});
	}
	
}
