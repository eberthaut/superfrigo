package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.services.ServiceAlerte;
import fr.insalyon.smartfridge.services.ServiceThermodynamique;
import fr.insalyon.smartfridge.vues.EntreeCategories;
import fr.insalyon.smartfridge.vues.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalControleur implements ActionListener {
    // On decouple la recuperation des actions des vues
    Fenetre fenetre;
    MenuPrincipal menu;
    JButton lastButton;

    public MenuPrincipalControleur(Fenetre fenetre, MenuPrincipal menu) {
        this.fenetre = fenetre;
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() != lastButton) {
            lastButton = (JButton)e.getSource();
            if(lastButton.equals(menu.getAjouterButton())) {
                fenetre.allerA(new EntreeCategories(fenetre));
            } else if(lastButton.equals(menu.getRetirerButton())) {
                fenetre.allerA(new SortieAliment(fenetre));
            } else if(lastButton.equals(menu.getRecettesButton())) {
                fenetre.allerA(new MenuRecettes(fenetre));
            } else if(lastButton.equals(menu.getCoursesButton())) {
                fenetre.allerA(new EditerListeCourses(fenetre));
            } else if(lastButton.equals(menu.getAlerteButton()) && menu.getAlerteButton().isEnabled()) {
                fenetre.allerA(new AlertePeremption(fenetre));
            }
        }
    }

    public void gererAlerte() {
        int status = ServiceAlerte.statusAlerte(2);
        switch(status) {
            case 0:
                menu.getAlerteButton().setEnabled(false);
                menu.getAlerteButton().setText("Rien a signaler");
                menu.getAlerteButton().setIcon(new ImageIcon(getClass().getResource("/icones/ok.png")));
                break;
            case 1:
                menu.getAlerteButton().setEnabled(true);
                menu.getAlerteButton().setForeground(new Color(200, 150, 0));
                menu.getAlerteButton().setText("Des aliments sont proches de la peremption");
                menu.getAlerteButton().setIcon(new ImageIcon(getClass().getResource("/icones/danger.png")));
                menu.getAlerteButton().addActionListener(this);
                break;
            case 2:
                menu.getAlerteButton().setEnabled(true);
                menu.getAlerteButton().setForeground(new Color(200, 0, 0));
                menu.getAlerteButton().setText("Des aliments sont perimes");
                menu.getAlerteButton().setIcon(new ImageIcon(getClass().getResource("/icones/erreur.png")));
                menu.getAlerteButton().addActionListener(this);
                break;
        }
    }

    public void gererTemperature() {
        menu.getTemperatureLabel().setText(String.format("%.2f degres Celcius",ServiceThermodynamique.mettreAJourTemperature()));
    }
}
