/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.validacoes;

import pacote.bean.Usuario;
import pacote.conexao.Factory;
import pacote.constantes.Constantes;
import pacote.dao.UsuarioDAO;

/**
 *
 * @author RHUAN
 */
public class Validate {
    
    
    /**
     * Verifica se senhas informadas são iguais
     * @return true se sim
     */
    public static boolean validaSenhaCadastro(String senha, String senhaConfere){
       
        if(senha.equals(senhaConfere)){ 
            return true;
        }
        return false;
    }  
    
   
    
    /**
     * Verifica se existe um login já cadastrado
     * @return true se sim
     */
    public static boolean existeUsuarioCadastrado(String email, String senha){        
        UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());
        Usuario u = dao.findForEmail(email);
        if(u != null){
            if(u.getEmail().equals(email) && u.getSenha().equals(senha)){
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Verifica se existe um email já cadastrado
     * @return true se sim
     */
    public static boolean existeEmailCadastrado(String email){        
        UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());        
        if(dao.findForEmail(email)!= null){
            return true;            
        }
        return false;
    }
    
    
 
    public static boolean isAdmin(String emailAdmin){
        if(emailAdmin.equals(Constantes.emailAdmin)){
            return true;
        }
        return false;
    }
    
}
