package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import model.User;
import dao.UserDAO;

/**
 * ユーザー編集に関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class UserEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * ユーザー編集画面に遷移させるための処理
     */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            // セッションがnullのときはログイン画面にフォワード
            HttpSession session = request.getSession(false);
            if (session == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request,response);
            }

            // クリックされたidを取得、そのidからユーザー情報すべてを取得
            String selectId = request.getParameter("id");
            User selectUser = UserDAO.getByUserId(Integer.parseInt(selectId));

            // sessionスコープにセット                
            session.setAttribute("selectUser",selectUser);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/userEdit.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException | NamingException e) {
            response.sendRedirect("/ninsyo/error.jsp");
        }
    }

    /**
     * ユーザー編集処理
     */
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");

            // sessionスコープの中身(selectUser)を取得
            HttpSession session = request.getSession();
            User selectUser = (User) session.getAttribute("selectUser");
            
            String editName = request.getParameter("editName");
            String editUserId = request.getParameter("editUserId");
            String editUserPw = request.getParameter("editUserPw");
            String editAuthId = request.getParameter("editAuthId");
            String editMailAddress = request.getParameter("editMailAddress");
            String editPhoneNumber = request.getParameter("editPhoneNumber");
            String editPostalCode = request.getParameter("zip11");
            String editAddress01 = request.getParameter("addr11");
            String editAddress02 = request.getParameter("editAddress02");
            
            User user = new User(selectUser.getId(),editName,editUserId,editUserPw,Integer.parseInt(editAuthId),editMailAddress,editPhoneNumber,editPostalCode,editAddress01,editAddress02);

            // DB側の情報を書き換え
            UserDAO.userEdit(user);     

            response.sendRedirect("/ninsyo/main");
        } catch (SQLException | NamingException e) {
            response.sendRedirect("/ninsyo/error.jsp");
        }   
    }

}