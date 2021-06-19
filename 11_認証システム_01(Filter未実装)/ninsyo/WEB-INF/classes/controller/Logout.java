package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 社員権限に関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * ログアウトのリンクが押されたときの処理
     */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        // セッションスコープを破棄する
        HttpSession session = request.getSession();
        session.invalidate();

        // ログイン画面へリダイレクト
        response.sendRedirect("/ninsyo/login.jsp");
    }
}