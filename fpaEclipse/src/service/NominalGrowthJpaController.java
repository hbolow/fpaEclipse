/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import exceptions.NonexistentEntityException;
import entity.NominalGrowth;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hbolow
 */
public class NominalGrowthJpaController  {

    public NominalGrowthJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NominalGrowth nominalGrowth) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nominalGrowth);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NominalGrowth nominalGrowth) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nominalGrowth = em.merge(nominalGrowth);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = nominalGrowth.getId();
                if (findNominalGrowth(id) == null) {
                    throw new NonexistentEntityException("The nominalGrowth with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NominalGrowth nominalGrowth;
            try {
                nominalGrowth = em.getReference(NominalGrowth.class, id);
                nominalGrowth.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nominalGrowth with id " + id + " no longer exists.", enfe);
            }
            em.remove(nominalGrowth);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NominalGrowth> findNominalGrowthEntities() {
        return findNominalGrowthEntities(true, -1, -1);
    }

    public List<NominalGrowth> findNominalGrowthEntities(int maxResults, int firstResult) {
        return findNominalGrowthEntities(false, maxResults, firstResult);
    }

    private List<NominalGrowth> findNominalGrowthEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NominalGrowth.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public NominalGrowth findNominalGrowth(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NominalGrowth.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominalGrowthCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NominalGrowth> rt = cq.from(NominalGrowth.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
