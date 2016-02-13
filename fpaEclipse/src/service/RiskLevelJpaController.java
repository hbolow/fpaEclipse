/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import exceptions.NonexistentEntityException;
import entity.RiskLevel;

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
public class RiskLevelJpaController  {

    public RiskLevelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RiskLevel riskLevel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(riskLevel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RiskLevel riskLevel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            riskLevel = em.merge(riskLevel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = riskLevel.getId();
                if (findRiskLevel(id) == null) {
                    throw new NonexistentEntityException("The riskLevel with id " + id + " no longer exists.");
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
            RiskLevel riskLevel;
            try {
                riskLevel = em.getReference(RiskLevel.class, id);
                riskLevel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The riskLevel with id " + id + " no longer exists.", enfe);
            }
            em.remove(riskLevel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RiskLevel> findRiskLevelEntities() {
        return findRiskLevelEntities(true, -1, -1);
    }

    public List<RiskLevel> findRiskLevelEntities(int maxResults, int firstResult) {
        return findRiskLevelEntities(false, maxResults, firstResult);
    }

    private List<RiskLevel> findRiskLevelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RiskLevel.class));
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

    public RiskLevel findRiskLevel(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RiskLevel.class, id);
        } finally {
            em.close();
        }
    }

    public int getRiskLevelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RiskLevel> rt = cq.from(RiskLevel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
