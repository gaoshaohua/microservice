<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#if model??>
<mapper namespace="${model.daoPackageName}.${model.className}Dao">

    <sql id="${model.className}Columns">
<#list model.columns as column>
    <#if !column_has_next>a.${column.columnName}<#else>a.${column.columnName},</#if>
</#list>
    </sql>
    <sql id="${model.className}Where">
    </sql>
    
    <!-- 查询所有数据 -->
    <select id="findAll" resultType="${model.entityPackageName}.${model.className}" parameterType="java.util.Map">
        SELECT <include refid="${model.className}Columns"/> FROM ${model.tableName} a <include refid="${model.className}Where"/>
    </select>
      <!-- 查询所有数据，返回map -->
    <select id="findAllByMap" resultType="java.util.Map" parameterType="java.util.Map">
        SELECT <include refid="${model.className}Columns"/> FROM ${model.tableName} a <include refid="${model.className}Where"/>
    </select>
      <!-- 批量删除 -->
    <delete id="deleteByIds">
		delete from ${model.tableName} where ${model.primarykey} in <foreach collection="array" item="item" open="(" close=")" separator=",">${"#"}{item}</foreach>
	</delete>
    <insert id="saveOne" parameterType="${model.entityPackageName}.${model.className}" useGeneratedKeys="true" keyProperty="id">
        insert into ${model.tableName} <trim prefix="(" suffix=")" suffixOverrides="," ><#list model.columns as column><if test=" ${column.realName} != null" >${column.columnName},</if></#list></trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," ><#list model.columns as column><if test=" ${column.realName} != null" >${"#"}{${column.realName}},</if></#list></trim>
    </insert>
    <update id="updateById" parameterType="${model.entityPackageName}.${model.className}">
        update ${model.tableName} <set><#list model.columns as column><if test="${column.realName} !=null">${column.columnName}=${"#"}{${column.realName}},</if></#list></set> where id=${"#"}{id}
    </update>
     <select id="findById" resultType="${model.entityPackageName}.${model.className}" parameterType="${model.entityPackageName}.${model.className}">
        SELECT <include refid="${model.className}Columns"/> FROM ${model.tableName} a  where a.id=${"#"}{id}
    </select>
    <delete id="deleteById">
        delete from ${model.tableName}  where id=${"#"}{id}
    </delete>
    
</mapper>
</#if>