package com.changh.sccms.test;


	import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
	public class FileChooseTest {
	public static void main(String [] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	//�����Ǵ���JFileChooser �������������������ʾĬ�ϴ򿪵�Ŀ¼��������Ĭ�ϴ򿪵�ǰ�ļ����ڵ�Ŀ¼��
	JFileChooser file = new JFileChooser (".");
	//���������ȥ����ʾ
	 UIManager.setLookAndFeel(
             UIManager.getSystemLookAndFeelClassName());

    JFileChooser jf = new JFileChooser();
    jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT","txt");//����������    
    jf.setFileFilter(filter);//��ʼ����    
    int returnVal =jf.showSaveDialog(jf);
//    File fi = jf.getSelectedFile();
//    String f = fi.getAbsolutePath()+"\\test.txt";
//    System.out.println("save: "+f);
//    try{
//        FileWriter out = new FileWriter(f);
//        out.write("successful!!!");
//        out.close();
//    }
//    catch(Exception e){}
	}
	}
