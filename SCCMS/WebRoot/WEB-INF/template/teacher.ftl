﻿<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${teacher.tchName} - 优异网校名师</title>
<meta name="keywords" content="${teacher.tchName},${teacher.tchName}老师,${teacher.tchName}主页" />
<meta name="description" content="优异网校名师：${teacher.tchName}老师介绍,${teacher.tchName}详细资料。" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="/main/css/teacher.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="/artDialog/jquery.artDialog.js?skin=default" type="text/javascript"></script>
<script type="text/javascript" >
var tchId =${tchId};
$(function(){
			//发送ajax请求,加载new.jsp信息");
			$("#top").load("/common/head1.jsp");
			$("#foot").load("/common/foot2.html");
			$.post("/user/stuSession",function(data){
				if(data==false){
				$("#discuss").html("<textarea name='custom_comment' id='custom_comment' class='pl_input'></textarea><span class='pl_text_span'><a href='javascript:void(0)'></a></span><span class='s2'><em>您还未登录哦，请登录后参与讨论</em><input type='image' src='/main/images/btn_dl1.gif' value='提交' id='button' name='button' onclick='ShowLogin()' class='log'><input type='image' src='/main/images/btn_dl.gif' value='注册' onclick='showRegbox()' class='log'></span>");
			}else{
				$("#discuss").html("<textarea name='custom_comment' id='custom_comment' class='pl_input'></textarea><span class='pl_text_span'><a onclick='AddComment()'><img  src='/main/images/pic_15.gif'  /></a></span>");
			}
			},"json")
			
			GetCommentList(1);
		});
		
	//分页	
 	function Pager(AllCount, PageSize, Page) {
	    var PageStr = "", MaxPage = 0, ShowPageNum = 3, k = 0;
	    Page = parseInt(Page);
	    MaxPage = parseInt(AllCount / PageSize);
	    if (AllCount % PageSize != 0) { MaxPage++; }
	    PageStr = "<span>共有" + AllCount + "条 当前" + Page + "/" + MaxPage + "页</span>";
	    PageStr += "<span class=\"prev\" onclick=\"GotoPage(1)\">首页</span>";
	    PageStr += (Page - 1 > 0) ? "<span class=\"prev\" onclick=\"GotoPage(" + (Page - 1) + ")\">上一页</span>" : "<span>上一页</span>";
	    for (j = 0; j < MaxPage; j++) {
	        if (k >= ShowPageNum) { break; }
	        if (((Page - j - 2 <= 0) && (MaxPage - j - ShowPageNum >= 0)) || (MaxPage - j - ShowPageNum <= 0)) {
	            k++;
	            PageStr += (Page - j == 1) ? "<span class=\"current\">" + (j + 1) + "</span>" : "<span class=\"prev\" onclick=\"GotoPage(" + (j + 1) + ")\">" + (j + 1) + "</span>";
	        }
	    }
	    PageStr += (Page - MaxPage < 0) ? "<span class=\"prev\" onclick=\"GotoPage(" + (Page + 1) + ")\">下一页</span>" : "<span>下一页</span>";
	    PageStr += "<span class=\"prev\" onclick=\"GotoPage(" + MaxPage + ")\">尾页</span>";
	    //PageStr += "<span class=\"goto_span\">第<input name=\"go_to\" class=\"goto_input\" id=\"num_go_to\" size=\"3\" type=\"text\">页</span><a href=\"javascript:;\" onclick=\"GotoPage($('#num_go_to').val())\">转到</a><div style=\"clear: both;\"></div>"
	    $("#PageStr").html(PageStr);
		}
	//评论
	function GetCommentList(page)
	{
		$(".contx").html("<img src=\"/artDialog/skins/icons/loading.png\">");
		$.ajax({
			type:"get",
			contentType:"text/html;UTF-8",
			url: "/main/commentShow",
			data: { page: page, tchId: tchId },
			dataType: 'json',
			success: function (result){
				if(result!=null)
				{
					if(result.comment.length>0)
					{
						var html="";
						for( var i=0;i<result.comment.length;i++)
						{
							var date = result.comment[i].commentAddTime.toString().replace("T","  ");
							html+="<li class=\"pl_one\"><dl><dt><img src='/main/images/no_face.gif' width='113' height='104'/><span>"+result.comment[i].student.stuUsername+"</span></dt><dd><span><em>"+date+"</em><img src=\"/main/images/x_pic_"+result.comment[i].commentScore+".gif\" width=\"94\" height=\"13\" /><font color=\"#AE1E30\">"+result.comment[i].commentScore+"分</font></span><ul><li><strong></strong>"+result.comment[i].commentContent+"</li></ul></dd></dl></li>"
						}
						
					}
					else
					{
						html="<div class=\"xdbox\">暂时没有讨论内容，您来第一个发表吧</div>";
					}
				/* 	$(".qt-cmtTabsMore").html("(共"+result.AllCount+"条评论)"); */
					$("#comment").html(html);
					pagestr = (result.total-result.pagesize>0)?Pager(result.total,result.pagesize,result.page):"";
					$("#PageStr").html(pagestr);
				}
				else
				{
				}
			},
			error: function (e) {/*错误处理*/},
			async:true,
			cache:false
		});
	}
	function GotoPage(i) {
    this.iPage = i;
    this.GetCommentList(i);
	}

