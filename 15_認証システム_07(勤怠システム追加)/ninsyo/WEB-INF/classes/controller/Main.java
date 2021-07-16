package controller;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.User;
import model.Attendance;
import java.sql.*;
import javax.naming.*;
import dao.UserDAO;
import dao.AttendanceDAO;

/**
 * ログインに関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * メインメニューのリクエストに関する処理
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // セッションがnullのときはログイン画面にフォワード
            HttpSession session = request.getSession(false);
            if (session == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request,response);
            }

            // セッションスコープからユーザー情報を取り出す
            User user = (User) session.getAttribute("user");

            // 出勤 または 退勤ボタンが押された場合
            if (request.getParameter("btn") != null){
                // 押されたボタンの情報を取得(出勤ボタン:1 , 退勤ボタン:2) 
                int btn = Integer.parseInt(request.getParameter("btn"));

                // 出勤ボタン
                if (btn == 1) {
                    // 出勤時刻を打刻
                    AttendanceDAO.punchBegin(user.getId());

                // 退勤ボタン
                } else if (btn == 2){
                    // 退勤時刻を打刻
                    AttendanceDAO.punchEnd(user.getId());
                    // 勤務時間を打刻
                    AttendanceDAO.punchWorktime(user.getId());
                    // 休憩時間を打刻
                    AttendanceDAO.punchRest(user.getId());
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                    dispatcher.forward(request,response);
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request,response);
            }

            // 勤務一覧を取得し、セッションスコープにセット
            List<Attendance> attendanceList = AttendanceDAO.getAttendanceList(user.getId());
            session.setAttribute("attendanceList",attendanceList);
            
            // 今日の日付の勤怠を取得し、セッションスコープにセット
            Attendance attendanceToday = AttendanceDAO.getAttedanceToday(user.getId());
            session.setAttribute("attendanceToday",attendanceToday);

            // メインメニューへ
            RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
            dispatcher.forward(request,response);

        } catch (SQLException | NamingException e) {
            response.sendRedirect("/ninsyo/error.jsp");
        }
    }

    /**
     * ログインボタンが押されたときの処理
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            // ログイン画面で入力されたID,PWを取得
            String inputId = request.getParameter("inputId");
            String inputPw = request.getParameter("inputPw");

            // Userクラスのインスタンスを作成
            User user = new User(inputId,inputPw);

            // 入力された値とDBの情報が一致するかを検索
            User findUser = UserDAO.findUser(user);

            // 一致するか不一致かにより条件分岐
            if (findUser == null) {
                response.sendRedirect("/ninsyo/login.jsp");
            } else { 
                // セッションスコープにユーザー情報をセット
                HttpSession session = request.getSession();
                session.setAttribute("user",findUser);

                // ユーザーがDBに登録されている場合
                if (findUser.getAuthId() == 1 || findUser.getAuthId() == 2){
                    // 勤務一覧を取得し、セッションスコープにセット
                    List<Attendance> attendanceList = AttendanceDAO.getAttendanceList(findUser.getId());
                    session.setAttribute("attendanceList",attendanceList);
                    
                    // 今日の日付の勤怠を取得し、セッションスコープにセット
                    Attendance attendanceToday = AttendanceDAO.getAttedanceToday(findUser.getId());
                    session.setAttribute("attendanceToday",attendanceToday);

                    // デバック確認用コード
                    // response.setContentType("text/html; charset=UTF-8;");
                    // PrintWriter out = response.getWriter();
                    // out.println("<html><body>");
                    // out.println(findUser.getId());
                    // out.println("aaa");
                    // out.println(attendanceToday);
                    // out.println("</body></html>");

                    // メインメニューへ
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
                    dispatcher.forward(request,response);                
                } else {
                    response.sendRedirect("/ninsyo/login.jsp");
                }
            }
        } catch (SQLException | NamingException e) {
            response.sendRedirect("/ninsyo/error.jsp");
        }
    }
}

