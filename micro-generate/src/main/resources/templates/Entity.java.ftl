<#if model??>
package ${model.entityPackageName};


/**
 * @desc 为了薪资破万，还是睡一会儿吧...!
 * @author 龚亮
 * @version 1.0
 * @date ${model.createDate}
 */
public class ${model.className}{

<#list model.columns as column>
	 /**
	  * desc: ${column.columnDesc}
	  */
	  private ${column.columnType} ${column.realName};
     
</#list>

<#list model.columns as column>
	public ${column.columnType} get${column.realName?cap_first}(){
        return ${column.realName};
    }

    public void set${column.realName?cap_first}(${column.columnType} ${column.realName}){
        this.${column.realName} = ${column.realName};
    }
</#list>
}
</#if>