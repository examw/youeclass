<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一级建造师考试:2014年一级建造师报名时间、报考条件、考试时间、考试科目、考试大纲、考试真题、模拟试题、成绩查询_优异网校</title>
<meta name="keywords" content="一级建造师,一级建造师考试,建造师,一级建造师考试科目,一级建造师考试时间,一级建造师报名时间,一级建造师报考条件" />
<meta name="description" content="2014年一级建造师报名时间、报考条件、考试时间、考试科目、考试大纲、考试真题、模拟试题、成绩查询" />
<link href="/youeclass/css/style_jianzhu.css" rel="stylesheet" type="text/css" />
<link href="/youeclass/css/style_add.css" rel="stylesheet" type="text/css" />
<script  type="text/javascript" src="/youeclass/js/style.js"></script>
<link href="/youeclass/css/zixunstyle.css" rel="stylesheet" type="text/css" />
<script src="/youeclass/js/jquery-jzgc.js" type="text/javascript"></script>

<script language="javascript">
  function switchmodTag(modtags,modcontents,modks,mstars,mends,css01,css02,bcss) {
    for(is=mstars; is <=mends; is++) {
      if (is==modks) {
        document.getElementById(modtags+is).className=(css01);document.getElementById(modcontents+is).className=(bcss);}
      else {
		document.getElementById(modtags+is).className=(css02);document.getElementById(modcontents+is).className="shownones";}
    }
  }
</script>
<SCRIPT type=text/javascript>
var $ = function (id) {
	return "string" == typeof id ? document.getElementById(id) : id;
};

var Extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
	return destination;
}

var CurrentStyle = function(element){
	return element.currentStyle || document.defaultView.getComputedStyle(element, null);
}

var Bind = function(object, fun) {
	var args = Array.prototype.slice.call(arguments).slice(2);
	return function() {
		return fun.apply(object, args.concat(Array.prototype.slice.call(arguments)));
	}
}

var Tween = {
	Quart: {
		easeOut: function(t,b,c,d){
			return -c * ((t=t/d-1)*t*t*t - 1) + b;
		}
	},
	Back: {
		easeOut: function(t,b,c,d,s){
			if (s == undefined) s = 1.70158;
			return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
		}
	},
	Bounce: {
		easeOut: function(t,b,c,d){
			if ((t/=d) < (1/2.75)) {
				return c*(7.5625*t*t) + b;
			} else if (t < (2/2.75)) {
				return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
			} else if (t < (2.5/2.75)) {
				return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
			} else {
				return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
			}
		}
	}
}


//容器对象,滑动对象,切换数量
var SlideTrans = function(container, slider, count, options) {
	this._slider = $(slider);
	this._container = $(container);//容器对象
	this._timer = null;//定时器
	this._count = Math.abs(count);//切换数量
	this._target = 0;//目标值
	this._t = this._b = this._c = 0;//tween参数
	
	this.Index = 0;//当前索引
	
	this.SetOptions(options);
	
	this.Auto = !!this.options.Auto;
	this.Duration = Math.abs(this.options.Duration);
	this.Time = Math.abs(this.options.Time);
	this.Pause = Math.abs(this.options.Pause);
	this.Tween = this.options.Tween;
	this.onStart = this.options.onStart;
	this.onFinish = this.options.onFinish;
	
	var bVertical = !!this.options.Vertical;
	this._css = bVertical ? "top" : "left";//方向
	
	//样式设置
	var p = CurrentStyle(this._container).position;
	p == "relative" || p == "absolute" || (this._container.style.position = "relative");
	this._container.style.overflow = "hidden";
	this._slider.style.position = "absolute";
	
	this.Change = this.options.Change ? this.options.Change :
		this._slider[bVertical ? "offsetHeight" : "offsetWidth"] / this._count;
};
SlideTrans.prototype = {
  //设置默认属性
  SetOptions: function(options) {
	this.options = {//默认值
		Vertical:	true,//是否垂直方向（方向不能改）
		Auto:		true,//是否自动
		Change:		0,//改变量
		Duration:	50,//滑动持续时间
		Time:		5,//滑动延时
		Pause:		2000,//停顿时间(Auto为true时有效)
		onStart:	function(){},//开始转换时执行
		onFinish:	function(){},//完成转换时执行
		Tween:		Tween.Quart.easeOut//tween算子
	};
	Extend(this.options, options || {});
  },
  //开始切换
  Run: function(index) {
	//修正index
	index == undefined && (index = this.Index);
	index < 0 && (index = this._count - 1) || index >= this._count && (index = 0);
	//设置参数
	this._target = -Math.abs(this.Change) * (this.Index = index);

	this._t = 0;
	this._b = parseInt(CurrentStyle(this._slider)[this.options.Vertical ? "top" : "left"]);
	this._c = this._target - this._b;
	
	this.onStart();
	this.Move();
  },
  //移动
  Move: function() {
	clearTimeout(this._timer);
	//未到达目标继续移动否则进行下一次滑动
	if (this._c && this._t < this.Duration) {
		this.MoveTo(Math.round(this.Tween(this._t++, this._b, this._c, this.Duration)));
		this._timer = setTimeout(Bind(this, this.Move), this.Time);
	}else{
		this.MoveTo(this._target);
		this.Auto && (this._timer = setTimeout(Bind(this, this.Next), this.Pause));
	}
  },
  //移动到
  MoveTo: function(i) {
	this._slider.style[this._css] = i + "px";
  },
  //下一个
  Next: function() {
	this.Run(++this.Index);
  },
  //上一个
  Previous: function() {
	this.Run(--this.Index);
  },
  //停止
  Stop: function() {
	clearTimeout(this._timer); this.MoveTo(this._target);
  }
};
</SCRIPT>
<style type="text/css" >
	.d_width{float:left;width:79px;overflow:hidden;}
	.a_left{float:left}
