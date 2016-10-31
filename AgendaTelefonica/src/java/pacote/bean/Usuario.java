/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.bean;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author RHUAN
 */

@Entity()
public class Usuario implements Serializable{

    private static final long serialVersionUID = -3128950798222375291L;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String senha;
    
    
    @OneToOne(cascade = CascadeType.ALL)     
    private Agenda agenda;
    
    
    public Usuario() {
        this.agenda = new Agenda();
    }

    public Usuario(String email, String senha) {    
        this.email = email;
        this.senha = senha;
    }
    
    /****************************************************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = new Agenda();
    }

    
    
}
