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
    @Test
    public void testGetAllInstances(){
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", 2);
        Squad squad2 = new Squad("Avengers", "fight off corona virus",5);
        assertTrue(Squad.getInstances().contains(mySquad));
        assertTrue(Squad.getInstances().contains(squad2));
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