package fr.insalyon.smartfridge.utilitaires;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/** Modele d'abstraction des modeles de listes avec toString() */
public class ListModel<E> extends DefaultListModel {
    /** Constructeur */
    public ListModel() {

    }

    /** Ajoute une liste d'elements
     *
     * @param elements La liste
     */
    public ListModel(List<E> elements) {
        for(E e : elements) {
            this.addElement(e);
        }
    }

    /** Retourne la liste d'elements
     *
     * @return La liste
     */
    public List<E> getList() {
        List<E> list = new ArrayList<E>();
        Enumeration<E> it = (Enumeration<E>)this.elements();
        while(it.hasMoreElements()) {
            list.add(it.nextElement());
        }
        return list;
    }

    /** Obtiens un index particulier
     *
     * @param index L'index
     * @return L'element
     */
    public E get(int index) {
        return (E)super.get(index);
    }
}
