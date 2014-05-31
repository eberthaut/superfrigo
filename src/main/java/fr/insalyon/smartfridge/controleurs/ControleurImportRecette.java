package fr.insalyon.smartfridge.controleurs;

import fr.insalyon.smartfridge.modeles.PropositionRecherche;
import fr.insalyon.smartfridge.services.ServiceImportInternet;
import fr.insalyon.smartfridge.services.ServiceImportMarmiton;
import fr.insalyon.smartfridge.utilitaires.Fenetre;
import fr.insalyon.smartfridge.utilitaires.ListModel;
import fr.insalyon.smartfridge.vues.VueImportRecette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Gere l'import depuis un service Internet */
public class ControleurImportRecette implements ActionListener {
    /** La vue */
    private VueImportRecette vue;
    /** La fenetre de l'application */
    private Fenetre fenetre;
    /** Le service d'import */
    private ServiceImportInternet serviceImport = new ServiceImportMarmiton();

    /** Constructeur
     *
     * @param fenetre La fenetre de l'application
     * @param vue La vue
     */
    public ControleurImportRecette(Fenetre fenetre, VueImportRecette vue) {
        this.fenetre = fenetre;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(vue.getRechercheButton())) {
            String recherche = vue.getRechercheField().getText();
            ListModel<PropositionRecherche> liensRecettes = new ListModel<PropositionRecherche>(serviceImport.rechercherRecettes(recherche));
            vue.getPropositionsList().setModel(liensRecettes);
        } else if(e.getSource().equals(vue.getImportButton())) {
            serviceImport.importRecette((PropositionRecherche) vue.getPropositionsList().getSelectedValue());
            fenetre.retourArriere();
        }
    }
}
