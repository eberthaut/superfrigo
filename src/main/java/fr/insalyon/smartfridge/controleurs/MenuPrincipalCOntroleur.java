package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.vues.EntreeCategories;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.MenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalControleur implements ActionListener {
// On decouple la recuperation des actions des vues
    Fenetre fenetre;
    MenuPrincipal menu;

    public MenuPrincipalControleur(Fenetre fenetre, MenuPrincipal menu) {
        this.fenetre = fenetre;
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(menu.getAjouterButton())) {
            fenetre.allerA(new EntreeCategories(fenetre));
        }
    }
}
