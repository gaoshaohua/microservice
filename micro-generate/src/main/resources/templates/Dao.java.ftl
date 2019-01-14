<#if model??>
package ${model.daoPackageName};

import com.micro.cloud.frame.commons.base.dao.IBaseDao;
import ${model.entityPackageName}.${model.className};

/**
 * @desc dao
 * @author gsh
 * @version 1.0
 * @date ${model.createDate}
 */
public interface ${model.className}Dao extends BaseDao<${model.className}> {

}
</#if>