package DAO;

import models.Hero;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import org.sql2o.Connection;
import java.util.List;

public class Sql2oHero implements HeroDao {
    private final Sql2o sql2o; //create property of the class to be instantiating, its what connects to the database/ this is the argument used in App.java

public Sql2oHero (Sql2o sql2o) {
            this.sql2o = sql2o;
        }
    @Override
    public void add(Hero hero) {
        String sql = "INSERT into heroes (heroName, heroAge, heroStrength, heroWeakness, squadId) VALUES (:heroName, :heroAge, :heroStrength, :heroWeakness, :squadId);";
        try (Connection con = sql2o.open()){
            int id =(int) con.createQuery(sql, true)//id will be integer form it is being casted into an integer, true mean return generated key
                    .bind(hero)//passing in object hero to avoid typing .addparameter for each property
                    .executeUpdate()
                    .getKey();//  this will get primary key and assign to the ID
                    hero.setId( id);// database assigning id

        }catch (Sql2oException ex) {
            System.out.println("there was a problem in adding the endangered animal");
        }
    }

    @Override
    public Hero findById(int id) {
        String sql = "SELECT * FROM heroes WHERE id = :id;";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
            .executeAndFetchFirst(Hero.class);//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }

    }

    @Override
    public List<Hero> getAllHeroes() {
        String sql = "SELECT * FROM heroes";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Hero.class);//takes the object that matches with the id, the execute and fetch fetches everything that matches condition give
        }
    }
// we are not using constructor so it wont be .binde
    @Override
    public void updateHero(int id, String heroName, int heroAge, String heroStrength, String heroWeakness) {
        String sql = "UPDATE heroes SET (heroName, heroAge, heroStrength, heroWeakness) = (:heroName, :heroAge, :heroStrength, :heroWeakness) WHERE id = : id";
        try (Connection con = sql2o.open()){
             con.createQuery(sql)
                     .addParameter("heroName", heroName)
                     .addParameter("heroAge", heroAge)
                     .addParameter("heroStrength", heroStrength)
                     .addParameter("heroWeakness", heroWeakness)
                     .addParameter("id", id)// not updating id because its a condition
                     .executeUpdate();//takes the first object that matches with the id instead all
        }
    }

    @Override
    public void deleteHeroById(int id) {
    String sql = "DELETE * FROM heroes WHERE id= :id;";
        try (Connection con = sql2o.open()){
             con.createQuery(sql)
                     .addParameter("id", id)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }

    }

    @Override
    public void deleteAllHeroes() {
        String sql = "DELETE * FROM heroes";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();//takes the first object that matches with the id instead all, hero.class used because something is being returned
        }

    }
}
