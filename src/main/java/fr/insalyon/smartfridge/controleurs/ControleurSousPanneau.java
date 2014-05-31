package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.vues.VueSousPanneau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurSousPanneau implements ActionListener {
    // On decouple la recuperation des actions des vues
    private Fenetre fenetre;
    private VueSousPanneau vue;

    public ControleurSousPanneau(Fenetre fenetre, VueSousPanneau vue) {
        this.fenetre = fenetre;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getButtonRetour()) {
            fenetre.retourArriere();
        } else if(e.getSource() == vue.getButtonMenuPrincipal()) {
            fenetre.revenirDebut();
        }
    }
}
