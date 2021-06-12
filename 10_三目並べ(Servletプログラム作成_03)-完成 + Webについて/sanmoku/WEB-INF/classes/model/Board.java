package model;
import java.io.Serializable;

public class Board implements Serializable {
	private String[] cells;
	private String mark;
	private int count;

    public Board(){
		this.cells = new String[]{"　","　","　","　","　","　","　","　","　"};
		this.mark = "◯";
		this.count = 0;
	}

	public String getCells(int index){
		return cells[index];
	}

	public String getMark(){
		return mark;
	}

	public int getCount(){
		return count;
	}

	public void setCells(int index,String cell){
		this.cells[index] = cell;
	}

	public void setMark(String mark){
		this.mark = mark;
	}

	public void setCount(int count){
		this.count = count;
	}

	public void countUp(){
		this.count++;
	}
}