</style>
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
    <div class="logos">
    	<div class="logo"><a target="_blank"  href="/"><img src="/youeclass/images/logo.png" alt="优异网校" /></a></div>
        <div class="pindao">${exam.examName}</div>
        <div class="fenlei">
        	<dl>
            	<dt><a href="http://www.youeclass.com/main/exam1011.html" target="_blank"><img src="/youeclass/images/fl1.png" alt="网络辅导课程" /></a></dt>
                <dd><a  href="http://www.youeclass.com/main/exam1011.html" target="_blank">网络课程</a></dd>
            </dl>
            <dl>
            	<dt><a target="_blank"  href="http://www.youeclass.com/yjjz/quanguo/bmsj/3455/"><img src="/youeclass/images/fl2.png" alt="考试报名" /></a></dt>
                <dd><a target="_blank"  href="http://www.youeclass.com/yjjz/quanguo/bmsj/3455/">考试报名</a></dd>
            </dl>
            <dl>
            	<dt><a   href="http://www.youeclass.com/yjjz/quanguo/zsjz/5190/" target="_blank"><img src="/youeclass/images/fl3.png" alt="招生简章" /></a></dt>
                <dd><a  href="http://www.youeclass.com/yjjz/quanguo/zsjz/5190/" target="_blank">招生简章</a></dd>
            </dl>
            <dl>
            	<dt><a href="http://www.youeclass.com/yjjz/lnzt/" target="_blank"><img src="/youeclass/images/fl5.png" /></a></dt>
                <dd><a href="http://www.youeclass.com/yjjz/lnzt/" target="_blank">历年真题</a></dd>
            </dl>
            <dl>
            	<dt><a target="_blank"  href="http://www.youeclass.com/yjjz/fdzl/"><img src="/youeclass/images/fl4.png" /></a></dt>
                <dd><a target="_blank" href="http://www.youeclass.com/yjjz/fdzl/">辅导资料</a></dd>
            </dl>
        </div>
    </div>   
	  <div class="nav">    
    	<div class="navleft"><img src="/youeclass/images/zx0.png" /></div>
    	<div class="navcenter">        	
            <div class="zixun"><img src="/youeclass/images/ks.png" /></div>  
            <div class="zixuncon">
            	<#list examList as ec>
			     <a target="_blank" style="display:inline-block" href='/${exam.examEname}/${ec.examEname}'  rel='dropmenu1'>${ec.examName}</a> 
			    </#list>
            </div>
            <div class="zixun2"><img src="/youeclass/images/zx2.png" /></div>
            <div class="zixun"><img src="/youeclass/images/ws.png" /></div>  
            <div class="zixuncon">
				 <a target="_blank" href='http://www.youeclass.com/yjjz/quanguo/kszx/3435/' >考试时间</a> 
				 <a target="_blank"  href='http://www.youeclass.com/yjjz/bmsj/' >报名时间</a> 
				 <a target="_blank"  href='http://www.youeclass.com/yjjz/zkzdy/' >准考证打印</a> <br />
				 <a target="_blank"  href='http://www.youeclass.com/yjjz/cjcx/' >成绩查询</a> 
				 <a target="_blank"  href='http://www.youeclass.com/yjjz/zslq/' >证书领取</a> 	
				 <a target="_blank"  href='' >注册公告</a> 
            </div>
            <div class="zixun2"><img src="/youeclass/images/zx2.png" /></div>
            <div class="zixun"><img src="/youeclass/images/zx4.png" /></div>  
            <div class="zixuncon">
				 <a target="_blank"  href='http://www.youeclass.com/yjjz/fdzl/' >辅导资料</a> 
				 <a target="_blank"  href='http://www.youeclass.com/yjjz/lnzt/' >历年真题</a> <br />
				 <a target="_blank"  href='http://www.youeclass.com/yjjz/mnst/' >模拟试题</a> 
				 <a target="_blank"  href='http://www.youeclass.com/yjjz/fdzl/' >复习资料</a>
            </div>
            <div class="zixun2"><img src="/youeclass/images/zx2.png" /></div>
            <div class="zixun"><img src="/youeclass/images/zx5.png" /></div>  
            <div class="zixuncon">
                <a  href="http://www.youeclass.com/main/exam1011.html" target="_blank">课程</a>
                <!-- <a href="http://www.youeclass.com/main/exam1011.html" target="_blank">试听</a> --><br />
				<a target="_blank"  href="http://www.youeclass.com/yjjz/"> 资讯  </a>
                <!-- <a target="_blank"  href="http://www.youeclass.com/main/exam1011.html" target="_blank">选课</a> -->
 
            </div>  
            <div class="zixun2"><img src="/youeclass/images/zx2.png" /></div>          
        </div>
        <div class="navright"><img src="/youeclass/images/zx6.png" /></div>    
    </div>
	
    <div class="navx">
		<h1 style=" float:left;font-size:15px; ">热门课程:</h1>
   			 <a href="http://www.youeclass.com/main/exam1011.html" target="_blank">&nbsp;&nbsp;2014年一级建造师四科全程VIP保过4000元/4科！包含基础、强化、习题、专题和考点预测、记忆课程的全程阶段的课程！</a>
    </div>
	<script type="text/javascript">
