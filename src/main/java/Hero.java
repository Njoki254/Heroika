import java.util.ArrayList;

public class Hero {

    private String heroName;
    private String heroStrength;
    private String heroWeakness;

    private static ArrayList<Hero> instances = new ArrayList<>();

    private static int id;

    public Hero (String heroName, String heroStrength, String heroWeakness){

        this.heroName = heroName;
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

    public String getHeroWeakness(){ return heroWeakness;}

    public static ArrayList<Hero> getInstances() {
        return instances;//arraylist represents data type to be returned,
    }


    public int getId() {
        return id;
    }
    public static Hero findHeroById(int id){
        return instances.get(id-1); //because indexing starts at zero
    }
    public static void deleteHero(int id){
        instances.remove(id-1);

    }
    public void updateHero(String heroName, String heroStrength, String heroWeakness) {
        this.heroName = heroName;
        this.heroStrength = heroStrength;
        this.heroWeakness = heroWeakness;
    }
}
