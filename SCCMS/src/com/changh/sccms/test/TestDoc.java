package com.changh.sccms.test;

import java.io.File;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

  

public class TestDoc {  

  

    /** 

     * @param args 

     */  

    public static void main(String[] args) {          

        exportExcel("D:\\text.xls");  

    }  

  

public static void exportExcel(String fileName){  

        try{  

            WritableWorkbook book = Workbook.createWorkbook(new File(fileName));  

            WritableSheet sheet = book.createSheet("ѧϰ���б�", 0);  

            // ���ø��п��  

            sheet.setColumnView(0, 10);  //���

            sheet.setColumnView(1, 20);  //����

            sheet.setColumnView(2, 15);  //����

            sheet.setColumnView(3, 25);  //����ʱ��

            sheet.setColumnView(4, 25);  //����ʱ��

            sheet.setColumnView(5, 15);  //��ֵ

            sheet.setColumnView(6, 15);  //�Ƿ�����

            sheet.setColumnView(7, 15);  

            sheet.setColumnView(8, 20);  

            sheet.setColumnView(9, 20);  

            sheet.setColumnView(10, 10);  

              

            // �����и�  

            sheet.setRowView(0, 500);  

            sheet.setRowView(1, 500);  

              

            // ��һ��  

            sheet.mergeCells(0, 0, 10, 0);  

            Label label = new Label(0,0,"����JXL����Excel���");  

            label.setCellFormat(ExcelStyleUtils.titleCellFormat(null, false, 16));  

            sheet.addCell(label);  

              

            // �ڶ���  

            sheet.mergeCells(0, 1, 10, 1);  

            Label line2 = new Label(0,1,"2011��4��28��");  

            line2.setCellFormat(ExcelStyleUtils.titleCellFormat(Alignment.RIGHT, false, 14));  

            sheet.addCell(line2);  

              

            // ������  

            sheet.addCell(new Label(0, 2, "����", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(1, 2, "�Ա�", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(2, 2, "��������", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(3, 2, "�μӹ���ʱ��", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(4, 2, "��ҵʱ��", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(5, 2, "��ҵԺ У��רҵ", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(6, 2, "רҵְ��", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(7, 2, "����ְ��", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(8, 2, "����ְ��", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(9, 2, "�س�", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

            sheet.addCell(new Label(10, 2, "��ע", ExcelStyleUtils.titleCellFormat(null, true, 12)));  

              

            // ѭ���������  

            int line = 3;  

            int counts = 0;  

            for(int i = 1 ; i <= 15 ; i++){  

                sheet.mergeCells(0, line, 10, line);  

                sheet.addCell(new Label(0, line, ++counts + "��" + "��������_" + i, ExcelStyleUtils.titleCellFormat(Alignment.LEFT, true, 12)));  

                line++;  

                for(int j = 0 ; j < 3 ; j++){  

                    sheet.addCell(new Label(0, line, "����_" + j, ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    String sex = "��";  

                    if(j == 2 || i % 2 == 0){  

                        sex = "Ů";  

                    }  

                    sheet.addCell(new Label(1, line, sex, ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(2, line, 1980+i-j+"��" + (3+ j) +"��"+(i + j)+"��", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(3, line, 2000+i-j+"��" + (3+ j) +"��"+(i + j)+"��", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(4, line, 1996+i-j+"��7��1��", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(5, line, "��ѧ����", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(6, line, "java��������ʦ", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(7, line, "Java�з�", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(8, line, "����PM", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(9, line, "Java��J2EE��SQL��SSH��JBPM", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    sheet.addCell(new Label(10, line, "���ּ���ţ��", ExcelStyleUtils.contentCellFormat(null, true, 10)));  

                    line++;  

                }  

            }  

              

            book.write();  

            book.close();  

        }catch(Exception e){  

            e.printStackTrace();  

        }  

    }  

  

}          