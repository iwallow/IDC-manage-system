

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="IP地址管理系统">
  <meta name="Description" content="">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
  <title>IP地址资源管理系统</title>
  <style>
  	*{
		margin:0;
		padding:0;
	}
	body{
		background-color:#f5f5f5;
	}
	#top-content{
		
	}
	#content{
		
		margin:0px auto;
		width:1250px;
		height:450px;
		box-shadow:0 0 15px #222;
		background-color:white;
		display:flex;
		justify-content:space-between;
	}
	#c_left{
		width:65%;
		height:100%;
		background-image:url('backgrounds/VCG41165762320.jpg');
		background-repeat:no-repeat;
		background-size:100% 100%; 
	}
	#c_right{
		width:34%;
		height:97%;
		background-color:white;
		margin:auto auto;
		border-style:solid;
		border-width:1px;
		border-color:#777777;
		border-radius:10px;
		-moz-border-radius:25px;
	}
	#top{
		width:1250px;
		margin:70px auto 10px;
	}
	h2{
		font-family:"Microsoft YaHei";
		font-size:20px;
		text-indent:1em;
		color:#777777;
		text-align:left;
	}
	ul{
		list-style:none;
	}
	h3{
		font-family:"Microsoft YaHei";
		font-size:18px;
		text-indent:1em;
		margin:30px;
		color:#777777;
	}
	#line{
		margin:0px auto 40px;
		width:85%;
	}
	h5{
		text-align:center;
		font-size:12px;
		color:#888888;
	}
	#button{
		text-align: center;
	}
	a{
		color:blue;
	}
	h6{
		text-align:left;
		text-indent:20em;
	}
  </style>
    <script type="text/javascript">
  function myfun()
  {
	 
	
	  
	  var properties = document.getElementById("123");
	 
	  var req = new XMLHttpRequest();
	  
	  
		req.onreadystatechange = function() {
			if (req.readyState == 4) {
				
				var reData = req.responseText;	//responseText 响应的文本
				
				properties.innerHTML =reData;
				
			}
		};
		
		req.open("post", "show.do", true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("param123=" +"123");
		
  	
  }
  
  </script>
 </head>
 
 
 <body onload="myfun()">
	<div id='top'><h2>IP地址资源管理系统<hr></h2></div>
 	<div id='content'>
		<div id='c_left'></div>
		<div id='c_right'>
			<ul>
				<li><h3>用户登录</h3></li>
				<div id='line'><hr></div>
				<li>
					<form class="layui-form" action="sign.do" method="post" >
						<div class="layui-form-item">
							<label class="layui-form-label">用户名</label>
							<div class="layui-input-block">
								<input type="text" name="name" lay-verify="title" style="width:240px;height:35px" autocomplete="off" placeholder="请输入用户名" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">密码 </label>
							<div class="layui-input-inline">
								<input type="password" name="password" lay-verify="pass" placeholder="请输入密码 " autocomplete="off" class="layui-input" style="width:240px;height:35px">
							</div>
							<div class="layui-form-item">
							
							 <div id='button'><br/>
						    <input class="layui-btn layui-btn-normal layui-btn-radius" name ="注册"value="注册" type="submit"/>
						    <input  class="layui-btn layui-btn-normal layui-btn-radius" name="登录"value ="登录" type="submit" />
						    
					        </div>
						   
					  
					</form>
				</li>
				
				<li>
					<a href="http://www.baidu.com"><h6>忘记密码？<h6></a>
				</li>
				<li><br><br><br><br><h5>Copyright  ©2018版权所有</h5></li>
			</ul>
		</div>
	</div>
 </body>
</html>
