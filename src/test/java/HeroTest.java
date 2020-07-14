import org.junit.*;
import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void Hero_instantiatesCorrectly_true() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful","Okoye");
        assertEquals(true, myHero instanceof Hero);
    }
    @Test
    public void getHero1() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful","Okoye");
        String expected = "Nakia";
        assertEquals(expected, myHero.getHero1());
    }

    @Test
    public void getHero2() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful","Okoye");
        String expected = "Too merciful";
        assertEquals(expected, myHero.getHero2());
    }
    @Test
    public void getHero3() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful","Okoye");
        String expected = "Too merciful";
        assertEquals(expected, myHero.getHero2());
    }
    @Test
    public void getHero4() {
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful","Okoye");
        String expected = "Too merciful";
        assertEquals(expected, myHero.getHero2());
    }
    @Test
    public void testGetAllInstances(){
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful","Okoye");
        Hero hero2 = new Hero("Nakia", "Can run fast", "Too merciful","Okoye");
        assertTrue(Hero.getInstances().contains(myHero));
        assertTrue(Hero.getInstances().contains(hero2));
    }

    @Test
    public void testGetId(){
        Hero myHero = new Hero("Nakia", "Can run fast", "Too merciful","Okoye");
        Squad.clear();
        assertEquals(3, myHero.getId());
    }
}
/*@After
public void tearDown() {
  Post.clearAllPosts(); //clear out all the posts before each test.
}

@Test
public void AllPostsAreCorrectlyReturned_true() {
   Post post = new Post("Day 1: Intro");
   Post otherPost = new Post ("How to pair successfully");
   assertEquals(2, Post.getAll().size());
}

@Test
public void AllPostsContainsAllPosts_true() {
   Post post = new Post("Day 1: Intro");
   Post otherPost = new Post ("How to pair successfully");
   assertTrue(Post.getAll().contains(post));
   assertTrue(Post.getAll().contains(otherPost));
}*/