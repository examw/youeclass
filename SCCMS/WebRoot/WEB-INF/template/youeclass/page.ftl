<#-- ��ҳ������ǰҳ -->
<#macro pagination pageView>  
	<div class="pages">
		<label>��${pageView.totalpage}ҳ��Լ${pageView.totalrecord}������</label>
		<#if pageView.currentpage != 1>
			<a href="javascript:pageinationView(1)" title="��ҳ" class="nav"><span>��ҳ</span></a>
			<a href="javascript:pageinationView(${pageView.currentpage - 1})" title="��һҳ" class="nav"><span>��һҳ</span></a>
		<#else>
			<span>��ҳ</span>
			<span>��һҳ</span>
		</#if>
		<#list pageView.pageindex.startindex..pageView.pageindex.endindex as index>
	        <#if pageView.currentpage == index>
	        	<a href="#" class="current">${index}</a>
	        <#else>
	            <a href="javascript:pageinationView(${index})" title="��${index}ҳ" >${index}</a>  
	        </#if>  
	    </#list>  
	    
		<#if pageView.currentpage != pageView.totalpage>
			<a href="javascript:pageinationView(${pageView.currentpage + 1})" title="��һҳ" class="nav"><span>��һҳ</span></a>
			<a href="javascript:pageinationView(${pageView.totalpage})" title="δҳ" class="nav"><span>δҳ</span></a>
		<#else>
			<span>��һҳ</span>
			<span>δҳ</span>
		</#if>
	</div>
	<script type="text/javascript">
		function pageinationView(pageNum) {
			document.getElementById("pageNum").value=pageNum;
			document.getElementById("pageinationForm").submit();
		}
	</script>
</#macro> 		