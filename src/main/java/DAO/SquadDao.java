package DAO;

import models.Hero;
import models.Squad;

import java.util.List;

public interface SquadDao {
    void add(Squad squad);// putting add method mandatory for all squad objects
    Squad findById(int id);
    List<Squad> getAllSquads();
    void updateSquad(String squadName, String squadCause, int squadSize, int id);
    void deleteSquadById(int id);
    void deleteAllSquads();
    List<Hero> getAllHeroesBySquad(int squadId);
}
