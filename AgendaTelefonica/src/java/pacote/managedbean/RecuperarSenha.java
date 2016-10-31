/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.managedbean;

import java.util.UUID;
import javax.faces.bean.ManagedBean;
import pacote.bean.Usuario;
import pacote.conexao.Factory;
import pacote.dao.UsuarioDAO;
import pacote.enviaemail.EnviaGmail;
import pacote.enviaemail.EnviaHotmail;
import pacote.interfaces.EnviarEmail;
import pacote.session.SessionUtils;


/**
 *
 * @author RHUAN
 */
@ManagedBean
public class RecuperarSenha{
    
    private String email;
    private static String subtitulo = "RECUPERAÇÃO SENHA - AGENDA TELEFONICA";
    
    public RecuperarSenha() {
    }
    
    
    /**
     * Envia uma nova senha para o email do usuario
     * @return 
     */
    public String enviarEmail(){
        
        if(verificaEmailBanco() == false){
            SessionUtils.setMenssagemInContext("Endereço de email não cadastrado no sistema!");
            return null;
        }
        
        if(this.email.contains("@gmail")){
            EnviarEmail gmail = new EnviaGmail("gmail", "senha");
            String ns = getNovaSenha();
            atualizaSenhaUsuario(ns);                      
            gmail.enviarEmail(email, subtitulo, "sua nova senha é: " + ns);            
            SessionUtils.setMenssagemInContext("Sua nova senha foi enviada com sucesso! Para o email: "+email);
        
        }else if(this.email.contains("@hotmail")){
            EnviarEmail hotmail = new EnviaHotmail("hotmail", "senha");
            String ns = getNovaSenha();
            atualizaSenhaUsuario(ns);            
            hotmail.enviarEmail(email, subtitulo, "sua nova senha é: " + ns);
            SessionUtils.setMenssagemInContext("Sua nova senha foi enviada com sucesso! Para o email: "+email);
        
        }else{
            SessionUtils.setMenssagemInContext("Endereço de email não reconhecido. Por favor informe sua conta hotmail ou gmail");
            return null;
        }
        return "";
    }
    
    
    /**
     * Atualiza no banco de dados uma nova senha
     * @param novaSenha 
     */
    private void atualizaSenhaUsuario(String novaSenha){
        UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());
        Usuario usuario = dao.findForEmail(email);
        usuario.setSenha(novaSenha);
        dao.atualizar(usuario);
    }
    
    
    /**
     * Verifica se o email está cadastrado no banco de dados
     * @return 
     */
    private boolean verificaEmailBanco(){
        UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());
        if(dao.findForEmail(email) != null)
            return true;
        return false;
    }
    
    /**
     * Retorna uma nova senha
     * @return 
     */
    private String getNovaSenha(){
        return geraSenhaRandomica();
        
    }
    
    /**
     * Gera uma nova senha 
     * @return 
     */
    private String geraSenhaRandomica(){
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();        
        return myRandom.substring(0,10);        
    }
    
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
    



