<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.bonc.entity.*,java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>列表页</title>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<style type="text/css">
div, ul, li {
	margin: 0;
	padding: 0;
}

li {
	float: left;
	width: 100px;
	heigth: 24px;
	line-height: 24px;
	list-style: none;
	border: solid 1px;
	text-align: center;
}

#cart {
	width: 420px;
	float: left;
	text-align: center;
	margin-right: 100px;
}

.add {
	width: 400px;
	height:185px;
	float: left;
}
.update {
	float: left;
	display:none;
}
.submit{
	margin-left:90px;
}
.select{
	float:left;
}


</style>
<script type="text/javascript">
	$(function() {
		//删除按钮
		$("input[name='delete']").click(function() {
			$(this).parent().parent().remove();
			var url ="<%=request.getContextPath()%>/delete";
			console.log("url ： " + url);
			var id = $(this).attr('id');
			console.log("id ： " + id);
			//ajax操作
			$.post(url, {
				id : id
			}, function(data, status) {
				if (status) {
					console.log("success");
					location.reload();
				}
			});
		});
		//修改
		$("input[name='update']").click(function(){
			$(".update").css("display","block");
		})
		$(".cancel").click(function(){
			$(".update").css("display","none");
		})
		$(".submit").click(function(){
			$(".update").css("display","none");
		})
		//Ajax显示数据
            $("input[name='update']").click(function(){
                $.ajax({
                    type: "POST",
                    url: "selectById",
                    dateType: "json",
                    data:{'id':$(this).attr('id')},
                    success: function(result){
                    	$("#update").children().last().val(result.stuId);
                    	$("#update").children().first().val(result.stuName);
                    	$("#update").children().first().next().next().val(result.classId);
                    }
                  
                });  
            });
		//ajax修改数据
		$("input[name='delete']").click(function() {
			$(this).parent().parent().remove();
			var url ="<%=request.getContextPath()%>/delete";
			console.log("url ： " + url);
			var id = $(this).attr('id');
			console.log("id ： " + id);
			//ajax操作
			$.post(url, {
				id : id
			}, function(data, status) {
				if (status) {
					console.log("success");
					location.reload();
				}
			});
		});
        
	})
</script>
</head>
<body>
	<div id="cart">
		<h1>显示学生信息</h1>
		<div class="all">
			<form action="remove" method="post">
				<input type="submit" value="显示所有信息">
			</form>
		</div>
		<ul>
			<li>学号</li>
			<li>姓名</li>
			<li>班级</li>
			<li>操作</li>
		</ul>
		<c:forEach items="${hello}" var="stu">
			<ul>
				<li>${stu.stuId}</li>
				<li>${stu.stuName}</li>
				<li>${stu.classId}</li>
				<li>
				<input name="delete" type="button" value="删除"id="${stu.stuId}">
				<input name="update" type="button" value="修改"id="${stu.stuId}">
				</li>
			</ul>
		</c:forEach>
			<a href="msg?p=${prePage}">上一页</a>
        	<a href="msg?p=${nxtPage}">下一页</a>
	</div>
	
	<div class="add">
	<h1>新增学生信息</h1>
	<form id="add" action="doAdd" method="post">
		姓名:<input type="text" name="stuName"> <br> 
		班级:<input type="text" name="classId"> <br>
		<input type="submit" value="提交" class="submit"><br>
	</form>
	</div>
	
	<div class="select">
		<h1>查询学生信息</h1>
		<form action="key" method="post">
			<select name="item">  
	  			<option value ="stuId">学号</option>  
	  			<option value ="stuName">姓名</option>  
	  			<option value="classId">班级</option>  
			</select>
			<input type="text" name="str"><br>
			<input type="submit" value="确定" class="submit"><br>
		</form> 
	</div>
	
	<div class="update">
		<h1>修改学生信息</h1>
		<form id="update" action="doAdd" method="post">
			姓名:<input type="text" name="stuName"> <br> 
			班级:<input type="text" name="classId"> <br>
			<input type="submit" value="提交" class="submit">
			<input type="button" value="取消" class="cancel"><br>
			<input type="hidden" name="stuId">
	</form>
	</div>
	
	
</body>
</html>