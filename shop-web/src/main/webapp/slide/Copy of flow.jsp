<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<c:url value="/common/css/${slideShow.style}.css"/>' />
<script type="text/javascript" src='<c:url value="/common/js/jquery-1.7.2.js"/>' ></script>

<script type="text/javascript">
	var jq = jQuery.noConflict();
	
	
	jq(document).ready(function() {

		// Images will highlight when rolled over
		jq('img').bind('mouseenter mouseleave', function() {
			//alert("rollover")
		    jq(this).attr({
		        src: jq(this).attr('data-other-src'), 'data-other-src': jq(this).attr('src') 
		    })
		});
		
		
		
	});
	

	
</script>

<title>${slideShow.title}</title>
</head>
<body>
<span class="slideshowTitle">${slideShow.title}</span> 
<br>

<span class="slideTitle">${slide.title}</span>

<div class="slideTextContent">${slide.content}</div>

<table width="100%">
<tr>
<td width="50%"><div class="slideTextContent">${slide.content}</div></td>
<td width="50%">
<div class="slideMediaContent">
Media will go here ${slide.imageName}
<c:choose>
  <c:when test="${slide.imageName != null}">Show image ${slide.imageName}
  	<img src='<c:url value="/common/images/${slideShow.style}/${slide.imageName}"/>'/>
  </c:when>
  <c:otherwise>No media to show</c:otherwise>
</c:choose>
</div>
</td>
</tr>
</table>
<br/>
<br/>
<br/>
<div class="slideFooter">
Page ${slide.slideNumber + 1} of ${slideShow.numSlides} &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
  
<!--  Back button -->
<c:choose>
  <c:when test="${slide.slideNumber > 0}"> 
	<a href='<c:url value="/slideshow/slide/${slideShow.slideShowId}/${slide.slideNumber - 1}.do"/>'>
		<img id="backBtn" alt="back" src='<c:url value="/common/images/${slideShow.style}/back-up.jpg"/>' data-other-src='<c:url value="/common/images/${slideShow.style}/back-down.jpg"/>' />
	</a>  
  </c:when>
  <c:otherwise>
  	<img id="backBtn" alt="back" src='<c:url value="/common/images/${slideShow.style}/back-up.jpg"/>' data-other-src='<c:url value="/common/images/${slideShow.style}/back-up.jpg"/>' />
  </c:otherwise>
</c:choose>

<!--  Next button -->
<c:choose>
  <c:when test="${slide.slideNumber < slideShow.numSlides-1}"> 
	<a href='<c:url value="/slideshow/slide/${slideShow.slideShowId}/${slide.slideNumber + 1}.do"/>'>
		<img id="nextBtn" alt="next" src='<c:url value="/common/images/${slideShow.style}/next-up.jpg"/>' data-other-src='<c:url value="/common/images/${slideShow.style}/next-down.jpg"/>' />
	</a>  
  </c:when>
  <c:otherwise>
  	<img id="nextBtn" alt="next" src='<c:url value="/common/images/${slideShow.style}/next-up.jpg"/>' data-other-src='<c:url value="/common/images/${slideShow.style}/next-up.jpg"/>' />
  </c:otherwise>
</c:choose>


</div>
</body>
</html>