package DAO;

import models.Hero;
import models.Squad;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquad implements SquadDao {
    private final Sql2o sql2o; //create property of the class to be instantiating, its what connects to the database

    public Sql2oSquad (Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(Squad squad) {
        String sql = "INSERT into squads (squadName, squadCause, squadSize, squadCreatedAt) VALUES (:squadName, :squadCause, :squadSize, now());";//there's brackets after now because now is in-built function
        try (Connection con = sql2o.open()){ //this is connected with the app.java, opening database.
            int id =(int) con.createQuery(sql, true)//id will be integer form it is being casted into an integer, true mean return generated key
                    .bind(squad)//passing in object squad to avoid typing .addparameter for each property
                    .executeUpdate()
                    .getKey();//  this will get primary key and assign to the ID
            squad.setId( id);// database assigning id

        }catch (Sql2oException ex) {
            System.out.println("there was a problem in adding the endangered animal");
        }
    }

    @Override
    public Squad findById(int id) {
        String sql = "SELECT * FROM squads WHERE id = :id;";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetchFirst(Squad.class);//takes the first object that matches with the id instead all, squad.class used because something is being returned
        }

    }

    @Override
    public List<Squad> getAllSquads() {
        String sql = "SELECT * FROM squads";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Squad.class);//takes the object that matches with the id, the execute and fetch fetches everything that matches condition give
        }
    }
    // we are not using constructor so it wont be .binde
    @Override
    public void updateSquad(String squadName, String squadCause, int squadSize, int id) {
        String sql = "UPDATE squads SET (heroName, heroAge, heroStrength, heroWeakness) = (:heroName, :heroAge, :heroStrength, :heroWeakness) WHERE id = : id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("squadName", squadName)
                    .addParameter("squadCause", squadCause)
                    .addParameter("squadSize", squadSize)
                    .addParameter("id", id)// not updating id because its a condition
                    .executeUpdate();//takes the first object that matches with the id instead all
        }
    }

    @Override
    public void deleteSquadById(int id) {
        String sql = "DELETE * FROM squads WHERE id= :id;";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();//takes the first object that matches with the id instead all, squad.class used because something is being returned
        }

    }

    @Override
    public void deleteAllSquads() {
        String sql = "DELETE * FROM squads";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();//takes the first object that matches with the id instead all, squad.class used because something is being returned
        }

    }

    @Override
    public List<Hero> getAllHeroesBySquad(int squadId) {
        String sql = "SELECT * FROM heroes WHERE squadId=:squadId";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Hero.class);//takes the object that matches with the id, the execute and fetch fetches everything that matches condition give
        }
    }
}
