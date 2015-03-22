package com.changh.sccms.until; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
  
public class FileUntil {  
	
	public static boolean copyFile(String from,String to){
		File target = new File(getRootPath()+to);
		if (target.exists()) {
			return false;
		}else{
			File source = new File(getRootPath()+from);
			try {
				nioTransferCopy(source,target);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}  
    
    @SuppressWarnings("unused")
	private static void nioTransferCopy(File source, File target) throws Exception {  
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	inStream.close();
            in.close();
            outStream.close();
            out.close();
        }
    }
    
   

    private static String getRootPath() {
		return PropertiesUtil.getOptValue("rootSCCMS")+File.separator+"WEB-INF/template/";
	}
}  