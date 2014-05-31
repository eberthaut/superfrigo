package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.vues.VueSousPanneau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere tout ce qui n'est pas le menu principal */
public class ControleurSousPanneau implements ActionListener {
    /** La fenetre de l'application */
    private final Fenetre fenetre;
    /** La vue */
    private final VueSousPanneau vue;

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param vue La vue
     */
    public ControleurSousPanneau(Fenetre fenetre, VueSousPanneau vue) {
        this.fenetre = fenetre;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getButtonRetour()) {
            actionRetour();
        } else if(e.getSource() == vue.getButtonMenuPrincipal()) {
            actionMenuPrincipal();
        }
    }

    /** Quand un clique sur retour */
    private void actionRetour() {
        fenetre.retourArriere();
    }

    /** Quand on clique sur menu principal */
    private void actionMenuPrincipal() {
        fenetre.revenirDebut();
    }
}
