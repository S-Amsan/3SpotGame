package SpotGame.tests;

import SpotGame.structure.Element;
import SpotGame.utils.Symbole;
import org.junit.Assert;
import org.junit.Test;

public class ElementTest {
    private final Element element = new Element(0, 0, Symbole.Vide);

    @Test
    public void test_Deplacement() {
        element.setPosition(3, 4);
        Assert.assertEquals(3, element.getLigne());
        Assert.assertEquals(4, element.getColonne());
    }



}
