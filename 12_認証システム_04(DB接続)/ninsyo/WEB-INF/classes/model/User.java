package model;
import java.io.Serializable;

/**
 * User情報(ID,PW)をもつJavaBeansモデル
 * @author N.Ono
 */
public class User implements Serializable{
    private int id;
    private String userId;
    private String userPw;
    private String name;
    private int auth;

    public User(){};
    public User(String userId,String userPw){
        this.userId = userId;
        this.userPw = userPw;
    }

    /**
     * IDを返す
     * @return ID
     */
    public int getId(){
        return id;
    }
    
    /**
     * ユーザーIDを返す
     * @return ユーザーID
     */
    public String getUserId(){
        return userId;
    }

    /**
     * ユーザーPWを返す
     * @return ユーザーPW
     */
    public String getUserPw(){
        return userPw;
    }

    /**
     * 名前を返す
     * @return 名前
     */
    public String getName(){
        return name;
    }

    /**
     * 権限を返す
     * @return 権限
     */
    public int getAuth(){
        return auth;
    }

    /**
     * IDをセットする
     * @param id ID
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * ユーザーIDをセットする
     * @param userId ユーザーID
     */
    public void setUserId(String userId){
        this.userId = userId;
    }

    /**
     * ユーザーPWをセットする
     * @param userPw ユーザーPW
     */
    public void setUserPw(String userPw){
        this.userPw = userPw;
    }

    /**
     * 名前をセットする
     * @param name 名前
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * 権限をセットする
     * @param auth 権限
     */
    public void setAuth(int auth){
        this.auth = auth;
    }
}