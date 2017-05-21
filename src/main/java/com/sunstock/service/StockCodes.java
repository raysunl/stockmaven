package com.sunstock.service;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunstock.dao.mapper.StockCodeMapper;

/**
 * ץȡ������ݵ���
 * ���λ �� ���ڡ������ߡ��ա��͡�������(���׽��)
 * @author Administrator
 *
 */
@Service
public class StockCodes {

	@Autowired
	private StockCodeMapper stockCodeMapper;
	
	public void insertStockCodes(File file) throws Exception{
		if(file == null){
			System.out.println("读取的文件有误");
			throw new Exception("读取的文件有误");
		}
		Workbook wb = null;
		
		if (file.getName().endsWith("xlsx")){
			wb = new XSSFWorkbook(new FileInputStream(file));
		}
		else {
			wb = new HSSFWorkbook(new FileInputStream(file));
		}
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();

		Sheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum()+1;
		for (int i = 1; i<rowNum; i++){
			Map<String,String> map = new HashMap<String,String>();
			Row row = sheet.getRow(i);
			if(row == null){
				continue;
			}
			String code = null;
			String name = null;
			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC){
				int num = (int)row.getCell(0).getNumericCellValue();
				code = String.valueOf(num);
			}
			else if(row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING){
				code = row.getCell(0).toString();
			}
			name = row.getCell(1).getStringCellValue().trim();
			map.put("stockCode", code);
			map.put("stockName", name);
			list.add(map);
		}
		System.out.println(file.getName()+"一共有"+ list.size()+"条记录。");
		stockCodeMapper.insertStockCode(list);
	}
	
	public List<HashMap<String,String>> queryStocks(){
		return stockCodeMapper.queryStockCodes();
	}
	
}