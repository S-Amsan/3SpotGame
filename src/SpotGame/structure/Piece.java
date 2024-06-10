package SpotGame.structure;

import SpotGame.utils.Position;
import SpotGame.utils.Symbole;

import java.util.ArrayList;
import java.util.Objects;

/** Représente une piece sur le plateau
 */
public class Piece {

    // Constantes :
    private static final int PREMIER_CARRE = 0;
    private static final int DEUXIEME_CARRE = 1;
    private static final int NOMBRES_DE_CARRES_DE_LA_PIECE = 2;
    Element[] carre = new Element[NOMBRES_DE_CARRES_DE_LA_PIECE]; // La pièce est constituée de deux carrés (éléments).

    /** Crée une piece
     * @param ligne la ligne avec laquelle il se trouve le premier carré sur le plateau
     * @param colonne la colonne avec laquelle il se trouve le premier carré sur le plateau
     * @param symbole le symbole qui le représentera sur le plateau
     */
    public Piece(int ligne, int colonne, Symbole symbole) {
        carre[PREMIER_CARRE] = new Element(ligne, colonne, symbole) ;
        carre[DEUXIEME_CARRE] = new Element(ligne, colonne + 1, symbole) ; // Le deuxième carré se situe à droite du premier (colonne de droite)
    }

    // ToString :
    public String toString() { return carre[PREMIER_CARRE].toString();} // Les deux carrés ont le même symbole

    /**
     * @return le nom du symbole
     */
    public String SymboletoString() { return carre[PREMIER_CARRE].getSymbole().getName().toLowerCase(); }

    // Getter et Setter:

    /**
     * @param i L’indice du carré
     * @return la position du carré
     */
    public Position getPosition(int i) { return carre[i].getPosition(); }
    public Element[] getCarre() { return carre; }

    public void Deplacer_vers(Position[] destinationChoisie) {
        for (int i = 0; i < NOMBRES_DE_CARRES_DE_LA_PIECE; ++i)
            this.carre[i].setPosition(destinationChoisie[i].getLigne(), destinationChoisie[i].getColonne());
    }

    // Fonctions :

    /** S’occupe du déplacement de la pièce
     * @param partie partie dans laquelle la pièce doit se déplacer
     */
    public void Deplacement(Partie partie) {
        // Message au joueur, affiche "la " ou "votre" si on parle de la piece du joueur ou non :
        System.out.println("Déplacement de " + (Objects.equals(toString(), Symbole.Blanche.toString()) ? "la" : "votre") + " pièce " + this.SymboletoString() + " : \n");

        // On crée les variables :
        ArrayList<Element> Carre_avec_destinations = new ArrayList<>(); // Stock l’emplacement des destinations en tant qu’éléments du jeu
        ArrayList<Position[]> Destinations_possibles; // Stock les destinations possibles des deux carrés de la pièce

        // On les remplie :
        Destinations_possibles = Destinations_possibles(partie);
        Ajouter_destinations(Destinations_possibles, Carre_avec_destinations);

        Plateau plateau = new Plateau(Carre_avec_destinations); // On ajoute les destinations en premier ce qui permet aux destinations de s’afficher devant les spots
        plateau.Ajouter_les_autres_elements_sauf(this, partie);

        // Affichage au joueur :
        partie.getPlateau().Afficher_destination(plateau);

        // On demande au joueur de choisir une destination :
        System.out.print("Veuillez choisir la destination de la pièce " + SymboletoString() + " parmi celles proposées : ");
        int Choix_joueur = Partie.Choix_destination(Destinations_possibles.size()); // Fonction qui scanne, évite les erreurs de saisie

        // On déplace la pièce :
        this.Deplacer_vers(Destinations_possibles.get(Choix_joueur));
    }

    /** Ajoute seulement les positions du premier carré en tant qu'élément, pour l’affichage
     * @param destinationsPossibles les destinations possibles de la pièce
     * @param carreAvecDestinations Liste où on ajoute les destinations
     */
    public void Ajouter_destinations(ArrayList<Position[]> destinationsPossibles, ArrayList<Element> carreAvecDestinations) {
        for (Position[] destinationsPossible : destinationsPossibles) {
            int ligne = destinationsPossible[0].getLigne();
            int colonne = destinationsPossible[0].getColonne();
            carreAvecDestinations.add(new Element(ligne, colonne, Symbole.Destination));
        }
    }

