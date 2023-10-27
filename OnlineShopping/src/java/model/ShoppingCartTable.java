/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author sarun
 */
public class ShoppingCartTable {

    public static List<Shoppingcart> findAllShoppingCart() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingPU");
        EntityManager em = emf.createEntityManager();
        List<Shoppingcart> ShoppingcartList = null;
        try {
            ShoppingcartList = (List<Shoppingcart>) em.createNamedQuery("Shoppingcart.findAll").getResultList();         
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
        finally {
            em.close();
            emf.close();
        }
        return ShoppingcartList;
    }
    public static Products findShoppingCartById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingPU");
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
    
    public static int updateShoppingCart(Products emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Products target = em.find(Products.class, emp.getId());
            if (target == null) {
                return 0;
            }
            target.setId(emp.getId());
            target.setMovie(emp.getMovie());
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
    public static int removeShoppingCart(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingPU");
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
    
   public static int insertShoppingcart(Shoppingcart shoppingCart) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingPU");
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        
        // ตรวจสอบว่ารายการ ShoppingCart ที่ต้องการเพิ่มลงในฐานข้อมูลมีอยู่หรือไม่
        Shoppingcart existingCartItem = em.find(Shoppingcart.class, shoppingCart.getShoppingcartPK());
        if (existingCartItem != null) {
            // ถ้ามีอยู่แล้ว อาจจะต้องทำการอัพเดตจำนวนสินค้าที่ถูกเพิ่มลงในตะกร้า
            existingCartItem.setQuantity(existingCartItem.getQuantity() + shoppingCart.getQuantity());
        } else {
            // ถ้ายังไม่มีรายการนี้ในฐานข้อมูล ให้ทำการเพิ่มลงในตาราง
            em.persist(shoppingCart);
        }
        
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        return 0; // ในกรณีที่มีข้อผิดพลาดในการเพิ่มข้อมูล
    } finally {
        em.close();
        emf.close();
    }
    return 1; // ถ้าสำเร็จในการเพิ่มข้อมูล
}


}
