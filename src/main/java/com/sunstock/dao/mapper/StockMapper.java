package com.sunstock.dao.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StockMapper {

	public int insertStock(List<Map<String,Object>> list);
	public List<HashMap<String,Object>> queryStocks(HashMap<String,String> map);
}
