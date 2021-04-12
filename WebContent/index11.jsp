<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<a>你好啊</a>

<form action="save.do" method="post" enctype="application/x-www-form-urlencoded">
    <input name ="name" type ="text"/>
    <input name="IpAddress" type ="text"/>
    <input name ="MacAddress" type="text"/>

    <input value="添加"type="submit"/>
    

</form>
<form action="query.do" method="post" enctype="application/x-www-form-urlencoded">
    <input type="submit"  value="查询"/>
</form>


 <div style="margin:auto;width:1200px;height:550px;">
        <table id="1" border="1" style="width:1200px;"cellspacing="0">
  
     
    
    </table>
    </div>
</body>
</html>