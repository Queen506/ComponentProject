/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
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
public class ShoppingcartTable {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
    
    public static List<Shoppingcart> findAllShoppingcart() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        List<Shoppingcart> shopList = null;
        try {
            shopList = (List<Shoppingcart>) em.createNamedQuery("Shoppingcart.findAll").getResultList();         
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
        finally {
            em.close();
            emf.close();
        }
        return shopList;
    }
    public static Shoppingcart findShoppingcartById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        Shoppingcart emp = null;
        try {
            emp = em.find(Shoppingcart.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            em.close();
            //emf.close();
        }
        return emp;
    }

    public static List<Shoppingcart> findShoppingcartBycartId(int cartId) {
    List<Shoppingcart> allshopLists = findAllShoppingcart(); // 
    List<Shoppingcart> shopList = new ArrayList<>();

    for (int i = 0; i < allshopLists.size(); i++) {
        Shoppingcart cart = allshopLists.get(i);
        if (cart.getShoppingcartPK().getCartId() == cartId) {
            shopList.add(cart);
        }
    }

    return shopList;
}
    
    public static int updateShoppingCart(Shoppingcart shop) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shoppingcart target = em.find(Shoppingcart.class, shop.getShoppingcartPK());
            if (target == null) {
                return 0;
            }
            target.setShoppingcartPK(shop.getShoppingcartPK());
            target.setQuantity(shop.getQuantity());
            target.setProducts(shop.getProducts());
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
    public static int removeShoppingcart(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shoppingcart target = em.find(Shoppingcart.class, id);
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
    
    
    public static int insertShoppingcart(Shoppingcart shop) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingTestPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shoppingcart target = em.find(Shoppingcart.class, shop.getShoppingcartPK());
            if (target != null) {
                return 0;
            }
            em.persist(shop);
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