    /** Crée une liste avec toutes les destinations possibles de la pièce
     * @param partie partie dans laquelle la pièce doit se déplacer
     * @return La liste avec les destinations possibles de la pièce dans le plateau
     */
    public ArrayList<Position[]> Destinations_possibles(Partie partie) {
        ArrayList<Position[]> destination = new ArrayList<>();
        // La position du premier carré :
        for (int ligne = 0; ligne < Plateau.NOMBRES_DE_LIGNES; ++ligne)
            for (int colonne = 0; colonne < Plateau.NOMBRES_DE_COLONNES; ++colonne) {
                if (this.Ne_rencontre_pas_de_obstacle(ligne, colonne, partie)) {
                    // On regarde d'abord si il peut se placer verticalement :
                    if (this.Peut_se_placer_verticalement(ligne, colonne, partie)) {
                        Position[] Nouvelle_destination = {
                                new Position(ligne, colonne), // La position du premier carré
                                new Position(ligne - 1, colonne)}; // La position du deuxième carré, ligne - 1, car il se place en haut du premier carré
                        if (this.Est_au_moins_dans_une_nouvelle_case(Nouvelle_destination)) {
                            destination.add(Nouvelle_destination);
                        }
                    }
                    // Puis horizontalement, car cela permet d’afficher la destination verticale puis horizontale ( ex : 1-2 )
                    if (this.Peut_se_placer_horizontalement(ligne, colonne, partie)) {
                        Position[] Nouvelle_destination = {
                                new Position(ligne, colonne), // La position du premier carré
                                new Position(ligne, colonne + 1)}; // La position du deuxième carré, colonne + 1, car il se place à droite du premier carré
                        if (this.Est_au_moins_dans_une_nouvelle_case(Nouvelle_destination)) {
                            destination.add(Nouvelle_destination);
                        }
                    }
                }
            }
        return destination;
    }

    /** Regarde à partir de la position du premier carré si le deuxième carré peut se placer verticalement
     * @param ligne La ligne du premier carré de la pièce
     * @param colonne La colonne du premier carré de la pièce
     * @param partie partie dans laquelle la pièce doit se déplacer
     * @return true si le deuxième carré de la pièce peut se placer en haut du premier carré
     */
    private boolean Peut_se_placer_verticalement(int ligne, int colonne, Partie partie) {
        // Le deuxième carré doit être dans le plateau et pas sur un obstacle
        return this.Ne_rencontre_pas_de_obstacle((ligne - 1), colonne, partie) && (ligne - 1) >= 0; // Ligne - 1 = carré en haut
    }

    /** Regarde à partir de la position du premier carré si le deuxième carré peut se placer horizontalement
     * @param ligne La ligne du premier carré de la pièce
     * @param colonne La colonne du premier carré de la pièce
     * @param partie partie dans laquelle la pièce doit se déplacer
     * @return true si le deuxième carré de la piece peut se placer à droite du premier carré
     */
    private boolean Peut_se_placer_horizontalement(int ligne, int colonne, Partie partie) {
        // Le deuxième carré doit être dans le plateau et pas sur un obstacle
        return this.Ne_rencontre_pas_de_obstacle(ligne, (colonne + 1), partie) && (colonne + 1) < Plateau.NOMBRES_DE_COLONNES;// Colonne + 1 = carré de droite
    }

    /** Regarde si la position donnée ne correspond pas à la position actuelle de la pièce
     * @param Nouvelle_destination position à comparer
     * @return true s’il a au moins un carré à une position différente
     */
    public boolean Est_au_moins_dans_une_nouvelle_case(Position[] Nouvelle_destination) {
        for (int i = 0; i < NOMBRES_DE_CARRES_DE_LA_PIECE; ++i) {
            if (!this.getPosition(i).A_la_meme_position_que(Nouvelle_destination[i]))
                return true;
        }
        return false;
    }

    /** Regarde à la position donnée s'il n’y à pas déjà d'autres pièces que lui-même
     * @param partie partie, on regarde les autres éléments
     * @return true si à la position donnée, il n’y à rien, un spot ou la pièce elle-même
     */
    public boolean Ne_rencontre_pas_de_obstacle(int ligne, int colonne, Partie partie) {
        String Element_rencontrer = partie.getPlateau().Element_a_la_positon(ligne, colonne); //On stocke l’élément rencontré
        return Objects.equals(Element_rencontrer, Symbole.Vide.toString()) || Objects.equals(Element_rencontrer, this.toString()) || Objects.equals(Element_rencontrer, Symbole.Spot.toString());
    }
}
