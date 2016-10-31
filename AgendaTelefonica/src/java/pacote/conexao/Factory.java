/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pacote.session.SessionUtils;

/**
 *
 * @author RHUAN
 */
public class Factory {
    
    /**
     * Cria uma instancia mananger de persistencia
     * @return entityManager
     */
    public static EntityManager createEntityManager(){
       EntityManagerFactory factory = Persistence.createEntityManagerFactory("AGENDA_PU");
       EntityManager manager = factory.createEntityManager();       
       return manager;      
    }
    
    public static EntityManager getConnection(){
        EntityManager manager = (EntityManager) SessionUtils.getRequest().getAttribute("conexaoManager");
        return manager;
    }  
}
