package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurSousPanneau;

import javax.swing.*;
import java.awt.*;

public abstract class SousPanneau extends JPanel {
    private ControleurSousPanneau controleurPrincipal;

    private JComponent retours = new JPanel();
    private JButton buttonRetour = new JButton();
    private JButton buttonMenuPrincipal = new JButton();


    public SousPanneau(Fenetre fenetre) {
        controleurPrincipal = new ControleurSousPanneau(fenetre, this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(1000,700));

        retours.setLayout(new GridLayout(1,2));

        getButtonRetour().setText("Retour");
        getButtonRetour().setIcon(new ImageIcon(getClass().getResource("/icones/retour.png")));
        getButtonRetour().addActionListener(controleurPrincipal);
        retours.add(getButtonRetour());

        getButtonMenuPrincipal().setText("Menu Principal");
        getButtonMenuPrincipal().setIcon(new ImageIcon(getClass().getResource("/icones/menu-principal.png")));
        getButtonMenuPrincipal().addActionListener(controleurPrincipal);
        retours.add(getButtonMenuPrincipal());

        this.add(retours, BorderLayout.NORTH);
    }

    public JButton getButtonRetour() {
        return buttonRetour;
    }

    public JButton getButtonMenuPrincipal() {
        return buttonMenuPrincipal;
    }
}
