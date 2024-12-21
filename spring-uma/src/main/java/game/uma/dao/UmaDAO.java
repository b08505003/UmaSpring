package game.uma.dao;

import game.uma.entity.Race;
import game.uma.entity.RaceInfo;
import game.uma.entity.Uma;

import java.util.List;

public interface UmaDAO {

    List<Uma> findUmas();
    List<Race> findRaces();
    List<RaceInfo> findRaceInfos();

    Uma findUmaById(int id);
    Race findRaceById(int id);
    RaceInfo findRaceInfoByTitle(String title);

    List<Uma> getRandomUmas(int count);

    void save(Uma uma);
    void save(Race race);
    void save(RaceInfo raceInfo);

    void delete(Uma uma);
    void delete(Race race);
    void delete(RaceInfo raceInfo);

}
