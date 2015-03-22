<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script src="/js/jquery-1.7.1.min.js" type="text/javascript"></script> 
<script type="text/javascript" >
var cknametf,ckpwdtf,ckrepwdtf,ckemailtf,ckmobile,ckname;
cknametf=ckpwdtf=ckrepwdtf=ckemailtf=cktruenametf=ckmobile=ckname=ckqq=false;
function showloginbox2(){
	art.dialog({id:'wxregistbox'}).close();
	login();
}

function ChecknotNull(tmpstr){
   if(tmpstr==''){
	  return false ;
   } else {
	  return true;
   }
}
function CheckPassWord(){
	var regpwd = $("#reg_pwd").val();
	if(ChecknotNull(regpwd)){
	  var reg = /^[0-9a-zA-Z_]+$/;
	  if(regpwd.length>5 && regpwd.length<17&&reg.test(regpwd)){
		 $("#reg_pwd_msg").html('密码填写正确!');
		 ckpwdtf = true;
	  }else{
		 $("#reg_pwd_msg").html('密码为6-16个字符，包括字母、数字、特殊符号!');
		 ckpwdtf = false;
		 }
	}else{
	  $("#reg_pwd_msg").html('请填写密码!');
	  ckpwdtf = false;
	}
	if(ckpwdtf){
		 $("#reg_pwd_msg").attr("class","line1px_4");
		}else{
		  $("#reg_pwd_msg").attr("class","line1px_3");
		}	
}
/* function checkmail()
{
  var email = $("#reg_mail").val();
  if(ChecknotNull(email)){
	if(/^([a-zA-Z0-9\._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/.test(email)){
	  $("#reg_mail_msg").html('邮箱填写正确!');
	  ckemailtf = true;
	 }else{ 
	  $("#reg_mail_msg").html('邮箱填写错误!');
	  ckemailtf = false
	 }
  }else{
	  $("#reg_mail_msg").html('请填写邮箱!');
	  ckemailtf = false
  }
	if(ckemailtf){
		 $("#reg_mail_msg").attr("class","line1px_4");
		}else{
		  $("#reg_mail_msg").attr("class","line1px_3");
		}	  
} */

function CheckMail() {
        var email = $("#reg_mail").val();
        if (!checkIsEmailNumber(email) && email != '') {
            $("#reg_mail_msg").html('邮箱填写错误!');
	  		ckemailtf = false;
        }
        else if ( email== '') { 
            $("#reg_mail_msg").html('&nbsp;邮件不能为空哦！| 例：youeclass@163.com!');
	 		ckemailtf = false;
        }else if(checkIsEmailNumber(email))
        {
        	$.ajax({
        		async:true,
        		type:"POST",
        		url:"../user/checkEmail",
        		data:{"email":email},
        		dataType:"json",
        		success:function(data)
        		{
        			if(!data)
        			{
        				 $("#reg_mail_msg").html('恭喜可以使用!');
        				 $("#reg_mail_msg").attr("class","line1px_4");
        		         ckemailtf = true;
        			}else
        			{
        				$("#reg_mail_msg").html('该邮箱已被使用!');
       		         	 ckemailtf = false;
        			}
        		},
        		error:function()
        		{
        			$("#reg_mail_msg").html('&nbsp;系统异常,请刷新网页重试');
        			 $("#reg_mail_msg").attr("class","line1px_3");
   		         	 ckemailtf = false;
        		}
        	});
        }
        if(ckemailtf){
		 $("#reg_mail_msg").attr("class","line1px_4");
		}else{
		  $("#reg_mail_msg").attr("class","line1px_3");
		}	  
    }
 function checkIsEmailNumber(strWord) {
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
        if (reg.test(strWord))
            return true;
        else
            return false;
    }
    
 function CheckPhone(){
 	var phone=$("#reg_phone").val();
 	var reg=/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
 	if(phone!=""&&!reg.test(phone)){
 		$("#reg_phone_msg").html('请输入正确格式的电话号码');
 		$("#reg_phone_msg").attr("class","line1px_3");
 		ckphone=false;
 		return false;
 	}else if(phone==""){
 		$("#reg_phone_msg").html('请输入您的电话号码');
 		$("#reg_phone_msg").attr("class","line1px_3");
 		ckphone=false;
 		return false;
 	}else{
 		$("#reg_phone_msg").html('电话号码输入正确');
 		$("#reg_phone_msg").attr("class","line1px_4");
 		ckphone=true;
 		return true;
 	}
 }
 function CheckQICQ(){
 	var qq=$("#reg_qq").val();
 	var reg=/^\d{5,10}$/; 
 	if(qq!=""&&!reg.test(qq)){
 		$("#reg_qq_msg").html('请输入正确格式的QQ号码');
 		$("#reg_qq_msg").attr("class","line1px_3");
 		ckqq=false;
 		return false;
 	}else if(qq==""){
 		$("#reg_qq_msg").html('请输入您的QQ号码');
 		$("#reg_qq_msg").attr("class","line1px");
 		ckqq=true;
 		return true;
 	}else{
 		$("#reg_qq_msg").html('QQ号码输入正确');
 		$("#reg_qq_msg").attr("class","line1px_4");
 		ckqq=true;
 		return true;
 	}
 }
 
