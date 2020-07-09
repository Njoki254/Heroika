import org.junit.*;
import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void Hero_instantiatesCorrectly_true() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful");
        assertEquals(true, myHero instanceof Hero);
    }
    @Test
    public void getName_forHeroesName_String() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful");
        String expected = "Nakia";
        assertEquals(expected, myHero.getHeroName());
    }

    @Test
    public void getWeakness() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful");
        String expected = "Too merciful";
        assertEquals(expected, myHero.getHeroWeakness());
    }
    @Test
    public void getStrength() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful");
        String expected = "Can run fast";
        assertEquals(expected, myHero.getHeroStrength());
    }


}