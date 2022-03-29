package com.tplaymeow.itmoweblab3.Managers;

import com.tplaymeow.itmoweblab3.Models.Result;
import lombok.extern.java.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Log
public class SaveManager {
    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("tplaymeow-persistence-unit");

    public List<Result> getAll() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        List<Result> results;

        try {
            results = entityManager.createQuery("SELECT r FROM Result r", Result.class).getResultList();
        } catch (Exception ignored) {
            log.log(Level.WARNING, "Fetch failed");
            results = new ArrayList<>();
        }

        entityManager.close();
        return results;
    }

    public boolean save(Result result) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        boolean status;

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(result);
            entityManager.getTransaction().commit();
            status = true;
        } catch (Exception ignored) {
            log.log(Level.WARNING, "Save failed");
            entityManager.getTransaction().rollback();
            status = false;
        }
        entityManager.close();
        return status;
    }
}
