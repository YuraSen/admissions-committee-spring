<head>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Statement</title>
    <#include "../fragments/bootsrap-links.ftl"/>
</head>


<body>
<#include "../fragments/nav-bar-admin.ftlh" />
<br>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel-body">
                <#if faculty_statement?size!=0>
                <td>

                    <form class="form-inline my-2 my-lg-0"
                          action="/admin/statement/finalize" method="Post">
                        <input type="hidden" name="id" id="id" value="${faculty_id}">
                        <input type="hidden" name="admissionOpen" id="admissionOpen" value="false">
                        <button class="btn btn-danger"
                                type="submit"><@spring.message "statement.finalize_statement"/></button>
                    </form>

                </td>
                <br>
                <table class="table table-striped table-bordered table-list">
                    <thead>
                    <tr>

                        <th style="width: 14.28%"><@spring.message "faculty.name"/></th>
                        <th style="width: 14.28%"><@spring.message "statement.applicant_name"/></th>
                        <th style="width: 14.28%"><@spring.message "statement.applicant_mail"/></th>
                        <th style="width: 14.28%"><@spring.message "statement.applicant_number"/></th>
                        <th style="width: 14.28%"><@spring.message "statement.applicant_grade"/></th>
                        <th style="width: 14.28%"><@spring.message "statement.budget_contract"/></th>

                    </tr>
                    </thead>
                    <tbody>
                    </#if>
                    <#list   faculty_statement as statement>
                        <tr>

                            <td style="width: 14.28%">    <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                                    ${statement.faculty.getNameUk()}
                                <#else >
                                    ${statement.faculty.getNameEn()}
                                </#if></td>
                            <td style="width: 14.28%">${statement.applicant.applicantProfile.firstName} ${statement.applicant.applicantProfile.lastName} </td>
                            <td style="width: 14.28%">${statement.applicant.applicantProfile.email}</td>
                            <td style="width: 14.28%">${statement.applicant.applicantProfile.phoneNumber}</td>
                            <td style="width: 14.28%">${statement.getSumOfGrades()}</td>
                            <td style="width: 14.28%">
                                <#assign index=statement_index
                                capacity =statement.faculty.getBudgetCapacity()
                                />
                                <#if ( index lt  capacity) >
                                    <@spring.message "statement.budget"/>
                                <#else>
                                    <@spring.message "statement.contract"/>
                                </#if>

                            </td>


                        </tr>
                    <#else >
                        <h2><@spring.message "statement.empty"/></h2>
                    </#list>
                    </tbody>

                </table>


            </div>

        </div>
    </div>
</div>
<br>
<br>

</body>
