package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Recette;
import fr.insalyon.smartfridge.services.ServiceCourses;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.vues.MenuRecettes;
import fr.insalyon.smartfridge.vues.Fenetre;
import fr.insalyon.smartfridge.vues.EntreeRecettes;
import org.eclipse.persistence.eis.RecordConverter;
import org.eclipse.persistence.internal.libraries.asm.tree.analysis.Value;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by agabriel on 17/04/14.
 */
public class MenuRecettesControleur implements ActionListener {

    Fenetre fenetre;
    MenuRecettes menuRecettes;
    ListModel recettes;

    public MenuRecettesControleur(Fenetre fenetre, MenuRecettes menuRecettes) {
        this.fenetre = fenetre;
        this.menuRecettes = menuRecettes;
        recettes = new ListModel(ServiceCourses.listerRecettes());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //menuRecettes.getRecettesList().setCellRenderer(new MyCellRenderer());
        //menuRecettes.getRecettesList().getCellRenderer().getListCellRendererComponent(menuRecettes.getRecettesList(),recettes.getRecettesAt(menuRecettes.getRecettesList().getSelectedIndex()), 1, true, true);

        System.out.println("test");

        if(e.getSource() == menuRecettes.getRetourButton()) {
            fenetre.retourArriere();
        } else if(e.getSource() == menuRecettes.getActiverButton()) {
            Recette r = recettes.getRecettesAt(menuRecettes.getRecettesList().getSelectedIndex());
            // r= initialisationR();
            ServiceCourses.activerRecette(r);
            activation(r);


        } else if(e.getSource() == menuRecettes.getDesactiverButton()) {
            Recette r = recettes.getRecettesAt(menuRecettes.getRecettesList().getSelectedIndex());
            //r= initialisationR();
            ServiceCourses.desactiverRecette(r);
            activation(r);

        } else if(e.getSource().equals(menuRecettes.getAjouterButton())) {
            fenetre.allerA(new EntreeRecettes(fenetre));

        }else if(e.getSource().equals(menuRecettes.getSupprimerButton())) {
            Recette r = recettes.getRecettesAt(menuRecettes.getRecettesList().getSelectedIndex());
            ServiceCourses.retraitRecette(r.getNom());
            rafraichirListe();
        }
    }

    public void creerListe() {
        menuRecettes.getRecettesList().setModel(recettes);
        menuRecettes.getRecettesList().setCellRenderer(new MyCellRenderer());
    }


    public void rafraichirListe() {
        recettes = new ListModel(ServiceCourses.listerRecettes());
        menuRecettes.getRecettesList().setModel(recettes);
    }

    /*public Recette initialisationR(){
        int i;
        Recette r = new Recette();
        for(i=0; i<menuRecettes.getRecettesList().getSelectedIndices().length; i++){
            if(i == menuRecettes.getRecettesList().getSelectedIndex()){
                r= recettes.getRecettesAt(i);

            }else{
            }
        }
        return r;
    }*/
    public void activation(Recette r){

        Color couleur = Color.black;
        String sActif = "Recette activée";
        String sNonActif = "Recette non activée";

        if(r.isActif()){
            System.out.println(r.isActif());
            menuRecettes.getText().setText(sActif );
            menuRecettes.getText().setBackground(Color.pink);
        }else{
            System.out.println(r.isActif());
            menuRecettes.getText().setText(sNonActif );
            menuRecettes.getText().setBackground(Color.lightGray);
        }
    }
    private class ListModel extends AbstractListModel {
        List<Recette> recettes;

        public ListModel(List<Recette> recettes) {
            this.recettes = recettes;
        }

        @Override // demande par la JList (on doit implementer un AbstractListModel)
        public int getSize() {
            return recettes.size();
        }

        @Override
        public Object getElementAt(int i) {
            return recettes.get(i).getNom();
        }

        public Recette getRecettesAt(int i) {
            if(i==0){

            }
            return recettes.get(i);
        }
    }

    class MyCellRenderer extends JLabel implements ListCellRenderer {
        MenuRecettesControleur mRControleur=new MenuRecettesControleur(fenetre, menuRecettes);


        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {

            Component c = null;
            if (value == null) {
                c = new JLabel("(null)");
            } else if (value instanceof Component) {
                c = (Component) value;
            } else {
                c = new JLabel(value.toString());
            }

            if (isSelected) {
                Recette r = recettes.getRecettesAt(index);
                System.out.println(recettes.getRecettesAt(index)+"" + "et l'index:" + menuRecettes.getRecettesList().getSelectedIndex() );
                mRControleur.activation(r);
                c.setBackground(list.getSelectionBackground());
                c.setForeground(list.getForeground());
            } else {
                c.setBackground(list.getBackground());
                c.setForeground(list.getForeground());
            }

            if (c instanceof JComponent) {
                ((JComponent) c).setOpaque(true);
            }

            return c;
        }

    }

}