function tag(obj){
return document.getElementById(obj);
}
window.onload=function(){
setTimeout("slideUp();",5000);
}
function slideUp(){
if(tag("testnew").offsetHeight>0){
if(tag("testnew").offsetHeight>10){
tag("testnew").style.height=tag("testnew").offsetHeight-10+"px"
setTimeout("slideUp();",5);
}else{
tag("testnew").style.display="none";
tag("testimg").src="/youeclass/images/32-14010G45U1157.jpg";
tag("testnew").style.display="block";
slideDown();
}
}
}
function slideDown(){
if(tag("testnew").offsetHeight<80){
if(tag("testnew").offsetHeight<70){
tag("testnew").style.height=tag("testnew").offsetHeight+10+"px";
setTimeout("slideDown();",8);
}else{
tag("testnew").style.height="90px";
tag("testimg").src="/youeclass/images/32-14010G45934635.jpg";
}
}
}
</script>

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
			<a href="/main/exam1080.html">消防工程师</a>
			<a href="/main/exam1076.html">全国造价员</a>
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
<!--<div align="center" id="testnew" style="overflow:hidden; float:left; margin-top:-3px; margin-bottom:5px;"><a href="/jianzhu/zhuanti/ejzb/" style="cursor:pointer" onclick="/jianzhu/zhuanti/ejzb/" target="_blank"><img src="/youeclass/images/32-14010G45U1157.jpg" name="testimg" border="0" alt="优异网校二级建造师" id="testimg"></a></div>-->
<!--<div style="width:960px;margin:5px auto; display:block;clear:both;"><a href="/main/exam1011.html" target="_blank"><img src="http://ubmcmm.baidustatic.com/media/v1/0f0002kBwMipBQ401j8cYs.jpg"></div>-->
    <div class="mains1">
    	<div class="ms1_left">
        	<div id=idContainer2 class=container>
                        <table id=idSlider2 border=0 cellSpacing=0 cellPadding=0>
                            <tbody>
                              <tr>
				<td class=td_f> <a href="/main/exam1011.html" target="_blank"><img src='/youeclass/images/29-131203142Z0O9.jpg' border='0' width='120' height='120' alt='2014年一级建造师全程协议保过班'></a></td>
				<td class=td_f> <a href="http://www.youeclass.com/yjjz/quanguo/bmsj/3455/" target="_blank"><img src='/youeclass/images/201406142.jpg' border='0' width='120' height='120' alt='2014年一级建造师报名时间汇总'></a></td>
                               </tr>
                            </tbody>
                        </table>
                        <UL id=idNum class=num></UL>
                    </DIV>
                        
                        <SCRIPT>
                            var forEach = function(array, callback, thisObject){
                                if(array.forEach){
                                    array.forEach(callback, thisObject);
                                }else{
                                    for (var i = 0, len = array.length; i < len; i++) { callback.call(thisObject, array[i], i, array); }
                                }
                            }
                            var st = new SlideTrans("idContainer2", "idSlider2", 2, { Vertical: false });
                            
                            var nums = [];
                            //插入数字
                            for(var i = 0, n = st._count - 1; i <= n;){
                                (nums[i] = $("idNum").appendChild(document.createElement("li"))).innerHTML = ++i;
                            }
                            
                            forEach(nums, function(o, i){
                                o.onmouseover = function(){ o.className = "on"; st.Auto = false; st.Run(i); }
                                o.onmouseout = function(){ o.className = ""; st.Auto = true; st.Run(); }
                            })
                            
                            //设置按钮样式
                            st.onStart = function(){
                                forEach(nums, function(o, i){ o.className = st.Index == i ? "on" : ""; })
                            }
                            st.Run();
                        </SCRIPT>
        </div>
        <div class="ms1_center">
        	<ul class="toutiaos">
            	<li class="toutiao">
		    <a href="http://www.youeclass.com/main/exam1011.html" target="_blank">2014年一建辅导方案</a>
		    <a href="http://www.youeclass.com/yjjz/quanguo/bmsj/3455/" target="_blank">2014年一建报名时间汇总</a>
		</li>
            	<li>
		    <a style="color:#999999; " class="d_width" href="http://www.youeclass.com/yjjz/quanguo/bmsj/3455/" target="_blank"><center>网上报名</center></a>
			<a class="hui a_left"> | </a>
		    <a style="color:#999999; " class="d_width" href="http://www.youeclass.com/yjjz/quanguo/zkzdy/5212/" target="_blank"><center>准考证打印</center></a>
			<a class="hui a_left"> | </a>
		    <a style="color:#999999; " class="d_width" href="http://www.youeclass.com/yjjz/lnzt/" target="_blank"><center>真题解析</center></a>
			<a class="hui a_left"> | </a>
		    <a style="color:#999999; " class="d_width" href="http://www.youeclass.com/yjjz/quanguo/bkzn/5290/" target="_blank"><center>专业对照表</center></a>
			<a class="hui a_left"></a>
<br />
						
		    <a style="color:#333333;" class="d_width" href="http://www.youeclass.com/main/exam1011.html" target="_blank"><center>2014备考视频</center></a>
			<a class="hui a_left"> | </a>
		    <a style="color:#333333;" class="d_width" href="http://www.youeclass.com/yjjz/mnst/" target="_blank"><center>2014模考试题</center></a>
			 <a class="hui a_left"> | </a>
		    <a style="color:#333333;" class="d_width" href="http://www.youeclass.com/yjjz/kszx/" target="_blank"><center>2014考试资讯</center></a>
			<a class="hui a_left"> | </a>
		    <a style="color:#333333;" class="d_width" href="http://www.youeclass.com/yjjz/bkzn/" target="_blank"><center>报考指南</center></a>
			<a class="hui a_left"></a>
                </li>            	
            </ul>
            <ul class="toutiao2">
            	<li class="toutiao">
		    <a href="http://www.youeclass.com/yjjz/quanguo/cjcx/4605/" target="_blank">历年一建考试合格标准</a>
		    <a href="http://www.youeclass.com/yjjz/lnzt/" target="_blank">往年一建真题解析汇总</a>
		</li>
            	<li>
		    <a class="hui" href="http://www.youeclass.com/main/exam1011.html" target="_blank">2014年一建招生简章(保过班)</a>
			<a class="hui"> | </a>
		    <a class="hui" href="http://www.youeclass.com/yjjz/quanguo/kszx/3435/" target="_blank">2014年一建考试时间</a>
			<a class="hui"></a>
