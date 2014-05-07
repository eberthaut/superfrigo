package fr.insalyon.smartfridge.controleurs;


import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.SousPanneau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurSousPanneau implements ActionListener {
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    SousPanneau panneau;

    public ControleurSousPanneau(Fenetre fenetre, SousPanneau panneau) {
        this.fenetre = fenetre;
        this.panneau = panneau;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == panneau.getButtonRetour()) {
            fenetre.retourArriere();
        } else if(e.getSource() == panneau.getButtonMenuPrincipal()) {
            fenetre.revenirDebut();
        }
    }
}
