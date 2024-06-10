package SpotGame.structure;

import SpotGame.utils.Position;
import SpotGame.utils.Symbole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/** Représente le plateau de la partie
 */
public class Plateau {

    // Constantes :
    public static final int NOMBRES_DE_LIGNES = 3;
    public static final int NOMBRES_DE_COLONNES = 3;

    // Attributs du plateau :

    /** Représente chaque élément du plateau, (les carrés vides ne sont pas comptés)
     */
    ArrayList<Element> carre = new ArrayList<>();

    /** Cela permet d’afficher des valeurs aux destinations
     */
    private int Compteur_destination = 1;

    /** Crée un nouveau plateau
     * @param partie les éléments de la partie sont ajoutés au plateau vide
     */
    public Plateau(Partie partie){
        // Les pièces de la partie sont ajoutés en premier :
        Placer(partie.getPieceRouge());
        Placer(partie.getPieceBlanche());
        Placer(partie.getPieceBleue());
        // Les spots après ce qui permet aux pièces de s’afficher sur les spots
        Placer_spots();

    }

    /** Crée un nouveau plateau
     * @param destinations les destinations données sont ajoutées au plateau vide
     */
    public Plateau(ArrayList<Element> destinations){
        carre.addAll(destinations);
    }

    // Placer les éléments dans le plateau :
    private void Placer_spots() {
        for (Spot spot : Partie.getSpots())
            carre.add(spot.getSpot());
    }

    private void Placer(Piece piece) {
        carre.addAll(Arrays.asList(piece.getCarre()));
    }

    // Affichage du plateau

    /** Affiche le plateau de neuf carrés avec ses éléments
     */
    public void Afficher() {
        String toString = this.toString();
        System.out.println(toString);
    }

    // Création du quadrillage en étoiles (*)

    /**Création du plateau en étoiles (*) avec les éléments du plateau
     * @return Le plateau avec ses éléments
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("* * * * * * * * * * * * *\n"); // On ajoute le dessus
        for (int ligne = 0; ligne < NOMBRES_DE_LIGNES; ligne++) {
            sb.append("*       *       *       *\n"); // On ajoute les étoiles entre le dessus de la ligne (dessus des 3 carrés) et les éléments
            sb.append("*");
            for (int colonne = 0; colonne < NOMBRES_DE_COLONNES; colonne++) {
                    sb.append("   ");
                    sb.append(Element_a_la_positon(ligne,colonne)); // On ajoute tous les élements de la ligne
                    sb.append("   *");
            }
            // Ajoute à chaque fois le bas de la ligne
            sb.append("\n");
            sb.append("*       *       *       *\n");
            sb.append("* * * * * * * * * * * * *\n");
        }
        return sb.toString();
    }
    /** Affiche deux plateaux côte à côte, le premier avec ses éléments et le deuxième avec les destinations
     * @param Destination le deuxième plateau à afficher, il doit contenir les destinations
     */
    public void Afficher_destination(Plateau Destination) {
        String toString = this.toString(Destination);
        System.out.println(toString);
    }

    /**Création des deux plateaux en étoiles (*) avec les éléments du plateau étant que premier tableau et le deuxième avec les destinations
     * @return Les deux plateaux côte à côte crées
     */
    private String toString(Plateau Destination) { // Cette fonction reprend le fonctionnement de toString
        StringBuilder sb = new StringBuilder();
        sb.append("Plateau :                          Destinations possibles : \n"); // Affiche aux joueurs à quoi correspond chaque plateau
        sb.append("* * * * * * * * * * * * *          * * * * * * * * * * * * *\n"); // Le dessus des plateaux (un espace de 10 entre les plateaux)
        for (int ligne = 0; ligne < NOMBRES_DE_LIGNES; ligne++) {
            sb.append("*       *       *       *          *       *       *       *\n");
            sb.append("*");
            // On ajoute d’abord tous les élements de la ligne du plateau de la partie :
            for (int colonne = 0; colonne < NOMBRES_DE_COLONNES; colonne++) {
                sb.append("   ");
                sb.append(Element_a_la_positon(ligne,colonne));
                sb.append("   *");
            }
            sb.append("          *");
            // Puis ajoute tous les élements de la ligne du plateau avec les destinations :
            for (int colonne = 0; colonne < NOMBRES_DE_COLONNES; colonne++) {
                sb.append(" ");
                String Carre_plateau_destination = Destination.Element_a_la_positon(ligne,colonne); // L'intérieur du carré
                if (Carre_plateau_destination.length() > 1){
                    sb.append(Carre_plateau_destination); // Pas d'espaces car l'intérieur du carré contient deux destinations
                }
                else {
                    sb.append("  "); // Espaces qui centrent la destination dans le carré
                    sb.append(Carre_plateau_destination);
                    sb.append("  ");
                }
                sb.append(" *");
            }
            // Ajoute à chaque fois le bas de la ligne pour chaque plateau
            sb.append("\n");
            sb.append("*       *       *       *          *       *       *       *\n");
            sb.append("* * * * * * * * * * * * *          * * * * * * * * * * * * *\n");
        }
        return sb.toString();
    }

    /** Regarde s'il y a deux destinations à la même position donnée
     * @return true si il y en a deux
     */
    private boolean A_la_meme_position(int ligne, int colonne) {
        int nb_destination_trouve = 0; // Compteur
        for (Element element : carre)
            // S’il y a un élément à la position donnée, et qu’il s’agit d’une destination
            if (element.getPosition().A_la_meme_position_que(new Position(ligne,colonne)) && element.getSymbole() == Symbole.Destination){
                ++nb_destination_trouve; // On augmente le compteur
                if (nb_destination_trouve == 2) return true; // Quand il y'a deux destinations
            }
            else if (nb_destination_trouve == 1) // S'il n'y a qu'une destination (les destinations avec la même position sont placées à côté dans l'ArrayList carré)
                return false ; // Il y'a une destination
        return false; // Il n'y a pas de destination
    }

    /** La fonction transforme en String l’élément à la position donnée, élément ou destination(s) (ex : 1-2).
     * @return le string qui sera à l’intérieur du carré à la position donnée, un espace est renvoyé si le carré est vide
     */
    public String Element_a_la_positon(int ligne, int colonne) {
        for (Element element : carre)
            if (element.getPosition().A_la_meme_position_que(new Position(ligne,colonne))) { // S’il y a un élément à la position donnée
                if (element.getSymbole() == Symbole.Destination) { // On regarde si c’est une destination
                    if (A_la_meme_position(ligne,colonne))// S’il y en a deux
                        return Compteur_destination++ +" - "+ Compteur_destination++; // On les renvoie côte à côte séparées d’un tiré (-)
                    else return Integer.toString(Compteur_destination++); // Il y'a qu'une destination à la même position
                }
                else { // Ce n’est pas une destination
                    return element.toString();
                }
            }
        return Symbole.Vide.toString(); // Il n'y a pas d'éléments à la position donnée
    }

    /** Permets d'afficher aux joueurs les destinations d'une pièce sans la pièce dans le plateau
     * @param Piece_a_exclure la pièce à ne pas afficher
     * @param partie celle en cours
     */
    public void Ajouter_les_autres_elements_sauf(Piece Piece_a_exclure,Partie partie) {
        Piece[] pieces = {partie.getPieceRouge(),partie.getPieceBlanche(),partie.getPieceBleue()};

        for (Piece piece : pieces){
            if (!Objects.equals(piece.toString(), Piece_a_exclure.toString()))
                Placer(piece);
        }
        Placer_spots();
    }
}
