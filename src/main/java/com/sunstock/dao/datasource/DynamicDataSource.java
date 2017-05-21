package com.sunstock.dao.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.sunstock.dao.datasource.DynamicDataSourceHolder;

/**
 * 动态数据源
 * 
 * @author wangjianjun
 * 
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSouce();
	}

}