package com.sunstock.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunstock.dao.mapper.StockMapper;

/**
 * ץȡ������ݵ���
 * ���λ �� ���ڡ������ߡ��ա��͡�������(���׽��)
 * @author Administrator
 *
 */
@Service
public class StockData {

	@Autowired
	private StockMapper stockMapper;
	
	private static String myStr="http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/";
/*���������磺http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/" +
"600006.phtml?year=2013&jidu=4";*/

	@SuppressWarnings("unused")
	public List<Map<String, Object>> getStockData(String name, String code,String year,String jidu) throws IOException{
		BufferedReader reader=null;
		try {
			
			//ƴ������url
			String tempStr=myStr+code+".phtml?year="+year+"&jidu="+jidu;
			URL url=new URL(tempStr);
			//String[] header = {"����","���̼�","��߼�","���̼�","��ͼ�","������(��)","���׽��(Ԫ)","test"};

			System.out.println(url); 
			///����������
			reader=new BufferedReader(new InputStreamReader(url.openStream()));
			
			if(reader == null){
				System.out.println("---reader为空---");
				reader=new BufferedReader(new InputStreamReader(url.openStream()));
			} 
			
			System.out.println("---服务器返回---");
			///����list�洢��
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			//����hashmap�洢ÿһ�о������
			HashMap<String, Object> hashMap=new HashMap<String, Object>();
			
			//������ȡ��ݵ�pattern������ʽ
			String regex="(?<=(date=)).*?(?=('>))|(?<=(\"center\">))\\d{1}.*?(?=(</div>))";
			String endregex = "(<div class=\"clearit\"></div>)";;
			Pattern dataPattern=Pattern.compile(regex);
			Pattern endPattern=Pattern.compile(endregex);
			
			///ƥ���ȡ���ݣ�������list����
			int i=0;
			String str = "";
			int j = 0;
			while((str = reader.readLine())!= null){
				//�����һ��ƥ������
				j++;
				
				if(j<900){
					continue;
				}
				Matcher endMatcher=endPattern.matcher(str);
				if(endMatcher.find()){
					System.out.println("该结束了");
					return list;
				}
				
				Matcher matcher=dataPattern.matcher(str);
				//����hashMap����
				while(matcher.find()){
					i++; 
					if(i == 1) {
					hashMap.put("tradeDate", matcher.group());
					continue;
					}
					if(i == 2) {
					hashMap.put("startPrice", matcher.group());
					continue;
					}
					if(i == 3) {
					hashMap.put("highestPrice", matcher.group());
					continue;
					}
					if(i == 4) {
					hashMap.put("endPrice", matcher.group());
					continue;
					}
					if(i == 5) {
					hashMap.put("lowestPrice", matcher.group());
					continue;
					}
					if(i == 6) {
					hashMap.put("turnover", matcher.group());
					continue;
					}
					if(i == 7) {
					hashMap.put("volume", matcher.group());
					continue;
					}

				}

				if(i==7){
					hashMap.put("code", code);
					hashMap.put("name", name);
					list.add(hashMap);
					i=0;
					hashMap=new HashMap<String, Object>();

				}
				if(list.size()>=5){
					return list;
				}
				
			}//end of while
			return list;
	
		} catch (IOException e) {
			System.out.println("---错误---");
			e.printStackTrace();
		}finally {
			reader.close();
			reader = null;
		}
		
		System.out.println("--返回空--");
		return null;
	}

	public int insertStock(List<Map<String,Object>> list){
		return stockMapper.insertStock(list);
	}
}