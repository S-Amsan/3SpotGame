package SpotGame.utils;

/** Represente une position avec le numero de la ligne et colonne
 */
public class Position {
    /** La position N ligne
     */
    private int ligne;
    /** La position N colonne
     */
    private int colonne;

    /** Crée une position
     * @param ligne Sur quelle ligne se situe l’objet?
     * @param colonne Sur quelle colonne se situe l’objet?
     */
    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    // Getter et Setter :
    public int getLigne() {
        return ligne;
    }
    public int getColonne() {
        return colonne;
    }

    public void setPosition(int ligne,int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }
    public boolean A_la_meme_position_que(Position position) {
        return this.ligne == position.ligne && this.colonne == position.colonne;
    }
}
