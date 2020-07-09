import java.util.ArrayList;

public class Squad {

    private String squadCause;
    private String squadName;
    private int maxSize;
    private static ArrayList<Squad> instances = new ArrayList<>();

    private static int id;

    public Squad (String squadName, String squadCause){

        this.squadName = squadName;
        this.squadCause= squadCause;
        this.maxSize= 5;
        instances.add(this);
        this.id=instances.size(); //size of array list if 1 item then id = 1,
    }

    public String getSquadCause() {
        return squadCause;
    }

    public String getSquadName() {
        return squadName;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public static ArrayList<Squad> getInstances() {
        return instances;//arraylist represents data type to be returned,
    }

    public int getId() {
        return id;
    }
    public static Squad findSquadById(int id){
        return instances.get(id-1); //because indexing starts at zero
    }
    public static void deleteSquad(int id){
        instances.remove(id-1);

    }
    public void update(String squadCause, String squadName) {
        this.squadCause = squadCause;
        this.squadName = squadName;
    }
}
