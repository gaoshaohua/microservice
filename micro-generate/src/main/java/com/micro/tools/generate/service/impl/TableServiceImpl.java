package com.micro.tools.generate.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.micro.tools.generate.service.ITableService;
import com.micro.tools.generate.utils.StrUtil;
import com.micro.tools.generate.vo.Column;
import com.micro.tools.generate.vo.Table;

@Service
public class TableServiceImpl implements ITableService {

    @Autowired
    private DataSource dataSource;
    
    @Value("${generate.dbName}")
    private String dbName;
    
    private String[] tableNames;
    
    @Value("${generate.packageName}")
    private String packageName;
    
    @Value("${generate.removedTableNamePrefixes}")
    private String[] removedTableNamePrefixes;
    
    @Value("${generate.mybatisPath}")
    private String mybatisPath;
    
    @Value("${generate.tableNames:[]}")
    public void setTableNames(String[] tableNames) {
        if(tableNames.length==0){
            try {
                Connection conn = dataSource.getConnection();
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet rs = meta.getTables(dbName, null, "%", new String[] { "TABLE" });
                rs.last();
                tableNames = new String[rs.getRow()];
                int i = 0;
                rs.beforeFirst();
                while (rs.next()) {
                    tableNames[i] = rs.getString(3);
                    System.out.println("表名："+tableNames[i]);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("总表数："+tableNames.length);
        this.tableNames=tableNames;
    }

    @Override
    public List<Table> getTables() {
        List<Table> tables = Lists.newArrayList();
        try {
            Connection conn = dataSource.getConnection();
            DatabaseMetaData dbmd = conn.getMetaData();
            if (tableNames != null && tableNames.length > 0) {
                for (int i = 0; i < tableNames.length; i++) {
                    String primarykey = null;
                    Table table = new Table();
                    table.setTableName(tableNames[i]);
                    table.setBasePackageName(packageName+"."+getRealTableName(tableNames[i]));
                    table.setMybatisPath(mybatisPath);
                    table.setClassName(getRealTableName(tableNames[i]));
                    List<Column> columnsList = Lists.newArrayList();
                    ResultSet rs = dbmd.getColumns(null, null, tableNames[i], "%");
                    ResultSet primaryKeys = dbmd.getPrimaryKeys(conn.getCatalog(), null, tableNames[i]);
                    while (primaryKeys.next()) {
                        primarykey = primaryKeys.getString("COLUMN_NAME");
                    }
                    table.setPrimarykey(primarykey);
                    while (rs.next()) {
                        String columnName = rs.getString("COLUMN_NAME");
                        Column column = new Column();
                        column.setColumnName(columnName);
                        column.setColumnDesc(rs.getString("REMARKS"));
                        column.setColumnType(StrUtil.sqlType2JavaType(rs.getString("COLUMN_NAME"), rs.getString("TYPE_NAME")));
                        if (null!=primarykey&&primarykey.equals(column.getColumnName())) {
                            column.setIsPrimaryKey("true");
                        }
                        columnsList.add(column);
                    }
                    table.setColumns(columnsList);
                    tables.add(table);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public String getRealTableName(String tablename) {
        String str = tablename;
        if (removedTableNamePrefixes != null && removedTableNamePrefixes.length > 0) {
            for (String s : removedTableNamePrefixes) {
                if(tablename.contains(s)) {
                    str = str.substring(s.length(), str.length());
                    break;
                }
            }
        }
        str = StrUtil.underlineToCamelhump(str);
        str = StrUtil.firstCharToUpperCase(str);
        return str;
    }

    

}
