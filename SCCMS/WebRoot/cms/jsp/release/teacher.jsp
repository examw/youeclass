<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/cms/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/cms/css/sliver/all.css" rel="stylesheet" type="text/css" /> 
    <script src="${pageContext.request.contextPath}/cms/js/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/cms/js/base.js" type="text/javascript"></script>
   	<script src="${pageContext.request.contextPath}/cms/js/ligerLayout.js" type="text/javascript"></script>
   	<script src="${pageContext.request.contextPath}/cms/js/ligerTree.js" type="text/javascript"></script>
   	<script src="${pageContext.request.contextPath}/cms/js/ligerTab.js" type="text/javascript"></script>
   	<script src="${pageContext.request.contextPath}/cms/js/ligerDialog2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/cms/js/ligerMenu.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/cms/js/ligerDrag.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/cms/js/LG.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/cms/js/ligerAccordion.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/cms/css/common.css" rel="stylesheet" type="text/css" />  
    <script src="${pageContext.request.contextPath}/cms/js/common.js" type="text/javascript"></script>  

    <style type="text/css">
    .l-panel td.l-grid-row-cell-editing { padding-bottom: 2px;padding-top: 2px;}
        .l-table-edit {margin-top:180px}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    </style>
</head>
<body style="padding:2px;text-align:center;">
  <div id="layout1">
  	<div position="left">
  	<div id="accordion1" > 
	      <div title="栏目" id="lesson">
              			<div id="maintree"></div>
        </div>	
  	</div> 
  	</div>    
    <div position="center" title="" > 
    <div id="layout2">
    	<div position="left" id="left" title="老师">
		<div id="teacher"></div>
		<div id="button"></div>
    	</div>
    	<div position="center" title="操作" > 
    		<center>
		   	<table cellpadding="0" cellspacing="0" class="l-table-edit">
		   		<tr>
		            	<td><input type="button" value="发布" class="l-button l-button-reset" onclick="f_release()"/></td>
		            	<td><input type="button" value="预览" id="Button1" class="l-button l-button-submit" onclick="f_preview()"/></td>
		        </tr>  
		   	</table>
    		</center>
    	</div>
    </div>
    </div>
  </div>
<script type="text/javascript">
	 function f_heightChanged(options) {
            if (accordion && options.middleHeight - 24 > 0)
                accordion.setHeight(options.middleHeight - 24);
        }
	$(function ()
           { 
				layout = $("#layout1").ligerLayout({ height: '100%', leftWidth: 220, onHeightChanged: f_heightChanged, minLeftWidth: 120 });
				layout2 = $("#layout2").ligerLayout({ height: '100%', leftWidth: 220, onHeightChanged: f_heightChanged, minLeftWidth: 120 });
          		var bodyHeight = $(".l-layout-center:first").height();
                accordion = $("#accordion1").ligerAccordion({ height: bodyHeight-24, speed: null ,changeHeightOnResize:true});
                
          });
      //验证
	 $(function ()
            { 
            //layout
            layout = $("#layout").ligerLayout({ height: '100%', heightDiff: -3, leftWidth: 190, minLeftWidth: 120 });
             layout1 = $("#layout1").ligerLayout();
            });
    
      //覆盖本页面grid的loading效果
    LG.overrideGridLoading(); 
    var currentMenuParentNo,data,manager;
    tree1=$("#maintree").ligerTree(
            {
                checkbox:false, 
                treeLine :true,
                icon: 'archives',
                nodeWidth :100,   
                slide :false,
                url: "${pageContext.request.contextPath}/exam/examTree",
                textFieldName:"examName",
                idFieldName:"examId",
                parentIDFieldName:"examPid",
                btnClickToToggleOnly:false  ,
                onClick: function (node)
		        {
		        },
		        onSelect:function(node) 
            	{	
            		if(node.data.children!=null) 
            		{
            			return;
            		}
            		f_teacher(node.data.examId);
            	}           
            });
            
    //加载老师
    function f_teacher(examId){
    	$("#teacher").html("");
    	$("#button").html("");
    	tree2=$("#teacher").ligerTree(
            {
                checkbox:true, 
                treeLine :true,
                icon: 'archives',
                nodeWidth :100,   
                slide :false,
                url: "${pageContext.request.contextPath}/grade/classTeacher?examPid="+examId+"&examId="+examId,
                textFieldName:"tchName",
                idFieldName:"tchId",
                btnClickToToggleOnly:false  ,
                onClick: function (node)
		        {
		        },
		        onSelect:function(node) 
            	{	
            		
            	},
            	onAfterAppend:function(){
            		inittree();
            	}	
            });
        manager = $("#teacher").ligerGetTreeManager(); 
     	data=tree2.getData();
        $("#button").append("<a class=\"l-button\" style=\"width:80px; margin-left:10px;float:left;\" onclick=\"f_cancelSelect()\">取消选择</a>");
            
    }
    //预览
    function f_preview(){
    	var text =getChecked();
    	if(text.length!=1){
    		$.ligerDialog.warn('请选择单个老师');
    		return;
    	}
    	 window.loginWin=$.ligerDialog.open({ 
	          	  	url: "http://www.youeclass.com/main/teacher/teacher"+text[0]+".html",
	          	    title:'预览',
	          	    isHidden: false,
	          	    icon: 'images/Program Files Folder.png',
	          	    height: 500,width:900
	          	    });	       
    }   
    //发布
     function f_release(){
     	var text = getChecked();
     	if(text.length==0){
     		$.ligerDialog.warn('请选择老师');
    		return;
     	}
     	
     	window.loginWin=$.ligerDialog.open({ 
			          	  	url: '${pageContext.request.contextPath}/cms/jsp/release/releaseTeacher.jsp?tchId='+text,
			          	    title:'发布课程',
			          	    isHidden: false,
			          	    icon: 'images/Program Files Folder.png',
			          	    height: 200,width: 300
			          	    });	
     /* 	
     	for (var i = 0; i < notes.length; i++)
            {
            	LG.hideLoading("正在发布"+notes[i].data.tchName+"请稍后");
   				$.post("${pageContext.request.contextPath}/release/releaseTeacher",{tchIdStr:notes[i].data.tchId},
          					function(data){
          						if(!data.IsError){
          							$.ligerDialog.success('发布成功');
          						}else{
          							$.ligerDialog.error('发布失败');
          						}
							},
						"json"
          			); 
            } */
     	
    }

    function f_cancelSelect()
    {
        var parm = function (data)
        {
            return false;
        };

        tree2.selectNode(parm);
    }
    function inittree()
    {   
        $(".l-checkbox", "#teacher").removeClass("l-checkbox-unchecked").addClass("l-checkbox-checked");  
    }  
        
    function clear()
    {
           manager.clear();
   	}
   	
   	function getChecked()
        {
        	var text = [];
            var nodes = tree2.getChecked();
            $(nodes).each(function (i, node)
                    {
                        text.push(node.data.tchId);
                    });
            return text;
        }
        
    function  f_closeWindow()
   			{
                //$.ligerDialog.close();  //写法1，这么写遮罩会不消失，这不是坑爹么？？
               window.loginWin.close(); 
    		}               
 
 </script>
</body>
</html>
