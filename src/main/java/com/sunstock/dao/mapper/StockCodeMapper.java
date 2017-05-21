package com.sunstock.dao.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface StockCodeMapper {

	public int insertStockCode(List<Map<String,String>> list);
	public List<HashMap<String,String>> queryStockCodes();
}
