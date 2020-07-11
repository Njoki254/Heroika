import java.util.ArrayList;

public class Squad {

    private String squadCause;
    private String squadName;

    private int squadSize;
    private String members;
    private static ArrayList<Squad> instances = new ArrayList<>();


    private static int id;
    private boolean posted; //i’m new

    public Squad (String squadName, String squadCause, String members){

        this.squadName = squadName;
        this.squadCause= squadCause;
        this.members= members;
        this.squadSize= 5;

        instances.add(this);
        this.id=instances.size(); //size of array list if 1 item then id = 1,
    }


    public String getSquadCause() {
        return squadCause;
    }

    public String getSquadName() {
        return squadName;
    }
    public String getMembers() {
        return members;
    }



    public int getSquadSize() {return squadSize;}

    public static ArrayList<Squad> getInstances() {
        return instances;//arraylist represents data type to be returned,
    }

    public static void clear() {
        instances.clear();
    }
//Why -1? Well, the length of an array is not the same as the index, remember?. Arrays and ArrayLists are 0-based in their indexing.
    public int getId() {
        return id;
    }
    public static Squad findSquadById(int id){
        return instances.get(id-1); //because indexing starts at zero
    }
    public static void deleteSquad(int id){
        instances.remove(id-1);

    }
    public void update(String squadCause, String squadName, String members) {
        this.squadCause = squadCause;
        this.squadName = squadName;
        this.members= members;
    }
    public boolean getSquadPosted(){ //new too
        return this.posted;
    }

}
