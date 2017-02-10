<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
</head>
<body>
<h2>Hello SSE!</h2>

<div id="msg"></div>

<script type="text/javascript">
	
	if( !!window.EventSource ){//Chrome,Firefox支持
		var source = new EventSource('push');
		s='';
		source.addEventListener('message',function(e){
			s+=e.data+"</br>";
			$("#msg").html(s);
		});
		
		source.addEventListener('open',function(e){
			console.log('连接打开');
		},false);
		
		source.addEventListener('error',function(e){
			if(e.readyState == EventSource.CLOSED){
				console.log('连接关闭');
			}
			else{
				console.log(e.readyStatus);
			}
		},false);
	}else{
		console.log('你的浏览器不支持');
	}

</script>
</body>
</html>
