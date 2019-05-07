package ${package.Controller};


    import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import ${package.Service}.${table.serviceName};
    import ${package.Entity}.${entity};
    import javax.annotation.Resource;
    /**
    * <p>
    * ${table.comment!} 前端控制器
    * </p>
    *
    * @author ${author}
    * @since ${date}
    */
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
    @RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {

        private Logger logger = LoggerFactory.getLogger(${table.controllerName}.class);

        @Resource(name = "${table.serviceImplName  ? uncap_first}")
        private ${table.serviceName} ${table.serviceName ? uncap_first};

        @RequestMapping("index")
        public ${entity} index() {
            return ${table.serviceName ? uncap_first}.getById("xxxx");
        }

    </#if>

    }
</#if>
