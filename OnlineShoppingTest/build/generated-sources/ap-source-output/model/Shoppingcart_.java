package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Products;
import model.ShoppingcartPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2566-10-27T22:12:08")
@StaticMetamodel(Shoppingcart.class)
public class Shoppingcart_ { 

    public static volatile SingularAttribute<Shoppingcart, ShoppingcartPK> shoppingcartPK;
    public static volatile SingularAttribute<Shoppingcart, Integer> quantity;
    public static volatile SingularAttribute<Shoppingcart, Products> products;

}