package SpotGame.structure;

/** Représente un joueur de la partie
 */
public class Joueur {

    // Constantes :
    public static final int SCORE_INITIAL = 0;
    private static final int SCORE_MINIMAL_REQUIS = 6;
    private static final int SCORE_VICTOIRE = 12;

    // Attributs du joueur :
    private final Piece piece;
    private int score;

    /** Crée un joueur
     * @param piece la pièce qu'il possède
     */
    public Joueur(Piece piece) {
        this.piece = piece;
        score = SCORE_INITIAL;
    }

    // ToString :
    /**
     *@return le score du joueur
     */
    public String toString(){ return Integer.toString(score); }

    // Getter et Setter:
    public Piece getPiece() { return piece; }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    // Fonctions :

    /** Ajoute un point pour chaque carré de la pièce du joueur qui se trouve sur un spot
     */
    public void A_sa_piece_sur_un_spot() {
        for (Element carre : piece.getCarre()) {
            if (carre.getColonne() == Spot.COLONNE_OCCUPE) { // Si un carré se trouve dans la meme colonne que les spots
                ++score; // On ajoute un point
            }
        }

    }

    /** Vérifie si le joueur a rempli la condition de victoire
     * @return true si le joueur a un score de 12 point ou plus
     */
    public boolean A_rempli_la_condition_de_victoire() {
        return score >= SCORE_VICTOIRE;
    }

    /** Vérifie que le joueur a atteint le pallier des 6 points
     * @return true si le joueur a au moins 6 points
     */
    public boolean A_au_moins_six_points() {
        return score >= SCORE_MINIMAL_REQUIS;
    }

    /** Vérifie si le joueur appelé gagne contre le joueur en paramètre
     * @return true si il gagne
     */
    public boolean A_gagne_contre(Joueur adversaire) {
        // le joueur peut gagner de DEUX manières :
        // 1. Il a 12 point ou plus et l'adversaire en a au moins 6
        // 2. Il a strictement moins de 6 points lorsque le joueur adverse atteint 12 points
        return this.A_rempli_la_condition_de_victoire() && adversaire.A_au_moins_six_points() || !this.A_au_moins_six_points();
    }
}