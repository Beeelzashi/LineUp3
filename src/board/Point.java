package board;

import java.util.Random;
/**
 * 
 * @author Julien LALLOYER & Colin NEMEGHAIRE
 *
 */
public class Point {
	private Color color;	
	private Trap typetrap;
	private int x;
	private int y;
	private boolean isBlocked;
	/**
	 * constructor 
	 * @param x layer coordinate 
	 * @param y coordinate in layer
	 * @param c the color 
	 */
	public Point (int x, int y, Color c) {
		this.color = c;
		this.typetrap = new Trap(ETraps.NONE);
		this.x=x;
		this.y=y;
		this.isBlocked = false;
	}
	
	
	/**
	 * get the point color
	 * @return the color of the point
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * set a new point color 
	 * @param the new color
	 */
	public void setColor(Color color) {
		this.color = color;;
	}
	/**
	 * return the coordinates 
	 * @return the coordinate of the point
	 */
	public String getCoordonate() {
		return "("+this.x+","+this.y+")";
	}

	/**
	 * set new coordinates
	 * @param x the new layer coordinate 
	 * @param y the new coordinate in the layer
	 */
	public void setCoordnate(int x, int y) {
		this.x=x;
		this.y=y; 
		
	}
	
	/**
	 * set a new x (with a String)
	 * @param x the new layer coordinate
	 */
	public void setX(String x) {
		this.x = Integer.parseInt(x);
	}
	
	/**
	 * set a new y (with a String)
	 * @param y the new coordinate in the layer
	 */
	public void setY(String y) {
		this.y = Integer.parseInt(y);
	}
	
	/**
	 * return the coordinate x 
	 * @return return the layer coordinate 
	 */
	public int getX() {
		return x;
	}

	/**
	 * return the coordinate y
	 * @return the coordinate in the layer
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * look if the point is Blocked or not 
	 * @return if the point is blocked
	 */
	public boolean isBlocked() {
		return isBlocked;
	}
	
	/**
	 * change the state of the point (if it's blocked or not)
	 * @param isBlocked (if the point is blocked)
	 */
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	@Override
	public String toString() {
			return this.color.getCode()+this.getCoordonate()+Color.ANSI_RESET.getCode();		
	}
	/**
	 * return the type of trap
	 * @return the trap's type
	 */
	public Trap getTypetrap() {
		return typetrap;
	}
	
	/**
	 * change the type of the trap
	 * @param type the new type trap
	 */
	public void setTypeTrap(ETraps type) {
		this.typetrap = new Trap(type);
	}
	
	
	

}
