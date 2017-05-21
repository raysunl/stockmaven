/**
*寮�鍙戝崟浣嶏細FESCO Adecco 
*鐗堟潈锛欶ESCO Adecco
*@author锛歸u.jinglei@fescoadecco.com
*@since锛� JDK1.6
*@version锛�1.0
*@date锛�2014骞�5鏈�23鏃� 涓嬪崍2:24:15
*/ 

package com.sunstock.comm.util.listener;

import javax.servlet.ServletContext;  
import javax.servlet.ServletContextEvent;  
  

import org.springframework.context.ApplicationContext;  
import org.springframework.web.context.ContextLoaderListener;  
import org.springframework.web.context.support.WebApplicationContextUtils;  

import com.sunstock.comm.util.SpringUtil;
  
/**
 * @ClassName: SpringContextLoaderListener
 * @Description: 鑷畾涔塻pring鍔犺浇鍣紝鎶夾pplicationContext瑁呰浇鍒癝pringUtil 
 * @author wu.jinglei@fescoadecco.com
 * @date 2014骞�5鏈�23鏃� 涓嬪崍2:24:15
 *
 */
public class SpringContextLoaderListener extends ContextLoaderListener {  
  
    public void contextInitialized(ServletContextEvent event) {  
  
        // 鍒濆鍖栫埗绫� ContextLoaderListener  
        super.contextInitialized(event);  
  
        ServletContext context = event.getServletContext();  
  
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);  
  
        // 瑁呰浇 SpringUtil 涓殑 ApplicationContext  
        new SpringUtil().setApplicationContext(ctx);  
    }  
}  
