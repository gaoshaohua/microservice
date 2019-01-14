<#if model??>
package ${model.controllerPackageName};

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.micro.cloud.frame.commons.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import ${model.entityPackageName}.${model.className};
import ${model.servicePackageName}.I${model.className}Service;

/**
 * @desc ${model.className}控制层
 * @author gsh
 * @version 1.0
 * @date ${model.createDate}
 */
@Controller
@RequestMapping("/${model.tableName}")
public class ${model.className}Controller extends BaseController {

    @Autowired
    private I${model.className}Service ${model.className?uncap_first}Service;
}
</#if>