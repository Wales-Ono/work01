package controller;
 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Board;
 
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
    	
    	HttpSession session = request.getSession();
    	Board board = (Board)session.getAttribute("board");
    	
    	
    	//最初のみインスタンスの生成
    	if (board == null) {
    		board = new Board();
    		
    	//それ以降
    	}else {
    		//btn には押されたボタンのインデックスが入っている
    		String btn = request.getParameter("btn");
    		System.out.println("btn の中身:" + btn);
    		
    		//index によって場合分け
    		switch(btn) {
    			case "0":
    				board.setCells(0,"◯");
    				break;
    			case "1":
    				board.setCells(1,"◯");
    				break;
    			case "2":
    				board.setCells(2,"◯");
    				break;
    			case "3":
    				board.setCells(3,"◯");
    				break;
    			case "4":
    				board.setCells(4,"◯");
    				break;
    			case "5":
    				board.setCells(5,"◯");
    				break;
    			case "6":
    				board.setCells(6,"◯");
    				break;
    			case "7":
    				board.setCells(7,"◯");
    				break;
    			case "8":
    				board.setCells(8,"◯");
    				break;
    			default:
    				break;
    		}
    	}
    	
    	
    	
    	// セッションスコープをセットする
    	session.setAttribute("board", board);
    	
    	// ゲーム画面へとばす
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/game.jsp");
        dispatcher.forward(request,response);
    }
    
}
