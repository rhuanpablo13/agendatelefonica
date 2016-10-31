/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.filtro;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import pacote.conexao.Factory;

/**
 *
 * @author RHUAN
 */
@WebFilter(filterName = "filtroConexao", urlPatterns = "*.xhtml")
public class FiltroConexao implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        try{
                //abre uma conexão
                EntityManager manager = Factory.createEntityManager();
                //seta a conexão no request
                request.setAttribute("conexaoManager", manager);                
                chain.doFilter(request, response);
                manager.close();
            }catch(IOException e){
            System.out.println("Erro no FiltroConexao");
            e.printStackTrace();
        }
    }
    
    
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }

    @Override
    public void destroy() {
    
    }

    
}
