package mvc.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {


    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        if ((session == null || session.getAttribute("idUser") == null) && !((HttpServletRequest) req).getRequestURI().equals("/checklog")) {

            request.getRequestDispatcher( "/vue/log.jsp" ).forward( request, response );

        } else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }

    }

    public void destroy() {
    }
}
