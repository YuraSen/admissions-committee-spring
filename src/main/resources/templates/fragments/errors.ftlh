<#macro errors>
    <#if errorMessages??>
        <#list errorMessages as errorMessage>
            <div class="alert alert-primary" role="alert">

                ${errorMessage.getDefaultMessage()}

            </div>
        </#list>
    <#else>
    </#if>
</#macro>