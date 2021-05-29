package model;

public class Board {
	private String[] cells;
	
	public Board() {
		this.cells = new String[]{" "," "," "," "," "," "," "," "," "};
	}

	public String getCells(int index) {
		return cells[index];
	}
	
	public void setCells(int index,String mark) {
		this.cells[index] = mark;
	}
	
	public String[] getCells() {
		return cells;
	}
	

	public void setCells(String[] cells) {
		this.cells = cells;
	}
	
	
	
}
