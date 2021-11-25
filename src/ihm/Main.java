package ihm;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	private Parent root;
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
			
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("/FirstWindow.fxml");
        if (fxmlFileUrl == null) {
                System.out.println("Impossible de charger le fichier fxml");
                System.exit(-1);
        }
		loader.setLocation(fxmlFileUrl);
		root = loader.load();
		FirstWindowController firstWindowControlTest = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Choix des joueurs");
		primaryStage.setScene(scene);
		primaryStage.show();
		firstWindowControlTest.startGame(primaryStage,root);
		
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
