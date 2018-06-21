package com.baishui.dom4j;

import java.util.ArrayList;
import java.util.List;

public class TableModel {

	
	private String tableName;
	private List<String> columns = new ArrayList<String>() ;
	 
	private List<String> dataTypes = new ArrayList<String>();

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<String> getDataTypes() {
		return dataTypes;
	}

	public void setDataTypes(List<String> dataTypes) {
		this.dataTypes = dataTypes;
	}
	 
	
	
	
}
