package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 * @author Colin NEMEGHAIRE / Mathéo SINQUIN / Alexis ROUSERÉ        
 *
 */
public class Board { 
	private int couche ;
	private Point[][] points;
	private BoardFormat boardFormat; 
	private int victory;
	boolean javaFx = false;
	String board="";
	/**
	 * Constructor
	 * @param boardFormat To know if it's a square or triangle
	 */
	public Board(BoardFormat s, int layer,boolean isDisplayedInJavafx) {
		this.boardFormat=s;
		this.setCouche(layer);
		this.javaFx = isDisplayedInJavafx;
		int x=6;
		String path = "";
		if(boardFormat == BoardFormat.SQUARE) {            
			x=8;
			path = "square_";
		}else {
			path = "triangle_";
		}
		points = new Point[layer][x]; 
		for(int i = 0;i<layer;i++) {
			for(int j=0;j<x;j++) {                
				points[i][j] = new Point(i+1,j+1, Color.ANSI_WHITE);                
			}
		}
		FileInputStream file;
		try {
			if(this.javaFx) {
				file = new FileInputStream(System.getProperty("user.dir")+File.separator+"data"+File.separator+path+layer+"FXlayer");
			}else {
				file = new FileInputStream("data/"+path+layer+"layer");
			}

			Scanner scanner = new Scanner(file);  
			while(scanner.hasNextLine())
			{
				board+=scanner.nextLine()+"\n";
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	/**
	 * return all the points
	 * @return all the board's point 
	 */
	public Point[][] getPoints() {
		return points;
	}


	/**
	 * return the board format
	 * @return the board's format 
	 */
	public BoardFormat getFormat() {
		return boardFormat;
	}
	/**
	 * return the victory State
	 * @return the victory state 
	 */
	public int getVictoryState() {return this.victory;}

	/**
	 * Function verifying if there is a winner
	 * @param s board format 
	 * @param p1 player 1
	 * @param p2 player 2
	 */
	public void verifGraphe (BoardFormat s, Player p1,Player p2)
	{
		boolean boardFormat = (s == BoardFormat.SQUARE);
		int maxFirstPos = boardFormat ? 7 : 5;

		for (int i = 0; i < this.getCouche() ; i++) {
			for (int j = 0; j < maxFirstPos; j++) {
				if (j%2 != 0) {
					if (this.points[i][j].getColor().equals(this.points[i][j-1].getColor()) && this.points[i][j].getColor().equals(this.points[i][j+1].getColor())) {
						if (this.colorEquals(this.points[i][j], p1)) {
							this.victory = 1;
							System.out.println(this.victory);
						}else if (this.colorEquals(this.points[i][j], p2)) {
							this.victory = 2;
							System.out.println(this.victory);
						}
					}

					if (i == 1) {
						if (this.points[i][j].getColor().equals(this.points[i-1][j].getColor()) && this.points[i][j].getColor().equals(this.points[i+1][j].getColor())) {
							if (this.colorEquals(this.points[i][j], p1)) {
								this.victory = 1;
								System.out.println(this.victory);
							}else if (this.colorEquals(this.points[i][j], p2)) {
								this.victory = 2;
								System.out.println(this.victory);
							}
						}
					}
				}
			}
		}

		if (this.points[1][maxFirstPos].getColor().equals(this.points[2][maxFirstPos].getColor()) && this.points[1][maxFirstPos].getColor().equals(this.points[0][maxFirstPos].getColor())) {
			if (this.colorEquals(this.points[1][maxFirstPos], p1)) {
				this.victory = 1;
				System.out.println(this.victory);
			}else if (this.colorEquals(this.points[1][maxFirstPos], p2)) {
				this.victory = 2;
				System.out.println(this.victory);
			}
		}

		for (int i = 0; i < this.getCouche(); i++) {
			if (this.points[i][0].getColor().equals(this.points[i][maxFirstPos].getColor()) && this.points[i][maxFirstPos].getColor().equals(this.points[i][maxFirstPos-1].getColor())) {

				if (this.colorEquals(this.points[i][maxFirstPos], p1)) {
					this.victory = 1;
					System.out.println(this.victory);
				}else if (this.colorEquals(this.points[i][maxFirstPos], p2)) {
					this.victory = 2;
					System.out.println(this.victory);
				}
			}
		}
	}
	/**
	 * return if the point and the color are equals
	 * @param p A point 
	 * @param pl A player
	 * @return if the player's color and the point's colo are the same
	 */
	boolean colorEquals(Point p, Player pl) {return p.getColor() == pl.getColor();}

	/**
	 * change the color of a point for the move
	 * @param x layer coordinate
	 * @param y coordinate in layer
	 * @param color the new color
	 */
	public void changeColor(int x, int y,Color color) {
		this.points[x][y].setColor(color);

	}

	/**
	 * change the color of a point 
	 * @param p A point 
	 */
	public void changeColor(Point p) {
		this.points[p.getX()][p.getY()].setColor(p.getColor());
	}

	/**
	 * Look if the coordinate is in the table and there is no pawn in
	 * @param x layer coordinate 
	 * @param y coordinate in the layer
	 * @return if the coordinate is in the table and there is no pawn in
	 */
	public boolean verifTab(int x, int y) {
		if(this.boardFormat == BoardFormat.SQUARE) {		
			return (x>=0 && x<3 && y>=0 && y<8 && this.points[x][y].getColor().equals(Color.ANSI_WHITE)) && !this.points[x][y].isBlocked();//if the move is possible and there is no traps 
		}else if (this.boardFormat == BoardFormat.TRIANGLE){
			return x>=0 && x<3 && y>=0 && y<6 && this.points[x][y].getColor().equals(Color.ANSI_WHITE);
		}else {
			return false;
		}
	}


	/**
	 * remove the pawn after a move
	 * @param x layer coordinate
	 * @param y coordinate in the layer
	 */
	public void removeColor(int x, int y) {
		this.points[x][y].setColor(Color.ANSI_WHITE);
	}

	/**
	 * remove the pawn after a move 
	 * @param p a point 
	 */
	public void removeColor(Point p) {
		this.points[p.getX()-1][p.getY()-1].setColor(Color.ANSI_WHITE);
	}

	/**
	 * Look if the pawn car go up or down 
	 * @param x layer
	 * @return if the vertical movement is possible
	 */
	public boolean checkMouvementLayer(int x) {
		if(x%2 == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Move the pawn because of the key the player type
	 * @param direction where the player want to go
	 * @param p the player who play
	 * @return if the move is possible
	 */

	public boolean movePlayerPoint(char direction, Player p, int idx) {
		if(direction == 'z') {//mouvement vers le haut
			return this.checkMouvementLayer(p.getjPion()[idx].getY()) && this.checkMouvement(-1, 0, p,idx);
		}else if(direction == 'q') {//vers la gauche 
			return this.checkMouvement(0, -1, p,idx);
		}else if(direction == 'd') {//vers la droite 
			return this.checkMouvement(0, 1, p,idx);
		}else if(direction == 's') {//vers le bas
			return this.checkMouvementLayer(p.getjPion()[idx].getY()) && this.checkMouvement(1, 0, p,idx);
		}else {//la donnée n'est pas valide
			return false;
		}
	}
	/**
	 * Make sure the move is possible and move the pawn
	 * @param x layer coordinate 
	 * @param y coordinate in the layer
	 * @param player the player who play
	 * @return if the move is possible 
	 */
	public boolean checkMouvement(int x, int y, Player player, int idx) {
		int a = player.getjPion()[idx].getX()-1+x;
		int b = player.getjPion()[idx].getY()-1+y;
		if(this.verifTab(a, b)) {//Verifie que la case où l'on veut aller est bien vide
			player.modifyJpion(idx, this.points[a][b]);
			System.out.println("nouvelle coordonnée : " + this.points[a][b].getX() + "/" + this.points[a][b].getY());
			return true;
		}else if((b == 8 && this.boardFormat == BoardFormat.SQUARE) || (b == 6 && this.boardFormat == BoardFormat.TRIANGLE)) {
			player.modifyJpion(idx, this.points[a][0]);
			System.out.println("nouvelle coordonnée : " + this.points[a][0].getX() + "/" + this.points[a][0].getY());
			return true;
		}else if(b == -1 && this.boardFormat == BoardFormat.SQUARE) {
			int tmpB=6;
			if(this.boardFormat == BoardFormat.SQUARE) {
				tmpB=7;
			}
			player.modifyJpion(idx, this.points[a][tmpB]);
			System.out.println("nouvelle coordonnée : " + this.points[a][tmpB].getX() + "/" + this.points[a][tmpB].getY());
			return true;
		}else {
			System.out.println("mouvement impossible");
			return false;
		}
	}

	/**
	 * do the move of the player
	 * @param board the current board
	 * @param player the player who play
	 * @param scan the player entry 
	 */
	public void playerMouvement(Board board,Player player, Scanner scan) {
		boolean correct = false;
		char mouvement;
		int pion = 0;
		while (!correct) {
			System.out.println("voici quel pion bouger : [ 1 : " + player.getjPion()[0].getCoordonate() + " | 2 : " + player.getjPion()[1].getCoordonate() +" | 3 : "+ player.getjPion()[2].getCoordonate() + "]");
			pion = scan.nextInt();
			while(pion > board.getCouche() || pion<1) {
				if(pion > board.getCouche()) {						
					System.out.println("entr�e invalide, vous avez mis un chiffre plus grand que le plateau");
				}else {
					System.out.println("entr�e invalide, vous avez mis un chiffre inf�rieur � 0");
				}
				System.out.println("voici quel pion bouger : [ 1 : " + player.getjPion()[0].getCoordonate() + " | 2 : " + player.getjPion()[1].getCoordonate() +" | 3 : "+ player.getjPion()[2].getCoordonate() + "]");
				pion = scan.nextInt();
			}

			do {
				if (player.getjPion()[pion-1].isBlocked()) {
					System.out.println("Ce pion a �t� pi�g� pour ce tour, veuillez en choisir un autre");
				}
			}
			while (player.getjPion()[pion-1].isBlocked());
			if(pion == 1) {
				System.out.println("déplace toi ");
				mouvement = scan.next().charAt(0);
				if(board.movePlayerPoint(mouvement, player,0)) {
					correct = true;
				}else {
					System.out.println("mouvement incorrect");
					correct = false;
				}
			}else if(pion == 2) {
				System.out.println("déplace toi ");
				mouvement = scan.next().charAt(0);
				if(board.movePlayerPoint(mouvement, player,1)) {
					correct = true;
				}else {
					System.out.println("mouvement incorrect");
					correct = false;
				}
			}else if(pion == 3) {
				System.out.println("déplace toi ");
				mouvement = scan.next().charAt(0);
				if(board.movePlayerPoint(mouvement, player,2)) {
					correct = true;
				}else {
					System.out.println("mouvement incorrect");
					correct = false;
				}
			}else {
				System.out.println("Choix impossible");
			}		
		}
		System.out.println(board.toString());
	}

	public String toString() {
		String boardtmp=this.board;
		for(int i = 0;i<this.points.length;i++) {
			for(int j = 0;j<this.points[i].length;j++) {
				boardtmp=boardtmp.replaceAll(this.points[i][j].getX()+","+this.points[i][j].getY(), this.points[i][j].getColor().getCode()+this.points[i][j].getX()+","+this.points[i][j].getY()+Color.ANSI_WHITE.getCode());
			}
		}
		return boardtmp;
	}

	/**
	 * write the data of the board in a file
	 */
	public void fileWriter() {
		String myPath = System.getProperty("user.dir")+File.separator+"data"+File.separator;
		try(PrintWriter pw = new PrintWriter(new File(myPath+"infoBoard"))){
			int couche = this.getCouche();
			String format = this.boardFormat.getPath();
			int javafx = 0;
			if(this.javaFx) {
				javafx = 1;
			}
			pw.println(String.valueOf(couche));
			pw.println(String.valueOf(format));
			pw.println(String.valueOf(javafx));

		} catch (IOException e) {
			System.out.println("Writing error : " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * delete the data of the save file
	 */
	public void fileDelete() {
		String myPath = System.getProperty("user.dir")+File.separator+"data"+File.separator;
		try(PrintWriter pw = new PrintWriter(new File(myPath+"infoBoard"))){
			pw.println();	
		} catch (IOException e) {
			System.out.println("Writing error : " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * look if the file is empty
	 * @return if the file is empty
	 */
	public static boolean fileIsEmpty() {
		String string = "";
		boolean res = true;
		String myPath = System.getProperty("user.dir")+File.separator+"data"+File.separator;
		try {
			try {
				FileInputStream file = new FileInputStream(myPath+"infoJoueur1");
				Scanner scanner = new Scanner(file);
				string=scanner.nextLine();
				if(string.charAt(0) == ' ') {
					res = true;
				}else {
					res = false;
				}

				scanner.close();

			}catch(NoSuchElementException i) {
				res = true;
			}catch(StringIndexOutOfBoundsException e) {
				res = true;
			}catch(NumberFormatException e) {
				res = true;
			}catch(NullPointerException e) {
				res = true;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e) {
			res = true;
		}
		return res;
	}

	/**
	 * return a board with the data of the file 
	 * @return a new board with the new data 
	 */
	public static Board fileReader() {
		String board = "";
		int layer = 0;
		int fx = 0;
		boolean Fx = false;
		String type = "";
		BoardFormat format = BoardFormat.SQUARE;
		String myPath = System.getProperty("user.dir")+File.separator+"data"+File.separator;
		try {
			FileInputStream file = new FileInputStream(myPath+"infoBoard");
			Scanner scanner = new Scanner(file);
			int i = 1;
			while(scanner.hasNextLine())
			{
				board=scanner.nextLine()+"\n";
				if(i == 1) {
					layer = Integer.parseInt(board.substring(0, 1));
				}else if(i == 2) {
					type = board;
				}else{
					fx = Integer.parseInt(board.substring(0, 1));
				}
				i++;
			}
			scanner.close();
			if(type == "data/triangle_") {
				format = BoardFormat.TRIANGLE;
			}
			if(fx == 1) { 
				Fx = true;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(board);

		return  new Board(format, layer, Fx);

	}

	/**
	 * return the board (String)
	 * @return
	 */
	public String getBoard() {
		return board;
	}
	/**
	 * return the layer
	 * @return the layer
	 */
	public int getCouche() {
		return couche;
	}
	
	/**
	 * set a new layer 
	 * @param couche the new layer
	 */
	public void setCouche(int couche) {
		this.couche = couche;
	}




}