<%-- 
    Document   : index
    Created on : Dec 15, 2011, 9:19:50 AM
    Author     : Fate
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bingle</title>
    </head>
    <body>
        <form id="frmSearch" action="indexController">
            <input type="text" id="key" name="key" value="${key}"/>
            <input type="submit" name="Search" value="Search"/>
            <input type="hidden" name="act" value="search"/>
        </form>
        <table>
            <c:forEach items="${articles}" var="item" varStatus="i" begin="0" end="${articles.size()}" step="1">            
            <tr>
                <td>
                    <strong><a href="${item.link}">${item.title}</a></strong><br/>
                    ${item.link}<br/>
                    ${item.description}<br/>
                    Source: ${item.source}<br/>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
