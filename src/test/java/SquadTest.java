
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import static org.junit.Assert.*;

public class SquadTest {

    @Test
    public void SquadInstantiate() {
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", 3);
        assertEquals(true, mySquad instanceof Squad);
    }
    @Test
    public void getSquadName_String() {
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", 4);
        String expected = "Wakanda";
        assertEquals(expected, mySquad.getSquadName());
    }

    @Test
    public void getSquadCause() {
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", 2);
        String expected = "fight off exploitation of Africa";
        assertEquals(expected, mySquad.getSquadCause());
    }
    @Test
    public void testGetSquadSize(){
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", 2);
        int expected= 2;
        assertEquals(expected, mySquad.getSquadSize());

    }
    @Test
    public void testGetId(){
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", 2);
        Squad.clear();
        assertEquals(5, mySquad.getId());
    }

    @Test
    public void testGetAllInstances(){
        Squad mySquad = new Squad("Wakanda", "fight off exploitation of Africa", 2);
        Squad squad2 = new Squad("Avengers", "fight off corona virus",5);
        assertTrue(Squad.getInstances().contains(mySquad));
        assertTrue(Squad.getInstances().contains(squad2));
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


