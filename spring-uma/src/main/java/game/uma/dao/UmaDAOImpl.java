package game.uma.dao;

import game.uma.entity.Race;
import game.uma.entity.RaceInfo;
import game.uma.entity.Uma;
import jakarta.persistence.EntityManager;
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
    public List<Uma> findAllUmas() {
        return entityManager.createQuery("from Uma", Uma.class).getResultList();
    }

    @Override
    public List<Race> findRaces() {
        return entityManager.createQuery("from Race", Race.class).getResultList();
    }

    @Override
    public Uma findUmaById(int id) {
        return entityManager.find(Uma.class, id);
    }

    @Override
    public List<Uma> getRandomUmas(int count) {
        return entityManager.createQuery("SELECT u FROM Uma u ORDER BY FUNCTION('RAND')", Uma.class)
                .setMaxResults(count).getResultList();
    }

    @Override
    public List<RaceInfo> findAllRaceInfos() {
        return entityManager.createQuery("from RaceInfo", RaceInfo.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Uma uma) {
        entityManager.merge(uma);
    }

    @Override
    @Transactional
    public void delete(Uma uma) {
        entityManager.remove(uma);
    }
}
