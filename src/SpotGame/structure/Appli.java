package SpotGame.structure;

public class Appli {

    public static void main(String[] args) {

        // Message de présentation pour les deux joueurs :
        System.out.println("\n*******************************************************************************");
        System.out.println("3 SPOT GAME");
        System.out.println("Projet codé en JAVA par : ");
        System.out.println("Amsan SUTHARSAN (109) et Rayan MERI (103)");
        System.out.println("Règles du jeu : http://jeuxstrategieter.free.fr/Jeu_des_3_croix_complet.php");
        System.out.println("*******************************************************************************\n");

        // Création d’une nouvelle partie
        Partie partie = new Partie();
        Piece Piece_blanche = partie.getPieceBlanche(); // Variable qui stocke la piece blanche

        // On commence le jeu
        partie.Debut_de_jeu();

        while (!partie.Est_Fini()) { // Tant que la partie n’est pas terminée
            for (int id = 0; id < Partie.NOMBRES_DE_JOUEUR; ++id) { // Pour chaque joueur
                Joueur joueur = partie.getJoueur(id); // Variable qui stocke le joueur qui joue
                Piece Piece_joueur = joueur.getPiece(); // Stocke sa pièce

                // Début du tour :
                System.out.println("Tour du Joueur " + (id + 1) + " : "); // On indique quel joueur doit jouer


                // Déplacement des deux pièces, grâce à la fonction Déplacement
                Piece_joueur.Deplacement(partie); // Déplacement de la pièce du joueur
                Piece_blanche.Deplacement(partie); // Déplacement de la pièce blanche


                // On augmente les points :
                joueur.A_sa_piece_sur_un_spot();


                // On affiche le score des deux joueurs :
                partie.Afficher_les_scores();

                // Vérifie à la fin du tour si le joueur a rempli la condition de victoire
                partie.setFin(joueur.A_rempli_la_condition_de_victoire()); // Actualise la variable 'Fin' de la partie

                // Si la partie est terminée :
                if (partie.Est_Fini()) {
                    System.out.println("Le joueur "+ (id + 1) + " a atteint 12 point !");
                    System.out.println("Plateau :");
                    partie.getPlateau().Afficher();
                    partie.Le_gagnant_est(); // On affiche le gagnant
                    if (partie.Recommencer()){ // On vérifie si les joueurs veulent rejouer
                        partie = new Partie(); // On réinitialise la partie
                        partie.Debut_de_jeu(); // On commence le jeu
                    }
                    break; // Permet de sortir de la boucle
                }
            }
        }
        System.out.print("Merci d'avoir joué !"); // Message de remerciement, qui s'affiche lorsque la partie est terminée
    }
}