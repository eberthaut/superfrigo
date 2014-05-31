package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurSousPanneau;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import javax.swing.*;
import java.awt.*;

public abstract class VueSousPanneau extends JPanel {
    private JButton buttonRetour = new JButton("Retour", Raccourcis.icone("retour"));
    private JButton buttonMenuPrincipal = new JButton("Menu Principal", Raccourcis.icone("menu-principal"));

    public VueSousPanneau(Fenetre fenetre) {
        ControleurSousPanneau controleurPrincipal = new ControleurSousPanneau(fenetre, this);

        buttonRetour.addActionListener(controleurPrincipal);
        buttonMenuPrincipal.addActionListener(controleurPrincipal);

        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(1000,700));
        this.setBackground(Color.WHITE);

        JPanel retours = new JPanel(new GridLayout(1, 2));
        retours.setBackground(Color.WHITE);
        retours.add(buttonRetour);
        retours.add(buttonMenuPrincipal);
        this.add(retours, BorderLayout.NORTH);
    }

    public JButton getButtonRetour() {
        return buttonRetour;
    }

    public JButton getButtonMenuPrincipal() {
        return buttonMenuPrincipal;
    }
}
