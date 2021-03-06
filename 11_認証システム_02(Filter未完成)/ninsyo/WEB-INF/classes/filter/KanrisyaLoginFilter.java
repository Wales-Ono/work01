package filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class KanrisyaLoginFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {};

    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException {
        
        // HttpSessionインスタンス を取得
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        
        // session の有無を確認
        if (session == null || session.getAttribute("kengenNum") == null ||
            (int)session.getAttribute("kengenNum") == 0 || (int)session.getAttribute("kengenNum") == 1) {
            ((HttpServletResponse)response).sendRedirect("/ninsyo/login.jsp");
        }
        chain.doFilter(request,response);
    }

    public void destroy() {}
}