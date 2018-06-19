package mvc.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * Filtre sur les requetes pour les encoder en UTF-8
 *
 **/
public class CharsetFilter implements Filter
{
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Raccord de méthode auto-généré

    }


    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        // TODO Raccord de méthode auto-généré
    }


}