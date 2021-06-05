package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import model.Board;
import model.GameLogic;

public class GameServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    public int count;
    public static final String MARU = "◯";
	public static final String BATSU = "✕";

    // 最初のゲーム画面遷移のみ
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        // 初期値として 0 をセット
        count = 0;

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
        // request.setCharacterEncodin("UTF-8");

        // 押されたボタンに対応する数字(cellNum)を入手
        String cellNum = request.getParameter("cellNum");
        int intCellNum = Integer.parseInt(cellNum);

        // セッションスコープから board 情報を取得
        HttpSession session = request.getSession();
        Board board = (Board) session.getAttribute("board");


        // 押されたボタンにマークをセット        
        board.setCells(intCellNum,board.getMark());
            

        // count が偶数ならマークを"×" 奇数ならマークを"○"にセット
        if(count % 2 == 0){ 
            board.setMark(BATSU);    
        } else {
            board.setMark(MARU);
        }


        // board をセッションスコープにセット
        session.setAttribute("board",board);


        // count に1を加える
        count++;

        
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