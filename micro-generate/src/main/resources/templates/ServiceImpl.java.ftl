<#if model??>
package ${model.servicePackageName}.impl;

import org.springframework.stereotype.Service;
import com.micro.cloud.frame.commons.base.service.impl.BaseServiceImpl;
import ${model.entityPackageName}.${model.className};
import ${model.servicePackageName}.I${model.className}Service;

/**
 * service层实现
 * 
 * @Description
 * @author gsh
 * @date ${model.createDate}
 */
@Service
public class ${model.className}ServiceImpl extends BaseServiceImpl<${model.className}>
        implements I${model.className}Service {

}
</#if>