function fetchObject(idname)
{
	if (document.getElementById) {
		return document.getElementById(idname);
	} else if (document.all) {
		return document.all[idname];
	} else if (document.layers) {
		return document.layers[idname];
	} else {
		return null;
	}
}

function mn(x)
{
	if(fetchObject('score').value!="")
	{
		fetchObject('img1').src = fetchObject('img2').src = fetchObject('img3').src = fetchObject('img4').src = fetchObject('img5').src = "/main/images/gx_hxx2.gif";
		}
	if(x==1){
		fetchObject('img1').src = "/main/images/gx_hxx1.gif";
	}
	if(x==2){
		fetchObject('img1').src = fetchObject('img2').src = "/main/images/gx_hxx1.gif";

	}
	if(x==3){
		fetchObject('img1').src = fetchObject('img2').src = fetchObject('img3').src = "/main/images/gx_hxx1.gif";
	
	}
	if(x==4){
		fetchObject('img1').src = fetchObject('img2').src = fetchObject('img3').src = fetchObject('img4').src = "/main/images/gx_hxx1.gif";
	
	}
	if(x==5){
		fetchObject('img1').src = fetchObject('img2').src = fetchObject('img3').src = fetchObject('img4').src = fetchObject('img5').src = "/main/images/gx_hxx1.gif";

	}
	}
	function mf(){
		if(fetchObject('score').value=="")
		fetchObject('img1').src = fetchObject('img2').src = fetchObject('img3').src = fetchObject('img4').src = fetchObject('img5').src = "/main/images/gx_hxx2.gif";

	}
	function mc(x){
		fetchObject('score').value = x;
	}
	
	//发布评论
	function AddComment() {
        var content = $("#custom_comment").val();
        alert(content);
        var score = document.getElementById("score").value;
        if (content == "" ) {
            notice("评论不能为空", 2);
			return;
        }
        if(score=="")
		{
			notice("请选择评分！", 2);
		 	return;
		}
        $.ajax({
        	contentType:"text/html;UTF-8",
            type: "get",
            url: "/main/commentAdd",
            data: {content:encodeURIComponent(content),score:score,tchId:tchId},
            dataType: 'json',
            success: function(result) {
                if (result) {
                	$("#custom_comment").val("");
                    GotoPage(1);
                }
                else {
                    notice('评论失败,请稍重新登录再试', 2);
                }
            },
            error: function(e) { /*错误处理*/ },
            async: true,
            cache: false
        });
    }
    
    //报名
    function SetCookie(objName, objValue, objHours) {
	    var str = objName + "=" + escape(objValue);
	    if (objHours > 0) {
	        var date = new Date();
	        var ms = objHours * 3600 * 1000;
	        date.setTime(date.getTime() + ms);
	        str += "; expires=" + date.toGMTString() + "";
	    }
	    document.cookie = str + ";path=/search/UserCenter/";
	    document.cookie = str + ";path=/search/UserCenter/";
    }
    function GetCookie(objName) {
	    var arrStr = document.cookie.split("; ");
	    for (var i = 0; i < arrStr.length; i++) {
	        var temp = arrStr[i].split("=");
	        if (temp[0] == objName) return unescape(temp[1]);
	    }
    } 
    function getCookie(name) {
		var cookieValue = "";
		var search = name + "=";
		if (document.cookie.length > 0) {
			offset = document.cookie.indexOf(search);
			if (offset != -1) {
				offset += search.length;
				end = document.cookie.indexOf(";", offset);
				if (end == -1) end = document.cookie.length;
				cookieValue = unescape(document.cookie.substring(offset, end));
			};
		}
	return unescape(cookieValue);
	}
	function setCookie(cookieName,cookieValue,DayValue) {
		var expire = "";
		var day_value=1;
		if (DayValue != null) {
			day_value=DayValue;
		}
		expire = new Date((new Date()).getTime() + day_value * 86400000);
		expire = "; expires=" + expire.toGMTString();
		document.cookie = cookieName + "=" + escape(cookieValue) +";path=/"+ expire;
	}
	 function CourseSelect(myid,type){
			//套餐cookie
			var MyPackageIDCookie = getCookie('package');
			//班级cookie
			var MyGradeIDCookie = getCookie('grade');
			if(type==0)
			{
				if(MyPackageIDCookie==''){
					setCookie('package',myid,15);
				}else{
					setCookie('package',MyPackageIDCookie+','+myid,15);
				};
			}else
			{
				if(MyGradeIDCookie==''){
					setCookie('grade',myid,15);
				}else{
					setCookie('grade',MyGradeIDCookie+','+myid,15);
				};
			};
		} 
    
    function notice(content, t) {//提示框
        //dianotice = art.dialog({ id: "notice", content: content, icon: "warning", lock: true, opacity: 0.1, time: t, title: false, fixed: true, zIndex: 18008 });
        dianotice = art.dialog({ id: "notice",title: "温馨提示",icon: "warning",content: "<div class=\"aui-boxt\" style=\"width:150px;\"><div>"+content+"</div></div>",zIndex: 18008,lock: true, time: t,resize: false,fixed: true});
    }

    function loading(content, t) {//提示框
        //dialoading = art.dialog({ id: "loading", content: content, icon: "loading", cancel: false, title: false, fixed: true, zIndex: 18008 });
        dialoading = art.dialog({ id: "loading",title: "温馨提示",icon: "loading",content: "<div class=\"aui-boxt\" style=\"width:150px;\"><div>"+content+"</div></div>",zIndex: 18008,lock: true, time: t,resize: false,fixed: true});
    }

    function succeed(content, t) {
        //artdialog = art.dialog({ id: "artdialog", content: content, icon: "succeed", lock: true, opacity: 0.1, time: t, fixed: true, title: false, zIndex: 18008 });
        artdialog = art.dialog({ id: "artdialog",title: "温馨提示",icon: "succeed",content: "<div class=\"aui-boxt\" style=\"width:150px;\"><div>"+content+"</div></div>",zIndex: 18008,lock: true, time: t,resize: false,fixed: true});
    }
