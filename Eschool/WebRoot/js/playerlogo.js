window.status="";
(function(){
	var host = location.host,
		path = location.pathname.toLowerCase();
	
	if(host && host.indexOf("edu24ol.com")<0){
		
		//��Ҫ����logo����Դվ��������logoͼƬ��ַ
		var sites = [
			{site:"kc24.exam8.com",name:"���԰�",
			 urls:["http://home.exam8.com/ks.html","http://www.exam8.com/shop/","http://bbs.exam8.com","http://www.exam8.com/zhenti/"],
			 linkHost:"kc.exam8.com",
			 chatUrl:"http://b.qq.com/webc.htm?new=0&sid=4000888566&eid=218808P8z8p8P8P8p8Q8z&o=www.exam8.com&q=7",		//�����ַ
			 signUpUrl:"http://wangxiao.exam8.com/OpenShopCart.asp?JigouID=1&lesson_type_code=@lessonUp&Class_Code=@classId",
			 logo:["/images2011/exam8_logo_150_70_b.png",
				   "/images2011/exam8_logo_115_45.png",
			 	   "/images2011/exam8_logo_45_55.png",
			 	   "/images2011/exam8_logo_150_70.png",
				   "/images2011/exam8_url.jpg"]}
		];
		//��Ҫ����logo��ҳ�棬ÿһ��ҳ���logoԪ�ز��ҷ�ʽ��ͬ
		var pages = [["lessonplay.asp","demoroom.asp","w_lessonplay.asp","h_lessonplay.asp","mp4player_h.asp"],["lessonplay_2009.asp","lessonplay_w_2009.asp","mp4player.asp","mp4player_w.asp"],["mp4player.aspx","hdvplayer.aspx"]];
		
		for(var j = 0;j<sites.length;j++){
			if(host==sites[j].site){
				for(var i = 0;i<pages.length;i++){
					if(contain(pages[i],path)){
						var logoEle ;
						switch(i){
							case 0:
								logoEle = document.body.children[0].rows[0].cells[0].children[0].rows[0].cells[0].children[0];
								break;
							case 1:
								if(contain(pages[2],path)==false){		//mp4player.aspx��mp4player.asp�ַ���������Ҳ��Ϊ0����ֹ����
									logoEle = document.body.children[0].rows[0].cells[0].children[0].children[0];
									var ele = document.body.children[0].rows[2].cells[0].children[0];
									if(path.indexOf("lessonplay_2009.asp")>0){
										ele = document.body.children[0].rows[3].cells[0].children[0];
									}
									if(ele){
										ele.style.backgroundImage = "url("+sites[j].logo[4]+")";
									}
								}
								break;
							case 2:
								if($.browser.msie&&parseInt($.browser.version)<9)		//����΢���ie��ע��Ҳ���ڵ���
									logoEle = document.forms[0].children[3].children[0].children[0].children[0];
								else
									logoEle = document.forms[0].children[2].children[0].children[0].children[0];
								break;
						}
						if(logoEle){
							if(i==2){
								logoEle.style.backgroundImage = "url("+sites[j].logo[i]+")";
							}else
								logoEle.src=sites[j].logo[i];
								
							if(i==1){
								logoEle.style.marginTop = "26px";
							}
							
							if(contain(["h_lessonplay.asp","mp4player_h.asp"],path)) 
								logoEle.src = sites[j].logo[3];
								
							logoEle.title = sites[j].name;
						}
						changeA(sites[j]);
						changeShowInfo();
					}
				}
			}
		}
	}
})();
function changeA(site){
	var aa = document.getElementsByTagName("a"),a,index,
		noUse = ["http://ask.edu24ol.com","http://blog.edu24ol.com"],
		needChange = ["/exam/","/web_book/","bbs.edu24ol.com","/web_news/index_exam.htm","/web_news/exam_more.asp"],
		noLinkHost = ["lessonplay.asp","lessonplay_2009.asp","lessonplay_w_2009.asp","w_lessonplay.asp","mp4player.asp","mp4player_h.asp","mp4player_w.asp","/sound/upCourseaware/","/help/tech_question.htm","/leraning/"];
	for(var i = 0;i<aa.length;i++){
		a = aa[i];
		//�ϰ岥������������ƶ���ʾ������Уȥ��
		if(a.className=="list" && typeof(a.onmousemove)=="function"){
			a.onmousemove = function(){window.status = "";};
		}
		//�����������ӵ�a��ǩ������
		if(contain(noUse,a.href)||a.innerHTML.indexOf("��ʦ����")>=0){
			a.removeAttribute("href");
			a.removeAttribute("target");
			a.style.color="gray";
			a.onclick=function(){};
		}
		//��Ҫ���������ӵ�ַ
		index = contain(needChange,a.href.toLowerCase(),true);
		if(index > -1 && site){
			if(index==needChange.length-1)
				index--;
			a.href=site.urls[index];
		}
		//������ַ�޸�
		if(a.href.toLowerCase().indexOf("classresultlist.asp")>=0){
			try{
				//var lessonId = QueryStr("lessonid",questionUrl);
				var lessonUp = document.getElementById("lessonUp");
				lessonUp = lessonUp?lessonUp.value:"";
				var classId = QueryStr("ids",a.href);
				a.href = site.signUpUrl.replace("@lessonUp",lessonUp).replace("@classId",classId);
			}catch(e){}
		}
		//�Ƕ������Ĳ������ӵ�������ҳ������Ӷ�������վ����������ַ
//		if(site.linkHost&&a.href&&contain(noLinkHost,a.href)==false&&location.href.indexOf("h_lessonplay.asp")<0){
//			a.href = a.href.replace(site.site,site.linkHost);
//		}
		if(a.href=="javascript:looyu()"){
			a.href="javascript:void(0);";
			a.onclick=function(){
				window.open(site.chatUrl, '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');
			};
		}
		if(a.innerHTML=="������"){
			a.onclick=function(){
				window.open("http://"+site.site+"/webtools/counter/count.htm",'_blank','width=400,height=215,top=260,left=260');
			}
		}
	}
}
function changeShowInfo(){
	if(document.getElementById("showinfo")){
		var node = document.getElementById("showinfo").parentNode;
		node.innerHTML = node.innerHTML.replace("������У..","");
	}
}
function contain(array,ele,needIndex){
	for(var i = 0;i<array.length;i++){
		if(ele.indexOf(array[i])>=0) return needIndex ? i: true;
	}
	return needIndex?-1:false;
}
function QueryStr(qs,str) {
	str = str||location.search;
    var svalue = str.match(new RegExp("[\?\&]" + qs + "=([^\&]*)(\&?)", "i"));
    return svalue ? svalue[1] : "";
}