package com.sunstock.entity;

public class StockCode extends BaseEntity{

	private static final long serialVersionUID = 889783552434997494L;

	private String stockCode;
	private String stockName;

	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	

}
