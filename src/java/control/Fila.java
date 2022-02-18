/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.LinkedList;
import java.util.List;

public class Fila<T> {

    private List<T> objetos = new LinkedList<T>();

    public void insere(T t) {
        this.objetos.add(t);
    }

    public T remove() {
        return this.objetos.remove(0);
    }

    public T removeLast() {
        int size = this.objetos.size();
        return this.objetos.remove(size - 1);
    }

    public boolean vazia() {
        return this.objetos.size() == 0;
    }

    public int size() {
        return this.objetos.size();
    }
}
