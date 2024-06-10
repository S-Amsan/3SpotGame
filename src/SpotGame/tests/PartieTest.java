package SpotGame.tests;

import SpotGame.structure.Partie;
import SpotGame.utils.Symbole;
import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class PartieTest {
    private final Partie partie = new Partie();

    @Test
    public void test_Creation_de_joueur(){
        // Choisie la couleur rouge de la piece à la place du joueur
        String couleur = "R\n";
        InputStream in = new ByteArrayInputStream(couleur.getBytes());
        System.setIn(in);

        // On lance le debut du jeu qui demande la couleur de la piece au joueur 1
        partie.Debut_de_jeu();
        Assert.assertEquals(Symbole.Rouge.toString(),partie.getJoueur(0).getPiece().toString()); // Le joueur 1 doit avoir la piece rouge
        Assert.assertEquals(Symbole.Bleue.toString(),partie.getJoueur(1).getPiece().toString()); // Le joueur 2 doit avoir la piece bleue
        System.setIn(System.in);
    }

    @Test
    public void test_Recommencer() {
        // Saisie Oui à la place des joueurs
        String choix = "Oui\n";
        InputStream in = new ByteArrayInputStream(choix.getBytes());
        System.setIn(in);

        Assert.assertTrue(partie.Recommencer()); // On a saisie "Oui"

        System.setIn(System.in);
    }
}
