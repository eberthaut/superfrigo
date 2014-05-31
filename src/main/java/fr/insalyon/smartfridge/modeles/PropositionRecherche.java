package fr.insalyon.smartfridge.modeles;

public class PropositionRecherche {
    private String nom;
    private String id;

    public PropositionRecherche(String nom, String id) {
        this.nom = nom;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return nom;
    }
}
