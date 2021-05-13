<head>
    <#include "../fragments/bootsrap-links.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Request Approval form</title>
</head>


<body>
<#include "../fragments/nav-bar-admin.ftlh" />
<br>

<div class="container">
    <div class="row">
        <h2 class="text-danger"><@spring.message "applicant.admission.requests_to"/> <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                ${request.faculty.nameUk}
            <#else >
                ${request.faculty.nameEn}
            </#if> </h2>


        <table class="table table-bordered success">
            <thead>
            <tr>
                <th><@spring.message "profile.edit.first_name"/></th>
                <td>${request.applicant.applicantProfile.firstName}</td>
            </tr>
            <tr>
                <th><@spring.message "profile.edit.last_name"/></th>
                <td>${request.applicant.applicantProfile.lastName}</td>
            </tr>
            <tr>
                <th ><@spring.message "profile.edit.email"/></th>
                <td>${request.applicant.applicantProfile.email}</td>
            </tr>
            <tr>
                <th ><@spring.message "profile.edit.address"/></th>
                <td>${request.applicant.applicantProfile.address}</td>
            </tr>

            <tr>
                <th ><@spring.message "profile.edit.city"/></th>
                <td>${request.applicant.applicantProfile.city}</td>
            </tr>
            <tr>
                <th ><@spring.message "profile.edit.region"/></th>
                <td>${request.applicant.applicantProfile.region}</td>
            </tr>
            <tr>
                <th ><@spring.message "profile.edit.school"/></th>
                <td>${request.applicant.applicantProfile.school}</td>
            </tr>
            <tr>
                <th ><@spring.message "profile.edit.phoneNumber"/></th>
                <td>${request.applicant.applicantProfile.phoneNumber}</td>
            </tr>

            <tr>
                <th ><@spring.message "applicant.admission.requests.grade.for"/>
                    <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                        ${request.faculty.requiredSubject1Uk}
                    <#else >
                        ${request.faculty.requiredSubject1En}
                    </#if>
                </th>
                <td>${request.getRequiredSubject1Grade()}</td>
            </tr>
            <tr>
                <th ><@spring.message "applicant.admission.requests.grade.for"/>    <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                        ${request.faculty.requiredSubject2Uk}
                    <#else >
                        ${request.faculty.requiredSubject2En}
                    </#if></th>
                <td>${request.getRequiredSubject2Grade()}</td>
            </tr>
            <tr>
                <th ><@spring.message "applicant.admission.requests.grade.for"/><#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                        ${request.faculty.requiredSubject3Uk}
                    <#else >
                        ${request.faculty.requiredSubject3En}
                    </#if>
                </th>
                <td>${request.getRequiredSubject3Grade()}</td>
            </tr>

            </thead>

        </table>

        <#if request.applicant.applicantProfile.getFileName()??>
                <div>
                    <img class="img-fluid" src="/img/${request.applicant.applicantProfile.fileName}">
                </div>
        </#if>

<br>
        <div class="container h-100">
            <br>
            <div class="row h-100 justify-content-center align-items-center">

                <form action="/admin/request_update" method="POST">
                    <input type="hidden" id="id" name="id" value="${request.getId()}">
                    <input type="hidden" id="facultyId" name="facultyId" value="${request.faculty.id}">
                    <input type="hidden" id="admissionRequestStatus" name="admissionRequestStatus" value="APPROVED">
                    <button class="btn btn-primary mr-2" type="submit"><@spring.message "admin.check_request.approve"/></button>
                </form>
                <form action="/admin/request_update" method="POST">
                    <input type="hidden" id="id" name="id" value="${request.getId()}">
                    <input type="hidden" id="facultyId" name="facultyId" value="${request.faculty.id}">
                    <input type="hidden" id="admissionRequestStatus" name="admissionRequestStatus" value="REJECTED">
                    <button class="btn btn-danger mr-2" type="submit"><@spring.message "admin.check_request.reject"/></button>
                </form>
                <form action="/admin/requests_of_faculty/${request.faculty.getId()}" method="get">
                    <button class="btn btn-warning mr-2" type="submit"> <@spring.message "admin.check_request.back"/></button>
                </form>

            </div>
        </div>
    </div>
</div>
<br>
<br>
</body>