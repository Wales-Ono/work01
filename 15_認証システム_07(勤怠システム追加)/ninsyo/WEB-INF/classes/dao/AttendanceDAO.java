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
        String sql = "SELECT date,begin,IFNULL(end,'00:00'),IFNULL(worktime,'00:00'),IFNULL(rest,'00:00') FROM attendances INNER JOIN users ON attendances.id = users.id WHERE attendances.id=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        // DBから読み込み
        while (rs.next()){
            // ユーザー情報をひとつずつ取得
            String date = rs.getString("date");
            String begin = String.format("%.5s",rs.getString("begin"));
            String end = String.format("%.5s",rs.getString("IFNULL(end,'00:00')"));
            String worktime = String.format("%.5s",rs.getString("IFNULL(worktime,'00:00')"));
            String rest = String.format("%.5s",rs.getString("IFNULL(rest,'00:00')"));

            // ユーザー情報をセット
            Attendance attendance = new Attendance(id,date,begin,end,worktime,rest);

            // リストに格納していく
            attendanceList.add(attendance);
        }
        rs.close();
        ps.close();
        con.close();

        return attendanceList;
    }

    /**
     * 今日の勤怠情報を取得する
     * @param 
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係    
     */
    public static Attendance getAttedanceToday(int id) throws SQLException,NamingException{
        
        Attendance attendanceToday;
        
        // DB接続
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        Connection con = ds.getConnection();

        // IDと今日の日付 で絞り込み
        String sql = "SELECT * FROM attendances WHERE id=? AND date=CURDATE();";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);

        // レコードを取得
        ResultSet rs = ps.executeQuery();

        // 取得したレコードからカラムをひとつずつ取り出してセット(レコードがない場合ばnullを返す)
        if (rs.next()) {
            String date = rs.getString("date");
            String begin = rs.getString("begin");
            String end = rs.getString("end");
            String worktime = rs.getString("worktime");
            String rest = rs.getString("rest");

            attendanceToday = new Attendance(id,date,begin,end,worktime,rest);
            
        } else {
            attendanceToday = null;
        }
        ps.close();
        con.close();
        rs.close();

        // 今日の勤怠情報を返す
        return attendanceToday;
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

        // 現在時刻を取得し、秒以下を切り捨てる
        LocalTime getNowTime = LocalTime.now();
        LocalTime localNowTime = LocalTime.of(getNowTime.getHour(),getNowTime.getMinute(),0);
        java.sql.Time nowTime = java.sql.Time.valueOf(localNowTime);

        // 日付"date" と 出勤時刻"begin" を打刻
        String sql = "INSERT INTO attendances (id,date,begin) values (?,CURDATE(),?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ps.setTime(2,nowTime);
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

        // 現在時刻を取得し、秒以下を切り捨てる
        LocalTime getNowTime = LocalTime.now();
        LocalTime localNowTime = LocalTime.of(getNowTime.getHour(),getNowTime.getMinute(),0);
        java.sql.Time nowTime = java.sql.Time.valueOf(localNowTime);

        // 退勤時刻"end"を打刻
        String sql = "UPDATE attendances SET end=? WHERE id=? AND date=CURDATE();";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setTime(1,nowTime);
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

        // 今日の勤怠情報を取得
        Attendance attendanceToday = getAttedanceToday(id);

        // 取得した情報から出勤時間と退勤時間を読み込み
        String begin = attendanceToday.getBegin();
        String end = attendanceToday.getEnd();
        // LocalTime型に変換
        LocalTime localBegin = LocalTime.parse(begin);
        LocalTime localEnd = LocalTime.parse(end);
        
        // 勤務時間を算出
        Duration d = Duration.between(localBegin,localEnd);
        // Time型に変換
        Time worktime = Time.valueOf(d.toHours() + ":" + d.toMinutesPart() + ":" + d.toSecondsPart());

        // 勤務時間を打刻
        String sql = "UPDATE attendances SET worktime=? WHERE id=? AND date=CURDATE();";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setTime(1,worktime);
        ps.setInt(2,id);
        ps.executeUpdate();

        ps.close();
        con.close();
    }

    /**
     * 休憩時間の打刻
     * @param id ログインしているユーザーのID      
     * @throws java.sql.SQLException DB関係
     * @throws javax.naming.NamingException ネーミング関係
     */
    public static void punchRest(int id) throws SQLException,NamingException{
        // DB接続
        InitialContext initialContext = new InitialContext();
        DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/NinsyoDB");
        Connection con = ds.getConnection();

        // 今日の勤怠情報を取得
        Attendance attendanceToday = getAttedanceToday(id);

        // 取得した情報から勤務時間を読み込む
        String worktime = attendanceToday.getWorktime();

        // LocalTime型に変換
        LocalTime localWorktime = LocalTime.parse(worktime); // 勤務時間を取得
        LocalTime localBaseTime = LocalTime.of(8,0,0); // 基準として8時間を設定
        LocalTime localRest = LocalTime.of(1,0,0); // 休憩時間は1時間で設定
 
        // 勤務時間が8時間以上を上回るときは休憩時間を1時間セット
        if (localWorktime.equals(localBaseTime) || localWorktime.isAfter(localBaseTime)) {
            // 休憩時間を打刻
            String sql = "UPDATE attendances SET rest=? WHERE id=? AND date=CURDATE();";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTime(1,Time.valueOf(localRest));
            ps.setInt(2,id);
            ps.executeUpdate();
            ps.close();
        } else {
            //何もしない
        }
        con.close();
    }
}