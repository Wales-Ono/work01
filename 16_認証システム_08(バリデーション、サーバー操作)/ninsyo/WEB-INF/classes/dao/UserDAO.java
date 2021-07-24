package dao;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import model.User;

/**
 * ユーザー情報に関してDB接続処理をするDAO
 * @author N.Ono
 */
public class UserDAO {
    
    /**
     * DBのユーザーIDとユーザーパスワードが共に一致するものがあるかを検索する. 
     * 一致するものがある場合はユーザー情報を取得、セットして Userを返す。ない場合は null を返す。
     * @return user
     * @param user ユーザー情報
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static User findUser(User user) throws SQLException,NamingException{ 
        // TomcatからInitialContextを取得
        InitialContext initialContext = new InitialContext();
        
        // JNDIルックアップを行いデータソースを取得
        // TomcatのJNDIソースはすべてjava:comp/env/への相対パスとなる
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        
        // データソースからコネクションを取得
        Connection con = ds.getConnection();

        // DBのユーザーIDとユーザーパスワードが共に一致するものがあるかを検索
        String sql = "SELECT * FROM ninsyo.users INNER JOIN ninsyo.auths ON users.auth_id = auths.auth_id WHERE users.user_id=? AND users.user_pw=?;";
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
            int authId = rs.getInt("auth_id");
            String auth = rs.getString("auth");
            String mailAddress = rs.getString("mail_address");
            String phoneNumber = rs.getString("phone_number");
            String postalCode = rs.getString("postal_code");
            String address01 = rs.getString("address_01");
            String address02 = rs.getString("address_02");

            // 取得したものを user にセット
            user.setId(id);
            user.setUserId(userId);
            user.setUserPw(userPw);
            user.setName(name);
            user.setAuthId(authId);
            user.setAuth(auth);
            user.setMailAddress(mailAddress);
            user.setPhoneNumber(phoneNumber);
            user.setPostalCode(postalCode);
            user.setAddress01(address01);
            user.setAddress02(address02);

        } else {
            return null;
        }
        rs.close();
        ps.close();
        con.close();

        return user;
    }

    /**
     * idからユーザー情報を取得する
     * @param id ID
     * @return user
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static User getByUserId(int id) throws SQLException,NamingException{
        User user = null;

        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        Connection con = ds.getConnection();

        // idに当てはまるユーザー情報を取得
        String sql = "SELECT * FROM ninsyo.users INNER JOIN ninsyo.auths ON users.auth_id = auths.auth_id WHERE id=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        // DBから読み込む
        rs.next();
        String name = rs.getString("name");
        String userId = rs.getString("user_id");
        String userPw = rs.getString("user_pw");
        int authId = rs.getInt("auth_id");
        String auth = rs.getString("auth");
        String mailAddress = rs.getString("mail_address");
        String phoneNumber = rs.getString("phone_number");
        String postalCode = rs.getString("postal_code");
        String address01 = rs.getString("address_01");
        String address02 = rs.getString("address_02");

        // 読み込んだ情報をセット
        user = new User(id,name,userId,userPw,authId,auth,mailAddress,phoneNumber,postalCode,address01,address02);

        rs.close();
        ps.close();
        con.close();
        
        return user;
    }            

    /**
     * ユーザー情報の一覧を取得する
     * @return userList
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static List<User> getUserList() throws SQLException,NamingException{
        // ユーザー情報を格納するためのリストを用意
        List<User> userList = new ArrayList<>();
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        Connection con = ds.getConnection();

        // 一覧を表示(usersテーブルとauthsテーブルをJOIN)
        String sql = "SELECT * FROM ninsyo.users INNER JOIN ninsyo.auths ON users.auth_id = auths.auth_id;";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        // DBから読み込み
        while (rs.next()){
            // ユーザー情報をひとつずつ取得
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String userId = rs.getString("user_id");
            String userPw = rs.getString("user_pw");
            int authId = rs.getInt("auth_id");
            String auth = rs.getString("auth");
            String mailAddress = rs.getString("mail_address");
            String phoneNumber = rs.getString("phone_number");
            String postalCode = rs.getString("postal_code");
            String address01 = rs.getString("address_01");
            String address02 = rs.getString("address_02");
            
            // ユーザー情報をセット
            User user = new User(id,name,userId,userPw,authId,auth,mailAddress,phoneNumber,postalCode,address01,address02);

            // user を userList に格納
            userList.add(user);

        }
        rs.close();
        st.close();
        con.close();

        return userList;
    }

    /**
     * ユーザー情報をDBに追加する
     * @param user ユーザー情報
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static void userAdd(User user) throws SQLException,NamingException{
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");            
        Connection con = ds.getConnection();

        // ユーザーをDBに追加 (0はprimarykeyを設定)
        String sql = "INSERT INTO ninsyo.users(name,user_id,user_pw,auth_id,mail_address,phone_number,postal_code,address_01,address_02) VALUES (?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,user.getName());
        ps.setString(2,user.getUserId());
        ps.setString(3,user.getUserPw());
        ps.setInt(4,user.getAuthId());
        ps.setString(5,user.getMailAddress());
        ps.setString(6,user.getPhoneNumber());
        ps.setString(7,user.getPostalCode());
        ps.setString(8,user.getAddress01());
        ps.setString(9,user.getAddress02());

        ps.executeUpdate();
        ps.close();
        con.close();
    }    

    /**
     * DBのユーザー情報を編集する
     * @param user ユーザー情報
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static void userEdit(User user) throws SQLException,NamingException{
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");            
        Connection con = ds.getConnection();

        // DBのユーザー情報を書き換える
        String sql = "UPDATE ninsyo.users SET name=?,user_id=?,user_pw=?,auth_id=?,mail_address=?,phone_number=?,postal_code=?,address_01=?,address_02=? WHERE id=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,user.getName());
        ps.setString(2,user.getUserId());
        ps.setString(3,user.getUserPw());
        ps.setInt(4,user.getAuthId());
        ps.setString(5,user.getMailAddress());
        ps.setString(6,user.getPhoneNumber());
        ps.setString(7,user.getPostalCode());
        ps.setString(8,user.getAddress01());
        ps.setString(9,user.getAddress02());
        ps.setInt(10,user.getId());
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    /**
     * DBのユーザー情報を削除する
     * @param id ID情報
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static void userDel(int id) throws SQLException,NamingException{
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");            
        Connection con = ds.getConnection();
            
        // IDに対応するユーザーを削除
        String sql = "DELETE FROM users WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
        ps.close();
        con.close();
    }    
}