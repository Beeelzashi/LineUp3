package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 * @author Julien LALLOYER and Matheo SINQUIN
 *
 */
public class Player {
	private String name; 
	private Color color;
	private Point[] jPion;
	private Trap[] jTrap;
	private final int ID;
	/**
	 * Constructor	
	 * @param n player's name 
	 * @param c player's color
	 * @param id player's id
	 */
	public Player(String n,Color c, int id) {
		this.name = n; 
		this.color = c;
		this.ID = id;
		this.jPion = new Point[3];
		this.jTrap = new Trap[6];
		this.AddTypeTrap();
	}
	
	/**
	 * Add all the trap of a player
	 */
	public void AddTypeTrap() {
		jTrap[0] = new Trap(ETraps.FREEZE);
		jTrap[1] = new Trap(ETraps.FREEZE);
		jTrap[2] = new Trap(ETraps.FREEZE);
		jTrap[3] = new Trap(ETraps.TELEPORT);
		jTrap[4] = new Trap(ETraps.TELEPORT);
		jTrap[5] = new Trap(ETraps.TELEPORT);
	}

	/**
	 * return the color of the player's point 
	 * @return player's color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * set the new color of the player 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	
	/**
	 * return the player's name
	 * @return player's name
	 */
	public String getName() {
		return name;
	}

	@SuppressWarnings("static-access")
	@Override
	public String toString() {
		return this.getColor().getCode()+name + color.ANSI_RESET.getCode();
	}

	
	/**
	 * return all the player's pawns 
	 * @return all the player's pawns
	 */
	public Point[] getjPion() {
		return jPion;
	}
	
	/**
	 * change the type of the trap
	 * @param idx index of the player's point in the tab
	 * @param typeTrap a new type trap
	 */
	public void modifyTrap(int idx, Trap typeTrap) {
		this.jTrap[idx] = typeTrap;
	}
	
