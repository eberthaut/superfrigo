package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.ControleurMenuPrincipal;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.Raccourcis;

import java.awt.*;

import javax.swing.*;

/** Vue du menu principal */
public class VueMenuPrincipal extends JPanel implements Rafraichissable {
    /** Le controleur */
    private final ControleurMenuPrincipal controleur;

    /** L'affichage de temperature */
    private final JLabel temperatureLabel = new JLabel();
    /** Le bouton pour ajouter */
    private final JButton ajouterButton = new JButton("Ajouter des Articles", Raccourcis.icone("ajout-articles"));
    /** Le bouton pour retirer */
    private final JButton retirerButton = new JButton("Retirer des articles", Raccourcis.icone("retrait-articles"));
    /** Le bouton pour la liste de courses */
    private final JButton coursesButton = new JButton("Generer une liste de courses", Raccourcis.icone("liste"));
    /** Le bouton d'alerte */
    private final JButton alerteButton = new JButton();
    /** Le menu deroulant d'alerte */
    private final JComboBox alerteCombo = new JComboBox();
    /** Le bouton pour les recettes */
    private final JButton recettesButton = new JButton("Gerer les Recettes", Raccourcis.icone("recettes"));

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     */
    public VueMenuPrincipal(Fenetre fenetre) {
        controleur = new ControleurMenuPrincipal(fenetre, this);

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
        JLabel alerteLabel = new JLabel(Raccourcis.html("Nombre de jours avant d'alerter <br> une peremption proche :"));
        alerteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        alertePanel.add(alerteLabel);
        alertePanel.add(alerteCombo);
        alertePanel.add(alerteButton);
        this.add(alertePanel);

        this.add(coursesButton);

        this.add(recettesButton);
    }

    @Override
    public void mettreAJour() {
        controleur.mettreAJour();
    }

    /** Retourne L'affichage de temperature
     *
     * @return L'affichage de temperature
     */
    public JLabel getTemperatureLabel() {
        return temperatureLabel;
    }

    /** Retourne Le bouton pour ajouter
     *
     * @return Le bouton pour ajouter
     */
    public JButton getAjouterButton() {
        return ajouterButton;
    }

    /** Retourne Le bouton pour retirer
     *
     * @return Le bouton pour retirer
     */
    public JButton getRetirerButton() {
        return retirerButton;
    }

    /** Retourne Le bouton pour la liste de courses
     *
     * @return Le bouton pour la liste de courses
     */
    public JButton getCoursesButton() {
        return coursesButton;
    }

    /** Retourne Le bouton d'alerte
     *
     * @return Le bouton d'alerte
     */
    public JButton getAlerteButton() {
        return alerteButton;
    }

    /** Retourne Le bouton pour les recettes
     *
     * @return Le bouton pour les recettes
     */
    public JButton getRecettesButton() {
        return recettesButton;
    }

    /** Retourne Le menu deroulant d'alerte
     *
     * @return Le menu deroulant d'alerte
     */
    public JComboBox getAlerteCombo() {
        return alerteCombo;
    }
}
