package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
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
    }

    /**
     * ユーザー編集処理
     */
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // sessionスコープの中身を取得
        HttpSession session = request.getSession();
        User selectUser = (User) session.getAttribute("selectUser");

        // User型のインスタンスを作成し入力内容をセット
        User user = new User();
        String editName = request.getParameter("editName");
        String editUserId = request.getParameter("editUserId");
        String editUserPw = request.getParameter("editUserPw");
        String editAuthId = request.getParameter("editAuthId");
        user.setId(selectUser.getId());
        user.setName(editName);
        user.setUserId(editUserId);
        user.setUserPw(editUserPw);
        user.setAuthId(Integer.parseInt(editAuthId));

        // DB側の情報を書き換え
        UserDAO.userEdit(user);

        response.sendRedirect("/ninsyo/main");
    }

}