	/**
	 * return all the player's traps
	 * @return all the player's traps
	 */
	public Trap[] getJTrap() {
		return jTrap;
	}
	
	
	/**
	 * change the pawn of the player
	 * @param idx idx of the player
	 * @param point the point where the player will move
	 */
	public void modifyJpion(int idx, Point point) {
		if(this.jPion[idx]!=null) {
			this.jPion[idx].setColor(Color.ANSI_WHITE);
		}		
		this.jPion[idx]=point;
		point.setColor(this.color);
	}
	
	
	/**
	 * write in a save file the player's data
	 */
	public void fileWriter() {
		String myPath = System.getProperty("user.dir")+File.separator+"data"+File.separator;
		try(PrintWriter pw = new PrintWriter(new File(myPath+"infoJoueur"+this.ID))){
			String name = this.name;
			String color = this.color.toString();
			int jpion1x = this.jPion[0].getX();
			int jpion1y = this.jPion[0].getY();
			int jpion2x = this.jPion[1].getX();
			int jpion2y = this.jPion[1].getY();
			int jpion3x = this.jPion[2].getX();
			int jpion3y = this.jPion[2].getY();
			String jtrap1 = this.jTrap[0].getName();
			String jtrap2 = this.jTrap[1].getName();
			String jtrap3 = this.jTrap[2].getName();
			String jtrap4 = this.jTrap[3].getName();
			String jtrap5 = this.jTrap[4].getName();
			String jtrap6 = this.jTrap[5].getName();
			pw.println(String.valueOf(name));
			pw.println(String.valueOf(color));
			pw.println(String.valueOf(jpion1x));
			pw.println(String.valueOf(jpion1y));
			pw.println(String.valueOf(jpion2x));
			pw.println(String.valueOf(jpion2y));
			pw.println(String.valueOf(jpion3x));
			pw.println(String.valueOf(jpion3y));
			pw.println(String.valueOf(jtrap1));
			pw.println(String.valueOf(jtrap2));
			pw.println(String.valueOf(jtrap3));
			pw.println(String.valueOf(jtrap4));
			pw.println(String.valueOf(jtrap5));
			pw.println(String.valueOf(jtrap6));
			
			
		} catch (IOException e) {
			System.out.println("Writing error : " + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * remove the player's data from the saveFile
	 */
	public void fileDelete() {
		String myPath = System.getProperty("user.dir")+File.separator+"data"+File.separator;
		try(PrintWriter pw = new PrintWriter(new File(myPath+"infoJoueur"+this.ID))){
		pw.println();	
		} catch (IOException e) {
			System.out.println("Writing error : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * look if the file is Empty
	 * @return if the file is Empty
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
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * return a player with the savefile's data
	 * @param id id of the player
	 * @param board current board
	 * @return the player with all the new data
	 */
	public Player fileReaderPlayer(int id, Board board) {
	
		String name = "";
		String fileRead = "";
		Player res= new Player("test", color, 1);
		int x1 = 0, x2=0, x3=0, y1=0, y2=0, y3=0;
		Trap t1 = new Trap(ETraps.NONE), t2= new Trap(ETraps.NONE), t3= new Trap(ETraps.NONE), t4= new Trap(ETraps.NONE), t5= new Trap(ETraps.NONE), t6= new Trap(ETraps.NONE);
		String myPath = System.getProperty("user.dir")+File.separator+"data"+File.separator;
		 try {
				FileInputStream file = new FileInputStream(myPath+"infoJoueur"+id);
				Scanner scanner = new Scanner(file);
				int i = 1;
				while(scanner.hasNextLine())
				{
					fileRead=scanner.nextLine()+"\n"; 
					if(i == 1) {
						name = fileRead.substring(0, fileRead.length()-1);
					}else if(i == 2) {
						this.color.setColor(fileRead.substring(0, fileRead.length()-1));
					
						if(name.equals("fake")) {
							res = new AiPlayer(name, color, id);
						}else {							
							res = new Player(name, color, id);
						}
					}else if(i == 3) {
						x1 = Integer.parseInt(fileRead.substring(0, 1));
					}else if(i == 4) {
						y1 = Integer.parseInt(fileRead.substring(0, 1));
					}else if(i == 5) {
						x2 = Integer.parseInt(fileRead.substring(0, 1));
					}else if(i == 6) {
						y2 = Integer.parseInt(fileRead.substring(0, 1));
					}else if(i == 7) {
						x3 = Integer.parseInt(fileRead.substring(0, 1));
					}else if(i == 8) {
						y3 = Integer.parseInt(fileRead.substring(0, 1));
					}else if(i == 9) {
						t1.setNameTrap(fileRead.substring(0, fileRead.length()-1));
					}else if(i == 10) {
						t2.setNameTrap(fileRead.substring(0, fileRead.length()-1));
					}else if(i == 11) {
						t3.setNameTrap(fileRead.substring(0, fileRead.length()-1));
					}else if(i == 12) {
						t4.setNameTrap(fileRead.substring(0, fileRead.length()-1));
					}else if(i == 13) {
						t5.setNameTrap(fileRead.substring(0, fileRead.length()-1));
					}else if(i == 14) {
						t6.setNameTrap(fileRead.substring(0, fileRead.length()-1));
					}
					i++;
				}
				res.modifyJpion(0, board.getPoints()[x1-1][y1-1]);
				res.modifyJpion(1, board.getPoints()[x2-1][y2-1]);
				res.modifyJpion(2, board.getPoints()[x3-1][y3-1]);
				res.modifyTrap(0, t1);
				res.modifyTrap(1, t2);
				res.modifyTrap(2, t3);
				res.modifyTrap(3, t4);
				res.modifyTrap(4, t5);
				res.modifyTrap(5, t6);
				scanner.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 System.out.println(board);
		 this.jPion = res.jPion;
		 this.jTrap = res.jTrap;
		 this.name = res.name;
		 return res;
		
	}
	
	
	
	
}
