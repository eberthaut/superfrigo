package fr.insalyon.smartfridge.vues;

import java.awt.Dimension;

import java.awt.FlowLayout;

import javax.swing.*;

public class EntreeCategories extends JPanel {
    private FlowLayout layout = new FlowLayout();
    private JList typesList = new JList();
    private JButton choixButton = new JButton();
    private JButton retourButton = new JButton();

    public EntreeCategories() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(layout);
        this.setSize(new Dimension(506, 372));
        typesList.setPreferredSize(new Dimension(200, 300));
        typesList.setMaximumSize(new Dimension(200, 1000));
        typesList.setMinimumSize(new Dimension(200, 0));
        typesList.setSize(new Dimension(200, 100));
        choixButton.setText("Choisir");
        retourButton.setText("Retour");
        this.add(typesList, null);
        this.add(choixButton, null);
        this.add(retourButton, null);
    }
}
