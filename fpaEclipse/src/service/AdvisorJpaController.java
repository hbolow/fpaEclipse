/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entity.Advisor;

/**
 *
 * @author hbolow
 */
public class AdvisorJpaController{

    public AdvisorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Advisor advisor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(advisor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Advisor advisor) throws exceptions.NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            advisor = em.merge(advisor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = advisor.getId();
                if (findAdvisor(id) == null) {
                    throw new exceptions.NonexistentEntityException("The advisor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws exceptions.NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Advisor advisor;
            try {
                advisor = em.getReference(Advisor.class, id);
                advisor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new exceptions.NonexistentEntityException("The advisor with id " + id + " no longer exists.", enfe);
            }
            em.remove(advisor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Advisor> findAdvisorEntities() {
        return findAdvisorEntities(true, -1, -1);
    }

    public List<Advisor> findAdvisorEntities(int maxResults, int firstResult) {
        return findAdvisorEntities(false, maxResults, firstResult);
    }

    private List<Advisor> findAdvisorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Advisor.class));
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

    public Advisor findAdvisor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Advisor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdvisorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Advisor> rt = cq.from(Advisor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
