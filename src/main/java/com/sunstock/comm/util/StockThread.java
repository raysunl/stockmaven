package com.sunstock.comm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sunstock.dao.mapper.StockMapper;
import com.sunstock.service.StockData;

public class StockThread implements Runnable{
	public static StockData stockData;
	public static StockMapper stockMapper;
	static{

		 WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		 stockData = context.getBean(StockData.class);
		 stockMapper  = context.getBean(StockMapper.class);
	}

	private String code;
	private String name;
	
	public StockThread(String code,String name){
		this.code = code;
		this.name = name;
	}

	@Override
	public void run() {
		try{
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			HashMap<String, String> map2 = new HashMap<String,String>();
			map2.put("code", code);
			List<HashMap<String,Object>> hlist2 = new ArrayList<HashMap<String,Object>>();
			hlist2 = stockMapper.queryStocks(map2);
			if(hlist2 == null || hlist2.size() == 0){
				System.out.println("开始获取股票[" + name + "],[" + code + "] and currentThread" + Thread.currentThread().getName() );
				list = stockData.getStockData(name,code, "2017", "2");
				if (list.size() >= 1) {
					int num = stockData.insertStock(list);
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}
