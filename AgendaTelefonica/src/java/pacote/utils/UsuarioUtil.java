/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.utils;

import java.util.List;
import javax.persistence.EntityManager;
import pacote.bean.Agenda;
import pacote.bean.Contato;
import pacote.bean.Usuario;
import pacote.conexao.Factory;
import pacote.constantes.Constantes;
import pacote.dao.ContatoDAO;
import pacote.dao.UsuarioDAO;
import pacote.session.SessionUtils;

/**
 *
 * @author RHUAN
 */
public class UsuarioUtil {

    
  
    
    public static boolean naoRemoveAdmin(Usuario usuario){
        if(usuario.getEmail().equals(Constantes.emailAdmin)){
            SessionUtils.setMenssagemInContext("Email de administrador não pode ser excluido!");
            return true;
        }
        return false;
    }
    
    
    public static void removeContatos(Usuario usuario){
        ContatoDAO dao = new ContatoDAO(Factory.getConnection());
        List<Contato>contatos = dao.listar(usuario.getAgenda().getId());
        for (Contato contato : contatos) {
            dao.excluir(contato);
        }
    }
    
    public static void removeAgenda(Agenda agenda, EntityManager manager){
        manager.getTransaction().begin();
        Agenda aa = manager.find(agenda.getClass(), agenda.getId());
        manager.remove(agenda);
        manager.getTransaction().commit();
    }
    
    public static void removeUsuario(Usuario usuario, EntityManager manager){
        manager.getTransaction().begin();        
        manager.remove(usuario);
        manager.getTransaction().commit();
    }
    
    /**
     * 
     * @param msg
     * @param retorno
     * @return 
     */
    public static boolean cadastroInvalido(String msg, boolean retorno){
        SessionUtils.setMenssagemInContext(msg);
        return retorno;
    }
    
    
    /**
     * Recupera o ID  da agenda do usuário logado no momento
     * @return ID de uma agenda
     */
    public static int getIDAgenda(){
        //pega o email do usuario logado para setar corretamente a chave extrangeira
        String usuario = SessionUtils.getUsuarioEmailInSession(); //id é o email        
        UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());             
        //busca no banco o id da agenda do contato
        int idAgenda = dao.getIDAgendaPorUsuarioEmail(usuario);
        return idAgenda;
    }
    
    
    
    /**
     * Recupera o nome da agenda da sessão, para apresentar nas paginas 
     * @return 
     */
    public static String cabecalhoApresentacao(){
        return SessionUtils.getUsuarioEmailInSession();
    }
}
