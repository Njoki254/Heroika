package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hero {

    private String heroName;
    private int heroAge;
    private String heroStrength;
    private String heroWeakness;
    private int squadId; //foreign key.to pick data of which squad each hero is in this is necessary for one to many relationship, foreign key

    private static ArrayList<Hero> instances = new ArrayList<>();
    private LocalDateTime heroCreatedAt; //see constructor and my method


    private int id; //to find a specific heroobject
    private boolean posted; //i’m new
//constructors are for instantiating objects
    //this.id = id;
    //constructor is for avoiding creating objects manually,
    public Hero (String heroName, int heroAge, String heroStrength, String heroWeakness) {

        this.heroName = heroName;
        this.heroAge= heroAge;
        this.heroStrength= heroStrength;
        this.heroWeakness= heroWeakness;
        this.heroCreatedAt = LocalDateTime.now();


        instances.add(this);
        this.id=instances.size(); //size of array list if 1 item then id = 1, can be replaced with database which will store the data instead
    }




    public String getHeroName() {
        return heroName;
    }

    public String getHeroStrength() {
        return heroStrength;
    }
    public String getHeroWeakness() {
        return heroWeakness;
    }
    public int getHeroAge(){ return heroAge;}
    public LocalDateTime getHeroCreatedAt() {
        return heroCreatedAt;
    }
    public void setId(int id){
        this.id=id;
    }//outside of the constructor because database is what is creating it, you make database assign id so it can be unique and you don't have to hardcoding the values,
    //if you do it yourself it won't be unique and wont be able to assign specific idea.
    public int getSquadId(){
        return squadId;
    }





    public static ArrayList<Hero> getInstances() {
        return instances;//arraylist represents data type to be returned,
    }

    public static void clear() {
        instances.clear();
    }
    //Why -1? Well, the length of an array is not the same as the index, remember?. Arrays and ArrayLists are 0-based in their indexing.
    public int getId() {
        return id;
    }
    public static Hero findHeroById(int id){
        return instances.get(id-1); //because indexing starts at zero
    }

    public void update(String heroName, int heroAge, String heroStrength, String heroWeakness) {
        this.heroName = heroName;
        this.heroAge= heroAge;
        this.heroStrength= heroStrength;
        this.heroWeakness= heroWeakness;
    }
    public boolean getHeroPosted(){ //new too
        return this.posted;
    }
    public void update(String content) {
        this.heroName = content;
    }
    public void deleteHero(){
        instances.remove(id-1); //same reason
    }


}
