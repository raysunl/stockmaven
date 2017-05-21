/**
*开发单位：FESCO Adecco 
*版权：FESCO Adecco
*@author：wu.jinglei@fescoadecco.com
*@since： JDK1.6
*@version：1.0
*@date：2014年5月23日 下午2:25:24
*/ 

package com.sunstock.comm.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
  
/**
 * @ClassName: SpringUtil
 * @Description: Spring工具类，提供取得Spring配置文件中定义的Bean的方法 
 * @author wu.jinglei@fescoadecco.com
 * @date 2014年5月23日 下午2:25:24
 *
 */
public class SpringUtil implements ApplicationContextAware {  
	  
    private static ApplicationContext applicationContext;
    
    public static ApplicationContext current() {
        return applicationContext;
    }
  
    @Override  
    public void setApplicationContext(ApplicationContext context) throws BeansException {  
        SpringUtil.applicationContext = context;  
    }  
  
    public static Object getBean(String name) {    
        return applicationContext.getBean(name);  
    }
}  
