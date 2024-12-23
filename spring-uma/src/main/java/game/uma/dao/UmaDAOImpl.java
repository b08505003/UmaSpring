package game.uma.dao;

import game.uma.entity.Race;
import game.uma.entity.RaceInfo;
import game.uma.entity.Uma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UmaDAOImpl implements UmaDAO{

    private EntityManager entityManager;

    public UmaDAOImpl(EntityManager em){
        entityManager = em;
    }

    @Override
    public List<Uma> findUmas(int difficulty) {
        var query = entityManager.createQuery(
                "select u from Uma u where u.difficulty <= :data", Uma.class);
        query.setParameter("data", difficulty);
        return query.getResultList();
    }

    @Override
    public List<Uma> findUmas() {
        return entityManager.createQuery("from Uma", Uma.class).getResultList();
    }

    @Override
    public List<Race> findRaces() {
        return entityManager.createQuery("from Race", Race.class).getResultList();
    }

    @Override
    public List<RaceInfo> findRaceInfos() {
        return entityManager.createQuery("from RaceInfo", RaceInfo.class).getResultList();
    }

    @Override
    public Uma findUmaById(int id) {
        return entityManager.find(Uma.class, id);
    }

    @Override
    public Race findRaceById(int id) {
        return entityManager.find(Race.class, id);
    }

    @Override
    public RaceInfo findRaceInfoByTitle(String title) {
        return entityManager.find(RaceInfo.class, title);
    }


    @Override
    public List<Uma> getRandomUmas(int count, int difficulty) {
        TypedQuery<Uma> query = entityManager.createQuery(
                "SELECT u FROM Uma u WHERE u.difficulty <= :data ORDER BY FUNCTION('RAND')", Uma.class);
        query.setParameter("data", difficulty);
        return query.setMaxResults(count).getResultList();
    }

    @Override
    @Transactional
    public void save(Uma uma) {
        entityManager.merge(uma);
    }

    @Override
    @Transactional
    public void save(Race race) {
        entityManager.merge(race);
    }

    @Override
    @Transactional
    public void save(RaceInfo raceInfo) {
        entityManager.merge(raceInfo);
    }

    @Override
    @Transactional
    public void delete(Uma uma) {
        entityManager.remove(uma);
    }

    @Override
    @Transactional
    public void delete(Race race) {
        entityManager.remove(race);
    }

    @Override
    @Transactional
    public void delete(RaceInfo raceInfo) {
        entityManager.remove(raceInfo);
    }
}
