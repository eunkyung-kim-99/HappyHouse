<?xml version="1.0" encoding = "utf-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<addresses>
	<c:foreach items="${sidolist }" var="sido">
		<address>
			<sidoname>${sido.sidoName }</sidoname>
		</address>
	</c:foreach>
</addresses>






