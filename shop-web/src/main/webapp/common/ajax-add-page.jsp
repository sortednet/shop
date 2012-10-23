<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
 <script type="text/javascript" src="/trader-web/js/jquery-1.7.2.js"></script>
 <script type="text/javascript">
     var jq = jQuery.noConflict();
 </script>
  
 <title>Spring MVC - jQuery Integration Tutorial</title>
 
</head>
<body>
 
<h3>Spring MVC - jQuery Integration Tutorial</h3>
<h4>AJAX version</h4>
 
Demo 1
<div style="border: 1px solid #ccc; width: 550px;">
 Add Two Numbers:
 
 <input id="inputNumber1" name="inputNumber1" type="text" size="5"> + <input id="inputNumber2" name="inputNumber2" type="text" size="5">
 <input type="submit" value="Add" onclick="add()" />
 
 Sum: <span id="sum">(Result will be shown here)</span>
</div>
 
<div style="border: 1px solid #ccc; width: 550px;">
Playing with jQuery
<script type="text/javascript">
jq(document).ready(function() {showDivs()});
</script>
</div>
 
 <p> a paragraph</p>
 
<script type="text/javascript">
 
function add() {

 jq(function() {
  // Call a URL and pass two arguments
  // Also pass a call back function
  // See http://api.jquery.com/jQuery.post/
  // See http://api.jquery.com/jQuery.ajax/
  // You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!'
  // See http://bugs.jquery.com/ticket/7535
  jq.post("/trader-web/common/ajax/add.do",
     {  inputNumber1:  jq("#inputNumber1").val(),
        inputNumber2:  jq("#inputNumber2").val() },
	    function(data){
	       // data contains the result
	       // Assign result to the sum id
	       jq("#sum").replaceWith('<span id="sum">'+ data + '</span>');
	    });
     }
   ); 
}

function showDivs() {
	//jq("div").css("background", "#ff0000");
	//jq("p").hide();
}
 
</script>
</body>
</html>