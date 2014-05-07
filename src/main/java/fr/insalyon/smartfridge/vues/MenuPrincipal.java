package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.MenuPrincipalControleur;

import java.awt.*;

import javax.swing.*;

public class MenuPrincipal extends JPanel implements Changeable {
// c'est le contenu de la fenetre par defaut
    private MenuPrincipalControleur controleur;

    private GridLayout layout = new GridLayout(2, 3);

    private JLabel temperatureLabel = new JLabel();
    private JButton ajouterButton = new JButton();
    private JButton retirerButton = new JButton();
    private JButton coursesButton = new JButton();
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
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(temperatureLabel, null);

        ajouterButton.setText("Ajouter des articles");
        ajouterButton.addActionListener(controleur);
        this.add(ajouterButton, null);

        retirerButton.setText("Retirer des articles");
        retirerButton.addActionListener(controleur);
        this.add(retirerButton, null);

        alerteButton.setText("Rien a signaler");
        alerteButton.setEnabled(false);
        this.add(alerteButton, null);

        coursesButton.setText("Generer une liste de courses");
        coursesButton.addActionListener(controleur);
        this.add(coursesButton, null);

        recettesButton.setText("Gerer les Recettes");
        recettesButton.addActionListener(controleur);
        this.add(recettesButton, null);
    }

    public void mettreAJour() {
        super.repaint();
        controleur.gererAlerte();
        controleur.gererTemperature();
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
