package model;
import java.io.Serializable;

/**
 * User情報(ID,PW)をもつモデル
 * @author N.Ono
 */
public class User implements Serializable{
    private int id;
    private String name;
    private String userId;
    private String userPw;
    private int authId;
    private String auth;
    private String mailAddress;
    private String phoneNumber;
    private String postalCode;
    private String address01;
    private String address02;

    public User(){};
    public User(String userId,String userPw){
        this.userId = userId;
        this.userPw = userPw;
    }

    public User(String name,String userId,String userPw,int authId){
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
        this.authId = authId;
    }

    public User(int id,String name,String userId,String userPw,int authId){
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
        this.authId = authId;
    }

    public User(int id,String name,String userId,String userPw,int authId,String auth){
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
        this.authId = authId;
        this.auth = auth;
    }

    public User(String name,String userId,String userPw,int authId,String mailAddress,String phoneNumber,String postalCode,String address01,String address02) {
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
        this.authId = authId;
        this.mailAddress = mailAddress;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.address01 = address01;
        this.address02 = address02;
    }

    public User(int id,String name,String userId,String userPw,int authId,String mailAddress,String phoneNumber,String postalCode,String address01,String address02) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
        this.authId = authId;
        this.mailAddress = mailAddress;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.address01 = address01;
        this.address02 = address02;
    }

    public User(int id,String name,String userId,String userPw,int authId,String auth,String mailAddress,String phoneNumber,String postalCode,String address01,String address02) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
        this.authId = authId;
        this.auth = auth;
        this.mailAddress = mailAddress;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.address01 = address01;
        this.address02 = address02;
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return this.userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public int getAuthId() {
		return this.authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress01() {
		return this.address01;
	}

	public void setAddress01(String address01) {
		this.address01 = address01;
	}

	public String getAddress02() {
		return this.address02;
	}

	public void setAddress02(String address02) {
		this.address02 = address02;
	}


    // /**
    //  * IDを返す
    //  * @return id
    //  */
    // public int getId(){
    //     return id;
    // }

    // /**
    //  * 名前を返す
    //  * @return name
    //  */
    // public String getName(){
    //     return name;
    // }

    // /**
    //  * ユーザーIDを返す
    //  * @return userId
    //  */
    // public String getUserId(){
    //     return userId;
    // }

    // /**
    //  * ユーザーPWを返す
    //  * @return userPw
    //  */
    // public String getUserPw(){
    //     return userPw;
    // }

    // /**
    //  * 権限IDを返す
    //  * @return authId
    //  */
    // public int getAuthId(){
    //     return authId;
    // }

    // /**
    //  * 権限を返す
    //  * @return auth
    //  */
    // public String getAuth(){
    //     return auth;
    // }

    // /**
    //  * IDをセットする
    //  * @param id ID
    //  */
    // public void setId(int id){
    //     this.id = id;
    // }

    // /**
    //  * 名前をセットする
    //  * @param name 名前
    //  */
    // public void setName(String name){
    //     this.name = name;
    // }
    
    // /**
    //  * ユーザーIDをセットする
    //  * @param userId ユーザーID
    //  */
    // public void setUserId(String userId){
    //     this.userId = userId;
    // }

    // /**
    //  * ユーザーPWをセットする
    //  * @param userPw ユーザーPW
    //  */
    // public void setUserPw(String userPw){
    //     this.userPw = userPw;
    // }

    // /**
    //  * 権限IDをセットする
    //  * @param authId 権限ID
    //  */
    // public void setAuthId(int authId){
    //     this.authId = authId;
    // }

    // /**
    //  * 権限をセットする
    //  * @param auth 権限
    //  */
    // public void setAuth(String auth){
    //     this.auth = auth;
    // }
}