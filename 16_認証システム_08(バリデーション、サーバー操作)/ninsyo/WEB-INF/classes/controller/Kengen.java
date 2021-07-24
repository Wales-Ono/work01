package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.naming.*;
import java.util.*;
import model.User;
import dao.UserDAO;

/**
 * 社員権限に関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class Kengen extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String KANRISYA = "kanrisya";
    public static final String IPPAN = "ippan";

    /**
     * メインメニューのリンクが押されたときの処理
     */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try{ 
            //セッションがnullのときはログイン画面にフォワード
            HttpSession session = request.getSession(false);
            if (session == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request,response);
            }


            // クリックされたリンクを"action"で判定
            String action = request.getParameter("action");
            User user = (User) session.getAttribute("user");
            
            // actionの文字列により条件分岐
            if (action.equals(IPPAN)){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ippan.jsp");
                dispatcher.forward(request,response);
            } else if (action.equals(KANRISYA)){
                // 管理者以外のときはメインメニュー、管理者のときは管理者権限画面へ
                if(user.getAuthId() == 1){
                    // ユーザーリストを作成し、セッションにセット
                    List<User> userList = UserDAO.getUserList();
                    request.setAttribute("userList",userList);

                    // 管理者ページへ
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/kanrisya.jsp");
                    dispatcher.forward(request,response);
                } else {
                    response.sendRedirect("/ninsyo/main.jsp");
                }
            } else {
                response.sendRedirect("/ninsyo/main.jsp");
            }
        } catch (SQLException | NamingException e) {
            response.sendRedirect("/ninsyo/error.jsp");
        }
    }
}