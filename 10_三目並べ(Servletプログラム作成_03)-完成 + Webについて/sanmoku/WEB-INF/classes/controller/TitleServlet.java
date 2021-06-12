package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/title.jsp");
        dispatcher.forward(request,response);
	}
}
