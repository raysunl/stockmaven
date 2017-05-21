package com.sunstock.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunstock.comm.util.StockThread;
import com.sunstock.dao.mapper.StockMapper;
import com.sunstock.service.EndPriceService;
import com.sunstock.service.StockCodes;
import com.sunstock.service.StockData;

@Controller
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	private StockData stockData;
	
	@Autowired
	private StockCodes stockCodes;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private EndPriceService endPriceService;

	@RequestMapping("/importStock")
	public void importStock(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, String> map = new HashMap<String,String>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		List<HashMap<String,String>> hlist = new ArrayList<HashMap<String,String>>();
		hlist= stockCodes.queryStocks();
		for (HashMap<String,String> hmap: hlist){
			map.put(hmap.get("NAME"), hmap.get("CODE"));
		}
		
		System.out.println("沪深共计" + map.size() +"支股票");
		
		request.getSession().setMaxInactiveInterval(100000);
		
		long currentTime = System.currentTimeMillis();
		
		startFetch(map);

		long passedTime = (System.currentTimeMillis()- currentTime)/1000;
		System.out.println("-----结束------消耗：" + passedTime);

	}

	public void startFetch(Map<String, String> map){
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50); 
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			
			fixedThreadPool.execute(new StockThread(entry.getValue(),entry.getKey()));
		}

		while(true){
			if(fixedThreadPool.isTerminated()){  //判断是否有任务执行。
				break;
			}
		}
	}
	
	@RequestMapping("/negetive")
	public void negetive(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, String> map = new HashMap<String,String>();
		Map<String, String> result = new HashMap<String,String>();
		List<HashMap<String,String>> hlist = new ArrayList<HashMap<String,String>>();
		hlist= stockCodes.queryStocks();
		for (HashMap<String,String> hmap: hlist){
			map.put(hmap.get("NAME"), hmap.get("CODE"));
		}
		
		System.out.println("沪深共计" + map.size() +"支股票");
		
		request.getSession().setMaxInactiveInterval(100000);
		
		long currentTime = System.currentTimeMillis();
		
		result = endPriceService.negetive(map);
		String names = "";
		for(Map.Entry<String, String> entry: result.entrySet()){
			if (!names.equals("")){
				names += "|";
			}
			names += entry.getValue();
		}
		System.out.println("股票" + names);
		long passedTime = (System.currentTimeMillis()- currentTime)/1000;
		System.out.println("-----结束------消耗：" + passedTime);

	}
	
	public Map<String, String> fetchNameCode() throws Exception{
		Map<String, String> map = new HashMap<String,String>();
		
		
//		String eastMoney = "http://quote.eastmoney.com/stocklist.html";
//		URL url = new URL(eastMoney);
		
		String bestopview = "http://www.bestopview.com/stocklist.html";
		URL url = new URL(bestopview);
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(url.openStream(),"gb2312"));
		
		String str = "";
//		String eastMoneyRegex = "(href=\\\"http://quote.eastmoney.com/.*.html\\\">.*</a></li>)";
//		Pattern pattern = Pattern.compile(eastMoneyRegex);
		
		String bestopviewRegex = "(href=\"http://www.bestopview.com/superView.php\\?stockcode=.*)";
		Pattern pattern = Pattern.compile(bestopviewRegex);
		
		while ((str = br.readLine()) != null) {
			Matcher matcher = pattern.matcher(str);
			while(matcher.find()){
				String data = matcher.group();
					String data2 = data.substring(data.indexOf(">")+1,data.indexOf("("));
					String data3 = data.substring(data.indexOf("(")+1,data.indexOf(")"));
//					System.out.println("name " + data2 + " code " + data3);
					map.put(data2, data3);
			}
		}
		return map;
	}
	
	@RequestMapping("/insertHuShenStock")
	public void insertHuShenStock() throws Exception{
		File file = new File("d:/stock");
		File[] fileList = file.listFiles();
		for(int i = 0; i<fileList.length; i++){
			if(fileList[i].isFile()){
				stockCodes.insertStockCodes(fileList[i]);
			}
		}
	}

}