<br />
		    <a class="hui" href="http://www.youeclass.com/yjjz/fdzl/" target="_blank">2014年一级建造师辅导资料</a>
			<a class="hui"> | </a>
		    <a class="hui" href="http://www.youeclass.com/yjjz/kszx/" target="_blank">一级建造师最新考试资讯</a>
			<a class="hui"></a>
                </li>
                <li style="clear:both;">
		    <a href="http://www.youeclass.com/main/exam1011.html" target="_blank"><img src="/youeclass/images/st.png" />一建押题名师2014备考讲座</a>
			<a class="hui"> | </a>
		    <a href="http://www.youeclass.com/main/gradeAudition?gradeId=1064" target="_blank"><img src="/youeclass/images/st.png" />一级建造师记忆大师公开课</a>
			<a class="hui"></a>
<br />
		    <a href="http://www.youeclass.com/main/gradeAudition?gradeId=1038" target="_blank"><img src="/youeclass/images/st.png" />实务案例《网络图》专题讲解</a>
			<a class="hui"> | </a>
		    <a href="http://www.youeclass.com/main/gradeAudition?gradeId=1006" target="_blank"><img src="/youeclass/images/st.png" />2014一建法律法规精品课</a>
			<a class="hui"></a>
                </li>            	
            </ul>
        </div>
        <div class="ms1_right">
        	<div class="title1"><a href="http://www.youeclass.com/main/exam1011.html" id="kc0" onmouseover="hgkc(0)">热门课程</a><a target="_blank" href="http://www.youeclass.com/yjjz/jwtz/" id="kc1" onmouseover="hgkc(1)">教务公告</a><a target="_blank" href="http://www.youeclass.com/yjjz/bkzn/" id="kc2" onmouseover="hgkc(2)">报考指南</a></div>
            <div class="title1_con" id="kchg0">
            	<ul>
		     <li><a href="http://www.youeclass.com/main/exam1011.html" title="" target="_blank">2014年一级建造师全程豪华班</a></li>
		     <li><a href="http://www.youeclass.com/main/exam1033.html" title="" target="_blank">2014年安全工程师全程精品班</a></li>
		     <li><a href="http://www.youeclass.com/main/exam1015.html" title="" target="_blank">2014年造价工程师VIP保过班</a></li>
		     <li><a href="" title="" target="_blank">2014年招标师精品保过班</a></li>
		     <li><a href="http://www.youeclass.com/main/exam1013.html" title="" target="_blank">2015年监理工程师VIP保过班</a></li>
		     <li><a href="http://www.youeclass.com/main/exam1012.html" title="" target="_blank">2015年二级建造师VIP保过班</a></li>
		     <li><a href="" title="" target="_blank">2014年消防工程师首年拿证班</a></li>
		     <li><a href="http://www.youeclass.com/main/exam1011.html" title="2014年一级建造师VIP保过班4000元/全科" target="_blank">2014年一级建造师VIP保过班 4000元/全科</a></li>
                </ul>
            </div>
            <div class="title1_con" id="kchg1" style="display:none;">
            	<ul>
            	<#list jwgg as b>
		   		 <li><a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]} </#if></a></li>	
		    	</#list> 
            	</ul>
            </div>
	                <div class="title1_con" id="kchg2" style="display:none;">
            	<ul>
            	<#list jwgg as b>
		   		 <li><a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]} </#if></a></li>	
		    	</#list> 
            	</ul>
            </div>

        </div>
    </div>
    <div class="mains2">
    	<div class="ms2_left">
        	<div class="ms2_left_title">课程体验</div>
            <div class="ms2_left_con">
            	<dl>
				 <dt><a href="http://www.youeclass.com/main/exam1011.html" target="_blank"><img src='http://www.youeclass.com/SCCMS/upload/teacherface/20140607091418_.jpg' border='0' width='120' height='67' alt='2014年一建复习备考高清试听 陈印'></a></dt>
                    <dd>
                    	<ul>
			    <li><a href="http://www.youeclass.com/main/gradeAudition?gradeId=1006" target="_blank">2014一建法规高清</a></li>
			    <li><a href="http://www.youeclass.com/main/gradeAudition?gradeId=1013" target="_blank">2014年二建施工管理高清</a></li>
			    <li><a href="http://www.youeclass.com/main/exam1011.html" target="_blank">2014年一建大型备考高清</a></li>
                        </ul>
                    </dd>
                </dl>
                <ul class="tubiao">
               	    <li><a href="http://www.youeclass.com/main/exam1011.html" target="_blank">2014年一建备考说明会试听 陈印</a></li>
               	    <li><a href="http://www.youeclass.com/main/gradeAudition?gradeId=1064" target="_blank">2014年建造师案例实务记忆课 兰善全</a></li>
               	    <li><a href="http://www.youeclass.com/main/gradeAudition?gradeId=1038" target="_blank">2013年案例实务《网络图》专题 王清涛</a></li>
               	    <li><a href="http://www.youeclass.com/main/exam1012.html" target="_blank">2013二级建造师高清试听 名师</a></li>
                </ul>
            </div>
        </div>
        <div class="ms2_center">
        	<div class="ms2_center_title"><a id="kca0" onmouseover="hgkca(0)">最新资讯</a><a id="kca1" onmouseover="hgkca(1)">备考资料</a><a id="kca2" onmouseover="hgkca(2)">招生简章</a></div>
            <div class="ms2_center_con" id="kchga0">
            <ul>
			<#list news as b>
			<#if b_index lt 1>
		    <li class="toutiao3" style="background:none;"><a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]}</#if></a></li>
		    <#else>
		    <li class="contitle"><a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]} </#if></a></li>
		    </#if>
		    </#list> 
			</ul>
            </div>
            <div class="ms2_center_con" id="kchga1" style="display:none;">
            <ul>
			<#list ksfd as b>
			<#if b_index lt 1>
		    <li class="toutiao3" style="background:none;"><a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]} </#if></a></li>
		    <#else>
		    <li class="contitle"><a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}" title="${b.subTitle}/" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]}</#if></a></li>
		    </#if>
		    </#list> 
			</ul>
            </div>
            <div class="ms2_center_con" id="kchga2" style="display:none;">
            <ul>
			<#list zsjz as b>
			<#if b_index lt 1>
		    <li class="toutiao3" style="background:none;"><a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]}</#if></a></li>
		    <#else>
		    <li class="contitle"><a href="/${b.exam.examEname}/${b.province.provinceEname}/${b.newclass.classEname}/${b.newId?c}/" title="${b.subTitle}" target="_blank"><#if b.newTitle?length lt 26 >${b.newTitle}<#else> ${b.newTitle[0..25]}</#if></a></li>
		    </#if>
		    </#list> 
			</ul>
            </div>
        </div>
        <div class="ms2_right">
        	<div class="title1">主讲名师</div>
            <div class="mingshi">
            	<dt><a href="http://www.youeclass.com/main/teacher/teacher482.html" target="_blank"><img src='/youeclass/images/chenyin-124.gif' border='0' width='73' height='80' alt='陈印'><br />陈印</a></dt>
				<dt><a href="http://www.youeclass.com/main/teacher/teacher480.html" target="_blank"><img src='http://www.youeclass.com/youeclass/images/meishiqiang-124.gif' border='0' width='73' height='80' alt='梅世强'><br />梅世强</a></dt>
				<dt><a href="http://www.youeclass.com/main/teacher/teacher493.html" target="_blank"><img src='http://www.youeclass.com/youeclass/images/liupingyu-124.gif' border='0' width='73' height='80' alt='刘平玉'><br />刘平玉</a></dt>
				<dt><a href="http://www.youeclass.com/main/teacher/teacher492.html" target="_blank"><img src='http://www.youeclass.com/youeclass/images/zhangshixing-124.gif' border='0' width='73' height='80' alt='张世星'><br />张世星</a></dt>
				<dt><a href="http://www.youeclass.com/main/teacher/teacher491.html" target="_blank"><img src='http://www.youeclass.com/youeclass/images/lilijun-124.gif' border='0' width='73' height='80' alt='李立军'><br />李立军</a></dt>
				<dt><a href="http://www.youeclass.com/main/teacher/teacher526.html" target="_blank"><img src='/youeclass/images/liangchengzhu-124.gif' border='0' width='73' height='80' alt='梁成柱'><br />梁成柱</a></dt>

            </div>
        </div>
    </div>
	<div class="ad"><a href='http://www.youeclass.com/yjjz/quanguo/bmsj/3455/' target='_blank'><img src='/youeclass/images/201406261.jpg' border='0' width='960' height='77' alt='2014年一级建造师网上报名时间汇总'></a></div>
	<div class="kb_title"><img src="/youeclass/images/kczx.png" /></div>
    <div class="kb_kong"></div>
    <div class="tuijian">
    <div class="tj_pic">
  	<h1>优异网校一级建造师顶级名师 高清课程试听</h1>
            <div class="center_pic">
            	<script type="text/javascript">
