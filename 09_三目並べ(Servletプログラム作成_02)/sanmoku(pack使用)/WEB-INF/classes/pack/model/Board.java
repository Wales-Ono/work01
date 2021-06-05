package model;
import java.io.Serializable;

public class Board implements Serializable {
	private String cells0;
	private String cells1;
	private String cells2;
	private String mark;

    public Board(){
		this.cells0 = " ";
		this.cells1 = " ";
		this.cells2 = " ";
		this.mark = "○";
	}

    public Board(String cells0,String cells1,String cells2,String mark) {
    	// this.cells = new String[]{" "," "," "," "," "," "," "," "," "};
    	this.cells0 = cells0;
    	this.cells1 = cells1;
    	this.cells2 = cells2;
		this.mark = mark;
    }

	public String getCells0(){
		return cells0;
	}

	public String getCells1(){
		return cells1;
	}

	public String getCells2(){
		return cells2;
	}

	public String getMark(){
		return mark;
	}

	public void setCells0(String cells0){
		this.cells0 = cells0;
	}

	public void setCells1(String cells1){
		this.cells1 = cells1;
	}
	
	public void setCells2(String cells2){
		this.cells2 = cells2;
	}

	public void setMark(String mark){
		this.mark = mark;
	}

}
