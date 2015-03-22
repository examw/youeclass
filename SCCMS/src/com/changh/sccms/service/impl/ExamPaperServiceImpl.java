package com.changh.sccms.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.changh.sccms.dao.ExamPaperDAO;
import com.changh.sccms.dao.ExamQuestionDAO;
import com.changh.sccms.dao.ExamRuleDAO;
import com.changh.sccms.entity.Administrator;
import com.changh.sccms.entity.ExamPaper;
import com.changh.sccms.entity.ExamQuestion;
import com.changh.sccms.entity.ExamRule;
import com.changh.sccms.service.ExamPaperService;
import com.changh.sccms.until.Constant;
import com.changh.sccms.until.DegistUtil;
import com.changh.sccms.until.GridDataUtil;
import com.opensymphony.xwork2.ActionContext;

public class ExamPaperServiceImpl implements ExamPaperService {
	private ExamPaperDAO examPaperDao;
	private ExamRuleDAO examRuleDao;
	private ExamQuestionDAO examQuestionDao;

	public void setExamPaperDao(ExamPaperDAO examPaperDao) {
		this.examPaperDao = examPaperDao;
	}

	public void setExamRuleDao(ExamRuleDAO examRuleDao) {
		this.examRuleDao = examRuleDao;
	}

	public void setExamQuestionDao(ExamQuestionDAO examQuestionDao) {
		this.examQuestionDao = examQuestionDao;
	}

	public Map<String, Object> findPageByExamId(int examId, int page,
			int pagesize, String sortname, String sortorder) throws Exception {
		// TODO Auto-generated method stub
		return GridDataUtil.gridMap(examPaperDao.findPageByExamId(examId, page,
				pagesize, sortname, sortorder), (int) examPaperDao
				.findTotalByExamId(examId));
	}

	public Map<String, Object> findPageByContent(String content, int page,
			int pagesize, String sortname, String sortorder) throws Exception {
		// TODO Auto-generated method stub
		return GridDataUtil.gridMap(examPaperDao.findPageByContent(content,
				page, pagesize, sortname, sortorder), (int) examPaperDao
				.findTotalByContent(content));
	}

	public void addPaper(ExamPaper paper, List<ExamRule> rules)
			throws Exception {
		// ����paper��һЩ����
		paper.setPaperAddTime(new Date());
		paper.setPaperIsChecked(0);
		paper.setPaperClickNum(0);
		int paperId = examPaperDao.save(paper);
		// ����list,���rule
		for (ExamRule rule : rules) {
			if (rule != null) {
				rule.setPaperId(paperId);// ���������Ծ��id
				rule.setRuleActualAddNum(0);
				examRuleDao.save(rule);
			}
		}
	}

	public Map<String, Object> findQuestionOfPaper(int paperId, int page,
			int pagesize, String sortname, String sortorder) throws Exception {
		// TODO Auto-generated method stub
		// �ҳ�paper�µ���������
		String criteria = " where eq.questPaperId = " + paperId;
		List<ExamQuestion> list = examQuestionDao.findPageByCriteria(criteria,
				page, pagesize, sortname, sortorder);
		int total = (int) examQuestionDao.findTotalByCriteria(criteria);
		return GridDataUtil.gridMap(list, total);
	}

	public Map<String, Object> findQuestionByContent(String content, int page,
			int pagesize, String sortname, String sortorder) throws Exception {
		// TODO Auto-generated method stub
		List<ExamQuestion> list = examQuestionDao.findPageByCriteria(content,
				page, pagesize, sortname, sortorder);
		int total = (int) examQuestionDao.findTotalByCriteria(content);
		return GridDataUtil.gridMap(list, total);
	}

	public ExamPaper findById(int paperId) throws Exception {
		// TODO Auto-generated method stub
		return examPaperDao.findById(paperId);
	}

	public ExamPaper findPaperByName(String paperName) throws Exception {
		return examPaperDao.findByName(paperName);
	}

