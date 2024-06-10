package SpotGame.structure;

import SpotGame.utils.Symbole;

import java.util.Objects;
import java.util.Scanner;

/** Représente une partie
 */
public class Partie {

    // Constante :
    public static final int NOMBRES_DE_JOUEUR = 2;

    // Attributs de la partie :
    private final Plateau plateau;
    private final Joueur[] joueurs = new Joueur[NOMBRES_DE_JOUEUR];
    private final Piece Rouge;
    private final Piece Blanche;
    private final Piece Bleue;
    private static final Spot[] spots = new Spot[]{ // Les spots présents dans toutes les parties et qui ne sont jamais modifiés (static)
            new Spot(0, Spot.COLONNE_OCCUPE),
            new Spot(1, Spot.COLONNE_OCCUPE),
            new Spot(2, Spot.COLONNE_OCCUPE)
    };
    private boolean Fin; // Boolean qui indique si la partie est terminée ou non

    /** Crée une nouvelle partie
     */
    public Partie() {
        Fin = false;
        // La position initiale des 3 pièces :
        Rouge = new Piece(0, 1, Symbole.Rouge);
        Blanche = new Piece(1, 1, Symbole.Blanche);
        Bleue = new Piece(2, 1, Symbole.Bleue);

        // On initialise le plateau, grâce aux éléments de la partie :
        plateau = new Plateau(this);

        // Les joueurs sont initialisés en dehors du constructeur, car ils doivent choisir leur pièce
    }

    // Getter et Setter :
    public Joueur getJoueur(int i) { return joueurs[i]; } // Renvoie le joueur i demandé
    public static Spot[] getSpots() { return spots; }
    public Piece getPieceRouge() { return Rouge; }
    public Piece getPieceBlanche() { return Blanche; }
    public Piece getPieceBleue() { return Bleue; }
    public Plateau getPlateau() { return plateau; }
    public void setFin(boolean Fin) { this.Fin = Fin; } // Modifie le statut de la partie
    public boolean Est_Fini() { return Fin; } // Renvoie le statut de la partie

    // Fonctions :

    /** Commence le jeu, avec une interface pour les joueurs
     */
    public void Debut_de_jeu() {
        // On annonce le debut :
        System.out.println("Debut du jeu : ");
        System.out.println("Bonjour Joueur 1 et Joueur 2 !");

        plateau.Afficher(); // On affiche le plateau pour permettre aux joueurs de le visualiser
        System.out.print("Joueur 1 choisissez la couleur de votre pièce, Tapez 'R' ou 'B' : ");

        String Couleur_choisie = Dilemme(Rouge.toString(), Bleue.toString()); // Le choix du joueur

        // Attribution des couleurs selon le choix du Joueur 1 :
        if (Objects.equals(Couleur_choisie, Rouge.toString())) {  // Si le joueur 1 prend la piece rouge
            System.out.println("Joueur 2 la couleur de votre pièce sera donc " + Bleue.SymboletoString() + " !");
            joueurs[0] = new Joueur(Rouge);
            joueurs[1] = new Joueur(Bleue);
        } else {
            System.out.println("Joueur 2 la couleur de votre pièce sera donc " + Rouge.SymboletoString() + " !");
            joueurs[0] = new Joueur(Bleue);
            joueurs[1] = new Joueur(Rouge);
        }
        System.out.println("Que la partie commence !\n");

        System.out.println("*******************************************************************************");

    }

    public void Afficher_les_scores() {
        System.out.println("\n*******************************************************************************");
        for (int id = 0; id < NOMBRES_DE_JOUEUR; ++id) { // Pour chaque joueur
            System.out.println("Joueur " + (id + 1) + " vous avez " + joueurs[id].toString() + " points"); // On affiche son nombre de points
        }
        System.out.println("*******************************************************************************\n");    }

    /**Demande à un joueur de faire un choix, évite les erreurs de saisie
     * @param DESTINATION_MAX Le nombre de destinations max possibles
     * @return Le choix du joueur
     */
    public static int Choix_destination(int DESTINATION_MAX) {
        Scanner scanner = new Scanner(System.in);
        int Choix_joueur;
        do {
            while (!scanner.hasNextInt()) { // Tant que la valeur entrée n’est pas un nombre entier
                // Message d’erreur :
                System.out.println("\nEntrée non valide, veuillez entrer un nombre entier");
                System.out.print("Veuillez entrer une destination correcte : ");
                scanner.next(); // On vide le scanner
            }
            Choix_joueur = scanner.nextInt() - 1; // -1, car utilisé dans une liste, qui commence à 0 contrairement à l’affichage qui commence à 1
            if (!(DESTINATION_MAX > Choix_joueur && Choix_joueur >= 0)) { // Vérifie si la valeur stockée est entre 1 et le nombre de destinations max
                // Message d'erreur :
                System.out.println("\nCette destination n'existe pas, recommencez");
                System.out.print("Veuillez entrer une destination correcte : ");
            }
        } while (!(DESTINATION_MAX > Choix_joueur && Choix_joueur >= 0)); // Tant que la valeur entrée ne correspond pas à une destination
        return Choix_joueur;
    }

    /**Demande aux joueurs de faire un choix parmi 2 options, évite les erreurs de saisie
     * @param Choix_1 Le premier choix
     * @param Choix_2 Le deuxième choix
     * @return Le choix du joueur tout en majuscule
     */
    public static String Dilemme(String Choix_1, String Choix_2) {
        Scanner scanner = new Scanner(System.in);
        String Choix_joueur;
        do {
            while (scanner.hasNextInt()) { // Tant que la valeur saisie est un entier naturel
                // Message d'erreur :
                System.out.print("\nEntrée non valide, veuillez entrer '" + Choix_1 + "' ou '" + Choix_2 + "': ");
                scanner.next(); // On vide le scanner
            }
            Choix_joueur = scanner.next().toUpperCase(); // On stocke la valeur saisie, on la met en majuscule
            if (!Choix_joueur.equals(Choix_1.toUpperCase()) && !Choix_joueur.equals(Choix_2.toUpperCase())) { // Vérifie que la valeur saisie fait partie des 2 options
                // Message d'erreur :
                System.out.print("\nEntrée non valide, veuillez entrer '" + Choix_1 + "' ou '" + Choix_2 + "': ");
            }
        } while (!(Choix_joueur.equals(Choix_1.toUpperCase()) || Choix_joueur.equals(Choix_2.toUpperCase())));  // Tant que la valeur saisie ne fait pas partie des 2 options
        return Choix_joueur;

    }

    /** Fonction qui annonce le gagnant
     */
    public void Le_gagnant_est() {

        if (joueurs[0].A_gagne_contre(joueurs[1])) { // Si le joueur 1 gagne contre le joueur 2
            System.out.println("Le Joueur 1 a gagné la partie avec votre piece "+joueurs[0].getPiece().SymboletoString()+"!");
            System.out.println("Joueur 2 vous gagnerez la prochaine fois ^^");
        } else { // Sinon
            System.out.println("Le Joueur 2 a gagné la partie avec votre piece "+joueurs[1].getPiece().SymboletoString()+"!");
            System.out.println("Joueur 1 vous gagnerez la prochaine fois ^^");
        }
    }

    /** Demande aux joueurs s’ils veulent recommencer
     * @return true si oui
     */
    public boolean Recommencer() {

        System.out.print("Voulez-vous recommencer ? Tapez \"Oui\" ou \"Non\": ");
        return Objects.equals(Dilemme("Oui", "Non"), "OUI"); // On renvoie leur choix
    }
}