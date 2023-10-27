<%-- 
    Document   : ConfirmInformation
    Created on : Oct 27, 2023, 7:12:16 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Information</title>
    </head>
    <body>
        <center>
        <% 
            Integer totalIn = (Integer)session.getAttribute("total"); 
           int total = totalIn.intValue();
        %>
        <h1>Your Order Is ConFirmed!</h1>
        <%
        out.println(" <h1>The Total amount is "+ total +"</h1>");

        session.removeAttribute("total"); //clear session
        %>
       
        </center>
    </body>
</html>
