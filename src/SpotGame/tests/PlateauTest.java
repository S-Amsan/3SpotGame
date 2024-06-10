package SpotGame.tests;

import SpotGame.structure.*;
import SpotGame.utils.Position;
import SpotGame.utils.Symbole;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlateauTest {
    private final Partie partie = new Partie();

    @Test
    public void test_Affichage_de_destinations_a_la_meme_position() {
        // On prend le cas où la pièce bleue commence, car elle a deux destinations dans le même carré à la position (2,0).
        int Ligne_destinations_a_la_meme_position = 2;
        int Colonne_destinations_a_la_meme_position = 0;

        // On prend le début de la fonction Déplacement de la classe pièce (comme si on l'avait appelé avec la pièce bleue).
        ArrayList<Element> Carre_avec_destinations = new ArrayList<>();
        ArrayList<Position[]> Destinations_possibles;
        Destinations_possibles = partie.getPieceBleue().Destinations_possibles(partie);
        partie.getPieceBleue().Ajouter_destinations(Destinations_possibles, Carre_avec_destinations);
        Plateau Destination = new Plateau(Carre_avec_destinations);
        Destination.Ajouter_les_autres_elements_sauf(partie.getPieceBleue(), partie);
        String Carre_plateau_destination = Destination.Element_a_la_positon(Ligne_destinations_a_la_meme_position,Colonne_destinations_a_la_meme_position);

        // On regarde
        Assert.assertTrue(Carre_plateau_destination.length() > 1); // Vérifie que le String envoyé a une taille supérieure à 1, si c’est le cas ça veut dire qu'elle affiche bien deux destinations séparées d’un tiré.
    }

    @Test
    public void test_Piece_par_dessus_les_spots() {
        // Les éléments de la partie n’ont pas bougé (position par défaut).
        // On regarde que c’est bien le symbole de la pièce rouge qui s’affiche devant les spots :
        Assert.assertEquals(Symbole.Rouge.toString(),partie.getPlateau()
                .Element_a_la_positon(partie.getPieceRouge().getPosition(1).getLigne(),partie.getPieceRouge().getPosition(1).getColonne())); //On met la position du deuxième carré de la piece rouge

        Assert.assertNotEquals(Symbole.Spot.toString(),partie.getPlateau()
                .Element_a_la_positon(Partie.getSpots()[0].getSpot().getLigne(),Spot.COLONNE_OCCUPE)); // On met la position d'un spot


    }
}
