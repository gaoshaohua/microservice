package com.micro.tools.generate.service;

import java.util.List;

import com.micro.tools.generate.vo.Table;

public interface ITableService {

    /**
     * 获取数据库中的table
     * @return
     */
    public List<Table> getTables();
    
    /**
     * 获取处理后的table名
     * @param tablename
     * @return
     */
    public String getRealTableName(String tablename);
}
