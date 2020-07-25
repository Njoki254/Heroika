package DAO;

import models.Hero;

import java.util.List;

public interface HeroDao {
    void add(Hero hero);// putting add method mandatory for all hero objects
    Hero findById(int id);
    List<Hero> getAllHeroes();
    void updateHero(int id,String heroName, int heroAge,  String heroStrength, String heroWeakness);
    void deleteHeroById(int id);
    void deleteAllHeroes();
}
