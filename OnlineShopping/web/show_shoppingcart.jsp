<%-- 
    Document   : showShoppingCart
    Created on : Nov 22, 2020, 3:13:07 PM
    Author     : sarun
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.Products"%>
<%@page import="model.ProductTable"%>
<%@page import="model.Shoppingcart"%>
<%@page import="model.ShoppingcartPK"%>
<%@page import="model.ShoppingCartTable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Products</title>
    </head>
    <jsp:useBean id="ShoppingCart" class="model.Shoppingcart" scope="request"/><!--ดึงข้อมูลมาเก็บไว้ในlist-->
     <%
        //Object cartIdObject = request.getAttribute("cartId");
            Integer cartIdInteger = (Integer) request.getAttribute("cartId");
            int cartId = 0;
            if (cartIdInteger != null) {

                try {
                     cartId = cartIdInteger.intValue();


                } catch (NumberFormatException e) {
                }
            }       
            List<Shoppingcart> ShoppingCartList = ShoppingCartTable.findAllShoppingCart();
            Iterator<Shoppingcart> itr = ShoppingCartList.iterator();
            
     %>

    <body>
        <%int total = 0;%>
        <center>  <!--สร้างtable-->
        <h1>Product List</h1>
        <table border="1">
          <tr>
            <th>DVD name</th>
            <th>Rating</th>
            <th>Year</th>
            <th>Price/Unit</th>
            <th>Quantity</th>
            <th>Price</th>
          </tr>
          <!--loop สร้างข้อมูล-->
          <%
              
               while(itr.hasNext()) {
                   Shoppingcart shoppingCart = itr.next();
                   Products product = shoppingCart.getProducts();
                   out.println("<tr>");
                   out.println("<td> "+ product.getMovie() + "</td>");
                   out.println("<td> "+ product.getRating() + "</td>");
                   out.println("<td> "+ product.getYearcreate() + "</td>");
                   out.println("<td> "+ product.getPrice() + "</td>");
                   out.println("<td> "+ shoppingCart.getQuantity() + "</td>");
                   int pricess = shoppingCart.getQuantity()*shoppingCart.getProducts().getPrice();
                   out.println("<td> "+ (pricess) + "</td>");
                   out.println("<tr>");
                   total += pricess;
               }
          %>
                    <tr>
              <td>Total</td>
              <%
                 out.println("<td> "+ total + "</td>");
              %></tr>
    </table>
    <a href="index.html">Back to Menu</a>
 </center>
    </body>
</html>
