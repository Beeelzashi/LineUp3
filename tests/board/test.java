package board;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;



import org.junit.jupiter.api.BeforeEach;



class test {



	Board boards,boardt;
	Player pl1, pl2;

	//=========Board.java Test====================================================================

	@BeforeEach	
	public void initialization() {

		boards = new Board(BoardFormat.SQUARE,3,false);
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		pl1 = new Player("J1", Color.ANSI_RED, 1);
		pl2 = new Player("J2", Color.ANSI_CYAN, 2);

	}


	@Test
	void squaredTest() {
		assertEquals(boards.getFormat(), BoardFormat.SQUARE);
		assertEquals(boardt.getFormat(), BoardFormat.TRIANGLE);
	}

	@Test
	void colorTest() {
		assertEquals(boards.getPoints()[0][0].getColor(), Color.ANSI_WHITE);
		boards.getPoints()[0][0].setColor(Color.ANSI_RED);
		assertEquals(boards.getPoints()[0][0].getColor(), Color.ANSI_RED);
	}

	@Test
	void coordonateTest() {
		assertEquals(boards.getPoints()[0][0].getCoordonate(), "(1,1)");
		assertEquals(boards.getPoints()[2][3].getCoordonate(), "(3,4)"); //each coordonate +1 compared to table coordonate because of game requiered
		Point p = new Point(0, 0, Color.ANSI_WHITE);	
		assertEquals(p.getCoordonate(), "(0,0)");
		p.setCoordnate(2, 4);
		assertEquals(p.getCoordonate(), "(2,4)");
	}

	@Test
	void testDontMoveTrap ()
	{
		Board boardSquare = new Board(BoardFormat.SQUARE,3,false);
		Point p = new Point(2, 0, Color.ANSI_RED);
		boardSquare.changeColor(p.getX(), p.getY(), p.getColor());
		assertFalse(p.isBlocked());
		p.setBlocked(true);
		assertTrue(p.isBlocked());
	}

