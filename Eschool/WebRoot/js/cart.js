$(function(){
	/* window.onerror=function(){ return true; }
	userName = getCookie("jsusername");
	if(userName != null && userName != "" && userName.length > 0){
		$("#logintop").html(' <div class="lgjg"><a href="/search/UserCenter/"><strong>'+userName+'</strong></a>,����! &nbsp;  <a href="javascript:;" onclick="ulogout();">�˳�</a></div>');
	}
	buynum()
	$("img:not([name='topbm'])").lazyload({
		placeholder : "http://img.233.com/www/img/cy/2012_3/space.gif",				  
		effect : "fadeIn"
	});  */
	MyPackageIDCookie = getCookie('package=MyPkgID');
	MyGradeIDCookie = getCookie('grade=MyGradeID');
	$(".bmbg[myclassid]").each(function(){
		if( MyPackageIDCookieTF($(this).attr("myclassid")) == true){
			$(this).attr("class","bmbg2");
		}
		if( MyGradeIDCookieTF($(this).attr("myclassid")) == true){
			$(this).attr("class","bmbg2");
		}
	});
});
	//myid:�ײͻ��߰༶id��o��dom����type:����0���ײͣ�1�ǵ��༶
	function CourseSelect(myid,o,type){
	var len = 0,price = 0,obj = $(o),tit = "";
	//�ײ�cookie
	var MyPackageIDCookie = getCookie('package=MyPkgID');
	//�༶cookie
	var MyGradeIDCookie = getCookie('grade=MyGradeID');
	if(obj.attr("class") == 'bmbg'){
		if(type==0)
		{
			if(MyPackageIDCookie==''){
				setCookie('package=MyPkgID',myid,15);
			}else{
				setCookie('package=MyPkgID',MyPackageIDCookie+','+myid,15);
			}
			obj.attr("class","bmbg2");
			$("#gkbox_img").attr("class","chen").html("�ɹ�");
			tit = "�γ��ѳɹ���ӵ����γ���";
		}else
		{
			if(MyGradeIDCookie==''){
				setCookie('grade=MyGradeID',myid,15);
			}else{
				setCookie('grade=MyGradeID',MyGradeIDCookie+','+myid,15);
			}
			obj.attr("class","bmbg2");
			$("#gkbox_img").attr("class","chen").html("�ɹ�");
			tit = "�γ��ѳɹ���ӵ����γ���";
		}
	}else{
		if(type==0)
		{
			var reg=new RegExp((myid+","),"g");
			MyPackageIDCookie = (MyPackageIDCookie+',').replace(reg,"");
			if(MyPackageIDCookie.substr(MyPackageIDCookie.length-1)==","){
			MyPackageIDCookie = MyPackageIDCookie.substr(0,MyPackageIDCookie.length-1);
			}
			setCookie('package=MyPkgID',MyPackageIDCookie,15);
			obj.attr("class","bmbg");
			$("#gkbox_img").attr("class","dele").html("ɾ��");
			tit = "�ÿγ��ѳɹ��ӹ��ﳵɾ����";
		}else
		{
			var reg=new RegExp((myid+","),"g");
			MyGradeIDCookie = (MyGradeIDCookie+',').replace(reg,"");
			if(MyGradeIDCookie.substr(MyGradeIDCookie.length-1)==","){
			MyGradeIDCookie = MyGradeIDCookie.substr(0,MyGradeIDCookie.length-1);
			}
			setCookie('grade=MyGradeID',MyGradeIDCookie,15);
			obj.attr("class","bmbg");
			$("#gkbox_img").attr("class","dele").html("ɾ��");
			tit = "�ÿγ��ѳɹ��ӹ��ﳵɾ����";
		}
	}
	$("div.bmbg2[myclassid]").each(function(){
		len += 1;
		price += parseInt($(this).attr("price"));
	});
	$("#gkbox_num").html(len);
	$("#gkbox_Price").html(price);
	$("#gkbox_tit").html(tit);
	$("#gkbox").css({ left: "" + (obj.offset().left - 342) + "px", top: "" + (obj.position().top - 40) + "px" }).show();
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
function MyPackageIDCookieTF(mid){
	if((","+MyPackageIDCookie+",").indexOf(","+mid+",")==-1){
		return false;
	}else{
		return true;
	}
}
function MyGradeIDCookieTF(mid){
	if((","+MyGradeIDCookie+",").indexOf(","+mid+",")==-1){
		return false;
	}else{
		return true;
	}
}