</script>
<style type="text/css">
    a:link, a:visited {
    color: #FFFFFF;
    text-decoration: none;
}
</style>
</head>
<body>
<div class="top" id="top">
 
</div>
<div class="loginStr" id="wxlogbox" style="height:249px;display:none;"></div>
<div class="logingc" id="wxregistbox" style="height:500px;display:none;"></div>
    <div class="ms_introduction center">
      <h2>
        <dl>
          <dt>${teacher.tchName}老师</dt>
          <dd class="ms_itdt_dd1">
          </dd>
          <dd class="ms_itdt_dd2"><a href="#004"><img src="/main/images/pic_10.gif" width="69" height="21" /></a></dd>
        </dl>
      </h2>
      <ul>
        <li class="ms_itdt_content">
          <ul>
            <li class="itdt_left">
              <div class="">
                <ul>
                 <#if teacher.tchImgUrl?exists>
                  <li><img src="http://www.youeclass.com${teacher.tchImgUrl}" width="479px" height="300px" /></li>
                  <#else>
                  <li><img src="/upload/userface/no_face.gif" width="479px" height="300px" /></li>
                  </#if>
                </ul>
              </div>
            </li>
            <li class="itdt_right">
              <ul>
                <li class="itdt_right_01">
                  <dl>
                    <dt><strong>平均得分：</strong></dt>
                    <dd class='dd_09' style='width:100px;padding-top:10px;line-height:0px'><img src='/main/images/x_pic_${score}.gif' width='94' height='13' /></dd><dd class='dd_10'>${score}分/满分5分</dd>
                  </dl>
                </li>
                <li class="itdt_right_01" style="height:58px;">
                  <dl>
                    <dt><strong>主要课程：</strong></dt>
                    <dd style="width:290px; line-height:20px;padding-top:7px;"> <font title="${teacher.tchLessons}">${teacher.tchLessons}</font></dd>
                  </dl>
                </li>
                <li class="itdt_right_01" style="height:215px;background:none;">
                  <dl>
                    <dt><strong>老师简介：</strong><#if teacher.tchDescription?exists></dt> ${teacher.tchDescription}  </dl><#else></dt> 暂无简介</dl></#if>
                </li>
              </ul>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <!--框架二-->
    <div id="audition" class="ms_kj_two center">
    <h2><a href="#audition" class="xz">课程试听</a><a href="#002">学员评价</a></h2>
      <ul class="center">
	  	<#if gradeList??><#list gradeList as g>
          <li>
              <dl>
                  <dt><img src="/main/images/t_st.jpg" width="171" height="107" /></dt>
                  <dd>
                    <span class="ms_kj_span1">${g.examCategory.examName}${g.gradeCategory.gtypeName}<br /></span>
                    <span class="ms_kj_span2"><em><a onclick="CourseSelect('${g.gradeId?c}',1);" href='/cart/cartList' target="_blank">报名>></a></em><a href="/main/gradeAudition?gradeId=${g.gradeId?c}" class="st" target="_blank">点击试听>></a></span>
                  </dd>
              </dl>
          </li> </#list></#if>
	</ul>
    </div>
    <div id="002" class="ms_kj_four center">
      <h2><a href="#audition">课程试听</a><a href="#002" class="xz">学员评价</a></h2>
      <div class="ms_kj_pl">
        <ul class="center" id="comment">
        </ul>
      </div>
  	  <div class="fy" >
            <div class="pagination" id="PageStr" ></div>
       </div>
      </div> 