/*  function CheckMobile(){
 	var mobile=$("#reg_mobile").val();
 	var reg=/^(\+86|0086)?\s?\d{11}$/;
 	if(mobile!=""&&!reg.test(mobile)){
 		$("#reg_mobile_msg").html('请输入正确格式的手机号码');
 		ckmobile=false;
 		$("#reg_mobile_msg").attr("class","line1px_3");
 		return false;
 	}else if(mobile==""){
 		$("#reg_mobile_msg").html('请输入您的手机号码');
 		$("#reg_mobile_msg").attr("class","line1px");
 		ckmobile=true;
 		return true;
 	}else{
 		$("#reg_mobile_msg").html('手机号码输入正确');
 		$("#reg_mobile_msg").attr("class","line1px_4");
 		ckmobile=true;
 		return true;
 	}
 } */
 
function CheckName(){
	var log_tun = $("#reg_reallyname").val();
	if (log_tun.length < 2 || log_tun.length > 16||!/^[\u4E00-\u9FA5]{2,5}$/.test(log_tun)) {
				$("#reg_rn_msg").attr("class","line1px_3");
                $("#reg_rn_msg").html("请认真填写真实姓名");
                ckname=false
                return false;
     }else if (!CheckSafe(log_tun)) {
            	$("#reg_rn_msg").attr("class","line1px_3");
                $("#reg_rn_msg").html("真实姓名不能含以下字符:'&<>?%,;:()`~!#$^*{}[]|+-=\"");
                ckname=false
                return false;
     }else{
        	$("#reg_rn_msg").attr("class","line1px_4");
        	ckname=true;
        	$("#reg_rn_msg").html("真实姓名填写正确");
     }
}
function CheckrePwd(){
   	var re_regpwd = $("#re_reg_pwd").val();
	if(ChecknotNull(re_regpwd)){
		if(re_regpwd==$("#reg_pwd").val()){
			$("#re_reg_pwd_msg").html('确认密码填写正确!');
			ckrepwdtf = true;
		}else{
			$("#re_reg_pwd_msg").html('两次密码输入不一致!');
			ckrepwdtf = false;
		}
	}else{
	   $("#re_reg_pwd_msg").html('请填写确认密码!');
	   ckrepwdtf = false;
	}
	if(ckrepwdtf){
		 $("#re_reg_pwd_msg").attr("class","line1px_4");
		}else{
		  $("#re_reg_pwd_msg").attr("class","line1px_3");
		}
}

function CheckSafe(str)
{
	var bads = "'&<>?%,;:()`~!#$^*{}[]|+-=\"";
	for (var i=0;i<bads.length;i++)
	{
		if (str.indexOf(bads.substring(i,i+1)) != -1)
		{
		   return false;
		   break;
		}
	}
	return true;
}

function countLeng(str){
	var count = 0;
	for(var i=0;i<str.length;i++){
		if(str.charCodeAt(i) > 255){ count++; }
		count++;
	}
	return count;
}

