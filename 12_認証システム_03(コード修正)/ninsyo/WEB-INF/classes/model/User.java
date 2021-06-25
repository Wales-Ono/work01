package model;
import java.io.Serializable;

/**
 * User情報(ID,PW)をもつJavaBeansモデル
 * @author N.Ono
 */
public class User implements Serializable{
    private String id;
    private String pw;
    private int kengenNum;

    public User(){};
    public User(String id,String pw){
        this.id = id;
        this.pw = pw;
    }

    /**
     * ID情報を返す
     * @return ID情報
     */
    public String getId(){
        return id;
    }

    /**
     * PW情報を返す
     * @return PW情報
     */
    public String getPw(){
        return pw;
    }

    /**
     * 権限情報を返す
     * @return 権限によって割り振られた数字
     */
    public int getKengenNum(){
        return kengenNum;
    }

    /**
     * ID情報をセットする
     * @param id ID情報
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * PW情報をセットする
     * @param pw PW情報
     */
    public void setPw(String pw){
        this.pw = pw;
    }

    /**
     * 権限情報をセットする
     * @param kengenNum 権限情報
     */
    public void setKengenNum(int kengenNum){
        this.kengenNum = kengenNum;
    }
}