<div class="ms_kj_five center" id="004">
  <h3>发表评论：</h3>
  <div class="mskj_left">
  <ul>
   	  <li class="px"><dl><dt>请评分：<input type="hidden" id="score" name="score" value="5" /></dt>
	  <dd class="px_dd_01" id="ss"> 
	  <IMG class=hand id=img1 onmouseover=mn(1) 
            onclick=mc(1) onmouseout=mf() height=16 
            src="/main/images/gx_hxx1.gif" width=16 
            border=0><IMG class=hand id=img2 onmouseover=mn(2) onclick=mc(2) 
            onmouseout=mf() height=16 
            src="/main/images/gx_hxx1.gif" width=16 
            border=0><IMG class=hand id=img3 onmouseover=mn(3) onclick=mc(3) 
            onmouseout=mf() height=16 
            src="/main/images/gx_hxx1.gif" width=16 
            border=0><IMG class=hand id=img4 onmouseover=mn(4) onclick=mc(4) 
            onmouseout=mf() height=16 
            src="/main/images/gx_hxx1.gif" width=16 
            border=0><IMG class=hand id=img5 onmouseover=mn(5) onclick=mc(5) 
            onmouseout=mf() height=16 
            src="/main/images/gx_hxx1.gif" width=16 
          border=0></dd>
    </dl></li>
      <li class="ms_yd">
          <dl>
              <dd>
			  <input type="hidden" id="type5" name="type5" value="5"  />
			  <input type="hidden" id="keyword5" name="keyword5" value=""  />
              	<ul class="ul0">
                	
                </ul>
              </dd>
          </dl>
      </li>
	  <input type="hidden" id="keywordnum" name="keywordnum" value="5" />
      <li class="pl_text">
      	<dl>
        	<dt>评论：</dt>
            <dd id="discuss">
            </dd>
        </dl>
      </li>
  </ul>
  </div>  
</div> 
<div class="clear"></div>
<div id="foot"></div>
<div style="display:none;"><script src="http://s19.cnzz.com/stat.php? id=5456201&web_id=5456201" language="JavaScript"></script><script language="javascript" type="text/javascript"  src="http://js.users.51.la/15961317.js"></script><noscript><a href="http://www.51.la/?15961317"  target="_blank"><img  alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;"  src="http://img.users.51.la/15961317.asp" style="border:none"  /></a></noscript></div>
</body>
</html>
l>

