package com.sunstock.comm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sunstock.dao.mapper.StockMapper;
import com.sunstock.service.StockData;

public class StockEndPriceThread implements Runnable{
	public static StockData stockData;
	public static StockMapper stockMapper;
	static{

		 WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		 stockData = context.getBean(StockData.class);
		 stockMapper  = context.getBean(StockMapper.class);
	}

	private String code;
	private String name;
	private Map<String,String> map;
	
	public StockEndPriceThread(String code,String name, Map<String,String> map){
		this.code = code;
		this.name = name;
		this.map = map;
	}

	@Override
	public void run() {
		try{
			HashMap<String, String> map2 = new HashMap<String,String>();
			Map<Integer, Double> map3 = new HashMap<Integer,Double>();
			map2.put("code", code);
			List<HashMap<String,Object>> hlist2 = new ArrayList<HashMap<String,Object>>();
			hlist2 = stockMapper.queryStocks(map2);
			if(hlist2 != null && hlist2.size() > 0){
				for(int i = 0; i<hlist2.size();i++){
					if(i+1<hlist2.size()){
						String priceString1 = hlist2.get(i).get("ENDPRICE").toString();
						String priceString2 = hlist2.get(i+1).get("ENDPRICE").toString();
//						double price1 = Double.valueOf(hlist2.get(i).get("ENDPRICE")).doubleValue();
						double price1 = (new Double(priceString1)).doubleValue();
						double price2 = (new Double(priceString2)).doubleValue();
//						double price2 = Double.valueOf(hlist2.get(i+1).get("ENDPRICE")).doubleValue();
						double rate = price1/price2;
						map3.put(i, rate);
					}
					
				}
			}
//			System.out.println("查完了");
			int count = 0;
			int skip = 0;
			for(int j = 0; j<map3.size();j++){

				if(count == 3 && skip ==3){
					map.put(code, name);
					System.out.println("---开始添加---" +name);
					break;
				}
				if(map3.get(j) < 1){
					count++;
				}
				skip++;
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
