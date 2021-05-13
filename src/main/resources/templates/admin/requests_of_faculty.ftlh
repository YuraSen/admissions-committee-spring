<head>
    <#import "../fragments/pager.ftl" as pager/>
    <#import "../fragments/items-per-page.ftl" as items_per_page/>
    <#include "../fragments/bootsrap-links.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">

</head>


<body>
<#include "../fragments/nav-bar-admin.ftlh" />
<br>


<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title"><@spring.message "admin.requests"/> </h3>
                        </div>

                    </div>
                </div>
                <div class="panel-body">
                    <@items_per_page.items url page/>
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>

                            <th><@spring.message "admin.requests_of_faculty.id"/></th>
                            <th><@spring.message "admin.requests_of_faculty.from"/>
                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=applicant,ASC">&#8593</a>
                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=applicant,DESC">&#8595</a>
                            </th>
                            <th><@spring.message "admin.check_request.request_to_the"/></th>
                            <th><@spring.message "admin.requests_of_faculty.status"/>
                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=admissionRequestStatus,ASC">&#8593</a>
                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=admissionRequestStatus,DESC">&#8595</a>
                            </th>
                            <th><@spring.message "admin.requests_of_faculty.action"/></th>


                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as request>
                            <tr>

                                <td>${request.getId()}</td>
                                <td>${request.applicant.getUsername()}</td>
                                <td>${request.faculty.getNameEn()}</td>
                                <td>${request.admissionRequestStatus.name()}</td>
                                <td>
                                    <form
                                            action="/admin/requests_of_faculty/request/${request.getId()}" method="get">
                                        <button class="btn btn-primary" type="submit"><@spring.message "admin.requests_of_faculty.check_request"/></button>
                                    </form>
                                </td>
                        <#else >
                            No requests for the faculty
                            </tr>

                        </#list>

                        </tbody>
                    </table>
                    <@pager.pager url page/>

                </div>
            </div>

        </div>
    </div>
</div>
<br>
<br>
</body>