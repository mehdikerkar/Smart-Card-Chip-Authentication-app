package interfaces;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import process.BandElectronicCardSim;
import process.BandElectronicCardSim.Bank;
import process.BandElectronicCardSim.Card;
import process.BandElectronicCardSim.CertificatAuthentification;
import sun.swing.StringUIClientPropertyKey;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import com.sun.xml.internal.ws.util.StringUtils;
import javafx.animation.*;

public class HomeController {
	
	BandElectronicCardSim band = new BandElectronicCardSim();
	Bank b = null;
	CertificatAuthentification c = null;
	Card carde = null;
	
	@FXML private TextField text;
	@FXML private Button button;
	@FXML private ComboBox<String> carteType;
	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private TextField pin;
	@FXML private TextField CN;
	@FXML private ImageView CA;
	@FXML private ImageView bank;
	@FXML private ImageView card;
	
	
	@FXML
	private void initialize() {
		carteType.getSelectionModel().select(0);
		
		CA.setOnMouseClicked((event)->{
			System.out.println("a");
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Error");
			a.setHeaderText("error");
			a.showAndWait();
			
		});
		
		bank.setOnMouseClicked((event)->{
			System.out.println("b");
			if(!name.getText().equals("") && !surname.getText().equals("") && !pin.getText().equals("") 
					&& pin.getText().chars().allMatch( Character::isDigit )) {
				try {
					b = band.new Bank();
					c = band.new CertificatAuthentification();
					carde = b.createCard(name.getText()+surname.getText(), pin.getText(), c);
					carteType.getItems().add(carde.toString());
					CN.setText(Integer.toString(carde.getnCard()));
				} catch (Exception e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Error");
					a.setHeaderText(e.getMessage());
					a.showAndWait();
				}
			}else {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText("info empty");
				a.showAndWait();
			}
		});

		card.setOnMouseClicked((event)->{
			System.out.println("c");
		});
		
		button.setOnAction((event)->{
			name.setText("hay!!");
			translate(200, 100, CA);
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
