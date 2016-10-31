/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.dao;

import pacote.interfaces.DAOInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import pacote.bean.Usuario;
import pacote.utils.UsuarioUtil;


/**
 *
 * @author RHUAN
 */
public class UsuarioDAO implements DAOInterface<Usuario>{

    private EntityManager manager;

    public UsuarioDAO(EntityManager manager) {
        this.manager = manager;
    }
     
    
    @Override
    public Usuario create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void adicionar(Usuario obj) {        
        getManager().getTransaction().begin();
        getManager().persist(obj);
        getManager().getTransaction().commit();
        //getManager().close();
    }

    @Override
    public void excluir(Usuario usuario) {
        UsuarioUtil.removeContatos(usuario);
        UsuarioUtil.removeAgenda(usuario.getAgenda(), getManager());
        UsuarioUtil.removeUsuario(usuario, getManager());        
    }

    @Override
    public void atualizar(Usuario obj) {    
        getManager().getTransaction().begin();
        getManager().merge(obj);
        getManager().getTransaction().commit();
        //getManager().close();
    }
    
    @Override
    public List<Usuario> listar() {        
        Query q = getManager().createQuery("SELECT c FROM Usuario c");                
        return q.getResultList();
    }

    @Override
    public Usuario findForId(int id) {       
        Query q = getManager().createQuery("SELECT c FROM Usuario c WHERE c.id =:id");
        q.setParameter("id", id);       
        try{
            Usuario u = (Usuario)q.getSingleResult();
            return u;
        }catch(NoResultException e){
            return null;
        }
        
    }

   
    public Usuario findForEmail(String email) {        
        Query q = getManager().createQuery("SELECT c FROM Usuario c WHERE c.email =:email");
        q.setParameter("email", email); 
        try{
            Usuario u = (Usuario) q.getSingleResult();
            return u;
        }catch(NoResultException e){
            return null;
        }        
    }
  
    
    
    
    public int getIDAgendaPorUsuarioEmail(String email){      
        Query q = getManager().createQuery("SELECT u.agenda.id FROM Usuario u WHERE u.email =:email");
        q.setParameter("email", email);                
        try{
              int i = (int)q.getSingleResult();
              return i;
        }catch(NoResultException e){
            return 0;
        }
    }
    
        
    @Deprecated
    @Override
    public Usuario findForString(String string) {               
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntityManager getManager() {
        return manager;
    }
}
