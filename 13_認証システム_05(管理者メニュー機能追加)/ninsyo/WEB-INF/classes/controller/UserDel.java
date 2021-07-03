package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import model.User;
import dao.UserDAO;

/**
 * ユーザー削除に関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class UserDel extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * ユーザー削除画面に遷移させるための処理
     */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //セッションがnullのときはログイン画面にフォワード
        HttpSession session = request.getSession(false);
        if (session == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);
        }

        String selectId = request.getParameter("id");
        User selectUser = UserDAO.getByUserId(Integer.parseInt(selectId));
                
        session.setAttribute("selectUser",selectUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/userDel.jsp");
        dispatcher.forward(request,response);
    }

    /**
     * ユーザー削除処理
     */
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User selectUser = (User) session.getAttribute("selectUser");

        User user = new User();

        UserDAO.userDel(selectUser.getId());

        response.sendRedirect("/ninsyo/main");
    }

}