var Speed_1 = 5; //速度(毫秒)
var Space_1 = 20; //每次移动(px)
var PageWidth_1 = 107 * 6; //翻页宽度
var interval_1 = 2000; //翻页间隔时间
var fill_1 = 0; //整体移位
var MoveLock_1 = false;
var MoveTimeObj_1;
var MoveWay_1="right";
var Comp_1 = 0;
var AutoPlayObj_1=null;
function GetObj(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}}
function AutoPlay_1(){clearInterval(AutoPlayObj_1);AutoPlayObj_1=setInterval('ISL_GoDown_1();ISL_StopDown_1();',interval_1)}
function ISL_GoUp_1(){if(MoveLock_1)return;clearInterval(AutoPlayObj_1);MoveLock_1=true;MoveWay_1="left";MoveTimeObj_1=setInterval('ISL_ScrUp_1();',Speed_1);}
function ISL_StopUp_1(){if(MoveWay_1 == "right"){return};clearInterval(MoveTimeObj_1);if((GetObj('ISL_Cont_1').scrollLeft-fill_1)%PageWidth_1!=0){Comp_1=fill_1-(GetObj('ISL_Cont_1').scrollLeft%PageWidth_1);CompScr_1()}else{MoveLock_1=false}
AutoPlay_1()}
function ISL_ScrUp_1(){if(GetObj('ISL_Cont_1').scrollLeft<=0){GetObj('ISL_Cont_1').scrollLeft=GetObj('ISL_Cont_1').scrollLeft+GetObj('List1_1').offsetWidth}
GetObj('ISL_Cont_1').scrollLeft-=Space_1}
function ISL_GoDown_1(){clearInterval(MoveTimeObj_1);if(MoveLock_1)return;clearInterval(AutoPlayObj_1);MoveLock_1=true;MoveWay_1="right";ISL_ScrDown_1();MoveTimeObj_1=setInterval('ISL_ScrDown_1()',Speed_1)}
function ISL_StopDown_1(){if(MoveWay_1 == "left"){return};clearInterval(MoveTimeObj_1);if(GetObj('ISL_Cont_1').scrollLeft%PageWidth_1-(fill_1>=0?fill_1:fill_1+1)!=0){Comp_1=PageWidth_1-GetObj('ISL_Cont_1').scrollLeft%PageWidth_1+fill_1;CompScr_1()}else{MoveLock_1=false}
AutoPlay_1()}
function ISL_ScrDown_1(){if(GetObj('ISL_Cont_1').scrollLeft>=GetObj('List1_1').scrollWidth){GetObj('ISL_Cont_1').scrollLeft=GetObj('ISL_Cont_1').scrollLeft-GetObj('List1_1').scrollWidth}
GetObj('ISL_Cont_1').scrollLeft+=Space_1}
function CompScr_1(){if(Comp_1==0){MoveLock_1=false;return}
var num,TempSpeed=Speed_1,TempSpace=Space_1;if(Math.abs(Comp_1)<PageWidth_1/2){TempSpace=Math.round(Math.abs(Comp_1/Space_1));if(TempSpace<1){TempSpace=1}}
if(Comp_1<0){if(Comp_1<-TempSpace){Comp_1+=TempSpace;num=TempSpace}else{num=-Comp_1;Comp_1=0}
GetObj('ISL_Cont_1').scrollLeft-=num;setTimeout('CompScr_1()',TempSpeed)}else{if(Comp_1>TempSpace){Comp_1-=TempSpace;num=TempSpace}else{num=Comp_1;Comp_1=0}
GetObj('ISL_Cont_1').scrollLeft+=num;setTimeout('CompScr_1()',TempSpeed)}}
function picrun_ini(){
GetObj("List2_1").innerHTML=GetObj("List1_1").innerHTML;
GetObj('ISL_Cont_1').scrollLeft=fill_1>=0?fill_1:GetObj('List1_1').scrollWidth-Math.abs(fill_1);
GetObj("ISL_Cont_1").onmouseover=function(){clearInterval(AutoPlayObj_1)}
GetObj("ISL_Cont_1").onmouseout=function(){AutoPlay_1()}
AutoPlay_1();
}
</script>
<!-- picrotate_left start  -->
<div class="blk_18"> <a class="LeftBotton" onmousedown="ISL_GoUp_1()" onmouseup="ISL_StopUp_1()" onmouseout="ISL_StopUp_1()" href="javascript:void(0);" target="_self"></a>
  <div class="pcont" id="ISL_Cont_1">
    <div class="ScrCont">
      <div id="List1_1">
        <!-- piclist begin -->		
  		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher482.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/chenyin-136.gif' border='0' width='136' height='86' alt='陈印：2014年一建法规高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher480.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/meishiqiang-136.gif' border='0' width='136' height='86' alt='梅世强：2014年一建工程经济高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher497.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/guyongcai-136.gif' border='0' width='136' height='86' alt='顾永才：2014年一建项目管理高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher526.html" target='_blank'><img src='/youeclass/images/liangchegnzhu-136.gif' border='0' width='136' height='86' alt='梁成柱：2014年一建项目管理高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher493.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/liupingyu-136.gif' border='0' width='136' height='86' alt='刘平玉：2014年一建机电实务高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher491.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/lilijun-136.gif' border='0' width='136' height='86' alt='李立军：2014年一建建筑实务高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/exam1011.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/xurong-136.gif' border='0' width='136' height='86' alt='徐蓉：2014年一建工程经济高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher510.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/zhangxintian-136.gif' border='0' width='136' height='86' alt='张新天：2014年一建市政实务高清课程名师'></a>
		<a class="pl" href="#" target='_blank'><img src='http://www.youeclass.com/youeclass/images/chenming-136.gif' border='0' width='136' height='86' alt='陈明：2014年一建市政实务高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher481.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/duanjixiao-136.gif' border='0' width='136' height='86' alt='段绩校：2014年一建工程经济高清课程名师'></a>
		<a class="pl" href="#" target='_blank'><img src='http://www.youeclass.com/youeclass/images/lanshanquan-136.gif' border='0' width='136' height='86' alt='兰善全：2014年一建实务课程记忆大师'></a>
		<a class="pl" href="#" target='_blank'><img src='http://www.youeclass.com/youeclass/images/wangjianlei-136.gif' border='0' width='136' height='86' alt='王建雷：2014年一建公路实务高清课程名师'></a>
		<a class="pl" href="#" target='_blank'><img src='http://www.youeclass.com/youeclass/images/xurong-136.gif' border='0' width='136' height='86' alt='徐蓉：2014年一建建筑、经济高清课程名师'></a>
		<a class="pl" href="http://www.youeclass.com/main/teacher/teacher483.html" target='_blank'><img src='http://www.youeclass.com/youeclass/images/lixiangguo-136.gif' border='0' width='136' height='86' alt='李向国：2014年一建法律法规、铁路实务高清课程名师'></a>
        <!-- piclist end -->
      </div>
      <div id="List2_1"></div>
    </div>
  </div>
 	 <a class="RightBotton" onmousedown="ISL_GoDown_1()" onmouseup="ISL_StopDown_1()" onmouseout="ISL_StopDown_1()" href="javascript:void(0);" target="_self"></a> </div>
