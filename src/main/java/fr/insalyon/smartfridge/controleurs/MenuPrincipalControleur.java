package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.services.ServiceAlerte;
import fr.insalyon.smartfridge.services.ServiceThermodynamique;
import fr.insalyon.smartfridge.vues.EntreeCategories;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.MenuPrincipal;
import fr.insalyon.smartfridge.vues.SortieAliment;

import java.awt.*;
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
        } if(e.getSource().equals(menu.getRetirerButton())) {
            fenetre.allerA(new SortieAliment(fenetre));
        }
    }

    public void gererAlerte() {
        int status = ServiceAlerte.statusAlerte(2);
        switch(status) {
            case 1:
                menu.getAlerteButton().setEnabled(true);
                menu.getAlerteButton().setForeground(new Color(200, 150, 0));
                menu.getAlerteButton().setText("Des aliments sont proches de la peremption");
                break;
            case 2:
                menu.getAlerteButton().setEnabled(true);
                menu.getAlerteButton().setForeground(new Color(200, 0, 0));
                menu.getAlerteButton().setText("Des aliments sont perimes");
                break;
        }
    }

    public void gererTemperature() {
        menu.getTemperatureLabel().setText(ServiceThermodynamique.mettreAJourTemperature() + " degres Celcius");
    }
}
