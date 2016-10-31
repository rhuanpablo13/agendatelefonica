package pacote.interfaces;


import java.util.List;
import javax.persistence.EntityManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RHUAN
 */
public interface DAOInterface<T> {
    T create();
    void adicionar(T obj);
    void excluir(T obj);
    void atualizar(T obj);
    List<T>listar();
    T findForId(int id);
    T findForString(String string);
}
