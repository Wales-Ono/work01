package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * ログアウトに関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * ログアウトのリンクが押されたときの処理
     */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //セッションがnullのときはログイン画面にフォワード
        HttpSession session = request.getSession(false);
        if (session == null) {
            // response.sendRedirect("http://localhost:8080/ninsyo/login.jsp");
            // RequestDispatcher dispatcher = request.getRequestDispatcher("http://localhost:8080/ninsyo/login.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);
        }
        
        // セッションスコープを破棄する
        session.invalidate();

        // ログイン画面へリダイレクト
        response.sendRedirect("/ninsyo/login.jsp");
    }
}