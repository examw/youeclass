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
import com.changh.sccms.service.NewsService;
import com.changh.sccms.service.NewClassService;
import com.changh.sccms.until.FileUntil;
import com.changh.sccms.until.FreeMarkerUtil;
import com.changh.sccms.until.PropertiesUtil;

public class YoueclassAction extends BaseAction{
	private boolean ok =false;
	private Integer examId;
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

	/**
	 *������ҳ 
	 **/
	public String doRelease() throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		//���񹫸�
		root.put("news", getNewsByCategory(1067,null,10));
		//����ָ��
		root.put("baok", getNewsByCategory(1068,null,9));
		//��������
		root.put("jzgc", newsService.findAll(0,1001,1001, 1, 8, "addTime", "desc"));
		//����������������
		List<ExamCategory> children = examCategoryService.findByPro("examPid", 1001);
		for(ExamCategory child:children){
			root.put(child.getExamEname(), newsService.findAll(0,child.getExamId(),1001, 1, 8, "addTime", "desc"));
		}
		root.put("examList", examCategoryService.findByPro("examPid", 0));
		boolean flag = FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,this.getRootPath(),"/youeclass/index.ftl", root, "", "index.html");
		setOk(flag);
		return "success";
	}
	
	
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	/**
	 * ������Ŀҳ��
	 **/
	public String releaseClass() throws Exception{
		ExamCategory ec = examCategoryService.examLoad(examId);
		Map<String, Object> root = new HashMap<String, Object>();
		List<ExamCategory> children = examCategoryService.findByPro("examPid",
				ec.getExamId());
		
		NewsExam newexam = getNewsExam(examCategoryService.examLoad(ec.getPid()));
		newexam.setNewsexam(getNewsExam(ec));
		//�ӿ������
		root.put("examList", children);
		//��ǰ�������
		root.put("exam", ec);
		//���񹫸�
		root.put("jwgg", newsService.findAll(1067,examId, 1001, 1, 10, "addTime", "desc"));
		//������Ѷ
		root.put("news",newsService.findAll(0,examId, 1001, 1, 10, "addTime", "desc"));
		//��������
		root.put("ksfd",newsService.findAll(1002,examId, 1001, 1, 10, "addTime", "desc"));
		//��������
		root.put("zsjz", newsService.findAll(1066,examId, 1001, 1, 10, "addTime", "desc"));
		//����ʱ��
		root.put("bmsj", newsService.findAll(1003,examId, 1001, 1, 10, "addTime", "desc"));
		//׼��֤��ӡ
		root.put("zkzdy", newsService.findAll(1057,examId, 1001, 1, 10, "addTime", "desc"));
		//�ɼ���ѯ
		root.put("cjcx", newsService.findAll(1058,examId, 1001, 1, 10, "addTime", "desc"));
		
		//��������
		root.put("fdzl", newsService.findAll(1060,examId, 1001, 1, 10, "addTime", "desc"));
		//ģ������
		root.put("mnst", newsService.findAll(1059,examId, 1001, 1, 10, "addTime", "desc"));
		//��������
		root.put("lnzt", newsService.findAll(1006,examId, 1001, 1, 10, "addTime", "desc"));
		root.put("newsexam", newexam);
		
		//�ж�ģ���Ƿ����  ������ ��copyһ��
		FileUntil.copyFile("/youeclass/category.ftl","/youeclass/"+ec.getExamEname()+".ftl");
		FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
				this.getRootPath(), "/youeclass/"+ec.getExamEname()+".ftl", root,
				ec.getExamEname(), "index.html");
		//�����ÿ�������������Ѷҳ��
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
		//�����ÿ�������������Ѷ
		//��ȡ������Ѷ���
		
		List<NewClass> newclass = newClassService.getAll();
		for(NewClass n:newclass){
			root.put("newclass",n);
			for (ExamCategory child : children) {
				child.setNews(newsService.findAll(n.getId(),child.getExamId(), 1001, 1, 10, "addTime", "desc"));
				//����С�������ľ�����ѯ
				root.put("child", child);
				root.put("newsList",newsService.findAll(n.getId(),child.getExamId(), 1001, 1, 40, "addTime", "desc"));
				FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
						this.getRootPath(), "/youeclass/list-small.ftl", root,
						ec.getExamEname()+"/"+n.getClassEname()+"/"+child.getExamEname(),
						"index.html");
			}
			root.put("newList", newsService.findAll(n.getId(),examId, 1001, 1, 10, "addTime", "desc"));
			root.put("children", children);
			FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,
					this.getRootPath(), "/youeclass/list-big.ftl", root,
					ec.getExamEname() + "/" +n.getClassEname(),
					"index.html");
			
			
		};
		
		
		//����������ѯ
		releaseNews(newexam);
		
		return "success";
	}
	
	
	//���ҳ��
	public Boolean releaseCategory(){
		List<ExamCategory> children = examCategoryService.findByPro("examPid",examId);
		List<NewClass> newclass = newClassService.getAll();
		for (ExamCategory child : children){
			
		}
		return true;
	}
	
	
	//������Ѷ,��������� 
	public String releaseNews(NewsExam newexam) throws Exception{
		
		ExamCategory exam = examCategoryService.examLoad(examId);
		//���õ�һ��exam
		
		//����ÿ�����������������
		Map<String, Object> root = new HashMap<String, Object>();
		//���¿�����Ѷ
		root.put("kszx", getNewsByCategory(1001,examId,10));
		//��������
		root.put("lnzt",  getNewsByCategory(1006,examId,10));
		//������������
		root.put("zsjz", getNewsByCategory(1066,examId,10));	
		
		root.put("newsexam", newexam);
		if(exam.getExamChildrenNum()!=0){
			List<ExamCategory> children = exam.getChildren();
			//�ȷ�������
			releaseNewByExam(examId,root);
			//�ٷ�������
			for(ExamCategory e:children){
				this.examId = e.getExamId();
				releaseNews(newexam);
			}
 		}else{
 			//���Ҹ�����������ѯ
 			releaseNewByExam(examId,root);
 		}
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
	
	
	//�����ÿ�������������ѯ����������
	public void releaseNewByExam(int examId,Map<String,Object> root) throws Exception{
		List<News> list = newsService.findNewsByPro("exam.examId",examId);
		if(list!=null&&list.size()!=0){
			for(News n:list){
				 //��������
				 root.put("relateList", getNewsList(n));
				 root.put("news", n);
				 System.out.println(n.getNewId());
				 FreeMarkerUtil.getInstance().genHtmlFile(httpRequest,this.getRootPath(),"/youeclass/article.ftl", root,n.getExam().getExamEname()+"/"+n.getProvince().getProvinceEname()+"/"+n.getNewclass().getClassEname()+"/"+n.getNewId(), "index.html");
			}
		}		
	}
	
	public List<News> getNewsList(News n) throws Exception{
		return newsService.findAll(n.getNewclass().getId(),n.getExam().getExamId(), 1001, 1, 5, "addTime", "desc");
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
	
	
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
