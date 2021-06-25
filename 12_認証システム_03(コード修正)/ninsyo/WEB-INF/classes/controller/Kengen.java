package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.User;

/**
 * 社員権限に関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class Kengen extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * メインメニューのリンクが押されたときの処理
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


        // クリックされたリンクを"action"で判定
        String action = request.getParameter("action");
        User user = (User) session.getAttribute("user");
        
        // 権限によって割り振られた数字によって条件分岐
        if (user.getKengenNum() == 0){ //0→ログイン画面へリダイレクト
            response.sendRedirect("/ninsyo/login.jsp");
        } else {
            //actionの文字列により条件分岐
            if (action.equals("ippan")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ippan.jsp");
                dispatcher.forward(request,response);
            } else if (action.equals("kanrisya")){
            //管理者以外のときはメインメニュー、管理者のときは管理者権限画面へ
                if(user.getKengenNum() != 1){  
                    response.sendRedirect("/ninsyo/main.jsp");
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/kanrisya.jsp");
                    dispatcher.forward(request,response);
                }
            } else {
                response.sendRedirect("/ninsyo/main.jsp");
            }
        }
    }
}