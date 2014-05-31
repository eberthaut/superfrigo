package fr.insalyon.smartfridge.modeles;

/** Represente une proposition dans les resultats de recherche
 *
 * N'est pas connu par JPA
 */
public class PropositionRecherche {
    /** Le nom */
    private String nom;
    /** L'identifiant ou le chemin vers le resultat */
    private String id;

    /** Le constructeur
     *
     * @param nom Le nom
     * @param id L'identifiant ou le chemin vers le resultat
     */
    public PropositionRecherche(String nom, String id) {
        this.nom = nom;
        this.id = id;
    }

    /** Retourne Le nom
     *
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /** Retourne L'identifiant
     *
     * @return L'identifiant
     */
    public String getId() {
        return id;
    }

    /** Resume la PropositionRecherche
     *
     * @return Le resume
     */
    public String toString() {
        return nom;
    }
}
