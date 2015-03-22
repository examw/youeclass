<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学员注册 - 网校学习中心</title>
<link href="../images/User.css" type="text/css" rel="stylesheet" id="mainStyle"/>
<script src="/js/jquery-1.4.3.js" type="text/javascript"></script>
<script src="/js/checkRegister.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		//focus input得到焦点，提示输入内容要求
		$("#username").focus(
			function(){
				$("#usernameInfo").html("<p class='warn'> 4-20位(数字字母下划线)</p>");
				});
		$("#pwd").focus(
			function(){
				$("#pwdInfo").html("<p class='warn'> 6-20位(数字字母下划线)</p>");
				});
		$("#pwd2").focus(
			function(){
				$("#pwd2Info").html("<p class='warn'> 请再输入一次上面的密码</p>");
				});
		$("#name").focus(
			function(){
				$("#nameInfo").html("<p class='warn'> 请输入您的真实姓名</p>");
				});
		$("#mobile").focus(
			function(){
				$("#mobileInfo").html("<p class='warn'> 请再输入您的联系电话</p>");
				});
		
		$("#email").focus(
			function(){
				$("#emailInfo").html("<p class='warn'> 请再输入您的邮箱地址</p>");
				});
		$("#qq").focus(
			function(){
				$("#qqInfo").html("<p class='warn'> 请再输入您的QQ号码</p>");
				});
		
		
		$("#deal").change(function(){
			if($("#deal").attr('checked')){$("#dealInfo").html("");}
		});
		//提交
		
		$("#register").click(function(){
			if(checkAll()){$("#form").submit();};
		});
	});
	function checkAll()
	{
		return checkUsername($("#username"),$("#usernameInfo"),"/user/checkUsername")&checkPassword($("#pwd"),$("#pwdInfo"))&checkRepeatPwd($("#pwd"),$("#pwd2"),$("#pwd2Info"))&checkName($("#name"),$("#nameInfo"))&checkPhone($("#mobile"),$("#mobileInfo"))&checkDeal($("#deal"),$("#dealInfo"))&checkEmail($("#email"),$("#emailInfo"),'/user/checkEmail')&checkQICQ($("#qq"),$("#qqInfo"));
	}
</script>
</head>
<body>
<div id="headWrap">
	<h1 id="logo"><a href="http://www.youeclass.com" title="网校系统 会员注册">&quot;网校会员中心</a></h1>
</div>
<div id="mainWrapper">
	<div class="pageTit">
		<h2>立即注册网校</h2>
		<p class="desc">为方便我们能更好的为您提供服务，请您详细填写下列信息、您的信息我们不会透露给任何人。</p>
 	</div>
  	<div class="W_reg_info clearfix" id="pl_account_reg">
  		<form action="/user/register" method="post" id="form">
  		<div class="W_reg_form">
            <div class="info_list clearfix">
                <div class="tit"><i>*</i>用 户 名：</div>
                <div class="inp">
                    <input id="username" name="stu.stuUsername" type="text" class="W_input" value="" onblur='checkUsername($("#username"),$("#usernameInfo"),"/user/checkUsername");'/>
                </div>
                <div class="tips" id="usernameInfo"></div>
            </div>
            <div class="info_list clearfix">
                <div class="tit"><i>*</i>登录密码：</div>
                <div class="inp">
                  <input action-data="" id="pwd" maxLength="20" name="stu.stuPassword" type="password" class="W_input" value="" onblur='checkPassword($("#pwd"),$("#pwdInfo"));'/>
              </div>
                <div class="tips" id="pwdInfo"></div>
            </div>
            <div class="info_list clearfix">
                <div class="tit"><i>*</i>确认密码：</div>
                <div class="inp">
                    <input action-data="" name="rpasswd" maxLength="20" id="pwd2" type="password" class="W_input" value="" onblur='checkRepeatPwd($("#pwd"),$("#pwd2"),$("#pwd2Info"));'/>
                </div>
                <div node-type="nickname_tip" class="tips" id="pwd2Info"></div>
            </div>
            <div class="info_list clearfix">
                <div class="tit"><i>*</i>邮箱地址：</div>
                <div class="inp">
                    <input id="email" action-data="" name="stu.stuEmail" type="text" class="W_input" value="" onblur='checkEmail($("#email"),$("#emailInfo"),"/user/checkEmail");'/>
                </div>
                <div node-type="nickname_tip" class="tips" id="emailInfo"></div>
            </div> 
            <div class="info_list clearfix">
                <div class="tit"><i>*</i>真实姓名：</div>
                <div class="inp">
                    <input id="name" action-data="" name="stu.stuName" type="text" class="W_input" value="" onblur='checkName($("#name"),$("#nameInfo"));'/>
                </div>
                <div node-type="nickname_tip" class="tips" id="nameInfo"></div>
            </div>
            <div class="info_list clearfix">
                <div class="tit"><i>*</i>联系电话：</div>
                <div class="inp">
                    <input action-data="" name="stu.stuMobile" id="mobile" type="text" class="W_input" value="" onblur='checkPhone($("#mobile"),$("#mobileInfo"));'/>
                </div>
                <div node-type="nickname_tip" class="tips" id="mobileInfo"></div>
                </div>
             <div class="info_list clearfix">
                <div class="tit">QQ：</div>
                <div class="inp">
                    <input action-data="" name="stu.QICQ" id="qq" type="text" class="W_input" value="" onblur='checkQICQ($("#qq"),$("#qqInfo"));'/>
                </div>
                <div node-type="nickname_tip" class="tips" id="qqInfo"></div>
                </div>
            <div class="info_submit clearfix">
                <div class="inp">
                    <a action-type="btn_submit" class="W_btn_big" href="javascript:void(0);" id="register"><span>立即注册</span></a>
                    <p><input type="checkbox" checked="checked" name="deal" id="deal">&nbsp;&nbsp;我已阅读并同意<a href="#" target="_blank">网校服务协议</a><br><a id="dealInfo" style="color:red;"></a></p>
                </div>
            </div>
        </div>
        </form>
       <div class="W_reg_sidebar">
                <p class="p1 line">已有帐号</p>
                <p class="p2" align="center"><input id="loginbtn" value="快速登录" type="submit" onclick="location='../user/login.jsp';"></p>
       </div>
   </div>
</div>
	<div style="clear:both"></div>
   <div class="footer clearfix">
  		<p>Copyright &copy; 2006 - 2013 www.youeclass.com All Rights Reserved</p>
   </div>
</body>
</html>