	public boolean deletePaper(int paperId) throws Exception {
		ExamPaper paper = examPaperDao.findById(paperId);
		// ɾС��
		if (paper != null) {
			List<ExamQuestion> questionList = examQuestionDao
					.findAllByPaperId(paperId);
			examQuestionDao.deleteAll(questionList);
			// ɾ����
			examRuleDao.deleteAll(paper.getExamRules()); // һ�Զ಻֪���������
			// ɾ�Ծ�
			examPaperDao.delete(paper);
			return true;
		}
		return false;
	}

	public boolean deleteRule(int ruleId) throws Exception {
		// TODO Auto-generated method stub
		ExamRule rule = examRuleDao.findById(ruleId);
		if (rule != null) {
			if (rule.getRuleActualAddNum() != 0) {
				List<ExamQuestion> list = examQuestionDao
						.findAllByRuleId(ruleId);
				// ɾ������С��
				examQuestionDao.deleteAll(list);
			}
			// ɾ���ô��� (�Ƿ�Ҫά���Ǹ��������ţ���)
			examRuleDao.delete(rule);
			// ά���ܷ�
			examPaperDao.updatePaperScore(rule.getPaperId(),
					rule.getRuleQuestionNum() * rule.getRuleScoreForEach());
			return true;
		}
		return false;
	}

	// �����Ծ�
	public void updatePaper(ExamPaper paper, List<ExamRule> rules)
			throws Exception {
		// TODO Auto-generated method stub
		// �ȴ����ݿ��ҳ����Ծ�
		ExamPaper realPaper = examPaperDao.findById(paper.getPaperId());
		List<ExamRule> realRules = realPaper.getExamRules();// ʵ�ʵĴ��⣬�����б�ɾ�������
		// ����rules���ԣ�Ȼ�󱣴�
		List<ExamRule> list = updateRuleAttribute(rules, realRules);
		examRuleDao.saveOrUpdate(list);
		// ����paper���ԣ�Ȼ�󱣴�
		updatePaperAttribute(paper, realPaper);
		examPaperDao.update(realPaper); // ���ɲ�д
	}

	private List<ExamRule> updateRuleAttribute(List<ExamRule> rules,
			List<ExamRule> realRules) {
		List<ExamRule> dest = new ArrayList<ExamRule>();
		Integer paperId = null;
		for (ExamRule rule : rules) // ѭ���ύ������list
		{
			if (rule != null) {
				if (rule.getRuleId() != null) {
					for (int i = 0; i < realRules.size(); i++) {
						ExamRule realRule = realRules.get(i); // ��ô����ͬһ������
						paperId = realRule.getPaperId(); // ���������µĴ����paperId
						if (rule.getRuleId().equals(realRule.getRuleId())) // ��ʵ�ʵĽ��жԱȣ��������ͬ�ģ���������
						{
							// ��������
							realRule.setRuleIdInPaper(rule.getRuleIdInPaper());// ���Ծ��е�λ��
							realRule.setRuleQuestionNum(rule
									.getRuleQuestionNum());
							realRule.setRuleScoreForEach(rule
									.getRuleScoreForEach());
							realRule.setRuleScoreSet(rule.getRuleScoreSet());
							realRule.setRuleTitle(rule.getRuleTitle());
							realRule.setRuleType(rule.getRuleType());
							// �����½���list
							dest.add(realRule);
							break;// �ҵ�һ��������ѭ����ֻ������һ��
						}
					}
				} else {
					// �µĴ���
					rule.setPaperId(paperId);
					rule.setRuleActualAddNum(0);
					dest.add(rule);
				}
			}

		}
		return dest;
	}

	private void updatePaperAttribute(ExamPaper paper, ExamPaper realPaper) {
		realPaper.setPaperName(paper.getPaperName());
		realPaper.setPaperScore(paper.getPaperScore());
		realPaper.setPaperTime(paper.getPaperTime());
		realPaper.setPaperEditor(paper.getPaperEditor());
		realPaper.setPaperEGCheaterId(paper.getPaperEGCheaterId());
		realPaper.setPaperEGradeId(paper.getPaperEGradeId());
		realPaper.setPaperLinkName(paper.getPaperLinkName());
		realPaper.setPaperType(paper.getPaperType());
	}