//检查用户名
function CheckUserName(){
    var uname = $("#reg_name").val();
	if(CheckSafe(uname)){
	  if(ChecknotNull(uname)){
	  	  var reg = /^[0-9a-zA-Z_]+$/;
		  if(countLeng(uname)>3&&countLeng(uname)<21&&reg.test(uname)){
		   $("#reg_nc_msg").html("程序加载中...");
		   $("#reg_nc_msg").attr("class","line1px_2");
		   $.ajax({type : "get", contentType : "text/html;utf-8", url : "/user/checkUsername", data : {username:uname},dataType:'json',success : function (result){
	
		  if(result==false){
			 $("#reg_nc_msg").html("恭喜，该名字可用");
			 cknametf = true;
			 $("#reg_nc_msg").attr("class","line1px_4");
			 } else{
				 $("#reg_nc_msg").html("该用户名已被注册");
				 $("#reg_nc_msg").attr("class","line1px_3");
				 cknametf = false;
			 }
		  }
				 });
		  }else{
			  $("#reg_nc_msg").html('4-20个字符(包括小写字母、数字)!');
			  cknametf = false;
		  }
	  } else {
		  $("#reg_nc_msg").html('请填写用户名!');
		  cknametf = false;

	  }
	}else{
		$("#reg_nc_msg").html('4-20个字符(包括小写字母、数字)!');
		cknametf = false;
	}
	if(cknametf){ 
	   $("#reg_nc_msg").attr("class","line1px_4");
	} else{
	   $("#reg_nc_msg").attr("class","line1px_3");
	}
}

function registeruser(){
	if(!cknametf){ CheckUserName(); return; }
	if(!ckname){ CheckName(); return; }
	if(!cknametf){ CheckUserName(); return; }
	if(!ckpwdtf) { CheckPassWord(); return; }
	if(!ckrepwdtf){ CheckrePwd();   return; }
	/* if(!ckteltf) { checkTel();      return; } */
	if(!ckemailtf){ checkmail();    return; }
	if(!ckphone){CheckPhone(); return;}
	if(!ckqq){CheckQICQ(); return;}
	/* if(!ckmobile){CheckMobile();return;} */
	var uname = $("#reg_name").val();
	var dataset = {
	    "username"  : uname,
		"password" : $("#reg_pwd").val(),
		"email": $("#reg_mail").val(),
		"phone":$("#reg_phone").val(),
		"mobile":$("#reg_phone").val(),
		"truename":escape($("#reg_reallyname").val()),
		"qq":$("#reg_qq").val()
		};
	$.ajax({type: "get", contentType: "text/html;utf-8", url: "/cart/registerFromCart",dataType:'json',data: dataset, success:function (result){																					
		 if(result){
			 art.dialog({id:'wxregistbox'}).close();
			window.location.reload();
		 }else{
			$("#errmsg").html("注册失败"); 
			$("#errmsg").attr("class","line1px_3");
		 }
		 }
		   });
}
</script>
 <div class="loglf" style="height:280px;"><TABLE width=500 border=0 align="center" cellPadding=5 cellSpacing=0>
  <TR>
    <td width="137" align="right" valign="middle" class="ft14" ><div align="right"><font color="red">*</font>用&nbsp;户&nbsp;名:</div></td>
    <TD width="343" align="left" valign="middle"><div  id="reg_nc2" class="box2_putlink" onmouseover="this.className='box2_puthover'" onmouseout="this.className='box2_putlink'" onmouseup="this.className='box2_puthover'" onmousedown="this.className='box2_puthover'"> 
      <input id="reg_name" type="text" class="box2_input a" value="" onblur="CheckUserName();" /></div> </TD>
  </TR>
  <TR>
    <td align="right" class="ft14" >&nbsp;</td>
    <TD><div class="line1px" id="reg_nc_msg">4-20个字符(包括小写字母、数字)</div></TD>
   </TR>
  <TR>
  <TR>
    <td width="137" align="right" valign="middle" class="ft14" ><div align="right"><font color="red">*</font>真实姓名:</div></td>
    <TD width="343" align="left" valign="middle"><div  id="reg_nc2" class="box2_putlink" onmouseover="this.className='box2_puthover'" onmouseout="this.className='box2_putlink'" onmouseup="this.className='box2_puthover'" onmousedown="this.className='box2_puthover'"> 
      <input id="reg_reallyname" type="text" class="box2_input a" value="" onblur="CheckName();" /></div> </TD>
  </TR>
  <TR>
    <td align="right" class="ft14" >&nbsp;</td>
    <TD><div class="line1px" id="reg_rn_msg">请输入您的真实姓名</div></TD>
   </TR>
  <TR>
    <td width="137" align="right" class="ft14" ><div align="right"><font color="red">*</font> 密&nbsp;&nbsp;&nbsp;&nbsp;码:</div></td>
    <TD width="343" align="left">
      <div id="reg_nc2" class="box2_putlink" onmouseover="this.className='box2_puthover'" onmouseout="this.className='box2_putlink'" onmouseup="this.className='box2_puthover'" onmousedown="this.className='box2_puthover'"> 
      <input  name="reg_pwd"  type="password"  id="reg_pwd" class="box2_input a" value="" onblur="CheckPassWord();" />
      </div>
    </TD>
    </TR>
   <TR>
    <td align="right" class="ft14" >&nbsp;</td>
    <TD><div class="line1px" id="reg_pwd_msg">6~16个字符，包括字母、数字、特殊符号，区分大小写</div></TD>
    </TR>
  <TR>
    <td width="137" align="right" class="ft14"><div align="right"><font color="red">*</font> 确认密码:</div></td>
    <TD width="343"  align="left"> <div  class="box2_putlink" onmouseover="this.className='box2_puthover'" onmouseout="this.className='box2_putlink'" onmouseup="this.className='box2_puthover'" onmousedown="this.className='box2_puthover'">
      <input name="re_reg_pwd" type="password" id="re_reg_pwd" class="box2_input a" value="" onblur="CheckrePwd();" />
