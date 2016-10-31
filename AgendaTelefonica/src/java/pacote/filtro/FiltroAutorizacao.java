/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RHUAN
 */

@WebFilter(filterName = "filtroAutorizacao", urlPatterns = {"*.xhtml"} )
public class FiltroAutorizacao implements Filter{

    public FiltroAutorizacao() {
    }

    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       
        try{
            HttpServletRequest req = (HttpServletRequest) request; 
            HttpServletResponse resp = (HttpServletResponse) response; 
            HttpSession sess = req.getSession(false);
            
            String reqURI = req.getRequestURI();
            if(reqURI.indexOf("/login.xhtml") >= 0 ||
                    //paths de acesso liberado
                    (sess != null && sess.getAttribute("email_session") != null) ||
                    reqURI.indexOf("/public/") >= 0 ||
                    reqURI.contains("javax.faces.resource") ||
                    reqURI.contains("/cadastroUsuario.xhtml") ||
                    reqURI.contains("/recuperarSenha.xhtml")
                    ){
                //libera a requisição 
                chain.doFilter(request, response);
            }else{
                //login errado mantém na pagina login
                resp.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
            }                 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
       
    }

    @Override
    public void destroy() {
        
    }
    
}
