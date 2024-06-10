package SpotGame.tests;


import SpotGame.structure.Partie;
import SpotGame.structure.Spot;
import SpotGame.utils.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PieceTest {
    private final Partie partie = new Partie();

    @Test
    public void test_Obstacle(){
        // La piece rouge se rencontre elle-même
        Assert.assertTrue(partie.getPieceRouge()
                .Ne_rencontre_pas_de_obstacle(partie.getPieceRouge().getPosition(0).getLigne(),partie.getPieceRouge().getPosition(0).getColonne(),partie));
        // Elle ne rencontre rien
        Assert.assertTrue(partie.getPieceRouge()
                .Ne_rencontre_pas_de_obstacle(0,0,partie)); //Nouvelle partie donc il n'y a donc rien à cette position
        // Elle rencontre un spot
        Assert.assertTrue(partie.getPieceRouge()
                .Ne_rencontre_pas_de_obstacle(Partie.getSpots()[0].getSpot().getLigne(), Spot.COLONNE_OCCUPE,partie));
        // Elle rencontre la pièce bleue
        Assert.assertFalse(partie.getPieceRouge()
                .Ne_rencontre_pas_de_obstacle(partie.getPieceBleue().getPosition(0).getLigne(),partie.getPieceBleue().getPosition(0).getColonne(),partie));
        // Elle rencontre la pièce blanche
        Assert.assertFalse(partie.getPieceRouge()
                .Ne_rencontre_pas_de_obstacle(partie.getPieceBlanche().getPosition(0).getLigne(),partie.getPieceBlanche().getPosition(0).getColonne(),partie));
    }

    @Test
    public void test_Nouvelle_position() {
        // Les deux carrés de la piece rouge n’ont pas bougé (on reprend sa position actuelle).
        Assert.assertFalse(partie.getPieceRouge()
                .Est_au_moins_dans_une_nouvelle_case(new Position[]{
                        new Position(partie.getPieceRouge().getPosition(0).getLigne(),partie.getPieceRouge().getPosition(0).getColonne()),
                        new Position(partie.getPieceRouge().getPosition(1).getLigne(),partie.getPieceRouge().getPosition(1).getColonne())
                }));
        // Seul le premier carré est dans une nouvelle position (on décale la piece d’un carré à gauche (colonne-1)).
        Assert.assertTrue(partie.getPieceRouge()
                .Est_au_moins_dans_une_nouvelle_case(new Position[]{
                        new Position(partie.getPieceRouge().getPosition(0).getLigne(),partie.getPieceRouge().getPosition(0).getColonne()-1),
                        new Position(partie.getPieceRouge().getPosition(1).getLigne(),partie.getPieceRouge().getPosition(1).getColonne()-1)
                }));
        // Seul le deuxième carré est dans une nouvelle position (on pivote la piece verticalement de sorte que le deuxième carré se trouve en dessous du premier).
        Assert.assertTrue(partie.getPieceRouge()
                .Est_au_moins_dans_une_nouvelle_case(new Position[]{
                        new Position(partie.getPieceRouge().getPosition(0).getLigne(),partie.getPieceRouge().getPosition(0).getColonne()-1),
                        new Position(partie.getPieceRouge().getPosition(0).getLigne()-1,partie.getPieceRouge().getPosition(0).getColonne())
                }));
        // Les deux carrés de la piece rouge ont bougé (on la déplace sur la piece blanche).
        Assert.assertTrue(partie.getPieceRouge()
                .Est_au_moins_dans_une_nouvelle_case(new Position[]{
                        new Position(partie.getPieceBlanche().getPosition(0).getLigne(),partie.getPieceBlanche().getPosition(0).getColonne()-1),
                        new Position(partie.getPieceBlanche().getPosition(1).getLigne(),partie.getPieceBlanche().getPosition(1).getColonne()-1)
                }));
    }
    @Test
    public void test_Placement_verticale_horizontale() {
        // Les éléments de la partie n’ont pas bougé, on va donc vérifier les positions par défaut de la pièce rouge et bleue
        // RAPPEL : aucune des fonctions appelée ne déplace les pièces.
        int nombres_de_destinations_attendues = 3;

        // La pièce rouge à trois destinations si c’est la première de la partie à bouger.
        Assert.assertEquals(3,partie.getPieceRouge()
                        .Destinations_possibles(partie).size()
                );
        // La pièce bleu à trois destinations si c’est la première de la partie à bouger.
        Assert.assertEquals(3,partie.getPieceBleue()
                .Destinations_possibles(partie).size()
        );

    }

    @Test
    public void test_Position_avec_deux_destination() {
        // Les éléments de la partie n’ont pas bougé
        // La piece bleu à trois destinations si c’est la première de la partie à bouger,
        // dont deux a la meme position en bas à gauche, nous allons vérifier ce cas.
        ArrayList <Position[]> destinationsPossibles = partie.getPieceBleue().Destinations_possibles(partie); // Les destinationsPossibles des deux carrés de la pièce, on teste le premier carré [0] :
        Assert.assertTrue(destinationsPossibles.get(1)[0].A_la_meme_position_que(destinationsPossibles.get(2)[0])); // La deuxième destination et la troisième doivent être à la même position

    }




}
