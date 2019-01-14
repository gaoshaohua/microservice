package com.micro.tools.generate.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

public class Table {

	private String className;
	private String tableName;
	private String basePackageName;
	private String entityPackageName;
	private String entityVoPackageName;
	private String primarykey;
	private String daoPackageName;
	private String servicePackageName;
	private String controllerPackageName;
	private String mybatisPath;
	private String createDate;
	private List<Column> columns = Lists.newArrayList();
	

	public Table() {
		createDate = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss").format(new Date());
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
        
        if (basePackageName != null) {
            this.entityPackageName = basePackageName+".entity";
            this.entityVoPackageName = basePackageName+".vo";
            this.daoPackageName = basePackageName+".dao";
            this.servicePackageName = basePackageName+".service";
            this.controllerPackageName = basePackageName+".controller";
        }
        
    }
    
	public String getEntityPackageName() {
		return entityPackageName;
	}

	public void setEntityPackageName(String entityPackageName) {
	    this.entityPackageName = entityPackageName;
	}

	public String getDaoPackageName() {
		return daoPackageName;
	}

	public void setDaoPackageName(String daoPackageName) {
		this.daoPackageName = daoPackageName;
	}

	public String getServicePackageName() {
		return servicePackageName;
	}

	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
	}

	public String getControllerPackageName() {
		return controllerPackageName;
	}

	public void setControllerPackageName(String controllerPackageName) {
		this.controllerPackageName = controllerPackageName;
	}

	public String getMybatisPath() {
		return mybatisPath;
	}

	public void setMybatisPath(String mybatisPath) {
		this.mybatisPath = mybatisPath;
	}

	public String getPrimarykey() {
		return primarykey;
	}

	public void setPrimarykey(String primarykey) {
		this.primarykey = primarykey;
	}

	public String getEntityVoPackageName() {
		return entityVoPackageName;
	}

	public void setEntityVoPackageName(String entityVoPackageName) {
		this.entityVoPackageName = entityVoPackageName;
	}

    

}
