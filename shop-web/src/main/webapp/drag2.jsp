<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">

.dropDiv {
	width: 100px;
	height: 100px;
	position: relative;
	float: left;
	border:2px solid;
    border-radius:25px;
}

.mainDropDiv {
	width: 210px;
	height: 100px;
}

.dragDiv {
	width: 100px;
	height: 100px;
	position: relative;
	border:2px solid;
}

</style>

<script type="text/javascript" src='<c:url value="/common/js/jquery-1.8.2.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/common/js/jquery-ui-1.9.0.custom.js"/>' ></script>

<script type="text/javascript">
	var jq = jQuery.noConflict();
	
	function Droppable(targetId) {		
		this.targetId = targetId;
		this.dropped = false;
	}
	
	var targets = new Object;
	targets.draggable1 = new Droppable("droppable1");
	targets.draggable2 = new Droppable("droppable2");
	targets.draggable3 = new Droppable("droppable3");
	targets.draggable4 = new Droppable("droppable4");
	targets.draggable5 = new Droppable("droppable5");
	

	jq(document).ready(function() {

		jq("#done").hide();
		
		jq(".dragDiv").draggable();		
		
		jq(".dropDiv").droppable({
			drop: function(event, ui) {
				dropOn(ui, this.id);
			},
			tolerance: "intersect"
		});
		
	});
	
	function dropOn(droppableUi, targetId) {
		var id = droppableUi.helper.attr('id');
		
		var allowedTarget = targets[id];

		if (targetId == allowedTarget.targetId) {
			// Move ui to cover target
			var target = jq("#"+targetId);
			var tx = target.offset().left;
			var ty = target.offset().top;
			
			var sx = droppableUi.offset.left;
			var sy = droppableUi.offset.top;
					
			if (sx > tx) {
				horiz = "-="+(sx - tx);
			} else {
				horiz = "+="+(tx - sx);
			}
			if (sy > ty) {
				vert = "-="+(sy - ty);
			} else {
				vert = "+="+(ty - sy);
			}		
			droppableUi.helper.animate({top: vert, left: horiz});
			allowedTarget.dropped = true;
			if (checkDone()) {
				jq("#done").show();
			}
		} else {
			// back to where it came from
			droppableUi.helper.animate({top: 0, left: 0}); 
		}
	}
	
	function checkDone() {
		
		for (var key in targets) {
			var d = targets[key];
			if (d.dropped == false) {
				return false;
			}
		}
		
		return true;
	}
	
</script>

<title>Test drag 2</title>
</head>
<body>

<table>
<tr><td><div id="droppable1" class="dropDiv">Drop 1</div></td>
    <td><div id="droppable2" class="dropDiv">Drop 2</div></td>
    <td><div id="droppable3" class="dropDiv">Drop 3</div></td>
    <td><div id="droppable4" class="dropDiv">Drop 4</div></td>
    <td><div id="droppable5" class="dropDiv">Drop 5</div></td>
</tr>
</table>

<table>
<tr>
<td><div id="draggable5" class="dragDiv">Drag me 5</div></td>
<td><div id="draggable2" class="dragDiv">Drag me 2</div></td>
<td><div id="draggable3" class="dragDiv">Drag me 3</div></td>
<td><div id="draggable1" class="dragDiv">Drag me 1</div></td>
<td><div id="draggable4" class="dragDiv">Drag me 4</div></td>
</tr>
</table>

<img id="done" alt="done" src="common/images/basic/next-inactive.jpg" />

</body>
</html>