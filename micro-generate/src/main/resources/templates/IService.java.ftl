<#if model??>
package ${model.servicePackageName};

import com.micro.cloud.frame.commons.base.service.IBaseService;
import ${model.entityPackageName}.${model.className};

/**
 * ${model.className}service层接口
 * 
 * @Description
 * @author gsh
 * @date ${model.createDate}
 */
public interface I${model.className}Service extends IBaseService<${model.className}> {

}
</#if>