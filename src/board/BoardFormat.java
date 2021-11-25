package board;

public enum BoardFormat {
	SQUARE("boards/square_"), TRIANGLE("boards/triangle_");

	private String path;
	
	BoardFormat(String string) {
		this.path=string; 
	}
	public String getPath() {
		return path;
	}
	
	
}
