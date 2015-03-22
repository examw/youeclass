package com.changh.sccms.action.release;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changh.sccms.action.BaseAction;
import com.changh.sccms.entity.ExamCategory;
import com.changh.sccms.entity.NewClass;
import com.changh.sccms.entity.News;
import com.changh.sccms.entity.NewsExam;
import com.changh.sccms.service.ExamCategoryService;
import com.changh.sccms.service.NewClassService;
import com.changh.sccms.service.NewsService;
import com.changh.sccms.until.FileUntil;
import com.changh.sccms.until.FreeMarkerUtil;
import com.changh.sccms.until.PropertiesUtil;

public class ReleaseNewsAction extends BaseAction{
	private boolean ok =false;
	private int examId;
	private String newsClassIds;
	private Integer num;
	private Integer withIndex;
	private Integer withColumn;	//栏目首页
	private Integer pagenum;
	
	public String getRootPath() {
		return PropertiesUtil.getOptValue("rootPath");
	}
	private NewsService newsService;
	private ExamCategoryService examCategoryService;
	
	private NewClassService newClassService;

	public void setNewClassService(NewClassService newClassService) {
		this.newClassService = newClassService;
	}

	public void setExamCategoryService(ExamCategoryService examCategoryService) {
		this.examCategoryService = examCategoryService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getNewsClassIds() {
		return newsClassIds;
	}

	public void setNewsClassIds(String newsClassIds) {
		this.newsClassIds = newsClassIds;
	}
	
	public Integer getWithIndex() {
		return withIndex;
	}

	public void setWithIndex(Integer withIndex) {
		this.withIndex = withIndex;
	}
	
	public Integer getWithColumn() {
		return withColumn;
	}

	public void setWithColumn(Integer withColumn) {
		this.withColumn = withColumn;
	}
	
	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}

