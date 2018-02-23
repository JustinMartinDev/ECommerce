/**
 * Title:        commerce
 * Description:  Class for e-commerce
 * Company:      IUT Laval - Université du Maine
 * @author  A. Corbière
 * @version 1.0, 21/01/13
 */

package commerce.web.utilitaire;

import java.io.IOException ;

import javax.servlet.Filter ;
import javax.servlet.FilterChain ;
import javax.servlet.FilterConfig ;
import javax.servlet.ServletRequest ;
import javax.servlet.ServletResponse ;
import javax.servlet.ServletException ;

public class CharsetFilter implements Filter {  
    public void init(FilterConfig config) throws ServletException {}  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {  
        if(request.getCharacterEncoding()==null)  
            request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html; charset=UTF-8"); // ContentType par défaut.
        response.setCharacterEncoding("UTF-8");  
        next.doFilter(request, response);  
    }  
    public void destroy(){}  
}  