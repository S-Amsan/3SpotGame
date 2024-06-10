package SpotGame.tests;

import SpotGame.structure.Joueur;
import SpotGame.structure.Partie;
import SpotGame.structure.Spot;
import SpotGame.utils.Position;
import org.junit.Assert;
import org.junit.Test;

public class JoueurTest {
    private final Partie partie = new Partie();
    private final Joueur joueur1Test = new Joueur(partie.getPieceRouge());
    private final Joueur joueur2Test = new Joueur(partie.getPieceBleue());

    @Test
    public void test_Condition_de_victoire(){

        // Les deux manières de gagner du joueur 1
        joueur1Test.setScore(5);
        joueur2Test.setScore(12);
        Assert.assertTrue(joueur1Test.A_gagne_contre(joueur2Test)); // Il a moins de six points et l’adversaire a douze points
        joueur1Test.setScore(12);
        joueur2Test.setScore(6);
        Assert.assertTrue(joueur1Test.A_gagne_contre(joueur2Test)); // Il a moins de douze points et l’adversaire a six points
    }

    @Test
    public void test_Gagne_des_points(){

        Assert.assertEquals(Joueur.SCORE_INITIAL, joueur1Test.getScore()); // Le joueur n’a pas de point au debut
        joueur1Test.getPiece().Deplacer_vers(new Position[] { // On déplace la piece du joueur sur deux spots
                new Position(Partie.getSpots()[0].getSpot().getLigne(), Spot.COLONNE_OCCUPE),
                new Position(Partie.getSpots()[1].getSpot().getLigne(),Spot.COLONNE_OCCUPE)
        });
        joueur1Test.A_sa_piece_sur_un_spot(); // ajoute des points
        Assert.assertEquals(2, joueur1Test.getScore()); // Le joueur doit avoir deux points


    }




}
