package model;
import java.io.Serializable;

public class Board implements Serializable {
	private String[] cells;
	private String mark;

    public Board(){
		this.cells = new String[]{"　","　","　","　","　","　","　","　","　"};
		this.mark = "◯";
	}

	public String getCells(int index){
		return cells[index];
	}

	public String getMark(){
		return mark;
	}

	public void setCells(int index,String cell){
		this.cells[index] = cell;
	}

	public void setMark(String mark){
		this.mark = mark;
	}
}