<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>列表</td>
            <td>额度</td>
            <td>余额</td>
            <td>扣减</td>
        </tr>
        <c:forEach items="${page}" var="c" varStatus="st">
            <tr>
                <td>${c.name}</td>
                <td>${c.budget}</td>
                <td>${c.balance}</td>
                <td><a href="updateAccount?id=${c.id}">编辑金额</a></td>
            </tr>
        </c:forEach>
         
    </table>
    <br>
