<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${newsexam.newsexam.cname}${newclass.classCname}_优异网校</title>
<meta name="keywords" content="${newsexam.newsexam.cname}${newclass.classCname}" />
<meta name="description" content="优异网校${newsexam.newsexam.cname}${newclass.classCname}。" />
<link href="/youeclass/css/public.css" rel="stylesheet" type="text/css" />
<link href="/youeclass/css/zixunstyle.css" rel="stylesheet" type="text/css" />
<link href="/youeclass/css/Pager.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/youeclass/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/youeclass/js/jquery.pager.js"></script>
<script type="text/javascript" src="http://cbjs.baidu.com/js/m.js"></script>
<script language="javascript">
$(document).ready(function() {
            $("#pager").pager({ pagenumber: ${current}, pagecount: ${total}, buttonClickCallback: PageClick });
        });
        PageClick = function(pageclickednumber) {
            $("#pager").pager({ pagenumber: pageclickednumber, pagecount: ${total}, buttonClickCallback: PageClick });
            if(pageclickednumber != 1)
            	location="./index"+pageclickednumber+".html";
            else
            	location="./index.html";
        }
function doZoom(size){	
	jQuery("#contentText").css("fontSize",size);
}
</script>
</head>
<body>
<div id="cbox">
	<div class="topnav">
        <div class="topnavleft">
        	<a href="/" class="index">优异网校首页</a> | 
            <a href="/jzgc/">建筑工程</a> | 
            <a href="/zyss/">专业硕士</a> |
        </div>
        <div id="top_loginarea" class="topnavright">
		<ul id="top_loginarea"></ul>
	   </div>
	</div>
    <!-- 广告位：一建顶部 -->
<script type="text/javascript">BAIDU_CLB_fillSlot("850535");</script>
    <div class="daohang">
    	<ul>
        	<li><a href="http://www.youeclass.com/" target="_blank"><img src="/youeclass/images/logo.png" height=52 alt="优异网校" /></a></li>
			<div class="channel"><a target="_blank"  href="/${newsexam.newsexam.ename}/"><h2>${newsexam.newsexam.cname}</h2></a></div>
            <li class="txt"><a href="http://www.youeclass.com/">优异网校</a> > <a href='/${newsexam.ename}/'>${newsexam.cname}</a> > <a href='/${newsexam.newsexam.ename}/'>${newsexam.newsexam.cname}</a> > <a href='/${newsexam.newsexam.ename}/${newclass.classEname}/'>${newclass.classCname}</a> > </li>
        </ul>

    </div>

</div>

<div id="cbox">
	<div class="daohang">
    <div class="kccon2">
        <ul>
            <li>
                <div class="kcb1"><a href="/jzgc/" target="_blank">建筑工程</a></div>
                <div class="kcb2">
                    <div style="float:left;">
                        <a href="/main/exam1011.html">一级建造师</a>
						<a href="/main/exam1012.html">二级建造师</a>
						<a href="/main/exam1013.html">监理工程师</a>
						<a href="/main/exam1033.html">安全工程师</a>
						<a href="/main/exam1015.html">造价工程师</a>
						<a href="">咨询工程师</a>
						<a href="/main/exam1002.html">全国造价员</a>
                    </div>
                </div>
            </li>
			<!-- <li>
                <div class="kcb1"><a href="/zyss/" target="_blank">专业硕士</a></div>
                <div class="kcb2">
                    <div style="float:left;">
                        <a href="/main/exam1011.html">MBA</a>
						<a href="/main/exam1012.html">MPA</a>
						<a href="/main/exam1013.html">GCT</a>
                    </div>
                </div>
            </li>-->
        </ul>
    </div>
	</div>
	<div class="leftbody">
	<!-- 广告位：一建列表的课程试听 -->
	<script type="text/javascript">BAIDU_CLB_fillSlot("850553");</script>
	<#if newList?exists&&(newList?size>0) > 
	<div class="listtle">${newsexam.newsexam.cname}${newclass.classCname}</div>
    <div class="listcon">
        	<ul>
            	<li></li>
            	<#list newList as n><li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank">${n.newTitle}</a><span>${n.addTime?date}</span></li></#list>
                <li></li>
			</ul>
	</div>
	<div style="clear:both"></div>
	<div id="pager" style="margin-left:10px" ></div>		
	</#if>
	<!-- 广告位：一建最新课程选课报名 -->
	<script type="text/javascript">BAIDU_CLB_fillSlot("850558");</script>
    </div>
       	<div class="rightbody">
    	<div class="rtongimg"><!-- 广告位：一建右上角广告 -->
		<script type="text/javascript">BAIDU_CLB_fillSlot("850566");</script></div>
        <div class="rightmenu listtle">最新考试资讯</div>
        <div class="rightcon rightlist1 bgblue">
        <ul>
        <#list news as b><li>·<a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]} </#if></a></li>	</#list>
        </ul>
        </div>
        <div class="rightmenu listtle">最新备考资料</div>
        <div class="rightcon rightlist1">
     	<ul>
        <#list ksfd as b><li>·<a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]} </#if></a></li>	</#list>
        </ul>
        </div>
        <div class="rightmenu listtle">最新招生简章</div>
        <div class="rightcon rightlist1 bgblue">
      	<ul>
        <#list zsjz as b><li>·<a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]} </#if></a></li>	</#list>
        </ul>
        </div>
    </div>
