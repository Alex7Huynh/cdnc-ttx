<%-- 
    Document   : index
    Created on : Dec 15, 2011, 9:19:50 AM
    Author     : Fate
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>Bingle</title>
        <link href="style/stylecss.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <form id="frmSearch" action="indexController">
            <img src="images/bingle.png" width="120px" height="60px"/>
            <input class="controlimage" type="text" name="key" value="${key}" size="70%" placeholder="Enter keywords"/>
            <input class="controlimage" type="submit" value="Search" />
            <input class="controlimage" type=button onClick="parent.location='about.jsp'" value='About'/>
            <input type="hidden" name="act" value="search" />
            <c:if test="${key != null}">
                <div>Trang:&nbsp;&nbsp;
                    <c:forEach varStatus="i" begin="0" end="9" step="1">
                        <a href="indexController?act=search&key=${key}&page=${i.count}">
                            <font style="font-size: 15px;" >${i.count}</font> &nbsp;&nbsp;&nbsp;
                        </a>
                    </c:forEach>
                </div>
            </c:if>
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
