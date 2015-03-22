<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${news.newTitle}</title>
<meta name="keywords" content="${news.newTitle}" />
<meta name="description" content="${news.newTitle}" />
<link href="/youeclass/css/public.css" rel="stylesheet" type="text/css" />
<link href="/youeclass/css/zixunstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/youeclass/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://cbjs.baidu.com/js/m.js"></script>
<script language="javascript">
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
            <div class="channel"><a target="_blank"  href="${newsexam.newsexam.ename}"><h2>${newsexam.newsexam.cname}</h2></a></div>
            <li class="txt"><a href="/">优异网校</a> > <a href='/${newsexam.ename}/'>${newsexam.cname}</a> > <a href='/${newsexam.newsexam.ename}/'>${newsexam.newsexam.cname}</a> > <a href='/${newsexam.newsexam.ename}/${news.newclass.classEname}/'>${news.newclass.classCname}</a> > </li>
        </ul>
    </div>
</div>
<div id="cbox">
	<div class="daohang">
    <div class="kccon2">
        <ul>
            <li>
                <div class="kcb1">在线课程</div>
                <div class="kcb2">
                    <div style="float:left;">
                        <a href="/main/exam1011.html" target="_blank">一级建造师</a>
						<a href="/main/exam1012.html" target="_blank">二级建造师</a>
						<a href="/main/exam1013.html" target="_blank">监理工程师</a>
						<a href="/main/exam1033.html" target="_blank">安全工程师</a>
						<a href="/main/exam1015.html" target="_blank">造价工程师</a>
						<a href="/main/exam1080.html" target="_blank">消防工程师</a>
						<a href="/main/exam1084.html" target="_blank">二级消防师</a>
						<a href="/main/exam1076.html" target="_blank">全国造价员</a>
                    </div>
                </div>
            </li>
	         <!--   <li>
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
    	<div class="conarea">
            <h1>${news.newTitle}</h1>
            <p class="showinfo">发表时间：${news.addTime} 　编辑：${news.aditor}</p>
            <p class="fontsz">
            	字号：<span id="fontSmall" class="small" onclick="javascript:doZoom(14)" title="切换到小字体">T</span>|<span id="fontBig" class="big" onclick="javascript:doZoom(16)" title="切换到大字体">T</span> 
            </p>
                 <script src="/youeclass/js/article-top.js" type="text/javascript"></script>
		        <div class="showcon" id="contentText">	
				   ${news.newContent}
				</div>
           <!--<div class="listcon" style="border:0">
                <ul class="fenye">
                    <li><a>共2页: </a></li><li><a href='#'>上一页</a></li><li class="thisclass"><a href='#'>1</a></li><li><a href='32176_2.html'>2</a></li><li><a href='32176_2.html'>下一页</a></li>  
                </ul>
            </div>-->
            <div class="share">
            	<!-- Baidu Button BEGIN -->
                <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
				<div class="bianji">责任编辑：${news.aditor}</div>
                    <span class="bds_more">分享到：</span>
                    <a class="bds_qzone"></a>
                    <a class="bds_tsina"></a>
                    <a class="bds_tqq"></a>
                    <a class="bds_renren"></a>
                </div>
				<script type="text/javascript" id="bdshare_js" data="type=tools" ></script>
                <script type="text/javascript" id="bdshell_js"></script>
                <script type="text/javascript">
                    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?t=" + new Date().getHours();
                </script>
                <!-- Baidu Button END -->
            </div>
            <div class="xgarc">
            	<ul>
                	<li class="tle"><b>相关阅读：</b></li>
                	<#if relateList?exists>
                   	<#list relateList as n>
                   		<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" target="_blank">
                   		<#if n.newTitle?length lt 26 >${n.newTitle}<#else> ${n.newTitle[0..25]} </#if></a> <span>${n.addTime}</span></li>
                   	</#list>
                   	</#if>
                </ul>
            </div>
			<!--<div class="xiangguan"><a href="#" target="_blank"><img src="/youeclass/images/tiaojian.gif" />报考条件</a> <a href="#" target="_blank"><img src="/youeclass/images/shijian.gif" />报名时间</a> <a href="/yijian/chengji/" target="_blank"><img src="/youeclass/images/chaxun.gif" />成绩查询</a> <a href="/yijian/shiti/moniti/" target="_blank"><img src="/youeclass/images/mokao.gif" />真题模拟</a> <a href="/yijian//ziliao/20120925/13605.html" target="_blank"><img src="/youeclass/images/zhuanti.gif" />热门专题</a></div>-->
        </div>
<!-- 广告位：一建最新课程选课报名 -->
<script type="text/javascript">BAIDU_CLB_fillSlot("850558");</script>
<!-- 广告位：一建列表的课程试听 -->
<script type="text/javascript">BAIDU_CLB_fillSlot("850553");</script>
	</div>
       	<div class="rightbody">
    	<div class="rtongimg"><!-- 广告位：一建右上角广告 -->
		<script type="text/javascript">BAIDU_CLB_fillSlot("850566");</script></div>
        <div class="rightmenu listtle">最新考试资讯</div>
        <div class="rightcon rightlist1 bgblue">
        <ul><#list kszx as n><li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank">${n.newTitle}</a></li></#list></ul>
        </div>
