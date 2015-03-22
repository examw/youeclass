package com.changh.sccms.test;

import jxl.format.Alignment;  

import jxl.format.Border;  

import jxl.format.BorderLineStyle;  

import jxl.format.Colour;  

import jxl.format.VerticalAlignment;  

import jxl.write.WritableCellFormat;  

import jxl.write.WritableFont;  

  

public class ExcelStyleUtils {  

  

    /** 

     * ��������������Excel�ĵ������������ 

     * �޸����ڣ�2011-04-28 

     * @author myclover 

     * @param size  �����С 

     * @return 

     * @throws Exception 

     */  

    public static WritableFont titleFont(int size) throws Exception{  

        WritableFont titleFont = new WritableFont(WritableFont.TAHOMA);  

        titleFont.setBoldStyle(WritableFont.BOLD);  

        titleFont.setColour(Colour.BLACK);  

        titleFont.setPointSize(size);  

        return titleFont;  

    }  

      

    /** 

     * ��������������Excel�ĵ������������ 

     * �޸����ڣ�2011-04-28 

     * @author myclover 

     * @param size  �����С 

     * @return 

     * @throws Exception 

     */  

    public static WritableFont contentFont(int size) throws Exception{  

        WritableFont titleFont = new WritableFont(WritableFont.TAHOMA);  

        titleFont.setColour(Colour.BLACK);  

        titleFont.setPointSize(size);  

        return titleFont;  

    }  

      

    /** 

     * ��������������Excel�ĵ����ⵥԪ����ʽ 

     * �޸����ڣ�2011-04-28 

     * @author myclover 

     * @param align  ���뷽ʽ 

     * @param border �Ƿ��б߿� 

     * @param size   �����С 

     * @return 

     * @throws Exception 

     */  

    public static WritableCellFormat titleCellFormat(Alignment align, boolean border, int size) throws Exception{  

        WritableCellFormat titleFormat = new WritableCellFormat();  

        titleFormat.setAlignment(null == align?Alignment.CENTRE:align);  

        titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);  

        if(border){  

            titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  

        }  

        titleFormat.setFont(titleFont(size));  

        titleFormat.setWrap(true);  

        return titleFormat;  

    }  

      

    /** 

     * ��������������Excel�ĵ����ݵ�Ԫ����ʽ 

     * �޸����ڣ�2011-04-28 

     * @author myclover 

     * @param align  ���뷽ʽ 

     * @param border �Ƿ��б߿� 

     * @param size   �����С 

     * @return 

     * @throws Exception 

     */  

    public static WritableCellFormat contentCellFormat(Alignment align, boolean border, int size) throws Exception{  

        WritableCellFormat contentFormat = new WritableCellFormat();  

        contentFormat.setAlignment(null == align?Alignment.CENTRE:align);  

        contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);  

        if(border){  

            contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  

        }  

        contentFormat.setFont(contentFont(size));  

        contentFormat.setWrap(true);  

        contentFormat.setShrinkToFit(true);  

        return contentFormat;  

    }  

      

}  





