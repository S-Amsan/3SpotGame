package SpotGame.structure;

import SpotGame.utils.Position;
import SpotGame.utils.Symbole;

/** Représente un élément du plateau
 */
public class Element {

    // Attributs de l'élément :
    private final Position position;
    private final Symbole symbole;

    /** Cree un élément
     * @param ligne la ligne où il se trouve sur le plateau
     * @param colonne la colonne où il se trouve sur le plateau
     * @param symbole le symbole qui le représentera sur le plateau
     */
    public Element(int ligne, int colonne, Symbole symbole) {
        this.position = new Position(ligne,colonne);
        this.symbole = symbole;
    }

    // ToString :
    /**
     *@return le symbole à afficher
     */
    public String toString(){return symbole.toString();}
    // Getter et Setter:
    /**
     *@return le symbole de l'élément
     */
    public Symbole getSymbole() { return symbole; }
    public int getColonne() { return position.getColonne(); }
    public int getLigne() { return position.getLigne(); }
    public Position getPosition() { return position; }

    /** Change la position
     *@param ligne valeur de la ligne
     *@param colonne valeur de la colonne
     */
    public void setPosition(int ligne, int colonne) { position.setPosition(ligne,colonne); } // Change la position de la piece avec celle donné


}
