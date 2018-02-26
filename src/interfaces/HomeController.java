package interfaces;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import process.BandElectronicCardSim;
import process.BandElectronicCardSim.*;
import java.math.BigInteger;
import java.util.Optional;
import java.util.Random;

import Rsa.Rsa;
import javafx.animation.*;

public class HomeController {
	
	BandElectronicCardSim band = new BandElectronicCardSim();
	Bank b = null;
	CertificatAuthentification c = null;
	CertificatAuthentification cDDA = null;
	Card carde,cardeF,cardeU = null;
	TerminalOperator t = null;
	BankDDA bDDA = null;
	CardDDA cardeDDA = null;
	String res,res2;
	boolean insert1=false;
	boolean insert2=false;
	boolean insert3=false;
	
	@FXML private Tab tab1;
	@FXML private Tab tab2;
	@FXML private Tab tab3;
	@FXML private Tab tab4;
	@FXML private Tab tab5;
	@FXML private TextArea Text;
	@FXML private Button button;
	@FXML private Button button2;
	@FXML private ComboBox<String> carteType;
	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private TextField pin;
	@FXML private TextField CN;
	@FXML private ImageView CA;
	@FXML private ImageView bank;
	@FXML private ImageView bankDDA;
	@FXML private ImageView card;
	@FXML private ImageView card11;
	@FXML private ImageView card12;
	@FXML private ImageView terminal;
	@FXML private ImageView terminal1;
	@FXML private ImageView terminal2;
	@FXML private ImageView card1;
	@FXML private ImageView cardF;
	@FXML private ImageView message;
	@FXML private ImageView message1;
	@FXML private ImageView message13;
	@FXML private ImageView message11;
	@FXML private ImageView message111;
	@FXML private ImageView message12;
	@FXML private ImageView message121;
	@FXML private ImageView message2;
	@FXML private ImageView message21;
	@FXML private ImageView message211;
	@FXML private ImageView message133;
	@FXML private ImageView message132;
	@FXML private ImageView message131;
	