<div class="c"></div>
<script type="text/javascript">
        <!--
        picrun_ini()
        //-->
        </script>
            </div>
        </div>
        <div class="mszd">
        	<div class="mszd_title"><a id="kcb0" onmouseover="hgkcb(0)">热门课程</a></div>
            <div class="mszd_con" id="kchgb0">
                <ul>
                 <li><a href="http://www.youeclass.com/main/exam1011.html" target="_blank">2014年一级建造师顶级名师全程班</a></li>
		 <li><a href="http://www.youeclass.com/main/exam1033.html" target="_blank">2014年安全工程师全程VIP班</a></li>
		 <li><a href="http://www.youeclass.com/main/exam1015.html" title="" target="_blank">2014年造价工程师精品豪华班</a></li>
		 <li><a href="" target="_blank">2014年消防工程师全程名师班</a></li>
                 </ul>
             </div>
        </div>
    </div>   
    <div class="kb_title"><img src="/youeclass/images/bkzx.png">
    	<li><a href="http://www.youeclass.com/yjjz/cjcx/" target="_blank">成绩查询</a><a>|</a> <a href="http://www.youeclass.com/yjjz/zkzdy/" target="_blank">准考证打印时间</a><a>|</a><a href="http://www.youeclass.com/yjjz/bmsj/" target="_blank">报名时间</a><a>|</a><a href="http://www.youeclass.com/yjjz/quanguo/kszx/3435/" target="_blank">考试时间</a></li>
    </div>
    <div id="content">
    <div class="xueli" style="width:310px;margin-left:0px;"><h2><span>考试报名</span><a href="http://www.youeclass.com/yjjz/bmsj/" target="_blank">更多&gt;&gt;</a></h2>
	  <ul class="one"><#list bmsj as n>
			<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank">${n.newTitle}</a></li>
		</#list></ul>
	 </div>
    <div   class="xueli" style="width:310px;margin-left:12px;"><h2><span>准考证打印</span><a href="http://www.youeclass.com/yjjz/zkzdy/" target="_blank">更多&gt;&gt;</a></h2>
	  <ul  class="one" ><#list zkzdy as n>
			<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank">${n.newTitle}</a></li>
		</#list></ul>
	</div>	
    <div class="xueli" style="width:310px;margin-left:12px;"><h2><span>成绩查询</span><a href="http://www.youeclass.com/yjjz/cjcx/" target="_blank">更多&gt;&gt;</a></h2>
	  <ul class="one" ><#list cjcx as n>
			<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank">${n.newTitle}</a></li>
		</#list></ul>
	</div>		
