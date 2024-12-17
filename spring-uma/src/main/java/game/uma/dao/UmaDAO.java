package game.uma.dao;

import game.uma.entity.Race;
import game.uma.entity.RaceInfo;
import game.uma.entity.Uma;

import java.util.List;

public interface UmaDAO {

    List<Uma> findAllUmas();

    List<Race> findRaces();

    Uma findUmaById(int id);

    List<Uma> getRandomUmas(int count);

    List<RaceInfo> findAllRaceInfos();

    void save(Uma uma);

    void delete(Uma uma);

}