</div>
<link href="/youeclass/css/footer.css" rel="stylesheet" type="text/css" />
<div class="hezuo">
    	<div class="d_title">
        	<a href="/jianzhu/" target='_blank' onMouseOver="hgkcs(0)" id="kca0">建筑类</a>
	<!--	<a href="/yixue/" target='_blank' onMouseOver="hgkcs(1)" id="kca1">硕士类</a>-->
        </div>
        <div class="d_con" id="kcbt0">
                <li><a href='http://www.youeclass.com/yjjz/' target='_blank'>一级建造师</a> </li> 
              	<li><a href='http://www.youeclass.com/ejjz/' target='_blank'>二级建造师</a> </li> 
              	<li><a href='http://www.youeclass.com/jlgc/' target='_blank'>监理工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/zjgc/' target='_blank'>造价工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/aqgc/' target='_blank'>安全工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/main/exam1076.html' target='_blank'>山西造价员</a> </li> 
              	<li><a href='#' target='_blank'>消防工程师</a> </li> 
              	<li><a href='http://www.youeclass.com' target='_blank'>优异网校</a> </li> 
        </div>      
		<!-- <div class="d_con d_con2" id="kcbt1" style="display:none;">
            <li><a href='/yixue/linchuang/' target='_blank'>MBA</a> </li>
			<li><a href='/yixue/lczl/' target='_blank'>GCT</a> </li>	
        </div>-->
    </div>
	<script language="javascript">
	function hgkcs(x){
	  for(i=0; i<9; i++){
		 document.getElementById("kcbt"+i).style.display="none";
		 document.getElementById("kcbt"+x).style.display="block";	
		 document.getElementById("kca"+i).style.color="#333333";
		 document.getElementById("kca"+x).style.color="#38a2db";					 
		 }
	 }	
	</script>  
<div id="cbox">
	<div class="bottom">
    	<ul>
        	<li><a href="http://www.youeclass.com/help/about.html" rel="nofollow" target="_blank">关于我们</a> | <a href="#" rel="nofollow"  target="_blank">招聘信息</a> | <a  href="#" rel="nofollow"  target="_blank">免责声明</a> | <a href="#"  rel="nofollow" target="_blank">合作加盟</a> | <a href="#" rel="nofollow" target="_blank">媒体关注</a> | <a href="/help/"  rel="nofollow" target="_blank">帮助中心</a> | <a href="http://www.youeclass.com/help/about.html#contact" rel="nofollow"  target="_blank">联系我们</a></li>
            <li>Copyright &copy; 2011-2012 Youeclass.net Inc. All Rights  Reserved.</li>
            <li><a rel="nofollow" href="http://www.youeclass.com">优异网校</a>&nbsp;<a href="http://www.youeclass.com/jzgc/">建筑工程网校</a>&nbsp; 版权所有 京ICP备11012638号</li>
			<li> <script src="http://s19.cnzz.com/stat.php? id=5456201&web_id=5456201" language="JavaScript"></script> &nbsp;&nbsp; <script language="javascript" type="text/javascript"  src="http://js.users.51.la/15961317.js"></script><noscript><a href="http://www.51.la/?15961317"  target="_blank"><img  alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"  src="http://img.users.51.la/15961317.asp" style="border:none"  /></a></noscript></li>
        </ul>
    </div>
</div>
<script type="text/javascript" src ="/youeclass/js/common.js"></script>
</body>
</html>
