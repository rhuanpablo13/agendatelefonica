/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.managedbean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pacote.bean.Contato;
import pacote.conexao.Factory;
import pacote.dao.ContatoDAO;
import pacote.session.SessionUtils;
import pacote.utils.ContatoUtil;


/**
 *
 * @author RHUAN
 */
@SessionScoped
@ManagedBean
public class ContatoMB implements Serializable{

    private static final long serialVersionUID = 7372760436097095751L;

    private Contato contato;
    private Contato contatoBackup; 
    private List<Contato> contatos;
    
   
    
    public ContatoMB() {        
        setContato(new Contato());
    }
    
      
   
    /**
     * Camada mb
     * Adicionar um contato ao banco de dados
     */    
    public void adicionar(){       
        ContatoDAO dao = new ContatoDAO(Factory.getConnection());         
        getContato().setFk_agenda(SessionUtils.getIdAgendaInSession());
        dao.adicionar(getContato());
        setContato(new Contato());
    }
    
    /**
     * Exclui um contato 
     * @param contato
     */
    public void excluir(Contato contato){        
        if(contato != null){
            ContatoDAO dao = new ContatoDAO(Factory.getConnection());            
            dao.excluir(contato);
        }
    }
    
    /**
     * Editar um contato
     * Na lista de contatos, o contato para editar é salvo em um contato auxiliar, pois
     * quando a pagina de lista muda para a tela de edição, os dados da variavel (contato)
     * são perdidos, então eles são salvos no contatoBackup, pelo método selecionarContato.
     */
    public void editar(){
        ContatoDAO dao = new ContatoDAO(Factory.getConnection()); 
        dao.atualizar(ContatoUtil.atualizaContato(getContatoBackup(), getContato(),
                      SessionUtils.getIdAgendaInSession()));        
    }
    
    
    /**
     * Salva o contato selecionado para edição em uma variavel auxiliar
     * @param contato
     */
    public void selecionarContato(Contato contato){        
        setContatoBackup(new Contato());
        setContatoBackup(contato);
        this.contato = new Contato();//zera os valores do contato no contexto
    }
    
    
    
    
    
    
    /******************************************************/
    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }


    public Contato getContatoBackup() {
        return contatoBackup;
    }

    public void setContatoBackup(Contato contatoBackup) {
        this.contatoBackup = contatoBackup;
    }

    public List<Contato> getContatos() {        
        ContatoDAO dao = new ContatoDAO(Factory.getConnection()); 
        setContatos(dao.listar(SessionUtils.getIdAgendaInSession()));
        return this.contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
    
}
