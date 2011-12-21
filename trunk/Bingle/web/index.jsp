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
        <link href="style/stylecss.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <form id="frmSearch" action="indexController">
            <img src="images/bingle.png" width="120px" height="60px"/>
            <input class="controlimage" type="text" name="key" value="${key}" size="70%" placeholder="Enter keywords"/>
            <input class="controlimage" type="submit" name="Search" value="Search" />
            <input class="controlimage" type=button onClick="parent.location='about.jsp'" value='About'/>            
            <input type="hidden" name="act" value="search"/>
        </form>
        <table width="100%" align="left">
            <c:forEach items="${articles}" var="item" varStatus="i" begin="0" end="${articles.size()}" step="1">            
                <tr>
                    <td>
                        <strong><a href="${item.link}">${item.title}</a></strong><br/>
                        <span class="style19">${item.link}</span><br/>
                        ${item.description}<br/>
                        <span class="source"><strong>Source:</strong> ${item.source}</span><br/>
                    </td>
                </tr>
                <tr><td>&nbsp;</td></tr>
            </c:forEach>
        </table>
    </body>
</html>
