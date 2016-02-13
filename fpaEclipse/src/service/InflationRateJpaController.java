/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import exceptions.NonexistentEntityException;
import entity.Country;
import entity.InflationRate;

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
 */public class InflationRateJpaController  {

    public InflationRateJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InflationRate inflationRate) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(inflationRate);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InflationRate inflationRate) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            inflationRate = em.merge(inflationRate);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = inflationRate.getId();
                if (findInflationRate(id) == null) {
                    throw new NonexistentEntityException("The inflationRate with id " + id + " no longer exists.");
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
            InflationRate inflationRate;
            try {
                inflationRate = em.getReference(InflationRate.class, id);
                inflationRate.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inflationRate with id " + id + " no longer exists.", enfe);
            }
            em.remove(inflationRate);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InflationRate> findInflationRateEntities() {
        return findInflationRateEntities(true, -1, -1);
    }

    public List<InflationRate> findInflationRateEntities(int maxResults, int firstResult) {
        return findInflationRateEntities(false, maxResults, firstResult);
    }

    private List<InflationRate> findInflationRateEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InflationRate.class));
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

    private List<InflationRate> findInflationRateEntitiesPerCountry(Country country) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InflationRate.class));
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public InflationRate findInflationRate(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InflationRate.class, id);
        } finally {
            em.close();
        }
    }

    public int getInflationRateCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InflationRate> rt = cq.from(InflationRate.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
