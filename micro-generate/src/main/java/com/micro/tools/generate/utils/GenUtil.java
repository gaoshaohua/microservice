package com.micro.tools.generate.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.micro.tools.generate.vo.Table;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class GenUtil {

    @Value("${generate.javaPath}")
    private String javaPath;

    @Value("${generate.mybatisPath}")
    private String mybatisPath;

    public static Configuration cfg = null;

    static {
        cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        cfg.setDefaultEncoding("UTF-8");
        String filePath = GenUtil.class.getClassLoader().getResource("templates").getPath();
        File templateFile = new File(filePath);
        try {
            cfg.setDirectoryForTemplateLoading(templateFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void genEntity(Table table) {
        try {
            String basePath = javaPath + File.separator + table.getEntityPackageName().replace(".", File.separator)
                    + File.separatorChar;
            genCode(table, basePath, table.getClassName() + ".java", "Entity.java.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genDao(Table table) {
        try {
            String basePath = javaPath + File.separator + table.getDaoPackageName().replace(".", File.separator)
                    + File.separatorChar;
            genCode(table, basePath, "I" + table.getClassName() + "Dao.java", "Dao.java.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genService(Table table) {
        try {
            String basePath = javaPath + File.separator + table.getServicePackageName().replace(".", File.separator)
                    + File.separatorChar;
            genCode(table, basePath, "I" + table.getClassName() + "Service.java", "IService.java.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genServiceImpl(Table table) {
        try {
            String basePath = javaPath + File.separator + table.getServicePackageName().replace(".", File.separator)
                    + File.separator+"impl"+ File.separatorChar;
            genCode(table, basePath, table.getClassName() + "ServiceImpl.java", "ServiceImpl.java.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void genController(Table table) {
        try {
            String basePath = javaPath + File.separator + table.getControllerPackageName().replace(".", File.separator)
                     + File.separatorChar;
            genCode(table, basePath, table.getClassName() + "Controller.java", "Controller.java.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genMybatis(Table table) {
        try {
            String basePath = mybatisPath + File.separator;
            genCode(table, basePath, table.getClassName() + ".xml", "Mapper.xml.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genCode(Table table, String savePath, String fileName, String templateName) {
        try {
            File baseFile = new File(savePath);
            if (!baseFile.exists()) {
                baseFile.mkdirs();
            }
            savePath += fileName;
            Map<String, Object> entityMap = new HashMap<String, Object>();
            entityMap.put("model", table);
            Writer entityOut;

            entityOut = new OutputStreamWriter(new FileOutputStream(savePath), "UTF-8");

            Template entityTemplate = cfg.getTemplate(templateName, "UTF-8");
            entityTemplate.process(entityMap, entityOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
