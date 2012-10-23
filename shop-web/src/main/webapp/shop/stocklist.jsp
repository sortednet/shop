<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock list</title>

<script type="text/javascript" src="/shop-web/common/js/jquery-1.7.2.js"></script>



<script type="text/javascript">

var jq = jQuery.noConflict();

jq(document).ready(function() {updateNumItems()}); 
</script>
 
<script type="text/javascript"> 
function updateNumItems() {

	jq(function() {
		jq.get("/shop-web/shop/basket/numItems.do",
	    	function(data){
	       		// data contains the result
	       		jq("#numItems").replaceWith('<span id="numItems">'+ data + '</span>');
	    });
  }); 
}

function addItem(stockItemId, quantity) {

	jq(function() {
		jq.post("/shop-web/shop/basket/addItem.do",
			{  	stockItemId:  stockItemId,
	       		quantity:  quantity },			  
		    function(data){
	       			updateNumItems();
		    });
	}); 
}

function removeItem(stockItemId, quantity) {

	jq(function() {
		jq.post("/shop-web/shop/basket/removeItem.do",
			{  	stockItemId:  stockItemId,
	       		quantity:  quantity },			  
		    function(data){
	       			updateNumItems();
		    });
	}); 
}
</script>

</head>
<body>
<c:url value="/logout.do" var="logoutUrl"/>

<a href="${logoutUrl}">logout</a>
<br>
Basket: <span id="numItems">---</span>
<br>
Stock list

<table>
<tr><th>SKU</th><th>Short Desc</th><th>Full Desc</th></tr>

<c:forEach var="item" items="${stockItems}">
<tr><td>${item.sku}</td><td>${item.shortDescription}</td><td>${item.fullDescription}</td><td onclick="addItem(${item.stockItemId}, 1)">Add</td><td onclick="removeItem(${item.stockItemId}, 1)">Remove</td></tr>
</c:forEach>
</table>

</body>
</html>