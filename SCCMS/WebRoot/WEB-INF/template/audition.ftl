<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>������У-${pkgName}-${g.examCategory.examName}${g.gradeCategory.gtypeName}����:${g.teacher.tchName} - ${classDetail.classTitle}</title>
<link rel="stylesheet" type="text/css" href="/common/images/common.css">
<link href="/main/css/play.css" type="text/css" rel="stylesheet" />
<script src="../js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="../js/jwplayer.min.js" type="text/javascript"></script>
<script type='text/javascript' src='/artDialog/jquery.artDialog.js?skin=blue'></script> 
<body>
<div class="top">
  <div class="harder">
    <div class="logo"><a href="javascript:;"><img src="/common/images/logo.png" alt="������У"/></a></div>
    <div style="z-index: 9999; position: relative;" class="logoFull">
        <div class="area">
            <div id="area-all">
                <ul>
                	<s:if test="#session.student==null">
                    <li><a id="registbox" onclick="showRegbox()" title="ע����У��Ա" href="javascript:;"><span class="wn">���ע��</span></a></li>
                    <li>|</li>
                    <li><a id="loginbox" onclick="ShowLogin()" title="��¼��У" href="javascript:;">
                        <span class="wn">�û���¼</span></a></li>
                    </s:if>
                    <s:else>
                    	<li style="color: #FFF;"><span class="wn">hi,<s:property value="#session.student.stuUsername"/>&nbsp;&nbsp;<em title="��ȫ�˳������������ʺŰ�ȫ"
                        id="tuichu">�˳�</em></span></li>
                		<li>|</li>
                		<li><a class="cblue3" target="_blank" href="/memberCenter/member.jsp"><span class="wn"><strong>��Ա����</strong></span></a></li>
                    </s:else>
                    <li id="Myrecords" class="out" onmouseover="this.className='on'" onmouseout="this.className='out'">
                        <a href="javascript:void(0)"><span class="bfjl">���ż�¼</span></a>
                        <div class="lnavp" id="Playrecords">
                           <ul class="navlist">
                           </ul>
                        </div>
                    </li>
                    <li id="Myrecords1" class="out" onmouseover="this.className='on'" onmouseout="this.className='out'">
                        <a href="javascript:void(0)"><span class="gwc">���γ�</span></a>
                        <div class="lnavp" id="Playrecords">
                            <ul class="navlist" id="myshop">
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="clear"></div>
  </div>
</div>

<!--banner����-->
<div class="videoBg">
<!--videoArea��ʼ-->
	<div class="videoArea">
    <h1><span class="f14 fb whiteTxt fl">${examName}:${pkgName}${g.examCategory.examName}${g.gradeCategory.gtypeName}:</span><span class="mb10 orangeTxt fl">${classDetail.classTitle}</span>
    <span class="mb10 orangeTxt fl"><a id="hlkWideVedio" style="color: red;" href="/member/classroom?classId=${classDetail.classId}">&nbsp;&nbsp;������</a>
    <a id="hlkAudio" style="color: red;" href="/member/classroomHD?classId=${classDetail.classId}">����</a></span>
    <span class="fr whiteTxt">  <a class="paid"  onclick='CourseSelect();' >��������</a></span></h1>
    <!--videoFlash��ʼ-->
    <div class="videoFlash">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#f1f9fb">
            <tr>
                <td id="hdvplayer" style="width: 860px; height: 508px;">
                    <!-- <div id="playerContent" style="width: 100%; height: 100%;"> -->
                    <IFRAME id="playerContent" frameBorder=0 width="860" height="508"  scrolling="no"></IFRAME>
                    <!-- </div> -->
                </td>
                <td>
                </td>
            </tr>
        </table>
    </div>
    <!--videoFlash����-->
     <!--videoFunction��ʼ-->
 	<div class="videoFunction">
    <a href="javascript:void(0)" class="n_left" id="n_left">��һ��</a>
    <a href="javascript:void(0)" class="n_right" id="n_right">��һ��</a>
    <a href="javascript:void(0)" class="n_right"  id="treelist">Ŀ&nbsp;¼</a>
    <a href="javascript:void(0)" id="donwloadbt" class="n_right"  style="font-weight: bold;">��&nbsp;��</a>
    <a onclick='CourseSelect();'  class="buyIt"></a>
    </div>
    <!--videoFunction����-->
 	</div>  
 
    <div class="colist_c" id="MyChapter" style="display:none"></div>
    
    
    <div class="logingc" id="wxregistbox" style="height:530px;display:none;"></div>
    <div class="loginStr" id="wxlogbox" style="height:249px;display:none;"></div>
