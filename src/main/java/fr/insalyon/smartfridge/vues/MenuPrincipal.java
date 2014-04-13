package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.MenuPrincipalControleur;

import java.awt.FlowLayout;

import javax.swing.*;

public class MenuPrincipal extends JPanel {
// c'est le contenu de la fenetre par defaut
    private MenuPrincipalControleur controleur;

    private JLabel temperatureLabel = new JLabel();
    private JButton ajouterButton = new JButton();
    private JButton retirerButton = new JButton();
    private JButton coursesButton = new JButton();
    private FlowLayout layout = new FlowLayout();
    private JButton alerteButton = new JButton();

    public MenuPrincipal(Fenetre fenetre) {
        controleur = new MenuPrincipalControleur(fenetre, this);
        try {
            jbInit();
            controleur.gererAlerte();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(layout);
        temperatureLabel.setText("5 degres Celcius");
        ajouterButton.setText("Ajouter des articles");
        ajouterButton.addActionListener(controleur);
        retirerButton.setText("Retirer des articles");
        retirerButton.addActionListener(controleur);
        coursesButton.setText("Editer une liste de courses");
        alerteButton.setText("Rien a signaler");
        alerteButton.setEnabled(false);
        this.add(temperatureLabel, null);
        this.add(ajouterButton, null);
        this.add(retirerButton, null);
        this.add(coursesButton, null);
        this.add(alerteButton, null);
    }

    public JLabel getTemperatureLabel() {
        return temperatureLabel;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public JButton getRetirerButton() {
        return retirerButton;
    }

    public JButton getCoursesButton() {
        return coursesButton;
    }

    public JButton getAlerteButton() {
        return alerteButton;
    }
}
