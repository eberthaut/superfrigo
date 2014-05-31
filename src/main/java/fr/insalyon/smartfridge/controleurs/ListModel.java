package fr.insalyon.smartfridge.controleurs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ListModel<E> extends DefaultListModel {
    public ListModel() {

    }

    public ListModel(List<E> elements) {
        for(E e : elements) {
            this.addElement(e);
        }
    }

    public List<E> getList() {
        List<E> list = new ArrayList<E>();
        Enumeration<E> it = (Enumeration<E>)this.elements();
        while(it.hasMoreElements()) {
            list.add(it.nextElement());
        }
        return list;
    }

    public E get(int index) {
        return (E)super.get(index);
    }
}