</div></TD>
        </TR>
   <TR>
    <td align="right" class="ft14">&nbsp;</td>
    <TD align="left"><div class="line1px" id="re_reg_pwd_msg">请再输入一遍您上面输入的密码<br /></div></TD>
  </TR>

   <TR>
     <td width="137" align="right" class="ft14" ><div align="right"><font color="red">*</font> 邮&nbsp;&nbsp;&nbsp;&nbsp;箱:</div></td>
     <TD width="343" align="left">
       <div  class="box2_putlink" onmouseover="this.className='box2_puthover'" onmouseout="this.className='box2_putlink'" onmouseup="this.className='box2_puthover'" onmousedown="this.className='box2_puthover'"> 
         <INPUT name="reg_mail"  id="reg_mail" class="box2_input a" value="" onblur="CheckMail();" ></div></TD>
   </TR>
    <TR>
     <td align="right" class="ft14" >&nbsp;</td>
     <TD align="left"><DIV id="reg_mail_msg" class="line1px">&nbsp;请输入您的邮箱地址</DIV></TD>
     </TR>
      <TR>
    <td width="137" align="right" class="ft14" ><div align="right"><font color="red">*</font> 电&nbsp;&nbsp;&nbsp;&nbsp;话:</div></td>
    <TD width="343" align="left">
      <div class="box2_putlink" onmouseover="this.className='box2_puthover'" onmouseout="this.className='box2_putlink'" onmouseup="this.className='box2_puthover'" onmousedown="this.className='box2_puthover'"> 
      <input  name="reg_phone"  type="text"  id="reg_phone" class="box2_input a" value="" onblur="CheckPhone();" />
      </div>
    </TD>
    </TR>
   <TR>
    <td align="right" class="ft14" >&nbsp;</td>
    <TD><div class="line1px" id="reg_phone_msg">请输入您的电话号码</div></TD>
    </TR>
     <TR>
    <td width="137" align="right" class="ft14" ><div align="right">&nbsp;&nbsp;&nbsp;&nbsp;QQ:</div></td>
    <TD width="343" align="left">
      <div class="box2_putlink" onmouseover="this.className='box2_puthover'" onmouseout="this.className='box2_putlink'" onmouseup="this.className='box2_puthover'" onmousedown="this.className='box2_puthover'"> 
      <input  name="reg_qq"  type="text"  id="reg_qq" class="box2_input a" value="" onblur="CheckQICQ();" />
      </div>
    </TD>
    </TR>
   <TR>
    <td align="right" class="ft14" >&nbsp;</td>
    <TD><div class="line1px" id="reg_qq_msg">请输入您的QQ号码</div></TD>
    </TR>
   <tr>
     <td align="center"  ></td>
     <td  align="center"><span class="zcbn"><a href="javascript:;" onclick="registeruser();">注 册</a></span> </td>
     </tr>
  
  </TBODY>
</TABLE></div>
<div class="clear"></div>

