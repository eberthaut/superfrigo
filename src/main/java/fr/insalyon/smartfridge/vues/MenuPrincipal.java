package fr.insalyon.smartfridge.vues;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(400, 300));
        this.setTitle("SmartFridge");
    }
}
