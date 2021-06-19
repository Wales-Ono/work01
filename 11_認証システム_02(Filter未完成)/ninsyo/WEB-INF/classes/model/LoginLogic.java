package model;

import java.util.ResourceBundle;

/**
 * ログインする際の権限を判定するモデル
 * @author N.Ono
 */
public class LoginLogic{

    // プロパティファイルのデータを取り出す
    private static ResourceBundle rb = ResourceBundle.getBundle("user"); //user.propatiesファイルを読み込む
    private static final String kanrisyaId = rb.getString("kanrisyaId");
    private static final String kanrisyaPw = rb.getString("kanrisyaPw");
    private static final String ippanId = rb.getString("ippanId");
    private static final String ippanPw = rb.getString("ippanPw");

    /**
     * 権限を判定して数字を返す. 
     * 数字が0のときは権限なし、1は管理者、2は一般を示す
     * @return 権限ナンバー
     * @param user ユーザー情報
     */
    public static int kengenJudge(User user){
        int kengenNum = 0;
        if (user.getId().equals(kanrisyaId) && user.getPw().equals(kanrisyaPw)) {
            kengenNum = 1;    
        } else if (user.getId().equals(ippanId) && user.getPw().equals(ippanPw)) {
            kengenNum = 2;
        }
        return kengenNum;
    }
}