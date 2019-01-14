package com.micro.tools.generate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.tools.generate.service.ITableService;
import com.micro.tools.generate.utils.GenUtil;
import com.micro.tools.generate.vo.Table;

@SpringBootApplication
@RestController
@RequestMapping("/auto")
public class MicroGenerateApplication {

    @Autowired
    private ITableService tableServiceImpl;
    
    @Autowired
    private GenUtil genUtil;
    
	public static void main(String[] args) {
		SpringApplication.run(MicroGenerateApplication.class, args);
	}

	@RequestMapping("/go")
	public String test(){
	    List<Table> tables = tableServiceImpl.getTables();
	    for (int i = 0; i < tables.size(); i++) {
	        autoGen(tables.get(i));
        }
	    return "代码生成完毕";
	}
	
	private void autoGen(Table table){
	    //生成entity
	    genUtil.genEntity(table);
	    //生成dao
	    genUtil.genDao(table);
	    //生成service接口
	    genUtil.genService(table);
	    //生成service实现
	    genUtil.genServiceImpl(table);
	    //生成controller
	    genUtil.genController(table);
	    //生成mybatis
	    genUtil.genMybatis(table);
	}
	
	
}

