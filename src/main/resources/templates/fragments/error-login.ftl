<#macro error_login>
    <#if errorMessage??>
        <div class="alert alert-primary" role="alert">
            ${errorMessage}
        </div>
    <#else>
    </#if>
</#macro>