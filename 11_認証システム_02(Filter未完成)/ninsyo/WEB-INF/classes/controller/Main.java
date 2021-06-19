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
        // セッションスコープの中身を取得        
        HttpSession session = request.getSession(false);
        
        // session の有無を確認
        if(session == null){
            response.sendRedirect("/ninsyo/login.jsp");
        } else {
            User user = (User) session.getAttribute("user");
            int kengenNum = (int) session.getAttribute("kengenNum");

            //権限により条件分岐させ画面遷移
            if (kengenNum == 0){ //権限なしのときはログイン画面へリダイレクト
                response.sendRedirect("/ninsyo/login.jsp");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
                dispatcher.forward(request,response);
            }
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

        // HttpSessionインスタンス の取得
        HttpSession session = request.getSession();

        // セッションスコープにユーザー情報 をセット
        session.setAttribute("user",user);

        // 権限を判定する(0→権限なし、1→管理者、2→一般)
        int kengenNum = LoginLogic.kengenJudge(user);

        // セッションスコープに権限によって割り振られた数字 をセット
        session.setAttribute("kengenNum",kengenNum);

        // 権限により条件分岐させ画面遷移(0→ログイン画面へリダイレクト、それ以外はメインメニューへ)
        if (kengenNum == 0){
            response.sendRedirect("/ninsyo/login.jsp");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
            dispatcher.forward(request,response);            
        }
    }
}