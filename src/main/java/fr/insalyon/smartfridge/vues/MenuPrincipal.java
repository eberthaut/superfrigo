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
    private JButton recettesButton = new JButton();

    public MenuPrincipal(Fenetre fenetre) {
        controleur = new MenuPrincipalControleur(fenetre, this);
        try {
            jbInit();
            controleur.gererAlerte();
            controleur.gererTemperature();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(layout);
        temperatureLabel.setText("...");
        ajouterButton.setText("Ajouter des articles");
        ajouterButton.addActionListener(controleur);
        retirerButton.setText("Retirer des articles");
        retirerButton.addActionListener(controleur);
        coursesButton.setText("Editer une liste de courses");
        coursesButton.addActionListener(controleur);
        alerteButton.setText("Rien a signaler");
        alerteButton.setEnabled(false);
        recettesButton.setText("Recettes");
        recettesButton.addActionListener(controleur);
        this.add(temperatureLabel, null);
        this.add(ajouterButton, null);
        this.add(retirerButton, null);
        this.add(coursesButton, null);
        this.add(alerteButton, null);
        this.add(recettesButton, null);
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

    public JButton getRecettesButton() {
        return recettesButton;
    }
}
