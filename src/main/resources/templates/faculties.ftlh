<#import "fragments/pager.ftl" as pager/>
<#import "fragments/items-per-page.ftl" as items_per_page/>
<#import "fragments/error.ftl" as error>
<head>

    <link rel="stylesheet" href="css/stylesheet.css">

    <#include "fragments/bootsrap-links.ftl"/>
</head>


<body>
<#include "fragments/nav-bar.ftl" />
<br>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet'
      type='text/css'>

<div class="container">
    <div class="row">

        <div class="row">
            <div class="col col-xs-6">
                <h3 class="panel-title"><@spring.message "admin.faculties"/></h3>
            </div>
            <@error.error/>

        </div>
        <div class="panel-body">
            <@items_per_page.items url page/>
            <table class="table table-bordered">
                <thead>
                <tr>


                    <th>
                        <@spring.message "faculty.name"/>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=nameEn,ASC">&#8593</a>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=nameEn,DESC">&#8595</a>
                    </th>
                    <th>  <@spring.message "faculty.description"/></th>
                    <th>  <@spring.message "faculty.budget_capacity"/>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=budgetCapacity,ASC">&#8593</a>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=budgetCapacity,DESC">&#8595</a>
                    </th>
                    <th>  <@spring.message "faculty.total_capacity"/>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=totalCapacity,ASC">&#8593</a>
                        <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=totalCapacity,DESC">&#8595</a>
                    </th>
                    <th>  <@spring.message "faculty.submit_request"/></th>

                </tr>
                </thead>
                <tbody>

                <#list page.content as faculty>
                    <tr>
                        <td> <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                                ${faculty.getNameUk()}
                            <#else >
                                ${faculty.getNameEn()}
                            </#if>
                        </td>
                        <td> <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                                ${faculty.getDescriptionUk()}
                            <#else >
                                ${faculty.getDescriptionEn()}
                            </#if>

                        </td>
                        <td>${faculty.getBudgetCapacity()}</td>
                        <td>${faculty.getTotalCapacity()}</td>
                        <td>
                            <#if isAdmin>
                            <#else >
                                <#if faculty.admissionOpen>
                                    <form method="Get" action="/applicant/submit_request_form">
                                        <input type="hidden" name="id" value="${faculty.id}">
                                        <input type="hidden" name="admissionOpen"
                                               value="${faculty.admissionOpen?string}">
                                        <button type="submit" class="btn btn-success btn-sm">  <@spring.message "faculty.submit_request"/>
                                        </button>
                                    </form>
                                </#if>
                            </#if>

                        </td>

                    </tr>
                </#list>
                </tbody>
            </table>
            <@pager.pager url page/>
        </div>
    </div>
</div>
<br>
<br>

</body>