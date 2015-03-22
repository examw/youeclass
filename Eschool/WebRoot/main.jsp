<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>优异网校</title>
	<META name="ROBOTS" content="All" />
	<META name="Description" content="中华考试网校" />
	<META name="keywords" content="中华考试网校" />
	<link rel="stylesheet" type="text/css" href="/main/css/index.css">
	<script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="artDialog/jquery.artDialog.js?skin=default" type="text/javascript"></script> 
	<script language="javascript">
		var currentindex=1;//flash图片参数
		$(function(){
			//发送ajax请求,加载new.jsp信息
			$("#top").load("/common/head1.jsp?date="+Math.random());
			$("#foot").load("/common/foot1.html");
			$("#main").load("/main/exam.html?time="+Math.random());
			
		});
		function onOver(id){
		    document.getElementById(id).style.display = "none";
		} 
		function onMous(id){
		    document.getElementById(id).style.display = "block";
		}
</script>
</head>
<body>
<div class="top" id="top">
</div>
<div class="main"><h2 style="color: red">住建部电教中心送教上门合作单位      《建造师》杂志战略合作伙伴</h2></div>
<div class="loginStr" id="wxlogbox" style="height:249px;display:none;"></div>
<div class="logingc" id="wxregistbox" style="height:530px;display:none;"></div>
<div class=main>
  <div class="main_box">
    <div class="wrp">
      <div class="banner" id="slide">
        <ul id="picList">
          <li class="slide_li first">
            <div class="imgBg_1"></div>
            <img class="slide_img" src="/images/1010-360.jpg" /> </li>
          <li class="slide_li">
            <div class="imgBg_2"></div>
            <img class="slide_img" src="/images/1010-3602.jpg" /></li>
          <li class="slide_li">
            <div class="imgBg_3"></div>
            <img class="slide_img" src="/images/1010-3603.jpg" /> </li>
          <li class="slide_li">
            <div class="imgBg_4"></div>
            <img class="slide_img" src="/images/1010-3604.jpg" /> </li>
          <li class="slide_li">
            <div class="imgBg_5"></div>
            <img class="slide_img" src="/images/1010-3605.jpg" /> </li>
        </ul>
        <div id="slideThumb"></div>
      </div>
    </div>
  <script type="text/javascript" src="/js/image.js"></script>
  <div class="css"></div>
  <div id="news">
    	<div class="newsZone nz1">
        	<h3></h3>
            <div class="newszlistWrap">
                <ul class="newszlist">
                    <li>
                    lia****nghh购买了<a href="http://member.lxw361.com/GradeType/GradeTypeDetail.aspx?GradeTypeId=3e487007-448d-45fa-a9a8-7ffb7d9fbd45" target="_blank">2014职称英语【协议保障...</a>
                    </li>
                    <li>
                    ddirene购买了<a href="http://member.lxw361.com/GradeType/GradeTypeDetail.aspx?GradeTypeId=074e4b5e-f363-49af-ad8d-9a2846dcc88a" target="_blank">2014年同等学力经济综合考点精讲班</a>
                    </li>
                    <li>
                    yuerer购买了<a href="http://member.lxw361.com/GradeType/GradeTypeDetail.aspx?GradeTypeId=a7abb484-803e-457f-9381-a2ac8c8ac23d" target="_blank">2014年同等学力工商综合经典保过班</a>
                    </li>
                 </ul>
            </div>
        </div>
    	<div class="newsZone nz2">
        	<h3><a href="/News/NewsList.aspx?id=367">MORE</a></h3>
            <div class="newszlistWrap">
                <ul class="newszlist">
			      <li><a target="_blank" href="/news/8187.html" style="color:;"> 2014年一级建造师大型免费直播课程回放入口</a></li>
			      <li><a target="_blank" href="/news/7993.html" style="color:#ff0000;"> 2014年环境影响评价师备考攻略免费直播回放入口</a></li>
			      <li><a target="_blank" href="/news/7882.html" style="color:#ff0000;"> 2014年二级建造师大型免费直播课程回放入口</a></li>
                </ul>
            </div>
        </div>
    	<div class="newsZone nz3">
        	<h3><a href="/News/NewsList.aspx?id=366">MORE</a></h3>
            <div class="newszlistWrap"> 
                <ul class="newszlist">
			      <li><a target="_blank" href="/news/8053.html" style="color:#ff0000;"> ［二级建造师］2010年-2013年真题及答案（汇总）</a></li>
			      <li><a target="_blank" href="/news/7776.html" style="color:;"> ［二级建造师］市政实务精选案例分析题（汇总）</a></li>
			      <li><a target="_blank" href="/news/7675.html" style="color:;"> ［二级建造师］建筑实务经典案例分析题（汇总）</a></li>
                </ul>
            </div>
        </div>
  </div>
  
  <div class="root"> 
  	<a href="/main/main?examId=1011" target="_blank"><img class="index_img" src="/main/images/navlo06.png" /></a>
  	<a href="/main/main?examId=1015" target="_blank"><img class="index_img" src="/main/images/navlo02.png" /></a> 
  	<a href="/main/main?examId=1033" target="_blank" class="rootright"><img  class="index_img" src="/main/images/navlo03.png" /></a>  
  <div style="clear:both;"></div>
  	<a href="/main/main?examId=1047" target="_blank"><img class="index_img" src="/main/images/navlo04.png" /></a>
  	<a href="/main/main?examId=1012" target="_blank"><img class="index_img" src="/main/images/navlo05.png" /></a>
  	<a href="/main/main?examId=1002" target="_blank" class="rootright"><img class="index_img" src="/main/images/navlo01.png" /></a> 
  <div style="clear:both;"></div>
  </div>
</div>
</div>
<div class="main" id="main">
</div>
<div id="foot"></div>
<div  style="float: right;margin-right: 180px"><script src="http://s19.cnzz.com/stat.php? id=5456201&web_id=5456201" language="JavaScript"></script> &nbsp;&nbsp;<script language="javascript" type="text/javascript"  src="http://js.users.51.la/15961317.js"></script><noscript><a href="http://www.51.la/?15961317"  target="_blank"><img  alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"  src="http://img.users.51.la/15961317.asp" style="border:none"  /></a></noscript></div></body>
</html>
