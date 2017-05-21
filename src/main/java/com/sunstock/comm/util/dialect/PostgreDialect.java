package com.sunstock.comm.util.dialect;


public class PostgreDialect extends Dialect{
	
	protected static final String SQL_END_DELIMITER = ";";
	
	
	
	public String getLimitString(String sql, boolean hasOffset) {
		return PostgrePageHepler.getLimitString(sql,-1,-1);
	}

	public String getLimitString(String sql, int offset, int limit) {
		return PostgrePageHepler.getLimitString(sql, offset, limit);
	}

	public boolean supportsLimit() {
		return true;
	}
}
