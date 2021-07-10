package model;
import java.io.Serializable;

/**
 * 勤怠情報をもつモデル
 * @author N.Ono
 */
public class Attendance implements Serializable{
    private int id;
    private String date;
    private String begin;
    private String end;
    private String worktime;

	public Attendance(int id,String date,String begin,String end,String worktime){
		this.id = id;
		this.date = date;
		this.begin = begin;
		this.end = end;
		this.worktime = worktime;
	}

    /**
     * IDを返す
     * @return id
     */
	public int getId() {
		return this.id;
	}

    /**
     * idをセットする
     * @param id id
     */
	public void setId(int id) {
		this.id = id;
	}

    /**
     * 日付を返す
     * @return date
     */
	public String getDate() {
		return this.date;
	}

    /**
     * 日付をセットする
     * @param date 日付
     */
	public void setDate(String date) {
		this.date = date;
	}

    /**
     * 出勤時刻を返す
     * @return begin
     */
	public String getBegin() {
		return this.begin;
	}

    /**
     * 出勤時刻をセットする
     * @param begin 出勤時刻
     */
	public void setBegin(String begin) {
		this.begin = begin;
	}

    /**
     * 退勤時刻を返す
     * @return end
     */
	public String getEnd() {
		return this.end;
	}

    /**
     * 退勤時刻をセットする
     * @param end 退勤時刻
     */
	public void setEnd(String end) {
		this.end = end;
	}

    /**
     * 勤務時間を返す
     * @return worktime
     */
	public String getWorktime() {
		return this.worktime;
	}

    /**
     * 勤務時間をセットする
     * @param worktime 勤務時間
     */
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}       

}