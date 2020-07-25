package models;

import java.security.Timestamp;
import java.util.ArrayList;

public class Squad {

    private String squadCause;
    private String squadName;

    private int squadSize;

    private static ArrayList<Squad> instances = new ArrayList<>();
    private Timestamp squadCreatedAt; //see constructor and my method changed from local date time


    private int id;
    private boolean posted; //i’m new

    public Squad (String squadName, String squadCause, int squadSize){

        this.squadName = squadName;
        this.squadCause= squadCause;
        this.squadSize= 5;


        instances.add(this);
        this.id=instances.size(); //size of array list if 1 item then id = 1,
    }
    public Timestamp getSquadCreatedAt() {
        return squadCreatedAt;
    }




    public String getSquadCause() {
        return squadCause;
    }

    public String getSquadName() {
        return squadName;
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
    public void setId(int id){this.id=id; }
    public static Squad findSquadById(int id){
        return instances.get(id-1); //because indexing starts at zero
    }


    public boolean getSquadPosted(){ //new too
        return this.posted;
    }

    public void deleteSquad(){
        instances.remove(id-1); //same reason
    }


}
