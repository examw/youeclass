package com.changh.sccms.until;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 学习卡过期时间计算器
 * @author Administrator
 *
 */
public class OverdueDateUtil {
	private static GregorianCalendar c = new GregorianCalendar();
	public static Date getOverdueDate(int month)
	{
		c.add(Calendar.MONTH, month);
		Date d = c.getTime();
		c.add(Calendar.MONTH, 0-month);
		return  d;
	}
}
