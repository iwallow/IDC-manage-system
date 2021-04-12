<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
			
				var strs= new Array(); //定义一数组 
                strs=reData.split("*"); //字符分割 
              
                for (var i=0;i<strs.length ;i++ ) 
                { 
                	
                	 
                	 document.getElementById((i+1).toString()).innerHTML=strs[i];
                	
                } 
				
				
			}
		};
		
		req.open("post", "que.do", true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("param123=" +number);
		
  	
  }
  function choose(obj){
	  
	  
	  
	  var a =obj.childNodes[0].innerHTML;
	  var b =obj.childNodes[1].innerHTML;
	  window.location.href = "result.jsp?aaa=" + a+"-"+b;
	  
	
	  
  }
  function myrefresh(){
	  
	   window.location.reload();
	   
	   
	   
	}
  function aas(){
	  setInterval('myfun()',3000);
  }
	
  </script>
 </head>
 <body onload="aas()" >
 	<div id='top'>
		<div id='title'>
			<h2><strong>IP地址资源管理系统</strong></h2>
		</div>
		<ul class="layui-nav layui-bg-cyan">
			<li class="layui-nav-item" lay-unselect="">
				<a href="javascript:;"><img src="http://t.cn/RCzsdCq" class="layui-nav-img">欢迎您，admin</a>
				<dl class="layui-nav-child">
					<dd><a href="javascript:;">修改信息</a></dd>
					<dd><a href="javascript:;">安全管理</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="">退出</a>
			</li>
		</ul>
	</div>
 <div id='content'>
	<div id='c_left'>
		<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;">
			<li class="layui-nav-item layui-nav-itemed">
				<a href="javascript:;">地址管理</a>
				<dl class="layui-nav-child">
					<dd><a href="javascript:;">地址使用列表</a></dd>
					<dd><a href="javascript:;">地址汇总信息</a></dd>
					<dd><a href="javascript:;">IP地址回收</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="javascript:;">交换机管理</a>
				<dl class="layui-nav-child">
					<dd><a href="">端口实时列表</a></dd>
					<dd><a href="">可用端口查询</a></dd>
					<dd><a href="">交换机端口分配</a></dd>
					<dd><a href="">端口使用历史</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="javascript:;">映射管理</a>
				<dl class="layui-nav-child">
					<dd><a href=""></a></dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="javascript:;">网关管理</a>
				<dl class="layui-nav-child">
					<dd><a href=""></a></dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="result.jsp">终端管理</a>
				<dl class="layui-nav-child">
					<dd><a href=""></a></dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="javascript:;">违规告警</a>
				<dl class="layui-nav-child">
					<dd><a href=""></a></dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="javascript:;">历史记录</a>
			</li>
			<li class="layui-nav-item">
				<a href="javascript:;">系统管理</a>
			</li>
		</ul>
	</div>

	<div id='c_right'>
		  <div id='data'>
		  	<h3>IP地址使用列表</h3>
			  <div id='an'> <button onclick="notadd()" class="layui-btn">上一页 </button>
			   <button onclick="point()" class="layui-btn">下一页</button>
			  
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
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                    </tr>
					    <tr  id="1" onclick="choose(this)">
					         <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                    </tr>
	                    <tr  id="2" onclick="choose(this)">
					          <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
	                        <tr  id="3" onclick="choose(this)">
					         <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
	                        <tr id="4" onclick="choose(this)">
					         <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
	                        <tr  id="5" onclick="choose(this)">
					          <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
	                        <tr  id="6" onclick="choose(this)">
					         <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
	                        <tr  id="7" onclick="choose(this)">
					         <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
	                        <tr  id="8" onclick="choose(this)">
					          <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
	                        <tr  id="9" onclick="choose(this)">
					          <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
	                        <tr  id="10" onclick="choose(this)">
					          <th>IP地址</th> 
						       <th>Mac地址</th>
	                           <th>子网掩码 </th>
	                           <th>IP地址 </th>
	                           <th>IP地址 </th>
	                        </tr>
						
					</table>
				</div>
		  </div>
		  <div id='bottom'>
		  <br>
			<h5>copyrightÂ© 2018    IP地址资源管理系统</h5>  
		  </div>
	</div>
 </div>
	<script src="layui/layui.js" charset="utf-8"></script>
	<script>
		layui.use('element', function(){
			var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  
			//监听导航点击
		element.on('nav(demo)', function(elem){
			//console.log(elem)
			layer.msg(elem.text());
		});
		});
	</script>
 </body>
</html>
