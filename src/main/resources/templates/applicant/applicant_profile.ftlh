
<head>
    <#include "../fragments/bootsrap-links.ftl"/>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Applicant Profile</title>
</head>


<body>
<#include "../fragments/nav-bar.ftl" />
<br>

<div class="container">
    <div class="row">
        <h2 class="text-danger"><@spring.message "navbar.my_profile"/></h2>
        <form class="form-inline ml-auto my-2 my-lg-0"
                action="/api/applicant/edit/${applicant.getId()}" method="get">
            <button class="btn btn-primary " type="submit"><@spring.message "profile.edit.edit_profile"/></button>
        </form>

        <table class="table table-bordered success">
            <thead>
            <tr>
                <th><@spring.message "profile.edit.username"/></th>
                <td>${applicant.username}</td>
            </tr>
            <tr>
                <th class="info"><@spring.message "profile.edit.email"/></th>
                <td>${applicant.applicantProfile.email}</td>
            </tr>
            <tr>
                <th><@spring.message "profile.edit.first_name"/></th>
                <td>${applicant.applicantProfile.firstName}</td>
            </tr>
            <tr>
                <th class="info"><@spring.message "profile.edit.last_name"/></th>
                <td>${applicant.applicantProfile.lastName}</td>
            </tr>

            <tr>
                <th class="info"><@spring.message "profile.edit.address"/></th>
                <td>${applicant.applicantProfile.address}</td>
            </tr>
            <tr>
                <th class="info"><@spring.message "profile.edit.city"/></th>
                <td>${applicant.applicantProfile.city}</td>
            </tr>

            <tr>
                <th class="info"><@spring.message "profile.edit.region"/></th>
                <td>${applicant.applicantProfile.region}</td>
            </tr>
            <tr>
                <th class="info"><@spring.message "profile.edit.region"/></th>
                <td>${applicant.applicantProfile.school}</td>
            </tr>
            <tr>
                <th class="info"><@spring.message "profile.edit.phoneNumber"/></th>
                <td>${applicant.applicantProfile.phoneNumber}</td>
            </tr>

            </thead>

        </table>
        <#if applicant.applicantProfile.getFileName()??>
            <div>
                <img class="img-fluid" src="/img/${applicant.applicantProfile.fileName}">
            </div>
        </#if>
    </div>
</div>
<br>
<br>
</body>