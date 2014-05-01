package fr.insalyon.smartfridge.vues;

import fr.insalyon.smartfridge.controleurs.MenuRecettesControleur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agabriel on 17/04/14.
 */
public class MenuRecettes extends JPanel {
    MenuRecettesControleur controleur;

    private FlowLayout layout = new FlowLayout();
    private BorderLayout layout1 = new BorderLayout();
    private JPanel panel = new JPanel();
    private JList recettesList = new JList();
    private JButton retourButton = new JButton();
    private JButton activerButton = new JButton();
    private JButton desactiverButton = new JButton();
    private JButton ajouterButton = new JButton();
    private JTextArea text = new JTextArea();
    private JButton supprimerButton = new JButton();

    public MenuRecettes(Fenetre fenetre) {
        controleur = new MenuRecettesControleur(fenetre, this);

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {

        this.setLayout(layout);
        this.setSize(new Dimension(1000, 700));
        recettesList.setPreferredSize(new Dimension(200, 300));
        recettesList.setMaximumSize(new Dimension(200, 1000));
        recettesList.setMinimumSize(new Dimension(200, 0));
        recettesList.setSize(new Dimension(200, 100));
        //recettesList=this.creationCheckList(recettesList);
        retourButton.setText("Retour");
        retourButton.addActionListener(controleur);
        activerButton.setText("Activer la recette");
        activerButton.addActionListener(controleur);
        desactiverButton.setText("Desactiver la recette");
        desactiverButton.addActionListener(controleur);
        ajouterButton.setText("Ajouter une nouvelle recette");
        ajouterButton.addActionListener(controleur);
        supprimerButton.setText("Supprimer la recette");
        supprimerButton.addActionListener(controleur);
        this.add(recettesList, null);
        panel.setLayout(new GridLayout(3,2));
        this.add(panel, null);
        panel.add(retourButton,null);
        panel.add(ajouterButton, null);
        panel.add(activerButton, null);
        panel.add(desactiverButton, null);
        panel.add(supprimerButton);
        text.setText("Activation");
        panel.add(text);
        controleur.creerListe();

    }
    /*public JList creationCheckList(JList rList){
        for(int i =0; i<rList.getModel().getSize(); i++ ) {

            rList.setComponentOrientation(JCheckBox c);
            JCheckBox a= new JCheckBox();
            a = (JCheckBox)rList.getModel().getElementAt(i);
            a.setName((String) rList.getModel().getElementAt(i));
            rList.add(a);
        }
        return rList;
    }*/


    public JButton getRetourButton() {
        return retourButton;
    }

    public JButton getActiverButton() {
        return activerButton;
    }

    public JButton getDesactiverButton() {
        return desactiverButton;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public JButton getSupprimerButton() { return supprimerButton; }

    public JList getRecettesList() {
        return recettesList;
    }

    public JTextArea getText(){ return text;}

    public void setText(String s){ this.text.setText(s);}

}









