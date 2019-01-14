package com.micro.tools.generate.vo;

import com.micro.tools.generate.utils.StrUtil;

public class Column {

	private String columnType;
	private String columnName;
	private String realName;
	private String columnDesc;
	private String isPrimaryKey = "false";

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDesc() {
		if (StrUtil.isBlank(columnDesc)) {
			columnDesc = columnName;
		}
		return columnDesc;
	}

	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}

	public String getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public void setIsPrimaryKey(String isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public String getRealName() {
		realName = StrUtil.underlineToCamelhump(columnName);
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
