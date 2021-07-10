package dao;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.text.*;
import java.time.*;
import model.Attendance;

/**
 * 勤怠情報に関してDB接続処理をするDAO
 * @author N.Ono
 */
public class AttendanceDAO {

    /**
     * 勤怠の一覧を取得する
     * @return attendanceList
     * @param id ログインしているユーザーのID
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係         
     */
    public static List<Attendance> getAttendanceList(int id) throws SQLException,NamingException{
        // ユーザー情報を格納するためのリストを用意
        List<Attendance> attendanceList = new ArrayList<>();
        
        // DB接続
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        Connection con = ds.getConnection();

        // 一覧を表示(usersテーブルとauthsテーブルをJOIN)
        String sql = "SELECT * FROM attendances INNER JOIN users ON attendances.id = users.id WHERE attendances.id=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        // DBから読み込み
        while (rs.next()){
            // ユーザー情報をひとつずつ取得
            String date = rs.getString("date");
            String begin = rs.getString("begin");
            String end = rs.getString("end");
            String worktime = rs.getString("worktime");
            
            // ユーザー情報をセット
            Attendance attendance = new Attendance(id,date,begin,end,worktime);

            attendanceList.add(attendance);
        }
        rs.close();
        ps.close();
        con.close();

        return attendanceList;
    }


    /**
     * 出勤時刻の打刻
     * @param id ログインしているユーザーのID
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係    
     */
    public static void punchBegin(int id) throws SQLException,NamingException{
        // DB接続
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        Connection con = ds.getConnection();

        // 現在日時を取得(Date型、Time型)
        java.sql.Date today = java.sql.Date.valueOf(LocalDate.now());
        java.sql.Time now = java.sql.Time.valueOf(LocalTime.now());


        // 日付"date" と 出勤時刻"begin" を打刻
        String sql = "INSERT INTO attendances (id,date,begin,end,worktime) values (?,?,?,'00:00:00','00:00:00');";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ps.setDate(2,today);
        ps.setTime(3,now);
        ps.executeUpdate();

        ps.close();
        con.close();
    }

    /**
     * 退勤時刻の打刻
     * @param id ログインしているユーザーのID
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static void punchEnd(int id) throws SQLException,NamingException{
        // DB接続
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        Connection con = ds.getConnection();

        // 現在時刻を取得(Time型)
        java.sql.Time now = java.sql.Time.valueOf(LocalTime.now());

        // 退勤時刻"end"を打刻(最も新しいレコードのものを書き換え)
        String sql = "UPDATE attendances SET end=? WHERE id=? ORDER BY attendance_id DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setTime(1,now);
        ps.setInt(2,id);
        ps.executeUpdate();

        ps.close();
        con.close();
    }

    /**
     * 勤務時間の打刻
     * @param id ログインしているユーザーのID      
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static void punchWorktime(int id) throws SQLException,NamingException{
        // DB接続
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        Connection con = ds.getConnection();

        // 最も新しいレコードの情報を取得
        String sql = "SELECT * FROM attendances ORDER BY attendance_id DESC LIMIT 1;";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        // 取得した情報から出勤時間と退勤時間を読み込み
        rs.next();
        Time begin = rs.getTime("begin");
        Time end = rs.getTime("end");
        // LocalTime型に変換
        LocalTime localBegin = begin.toLocalTime();
        LocalTime localEnd = end.toLocalTime();

        // 勤務時間を算出
        Duration d = Duration.between(localBegin,localEnd);
        // Time型に変換        
        Time worktime = Time.valueOf(d.toHours() + ":" + d.toMinutes() + ":" + d.getSeconds());

        // 勤務時間を打刻(最も新しいレコードのものを書き換え)
        sql = "UPDATE attendances SET worktime=? WHERE id=? ORDER BY attendance_id DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setTime(1,worktime);
        ps.setInt(2,id);
        ps.executeUpdate();

        ps.close();
        rs.close();
        st.close();
        con.close();
    }
}