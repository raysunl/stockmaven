package com.sunstock.dao.interceptor;

public abstract class SearchPageableCondition {

	private Integer offset;
	
	private Integer limit;
	
	protected String sidx = null;	//排序字段
	
	protected String sord = null;	//排序规则

	
	private boolean autoCount = false;
	
	
	
	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
	
}
