package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.MenuPrincipalControleur;

import java.awt.*;

import javax.swing.*;

public class MenuPrincipal extends JPanel implements Changeable {
// c'est le contenu de la fenetre par defaut
    private MenuPrincipalControleur controleur;

    private JLabel temperatureLabel = new JLabel();
    private JButton ajouterButton = new JButton("Ajouter des Articles", UtilitairesVues.icone("ajout-articles"));
    private JButton retirerButton = new JButton("Retirer des articles", UtilitairesVues.icone("retrait-articles"));
    private JButton coursesButton = new JButton("Generer une liste de courses", UtilitairesVues.icone("liste"));
    private JButton alerteButton = new JButton();
    private JComboBox alerteCombo = new JComboBox();
    private JButton recettesButton = new JButton("Gerer les Recettes", UtilitairesVues.icone("recettes"));

    public MenuPrincipal(Fenetre fenetre) {
        controleur = new MenuPrincipalControleur(fenetre, this);

        ajouterButton.addActionListener(controleur);
        retirerButton.addActionListener(controleur);
        alerteCombo.addActionListener(controleur);
        coursesButton.addActionListener(controleur);
        recettesButton.addActionListener(controleur);

        this.setLayout(new GridLayout(2, 3));
        this.setBackground(Color.WHITE);

        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(temperatureLabel);

        this.add(ajouterButton);

        this.add(retirerButton);

        JPanel alertePanel = new JPanel(new GridLayout(3, 1));
        alertePanel.setBackground(Color.WHITE);
        JLabel alerteLabel = new JLabel(UtilitairesVues.html("Nombre de jours avant d'alerter <br> une peremption proche :"));
        alerteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alertePanel.add(alerteLabel);
        alertePanel.add(alerteCombo);
        alertePanel.add(alerteButton);
        this.add(alertePanel);

        this.add(coursesButton);

        this.add(recettesButton);
    }

    public void mettreAJour() {
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

    public JComboBox getAlerteCombo() {
        return alerteCombo;
    }
}
