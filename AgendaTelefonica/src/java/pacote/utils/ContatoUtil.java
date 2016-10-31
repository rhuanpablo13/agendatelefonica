/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.utils;

import pacote.bean.Contato;
import pacote.dao.ContatoDAO;

/**
 *
 * @author RHUAN
 */
public class ContatoUtil{
  
    private static final long serialVersionUID = -3222877546045413736L;

    
    /**
     * Atualiza os dados de um objeto contato (auxiliar) e retorna
     * @param id
     * @param idAgenda
     * @return objeto contato com dados atualizados
     */
    public static Contato atualizaContato(Contato dadosAntigos, Contato dadosNovos, int idAgenda){        
        Contato c = dadosNovos;
        c.setId(dadosAntigos.getId());
        c.setFk_agenda(idAgenda);
        return c;
    }
    
    
    
    
}
