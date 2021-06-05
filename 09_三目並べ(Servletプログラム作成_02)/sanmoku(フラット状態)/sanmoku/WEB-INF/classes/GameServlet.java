import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class GameServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{       
        //Board インスタンスの作成
        Board board = new Board();
        board.setCells0(" ");
        board.setCells1("○");
        board.setCells2(" ");

        //board情報をセッションスコープに保存
        HttpSession session = request.getSession();
        session.setAttribute("board",board);
    	
    	// ゲーム画面へフォワード
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/game.jsp");
        dispatcher.forward(request,response);
	}

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        //盤面のマスを受け取る
        String cell = request.getParameter("cell");

        //再度ゲーム画面へフォワード        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/game.jsp");
        dispatcher.forward(request,response);
    }

}

