import java.util.ArrayList;

public class Squad {

    private String content;
    private static ArrayList<Squad> instances = new ArrayList<>();
    private boolean published; //i’m new
    private int id;

    public Squad (String content){
        this.content = content;
        this.published = false; //also new
        instances.add(this);
        this.id=instances.size();
    }

    public String getContent() {
        return content;
    }

    public static ArrayList<Squad> getAll(){
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
    public static Squad findById(int id){
        return instances.get(id-1); //why minus 1? See if you can figure it out.
    }
    public void deleteSquad(){
        instances.remove(id-1); //same reason
    }


    public void update(String content) {
        this.content = content;
    }
}
