package ihm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import board.BoardFormat;
import board.Color;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
/**
 * 
 * @author Colin Nemeghaire and Julien Lalloyer
 *
 */
public class FirstWindowController implements Initializable{
	@FXML
	Button confirmButton,rulesButton,continueButton;
	
	@FXML
	TextField p1NameField,p2NameField;
	
	@FXML
	ComboBox<Integer> nbCouche, nbTeleport;
	
	@FXML
	RadioButton platC,platT;
	
	@FXML
	ComboBox<Color> comboColorP1,comboColorP2;
	
	
	
	
	final ToggleGroup selectPlat= new ToggleGroup();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comboColorP1.getItems().addAll(Color.ANSI_CYAN,Color.ANSI_GREEN,Color.ANSI_PURPLE,Color.ANSI_RED,Color.ANSI_YELLOW);
		comboColorP1.setValue(Color.ANSI_CYAN);
		comboColorP2.getItems().addAll(Color.ANSI_CYAN,Color.ANSI_GREEN,Color.ANSI_PURPLE,Color.ANSI_RED,Color.ANSI_YELLOW);
		comboColorP2.setValue(Color.ANSI_CYAN);
		platT.setToggleGroup(selectPlat);
		platC.setToggleGroup(selectPlat);
		platC.setSelected(true);
		nbCouche.getItems().addAll(3,4,5);
		nbCouche.setValue(3);
		nbTeleport.getItems().addAll(1,2,3);
		nbTeleport.setValue(3);
		
		
		
	}
/**
 * Method that start the game, load the main window
 * @param primaryStage The primary stage
 * @param root the Root parent
 */
	public void startGame(Stage primaryStage, Parent root) {
		
		
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("/Window.fxml");
        if (fxmlFileUrl == null) {
                System.out.println("Impossible de charger le fichier fxml");
                System.exit(-1);
        }
		loader.setLocation(fxmlFileUrl);
		try {
			root = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MainController mainController = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Jeu du moulin");
		mainController.setStageAndParent(primaryStage, root);
		continueButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			mainController.continueGame();
			primaryStage.setScene(scene);
			primaryStage.show();
		});
		confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			
			if(comboColorP1.getValue().equals(comboColorP2.getValue())) {
				Alert alert = new Alert(AlertType.WARNING,"Les couleurs choisient doivent être différentes",ButtonType.OK);
				alert.showAndWait();
			}else if(p1NameField.getText()==null || p2NameField.getText()==null || p1NameField.getText().trim().isEmpty() || p2NameField.getText().trim().isEmpty() ){
				Alert alert = new Alert(AlertType.WARNING,"Un des champs est vide",ButtonType.OK);
				alert.showAndWait();
			}else{
				
				if(platC.isSelected()) {
					mainController.initialiseBoard(BoardFormat.SQUARE, nbCouche.getValue());
				}else {
					mainController.initialiseBoard(BoardFormat.TRIANGLE, nbCouche.getValue());
				}
				mainController.initializeLabelNames(p1NameField.getText(), p2NameField.getText(),comboColorP1.getValue(),comboColorP2.getValue());				
				mainController.setTrapLabelNumber( this.nbTeleport.getValue());
				mainController.deleteSaveFile();
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			
		});
		rulesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			Stage stage = new Stage();
			HBox hbox = new HBox();
			Text rules = new Text();
			rules.setTextAlignment(TextAlignment.CENTER);
			rules.setText("Les règles du jeu du moulin sont simple, deux joueurs avec trois pions chacun, le but est de bouger vos pions à tour de role et ainsi les aligner pour décrocher la victoire."
					+ "\nPour mettre des battons dans les roues de votre adversaire vous disposez de pièges: Immobilisation et Teleporteur \n L'immobilisation vous permettra d'immobiliser le pion qui tombe dessus \n Le teleporteur teleporte le pion qui tombe dessus aléatoirement sur le plateau"
					+ "\n\nSi vous avez mis en joueur une IA, Appuyez sur le bouton 'Terminer le Tour' pour que l'IA bouge ses pionsd");
			hbox.getChildren().add(rules);
			Scene sceneRules = new Scene(hbox);
			stage.setTitle("Game Rules");
			stage.setScene(sceneRules);
			stage.show();
			
		});
	}
}
