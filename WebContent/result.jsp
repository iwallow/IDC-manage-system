<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%String aa= new String(request.getParameter("aaa").getBytes("ISO-8859-1"),"utf-8"); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
  <title>IP地址资源管理系统</title>
  <style>
  *{
	margin:0;
	padding:0;
  }
  #content{
  	display:flex;
  }
  #c_left{								   	
	width:200px;
	height:693px;
	background-color:#393D49;
  }
  #c_right{
	width:1337px;
	height:693px;				  
  }
  #top{
  	display:flex;
	justify-content:space-between;
	background-color:#23262F;
  }
  #data{
	width:100%;
	height:90%;
  }
  #bottom{
  	width:100%;
	height:10%;
	background-color:#EEEEEE;
  }
  h2{
  	font-family:"Microsoft YaHei";
	font-size:25px;
  	color:#F2F2F2;
	letter-spacing:4px;
  }
  h3{
	padding:30px 30px;
	letter-spacing:2px;
	font-size:22px;
	border-bottom:solid;
  }
  #title{
	margin:14px 20px;
  }
  #an{
  	text-align:right;
	margin:20px 40px;
  }
  h5{
	text-align:center;
  }
  </style>
  <script type="text/javascript">
  var ss =aa;
  var allnum=0;
  var number=0;
 
  var i=1;

  function point(){
	     if(number+10>=allnum){
			 alert("当前是最后一页");
		 }else{
			 number=number+10;
	  		 myfun();	
		 }
	     
  		
  }
  function notadd(){
  	if(number==0){
  		alert("当前是第一页");
  	}else{
  		
  		 number=number-10;
  		 myfun();
  	}
  
  }
  function queryallnum(){
	  
	  
		 
	  var req = new XMLHttpRequest();
	
	  
		req.onreadystatechange = function() {
			if (req.readyState == 4) {
				
				var reData = req.responseText;	//responseText 响应的文本
				
				allnum=reData;
				
			}
		};
		
		req.open("post", "queryallnum.do", true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("param123=" +number);
  }
	  
  
  function myfun()
  {
	  queryallnum();
	  var properties = document.getElementById("123");
	  
	  var req = new XMLHttpRequest();
	  
	  
		req.onreadystatechange = function() {
			if (req.readyState == 4) {
				
				var reData = req.responseText;	//responseText 响应的文本
				
				properties.innerHTML =reData;
				
			}
		};
		
		req.open("post", "getresult.do", true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("param123=" +number);
		
  	
  }
  function myrefresh(){
	  
	   window.location.reload();
	   
	   
	   
	}
  function aas(){
	  setInterval('xx()',1000);
  }
	function xx(){
		var jsStr = "<%=aa.replace("\"", "\\\"").replace("</script>", "<\"+\"/script>").replace("\r", "").replace("\n", "\\\n")%>";
		var req = new XMLHttpRequest();
		  
		 var properties = document.getElementById("123");
		req.onreadystatechange = function() {
			if (req.readyState == 4) {
				
                var reData = req.responseText;	//responseText 响应的文本
				
				properties.innerHTML =reData;
				
			}
		};
		
		req.open("post", "getresult.do", true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("param123=" +jsStr);
		
		 
	}
  </script>
 </head>
 <body onload="aas()" >
 	
		  <div id='data'>
		  	<h3>流量监控表</h3>
			  
			  
			   	</div> 
			 
				<div class="layui-form">
					<table id="123" class="layui-table">
						<colgroup>
							<col width="200">
							<col width="200">
							<col width="200">
							<col width="200">
							<col width="200">
						</colgroup>
					       <tr>
					          <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>10s内平均流量 </th>
	                           <th>10s内接收数据包 </th>
	                           <th>10s内发送数据包 </th>
	                        </tr>
	                      
						
					</table>
				</div>
		  </div>
		 
 </body>
</html>
