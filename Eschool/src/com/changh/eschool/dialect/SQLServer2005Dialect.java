package com.changh.eschool.dialect;

import org.hibernate.dialect.SQLServerDialect;

/**
 * �޸ķ��ԣ��������ҳ
 * @author Administrator
 *
 */
public class SQLServer2005Dialect extends SQLServerDialect {

	/**
	 * 
	 * �Ƿ���Ҫ��limit����?
	 * 
	 * ��SQL Server��ʹ��topʱ����ʹ�ò�����ʾtop����,��ʹ��ROW_NUMBER()����Ҫ�ṩlimit����
	 */

	private ThreadLocal<Boolean> supportsVariableLimit = new ThreadLocal<Boolean>();
	public SQLServer2005Dialect() {
		super();   
	    registerHibernateType(1, "string");   
	    registerHibernateType(-9, "string");   
	    registerHibernateType(-16, "string");   
	    registerHibernateType(3, "double");  
		registerFunction("bitand", new BitAndFunction());
		registerFunction("bitxor", new BitXorFunction());
		registerFunction("bitor", new BitOrFunction());
		setSupportsVariableLimit(false);
	}

	/**
	 * 
	 * <p>
	 * �����Ƿ��Ȱ�limit������
	 * </p>
	 * 
	 * @param first
	 */

	private void setSupportsVariableLimit(boolean first) {
		this.supportsVariableLimit.set(Boolean.valueOf(first));
	}

	/**
	 * 
	 * <p>
	 * ��ȡsql��select�Ӿ�λ�á�
	 * </p>
	 * 
	 * @param sql
	 * 
	 * @return int
	 */
	protected static int getSqlAfterSelectInsertPoint(String sql) {
		int selectIndex = sql.toLowerCase().indexOf("select");

		int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");

		return selectIndex + ((selectDistinctIndex == selectIndex) ? 15 : 6);
	}

	public boolean supportsLimitOffset() {
		return true;
	}

	/*
	 * Hibernate�ڻ��Limit String(�������limit�Ӿ�)������˷�������true��
	 * 
	 * �����Ӷ���Ĳ���ֵ(ROW_NUMBER()��Χ)(���Կ�������������offset������������ֵ��û������һ������ֵ)
	 */
	public boolean supportsVariableLimit() {
		return ((Boolean) this.supportsVariableLimit.get()).booleanValue();
	}

	public boolean useMaxForLimit() {
		return true;
	}
	/**
	 * ��ҳtop���Ժ���ROW_NUMBER
	 */
	public String getLimitString(String query, int offset, int limit) {
		setSupportsVariableLimit(offset > 0);

		if (offset == 0) {
			return new StringBuffer(query.length() + 8).append(query).insert(
					getSqlAfterSelectInsertPoint(query), " top " + limit)
					.toString();
		}

		return getLimitString(query, offset > 0);
	}
	
	public String getLimitString(String sql, boolean hasOffset) {
		int orderByIndex = sql.toLowerCase().lastIndexOf("order by");

		if (orderByIndex <= 0) {
			throw new UnsupportedOperationException(
					"must specify 'order by' statement to support limit operation with offset in sql server 2005");
		}

		String sqlOrderBy = sql.substring(orderByIndex + 8);

		String sqlRemoveOrderBy = sql.substring(0, orderByIndex);

		int insertPoint = getSqlAfterSelectInsertPoint(sql);
		return new StringBuffer(sql.length() + 100)
				.append("with tempPagination as(")
				.append(sqlRemoveOrderBy)
				.insert(
						insertPoint + 23,
						" ROW_NUMBER() OVER(ORDER BY " + sqlOrderBy
								+ ") as RowNumber,")
				.append(
						") select * from tempPagination where RowNumber>?  and RowNumber<=?")
				.toString();
	}
}