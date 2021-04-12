<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"> 
<title>Js获取 table当前行的值</title> 
<script language=javascript> 
var selectedTr=null; 
function c1(obj){ 
obj.style.backgroundColor='blue'; //把点到的那一行变希望的颜色; 
if(selectedTr!=null) selectedTr.style.removeAttribute("backgroundColor"); 
if(selectedTr==obj) selectedTr=null;//加上此句，以控制点击变白，再点击反灰 
else selectedTr=obj; 
} 
/*得到选中行的第一列的值*/ 
function check(){ 
if(selectedTr!=null){ 
//var str=selectedTr.cells[0].childNodes[0].value+"*********"+selectedTr.cells[1].childNodes[0].value; //值
var str=selectedTr.cells[0].innerHTML+selectedTr.cells[1].innerHTML; //内容
document.getElementById("lab").innerHTML=str; 
} 
else{ 
alert("请选择一行"); 
} 
} 
/*删除选中行*/ 
function del(){ 
if(selectedTr!=null){ 
if(confirm("确定要删除吗?")){ 
alert(selectedTr.cells[0].childNodes[0].value+"*********"+selectedTr.cells[1].childNodes[0].value); 
var tbody=selectedTr.parentNode; 
tbody.removeChild(selectedTr); 
} 
} 
else{ 
alert("请选择一行"); 
} 
}  
</script> 
</head> 
<body> 
<input type=button value="选中的是哪一行？" onclick="check()">
<input type=button value="删除选中行" onclick="del()">
<table border="1" cellspacing="0" cellpadding="0" id="tab"> 
<tr onclick="c1(this);"> 
<td ><input type="text" value="11"></td>
<td ><input type="text" value="12"></td>
</tr> 
<tr onclick="c1(this);"> 
<td ><input type="text" value="21"></td>
<td ><input type="text" value="22"></td> 
</tr> 
<tr onclick="c1(this);"> 
<td ><input type="text" value="31"></td>
<td ><input type="text" value="32"></td> 
</tr> 
<tr onclick="c1(this);"> 
<td ><input type="text" value="41"></td>
<td ><input type="text" value="42"></td> 
</tr> 
<tr onclick="c1(this);"> 
<td ><input type="text" value="51"></td>
<td ><input type="text" value="52"></td> 
</tr> 
</table> 
<label id="lab"></label> 
</body> 
</html>