	public void dialog(TranslateTransition transition4) {
		TextInputDialog in = new TextInputDialog("0000");
		in.setTitle("PIN");
		in.setHeaderText("Please enter your PIN:");
		Optional<String> result = in.showAndWait();
		result.ifPresent( e1 ->{
			try {
				
				if(cardeU.getUtil() < 3) {
					if(result.get().equals(cardeU.getPIN()) || cardeF == cardeU ) {
    					Text.appendText("Do transaction...");
    				}else{
    					Alert a = new Alert(AlertType.ERROR);
    					a.setTitle("Error");
    					a.setHeaderText("Invalid PIN");
    					a.show();
    					cardeU.setUtil(cardeU.getUtil()+1);
    				}
				}else throw new Exception("Error : Card Ejected");
				message1.setVisible(false);
				message13.setVisible(true);
				transition4.play();
			} catch (Exception e2) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(e2.getMessage());
				a.show();
			}
			
		});
	}
	
	public void dialog2(TranslateTransition transition4) {
		TextInputDialog in = new TextInputDialog("0000");
		in.setTitle("PIN");
		in.setHeaderText("Please enter your PIN:");
		Optional<String> result = in.showAndWait();
		result.ifPresent( e1 ->{
			try {
				res2=cardeDDA.getRsaC().Decrypter(result.get()+cardeDDA.setCalt().toString()+t.setTalt().toString(), cardeDDA.getRsaC().getPublicKey());
				Text.appendText("###########################################################\n"
	    				+"Crypt(Code PIN,alt1,alt2) : "+res2
	    				+"\nCrypt(alt1) : "+cardeDDA.getCalt().toString()
	    				+"\nCrypt(alt2) : "+t.getTalt().toString()
	    				+"\n");
				/*if(cardeDDA.getUtil() < 3) {
					
					if(result.get().equals(cardeDDA.getPIN()) ) {
    					
    				}else{
    					Alert a = new Alert(AlertType.ERROR);
    					a.setTitle("Error");
    					a.setHeaderText("Invalid PIN");
    					a.show();
    					cardeDDA.setUtil(cardeDDA.getUtil()+1);
    				}
				}else throw new Exception("Error : Card Ejected");*/
				message12.setVisible(false);
				message121.setVisible(true);
				transition4.play();
			} catch (Exception e2) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(e2.getMessage());
				a.show();
			}
			
		});
	}
	
	public void dialog1(TranslateTransition transition4) {
		TextInputDialog in = new TextInputDialog("0000");
		in.setTitle("PIN");
		in.setHeaderText("Please enter your PIN:");
		Optional<String> result = in.showAndWait();
		result.ifPresent( e1 ->{
			message132.setVisible(false);
			message131.setVisible(true);
			transition4.play();
			/*try {
				
				if(carde.getUtil() < 3) {
					if(result.get().equals(carde.getPIN())) {
    					
    				}else{
    					Alert a = new Alert(AlertType.ERROR);
    					a.setTitle("Error");
    					a.setHeaderText("Invalid PIN");
    					a.show();
    					carde.setUtil(carde.getUtil()+1);
    				}
				}else throw new Exception("Error : Card Ejected");
				message131.setVisible(false);
				message132.setVisible(true);
				transition4.play();
			} catch (Exception e2) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(e2.getMessage());
				a.show();
			}*/
			
		});
	}
	
	@FXML
	private void initialize() {
		
		carteType.getItems().add("Original");
		carteType.getItems().add("Fake");
		carteType.getSelectionModel().select(0);
		carteType.setDisable(true);
		
		try {
			cardeF = new Card("????", "0000");
			cardeF.setnCard(120);
			cardeF.init(new Rsa(521), "?????");
			b = band.new Bank();
			bDDA = band.new BankDDA();
			c = band.new CertificatAuthentification();
			cDDA = band.new CertificatAuthentification();
			t = band.new TerminalOperator(c);
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Error");
			a.setHeaderText(e.getMessage());
			a.showAndWait();
		}
		
		
		double mX = message.getLayoutX();
		double mY = message.getLayoutY();
		double m11X = message11.getLayoutX();
		double m11Y = message11.getLayoutY();
		double m111X = message111.getLayoutX();
		double m111Y = message111.getLayoutY();
		
		TranslateTransition transition5 = new TranslateTransition();
		transition5.setDuration(Duration.seconds(1));
		transition5.setNode(message1);
		transition5.setFromX(message1.getX());
		transition5.setFromY(message1.getY());
		
		transition5.setToX(message13.getLayoutX()-message1.getLayoutX());
		transition5.setToY(0);
		transition5.setOnFinished(e -> {
			message1.setVisible(false);
		});
		
		TranslateTransition transition13 = new TranslateTransition();
		transition13.setDuration(Duration.seconds(5));
		transition13.setNode(message121);
		transition13.setFromX(message121.getX());
		transition13.setFromY(message121.getY());
		transition13.setToX(message12.getLayoutX()-message121.getLayoutX());
		transition13.setToY(message12.getLayoutY()-message121.getLayoutY());
		transition13.setAutoReverse(true);
		transition13.setOnFinished(ev->{
			try {
				message121.setVisible(false);
				if(cardeDDA.getUtil() < 3) {
					res2=cardeDDA.getRsaC().Crypter(res2, cardeDDA.getRsaC().getPrivateKey());
					if(res2.subSequence(0, 3).equals((cardeDDA.getPIN()+cardeDDA.getCalt().toString()+t.getTalt().toString()).substring(0,3)) ) {
						Text.appendText("Do transaction...");
					}else{
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("Error");
						a.setHeaderText("Invalid PIN");
						a.show();
						cardeDDA.setUtil(cardeDDA.getUtil()+1);
					}
				}else throw new Exception("Error : Card Ejected");
			}catch (Exception e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(e.getMessage());
				a.showAndWait();
			}
		});
		
		TranslateTransition transition4 = new TranslateTransition();
		transition4.setDuration(Duration.seconds(1));
		transition4.setNode(message13);
		transition4.setFromX(message13.getX());
		transition4.setFromY(message13.getY());
		transition4.setToX(message1.getLayoutX()-message13.getLayoutX());
		transition4.setToY(0);
		transition4.setAutoReverse(true);
		transition4.setOnFinished(e->{
			
			message13.setVisible(false);
			message1.setVisible(true);
			
			transition5.play();
		});
		
		TranslateTransition transition7 = new TranslateTransition();
		transition7.setDuration(Duration.seconds(5));
		transition7.setNode(message132);
		transition7.setFromX(message132.getX());
		transition7.setFromY(message132.getY());
		transition7.setToX(message131.getLayoutX()-message132.getLayoutX());
		transition7.setToY(message131.getLayoutY()-message132.getLayoutY());
		transition7.setAutoReverse(true);
		transition7.setOnFinished(ev->{
			System.out.println("00");
			message132.setVisible(false);
			//message131.setVisible(true);
			
		});
		
		TranslateTransition transition9 = new TranslateTransition();
		transition9.setDuration(Duration.seconds(5));
		transition9.setNode(message132);
		transition9.setFromX(message132.getX());
		transition9.setFromY(message132.getY());
		transition9.setToX(message133.getLayoutX()-message132.getLayoutX());
		transition9.setToY(message133.getLayoutY()-message132.getLayoutY());
		transition9.setAutoReverse(true);
		transition9.setOnFinished(ev->{
			
			message132.setVisible(false);
			cardeF=carde;
			Text.appendText("Done!!!");
			
			
		});
		
		TranslateTransition transition8 = new TranslateTransition();
		transition8.setDuration(Duration.seconds(5));
		transition8.setNode(message131);
		transition8.setFromX(message131.getX());
		transition8.setFromY(message131.getY());
		transition8.setToX(message132.getLayoutX()-message131.getLayoutX());
		transition8.setToY(message132.getLayoutY()-message131.getLayoutY());
		transition8.setAutoReverse(true);
		transition8.setOnFinished(ev->{
			
			message131.setVisible(false);
			message132.setVisible(true);
			transition9.play();
			
		});
		
		TranslateTransition transition12 = new TranslateTransition();
		transition12.setDuration(Duration.seconds(5));
		transition12.setNode(message12);
		transition12.setFromX(message12.getX());
		transition12.setFromY(message12.getY());
		transition12.setToX(message121.getLayoutX()-message12.getLayoutX());
		transition12.setToY(message121.getLayoutY()-message12.getLayoutY());
		transition12.setAutoReverse(true);
		transition12.setOnFinished(ev->{
			
			message12.setVisible(false);
			try {
				Rsa Cpub = cDDA.exist(cardeDDA);
	    		if(Cpub != null) {
	    			Text.appendText("###########################################################\n"
		    				+"CApub : "+Cpub.getPublicKey()
		    				+"\nCalcule Epub!!!\n");
	    			String EpubS = Cpub.Decrypter(cardeDDA.getEcert(), Cpub.getPublicKey());
	    			Text.appendText("###########################################################\n"
		    				+"Epub : "+EpubS
		    				+"\nCalcule Cpub!!!\n");
	    			String Cpubs = cardeDDA.getEpub().Decrypter(cardeDDA.getCcert(), new BigInteger(EpubS));
	    			Text.appendText("###########################################################\n"
		    				+"Cpub : "+Cpubs
		    				+"\nCalcule Res!!!\n");
	    			String RES = cardeDDA.getRsaC().Decrypter(res, new BigInteger(Cpubs));
	    			insert3=true;
	    			
	    			if(RES.equals(cardeDDA.getCalt().toString()+t.getTalt().toString())) {
	    					Text.appendText("Insert PIN\n");
	    			}else {
	    				insert3=false;
	    				throw new Exception("Error: Hacked card");
	    			}
	    		}else throw new Exception("Error: card note authorized");
			}catch (Exception e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(e.getMessage());
				a.show();
			}	
			
			
		});
		
		TranslateTransition transition11 = new TranslateTransition();
		transition11.setDuration(Duration.seconds(5));
		transition11.setNode(message121);
		transition11.setFromX(message121.getX());
		transition11.setFromY(message121.getY());
		transition11.setToX(message12.getLayoutX()-message121.getLayoutX());
		transition11.setToY(message12.getLayoutY()-message121.getLayoutY());
		transition11.setAutoReverse(true);
		transition11.setOnFinished(ev->{
			
			message121.setVisible(false);
			cardeDDA.setCalt();
			res=cardeDDA.getRsaC().Crypter(cardeDDA.getCalt().toString()+t.getTalt().toString(), cardeDDA.getRsaC().getPrivateKey());
			Text.appendText("###########################################################\n"+
					"\n"+"Calt =="+cardeDDA.getCalt()+
					"\n"+"res =="+res);
			
			message12.setVisible(true);
			transition12.play();
			
		});
		
		
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(5));
		transition.setNode(message);
		transition.setFromX(message.getX());
		transition.setFromY(message11.getY());
		transition.setToX(m11X-mX);
		transition.setToY(m11Y-mY);
		transition.setAutoReverse(true);
		transition.setOnFinished(ev->{
			message.setVisible(false);
			Text.appendText("###########################################################\n"+carde.getInfo()+
					"\n"+"Certificat publicKey =="+c.getCardList().get(c.getCardList().size()-1).getR().getPublicKey()+
					"\n"+"Certificat privateKey =="+c.getCardList().get(c.getCardList().size()-1).getR().getPrivateKey());
			message11.setVisible(true);
		});
		
		TranslateTransition transition1 = new TranslateTransition();
		transition1.setDuration(Duration.seconds(5));
		transition1.setNode(message11);
		transition1.setFromX(message11.getX());
		transition1.setFromY(message11.getY());
		
		transition1.setToX(mX-m11X);
		transition1.setToY(mY-m11Y);
		transition1.setOnFinished(e->{
			message11.setVisible(false);
			Text.appendText("###########################################################\n"+carde.getInfo()+
					"\n"+"Cart VA =="+carde.getVA()+
					"\n"+"Cart Epub =="+carde.getEpub().getPublicKey()+
					"\n"+"Cart Ecert =="+carde.getEcert()+"\n");
			message.setVisible(true);
		});
		
		TranslateTransition transition2 = new TranslateTransition();
		transition2.setDuration(Duration.seconds(5));
		transition2.setNode(message);
		transition2.setFromX(message.getX());
		transition2.setFromY(message.getY());
		
		transition2.setToX(m111X-mX);
		transition2.setToY(m111Y-mY);
		transition2.setOnFinished(e->{
			message.setVisible(false);
		});
		
		TranslateTransition transition20 = new TranslateTransition();
		transition20.setDuration(Duration.seconds(5));
		transition20.setNode(message2);
		transition20.setFromX(message2.getX());
		transition20.setFromY(message11.getY());
		transition20.setToX(m11X-mX);
		transition20.setToY(m11Y-mY);
		transition20.setAutoReverse(true);
		transition20.setOnFinished(ev->{
			message2.setVisible(false);
			Text.appendText("###########################################################\n"+cardeDDA.getInfo()+
					"\n"+"Certificat publicKey =="+cDDA.exist(cardeDDA).getPublicKey()+
					"\n"+"Certificat privateKey =="+cDDA.exist(cardeDDA).getPrivateKey()+"\n");
			message21.setVisible(true);
		});
		
		TranslateTransition transition21 = new TranslateTransition();
		transition21.setDuration(Duration.seconds(5));
		transition21.setNode(message21);
		transition21.setFromX(message21.getX());
		transition21.setFromY(message21.getY());
		
		transition21.setToX(mX-m11X);
		transition21.setToY(mY-m11Y);
		transition21.setOnFinished(e->{
			message21.setVisible(false);
			
			
			message2.setVisible(true);
		});
		
		TranslateTransition transition22 = new TranslateTransition();
		transition22.setDuration(Duration.seconds(5));
		transition22.setNode(message2);
		transition22.setFromX(message2.getX());
		transition22.setFromY(message2.getY());
		
		transition22.setToX(m111X-mX);
		transition22.setToY(m111Y-mY);
		transition22.setOnFinished(e->{
			Text.appendText("###########################################################\n"+cardeDDA.getInfo()+
					"\n"+"Card Info : "+cardeDDA.getInfo()+
					"\n"+"Cart Cpub =="+cardeDDA.getRsaC().getPublicKey()+
					"\n"+"Cart Cpriv =="+cardeDDA.getRsaC().getPrivateKey()+
					"\n"+"Cart Ccert =="+cardeDDA.getCcert()+
					"\n"+"Cart Epub =="+cardeDDA.getEpub().getPublicKey()+
					"\n"+"Cart Ecert =="+cardeDDA.getEcert()+"\n");
			message2.setVisible(false);
		});
		
		TranslateTransition transition3 = new TranslateTransition();
		transition3.setDuration(Duration.seconds(5));
		transition3.setNode(message1);
		transition3.setFromX(message1.getX());
		transition3.setFromY(message1.getY());
		
		transition3.setToX(message13.getLayoutX()-message1.getLayoutX());
		transition3.setToY(0);
		transition3.setOnFinished(e->{
			try {
				message1.setVisible(false);
				if(cardeU != null) {
					Text.appendText("###########################################################\n"
		    				+"Card Info : "+cardeU.getInfo()
		    				+"\nCard VA : "+cardeU.getVA()
		    				+"\nCard Ecert : "+cardeU.getEcert()
		    				+"\nLooking for CApub!!!\n");
		    		Rsa Cpub = c.exist(cardeU);
		    		if(Cpub != null) {
		    			Text.appendText("###########################################################\n"
			    				+"CApub : "+Cpub.getPublicKey()
			    				+"\nCalcule Epub!!!\n");
		    			String EpubS = Cpub.Decrypter(cardeU.getEcert(), Cpub.getPublicKey());
		    			Text.appendText("###########################################################\n"
			    				+"Epub : "+EpubS
			    				+"\nCalcule Info!!!\n");
		    			String info = cardeU.getEpub().Decrypter(cardeU.getVA(), new BigInteger(EpubS));
		    			Text.appendText("###########################################################\n"
			    				+"Info : "+info
			    				+"\n");
		    			insert1=true;
		    			
		    			if(info.equals(cardeU.getInfo())) {
		    					Text.appendText("Insert PIN\n");
		    			}else {
		    				insert1=false;
		    				throw new Exception("Error: Hacked card");
		    			}
		    		}else throw new Exception("Error: card note authorized");
				}else throw new Exception("Error: Please create a card");
			} catch (Exception e1) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(e1.getMessage());
				a.show();
			}	
		});
		
		TranslateTransition transition10 = new TranslateTransition();
		transition10.setDuration(Duration.seconds(5));
		transition10.setNode(message12);
		transition10.setFromX(message12.getX());
		transition10.setFromY(message12.getY());
		
		transition10.setToX(message121.getLayoutX()-message12.getLayoutX());
		transition10.setToY(0);
		transition10.setOnFinished(e->{
			try {
				message12.setVisible(false);
				if(cardeDDA != null) {
					t.setTalt();
					Text.appendText("###########################################################\n"
		    				+"Card Info : "+cardeDDA.getInfo()
		    				+"\nCard Ccert : "+cardeDDA.getCcert()
		    				+"\nCard Ecert : "+cardeDDA.getEcert()
		    				+"\nTalt :"+t.getTalt() +"\n");
					
					message121.setVisible(true);
					transition11.play();
					/*
		    		Rsa Cpub = cDDA.exist(cardeDDA);
		    		if(Cpub != null) {
		    			Text.appendText("###########################################################\n"
			    				+"CApub : "+Cpub.getPublicKey()
			    				+"\nCalcule Epub!!!\n");
		    			String EpubS = Cpub.Decrypter(cardeU.getEcert(), Cpub.getPublicKey());
		    			Text.appendText("###########################################################\n"
			    				+"Epub : "+EpubS
			    				+"\nCalcule Info!!!\n");
		    			String info = cardeU.getEpub().Decrypter(cardeU.getVA(), new BigInteger(EpubS));
		    			Text.appendText("###########################################################\n"
			    				+"Info : "+info
			    				+"\n");
		    			insert1=true;
		    			
		    			if(info.equals(cardeU.getInfo())) {
		    					Text.appendText("Insert PIN\n");
		    			}else {
		    				insert1=false;
		    				throw new Exception("Error: Hacked card");
		    			}
		    		}else throw new Exception("Error: card note authorized");*/
				}else throw new Exception("Error: Please create a card");
			} catch (Exception e1) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(e1.getMessage());
				a.show();
			}	
		});
		
		TranslateTransition transition6 = new TranslateTransition();
		transition6.setDuration(Duration.seconds(5));
		transition6.setNode(message133);
		transition6.setFromX(message133.getX());
		transition6.setFromY(message133.getY());
		
		transition6.setToX(message132.getLayoutX()-message133.getLayoutX());
		transition6.setToY(message132.getLayoutY()-message133.getLayoutY());
		transition6.setOnFinished(e->{
			try {
				message133.setVisible(false);
				if(carde != null) {
					Text.appendText("###########################################################\n"
		    				+"Card Info : "+carde.getInfo()
		    				+"\nCard VA : "+carde.getVA()
		    				+"\nCard Ecert : "+carde.getEcert()
		    				+"\n");
		    		Rsa Cpub = c.exist(carde);
		    		if(Cpub != null) {
		    			
		    			String EpubS = Cpub.Decrypter(carde.getEcert(), Cpub.getPublicKey());
		    		
		    			String info = carde.getEpub().Decrypter(carde.getVA(), new BigInteger(EpubS));
		    			
		    			insert2=true;
		    			
		    			if(info.equals(carde.getInfo())) {
		    				message132.setVisible(true);
		    				transition7.play();
		    			}else {
		    				insert2=false;
		    				throw new Exception("Error: Hacked card");
		    			}
		    		}else throw new Exception("Error: card note authorized");
				}else throw new Exception("Error: Please create a card");
			} catch (Exception e1) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText(e1.getMessage());
				a.show();
			}	
		});
		
		carteType.setOnAction(e->{
			if(carteType.getValue().equals("Original")) {
				cardF.setVisible(false);
				card1.setVisible(true);
				cardeU=carde;
			}else {
				card1.setVisible(false);
				cardF.setVisible(true);
				cardeU=cardeF;
			}
			
		});
		
		terminal.setOnMouseClicked(e ->{
			if(insert1) {
				dialog(transition4);
			}
		});
		
		terminal1.setOnMouseClicked(e->{
			if(insert3) {
				dialog2(transition13);
			}
		});
		
		terminal2.setOnMouseClicked(e ->{
			if(insert2) {
				dialog1(transition8);
			}
		});
		
		SequentialTransition s = new SequentialTransition(transition,transition1,transition2);
		SequentialTransition s2 = new SequentialTransition(transition20,transition21,transition22);
		
		tab2.setOnSelectionChanged((e)->{
			carteType.setDisable(false);
			insert2=false;
			insert3=false;
			//insert1=false;
			if(carteType.getValue().equals("Original")) {
				cardF.setVisible(false);
				card1.setVisible(true);
				cardeU=carde;
				
			}else {
				card1.setVisible(false);
				cardF.setVisible(true);
				cardeU=cardeF;
			}
		});
		
		
		tab3.setOnSelectionChanged((e)->{
			carteType.setDisable(true);
			insert1=false;
			insert2=false;
			insert3=false;
		});
		
		tab4.setOnSelectionChanged((e)->{
			carteType.setDisable(true);
			insert1=false;
			insert2=false;
			insert3=false;
		});
		
		tab5.setOnSelectionChanged((e)->{
			carteType.setDisable(true);
			insert1=false;
			insert2=false;
			insert3=false;
		});
		
		tab1.setOnSelectionChanged((e)->{
			carteType.setDisable(true);
			insert1=false;
			insert2=false;
			insert3=false;
		});
		
		card1.setOnMouseClicked((event)->{
			
			message1.setVisible(true);
			transition3.play();
			
		});
		
		cardF.setOnMouseClicked((event)->{
			
			message1.setVisible(true);
			transition3.play();
			
		});
		
		card12.setOnMouseClicked((event)->{
			
			message133.setVisible(true);
			transition6.play();
			
		});
		
		card11.setOnMouseClicked((event)->{
			
			message12.setVisible(true);
			transition10.play();
			
		});
		
		
		
		bank.setOnMouseClicked((event)->{
			
			System.out.println("a");
			if(!name.getText().equals("") && !surname.getText().equals("") && !pin.getText().equals("")) {
				if (pin.getText().chars().allMatch( Character::isDigit) && pin.getText().length() == 4) {
					try {
						carde = b.createCard(name.getText()+"-"+surname.getText(), pin.getText(), c);
						//carteType.getItems().add(carde.toString());
						
						CN.setText(Integer.toString(carde.getnCard()));
						Text.appendText("###########################################################\n"+carde.getInfo()+
								"\n"+"Bank publicKey =="+b.getCardList().get(b.getCardList().size()-1).getR().getPublicKey()+
								"\n"+"Bank privateKey =="+b.getCardList().get(b.getCardList().size()-1).getR().getPrivateKey()+"\n");
						message.setVisible(true);
						s.play();
					} catch (Exception e) {
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("Error");
						a.setHeaderText(e.getMessage());
						a.showAndWait();
					}
				}else {
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Error");
					a.setHeaderText("PIN invalid");
					a.showAndWait();
				}
			}else {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText("info empty");
				a.showAndWait();
			}
		});

		
		bankDDA.setOnMouseClicked((event)->{
			
			System.out.println("aDDA");
			if(!name.getText().equals("") && !surname.getText().equals("") && !pin.getText().equals("")) {
				if (pin.getText().chars().allMatch( Character::isDigit) && pin.getText().length() == 4) {
					try {
						cardeDDA = bDDA.createCard(name.getText()+"-"+surname.getText(), pin.getText(), cDDA);
						//carteType.getItems().add(carde.toString());
						
						CN.setText(Integer.toString(cardeDDA.getnCard()));
						Text.appendText("###########################################################\n"+cardeDDA.getInfo()+
								"\n"+"Bank publicKey =="+bDDA.getCardList().get(bDDA.getCardList().size()-1).getR().getPublicKey()+
								"\n"+"Bank privateKey =="+bDDA.getCardList().get(bDDA.getCardList().size()-1).getR().getPrivateKey()+
								"\n"+"Card publicKey =="+cardeDDA.getRsaC().getPublicKey()+
								"\n"+"Card privateKey =="+cardeDDA.getRsaC().getPrivateKey()+"\n");
						message2.setVisible(true);
						s2.play();
					} catch (Exception e) {
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("Error");
						a.setHeaderText(e.toString());
						a.showAndWait();
					}
				}else {
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Error");
					a.setHeaderText("PIN invalid");
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
		
		button2.setOnAction((event)->{
			Text.clear();
		});
	}
	
	
}
