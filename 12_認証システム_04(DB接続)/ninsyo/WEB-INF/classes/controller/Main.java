package controller;

import java.util.ResourceBundle;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.User;
import model.LoginLogic;

/**
 * ログインに関するリクエストを処理するコントローラ
 * @author N.Ono
 */
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * 権限メニューのリンクが押されたときの処理
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションがnullのときはログイン画面にフォワード
        HttpSession session = request.getSession(false);
        if (session == null) {
            // response.sendRedirect("http://localhost:8080/ninsyo/login.jsp");
            // RequestDispatcher dispatcher = request.getRequestDispatcher("http://localhost:8080/ninsyo/login.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);
        }

        // セッションスコープからユーザー情報を取り出す
        User user = (User) session.getAttribute("user");

        // DBに登録されている場合と登録されていない場合で条件分岐
        if (user.getAuth() == 1 || user.getAuth() == 2){ 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
            dispatcher.forward(request,response);            
        } else {
            response.sendRedirect("/ninsyo/login.jsp");
        }
    }

    /**
     * ログインボタンが押されたときの処理
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // ログイン画面で入力されたID,PWを取得
        String inputId = request.getParameter("inputId");
        String inputPw = request.getParameter("inputPw");

        // Userクラスのインスタンスを作成
        User user = new User(inputId,inputPw);

        // 入力された値とDBの情報が一致するかを検索
        User findUser = LoginLogic.findUser(user);

        // 一致するか不一致かにより条件分岐
        if (findUser == null) {
            response.sendRedirect("/ninsyo/login.jsp");
        } else { 
            // セッションスコープにユーザー情報をセット
            HttpSession session = request.getSession();
            session.setAttribute("user",findUser);

            // 権限により条件分岐させ画面遷移(0→ログイン画面へリダイレクト、それ以外はメインメニューへ)
            if (findUser.getAuth() == 1 || findUser.getAuth() == 2){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
                dispatcher.forward(request,response);                
            } else {
                response.sendRedirect("/ninsyo/login.jsp");      
            }
        }
    }
}