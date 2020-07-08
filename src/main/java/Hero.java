
import java.util.ArrayList;

public class Hero {
    private String content;
    private static ArrayList<Hero> instances = new ArrayList<>();
    private boolean published; //i’m new
    private int id;

    public Hero (String content){
        this.content = content;
        this.published = false; //also new
        instances.add(this);
        this.id = instances.size();
    }

    public String getContent() {
        return content;
    }

    public static ArrayList<Hero> getAll(){
        return instances;
    }

    public static void clearAllSquads(){
        instances.clear(); //clear as a method is part of the ArrayList class.
    }

    public boolean getPublished(){ //new too
        return this.published;
    }
    public int getId() {
        return id;
    }
    public static Hero findById(int id){
        return instances.get(id-1); //why minus 1? See if you can figure it out.
    }
}

