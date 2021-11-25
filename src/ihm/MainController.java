package ihm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import board.AiPlayer;
import board.Board;
import board.BoardFormat;
import board.Color;
import board.ETraps;
import board.Player;
import board.Point;
import board.Trap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
/**
 * 
 * @author Colin Nemeghaire and Julien Lalloyer
 *
 */
public class MainController implements Initializable{
	@FXML
	Label labelj1,labelj2,playerTurnLabel, nbDeplj1, nbDeplj2, tpnbj1, tpnbj2, freezenbj1, freezenbj2;

	@FXML
	Button stopButton,restartButton;

	@FXML
	Canvas canvas;

	@FXML
	TextFlow textFlow;

	@FXML
	Button moveConfirm,TeleportButton, FreezeButton ;

	@FXML
	ListView<String> consoleListView;

	@FXML
	RadioButton pointRadioButton1,pointRadioButton2,pointRadioButton3,upLayerRadioButton,downLayerRadioButton,rightRadioButton,leftRadioButton,PionEnemie1, PionEnemie2,PionEnemie3;


	@FXML
	TextField TpField, FreezeField;

	Board board;
	Player player1,player2;
	Parent root;
	Integer deplj1 = 0, deplj2 = 0;
	Stage primaryStage;
	private Player currentPlayer;
	private BoardFormat format;
	final ToggleGroup pointGroup= new ToggleGroup(),moveGroup= new ToggleGroup(), trapGroup = new ToggleGroup(), trapRadioPoint = new ToggleGroup(), trapRadioLayer = new ToggleGroup();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pointRadioButton1.setToggleGroup(pointGroup);
		pointRadioButton2.setToggleGroup(pointGroup);
		pointRadioButton3.setToggleGroup(pointGroup);				


		upLayerRadioButton.setToggleGroup(moveGroup);
		downLayerRadioButton.setToggleGroup(moveGroup);
		rightRadioButton.setToggleGroup(moveGroup);
		leftRadioButton.setToggleGroup(moveGroup);

		PionEnemie1.setToggleGroup(trapGroup);
		PionEnemie2.setToggleGroup(trapGroup);
		PionEnemie3.setToggleGroup(trapGroup);


		pointRadioButton1.setSelected(true);
		upLayerRadioButton.setSelected(true);
		PionEnemie1.setSelected(true);

		stopButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			this.player1.fileWriter();
			this.player2.fileWriter();
			this.board.fileWriter();
			System.exit(0);
		});

		restartButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			this.replay();

		});
		moveConfirm.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			this.play();
			this.updateRadioButton();
			if(currentPlayer instanceof AiPlayer) {
				this.play();
				this.updateRadioButton();
			}	

			board.verifGraphe(format, player1, player2);

			if(board.getVictoryState()==1) {
				Alert alert = new Alert(AlertType.INFORMATION,"Bravo "+player1.getName()+" pour ta victoire, Voulez vous rejouer ?",ButtonType.YES,ButtonType.NO);
				Optional<ButtonType> result = alert.showAndWait();
				this.board.fileDelete();
				this.player1.fileDelete();
				this.player2.fileDelete();
				if(result.get()==ButtonType.YES) {
					this.replay();
				}else {
					System.exit(0);
				}
			}else if(board.getVictoryState()==2){
				Alert alert = new Alert(AlertType.INFORMATION,"Bravo "+player2.getName()+" pour ta victoire, Voulez vous rejouer ?",ButtonType.YES,ButtonType.NO);
				Optional<ButtonType> result = alert.showAndWait();
				this.board.fileDelete();
				this.player1.fileDelete();
				this.player2.fileDelete();
				if(result.get()==ButtonType.YES) {
					this.replay();
				}else {
					System.exit(0);
				}
			}else {

			}
		});

		TeleportButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			int idx;
			String point="";
			if(this.PionEnemie1.isSelected()) {
				idx=0;
				point=PionEnemie1.getText();
			}else if(this.PionEnemie2.isSelected()) {
				idx=1;
				point=PionEnemie2.getText();
			}else {
				idx=2;
				point=PionEnemie3.getText();
			}
			Alert noMoreTrapAlert = new Alert(AlertType.INFORMATION,"Vous ne disposez plus de piège de teleportation",ButtonType.OK);


			if(currentPlayer.equals(player1)) {
				if(tpnbj1.getText().equals("0")) {
					noMoreTrapAlert.showAndWait();
				}else {
					TpTrap(this.board, idx , this.player2);
					tpnbj1.setText(String.valueOf(Integer.valueOf(tpnbj1.getText())-1));
					boolean flag=false;
					int cpt=0;
					while(!flag&&cpt<player1.getJTrap().length) {
						if(player1.getJTrap()[cpt].getTypeETrap().equals(ETraps.TELEPORT)) {
							player1.getJTrap()[cpt].setTypeTrap(ETraps.NONE);
							flag=true;
						}
						cpt++;
					}
					this.currentPlayer = player2;		
					if(currentPlayer instanceof AiPlayer) {
						this.updateRadioButton();
						this.currentPlayer = player1;
					}
					consoleListView.getItems().add(player1.getName()+" a décidé de lancer une téléportation sur le point "+point);
				}

			}else {
				if(tpnbj2.getText().equals("0")) {
					noMoreTrapAlert.showAndWait();
				}else {
					TpTrap(this.board, idx, this.player1);
					tpnbj2.setText(String.valueOf(Integer.valueOf(tpnbj2.getText())-1));
					boolean flag=false;
					int cpt=0;
					while(!flag&&cpt<player2.getJTrap().length) {
						if(player2.getJTrap()[cpt].getTypeETrap().equals(ETraps.TELEPORT)) {
							player2.getJTrap()[cpt].setTypeTrap(ETraps.NONE);
							flag=true;
						}
						cpt++;
					}
					this.currentPlayer = player1;
					if(currentPlayer instanceof AiPlayer) {
						this.updateRadioButton();
						this.currentPlayer = player2;
					}
					consoleListView.getItems().add(player2.getName()+" a décidé de lancer une téléportation sur le point "+point);
				}
			}			
			consoleListView.scrollTo(consoleListView.getItems().size());
			board.fileWriter();
			this.player1.fileWriter();
			this.player2.fileWriter();
			this.updateRadioButton();
			this.displayBoard(board);

		});

		/*FreezeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			//utiliser la fonction dontmovetap
		 * 
		 * freezenbj1.setText(String.valueOf(Integer.valueOf(freezenbj1.getText())-1));
		 * 
		 * freezenbj2.setText(String.valueOf(Integer.valueOf(freezenbj2.getText())-1));
		});*/

	}



	/**
	 * Use to set Parent and Stage of the MainController
	 * @param stage
	 * @param parent
	 */
	public void setStageAndParent(Stage stage,Parent parent) {
		this.root=parent;
		this.primaryStage=stage;
	}

	/**
	 * Use to set Trap in MainController 
	 * @param Tp
	 */
	public void setTrapLabelNumber(int Tp) {
		this.tpnbj1.setText((String.valueOf(Tp)));
		this.tpnbj2.setText((String.valueOf(Tp)));
	}
	/**
	 * Replay Method
	 */
	private void replay() {
		this.player1.fileDelete();
		this.player2.fileDelete();
		this.board.fileDelete();
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("/FirstWindow.fxml");
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
		FirstWindowController firstWindowControlTest = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Choix des joueurs");
		primaryStage.setScene(scene);
		primaryStage.show();
		firstWindowControlTest.startGame(primaryStage,root);
	}
	
	/**
	 * Initialise the board
	 * @param format Board format
	 * @param layer Number of layer
	 */
	public void initialiseBoard(BoardFormat format,int layer) {
		this.format=format;
		board = new Board(format, layer,true);
	}
	
	/**
	 * Method for continue previous game
	 */
	public void continueGame() {		
		this.board=Board.fileReader();

		player1= new Player("initialise", Color.ANSI_RED, 1);
		player2= new Player("initialise", Color.ANSI_CYAN, 2);
		player1=player1.fileReaderPlayer(1, board);
		player2=player2.fileReaderPlayer(2, board);
		currentPlayer=player1;
		this.labelj1.setText(player1.getName());
		this.labelj2.setText(player2.getName());
		this.setPlayerLabelColor(player1, labelj1);
		this.setPlayerLabelColor(player2, labelj2);
		this.updateRadioButton();
		this.displayBoard(board);
		int freezeNb=0,tpNb=0;
		for(int i =0;i<player1.getJTrap().length;i++) {

			if(player1.getJTrap()[i].getTypeETrap().equals(ETraps.FREEZE)) {
				freezeNb++;
			}
			if(player1.getJTrap()[i].getTypeETrap().equals(ETraps.TELEPORT)) {
				tpNb++;
			}
		}
		this.freezenbj1.setText(String.valueOf(freezeNb));
		this.tpnbj1.setText(String.valueOf(tpNb));
		freezeNb=0;tpNb=0;
		for(int i =0;i<player2.getJTrap().length;i++) {
			if(player2.getJTrap()[i].getTypeETrap().equals(ETraps.FREEZE)) {
				freezeNb++;
			}
			if(player2.getJTrap()[i].getTypeETrap().equals(ETraps.TELEPORT)) {
				tpNb++;
			}
		}
		this.freezenbj2.setText(String.valueOf(freezeNb));
		this.tpnbj2.setText(String.valueOf(tpNb));
	}

	/**
	 * Method serving to display the board to the  TextFlow
	 * @param board the board to be displayed
	 */
	public void displayBoard(Board board)  {


		Point[][] pTab = board.getPoints();
		String cleanBoard = board.getBoard();
		textFlow.getChildren().clear();
		String test="";
		boolean spaceSwitchFlag=false;
		for(int i=0;i<cleanBoard.length();i++) {
			Text temp=null;
			test+=cleanBoard.charAt(i);

			if(cleanBoard.charAt(i)=='(') {
				temp= new Text(cleanBoard.substring(i, i+5));
				test+=cleanBoard.substring(i+1, i+5);
				Point ptemp = pTab[Character.getNumericValue(cleanBoard.charAt(i+1)-1)][Character.getNumericValue(cleanBoard.charAt(i+3)-1)];
				if(ptemp.getColor().equals(Color.ANSI_RED)) {
					temp.setFill(javafx.scene.paint.Color.RED);					
				}else if (ptemp.getColor().equals(Color.ANSI_CYAN)){
					temp.setFill(javafx.scene.paint.Color.CYAN);		
				}else if (ptemp.getColor().equals(Color.ANSI_GREEN)){
					temp.setFill(javafx.scene.paint.Color.GREEN);		
				}else if (ptemp.getColor().equals(Color.ANSI_PURPLE)){
					temp.setFill(javafx.scene.paint.Color.PURPLE);		
				}else if (ptemp.getColor().equals(Color.ANSI_YELLOW)){
					temp.setFill(javafx.scene.paint.Color.YELLOW);		
				}else {
					temp.setFill(javafx.scene.paint.Color.BLACK);		
				}				
				textFlow.getChildren().add(temp);
				i+=4;

			}else {
				temp = new Text(String.valueOf(cleanBoard.charAt(i)));

				temp.setStyle("-fx-font: 12 consolas;");
				textFlow.getChildren().add(temp);

			}

			spaceSwitchFlag=!spaceSwitchFlag;
		}
	}

	/**
	 * Update the radioButton with the point of the current player
	 */
	public void updateRadioButton() {
		if(player1 instanceof AiPlayer&&player2 instanceof AiPlayer){
			this.playerTurnLabel.setText("C'est au tour d'une IA(Cliquer sur \"terminer le tour\" pour continuer)");
			this.pointRadioButton1.setText("");
			this.pointRadioButton2.setText("");
			this.pointRadioButton3.setText("");
			this.pointRadioButton1.setSelected(false);
			this.pointRadioButton1.setSelected(false);
			this.pointRadioButton1.setSelected(false);
		}else {
			this.playerTurnLabel.setText("C'est au tour de: "+currentPlayer.getName());
			this.pointRadioButton1.setText(currentPlayer.getjPion()[0].getCoordonate());
			this.pointRadioButton2.setText(currentPlayer.getjPion()[1].getCoordonate());
			this.pointRadioButton3.setText(currentPlayer.getjPion()[2].getCoordonate());

			if(currentPlayer == player1) {
				this.PionEnemie1.setText(player2.getjPion()[0].getCoordonate());
				this.PionEnemie2.setText(player2.getjPion()[1].getCoordonate());
				this.PionEnemie3.setText(player2.getjPion()[2].getCoordonate());	
			}else {
				this.PionEnemie1.setText(player1.getjPion()[0].getCoordonate());
				this.PionEnemie2.setText(player1.getjPion()[1].getCoordonate());
				this.PionEnemie3.setText(player1.getjPion()[2].getCoordonate());	
			}
		}

	}
	/**
	 * Play method
	 */
	public void play() {

		if(currentPlayer instanceof AiPlayer) {
			String[] action =((AiPlayer) currentPlayer).play(board);
			consoleListView.getItems().add(this.modifyConsoleList(Integer.valueOf(action[0]), action[1].charAt(0)));
			if(currentPlayer.equals(player1)) {
				this.currentPlayer=player2;
			}else {
				this.currentPlayer=player1;
			}
			pointRadioButton1.setSelected(true);
			upLayerRadioButton.setSelected(true);

			this.displayBoard(board);
			board.fileWriter();
			this.player1.fileWriter();
			this.player2.fileWriter();

		}else {

			int idx;
			char direction;
			if(this.pointRadioButton1.isSelected()) {
				idx=0;
			}else if(this.pointRadioButton2.isSelected()) {
				idx=1;
			}else {
				idx=2;
			}


			if(this.upLayerRadioButton.isSelected()) {
				direction='s';
			}else if(this.downLayerRadioButton.isSelected()) {
				direction='z';
			}else if(this.leftRadioButton.isSelected()) {
				direction='q';
			}else {
				direction='d';
			}

			if(!board.movePlayerPoint(direction, currentPlayer, idx)) {
				Alert alert = new Alert(AlertType.INFORMATION,"Mouvement impossible",ButtonType.OK);
				alert.showAndWait();			
			}else {
				consoleListView.getItems().add(this.modifyConsoleList(idx, direction));
				if(currentPlayer.equals(player1)) {
					this.deplj1++;
					this.nbDeplj1.setText(deplj1.toString());
				}else {
					this.deplj2++;
					this.nbDeplj2.setText(deplj2.toString());
				}
				if(currentPlayer.equals(player1)) {
					this.currentPlayer=player2;
				}else {
					this.currentPlayer=player1;
				}
				pointRadioButton1.setSelected(true);
				upLayerRadioButton.setSelected(true);

			}

			this.displayBoard(board);
			board.fileWriter();
			this.player1.fileWriter();
			this.player2.fileWriter();
		}
		consoleListView.scrollTo(consoleListView.getItems().size());
	}

	private String modifyConsoleList(int idx,char direction) {
		StringBuilder res = new StringBuilder();
		if(currentPlayer instanceof AiPlayer) {
			res.append("L'IA a décidé de deplacer le pion : ");
		}else {
			res.append(currentPlayer.getName()+" a décidé de deplacer le pion : ");
		}

		switch (idx) {
		case 0:
			res.append(pointRadioButton1.getText());
			break;

		case 1:
			res.append(pointRadioButton2.getText());
			break;
		case 2:
			res.append(pointRadioButton3.getText());
			break;
		}
		res.append(" vers ");
		switch (direction) {
		case 'z':
			res.append("la couche supérieur");
			break;
		case 's':
			res.append("la couche inférieur");
			break;
		case 'q':
			res.append("la gauche");
			break;
		case 'd':
			res.append("la droite");
			break;

		}

		return res.toString();
	}
	/**
	 * Place player point in the board
	 * @param board the board to set
	 * @param p1 player1
	 * @param p2 player 2
	 */
	public void PionIntoBoard(Board board,Player p1,Player p2) {
		for(int i = 0 ;  i < 6; i++) {
			int x = -1;
			int y = -1;
			while(!board.verifTab(x,y)) {
				Random rdmx = new Random();
				Random rdmy = new Random();
				int maxX=0;
				switch (board.getFormat()) {
				case SQUARE:
					maxX=8;
					break;
				case TRIANGLE:
					maxX=6;
					break;
				}
				x = rdmx.nextInt(maxX);
				y = rdmy.nextInt(4);
			}
			if(i<3) {
				p1.modifyJpion(i, board.getPoints()[x][y]);
			}else {
				p2.modifyJpion(i-3, board.getPoints()[x][y]);
			}					
		}
	}
	/**
	 * Initialise the player label with their name in their color + create instances of players
	 * @param p1 Player1's name
	 * @param p2 Player2's name
	 * @param cp1 Player's 1 color
	 * @param cp2 Player's 2 color
	 */
	public void initializeLabelNames(String p1,String p2,Color cp1,Color cp2) {
		if(p1.toLowerCase().contentEquals("fake")) {
			player1 = new AiPlayer(p1, cp1, 1);
		}else {
			player1 = new Player(p1, cp1, 1);
		}

		if(p2.toLowerCase().contentEquals("fake")) {
			player2 = new AiPlayer(p2, cp2, 2);
		}else {
			player2 = new Player(p2, cp2, 2);
		}
		this.currentPlayer=player1;
		this.PionIntoBoard(board, player1, player2);

		labelj1.setText(player1.getName());
		labelj2.setText(player2.getName());
		this.setPlayerLabelColor(player1, labelj1);
		this.setPlayerLabelColor(player2, labelj2);
		this.updateRadioButton();
		this.displayBoard(board);

	}

	/**
	 * Use teleportTrap
	 * @param b Board used
	 * @param p player aim for
	 */
	public void TpTrap(Board b, int i, Player p) {
		Trap.TeleportRandTrap(b, p.getjPion()[i].getX()-1, p.getjPion()[i].getY()-1 , b.getFormat(), p);
	}

	/**
	 * Delete save file
	 */
	public void deleteSaveFile() {
		this.player1.fileDelete();
		this.player2.fileDelete();
		this.board.fileDelete();
	}
	/**
	 * Set the color of player to the label
	 * @param p the player
	 * @param l the label to set
	 */
	private void setPlayerLabelColor(Player p,Label l) {
		if(p.getColor().equals(Color.ANSI_RED)) {
			l.setTextFill(javafx.scene.paint.Color.RED);					
		}else if (p.getColor().equals(Color.ANSI_CYAN)){
			l.setTextFill(javafx.scene.paint.Color.CYAN);		
		}else if (p.getColor().equals(Color.ANSI_GREEN)){
			l.setTextFill(javafx.scene.paint.Color.GREEN);		
		}else if (p.getColor().equals(Color.ANSI_PURPLE)){
			l.setTextFill(javafx.scene.paint.Color.PURPLE);		
		}else if (p.getColor().equals(Color.ANSI_YELLOW)){
			l.setTextFill(javafx.scene.paint.Color.YELLOW);		
		}else {
			l.setTextFill(javafx.scene.paint.Color.BLACK);		
		}
	}
}