</div>
	<div class="ad"><a href='/main/exam1011.html' target='_blank'><img src='/youeclass/images/20140626.gif' border='0' width='960' height='100' alt='2014年一级建造师保过班热招'></a></div>

	<div class="kb_title"><img src="/youeclass/images/bkfd.png" />
        <li><a href="http://www.youeclass.com/yjjz/fdzl/">辅导资料</a><a href="http://www.youeclass.com/yjjz/mnst/">模拟试题|</a><a href="http://www.youeclass.com/yjjz/lnzt/">历年真题|</a></li>
    </div>


    <div id="content">
    <div class="xueli" style="width:310px;margin-left:0px;"><h2><span>历年真题</span><a href="http://www.youeclass.com/yjjz/lnzt/" target="_blank">更多&gt;&gt;</a></h2>
	  <!--<ul class="one"><#list bmsj as n>
			<li>·<a href="/gcjj/quanguo/lnzt/2054/" title="2013年一建工程经济" target="_blank">2013年一级建造师工程经济真题及答案解析</a></li>
			<li>·<a href="/flfg/quanguo/lnzt/2111/" title="2013年一建法规真题" target="_blank">2013年一级建造师建设工程法规及相关知识真题及答案解析</a></li>
			<li>·<a href="/xmgl/quanguo/lnzt/2120/" title="2013年一建项目管理真题" target="_blank">2013年一级建造师建设工程项目管理真题及答案</a></li>
			<li>·<a href="/jzsw/quanguo/lnzt/2520/" title="2013年一建建筑实务真题" target="_blank">2013年一级建造师建筑工程管理与实务真题及答案</a></li>
			<li>·<a href="/jdsw/quanguo/lnzt/2688/" title="2013年一建机电实务真题" target="_blank">2013年一级建造师机电工程管理与实务真题</a></li>
			<li>·<a href="/glsw/quanguo/lnzt/2792/" title="2013年一建公路实务真题" target="_blank">2013年一级建造师公路工程管理与实务真题</a></li>
			<li>·<a href="/szsw/quanguo/lnzt/3139/" title="2011年一级建造师市政实务真题" target="_blank">2011年一级建造师《市政实务》考试真题</a></li>
			<li>·<a href="/tlsw/quanguo/lnzt/3143/" title="2010年一级建造师铁路实务真题" target="_blank"> 2010年一级建造师《铁路实务》考试真题</a></li>
			<li>·<a href="/kysw/quanguo/lnzt/3146/" title="2009年一级建造师矿业实务真题" target="_blank"> 2009年一级建造师《矿业实务》考试真题</a></li>
			<li>·<a href="/slsd/quanguo/lnzt/3152/" title="2011年一级建造师水利水电实务真题" target="_blank">2011年一级建造师《水利水电实务》考试真题</a></li>
		</#list></ul>-->
		<ul class="one"><#list lnzt as n>
			<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank"><#if n.newTitle?length lt 26 >${n.newTitle}<#else> ${n.newTitle[0..25]}</#if></a></li>
		</#list></ul>
	 </div>
    <div   class="xueli" style="width:310px;margin-left:12px;"><h2><span>模拟试题</span><a href="http://www.youeclass.com/yjjz/mnst/" target="_blank">更多&gt;&gt;</a></h2>
	  <!--<ul  class="one" ><#list zkzdy as n>
			<li>·<a href="/gcjj/quanguo/lnzt/1584/" title="2012年一建工程经济" target="_blank">2012年一级建造师工程经济真题</a></li>
			<li>·<a href="/flfg/quanguo/lnzt/2110/" title="2012年一建法规真题" target="_blank">2012年一级建造师建设工程法规及相关知识真题及答案解析</a></li>
			<li>·<a href="/xmgl/quanguo/lnzt/2119/" title="2012年一建项目管理真题" target="_blank">2012年一级建造师建设工程项目管理真题及答案</a></li>
			<li>·<a href="/jzsw/quanguo/lnzt/2519/" title="2012年一建建筑实务真题" target="_blank">2012年一级建造师建筑工程管理与实务真题及答案</a></li>
			<li>·<a href="/jdsw/quanguo/lnzt/2687/" title="2012年一建机电实务真题" target="_blank">2012年一级建造师机电工程管理与实务真题及答案</a></li>
			<li>·<a href="/glsw/quanguo/lnzt/2791/" title="2012年一建公路工实务真题" target="_blank">2012年一级建造师公路工程管理与实务真题</a>></li>
			<li>·<a href="/szsw/quanguo/lnzt/3138/" title="2010年一级建造师市政实务真题" target="_blank">2010年一级建造师《市政实务》考试真题</a></li>
			<li>·<a href="/slsd/quanguo/lnzt/3151/" title="2010年一级建造师水利水电实务真题" target="_blank">2010年一级建造师《水利水电实务》考试真题</a></li>
			<li>·<a href="/txgd/quanguo/lnzt/3157/" title="2011年一级建造师通信与广电实务真题" target="_blank">2011年一级建造师《通信与广电实务》考试真题</a></li>
		</#list></ul>-->
		<ul  class="one" ><#list mnst as n>
			<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank"><#if n.newTitle?length lt 26 >${n.newTitle}<#else> ${n.newTitle[0..25]}</#if></a></li>
		</#list></ul>
	</div>	
    <div class="xueli" style="width:310px;margin-left:12px;"><h2><span>辅导资料</span><a href="http://www.youeclass.com/yjjz/fdzl/" target="_blank">更多&gt;&gt;</a></h2>
	  <!--<ul class="one" ><#list cjcx as n>
			<li>·<a href="/gcjj/quanguo/fdzl/4308/" title="2014年一级建造师复习资料：《工程经济》存货的核算" target="_blank">2014年一级建造师复习资料：《工程经济》存货的核算</a></li>
			<li>·<a href="/gcjj/quanguo/fdzl/4307/" title="2014年一级建造师复习资料：《工程经济》应收及预付款项的核算" target="_blank">2014年一级建造师复习资料：《工程经济》应收及预付款项的核算</a></li>
			<li>·<a href="/flfg/quanguo/fdzl/4328/" title="2014年一级建造师复习资料：《法规及相关知识》仲裁庭的组成" target="_blank">2014年一级建造师复习资料：《法规及相关知识》仲裁庭的组成</a></li>
			<li>·<a href="/xmgl/quanguo/fdzl/4351/" title="2014年一级建造师复习资料：《项目管理》索赔款的利息" target="_blank">2014年一级建造师复习资料：《项目管理》索赔款的利息</a></li>
			<li>·<a href="/jzsw/quanguo/fdzl/5233/" title="《建筑实务》施工项目管理规划" target="_blank">2014年一级建造师复习资料：《建筑实务》施工项目管理规划</a></li>
			<li>·<a href="/jdsw/quanguo/fdzl/5159/" title="《机电实务》检验批划分的原则" target="_blank">2014年一级建造师复习资料：《机电实务》检验批划分的原则</a></li>
			<li>·<a href="/jdsw/quanguo/fdzl/5154/" title="《机电实务》建筑安装工程质量验收要求" target="_blank">2014年一级建造师复习资料：《机电实务》建筑安装工程质量验收要求</a></li>
			<li>·<a href="/glsw/quanguo/fdzl/5174/" title="《公路实务》滑坡地段路基的施工技术要点" target="_blank">一级建造师复习资料：《公路实务》滑坡地段路基的施工技术要点</a></li>
			<li>·<a href="/glsw/quanguo/fdzl/5174/" title="《公路实务》滑坡地段路基的施工技术要点" target="_blank">一级建造师复习资料：《公路实务》滑坡地段路基的施工技术要点</a></li>
			<li>·<a href="/slsd/quanguo/fdzl/5206/" title="《水利水电实务》平底板施工" target="_blank">一级建造师复习资料：《水利水电实务》平底板施工</a></li>
		</#list></ul>-->
		<ul class="one" ><#list fdzl as n>
			<li>·<a href="/${n.exam.examEname}/${n.province.provinceEname}/${n.newclass.classEname}/${n.newId?c}/" title="${n.subTitle}" target="_blank"><#if n.newTitle?length lt 26 >${n.newTitle}<#else> ${n.newTitle[0..25]}</#if></a></li>
		</#list></ul>
	</div>		
