package fr.insalyon.smartfridge.controleurs;

import javax.swing.*;
import java.util.List;

public class ListModel<E> extends DefaultListModel<E> {
    public ListModel(List<E> elements) {
        for(E e : elements) {
            this.addElement(e);
        }
    }
}
