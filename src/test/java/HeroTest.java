
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class HeroTest {

    @Test
    public void HeroInstantiate() {
        Hero myHero = new Hero("Nakia", 30, "agility",  "gullibility");
        assertEquals(true, myHero instanceof Hero);
    }
    @Test
    public void getHeroName_String() {
        Hero myHero = new Hero("Nakia", 30, "agility",  "gullibility");

        assertEquals("Nakia", myHero.getHeroName());
    }

    @Test
    public void getHeroStrength() {
        Hero myHero = new Hero("Nakia", 30, "agility",  "gullibility");
        String expected = "agility";
        assertEquals(expected, myHero.getHeroStrength());
    }
    @Test
    public void testGetHeroAge(){
        Hero myHero = new Hero("Nakia", 30, "agility",  "gullibility");
        int expected= 30;
        assertEquals(30, myHero.getHeroAge());

    }
    @Test
    public void testGetId(){
        Hero myHero = new Hero("Nakia", 30, "agility",  "gullibility");
        Hero.clear();
        assertEquals(2, myHero.getId());
    }

    @Test
    public void testGetHeroInstances(){
        Hero myHero = new Hero("Nakia", 30, "agility",  "gullibility");
        Hero hero2 = new Hero("Okoye", 29,"Good with Swords", "anger issues");
        assertTrue(Hero.getInstances().contains(myHero));
        assertTrue(Hero.getInstances().contains(hero2));
    }
    @Test
    public void getHeroisPosted() throws Exception {
        Hero myHero = new Hero("Nakia", 30, "agility",  "gullibility");
        assertEquals(false, myHero.getHeroPosted()); //should never start as published
    }
    @Test
    public void findReturnsCorrectHero() throws Exception {
        Hero hero= setupNewHero();
        Hero myHero = new Hero("Nakia", 30, "agility",  "gullibility");
        assertEquals(2, Hero.findHeroById(myHero.getId()).getId());
    }

    public Hero setupNewHero() {
        return new Hero("Nakia", 30, "agility", "gullibility");
    }
    @Test
    public void updateChangesHeroContent() throws Exception {
        Hero post = setupNewHero();
        String formerName = post.getHeroName();
        String formerStrength = post.getHeroStrength();
        String formerWeakness = post.getHeroWeakness();
        int formerAge = post.getHeroAge();
        int formerId = post.getId();

        post.update( "T'Challa", 30, "agility",  "gullibility");

        assertEquals(formerId, post.getId());

        assertNotEquals("Nakia", post.getHeroName());
        assertNotEquals(33, post.getHeroAge());
        assertNotEquals("runs fast", post.getHeroStrength());
        assertNotEquals("ill-tempered", post.getHeroWeakness());
    }



  /*  @Test
    public void deleteDeletesASpecificPost() throws Exception {
        Hero post = setupNewHero();
        Hero otherHero = new Hero( "Nakia", 30, "agility",  "gullibility");
        post.deleteHero();
        assertEquals(1, Hero.getInstances().size()); //one is left
        assertEquals(Hero.getInstances().get(2).getId(),2 ); //the one that was deleted has the id of 2. Why do we care?
    }*/
    @Test
    public void deleteAllHeroes() throws Exception {
        Hero hero= setupNewHero();
        Hero otherHero = setupNewHero();

        Hero.clear();
        assertEquals(0, Hero.getInstances().size());
    }

}
//testing codes that display newly created object
   /* @After
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
    }
    //test for ensuring we can instantiate an object
    @Test
    public void newCube_instantiatesCorrectly() {
        Rectangle testRectangle = new Rectangle(30, 30);
        Cube testCube = new Cube(testRectangle);
        assertEquals(true, testCube instanceof Cube);
    }
    @Test
    public void getPublished_isFalseAfterInstantiation_false() throws Exception {
        Post myPost = new Post("Day 1: Intro");
        assertEquals(false, myPost.getPublished()); //should never start as published
    }
    //Test to make sure one can update posts correctly
@Test
public void updateChangesPostContent() throws Exception {
    Post post = setupNewPost();
    String formerContent = post.getContent();
    LocalDateTime formerDate = post.getCreatedAt();
    int formerId = post.getId();

    post.update("Android: Day 40");

    assertEquals(formerId, post.getId());
    assertEquals(formerDate, post.getCreatedAt());
    assertNotEquals(formerContent, post.getContent());*/


