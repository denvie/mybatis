/*
* ${cfg.copyright}
*/

package ${package.Controller};

<#if swagger2>
import io.swagger.annotations.Api;
</#if>
<#if entityLombokModel>
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.comment!}前端控制器。
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if entityLombokModel>
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
</#if>
<#if swagger2>
@Api(value="${table.comment!}管理")
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
}
</#if>
