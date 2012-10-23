<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<html>
  <head>
	<style>html { display:none }</style>
	<script>
		if (self == top) {
			document.documentElement.style.display = 'block';
		} else {
			top.location = self.location;
		}
	</script>
	<title>Shop</title>
  	<link rel="stylesheet" type="text/css" href='<c:url value="/css/dashboard.css"/>' />
  	<link rel="shortcut icon" href='<c:url value="/favicon.ico"/>' />
    <decorator:title />
    <decorator:head/>
  </head>
  <body>
	  <div class="pageWrapper">
			<div class="menu">
				&nbsp;&nbsp;Menu items here
				<a href="<c:url value="/logout.do"/>">Log Out</a>
			</div>
		<br>
	    <decorator:body/>
  </div>
  </body>
</html>