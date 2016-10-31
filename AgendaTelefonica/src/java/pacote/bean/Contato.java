package pacote.bean;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RHUAN
 */

@Entity()
public class Contato implements Serializable{
    private static final long serialVersionUID = -3369612147295439766L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    
    private int fk_agenda; //vai guardar o id da agenda a qual pertence
    
    
    
    

   
    
   
    

    public Contato() {
    }

    public Contato(String nome, String telefone) {    
        this.nome = nome;
        this.telefone = telefone;        
    }
     
    public Contato(String nome, String telefone, String endereco, String email) {        
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }
    
     public Contato(int id, String nome, String telefone, String endereco, String email) {        
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", email=" + email + ", fk_agenda=" + fk_agenda + '}';
    }


     
     
     
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getFk_agenda() {
        return fk_agenda;
    }

    public void setFk_agenda(int fk_agenda) {
        this.fk_agenda = fk_agenda;
    }
    
    
    
    
}
