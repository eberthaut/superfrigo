package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EditerListeCoursesControleur;

import javax.swing.*;
import java.awt.*;


public class EditerListeCourses extends SousPanneau {
    EditerListeCoursesControleur controleur;

    private JList listeDeCourses = new JList();
    private JScrollPane scroll = new JScrollPane(listeDeCourses);
    private JPanel options = new JPanel();
    private JButton changerHabitudesButton = new JButton();
    private JButton impressionButton = new JButton();

    public EditerListeCourses(Fenetre fenetre) {
        super(fenetre);
        controleur = new EditerListeCoursesControleur(fenetre, this);

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.add(scroll, BorderLayout.CENTER);

        options.setLayout(new GridLayout(1, 2));
        changerHabitudesButton.setText("Changer mes habitudes");
        changerHabitudesButton.setIcon(new ImageIcon(getClass().getResource("/icones/utilisateur.png")));
        changerHabitudesButton.addActionListener(controleur);
        options.add(changerHabitudesButton);
        impressionButton.setText("Imprimer");
        //impressionButton.setIcon(new ImageIcon(getClass().getResource("/icones/desactiver.png")));
        impressionButton.addActionListener(controleur);
        options.add(impressionButton);
        this.add(options, BorderLayout.SOUTH);
        controleur.creerListe();
        controleur.rafraichirListe();
    }

    public JButton getChangerHabitudesButton() { return changerHabitudesButton; }
    public JButton getImpressionButton() { return impressionButton; }
    public JList getListeDeCourses() { return listeDeCourses; }

}





