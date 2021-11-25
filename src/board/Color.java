package board;

public enum Color {

	ANSI_RESET("\u001B[0m","RESET"),ANSI_RED("\u001B[31m","Rouge"),ANSI_GREEN("\u001B[32m","Vert"),ANSI_YELLOW("\u001B[33m","Jaune"),ANSI_PURPLE("\u001B[35m","Violet"),ANSI_CYAN("\u001B[36m","Cyan"),ANSI_WHITE("\u001B[37m","Blanc");
	private String code;
	private String colorName;
	
	/**
	 * Constructor
	 * @param string code of the color
	 * @param string2 naùe of the color
	 */
	Color(String string, String string2) { 
		this.code=string;
		this.colorName=string2;
	}
	/**
	 * set the color type using the name 
	 * @param string the color name 
	 */
	public void setColor(String string){
		this.colorName = string;
		if(string.equals("Rouge")) {
			this.code = "\u001B[31m";
		}else if(string.equals("Vert")) {
			this.code = "\u001B[32m";
		}else if(string.equals("Jaune")) {
			this.code = "\u001B[33m";
		}else if(string.equals("Cyan")){
			this.code = "\u001B[36m";
		}else{
			this.code = "\u001B[35m";
		}
	}

	
	/**
	 * return the code of the color
	 * @return the code of the color 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * return the name of the color 
	 * @return the name of the color 
	 */
	public String getColorName() {
		return colorName;
	}
	
	public String toString() {
		return this.colorName;
	}




}
