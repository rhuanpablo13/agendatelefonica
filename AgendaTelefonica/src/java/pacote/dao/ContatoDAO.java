/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.dao;

import pacote.interfaces.DAOInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pacote.bean.Contato;


/**
 *
 * @author RHUAN
 */
public class ContatoDAO implements DAOInterface<Contato>{

    private EntityManager manager;

    public ContatoDAO(EntityManager manager) {
        this.manager = manager;
    }       

    @Override
    public Contato create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    
    @Override
    public void adicionar(Contato obj) {        
        getManager().getTransaction().begin();
        getManager().persist(obj);
        getManager().getTransaction().commit();
        //getManager().close();
        
    }

    @Override
    public void excluir(Contato obj) {
       getManager().getTransaction().begin();
       Contato c = null;
       c = getManager().find(Contato.class, obj.getId());
       if(c != null){
            getManager().remove(c);
            getManager().getTransaction().commit();
            //getManager().close();
        }else{
            System.out.println("Erro ao excluir contato");
        }

    }
    
    @Override
    public void atualizar(Contato obj) {
        getManager().getTransaction().begin();
        getManager().merge(obj);
        getManager().getTransaction().commit();
        //getManager().close();
    }
    
    
    public List<Contato> listar(int idAgenda) {
        Query q = getManager().createQuery("SELECT c FROM Contato c WHERE c.fk_agenda =:idAgenda");
        q.setParameter("idAgenda", idAgenda);
        return q.getResultList();
    }

    @Override
    public Contato findForId(int id) {        
        Query q = getManager().createQuery("SELECT c FROM Contato c WHERE c.id =:id");
        q.setParameter("id", id);        
        return (Contato) q.getSingleResult();
    }
   
    
    
    
    
    
    public EntityManager getManager() {
        return manager;
    }

    @Deprecated
    @Override
    public List<Contato> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Deprecated
    @Override
    public Contato findForString(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
