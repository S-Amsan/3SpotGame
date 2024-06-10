package SpotGame.structure;

import SpotGame.utils.Symbole;

/** Représente un spot sur le plateau
 */
public class Spot {

    // Constante :
    public static final int COLONNE_OCCUPE = 2;
    // Attribut du Spot :
    private final Element spot;

    /** Crée un spot
     * @param ligne la ligne où il se trouve sur le plateau
     * @param colonne la colonne où il se trouve sur le plateau
     */
    public Spot(int ligne, int colonne){
        spot = new Element(ligne,colonne, Symbole.Spot);
    }

    // Getter :
    public Element getSpot() {
        return spot;
    }

    // toString :
    public String toString(){
        return Symbole.Spot.toString();
    }
}
