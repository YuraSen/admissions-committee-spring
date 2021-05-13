<head>
    <#import "../fragments/error.ftl" as error>
    <#import "../fragments/pager.ftl" as pager/>
    <#import "../fragments/items-per-page.ftl" as items_per_page/>
    <#include "../fragments/bootsrap-links.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">

</head>


<body>
<#include "../fragments/nav-bar-admin.ftlh" />
<br>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet'
      type='text/css'>

<div class="container-sm ml-4">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-1">
                            <h3 class="panel-title"><@spring.message "admin.faculties"/></h3>
                        </div>
                        <div class=" pull-right">
                            <form action="/admin/faculty/add" method="get">
                                <button type="submit"
                                        class="btn btn-primary btn-create"><@spring.message "admin.create_new_faculty"/>
                                </button>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="float-left">
                        <@items_per_page.items url page/>
                    </div>
                </div>
                <div class="panel-body  row align-items-center">
                    <@error.error/>
                    <table class="table-responsive-md table-striped table-bordered ">
                        <thead >
                        <tr>
                            <th class="text-sm-center p-2"><@spring.message "admin.name"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.new_requests"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.approved_requests"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.rejected_requests"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.budget_capacity"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.total_capacity"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.work_with_requests"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.block_unblock_registration"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.edit_faculty"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.delete_faculty"/></th>
                            <th class="text-sm-center p-2"><@spring.message "admin.statement"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.content as faculty>
                            <tr  >
                                <td class="text-sm-center p-2">
                                    <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                                        ${faculty.getNameUk()}
                                    <#else >
                                        ${faculty.getNameEn()}
                                    </#if>
                                </td>
                                <td class="text-sm-center p-2">${faculty.numberOfRequestsNew()}</td>
                                <td class="text-sm-center m-2">${faculty.numberOfRequestsApproved()}</td>
                                <td class="text-sm-center m-2">${faculty.numberOfRequestsRejected()}</td>
                                <td class="text-sm-center m-2">${faculty.budgetCapacity}</td>
                                <td class="text-sm-center m-2">${faculty.totalCapacity}</td>
                                <td class="col-lg-11 m-auto col-centered">
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/admin/requests_of_faculty/${faculty.getId()}" method="get">
                                        <button class="btn btn-primary btn-xs"
                                                type="submit"><@spring.message "admin.requests"/></button>
                                    </form>
                                </td>
                                <td  class="col-lg-11 m-auto  col-centered">
                                    <#if faculty.admissionOpen>
                                        <form class="form-inline my-2 my-lg-0"
                                              action="/admin/block_reg/${faculty.getId()}" method="post">
                                            <input type="hidden" name="id" value="${faculty.getId()}">
                                            <input type="hidden" name="admissionOpen" value="${false?string}">
                                            <button class="btn btn-warning btn-xs m-2"
                                                    type="submit"><@spring.message "admin.block"/></button>
                                        </form>
                                    <#else>
                                        <form class="form-inline m-auto my-lg-0"
                                              action="/admin/block_reg/${faculty.getId()}" method="post">
                                            <input type="hidden" name="id" value="${faculty.getId()}">
                                            <input type="hidden" name="admissionOpen" value="${true?string}">
                                            <button class="btn btn-success btn-xs"
                                                    type="submit"><@spring.message "admin.unblock"/></button>
                                        </form>
                                    </#if>
                                </td>

                                <td  class="col-lg-11  m-auto col-centered" >
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/admin/faculty/edit/${faculty.getId()}" method="get">
                                        <button class="btn btn-primary btn-xs"
                                                type="submit"><@spring.message "admin.edit_faculty"/></button>
                                        <input type="hidden" name="id" value="${faculty.getId()}">
                                    </form>
                                </td>
                                <td class="col-lg-11  m-auto col-centered">
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/admin/faculties/delete/${faculty.getId()}" method="post">
                                        <button class="btn btn-danger btn-xs"
                                                type="submit"><@spring.message "admin.delete_faculty"/></button>
                                    </form>
                                </td>
                                <td class="col-lg-11 col-centered">
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/admin/statement/faculty/${faculty.id}" method="get">
                                        <button class="btn btn-primary btn-xs"
                                                type="submit"><@spring.message "admin.statement"/></button>
                                    </form>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                    <@pager.pager url page/>
                    <br>
                </div>
            </div>

        </div>
    </div>
</div>

<br>
<br>
</body>
