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
        request.setCharacterEncoding("UTF-8");

        String addName = request.getParameter("addName");
        String addUserId = request.getParameter("addUserId");
        String addUserPw = request.getParameter("addUserPw");
        String addAuthId = request.getParameter("addAuthId");

        User user = new User(addName,addUserId,addUserPw,Integer.parseInt(addAuthId));
        try{
            UserDAO.userAdd(user);
        } catch (SQLException | NamingException e) {
            response.sendRedirect("/ninsyo/error.jsp");
        }
        response.sendRedirect("/ninsyo/main");
    }

}