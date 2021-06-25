package model;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;


/**
 * ログインする際の権限を判定するモデル
 * @author N.Ono
 */
public class LoginLogic{
    /**
     * DBのユーザーIDとユーザーパスワードが共に一致するものがあるかを検索する. 
     * 一致するものがある場合はユーザー情報を取得、セットして Userを返す。ない場合は null を返す。
     * @return User型変数user
     * @param user ユーザー情報
     */
    public static User findUser(User user){ 
        try {
            // TomcatからInitialContextを取得
            InitialContext initialContext = new InitialContext();
            
            // JNDIルックアップを行いデータソースを取得
            // TomcatのJNDIソースはすべてjava:comp/env/への相対パスとなる
            DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
            
            // データソースからコネクションを取得
            Connection con = ds.getConnection();

            // DBのユーザーIDとユーザーパスワードが共に一致するものがあるかを検索
            String sql = "SELECT * FROM user WHERE user_id=? AND user_pw=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,user.getUserId());
            ps.setString(2,user.getUserPw());
            ResultSet rs = ps.executeQuery();

            // 一致するものがある場合は userを返す。ない場合は null を返す。
            if (rs.next()){
                // ユーザー情報をひとつずつ取得
                int id = rs.getInt("id");
                String userId = rs.getString("user_id");
                String userPw = rs.getString("user_pw");
                String name = rs.getString("name");
                int auth = rs.getInt("auth");

                // 取得したものを user にセット
                user.setId(id);
                user.setUserId(userId);
                user.setUserPw(userPw);
                user.setName(name);
                user.setAuth(auth);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQLExceptionが発生");
        }catch (NamingException e) {
            System.out.println("NamingExcepionが発生");
        }
        return user;
    }
}