	public ExamRule findRuleById(int ruleId) throws Exception {
		// TODO Auto-generated method stub
		return examRuleDao.findById(ruleId);
	}

	public List<String> findQuestionNo(int ruleId, List<String> list)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> real = examQuestionDao.findQuestionNo(ruleId);
		list.removeAll(real);
		return list;
	}

	public String addQuestion(ExamQuestion question, String link)
			throws Exception {
		// TODO Auto-generated method stub
		ExamRule rule = examRuleDao.findById(question.getQuestRuleId());
		if (rule == null)
			return "-1";
		question.setQuestAddTime(new Date());
		question.setQuestMd5code(DegistUtil.getMd5CodeOfQuestion(question
				.getQuestContent())); // ����md5code
		if (link == null) // ��ʾ��������
		{
			examQuestionDao.save1(question);
			rule.setRuleActualAddNum(rule.getRuleActualAddNum() + 1); // ���¸ô���ʵ����ӵ���Ŀ��
			return "1";
		} else if (link.equals("1")) {
			// ��ʾ��ѡ�����⿪ʼ
			int questId = examQuestionDao.save(question);
			// ������������
			ExamQuestion question1 = examQuestionDao.findById(questId);
			question1.setQuestLinkQuestionId(questId + "_start");
			// examQuestionDao.update(question1)
			rule.setRuleActualAddNum(rule.getRuleActualAddNum() + 1); // ���¸ô���ʵ����ӵ���Ŀ��
			return questId + "_";
		} else // if(link.endsWith("_end"))
		{
			// ��ʾ����������߽���
			question.setQuestLinkQuestionId(link);
			examQuestionDao.save1(question);
			rule.setRuleActualAddNum(rule.getRuleActualAddNum() + 1); // ���¸ô���ʵ����ӵ���Ŀ��
			return link;
		}
	}

	public boolean checkRepeat(String content, int ruleId, int model)
			throws Exception {
		// TODO Auto-generated method stub
		String code = DegistUtil.getMd5CodeOfQuestion(content);
		List<ExamQuestion> list = examQuestionDao.findByMD5Code(code, ruleId);
		if (model == 0)
			return list == null; // Ϊ�棬��ʾ���Լ���
		return list == null || list.size() == 1;
	}

	public ExamQuestion findQuestionById(int questId) throws Exception {
		// TODO Auto-generated method stub
		return examQuestionDao.findById(questId);
	}

	public String findLinkedQid() throws Exception {
		// TODO Auto-generated method stub
		return examQuestionDao.findLinkedQid();
	}

	public boolean deleteQuestion(int questId) throws Exception {
		// TODO Auto-generated method stub
		ExamQuestion question = examQuestionDao.findById(questId);
		if (question == null) {
			return false;
		} else {
			examQuestionDao.delete(question);
			// ���´���ʵ�ʵ������
			ExamRule rule = examRuleDao.findById(question.getQuestRuleId());
			rule.setRuleActualAddNum(rule.getRuleActualAddNum() - 1);
			// examRuleDao.update(rule);
			return true;
		}
	}

	public boolean updateQuestion(ExamQuestion question) throws Exception {
		// TODO Auto-generated method stub
		ExamQuestion real = examQuestionDao.findById(question.getQuestId());
		if (real != null) {
			real.setQuestAnalysis(question.getQuestAnalysis());
			real.setQuestAnswer(question.getQuestAnswer());
			real.setQuestContent(question.getQuestContent());
			real.setQuestEditor(question.getQuestEditor());
			real.setQuestOptionNum(question.getQuestOptionNum());
			real.setQuestMd5code(DegistUtil.getMd5CodeOfQuestion(question
					.getQuestContent()));
			// examQuestionDao.update(question);
			return true;
		}
		return false;
	}

	public boolean updateQuestionForCancelLink(String questionIds)
			throws Exception {
		// ȡ���������ԣ�һȡ����ȡ��
		// questionIdΪlinked��idֵ
		String criteria = getQueryStr(questionIds);
		List<ExamQuestion> list = examQuestionDao.findByCriteria(criteria);
		cancelLink(list);
		return true;
	}

	private String getQueryStr(String questionIds) {
		StringBuffer buf = new StringBuffer();
		buf.append(" where eq.questLinkQuestionId like '%");
		String[] arr = questionIds.split(",");
		buf.append(arr[0].split("_")[0]);
		buf.append("%'");
		for (int i = 1; i < arr.length - 1; i++) {
			buf.append(" or eq.questionLinkQuestionId like '%");
			buf.append(arr[1].split("_")[0]);
			buf.append("%'");
		}
		if (arr.length >= 2) {
			buf.append(" or eq.questionLinkQuestionId like '%");
			buf.append(arr[arr.length - 1].split("_")[0]);
			buf.append("%'");
		}
		return buf.toString();
	}

	private void cancelLink(List<ExamQuestion> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setQuestLinkQuestionId("");
		}
	}

	public boolean updateQuestionsForSetLink(String questionIds)
			throws Exception {
		// TODO Auto-generated method stub
		// ��ѯquestionIds�ļ���
		String criteria = " where eq.questId in (" + questionIds
				+ ") order by eq.questId asc";
		List<ExamQuestion> list = examQuestionDao.findByCriteria(criteria);
		if (questionIds.split("[,]").length != list.size()) {
			return false;
		}
		setLink(list);
		return true;
	}

	private void setLink(List<ExamQuestion> list) {
		int questId = list.get(0).getQuestId();
		list.get(0).setQuestLinkQuestionId(questId + "_start");
		for (int i = 1; i < list.size() - 1; i++) {
			list.get(i).setQuestLinkQuestionId(questId + "_");
		}
		list.get(list.size() - 1).setQuestLinkQuestionId(questId + "_end");
	}

	public boolean saveBatchFromFile(File mf, int ruleId, int paperId,
			int questType, int startNo, int model) throws Exception {
		// TODO Auto-generated method stub
		String editor = ((Administrator) ActionContext.getContext()
				.getSession().get("admin")).getAdmUsername();
		ExamRule rule = examRuleDao.findById(ruleId);
		if (rule == null)
			return false;
		List<ExamQuestion> list = null;
		if (model == 0) {
			list = parseFile(mf, ruleId, paperId, questType, startNo, editor);
		} else {
			list = parseFile2(mf, ruleId, paperId, questType, startNo, editor);
		}
		if (list.size() < 1) {
			return false;
		}
		examQuestionDao.save(list,
				rule.getRuleQuestionNum() - rule.getRuleActualAddNum());
		// ����rule
		rule.setRuleActualAddNum(rule.getRuleActualAddNum() + list.size());
		examRuleDao.update(rule);
		return true;

	}

	private List<ExamQuestion> parseFile(File mf, int ruleId, int paperId,
			int questType, int startNo, String editor) throws IOException {
		List<ExamQuestion> list = null;
		BufferedReader br = null;
		try {
			list = new ArrayList<ExamQuestion>();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					mf)));
			String str = br.readLine();
			String str2 = null;
			String str3 = null;
			String str4 = null;
			StringBuffer buf = new StringBuffer();
			StringBuffer buf1 = new StringBuffer();
			StringBuffer buf2 = new StringBuffer();
			StringBuffer md5buf = new StringBuffer();
			int count = 1;
			while (str != null) {
				if (!str.startsWith(String.valueOf(count))) {
					str = br.readLine();
				} else {
					// ��ʾ��һ�⿪ʼ
					ExamQuestion question = new ExamQuestion();
					str2 = str;
					while (str2 != null) {
						if (str2.indexOf("[��]") == -1) {
							buf.append(str2 + "\n");
							str2 = br.readLine();
						} else {
							str3 = str2;
							if (questType != Constant.Q_QANDA) {
								while (str3 != null) {
									if (str3.indexOf("[����]") == -1) {
										buf1.append(str3 + "\n");
										str3 = br.readLine();
									} else {
										str4 = str3;
										while (str4 != null) {
											if (!str4.startsWith(String
													.valueOf(count + 1))) {
												buf2.append(str4 + "\n");
												str4 = br.readLine();
											} else {
												count = count + 1;
												break;
											}
										}
										// ��������
										question.setQuestAnalysis(buf2
												.delete(0, 5).toString().trim());
										buf2.delete(0, buf2.length());
										str3 = null;
									}
								}
							} else {
								str4 = str2;
								while (str4 != null) {
									if (!str4.startsWith(String
											.valueOf(count + 1))) {
										buf2.append(str4 + "\n");
										str4 = br.readLine();
									} else {
										count = count + 1;
										break;
									}
								}
								// ��������
								question.setQuestAnalysis(buf2.delete(0, 5)
										.toString().trim());
								buf2.delete(0, buf2.length());
								str3 = null;
							}
							// �𰸼���
							question.setQuestAnswer(buf1.delete(0, 5)
									.toString().trim());
							buf1.delete(0, buf1.length()); // ���buf1
							str2 = null;
						}
					}
					// ��Ŀ����
					buf.delete(0, 2);
					question.setQuestContent(buf.toString().trim());
					String md5 = DegistUtil.getMd5CodeOfQuestion(buf.toString()
							.trim());
					question.setQuestMd5code(md5);
					if (list.size() == 0) {
						question.setQuestAddTime(new Date());
						question.setQuestOrderId(startNo);
						question.setQuestType(questType);
						question.setQuestRuleId(ruleId);
						question.setQuestPaperId(paperId);
						question.setQuestEditor(editor);
						question.setQuestOptionNum(buf.toString()
								.replaceAll("[A-Z][.)����]", "@@@").split("@@@").length - 1);
						list.add(question);
						md5buf.append(md5);
					} else if (md5buf.indexOf(md5) == -1)// �ж��ظ�
					{
						question.setQuestAddTime(new Date());
						question.setQuestOrderId(startNo + list.size());
						question.setQuestType(questType);
						question.setQuestRuleId(ruleId);
						question.setQuestPaperId(paperId);
						question.setQuestEditor(editor);
						question.setQuestOptionNum(buf.toString()
								.replaceAll("[A-Z][.)����]", "@@@").split("@@@").length - 1);
						list.add(question);
						md5buf.append(md5);
					}
					str = str4;
					buf.delete(0, buf.length()); // ���buf
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}
		return list;
	}

	private List<ExamQuestion> parseFile2(File mf, int ruleId, int paperId,
			int questType, int startNo, String editor) throws IOException {
		List<ExamQuestion> list = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					mf)));
			String str = br.readLine();
			String str2 = null;
			String str3 = null;
			StringBuffer buf = new StringBuffer();
			StringBuffer buf2 = new StringBuffer();
			StringBuffer md5buf = new StringBuffer();
			list = new LinkedList<ExamQuestion>();
			int count = 1;
			while (str != null) {
				if (!str.startsWith(String.valueOf(count))) {
					str = br.readLine();
				} else {
					// ��ʾ��һ�⿪ʼ
					ExamQuestion q = new ExamQuestion();
					str2 = str;
					while (str2 != null) {
						if (!str2.startsWith(String.valueOf(count + 1))) {
							if (str2.startsWith("[��]")) {
								str3 = br.readLine();
								while (str3 != null) {
									buf2.append(str3);
									str3 = br.readLine();
								}
							}
							buf.append(str2 + "\n");
							str2 = br.readLine();
						} else {
							count = count + 1;
							break;
						}
					}
					buf.delete(0, 2);
					String content = buf.toString().trim();
					q.setQuestContent(content);
					String md5 = DegistUtil.getMd5CodeOfQuestion(content);
					q.setQuestMd5code(md5);
					if (list.size() == 0) {
						q.setQuestAddTime(new Date());
						q.setQuestOrderId(startNo);
						q.setQuestType(questType);
						q.setQuestRuleId(ruleId);
						q.setQuestPaperId(paperId);
						q.setQuestEditor(editor);
						q.setQuestOptionNum(content.replaceAll("[A-Z][.)����]",
								"@@@").split("@@@").length - 1);
						list.add(q);
						md5buf.append(md5);
					} else if (md5buf.indexOf(md5) == -1)// �ж��ظ�
					{
						q.setQuestAddTime(new Date());
						q.setQuestOrderId(startNo);
						q.setQuestType(questType);
						q.setQuestRuleId(ruleId);
						q.setQuestPaperId(paperId);
						q.setQuestEditor(editor);
						q.setQuestOptionNum(content.replaceAll("[A-Z][.)����]",
								"@@@").split("@@@").length - 1);
						list.add(q);
						md5buf.append(md5);
					} else {
						list.add(null); // �����ظ��Ĵ�
					}
					str = str2;
					buf.delete(0, buf.length()); // ���buf
				}
			}
			String answer[] = buf2.toString().split("[0-9]+[.��)]");
			// Ϊquestion�Ӵ�
			for (int i = 0; i < list.size(); i++) {
				ExamQuestion e = list.get(i);
				if (e != null) {
					e.setQuestAnswer(answer[i + 1].trim());
				}
			}
			// ���Ϊnull��Ԫ��
			while (list.contains(null)) {
				list.remove(null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}
		return list;
	}

	public boolean saveBatchFromEditor(String questContent, int ruleId,
			int paperId, int questType, int startNo, int model)
			throws Exception {
		// TODO Auto-generated method stub
		String editor = ((Administrator) ActionContext.getContext()
				.getSession().get("admin")).getAdmUsername();
		ExamRule rule = examRuleDao.findById(ruleId);
		if (rule == null)
			return false;
		List<ExamQuestion> list = null;
		if (model == 0) {
			list = parseContent(questContent, ruleId, paperId, questType,
					startNo, editor);
		} else {
			list = parseContent2(questContent, ruleId, paperId, questType,
					startNo, editor);
		}
		if (list.size() == 0) {
			return false;
		}
		examQuestionDao.save(list,
				rule.getRuleQuestionNum() - rule.getRuleActualAddNum());
		// ����rule
		rule.setRuleActualAddNum(rule.getRuleActualAddNum() + list.size());
		examRuleDao.update(rule);
		return true;
	}

	private List<ExamQuestion> parseContent(String questContent, int ruleId,
			int paperId, int questType, int startNo, String editor) {
		List<ExamQuestion> list = new ArrayList<ExamQuestion>();
		try {
			questContent = "<p><br /></p>" + questContent;
			questContent = questContent.replaceAll("<[/]?p>", "")
					.replaceAll("&nbsp;", "")
					.replaceAll("<br />[0-9]+[.����]", "@@@@");
			String[] contentArr = questContent.split("@@@@");
			StringBuffer buf = new StringBuffer();
			StringBuffer md5buf = new StringBuffer();
			for (int i = 1; i < contentArr.length; i++) {
				String s = contentArr[i];
				ExamQuestion q = new ExamQuestion();
				if (buf.length() != 0) {
					buf.delete(0, buf.length());
				}
				buf.append(s.replaceAll("<br />", "\n"));
				String content = buf.substring(0, buf.indexOf("[��]:")).trim();
				q.setQuestContent(content);
				String md5 = DegistUtil.getMd5CodeOfQuestion(content);
				q.setQuestMd5code(md5);
				if (list.size() == 0) {
					q.setQuestAnswer(buf.substring(buf.indexOf("[��]:") + 5,
							buf.indexOf("[����]:")).trim());
					q.setQuestAnalysis(buf.substring(buf.indexOf("[����]:") + 5)
							.trim());
					q.setQuestAddTime(new Date());
					q.setQuestOrderId(startNo);
					q.setQuestType(questType);
					q.setQuestRuleId(ruleId);
					q.setQuestPaperId(paperId);
					q.setQuestEditor(editor);
					q.setQuestOptionNum(content
							.replaceAll("[A-Z][.)����]", "@@@").split("@@@").length - 1);
					list.add(q);
					md5buf.append(md5);
				} else if (md5buf.indexOf(md5) == -1)// �ж��ظ�
				{
					q.setQuestAnswer(buf.substring(buf.indexOf("[��]:") + 5,
							buf.indexOf("[����]:")).trim());
					q.setQuestAnalysis(buf.substring(buf.indexOf("[����]:") + 5)
							.trim());
					q.setQuestAddTime(new Date());
					q.setQuestOrderId(startNo + list.size());
					q.setQuestType(questType);
					q.setQuestRuleId(ruleId);
					q.setQuestPaperId(paperId);
					q.setQuestEditor(editor);
					q.setQuestOptionNum(content
							.replaceAll("[A-Z][.)����]", "@@@").split("@@@").length - 1);
					list.add(q);
					md5buf.append(md5);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	// ���������������棬ֻ�д�û�н���
	private List<ExamQuestion> parseContent2(String questContent, int ruleId,
			int paperId, int questType, int startNo, String editor) {
		List<ExamQuestion> list = new ArrayList<ExamQuestion>();
		try {
			questContent = "<p><br /></p>" + questContent;
			questContent = questContent.replaceAll("<[/]?p>", "").replaceAll(
					"&nbsp;", "");
			String[] answer = questContent
					.substring(questContent.indexOf("[��]:"))
					.replaceAll("[0-9]+[.����)]", "@@@").replaceAll("<br />", "")
					.split("@@@");
			questContent = questContent.substring(0,
					questContent.indexOf("[��]:")).replaceAll(
					"<br />[0-9]+[.����]", "@@@@");
			String[] contentArr = questContent.split("@@@@");
			StringBuffer buf = new StringBuffer();
			StringBuffer md5buf = new StringBuffer();
			for (int i = 1; i < contentArr.length; i++) {
				String s = contentArr[i];
				ExamQuestion q = new ExamQuestion();
				if (buf.length() != 0) {
					buf.delete(0, buf.length());
				}
				buf.append(s.replaceAll("<br />", "\n"));
				String content = buf.toString().trim();
				q.setQuestContent(content);
				String md5 = DegistUtil.getMd5CodeOfQuestion(content);
				q.setQuestMd5code(md5);
				if (list.size() == 0) {
					q.setQuestAnswer(answer[i].trim());
					// q.setQuestAnalysis(buf.substring(buf.indexOf("[����]:")+5).trim());
					q.setQuestAddTime(new Date());
					q.setQuestOrderId(startNo);
					q.setQuestType(questType);
					q.setQuestRuleId(ruleId);
					q.setQuestPaperId(paperId);
					q.setQuestEditor(editor);
					q.setQuestOptionNum(content
							.replaceAll("[A-Z][.)����]", "@@@").split("@@@").length - 1);
					list.add(q);
					md5buf.append(md5);
				} else if (md5buf.indexOf(md5) == -1)// �ж��ظ�
				{
					q.setQuestAnswer(answer[i].trim());
					// q.setQuestAnalysis(buf.substring(buf.indexOf("[����]:")+5).trim());
					q.setQuestAddTime(new Date());
					q.setQuestOrderId(startNo + list.size());
					q.setQuestType(questType);
					q.setQuestRuleId(ruleId);
					q.setQuestPaperId(paperId);
					q.setQuestEditor(editor);
					q.setQuestOptionNum(content
							.replaceAll("[A-Z][.)����]", "@@@").split("@@@").length - 1);
					list.add(q);
					md5buf.append(md5);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	public List<ExamQuestion> findQuestionOfRule(int ruleId) throws Exception {
		// TODO Auto-generated method stub
		return examQuestionDao.findAllByRuleId(ruleId);
	}

	//����ĳ�����µ�����С��
	public List<ExamQuestion> findByCriteria(String criteria) throws Exception {
		// TODO Auto-generated method stub
		return examQuestionDao.findByCriteria(criteria);
	}
}