	/**
	 * 发布首页
	 * @return
	 * @throws Exception
	 */
	public String releaseIndex() throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		//教务公告
		root.put("news", getNewsByCategory(1067,null,10));
		//报考指南
		root.put("baok", getNewsByCategory(1068,null,9));
		//建筑工程
		root.put("jzgc", newsService.findAll(0,1001,1001, 1, 8, "addTime", "desc"));
		//其他建筑工程子类
		List<ExamCategory> children = examCategoryService.findByPro("examPid", 1001);
		for(ExamCategory child:children){
			root.put(child.getExamEname(), newsService.findAll(0,child.getExamId(),1001, 1, 8, "addTime", "desc"));
		}
		root.put("examList", examCategoryService.findByPro("examPid", 0));
		boolean flag = FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,this.getRootPath(),"/youeclass/index.ftl", root, "", "index.html");
		setOk(flag);
		return "success";
	}
	/**
	 * 发布栏目页面
	 **/
	public void releaseClass() throws Exception{
		ExamCategory ec = examCategoryService.examLoad(examId);
		Map<String, Object> root = new HashMap<String, Object>();
		List<ExamCategory> children = examCategoryService.findByPro("examPid",
				ec.getExamId());
		
		NewsExam newexam = getNewsExam(examCategoryService.examLoad(ec.getPid()));
		newexam.setNewsexam(getNewsExam(ec));
		//子考试类别
		root.put("examList", children);
		//当前考试类别
		root.put("exam", ec);
		//教务公告
		root.put("jwgg", newsService.findAll(1067,examId, 1001, 1, 10, "addTime", "desc"));
		//最新资讯
		root.put("news",newsService.findAll(0,examId, 1001, 1, 10, "addTime", "desc"));
		//备考资料
		root.put("ksfd",newsService.findAll(1002,examId, 1001, 1, 10, "addTime", "desc"));
		//招生简章
		root.put("zsjz", newsService.findAll(1066,examId, 1001, 1, 10, "addTime", "desc"));
		//报名时间
		root.put("bmsj", newsService.findAll(1003,examId, 1001, 1, 10, "addTime", "desc"));
		//准考证打印
		root.put("zkzdy", newsService.findAll(1057,examId, 1001, 1, 10, "addTime", "desc"));
		//成绩查询
		root.put("cjcx", newsService.findAll(1058,examId, 1001, 1, 10, "addTime", "desc"));
		
		//辅导资料
		root.put("fdzl", newsService.findAll(1060,examId, 1001, 1, 10, "addTime", "desc"));
		//模拟试题
		root.put("mnst", newsService.findAll(1059,examId, 1001, 1, 10, "addTime", "desc"));
		//历年真题
		root.put("lnzt", newsService.findAll(1006,examId, 1001, 1, 10, "addTime", "desc"));
		root.put("newsexam", newexam);
		
		//判断模板是否存在  不存在 则copy一个
		FileUntil.copyFile("/youeclass/category.ftl","/youeclass/"+ec.getExamEname()+".ftl");
		FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
				this.getRootPath(), "/youeclass/"+ec.getExamEname()+".ftl", root,
				ec.getExamEname(), "index.html");
		//发布该考试类别下面的资讯页面
		for (ExamCategory child : children) {
			Map<String, Object> root1 = new HashMap<String, Object>();
			root1.put("exam", child);
			List<ExamCategory> subList = examCategoryService.findByPro(
					"examPid", child.getExamId());
			root1.put("examList", subList);
			FileUntil.copyFile("/youeclass/category-small.ftl","/youeclass/"+child.getExamEname()+".ftl");
			FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
					this.getRootPath(), "/youeclass/"+child.getExamEname()+".ftl", root1,
					ec.getExamEname() + "/" + child.getExamEname(),
					"index.html");
		}		
	}
	/**
	 * 发布资讯页面
	 **/
	public String execute() throws Exception{
		if(pagenum == null || pagenum.equals(0) || pagenum<10){
			pagenum = 35;
		}
		if(withIndex != null && withIndex.equals(1)){
			releaseIndex();
		}
		if(withColumn != null && withColumn.equals(1)){
			releaseClass();
		}
		ExamCategory ec = examCategoryService.examLoad(examId);
		Map<String, Object> root = new HashMap<String, Object>();
		List<ExamCategory> children = examCategoryService.findByPro("examPid",
				ec.getExamId());
		List<NewClass> newclass = newClassService.findByIds(newsClassIds);
		if(newclass==null ||newclass.size() ==0){
			return "success";
		}
		NewsExam newexam = getNewsExam(examCategoryService.examLoad(ec.getPid()));
		newexam.setNewsexam(getNewsExam(ec));
		root.put("examBig", ec);
		root.put("newsexam", newexam);
		//最新资讯
		root.put("news",newsService.findAll(0,examId, 1001, 1, 10, "addTime", "desc"));
		//备考资料
		root.put("ksfd",newsService.findAll(1002,examId, 1001, 1, 10, "addTime", "desc"));
		//招生简章
		root.put("zsjz", newsService.findAll(1066,examId, 1001, 1, 10, "addTime", "desc"));
		
		for(NewClass n:newclass){
			root.put("newclass",n);
			releaseBig(root,ec,n);
			int count = 0;
			for (ExamCategory child : children) {
				child.setNews(newsService.findAll(n.getId(),child.getExamId(), 1001, 1, 10, "addTime", "desc"));
				if(child.getNews() == null || child.getNews().size() == 0){
					count ++ ;
				}
				//发布小考上类别的具体咨询
				root.put("child", child);
				List<News> all = newsService.findAll(n.getId(),child.getExamId(), 1001, 0, 40, "addTime", "desc");
				int total = 0;
				if(all.size()!=0){
					total = all.size()%pagenum==0?all.size()%pagenum:all.size()/pagenum+1;
				}
				for(int current = 1;current<=total;current++)
				{
					root.put("newsList", getNewsList(all,current));
					root.put("current", current);
					root.put("total", total);
					FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
							this.getRootPath(), "/youeclass/list-small.ftl", root,
							ec.getExamEname()+"/"+n.getClassEname()+"/"+child.getExamEname(),
							"index"+(current==1?"":current)+".html");
				}
			}
			if(count < children.size())
			{
				root.put("newList", newsService.findAll(n.getId(),examId, 1001, 1, 10, "addTime", "desc"));
				root.put("children", children);
				FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
						this.getRootPath(), "/youeclass/list-big.ftl", root,
						ec.getExamEname() + "/" +n.getClassEname(),
						"index.html");
			}else{
				List<News> all = newsService.findAll(n.getId(),examId, 1001, 0, 40, "addTime", "desc");
				int total = 0;
				if(all.size()!=0){
					total = all.size()%pagenum==0?all.size()%pagenum:all.size()/pagenum+1;
				}
				for(int current = 1;current<=total;current++)
				{
					root.put("newList", getNewsList(all,current));
					root.put("current", current);
					root.put("total", total);
					FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
							this.getRootPath(), "/youeclass/list-big2.ftl", root,
							ec.getExamEname()+"/"+n.getClassEname(),
							"index"+(current==1?"":current)+".html");
				}
			}
		};
		//发布具体咨询
		releaseNews(newexam);
		setOk(true);
		return "success";
	}
	public NewsExam getNewsExam(ExamCategory exam){
		NewsExam newexam  = new NewsExam();
		newexam.setCname(exam.getExamName());
		newexam.setEname(exam.getExamEname());
		newexam.setId(exam.getExamId());
		return newexam;
	}
	private List<News> getNewsList(List<News> all,int page){
		if(page*pagenum > all.size()){
			if(page == 1)
				return all;
			else{
				List<News> list = new ArrayList<News>();
				for(int i=(page-1)*pagenum;i<all.size();i++)
				{
					list.add(all.get(i));
				}
				return list;
			}
		}else{
			List<News> list = new ArrayList<News>();
			for(int i=(page-1)*pagenum;i<page*pagenum;i++){
				list.add(all.get(i));
			}
			return list;
		}
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	//发布资讯,按考试类别 
	public String releaseNews(NewsExam newexam) throws Exception{
		
		ExamCategory exam = examCategoryService.examLoad(examId);
		//设置第一层exam
		
		//如果该考试类别下面包含子类
		Map<String, Object> root = new HashMap<String, Object>();
		//最新考试资讯
		root.put("kszx", getNewsByCategory(1001,examId,10));
		//最新真题
		root.put("lnzt",  getNewsByCategory(1006,examId,10));
		//最新招生简章
		root.put("zsjz", getNewsByCategory(1066,examId,10));	
		
		root.put("newsexam", newexam);
		if(exam.getExamChildrenNum()!=0){
			List<ExamCategory> children = exam.getChildren();
			//先发布本类
			releaseNewByExam(examId,root);
			//再发布子类
			for(ExamCategory e:children){
				this.examId = e.getExamId();
				releaseNews(newexam);
			}
 		}else{
 			//查找该类别下面的咨询
 			releaseNewByExam(examId,root);
 		}
		setOk(true);
		return "success";
	}
	
	//发布该考试类别下面的咨询不包括子类
	public void releaseNewByExam(int examId,Map<String,Object> root) throws Exception{
		List<News> list = newsService.findTopNewsByPro(num,"exam.examId",examId);
		if(list!=null&&list.size()!=0){
			for(News n:list){
				 //新闻内容
				 root.put("relateList", getNewsList(n));
				 root.put("news", n);
				 FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,this.getRootPath(),"/youeclass/ar ticle.ftl", root,n.getExam().getExamEname()+"/"+n.getProvince().getProvinceEname()+"/"+n.getNewclass().getClassEname()+"/"+n.getNewId(), "index.html");
			}
		}		
	}
	public List<News> getNewsByCategory(Integer categoryId,Integer examId,Integer pagesize){
		List<News> list = new ArrayList<News>();
		HashMap<String,Object> map =new HashMap<String,Object>(); 
		if(categoryId!=null){
			map.put("newclass.id", categoryId);
		}
		if(examId!=null){
			map.put("exam.examId", examId);
		}
		list = newsService.findListNews(1,pagesize,"addTime","desc",map);
		return list;
	}
	public List<News> getNewsList(News n) throws Exception{
		return newsService.findAll(n.getNewclass().getId(),n.getExam().getExamId(), 1001, 1, 5, "addTime", "desc");
	}
	
	private void releaseBig(Map<String, Object> root,ExamCategory ec,NewClass n) throws Exception{
		//发布小考上类别的具体咨询
		root.put("child", ec);
		List<News> all = newsService.findAll(n.getId(),ec.getExamId(), 1001, 0, 40, "addTime", "desc");
		int total = 0;
		if(all.size()!=0){
			total = all.size()%pagenum==0?all.size()%pagenum:all.size()/pagenum+1;
		}
		for(int current = 1;current<=total;current++)
		{
			root.put("newsList", getNewsList(all,current));
			root.put("current", current);
			root.put("total", total);
			FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
					this.getRootPath(), "/youeclass/list-small.ftl", root,
					ec.getExamEname()+"/"+n.getClassEname()+"/"+ec.getExamEname(),
					"index"+(current==1?"":current)+".html");
		}
	}
}
