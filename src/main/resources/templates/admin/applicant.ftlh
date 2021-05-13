<#import "../fragments/pager.ftl" as pager/>
<#import "../fragments/items-per-page.ftl" as items_per_page/>
<head>
    <#include "../fragments/bootsrap-links.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
<#include "../fragments/nav-bar-admin.ftlh" />
<br>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet'
      type='text/css'>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title"><@spring.message "navbar.Admin_workspace_applicant"/></h3>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <@items_per_page.items url page/>
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>
                            <th><@spring.message "admin.requests_of_faculty.id"/></th>
                            <th><@spring.message "profile.edit.username"/></th>
                            <th><@spring.message "profile.edit.email"/></th>
                            <th><@spring.message "applicant.role"/></th>
                            <th><@spring.message "applicant.admission.requests.status"/></th>
                            <th><@spring.message "applicant.edit"/></th>
                            <th><@spring.message "admin.delete_faculty"/></th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list page.content as applicant>
                            <tr>

                                <td>${applicant.getId()}</td>
                                <td>${applicant.getUsername()}</td>
                                <td>${applicant.applicantProfile.email}</td>
                                <td>${applicant.getRole().name()}</td>
                                <td>${applicant.getApplicantStatus().name()}</td>

                                <td align="center">
                                    <form action="/admin/applicant/edit/${applicant.getId()}" method="get">
                                        <button class="btn btn-primary" type="submit"> <@spring.message "applicant.edit"/></button>
                                    </form>

                                </td>

                                <td align="center">
                                    <form action="/admin/applicant/delete/${applicant.getId()}" method="post">
                                        <button class="btn btn-danger" type="submit"><@spring.message "admin.delete_faculty"/></button>
                                    </form>

                                </td>
                            </tr>
                        <#else >
                            <h2>No Applicant Available</h2>
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