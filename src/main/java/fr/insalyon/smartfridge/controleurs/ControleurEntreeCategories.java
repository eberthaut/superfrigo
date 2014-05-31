package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.Type;
import fr.insalyon.smartfridge.services.ServiceStock;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.utilitaires.Rafraichissable;
import fr.insalyon.smartfridge.vues.VueEntreeArticles;
import fr.insalyon.smartfridge.vues.VueEntreeCategories;
import fr.insalyon.smartfridge.utilitaires.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere le choix d'un type lors de l'ajout */
public class ControleurEntreeCategories implements ActionListener, Rafraichissable {
    /** La fenetre de l'application */
    private Fenetre fenetre;
    /** La vue */
    private VueEntreeCategories vue;
    /** Le modele des types */
    private ListModel<Type> types;

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param vue La vue
     */
    public ControleurEntreeCategories(Fenetre fenetre, VueEntreeCategories vue) {
        this.fenetre = fenetre;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vue.getChoixButton()) {
            Type t = types.get(vue.getTypesList().getSelectedIndex());
            fenetre.allerA(new VueEntreeArticles(fenetre, t));
        }
    }

    @Override
    public void mettreAJour() {
        types = new ListModel<Type>(ServiceStock.listerTypes()); // On récupère tous les types
        vue.getTypesList().setModel(types);
    }
}
