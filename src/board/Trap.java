package board;

import java.util.Random;


/**
 * 
 * @author matheo SINQUIN and Alexis ROUSERE      
 *
 */



public class Trap { 
	private ETraps typeTrap;
	/**
	 * Constrctor
	 * @param typeTrap the type trap
	 */
	public Trap(ETraps typeTrap) {
		this.typeTrap = typeTrap;
	} 

	/**
	 * return the name (String) of the trap 
	 * @return the player name 
	 */
	public String getName() {
		return ""+this.typeTrap;
	}



	/**
	 * set a strap's type with his name(String)
	 * @param string name of the trap
	 */
	public void setNameTrap(String string) {
		if(string.equals("NONE")) {
			this.typeTrap = ETraps.NONE;
		}else if(string.equals("FREEZE")) {
			this.typeTrap = ETraps.FREEZE;
		}else if(string.equals("TELEPORT")) {
			this.typeTrap = ETraps.TELEPORT;
		}
	}

	/**
	 * return the trap's type
	 * @return the trap's type
	 */
	public ETraps getTypeETrap() {return this.typeTrap;}

	/**
	 * set the trap's type
	 * @param trap the new type trap
	 */
	public void setTypeTrap(ETraps trap) {this.typeTrap = trap;}

	/**
	 * teleport a pawn in a random position
	 * @param board the current board
	 * @param _x the layer coordinate 
	 * @param _y the coordinate in the layer
	 * @param format the board format
	 * @param attackedPlayer the player who is attacked
	 */
	public static void TeleportRandTrap (Board board, int _x, int _y ,BoardFormat format, Player attackedPlayer) {
		boolean exist = false;
		boolean exist1 = false; 
		Random rdm = new Random();
		int x = _x;
		int y = _y;
		while(exist1 == false) {
			if(x == 2) {
				x = x - 1;
			}else {
				x = x+1;
			}
			do{
				x = rdm.nextInt(3);
				y = format == BoardFormat.SQUARE ? rdm.nextInt(8) : rdm.nextInt(6);	
				System.out.println(x);
				exist1 = (board.getPoints()[x][y].getColor() == Color.ANSI_WHITE);
				System.out.println("x ="+ x + "y = "+ y);
				System.out.println(board.getPoints()[x][y].getColor());
				System.out.println(exist1);
			}while((x == _x || y == _y )  ) ;
		}

		Point p = board.getPoints()[_x][_y];
		Point p1 = board.getPoints()[x][y];
		int idx = 0;
		while(!exist && idx<3) {
			System.out.println(p.getCoordonate());
			if(p.getCoordonate().equals(attackedPlayer.getjPion()[idx].getCoordonate())) {
				exist = true;
			}else {
				idx++;				
			}
		}
		board.removeColor(_x, _y);
		board.changeColor(x, y, attackedPlayer.getColor());
		attackedPlayer.modifyJpion(idx, p1);
		System.out.println("Votre pions a été teleporté aux coordonnées suivantes :"+p1.getCoordonate());



	}

	/**
	 * freeze a trap , he can't move 
	 * @param point the point locked
	 * @param x layer coordinate 
	 * @param y coordinate in the layer
	 * @param p the player locked
	 */
	public static void DontMoveTrap(Point[][] point, int x, int y, Player p) {
		//Le pion est bloqué et ne peut plus bouger pendant un tour
		if(point[x][y].getColor() == Color.ANSI_WHITE) {
			point[x][y].setBlocked(true);
		}else {
			for(int i= 0 ; i<3 ; i++) {
				if(p.getjPion()[i].getCoordonate().equals(point[x][y].getCoordonate())) {
					p.getjPion()[i].setBlocked(true);
				}
			}
			point[x][y].setBlocked(true);

		}

		x++;
		y++;
		System.out.println("le pi�ge a bien �t� plac� en ("+x+","+y+")");
	}

	/**
	 * Unblock all the pawn 
	 * @param points the point of the board
	 * @param p1 player 1
	 * @param p2 player 2
	 * @param nbCouches the number of layer in the board
	 */
	public static void ResetTrap(Point[][] points, Player p1, Player p2, int nbCouches)
	{
		for (int i = 0; i < p1.getjPion().length; i++) {
			if (p1.getjPion()[i].isBlocked()) {
				p1.getjPion()[i].setBlocked(false);
			}
		}

		for (int i = 0; i < p2.getjPion().length; i++) {
			if (p2.getjPion()[i].isBlocked()) {
				p2.getjPion()[i].setBlocked(false);
			}
		}

		for (int i = 0; i < nbCouches; i++) {
			for (int j = 0; j < points.length; j++) {
				if (points[i][j].isBlocked()) {
					points[i][j].setBlocked(false);
				}
			}
		}
	}


	
}
