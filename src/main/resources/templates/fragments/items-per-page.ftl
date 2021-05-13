<#import "/spring.ftl" as spring/>
<#macro items url page>
    <div class="mt-3">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><@spring.message "pagination.items_on_a_page"/></a>
            </li>
            <#list  [5,10,20] as el>
                <#if el ==page.getSize()>
                    <li class="page-item active">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">${el}</a>
                    </li>
                <#else >
                    <li class="page-item">
                        <a class="page-link" href="${url}?page=0&size=${el}" tabindex="-1"
                           aria-disabled="true">${el}</a>
                    </li>
                </#if>
            </#list>
        </ul>
    </div>
</#macro>