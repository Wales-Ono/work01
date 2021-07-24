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
 * ユーザー追加に関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class UserAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * ユーザー追加画面に遷移させるための処理
     */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //セッションがnullのときはログイン画面にフォワード
        HttpSession session = request.getSession(false);
        if (session == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/userAdd.jsp");
        dispatcher.forward(request,response);
    }

    /**
     * ユーザー追加処理
     */
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setCharacterEncoding("UTF-8");

            String addName = request.getParameter("addName");
            String addUserId = request.getParameter("addUserId");
            String addUserPw = request.getParameter("addUserPw");
            String addAuthId = request.getParameter("addAuthId");
            String addMailAddress = request.getParameter("addMailAddress");
            String addPhoneNumber = request.getParameter("addPhoneNumber");
            String addPostalCode = request.getParameter("zip11");
            String addAddress01 = request.getParameter("addr11");
            String addAddress02 = request.getParameter("addAddress02");

            User user = new User(addName,addUserId,addUserPw,Integer.parseInt(addAuthId),addMailAddress,addPhoneNumber,addPostalCode,addAddress01,addAddress02);
            UserDAO.userAdd(user);
            response.sendRedirect("/ninsyo/main");
        } catch (SQLException | NamingException e) {
            response.sendRedirect("/ninsyo/error.jsp");
        }
    }
}