import java.util.ArrayList;

public class Hero {

    private String heroName;
    private int heroAge;
    private String heroStrength;
    private String heroWeakness;

    private static ArrayList<Hero> instances = new ArrayList<>();


    private static int id;
    private boolean posted; //i’m new

    public Hero (String heroName, int heroAge, String heroStrength, String heroWeakness) {

        this.heroName = heroName;
        this.heroAge= heroAge;
        this.heroStrength= heroStrength;
        this.heroWeakness= heroWeakness;

        instances.add(this);
        this.id=instances.size(); //size of array list if 1 item then id = 1,
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
