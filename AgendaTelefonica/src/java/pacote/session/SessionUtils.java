/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.session;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RHUAN
 */
public class SessionUtils {
    
        public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

        
        /**
         * Seta o ID(email) do usuario, no contexto de sessão
         * @param email 
         */
        public static void setEmailUsuarioInSession(String email) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.setAttribute("email_session",email);
	}
        
        /**
         * Seta o nome da agenda no contexto de sessão
         * @param agendaNome 
         */
	public static void setIdAgendaInSession(int idAgenda) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.setAttribute("idAgenda_session", idAgenda);
	}
        

        
        /**
         * Recupera o ID(email) do usuario na sessão
         * @return 
         */
	public static String getUsuarioEmailInSession() {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("email_session");
		else
			return null;
	}
        
        
        /**
         * Recupera o nome da agenda do usuario na sessão
         * @return 
         */
        public static int getIdAgendaInSession() {
		HttpSession session = getSession();
		if (session != null)
			return (int) session.getAttribute("idAgenda_session");
		else
			return 0;
	}
    
        
        
        /**
        * Seta uma mensagem qualquer no contexto do JSF
        * @param msg
        */
        public static void setMenssagemInContext(String msg){
            /* Cria uma mensagem. */
            FacesMessage str = new FacesMessage(msg);
            /* Obtém a instancia atual do FacesContext e adiciona a mensagem de erro nele. */
            FacesContext.getCurrentInstance().addMessage("erro", str);
        }
}
