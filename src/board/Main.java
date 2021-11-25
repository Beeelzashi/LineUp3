package board;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		Board board= null;
		int choise=-1;	
		int couche = 1;
		String choise1 = "";
		Point pointTest = new Point(15, 15, Color.ANSI_YELLOW);
		Player p1= new Player("initialise", Color.ANSI_RED, 1);
		Player p2= new Player("initialise", Color.ANSI_CYAN, 2);
		for(int i = 0 ; i<3 ; i++) {
			p1.modifyJpion(i, pointTest);
		}
		for(int i = 0 ; i<3 ; i++) {
			p2.modifyJpion(i, pointTest);
		}
		while(choise!=1) {
			System.out.println("Que voulez vous faire?\n1. Jouer\n2. Lire les r�gles\n3. Quitter");
			choise1=(scan.next());	
			if(choise1.equals("1")|| choise1 == "2" ||choise1 == "3") {
				choise = Integer.valueOf(choise1);
			}
			if(choise==3) {
				scan.close();
				return;
			}
			if(choise==2) {
				System.out.println("To Be Completed");
			}
		}
		choise=-1;
		while(choise !=1 && choise !=2) {

			System.out.println("\n1.Nouvelle Partie  \n2.Continuer ");
			choise=scan.nextInt();	
			if(choise == 1) {
				choise = -1;
				while(choise !=1 && choise!=2) {
					System.out.println("Quel plateau?\n1. Carré \n2. Triangle");
					choise=scan.nextInt();			
				}	
				if(choise==1) {
					System.out.println("combien de couches ?");
					couche=scan.nextInt();	
					while(couche < 3 || couche > 5) {
						System.out.println("entrée invalide");
						couche=scan.nextInt();	
					}
					board=new Board(BoardFormat.SQUARE, couche,false);
				}
				if(choise==2) {
					System.out.println("combien de couches ?");
					couche=scan.nextInt();		
					board=new Board(BoardFormat.TRIANGLE, couche,false);
				}
				System.out.println("Joueur 1, quel est ton pseudo ? mettre \"fake\" pour avoir une IA en 2eme joueur");
				String j1Name = scan.next();
				if(j1Name.toLowerCase().equals("fake")) {
					p1=new AiPlayer("fake", Color.ANSI_GREEN, 1);
				}else {
					System.out.println(j1Name+", quelle couleur ?"+Color.ANSI_RESET.getCode());
					p1=new Player(j1Name, colorChoise(scan,null), 1);
				}

				System.out.println("Joueur 2, quel est ton pseudo ? mettre \"fake\" pour avoir une IA en 2eme joueur");
				String j2Name = scan.next();
				if(j2Name.toLowerCase().equals("fake")) {
					Color ctemp=p1.getColor();
					while(ctemp.equals(p1.getColor())){
						Color[] tab = new Color[] {Color.ANSI_RED,Color.ANSI_CYAN,Color.ANSI_GREEN,Color.ANSI_PURPLE,Color.ANSI_YELLOW};
						ctemp=tab[new Random().nextInt(5)];
					}
					p2=new AiPlayer("fake", ctemp, 2);
				}else {
					System.out.println(j2Name+", quelle couleur ?"+Color.ANSI_RESET.getCode());
					boolean ok=false;
					Color ctemp= colorChoise(scan,p1.getColor());
					while(!ok) {

						if(ctemp==p1.getColor()) {
							System.out.println("Tu as choisi la même couleur que "+p1.getName()+", choisi en une autre");
							ctemp=colorChoise(scan,p1.getColor());
						}else {
							ok=true;
						}
					}
					p2=new Player(j2Name, ctemp,2);
				}	

				System.out.println("Joueur1: "+p1.getName()+" Couleur: "+p1.getColor().getCode()+p1.getColor().getColorName()+Color.ANSI_RESET.getCode());
				System.out.println("Joueur2: "+p2.getName()+" Couleur: "+p2.getColor().getCode()+p2.getColor().getColorName()+Color.ANSI_RESET.getCode());
				//System.out.println(board);
				/*
				 * les pion sont donn� automatiquement pour test le fonctionnement global du main (comme je n'ai pas manipul� player)
				 * -Math�o
				 */
				//---------------------------------------
				int placement = 0;
				while(placement != 1 && placement != 2) {
					System.out.println("1 : les joueurs choisissent les pions | 2 : les pions sont pos�s al�atoirement");
					placement = scan.nextInt();
					if(placement == 1) {
						System.out.println(board);
						for(int i = 0; i< 3; i++) {
							System.out.println();
							System.out.println();
							System.out.println(p1.getName() + " placez vos pions");
							int x = -1;
							int y = -1;
							while(!board.verifTab(x,y)) {
								System.out.println("Entrez vos coordonées : X");
								x = scan.nextInt() -1 ;
								System.out.println("Entrez vos coordonées : Y");
								y = scan.nextInt() -1 ;
								if(!board.verifTab(x,y)) {
									System.out.println("Coordonnées incorrect");
								}
							}
							p1.modifyJpion(i, board.getPoints()[x][y]);
							System.out.println("pion placé");
							System.out.println();
							System.out.println();
							System.out.println(p2.getName() + " placez vos pions");
							int x2 = -1;
							int y2 = -1;
							while(!board.verifTab(x2,y2)) {
								System.out.println("Entrez vos coordonées : X");
								x2 = scan.nextInt() -1 ;
								System.out.println("Entrez vos coordonées : Y");
								y2 = scan.nextInt() -1 ;
								if(!board.verifTab(x2,y2)) {
									System.out.println("Coordonnées incorrect");
								}
							}
							p2.modifyJpion(i, board.getPoints()[x2][y2]);
							System.out.println("pion placé");


						}


					}else if (placement == 2) {
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
					}else {
						System.out.println("Entrée incorrect");
					}
				}
				board.fileWriter();
				p1.fileWriter();
				p2.fileWriter();
				board.verifGraphe(board.getFormat(), p1, p2);
				System.out.println(board);

			}else {
				if(Player.fileIsEmpty() && Board.fileIsEmpty() || Player.fileIsEmpty() || Board.fileIsEmpty()) {
					System.out.println("Aucune sauvegarde");
					choise = -1;
				}else {
					board = Board.fileReader();
					p1 = p1.fileReaderPlayer(1, board);
					p2 = p2.fileReaderPlayer(2, board);	
					couche = board.getCouche();

				}

			}
		}
		while(board.getVictoryState() == 0) {
			if(p1 instanceof AiPlayer) {
				((AiPlayer) p1).play(board);
				board.verifGraphe(board.getFormat(), p1, p2);
				board.fileWriter();
				System.out.println(board);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println(p1 + " c'est ton tour");
				System.out.println("Que veux-tu faire ? \n(1) Déplacer un pion \n(2) Placer un piège");

				try {
					choise = scan.nextInt();						
				}catch(InputMismatchException e) {
					System.out.println("saisie incorrect");
					choise = -1;
				}



				if (choise == 1){
					board.playerMouvement(board,p1, scan);
				}else if (choise == 2) {
					int x = -1;
					int y = -1;
					boolean haveDmt = p1.getJTrap()[0].getTypeETrap() != ETraps.NONE || p1.getJTrap()[1].getTypeETrap() != ETraps.NONE || p1.getJTrap()[2].getTypeETrap() != ETraps.NONE;
					boolean haveRt = p1.getJTrap()[3].getTypeETrap() != ETraps.NONE || p1.getJTrap()[4].getTypeETrap() != ETraps.NONE || p1.getJTrap()[5].getTypeETrap() != ETraps.NONE;
					String trapStr = "";
					if (haveDmt) {
						trapStr += "\n(1) Don't Move Trap";
					}
					if (haveRt) {
						trapStr += "\n(2) Random Teleport";
					}
					if (!haveRt && !haveDmt) {
						System.out.println("Vous ne pouvez plus utiliser de pièges, vous n'en avez plus !");
						board.playerMouvement(board, p1, scan);
					}else {

						System.out.println("Quel piège voulez-vous utiliser ?" + trapStr);
						do {
							choise = scan.nextInt();
						} while (choise != 1 && choise != 2);

						if (haveDmt && choise == 1) {
							if (p1.getJTrap()[2].getTypeETrap() != ETraps.NONE)
								p1.getJTrap()[2].setTypeTrap(ETraps.NONE);
							else if (p1.getJTrap()[1].getTypeETrap() != ETraps.NONE)
								p1.getJTrap()[1].setTypeTrap(ETraps.NONE);
							else if (p1.getJTrap()[0].getTypeETrap() != ETraps.NONE)
								p1.getJTrap()[0].setTypeTrap(ETraps.NONE);

							System.out.println("Coordonnée de couche : ");
							x = scan.nextInt();																
							while(x>couche || x<1) {
								System.out.println("1 < " + x + "<" + couche);
								System.out.println("couche invalide 2");
								x = scan.nextInt();		
							}
							System.out.println("Coordonnée dans la couche : ");
							y = scan.nextInt();
							while (board.getFormat() == BoardFormat.SQUARE ? y > 8 : y > 6) {
								System.out.println("Coordonnée invalide");
								y = scan.nextInt();
							}

							Trap.DontMoveTrap(board.getPoints(), x-1, y-1, p2);
						} else if (haveRt && choise == 2)
						{
							if (p1.getJTrap()[5].getTypeETrap() != ETraps.NONE)
								p1.getJTrap()[5].setTypeTrap(ETraps.NONE);
							else if (p1.getJTrap()[4].getTypeETrap() != ETraps.NONE)
								p1.getJTrap()[4].setTypeTrap(ETraps.NONE);
							else if (p1.getJTrap()[3].getTypeETrap() != ETraps.NONE)
								p1.getJTrap()[3].setTypeTrap(ETraps.NONE);
							do {
								System.out.println("Coordonnée de la couche de la cible : ");
								x = scan.nextInt();							
							} while (x > couche && x <= 0);
							do {
								System.out.println("Coordonnée dans la couche de la cible : ");
								y = scan.nextInt();
								if(board.getPoints()[x-1][y-1].getColor().equals(p1.getColor())) {
									System.out.println("tu ne peux pas viser ton propre pion");
								}
							} while (board.getFormat() == BoardFormat.SQUARE ? y > 9 : y > 7);
							Trap.TeleportRandTrap(board, x-1, y-1, board.getFormat(), p2);								
							System.out.println("Piège placé à (" + x + "," + y +")");

						}
					}

				}

				board.verifGraphe(board.getFormat(), p1, p2);
			}

			//dmt.MAJTraps(board, couche, p1, p2); 

			if (board.getVictoryState() == 0) {
				if(p2 instanceof AiPlayer) {
					((AiPlayer) p2).play(board);					
					board.verifGraphe(board.getFormat(), p1, p2);
					System.out.println(board);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					System.out.println(board);
					System.out.println(p2 + " c'est ton tour");
					System.out.println("Que veux-tu faire ? \n(1) Déplacer un pion \n(2) Placer un piège");
					do {
						choise = scan.nextInt();
					} while (choise != 1 && choise != 2);

					if (choise == 1){
						board.playerMouvement(board,p2, scan);
					}else if (choise == 2) {
						int x = -1;
						int y = -1;
						boolean haveDmt = p2.getJTrap()[0].getTypeETrap() != ETraps.NONE || p2.getJTrap()[1].getTypeETrap() != ETraps.NONE || p2.getJTrap()[2].getTypeETrap() != ETraps.NONE;
						boolean haveRt = p2.getJTrap()[3].getTypeETrap() != ETraps.NONE || p2.getJTrap()[4].getTypeETrap() != ETraps.NONE || p2.getJTrap()[5].getTypeETrap() != ETraps.NONE;
						String trapStr = "";
						if (haveDmt) {
							trapStr += "\n(1) Don't Move Trap";
						}
						if (haveRt) {
							trapStr += "\n(2) Random Teleport";
						}
						System.out.println("Quel piège voulez-vous utiliser ?" + trapStr);
						do {
							choise = scan.nextInt();
						} while (choise != 1 && choise != 2);

						if (haveDmt && choise == 1) {
							if (p2.getJTrap()[2].getTypeETrap() != ETraps.NONE)
								p2.getJTrap()[2].setTypeTrap(ETraps.NONE);
							else if (p2.getJTrap()[1].getTypeETrap() != ETraps.NONE)
								p2.getJTrap()[1].setTypeTrap(ETraps.NONE);
							else if (p2.getJTrap()[0].getTypeETrap() != ETraps.NONE)
								p2.getJTrap()[0].setTypeTrap(ETraps.NONE);

							System.out.println("Coordonnée de couche : ");
							do {
								x = scan.nextInt();							
							} while (x > couche && x <= 0);
							System.out.println("Coordonnée dans la couche : ");
							do {
								y = scan.nextInt();
							} while (board.getFormat() == BoardFormat.SQUARE ? y > 8 : y > 6);

							Trap.DontMoveTrap(board.getPoints(), x-1, y-1, p1);
						} else if (haveRt && choise == 2)
						{
							if (p2.getJTrap()[5].getTypeETrap() != ETraps.NONE)
								p2.getJTrap()[5].setTypeTrap(ETraps.NONE);
							else if (p2.getJTrap()[4].getTypeETrap() != ETraps.NONE)
								p2.getJTrap()[4].setTypeTrap(ETraps.NONE);
							else if (p2.getJTrap()[3].getTypeETrap() != ETraps.NONE)
								p2.getJTrap()[3].setTypeTrap(ETraps.NONE);
							do {
								System.out.println("Coordonnée de la couche de la cible : ");
								x = scan.nextInt();							
							} while (x > couche && x <= 0);
							do {
								System.out.println("Coordonnée dans la couche de la cible : ");
								System.out.println("Coordonnée dans la couche de la cible : ");
								y = scan.nextInt();
								if(board.getPoints()[x-1][y-1].getColor().equals(p1.getColor())) {
									System.out.println("tu ne peux pas viser ton propre pion");
								}
							} while (board.getFormat() == BoardFormat.SQUARE ? y > 9 : y > 7);

							Trap.TeleportRandTrap(board, x-1, y-1, board.getFormat(), p1);
						}
						System.out.println("Piège placé à (" + x + "," + y +")"); 
					}
					Trap.ResetTrap(board.getPoints(), p1, p2, couche);
				}

				board.verifGraphe(board.getFormat(), p1, p2);
				board.fileWriter();
				p1.fileWriter();
				p2.fileWriter();
			}

		}
		if(board.getVictoryState() == 1){
			System.out.println("Bien joué " + p1.getName() + " tu as gagné !");
		}else {
			System.out.println("Bien joué " + p2.getName() + " tu as gagné !");
		}
		board.fileDelete();
		p1.fileDelete();
		p2.fileDelete();
		scan.close();
	}

	private static Color colorChoise(Scanner scan, Color pcolor) {
		Color[] tab = new Color[] {Color.ANSI_RED,Color.ANSI_CYAN,Color.ANSI_GREEN,Color.ANSI_PURPLE,Color.ANSI_YELLOW};
		int choise=-1;
		while(choise<0 || choise>5) {			
			for(int i=0;i<tab.length;i++) {
				if(tab[i]!=pcolor) {
					System.out.println((i+1)+". "+tab[i].getCode()+tab[i].getColorName()+Color.ANSI_RESET.getCode());
				}else {
					System.out.println(" ");
				}
			}
			if(scan.hasNextInt()) {
				choise=scan.nextInt();
			}else {
				scan.next();
				System.out.println("Mauvaise entrée, choisi entre les propositions");
				continue;

			}		
		}
		return tab[choise-1];	
	}




}
