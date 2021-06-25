package filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {};

    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException {
        // デバック確認用コード
        // response.setContentType("text/html; charset=UTF-8;");
        // PrintWriter out = response.getWriter();
        // out.println("<html><body>");
        // out.println(session.getId());
        // out.println("</body></html>");

        //セッションがnullのときはログイン画面にフォワード
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session == null) {
            // response.sendRedirect("http://localhost:8080/ninsyo/login.jsp");
            // RequestDispatcher dispatcher = request.getRequestDispatcher("http://localhost:8080/ninsyo/login.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);
        }
        chain.doFilter(request,response);
    }

    public void destroy() {}
}