<!-- 广告位：一建右侧热门搜索及热门推荐 -->
<script type="text/javascript">BAIDU_CLB_fillSlot("850569");</script>
        <div class="rightmenu listtle">最新真题解析</div>
        <div class="rightcon rightlist1">
		<ul><#list lnzt as n>
			<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname?if_exists}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank">${n.newTitle}</a></li>
		</#list></ul>	
        </div>
        <div class="rightmenu listtle">最新模拟试题</div>
        <div class="rightcon rightlist1">
		<ul><#list lnzt as n>
			<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname?if_exists}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank">${n.newTitle}</a></li>
		</#list></ul>
        </div>
        <div class="rightmenu listtle">最新招生简章</div>
        <div class="rightcon rightlist1 bgblue">
        <ul><#list zsjz as n>
        	<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname?if_exists}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank">${n.newTitle}</a></li>
        </#list></ul>
        </div>
    </div>
</div>
<link href="/youeclass/css/footer.css" rel="stylesheet" type="text/css" />
<div class="hezuo">
    	<div class="d_title">
        	<a href="/jzgc/" target='_blank' onMouseOver="hgkcs(0)" id="kca0">建筑工程项目资讯导航</a>
			<!--<a href="/yixue/" target='_blank' onMouseOver="hgkcs(1)" id="kca1" rel="nofollow">硕士类</a>-->
        </div>
        <div class="d_con" id="kcbt0">
              	<li><a href='http://www.youeclass.com/yjjz/' target='_blank'>一级建造师</a> </li> 
              	<li><a href='http://www.youeclass.com/ejjz/' target='_blank'>二级建造师</a> </li> 
              	<li><a href='http://www.youeclass.com/jlgc/' target='_blank'>监理工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/zjgc/' target='_blank'>造价工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/aqgc/' target='_blank'>安全工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/main/exam1076.html' target='_blank'>山西造价员</a> </li> 
              	<li><a href='http://www.youeclass.com/yjxf/' target='_blank'>消防工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/ejxf/' target='_blank'>二级消防师</a> </li> 
		<li><a href='http://wx.kuailekao.com/channel.aspx?domain=zl' target='_blank' rel="nofollow">质量工程师</a> </li> 
              	<li><a href='http://wx.kuailekao.com/channel.aspx?domain=wuye' target='_blank' rel="nofollow">物业管理师</a> </li> 
              	<li><a href='http://wx.kuailekao.com/channel.aspx?domain=zixun' target='_blank' rel="nofollow">咨询工程师</a> </li> 
              	<li><a href='http://wx.kuailekao.com/channel.aspx?domain=zbs' target='_blank' rel="nofollow">招标工程师</a> </li>
		<li><a href='http://lm.jianshe99.com/ManageCheck.asp?adsid=258&UnionID=6125' target='_blank' rel="nofollow">房产估价师</a> </li>
		<li><a href='http://wx.kuailekao.com/channel.aspx?domain=zcyy' target='_blank' rel="nofollow">职称英语</a> </li>
        </div>      
	<!--	 <div class="d_con d_con2" id="kcbt1" style="display:none;">
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
        	<li><a href="http://www.youeclass.com/help/about.html"  target="_blank">关于我们</a> | <a href="" rel="nofollow"  target="_blank">招聘信息</a> | <a  href="" rel="nofollow"  target="_blank">免责声明</a> | <a href=""  rel="nofollow" target="_blank">合作加盟</a> | <a href="http://www.youeclass.com/sitemap.html" target="_blank">网站地图</a> | <a href="/help/"  rel="nofollow" target="_blank">帮助中心</a> | <a href="http://www.youeclass.com/help/about.html#contact"  target="_blank">联系我们</a></li>
                <li>Copyright &copy; 2011-2012 Youeclass.net Inc. All Rights  Reserved.</li>
                <li><a  href="http://www.youeclass.com">优异网校</a>&nbsp;<a href="/jzgc/">建筑网校</a>&nbsp; 版权所有 京ICP备11012638号</li>
		<li> <script src="http://s19.cnzz.com/stat.php? id=5456201&web_id=5456201" language="JavaScript"></script> &nbsp;&nbsp; <script language="javascript" type="text/javascript"  src="http://js.users.51.la/15961317.js"></script><noscript><a href="http://www.51.la/?15961317"  target="_blank"><img  alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"  src="http://img.users.51.la/15961317.asp" style="border:none"  /></a></noscript></li>
        </ul>
    </div>
</div>
<script type="text/javascript" src ="/youeclass/js/common.js"></script>
</body>
</html>