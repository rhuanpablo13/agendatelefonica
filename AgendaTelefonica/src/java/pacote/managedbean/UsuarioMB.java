/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import pacote.bean.Agenda;
import pacote.session.SessionUtils;
import pacote.bean.Usuario;
import pacote.conexao.Factory;
import pacote.dao.UsuarioDAO;
import pacote.utils.UsuarioUtil;
import pacote.validacoes.Validate;

/**
 *
 * @author RHUAN
 */

@SessionScoped
@ManagedBean
public class UsuarioMB implements Serializable{
    
    private Usuario usuario;
    private List<Usuario> usuarios;
    private String repeteSenha;
    private Agenda agenda;
    private boolean apresentaMsg;
    private boolean configAdmin;

    
   
    public UsuarioMB() {
        novoUsuario();
    }
    
    
    /**
     * Seta um novo usuario
     */   
    public void novoUsuario(){
        this.usuario = new Usuario();
        this.agenda = new Agenda();
        this.repeteSenha = null;     
        this.usuarios = new ArrayList<Usuario>();
        this.apresentaMsg = false;
        this.configAdmin = true;
    }
    
    /**
     * Cadastra um novo usuario
     * @return 
     */
    public String cadastrar(){
        if((Validate.existeEmailCadastrado(getUsuario().getEmail()) == false)){ //não existe login pré cadastrado
            if(Validate.validaSenhaCadastro(getUsuario().getSenha(), getRepeteSenha()) == true){ //Senha informada OK                                
                getUsuario().getAgenda().setNomeAgenda(getAgenda().getNomeAgenda());
                UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());
                dao.adicionar(this.usuario);                
                novoUsuario();
                return "login.xhtml";
            }else{
                setApresentaMsg(UsuarioUtil.cadastroInvalido("Senha inválida", true));                               
                novoUsuario();
                return null;
            }
        }else{
            setApresentaMsg(UsuarioUtil.cadastroInvalido("Email já cadastrado!", true));            
            novoUsuario();
            return null;
        }      
        
    }
    
    
    /**
     * Exclui um contato 
     * @param usuario
     */
    public String excluir(Usuario usuario){                
        if(usuario != null){          
            if(UsuarioUtil.naoRemoveAdmin(usuario)){
                return null;
            }            
            UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());            
            dao.excluir(usuario);
        }else{
            System.out.println("usuario é nulo");                   
        }
        return "";
    }
 
    
    /**
     * Efetua o login
     * @return 
     */
    public String login(){
        if(Validate.existeUsuarioCadastrado(getUsuario().getEmail(), getUsuario().getSenha())){
            SessionUtils.setEmailUsuarioInSession(getUsuario().getEmail()); //seta o email do usuario no contexto
            getUsuario().setSenha(null); //evita que preencha os formulários automaticamente
            if(Validate.isAdmin(getUsuario().getEmail())){
                configAdmin = false;
                return "admin.xhtml";
            }            
            SessionUtils.setIdAgendaInSession(UsuarioUtil.getIDAgenda()); //seta o id da agenda no contexto
            return "listarContatos.xhtml";
        }else{
            setApresentaMsg(UsuarioUtil.cadastroInvalido("Usuário ou senha incorretos", true));            
            novoUsuario();
            return "login.xhtml";
        }
    }
    
    
    public String atualizarSenha(){        
        String senhaAntiga = getUsuario().getSenha();
        String novaSenha = getRepeteSenha();
        UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());
        Usuario usuario = dao.findForEmail(SessionUtils.getUsuarioEmailInSession());
        if(usuario != null){
            if(usuario.getSenha().equals(senhaAntiga)){
                usuario.setSenha(novaSenha);
                dao = new UsuarioDAO(Factory.getConnection());
                dao.atualizar(usuario);
                SessionUtils.setMenssagemInContext("Atualizado com Sucesso!");
                return "listarContatos.xhtml";
            }else{SessionUtils.setMenssagemInContext("Senha incorreta!");}
        }else{
            SessionUtils.setMenssagemInContext("Usuário não encontrado!");
        }
        return null;
    }
    
    //logout event, invalidate session
    public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login.xhtml";
    }
        
    /**
     * Cabeçalho de apresentar o usuario logado
     * @return 
     */
    public String cabecalho(){
        return UsuarioUtil.cabecalhoApresentacao();
    }
    /*****************************************************************************/
    
  
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        UsuarioDAO dao = new UsuarioDAO(Factory.getConnection());
        this.usuarios = dao.listar();        
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
            
    public String getRepeteSenha() {
        return repeteSenha;
    }

    public void setRepeteSenha(String repeteSenha) {
        this.repeteSenha = repeteSenha;
    }
   
    
    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = new Agenda();
    }   
    
     public boolean isApresentaMsg() {
        return apresentaMsg;
    }

    public void setApresentaMsg(boolean apresentaMsg) {
        this.apresentaMsg = apresentaMsg;
    }
  
    public boolean isConfigAdmin() {
        return configAdmin;
    }

    public void setConfigAdmin(boolean configAdmin) {
        this.configAdmin = configAdmin;
    }
}
    