<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<span class="lessionTop">
<div id="layer1">我的课堂</div></span>
<ul>
<s:if test="myLesson.classPackages.size()!=0||myLesson.grade.size()!=0">
<s:iterator value="myLesson.classPackages" var="cp" status="col">
<li class="Title">
<p>
	<img src="../images/pic_010.gif" width="18" height="12" />&nbsp;${cp.pkgName}
</p>
	<!-- <span><a href="#">更多课程>></a></span> -->
</li>
<s:iterator value="#cp.grade" var="g" status="status">
<li>
<dl>
	<dt><b>&middot;</b> <a href="/member/myClass?gradeId=${g.gradeId}" target="_blank">${g.examCategory.examName}-${g.gradeCategory.gtypeName}</a></dt>
	<dd>	
		<s:iterator value="#g.classDetails" var="cd" status="col">
		<s:if test="#col.getIndex()<6">
		<a href="/member/classroomHD?classId=${cd.classId}" target="_blank">0${col.index+1}讲</a>
		</s:if>
		</s:iterator>	
		<s:if test="#g.classDetails.size>6">
		<a href="/member/myClass?gradeId=${g.gradeId}" target="_blank">更多...</a>
		</s:if>
	</dd>
</dl>
</li>
</s:iterator>
</s:iterator>
<s:iterator value="myLesson.grade" var="g" status="col">
<li class="Title">
<p class="course">
	<img src="../images/pic_bh.gif" width="18" height="15" />&nbsp;${g.examCategory.examName}${g.gradeCategory.gtypeName}</p>
		<span class="courseList">
			<s:iterator value="#g.classDetails" var="cd" status="col">
			<s:if test="#col.getIndex()<6">
			<a href="/member/myClass?gradeId=${g.gradeId}">0${col.index+1}讲</a>
			</s:if>
			</s:iterator>
			<s:if test="#g.classDetails.size>6">
			<a href="/member/myClass?gradeId=${g.gradeId}">更多...</a>
			</s:if>
		</span>
</li>
</s:iterator>
<li id="morelesson"><a href="/member/myLessons?stuId=<s:property value="#session.student.stuId"/>">更多课程>></a></li>
</s:if>
<s:else>
<li class="Title">您还没有选购课程</li>
</s:else>
</ul>
