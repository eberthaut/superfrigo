package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.EditerListeCoursesControleur;
import fr.insalyon.smartfridge.controleurs.MenuRecettesControleur;

import javax.swing.*;
import java.awt.*;


public class EditerListeCourses extends JPanel {
    EditerListeCoursesControleur controleur;

    private JList listeDeCourses = new JList();
    private JButton changerHabitudesButton = new JButton();
    private JButton retourButton = new JButton();
    private JButton reinitialiserButton = new JButton();
    private FlowLayout layout = new FlowLayout();
    private JScrollPane scrollPane = new JScrollPane(listeDeCourses);

    public EditerListeCourses(Fenetre fenetre) {
        controleur = new EditerListeCoursesControleur(fenetre, this);

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(layout);
        this.setSize(new Dimension(1000, 700));
        scrollPane.setPreferredSize(new Dimension(250,450));

        //listeDeCourses.setPreferredSize(new Dimension(250, 450));

        changerHabitudesButton.setText("Changer mes habitudes");
        reinitialiserButton.setText("Réinitialiser la liste");
        retourButton.setText("Retour");


        changerHabitudesButton.addActionListener(controleur);
        retourButton.addActionListener(controleur);
        reinitialiserButton.addActionListener(controleur);

        //this.add(listeDeCourses);
        this.add(reinitialiserButton);
        this.add(changerHabitudesButton);
        this.add(retourButton);
        this.add(scrollPane);

    }

    public JButton getChangerHabitudesButton() { return changerHabitudesButton; }
    public JButton getRetourButton() { return retourButton; }
    public JButton getReinitialiserButton() { return reinitialiserButton; }
    public JList getListeDeCourses() { return listeDeCourses; }

}