<div id="foot"></div>
</div>
<script type="text/javascript">
 	$(function(){
			//����ajax����,����new.jsp��Ϣ
			$("#foot").load("/system/webInfo");
		});
		var examwplay;
		var gradeId="${g.gradeId}";
		var classId="${classDetail.classId}";
		var nextdid = "${nextId}";
		var lastdid = "${prevId}";
		var Playrecordstr = "";
		var videotime = 0;	
		var loginSate=0;
		var logindialog;
		var HaveDetailTF= "${classDetail.classHdUrl}";
		var MyClassCName = "${g.examCategory.examName}-${g.gradeCategory.gtypeName}";
		var MyClassTitle = "${classDetail.classTitle}";
		var tingTF = "${classDetail.classIfFree}";
		var Playrecordstr = "";
		var videotime = 0;
		var payflag = ${payflag};
		var t_name="${g.teacher.tchName}";
		var userName ="<s:property value="#session.student.stuUsername"/>";
		var isMaxthon = navigator.userAgent.indexOf("Maxthon")>-1;


$(document).ready(function(){	
		    if(userName==""){
	    		loginSate=0;
	    	}else{
	    		loginSate=1;
	    	}
	    	GetChapterList();
	    	//���ż�¼
	    	LoadPlayrecords();
	    	//�ж��Ƿ���ÿγ�
	    	 if(payflag==1){ 
            $(".paid,.buyIt").css("display","none");
            
        	}
	    	//���ﳵ���㴥���¼�
	    	$("#Myrecords1").hover(function() {
            if ($("#myshop").find("li").size() == 0) {
                $("#myshop").html("<li style=\"text-align:center; margin:30px 0px; \"><img src=\"/main/images/loading24.gif\"><\/li>");
                GetAllECart(0);
            } 
        	});
        	//ע��
        	$("#tuichu").click(function(){
        		loginOut();
       
        	}); 
		
			if(HaveDetailTF==""){
			playTipsObj = art.dialog({
				title:"��ܰ��ʾ",
				content:" <div class=\"aui-boxt\"><div class=\"aui_iconBgx\"></div><div class=\"bq\">�����γ��������ݣ����Ժ�����ѧϰ!</div></div>",
				zIndex : 18008,
				lock  : true,
				resize:false,
				fixed:true
			});
			}else{
			if(tingTF=="1"||payflag==1){
				StartPlay();
			}else{
				playTipsObj = art.dialog({
					title:"��ܰ��ʾ",
					content:" <div class=\"aui-boxt\"><div class=\"aui_iconBgx\"></div><div class=\"bq\">��Ǹ�������γ�Ϊ�շѿγ̣��빺��ÿγ̺�����ѧϰ!<br>������ѯ��4000-525-585</div></div>",
					zIndex : 18008,
					lock  : true,
					resize:false,
					fixed:true
					});
				}
			}
		
		$("#n_right").click(function(){
			if(nextdid!=0)
				window.location = "/member/classroomHD?classId="+nextdid ;
			else 
				notice("û����һ���ˣ���ѡ�������Ŀμ�ѧϰ��",3);   
		});
		$("#n_left").click(function(){
			if(lastdid!=0)				
				window.location = "/member/classroomHD?classId="+lastdid ;
			else 
				notice("û����һ���ˣ���ѡ�������Ŀμ�ѧϰ��",3); 
		});	
		$("#treelist").click(function(){
			playTipsObj = art.dialog({
				title:"�½��б�",
				content:$("#MyChapter")[0],
				zIndex : 18008,
				lock  : true,
				resize:false,
				fixed:true,
				padding:0
			}); 
		});
 
	});	 
	//Ŀ¼
	function GetChapterList()
	{
		$("#MyChapter").html("<img src=\"../artDialog/skins/icons/loading.png\">");
		$.ajax({
			type:"get",
			contentType:"text/html;utf-8",
			url:"/member/contents",
			data:{gradeId:gradeId,payflag:payflag},
			dataType: 'json',
			success: function (result){
				var html = "",state="",childHtml="",childListNO=0,temp=0,lname="";
				var wordlength=36;
				if(result.length!=0)
				{
					/* html = "<h3>"+MyClassCName+"����"+result.length+"�����Ѹ��µ���"+5+"��"+ ((2>0)?"�γ�¼���С���":"�������")+"��</h3>"; */
					html+="</div>";
					html+="<div class=\"contant\"><div class=\"copli\" style=\"height:400px;margin:0px;padding:0px;\"><ul class=\"List\" style=\"height:390px;overflow:auto;margin:0px;padding:0px;width:100%\">";
					for (i=0;i<result.length;i++){
						childHtml="";
						/* if (result.List[i].List.length>0){
							for (n=0;n<result.List[i].List.length;n++){ */
								
								isFree=(result[i].classIfFree==1)?"<span class=\"free\">���</span>":"";
								childListNO++;
								playIcon = "";
								if(result[i].classId==classId)
								{
									playIcon = "bg";
									temp = i;
								}
								//playIcon=result.List[i].List[n].classId==classId?'bg':'';
								lname = unescape(result[i].classTitle);
								lname= (StrLength(lname)-wordlength>0)?lname.substring(0,wordlength)+"...":lname;
								playIcon=childHtml+='<li><a href="/member/classroomHD?classId='+result[i].classId+'" title="'+unescape(result[i].classTitle)+'" classId="'+result[i].classId+'" class="'+playIcon+'"  target="_self"><span>'+(parseInt(childListNO)<10?"0"+parseInt(childListNO):parseInt(childListNO))+'��'+lname+'</span>'+isFree+'</a></li>';
							/*}
						 }else{
							childHtml='';
						} */
						if(childHtml==''){
							html+='<li style="display:none"><a class="expanded" ></a><ul class="navigation">'+childHtml+'</ul></li>';
						}else{
							html+='<li><a  ></a><ul class="navigation">'+childHtml+'</ul></li>';
						}
					}
					html+="</ul><div style=\"clear:both\"></div></div>";
					//html+="</ul><div style=\"clear:both\"></div></div></div>";
				}
				else
				{
					html = "<h3>"+MyClassCName+" ����ʧ�� <a href=\"javascript:;\" onclick=\"GetChapterList()\">������¼���</a>";
				}
				$("#MyChapter").html(html);
				$(".nav > li > a").click(function() {
					$(this).toggleClass('expanded').toggleClass('collapsed').parent().find('ul').slideToggle('medium');
				});
			},
			error: function (e) {/*������*/},
			async:true,
			cache:false
		});
	}
	//���ż�¼
	function LoadPlayrecords() {
	    	if(loginSate==1){
	    		if (Playrecordstr == "") {
		       		$("#Playrecords").html('<div class="jltxt"><img src="../artDialog/skins/icons/loading.png"></div>');
		       		var html="";
		       		$.post(
		       			"/course/studyRecordList",
		       			function (studyRecord){
							if(studyRecord!=null){
								if(studyRecord.length>0){
									for(var i=0;i<studyRecord.length;i++){
										if(i==0){
											html +="<li class='ico2'>"+studyRecord[i].recordName+"<li><font style='color:#ff0000'>���ڲ���...<\/font><\/>";
										}else{
											html +="<li class='ico'>"+studyRecord[i].recordName+"<\/li><li>�ϴ�"+studyRecord[i].recordStartTime+"&nbsp;<a href='?classId="+studyRecord[i].classId+"'>��������...<\/a><\/li>";
										}
									}
									$("#Playrecords").html('<ul class="navlist"><li><a class="remove" href="javascript:;" onclick="javascript:clearPlayrecords();">�����¼</a></li>' + html + '</ul>');
								}else{
									$("#Playrecords").html("<ul class=\"navlist\"><li>����ʱû��ѧϰ��¼<\/li><\/ul>");
								}
							}else{
								$("#Playrecords").html("<ul class=\"navlist\"><li>ϵͳ�쳣���Ժ�����<\/li><\/ul>");
							}
						}
		       		);

	    	}
	    	
	    	}else{
	    		 $("#Playrecords").html("<ul class=\"navlist\"><li>����δ��¼,��¼�鿴����ѧϰ��¼<\/li><\/ul>");
	    	}
		} 
	
	function clearPlayrecords() {
		$.post("/course/studyRecordDelete", 
		    function(ok) {
		    	if(ok==true){
		    		$("#Playrecords").html('<ul class="navlist"><li><a class="remove" href="javascript:;" onclick="javascript:clearPlayrecords();">�����¼</a></li><li style="margin-top:5px;text-align:center;padding:10px 0px;">���Ĺۿ���ʷ����ա�</li></ul>')
		    	}	
		    });
		}
	    
	    //���ﳵ�б�
     	function GetAllECart(type) {
        var taboy = "";
        var html="";
        var pricecount = 0;
        var price = 0;
        if(loginSate==1){
            $.ajax({
                type: "GET",
                url: "/cart/simpleList",
                dataType: "json",
                cache: false,
                success: function(result) {
                    if (result.length==0) {
                        taboy = "<li class='ico2'>���Ĺ��ﳵ��û���κογ̣�<\/li>"; 
                    }
                    if (result.length != 0) {
                        if (result.length > 0) {
                            for (var i = 0; i < result.length; i++) {
                                taboy += "<li class='ico2'>" + unescape(result[i].item.itemName) + "<\/li><li><em class='lf'>��" + result[i].item.itemRPrice + "<\/em><a class='rt' onclick='DelShop("+result[i].item.productId+","+result[i].item.itemPType+")' href='javascript:void(0);'>ɾ��<\/a><\/li>";
                                price = price + parseInt(result[i].item.itemRPrice);
                                pricecount = pricecount + 1;
                                html=html+result[i].item.productId+",";
                                
                            };
                        } ;
                        /* setCookie("WangXiao=MyClassID",result.listMyCID,30);   */
                    }
                    if(html==""){
                      	html="0";
                    }
                    else{
                     	html=html.substring(0,html.length-1);	
                    } 
                    taboy = "<li><a class='remove' href='javascript:;' onclick='removeAll()'>�����¼<\/a><\/li>"+taboy+"<li class='settl'><a href='/cart/cartList' class='set' target='_blank'>ȥ���ﳵ����<\/a><\/li>";  
                    $("#myshop").html(taboy);
                    if(type=="1"){                
                    $("#kcnum").html(pricecount);
                    $(".tb-price").html(price);                        
                    $(".tb-cart").css("display","block");
                  }
                }
            });
        }else{
            $("#myshop").html("<li>���γ��ﻹû�пγ�<\/li>");
        }
    }
    
    //ɾ��ָ����Ŀ
    function DelShop(id,pType)
	{
		if(loginSate==1){
		var content="",width=0;   
        content="��ȷ�����γ̴ӹ��γ��Ƴ���";
        width=300;
        art.dialog({
            title: "��ܰ��ʾ",
            content: "<span style=\"font-size:14px;\">"+content+"</span>",
            zIndex: 18008,
            icon: "question",
            width: 300,
            lock: true,
            resize: false,
            fixed: true,
            button: [{ name: "ȷ��", callback: function() { 
                $.ajax({
                    type: "POST",
                    async: true,
                    dataType: "json",
                    url: "/cart/deleteItem",  
                    data: {"productId":id,"pType":pType},
                    success: function(json) {
                        if (json.ok == 1) {
						 //�޸�cookie��ֵ
                           setCookie("package",json.packageIds, 30);
                           setCookie("grade",json.gradeIds, 30);
                           GetAllECart(0);
                        }else{
                            notice("�Ƴ�ʧ��", 2,100);
                        }
                    }
                });
            }}, { name: "ȡ��"}]
        });
	  }else {
            LoginTishi("����û�е�¼�����ȵ�¼��ע���л���������ѻ�Ա�ٲ�����");
      }
	}
	//��չ��ﳵ
	function removeAll()
    {
    	art.dialog({
            title: "��ܰ��ʾ",
            content: "<span style=\"font-size:14px;\">"+"ȷ��Ҫɾ�����ﳵ�е�ȫ���γ�"+"</span>",
            zIndex: 18008,
            icon: "question",
            width: 300,
            lock: true,
            resize: false,
            fixed: true,
            button: [{ name: "ȷ��", callback: function() { 
    			$.ajax({
    			type:"POST",
    			async:true,
    			dataType:"json",
    			url:"/cart/removeAll",
    			success:function(data){
    			if(data)
    			{
    				//���
                    setCookie("package","",0);
                    setCookie("grade","",0);
                    GetAllECart(0);
    			}
    		}
    	}); }}, { name: "ȡ��"}]});
    }
  
    //�˳�
	 function loginOut()
    {
    	$.ajax({
    		async:true,
    		type:"POST",
    		dataType:'json',
    		url:"/user/loginOut",
    		success:function(data)
    		{
    			if(data)
    			{
					location.reload();
    			}else
    			{
    				notice("�ǳ�ʧ��",2,100);
    			}
    		},
    		error:function()
    		{
    			notice("ϵͳ�쳣",2,100);
    		}
    		
    	});
    }
  	//��¼
 	function ShowLogin(){
		$("#wxlogbox").load("/common/login.jsp")
			var loginbox = art.dialog({ id: "loginbox", content: $("#wxlogbox")[0], lock: true, padding: 10, opacity: 0.5, fixed: true, title: "��¼��У" });
			return false;
    }
    //ע��
	function showRegbox(){
		$("#wxregistbox").load("/common/regist.jsp")
			var wxregistbox = art.dialog({ id: "wxregistbox", content: $("#wxregistbox")[0], lock: true, padding: 5, opacity: 0.5, fixed: true, title: "ע����У" });
			return false;
	}

	 //���� 
	 function CourseSelect(){
	 	//�ײ�cookie
		var MyPackageIDCookie = getCookie('package');
		//�༶cookie
		var MyGradeIDCookie = getCookie('grade');
	 	var pkgId="${pkgId}";
	 	if(pkgId==""){
	 		var myid=${g.gradeId};
	 		if(MyGradeIDCookie==''){
				setCookie('grade',myid,15);
			}else{
				setCookie('grade',MyGradeIDCookie+','+myid,15);
			};
			window.open("/cart/cartList");
	 	}else{
	 		myid=parseInt(pkgId);
	 		if(MyPackageIDCookie==''){
				setCookie('package',myid,15);
			}else{
				setCookie('package',MyPackageIDCookie+','+myid,15);
			};
			window.open("/cart/cartList");
	 	}
	} 
    //cookies
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
	//������
	function StartPlay()
	{
		player = jwplayer("playerContent").setup({
			    flashplayer: '/js/player.swf',			   
                'file':HaveDetailTF ,
				width: '100%',
				height: '100%',
				controlbar: 'bottom',
				image:'/main/images/bg2.png',
				"skin":"/js/stormtrooper.zip",	
				provider: 'http',
				autostart: 'true',
				'stretching': 'fill'
            });
    
     	 
  /*    player =jwplayer('playerContent').setup({
    'flashplayer': '../js/player.swf',
    'file': '974775C2453576C5-1.flv',
    'streamer': 'rtmpt://192.168.1.236:5080/oflaDemo',
    'stretching': 'fill',
    'width': '862',
    'height': '510',
     dock: false 
  	}); */
			
	}
	//����
	function StrLength(str){
		var i,sum;
		sum=0;
		for(i=0;i<str.length;i++){
			if ((str.charCodeAt(i)>=0) && (str.charCodeAt(i)<=255))
			sum=sum+1;
			else{
				sum=sum+2;
			}
		}
		return sum;
	}
	//��ʾ��
  	function notice(content,t)
	{
		dianotice = art.dialog({id:"notice",content:"<div style=\"white-space:nowrap;\">"+content+"</div>",icon:"warning",lock:true,opacity:0.1,time:t,title: false,fixed:true,zIndex : 18008});
	}
	//��ʾ��
	function loading(content)
	{
		dialoading = art.dialog({id:"loading123",content:"<div style=\"white-space:nowrap;\">"+content+"</div>",icon:"loading",cancel:false,title: false,fixed:true,zIndex : 18008});
	}
	//��ʾ��
	function succeed(content,t)
	{
		artdialog = art.dialog({id:"artdialog",content:"<div style=\"white-space:nowrap;\">"+content+"</div>",icon:"succeed",lock:true,opacity:0.1,time:t,fixed:true,title: false,zIndex : 18008});
	}
</script>          
</body>
</html>
