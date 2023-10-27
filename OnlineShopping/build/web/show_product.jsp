<%-- 
    Document   : showemployee
    Created on : Nov 22, 2020, 3:13:07 PM
    Author     : sarun
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.Products"%>
<%@page import="model.ProductTable"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Products</title>
    </head>
    <jsp:useBean id="product" class="model.Products" scope="request"/><!--ดึงข้อมูลมาเก็บไว้ในlist-->
     <%
                       
            List<Products> ProductList = ProductTable.findAllProducts();
            Iterator<Products> itr = ProductList.iterator();
            
     %>
    <body>
        <center>  <!--สร้างtable-->
        <h1>Product List</h1>
        <table border="1">
          <tr>
            <th>ID</th>
            <th>Movie</th>
            <th>Rating</th>
            <th>Yearcreate</th>
            <th>Price</th>
          </tr>
          <!--loop สร้างข้อมูล-->
          <%
              
               while(itr.hasNext()) {
                   product = itr.next();
                   out.println("<tr>");
                   out.println("<td> "+ product.getId() + "</td>");
                   out.println("<td> "+ product.getMovie() + "</td>");
                   out.println("<td> "+ product.getRating() + "</td>");
                   out.println("<td> "+ product.getYearcreate() + "</td>");
                   out.println("<td> "+ product.getPrice() + "</td>");
                   out.println("<tr>");
               }
          %>
    </table>
    <a href="index.html">Back to Menu</a>
 </center>
    </body>
</html>
