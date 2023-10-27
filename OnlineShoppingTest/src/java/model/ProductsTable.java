/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LENOVO
 */
public class ProductsTable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
    
    public static List<Products> findAllProducts() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        List<Products> proList = null;
        try {
            proList = (List<Products>) em.createNamedQuery("Products.findAll").getResultList();         
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
        finally {
            em.close();
            emf.close();
        }
        return proList;
    }
    public static Products findProductsById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager em = emf.createEntityManager();
        Products emp = null;
        try {
            emp = em.find(Products.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            em.close();
            //emf.close();
        }
        return emp;
    }
    
    public static int updateProducts(Products pro) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Products target = em.find(Products.class, pro.getId());
            if (target == null) {
                return 0;
            }
            target.setMovie(pro.getMovie());
            target.setRating(pro.getRating());
            target.setPrice(pro.getPrice());
            em.persist(target);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            
        }
        finally {
            em.close();
            emf.close();
        }
        return 1;
        
    }
    public static int removeProducts(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Products target = em.find(Products.class, id);
            if (target == null) {
                return 0;
            }
            em.remove(target);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            
        }
        finally {
            em.close();
            emf.close();
        }
        return 1;
    }
    
    
    public static int insertProducts(Products pro) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Products target = em.find(Products.class, pro.getId());
            if (target != null) {
                return 0;
            }
            em.persist(pro);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            
        }
        finally {
            em.close();
            emf.close();
        }
        return 1;
    }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
