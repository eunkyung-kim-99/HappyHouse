<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
 <addresses>
<c:forEach items="${gugunlist }" var="item">
	<address>
		<gugunName>${item.gugunName }</gugunName>
	</address>
</c:forEach>
</addresses>
