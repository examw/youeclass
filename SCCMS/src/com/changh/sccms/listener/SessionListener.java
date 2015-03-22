package com.changh.sccms.listener;


 import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.changh.sccms.entity.Administrator;

   

public class SessionListener implements HttpSessionListener,HttpSessionAttributeListener {      

    // ���浱ǰ��¼�������û�   

    public static Map<HttpSession, Administrator> loginAdmin = new HashMap<HttpSession, Administrator>();   

    /**  

     * session����ʱ�����������  

     */   
    public void sessionCreated(HttpSessionEvent se) {
    	// TODO Auto-generated method stub
    	
    }
    /**
     * SessionʧЧ���߹��ڵ�ʱ����õ��������  
     */
    public void sessionDestroyed(HttpSessionEvent se) {
    	// TODO Auto-generated method stub
    	//�Ƴ�����û�
    	loginAdmin.remove(se.getSession());
    }
    
    /**
     *ִ��setAttribute��ʱ��, ��������Ա�����������Session��ʱ, �����������. 
     */
    
    public void attributeAdded(HttpSessionBindingEvent se) {
    	// TODO Auto-generated method stub
    	if(se.getName().equals("admin"))
    	{
    		Administrator admin = (Administrator) se.getValue();
    		//�Ѵ��ڣ�Ҳ�����Ѿ���½
    		if(admin!=null)
    		{
    			destroyedOldSession(admin);   
    		}
    		loginAdmin.put(se.getSession(), admin);
    	}
    }
    
    public void attributeRemoved(HttpSessionBindingEvent se) {
    	// TODO Auto-generated method stub
    	if(se.getName().equals("admin"))
    	{
    		loginAdmin.remove(se.getSession());
    	}
    }
    
    public void attributeReplaced(HttpSessionBindingEvent se) {
    	// TODO Auto-generated method stub
    	if(se.getName().equals("admin"))
    	{
    		loginAdmin.put(se.getSession(), (Administrator)se.getValue());
    	}
    }
    /**
     * �����ǰ�û��Ѿ���¼������session����  
     */
    private void destroyedOldSession(Administrator admin)
    {
    	for(Entry<HttpSession,Administrator> entry : loginAdmin.entrySet()){   
    		Administrator admin1 = entry.getValue();
    		Integer tempAdminId = admin1.getAdmId();  	   
            if(null != admin1 && admin.getAdmId().equals(tempAdminId)){   
                HttpSession session = entry.getKey();   
                session.removeAttribute("admin");   
                //session.setAttribute(SysConstant.SESSION_UDER_RE_LOGIN, SysConstant.SESSION_UDER_RE_LOGIN);   
                loginAdmin.remove(session); 
                break;
             }   
    }
    }
}