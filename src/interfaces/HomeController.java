package interfaces;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import process.BandElectronicCardSim;
import process.BandElectronicCardSim.Bank;
import process.BandElectronicCardSim.CertificatAuthentification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javafx.animation.*;

public class HomeController {
	
	
	
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
		try {
			BandElectronicCardSim band = new BandElectronicCardSim();
			Bank b = band.new Bank();
			CertificatAuthentification c = band.new CertificatAuthentification();
			
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Error");
			a.setHeaderText(e.getMessage());
			a.showAndWait();
		}
		
		
		CA.setOnMouseClicked((event)->{
			System.out.println("a");
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Error");
			a.setHeaderText("error");
			a.showAndWait();
			
		});
		
		bank.setOnMouseClicked((event)->{
			System.out.println("b");
			if(name.getText() != null && surname.getText() != null && pin.getText() != null) {
				try {
					BandElectronicCardSim band = new BandElectronicCardSim();
					Bank b = band.new Bank();
					CertificatAuthentification c = band.new CertificatAuthentification();
					
					b.createCard(name.getText(), pin.getText(), c);
					
					
				} catch (Exception e) {
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Error");
					a.setHeaderText(e.getMessage());
					a.showAndWait();
				}
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
