<%-- 
    Document   : index
    Created on : Oct 26, 2023, 11:17:45 PM
    Author     : LENOVO
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.ProductsTable"%>
<%@page import="java.util.List"%>
<%@page import="model.Products"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <jsp:useBean id="pro" class="model.Products" scope="request"/>
     <%
                       
            List<Products> proList =ProductsTable.findAllProducts();
            Iterator<Products> itr = proList.iterator();
            
     %>
    <body>
        <center>
        <h1>DVD Catalog</h1>
            
    <form name="AddShoppingcart" action="AddShoppingcartController" method="POST">
        <table border="1">
              <tr>
                <th>DVD Name</th>
                <th>Rate</th>
                <th>Year</th>
                <th>Price</th>
                <th>Quantity</th>
              </tr>
              <%
                   while(itr.hasNext()) {
                       pro = itr.next();
                       out.println("<tr>");
                       %>
                       
                       <td>
                           <input type="checkbox" name="select" value="<%= pro.getId() %>">
                           <input type="hidden" name="selected_<%= pro.getId() %>" value="true">
                           <%
                               out.println(pro.getMovie());
                           %>
                       </td>
                       <%
                       
                       out.println("<td> "+ pro.getRating() + "</td>");
                       out.println("<td> "+ pro.getYearcreate() + "</td>");
                       out.println("<td> "+ pro.getPrice() + "</td>");
                       %>
                       <td>
                           <input type="text" name="qty" value="" />
                       </td>
                       <%
                       out.println("<tr>");
                   }
              %>
        </table>
        <input type="submit" value="Add to Cart"/>
    </form>
    
    
 </center>
    </body>
</html>