	@Test
	void testVictorySquare()
	{
		assertEquals(BoardFormat.SQUARE,boards.getFormat());
		Point p1, p2, p3, p4, p5, p6;

		//Test 1
		p1 = new Point(2, 0, Color.ANSI_RED);
		p2 = new Point(2, 1, Color.ANSI_RED);
		p3 = new Point(2, 2, Color.ANSI_RED);
		p4 = new Point(0, 1, Color.ANSI_CYAN);
		p5 = new Point(1, 1, Color.ANSI_CYAN);
		p6 = new Point(0, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 2
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(2, 2, Color.ANSI_RED);
		p2 = new Point(2, 3, Color.ANSI_RED);
		p3 = new Point(2, 4, Color.ANSI_RED);
		p4 = new Point(0, 1, Color.ANSI_CYAN);
		p5 = new Point(1, 1, Color.ANSI_CYAN);
		p6 = new Point(0, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 3
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(2, 4, Color.ANSI_RED);
		p2 = new Point(2, 5, Color.ANSI_RED);
		p3 = new Point(2, 6, Color.ANSI_RED);
		p4 = new Point(0, 1, Color.ANSI_CYAN);
		p5 = new Point(1, 1, Color.ANSI_CYAN);
		p6 = new Point(0, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 4
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(2, 6, Color.ANSI_RED);
		p2 = new Point(2, 7, Color.ANSI_RED);
		p3 = new Point(2, 0, Color.ANSI_RED);
		p4 = new Point(0, 1, Color.ANSI_CYAN);
		p5 = new Point(1, 1, Color.ANSI_CYAN);
		p6 = new Point(0, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 5
		p1 = new Point(1, 0, Color.ANSI_RED);
		p2 = new Point(1, 1, Color.ANSI_RED);
		p3 = new Point(1, 2, Color.ANSI_RED);
		p4 = new Point(0, 1, Color.ANSI_CYAN);
		p5 = new Point(2, 1, Color.ANSI_CYAN);
		p6 = new Point(0, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 6
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(1, 2, Color.ANSI_RED);
		p2 = new Point(1, 3, Color.ANSI_RED);
		p3 = new Point(1, 4, Color.ANSI_RED);
		p4 = new Point(0, 1, Color.ANSI_CYAN);
		p5 = new Point(2, 1, Color.ANSI_CYAN);
		p6 = new Point(0, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 7
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(1, 4, Color.ANSI_RED);
		p2 = new Point(1, 5, Color.ANSI_RED);
		p3 = new Point(1, 6, Color.ANSI_RED);
		p4 = new Point(0, 1, Color.ANSI_CYAN);
		p5 = new Point(2, 1, Color.ANSI_CYAN);
		p6 = new Point(0, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 8
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(1, 6, Color.ANSI_RED);
		p2 = new Point(1, 7, Color.ANSI_RED);
		p3 = new Point(1, 0, Color.ANSI_RED);
		p4 = new Point(0, 1, Color.ANSI_CYAN);
		p5 = new Point(2, 1, Color.ANSI_CYAN);
		p6 = new Point(0, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 9
		p1 = new Point(0, 0, Color.ANSI_RED);
		p2 = new Point(0, 1, Color.ANSI_RED);
		p3 = new Point(0, 2, Color.ANSI_RED);
		p4 = new Point(1, 1, Color.ANSI_CYAN);
		p5 = new Point(2, 1, Color.ANSI_CYAN);
		p6 = new Point(2, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 10
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(0, 2, Color.ANSI_RED);
		p2 = new Point(0, 3, Color.ANSI_RED);
		p3 = new Point(0, 4, Color.ANSI_RED);
		p4 = new Point(1, 1, Color.ANSI_CYAN);
		p5 = new Point(2, 1, Color.ANSI_CYAN);
		p6 = new Point(2, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 11
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(0, 4, Color.ANSI_RED);
		p2 = new Point(0, 5, Color.ANSI_RED);
		p3 = new Point(0, 6, Color.ANSI_RED);
		p4 = new Point(1, 1, Color.ANSI_CYAN);
		p5 = new Point(2, 1, Color.ANSI_CYAN);
		p6 = new Point(2, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 12
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(0, 6, Color.ANSI_RED);
		p2 = new Point(0, 7, Color.ANSI_RED);
		p3 = new Point(0, 0, Color.ANSI_RED);
		p4 = new Point(1, 1, Color.ANSI_CYAN);
		p5 = new Point(2, 1, Color.ANSI_CYAN);
		p6 = new Point(2, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 13
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(0, 1, Color.ANSI_RED);
		p2 = new Point(1, 1, Color.ANSI_RED);
		p3 = new Point(2, 1, Color.ANSI_RED);
		p4 = new Point(0, 0, Color.ANSI_CYAN);
		p5 = new Point(2, 4, Color.ANSI_CYAN);
		p6 = new Point(2, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 14
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(0, 3, Color.ANSI_RED);
		p2 = new Point(1, 3, Color.ANSI_RED);
		p3 = new Point(2, 3, Color.ANSI_RED);
		p4 = new Point(0, 0, Color.ANSI_CYAN);
		p5 = new Point(2, 4, Color.ANSI_CYAN);
		p6 = new Point(2, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 15
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(0, 5, Color.ANSI_RED);
		p2 = new Point(1, 5, Color.ANSI_RED);
		p3 = new Point(2, 5, Color.ANSI_RED);
		p4 = new Point(0, 0, Color.ANSI_CYAN);
		p5 = new Point(2, 4, Color.ANSI_CYAN);
		p6 = new Point(2, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);

		//Test 16
		boards = new Board(BoardFormat.SQUARE,3,false);
		p1 = new Point(0, 7, Color.ANSI_RED);
		p2 = new Point(1, 7, Color.ANSI_RED);
		p3 = new Point(2, 7, Color.ANSI_RED);
		p4 = new Point(0, 0, Color.ANSI_CYAN);
		p5 = new Point(2, 4, Color.ANSI_CYAN);
		p6 = new Point(2, 0, Color.ANSI_CYAN);

		boards.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boards.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boards.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boards.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boards.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boards.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boards.verifGraphe(boards.getFormat(), pl1, pl2);

		assertTrue(boards.getVictoryState() == 1);
	}

	@Test
	void testVictoryTriangle()
	{
		assertEquals(BoardFormat.TRIANGLE,boardt.getFormat());
		Point p1, p2, p3, p4, p5, p6;

		//Test 1
		p1 = new Point(2, 0, pl1.getColor());
		p2 = new Point(2, 1, pl1.getColor());
		p3 = new Point(2, 2, pl1.getColor());
		p4 = new Point(0, 1, pl2.getColor());
		p5 = new Point(1, 1, pl2.getColor());
		p6 = new Point(0, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 2
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(2, 2, pl1.getColor());
		p2 = new Point(2, 3, pl1.getColor());
		p3 = new Point(2, 4, pl1.getColor());
		p4 = new Point(0, 1, pl2.getColor());
		p5 = new Point(1, 1, pl2.getColor());
		p6 = new Point(0, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 3
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(2, 0, pl1.getColor());
		p2 = new Point(2, 4, pl1.getColor());
		p3 = new Point(2, 5, pl1.getColor());
		p4 = new Point(0, 1, pl2.getColor());
		p5 = new Point(1, 1, pl2.getColor());
		p6 = new Point(0, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 4
		p1 = new Point(1, 0, pl1.getColor());
		p2 = new Point(1, 1, pl1.getColor());
		p3 = new Point(1, 2, pl1.getColor());
		p4 = new Point(0, 1, pl2.getColor());
		p5 = new Point(2, 1, pl2.getColor());
		p6 = new Point(0, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 5
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(1, 2, pl1.getColor());
		p2 = new Point(1, 3, pl1.getColor());
		p3 = new Point(1, 4, pl1.getColor());
		p4 = new Point(0, 1, pl2.getColor());
		p5 = new Point(2, 1, pl2.getColor());
		p6 = new Point(0, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 6
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(1, 0, pl1.getColor());
		p2 = new Point(1, 5, pl1.getColor());
		p3 = new Point(1, 4, pl1.getColor());
		p4 = new Point(0, 1, pl2.getColor());
		p5 = new Point(2, 1, pl2.getColor());
		p6 = new Point(0, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 7
		p1 = new Point(0, 0, pl1.getColor());
		p2 = new Point(0, 1, pl1.getColor());
		p3 = new Point(0, 2, pl1.getColor());
		p4 = new Point(1, 1, pl2.getColor());
		p5 = new Point(2, 1, pl2.getColor());
		p6 = new Point(2, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 8
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(0, 2, pl1.getColor());
		p2 = new Point(0, 3, pl1.getColor());
		p3 = new Point(0, 4, pl1.getColor());
		p4 = new Point(1, 1, pl2.getColor());
		p5 = new Point(2, 1, pl2.getColor());
		p6 = new Point(2, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 9
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(0, 0, pl1.getColor());
		p2 = new Point(0, 5, pl1.getColor());
		p3 = new Point(0, 4, pl1.getColor());
		p4 = new Point(0, 1, pl2.getColor());
		p5 = new Point(2, 1, pl2.getColor());
		p6 = new Point(2, 0, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 1);

		//Test 10
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(2, 0, pl1.getColor());
		p2 = new Point(2, 2, pl1.getColor());
		p3 = new Point(2, 4, pl1.getColor());
		p4 = new Point(0, 3, pl2.getColor());
		p5 = new Point(1, 3, pl2.getColor());
		p6 = new Point(2, 3, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 2);

		//Test 11
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(2, 0, pl1.getColor());
		p2 = new Point(2, 2, pl1.getColor());
		p3 = new Point(2, 4, pl1.getColor());
		p4 = new Point(0, 1, pl2.getColor());
		p5 = new Point(1, 1, pl2.getColor());
		p6 = new Point(2, 1, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 2);

		//Test 12
		boardt = new Board(BoardFormat.TRIANGLE,3,false);
		p1 = new Point(2, 0, pl1.getColor());
		p2 = new Point(2, 2, pl1.getColor());
		p3 = new Point(2, 4, pl1.getColor());
		p4 = new Point(0, 5, pl2.getColor());
		p5 = new Point(1, 5, pl2.getColor());
		p6 = new Point(2, 5, pl2.getColor());

		boardt.changeColor(p1.getX(), p1.getY(), p1.getColor());
		boardt.changeColor(p2.getX(), p2.getY(), p2.getColor());
		boardt.changeColor(p3.getX(), p3.getY(), p3.getColor());
		boardt.changeColor(p4.getX(), p4.getY(), p4.getColor());
		boardt.changeColor(p5.getX(), p5.getY(), p5.getColor());
		boardt.changeColor(p6.getX(), p6.getY(), p6.getColor());

		boardt.verifGraphe(boardt.getFormat(), pl1, pl2);

		assertTrue(boardt.getVictoryState() == 2);
	}


	@Test
	void addTest() {
		Point p = new Point(0, 0, Color.ANSI_RED);
		assertEquals(boards.getPoints()[0][0].getColor(), Color.ANSI_WHITE);
		boards.changeColor(p.getX(), p.getY(), p.getColor());
		assertEquals(boards.getPoints()[0][0].getColor(), Color.ANSI_RED);

	}

	@Test
	void verifTabTest() {
		boards.changeColor(0, 0, Color.ANSI_RED);
		assertTrue(boards.verifTab(1, 2));
		assertTrue(boardt.verifTab(1, 2));
		assertFalse(boards.verifTab(0, 0));
		assertFalse(boards.verifTab(60, 20));
		assertFalse(boardt.verifTab(60, 20));
	}
	@Test
	void checkMouvementLayer() {
		assertFalse(boards.checkMouvementLayer(3));
		assertFalse(boards.checkMouvementLayer(5));
		assertTrue(boards.checkMouvementLayer(2));
		assertTrue(boards.checkMouvementLayer(6));
	}



	@Test
	void testCheckMouvement() {
		Point p1 = new Point(0, 0, Color.ANSI_RED);
		Point p2 = new Point(1, 1, Color.ANSI_RED);
		Point p3 = new Point(1, 2, Color.ANSI_RED);
		Player j1 = new Player("test", Color.ANSI_RED, 1);
		j1.modifyJpion(0, p1);
		j1.modifyJpion(1, p2);
		j1.modifyJpion(2, p3);
		assertTrue(boards.checkMouvement(0, 1, j1, 1 ));
		assertFalse(boards.checkMouvement(0, 1, j1, 0));
		assertTrue(boards.checkMouvement(0, 1, j1, 1));
		assertTrue(boards.checkMouvement(0, 1, j1, 1));
		assertTrue(boards.checkMouvement(1, 0, j1, 0));
	}



	@Test
	void bougerPionTest() {
		Point p1 = new Point(1, 1, Color.ANSI_RED);
		Point p2 = new Point(2, 2, Color.ANSI_RED);
		Point p3 = new Point(2, 3, Color.ANSI_RED);
		System.out.println(boards);
		Player j1 = new Player("test", Color.ANSI_RED, 1);
		j1.modifyJpion(0, p1);
		j1.modifyJpion(1, p2);
		j1.modifyJpion(2, p3);
		assertTrue(boards.movePlayerPoint('d', j1, 0));
		assertTrue(boards.movePlayerPoint('d', j1, 1));
		assertTrue(boards.movePlayerPoint('d', j1, 1));
		assertTrue(boards.movePlayerPoint('s', j1, 1));
		System.out.println(boards.toString());
	}

	//================Trap.java test===============================================
	@Test
	void testTeleportRandTrap()
	{
		Point p1_1 = new Point(1, 2, Color.ANSI_RED);
		Point p2_1 = new Point(1, 3, Color.ANSI_RED);
		Point p3_1 = new Point(1, 1, Color.ANSI_RED);

		Point p1_2 = new Point(1, 6, Color.ANSI_GREEN);
		Point p2_2 = new Point(1, 2, Color.ANSI_GREEN);
		Point p3_2 = new Point(2, 4, Color.ANSI_GREEN);

		boards.changeColor(p1_1.getX(), p1_1.getY(), p1_1.getColor());
		boards.changeColor(p2_1.getX(), p2_1.getY(), p2_1.getColor());
		boards.changeColor(p3_1.getX(), p3_1.getY(), p3_1.getColor());

		boards.changeColor(p1_2.getX(), p1_2.getY(), p1_2.getColor());
		boards.changeColor(p2_2.getX(), p2_2.getY(), p2_2.getColor());
		boards.changeColor(p3_2.getX(), p3_2.getY(), p3_2.getColor());

		Player player1 = new Player("Oui", Color.ANSI_RED, 1);
		Player player2 = new Player("Le fromage", Color.ANSI_GREEN, 2);
		player1.modifyJpion(0, p1_1);
		player1.modifyJpion(1, p2_1);
		player1.modifyJpion(2, p3_1);

		player2.modifyJpion(0, p1_2);
		player2.modifyJpion(1, p2_2);
		player2.modifyJpion(2, p3_2);


		Trap trap = new Trap (ETraps.TELEPORT);

		assertTrue(trap.getTypeETrap() == ETraps.TELEPORT);

		boards.getPoints()[1][0].setTypeTrap(trap.getTypeETrap());
		player1.getjPion()[0].setCoordnate(2, 1);
		boards.changeColor(2, 0, Color.ANSI_WHITE);
		boards.changeColor(player1.getjPion()[0].getX()-1, player1.getjPion()[0].getY()-1, player1.getColor());

		assertFalse(boards.getPoints()[1][0].isBlocked());

		assertEquals(boards.getPoints()[1][0].getCoordonate(), player1.getjPion()[0].getCoordonate());
	}


}
