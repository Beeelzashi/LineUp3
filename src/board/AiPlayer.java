package board;

import java.util.Random;

/**
 * 
 * @author Colin NEMEGHAIRE
 *
 */
public class AiPlayer extends Player{


	public AiPlayer(String n, Color c, int id) {
		super(n, c, id);

	}

	/**
	 * Play method, made AI play the game
	 * @param board The board to play with
	 * @return String table with action made
	 */
	public String[] play(Board board) { 
		Random rand = new Random();
		boolean flag=false;
		int point = rand.nextInt(3);
		int dir;
		char dirChar='0';
		do {
			dir = rand.nextInt(4);
			switch (dir) {
			case 1:
				flag=board.movePlayerPoint('z', this, point);
				dirChar='z';
				break;
			case 2:
				flag=board.movePlayerPoint('s', this, point);
				dirChar='s';
				break;			

			case 3:
				flag=board.movePlayerPoint('q', this, point);
				dirChar='q';
				break;

			case 4:
				flag=board.movePlayerPoint('d', this, point);
				dirChar='d';
				break;		
			}
			point = rand.nextInt(3);
		}while(!flag);

		return new String[] {String.valueOf(point),String.valueOf(dirChar)};
	}
}
