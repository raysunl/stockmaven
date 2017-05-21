package com.sunstock.dao.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.annotation.Transactional;


/**
 * 数据源AOP
 * 
 * @author wangjianjun
 * 
 */
public class DataSourceAspect {
	
	public void before(JoinPoint point) throws Exception{
		Object target = point.getTarget();
		String method = point.getSignature().getName();

		Class<?> classz = target.getClass();

		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
				.getMethod().getParameterTypes();
	
		Method m = classz.getMethod(method, parameterTypes);
		
		boolean readOnly = false;
		
		if(m != null){
			if(m.isAnnotationPresent(Transactional.class)){
				Transactional tran = m.getAnnotation(Transactional.class);
				readOnly = tran.readOnly();
			}
		}
		
		if(readOnly){
			DynamicDataSourceHolder.putDataSource("read");				
		}else{
			DynamicDataSourceHolder.putDataSource("write");
		}
			
	}
}