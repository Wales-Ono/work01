package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import model.Board;
import model.GameLogic;

public class GameServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public static final String MARU = "◯";
	public static final String BATSU = "✕";

    // 最初のゲーム画面遷移のみ
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
    
        // Boardインスタンスの作成
        Board board = new Board();

        // セッションスコープの取得
        HttpSession session = request.getSession();
        session.setAttribute("board",board);    	
        
    	// ゲーム画面へとばす
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/game.jsp");
        dispatcher.forward(request,response);
	}

    // ボタンがクリックされたらdoPostへ
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        request.setCharacterEncoding("UTF-8");

        // 押されたボタンに対応する数字(cellNum)を入手
        String cellNum = request.getParameter("cellNum");
        int intCellNum = Integer.parseInt(cellNum);

        // セッションスコープから board 情報を取得
        HttpSession session = request.getSession();
        Board board = (Board) session.getAttribute("board");

        // countに1を加える
        board.countUp();


        // 押されたボタンにマークをセット        
        board.setCells(intCellNum,board.getMark());

        // judge 引き分け、勝利であればtrue
        GameLogic gameLogic = new GameLogic();
        if (gameLogic.judge(board) == 1) { //1 が返されると勝利
            RequestDispatcher dispatcher = request.getRequestDispatcher("/winGame.jsp");
            dispatcher.forward(request,response);
        } else if (gameLogic.judge(board) == 2) { //2 が返させると引き分け
            RequestDispatcher dispatcher = request.getRequestDispatcher("/drawGame.jsp");
            dispatcher.forward(request,response);
        }


        // count が偶数ならマークを"×" 奇数ならマークを"○"
        if(board.getCount() % 2 != 0){ 
            board.setMark(BATSU);    
        } else {
            board.setMark(MARU);
        }

        // board をセッションスコープにセット
        session.setAttribute("board",board);

        
        // ゲーム画面へフォワード       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/game.jsp");
        dispatcher.forward(request,response);

        // デバックチェックのためのコード
        // response.setContentType("text/html; charset=UTF-8;");
        // PrintWriter out = response.getWriter();
        // out.println("<html><body>");
        // out.println(cellNum);
        // out.println(board.getCells0());
        // out.println("</body></html>"); 
    }

}