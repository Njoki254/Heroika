
import java.util.ArrayList;

public class Hero {

    private String hero1;
    private String hero2;
    private String hero3;
    private String hero4;

    private static ArrayList<Hero> instances = new ArrayList<>();

    private static int id;

    public Hero (String hero1, String hero2, String hero3, String hero4){

        this.hero1 = hero1;
        this.hero2= hero3;
        this.hero3= hero3;
        this.hero3= hero3;
        instances.add(this);
        this.id=instances.size(); //size of array list if 1 item then id = 1,
    }


    public String getHero1() {
        return hero1;
    }

    public String getHero2() {
        return hero2;
    }

    public String getHero3(){ return hero3;}
    public String getHero4(){ return hero4;}

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
        this.hero1 = hero1;
        this.hero2 = hero2;
        this.hero3 = hero3;
    }
    public static void clear() {
        instances.clear();
    }

}