</div>



    		<div class="ad"><a href='/yjjz/lnzt/' target='_blank'><img src='/youeclass/images/201406262.gif' border='0' width='960' height='86' alt='一级建造师各科历年真题汇总'></a></div>



    <div class="kb_title"><img src="/youeclass/images/hzhb.png" /></div>	
    <div class="hzhb">
    	<ul>
		
        		<ul>
				 
              	<li><a href='http://www.youeclass.com/yjjz/' target='_blank'>一级建造师</a> </li> 
              	<li><a href='http://www.youeclass.com/ejjz/' target='_blank'>二级建造师</a> </li> 
              	<li><a href='http://www.youeclass.com/jlgc/' target='_blank'>监理工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/zjgc/' target='_blank'>造价工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/aqgc/' target='_blank'>安全工程师</a> </li> 
              	<li><a href='http://www.youeclass.com/main/exam1076.html' target='_blank'>山西造价员</a> </li> 
              	<li><a href='http://www.youeclass.com/xfgc/' target='_blank'>消防工程师</a> </li> 
              	<li><a href='http://www.youeclass.com' target='_blank'>优异网校</a> </li> 
 
			</ul>
              	
        </ul>
    </div>
    <div id="cbox">
	<div class="bottom">
    	<ul>
        	<li><a href="http://www.youeclass.com/help/about.html" rel="nofollow" target="_blank">关于我们</a> | <a href="#" rel="nofollow"  target="_blank">招聘信息</a> | <a  href="#" rel="nofollow"  target="_blank">免责声明</a> | <a href="#"  rel="nofollow" target="_blank">合作加盟</a> | <a href="http://www.youeclass.com/sitemap.html" target="_blank">网站地图</a> | <a href="http://www.youeclass.com/help/"  rel="nofollow" target="_blank">帮助中心</a> | <a href="http://www.youeclass.com/help/about.html#contact" rel="nofollow"  target="_blank">联系我们</a></li>
            <li>Copyright &copy; 2013-2016 Youeclass.net Inc. All Rights  Reserved.</li>
            <li><a rel="nofollow" href="#">优异网校</a>&nbsp;<a href="/jianzhu/">建筑网校</a>&nbsp; 版权所有 京ICP备11012638号</li>
			<li> <script src="http://s19.cnzz.com/stat.php? id=5456201&web_id=5456201" language="JavaScript"></script> &nbsp;&nbsp; <script language="javascript" type="text/javascript"  src="http://js.users.51.la/15961317.js"></script><noscript><a href="http://www.51.la/?15961317"  target="_blank"><img  alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"  src="http://img.users.51.la/15961317.asp" style="border:none"  /></a></noscript></li>
        </ul>
    </div>
</div>
</div>
<script type="text/javascript" src ="/youeclass/js/common.js"></script>
</body>
</html>