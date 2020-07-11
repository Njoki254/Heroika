
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import static org.junit.Assert.*;

public class SquadTest {

    @Test
    public void SquadInstantiate() {
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", "Nakia");
        assertEquals(true, mySquad instanceof Squad);
    }
    @Test
    public void getSquadName_String() {
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", "Nakia");
        String expected = "Wakanda";
        assertEquals(expected, mySquad.getSquadName());
    }

    @Test
    public void getSquadCause() {
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", "Nakia");
        String expected = "fight off exploitation of Africa";
        assertEquals(expected, mySquad.getSquadCause());
    }
    @Test
    public void testGetSquadSize(){
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", "Nakia");
        int expected= 2;
        assertEquals(expected, mySquad.getSquadSize());

    }
    @Test
    public void testGetId(){
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", "Nakia");
        Squad.clear();
        assertEquals(3, mySquad.getId());
    }

    @Test
    public void testGetSquadInstances(){
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", "Nakia");
        Squad squad2 = new Squad("Avengers", "fight off corona virus","Okoye");
        assertTrue(Squad.getInstances().contains(mySquad));
        assertTrue(Squad.getInstances().contains(squad2));
    }
    @Test
    public void getSquadisPosted() throws Exception {
        Squad mySquad = new Squad("Name","Poverty", "Nakia");
        assertEquals(false, mySquad.getSquadPosted()); //should never start as published
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


