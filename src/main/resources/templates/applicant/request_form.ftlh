<#include "../fragments/security.ftlh"/>

<#import "/spring.ftl" as spring/>
<#import "../fragments/error.ftl" as error/>
<!doctype html>
<html lang="en">
<head>
    <#include "../fragments/bootsrap-links.ftl" />

    <title>Admission request form</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <#if know>
        <a class="navbar-brand" href="/faculties"><@spring.message "navbar.Admission_Board_App"/></a>
    <#else >
        <a class="navbar-brand" href="/"><@spring.message "navbar.Admission_Board_App"/></a>
    </#if>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item dropdown">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                            data-target="#navbarDropdown"><@spring.message "navbar.Change_Language"/>
                            <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdown">
                            <a class="dropdown-item"
                               href="/applicant/submit_request_form?lang=en <#if faculty??>&id=${faculty.id}&admissionOpen=${faculty.admissionOpen?string}</#if>"><@spring.message "navbar.English"/></a>
                            <a class="dropdown-item"
                               href="/applicant/submit_request_form?lang=uk<#if faculty??>&id=${faculty.id}&admissionOpen=${faculty.admissionOpen?string}</#if>"><@spring.message "navbar.Ukrainian"/></a>
                        </div>

                    </li>
                </ul>
            </li>


            <#if isAdmin>
                <li class="nav-item">
                    <form class="form-inline ml-2 mr-2 my-2 my-lg-0" action="/admin/workspace" method="get">
                        <button class="btn btn-primary"
                                type="submit"><@spring.message "navbar.Admin_workspace"/></button>
                    </form>
                </li>
            </#if>
        </ul>


        <#if know>

            <div class="nav-item dropdown">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="" class="dropdown-toggle" data-target="#navbarDropdownPr"
                                            data-toggle="dropdown"><@spring.message "navbar.Account"/> ${name}
                            <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdownPr">

                            <form action="/api/applicant/profile" method="get">
                                <button class="dropdown-item"
                                        type="submit"><@spring.message "navbar.my_profile"/></button>
                            </form>
                            <form action="/applicant/applicant_requests" method="get">
                                <button class="dropdown-item"
                                        type="submit"><@spring.message "navbar.my_requests"/></button>
                            </form>
                            <form action="/auth/logout" method="post">
                                <button class="dropdown-item" type="submit"><@spring.message "navbar.logout"/></button>
                            </form>
                        </div>

                        </form>
                    </li>
                </ul>
            </div>

        <#else>
            <div>

                <form class="form-inline my-2 mr-2 my-lg-0">
                    <a class="btn btn-primary my-2 my-sm-0" href="/auth/login"
                       role="button"><@spring.message "navbar.login"/></a>
                </form>
            </div>
            <div>

                <form class="form-inline my-2 my-lg-0">
                    <a class="btn btn-primary my-2 my-sm-0" href="/registration"
                       role="button"><@spring.message "navbar.registration"/></a>
                </form>

            </div>

        </#if>
    </div>
</nav>

<br>
<main class="login-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <#if errorMessage??>
                    <div class="alert alert-primary" role="alert">
                        ${errorMessage}

                        <form method="get" action="/faculties">
                            <button type="submit" class="btn btn-primary">
                                <@spring.message "admin.check_request.back"/>
                            </button>
                        </form>
                    </div>
                </#if>
                <div class="card">
                    <div class="card-header"><@spring.message "admin.requests_of_faculty.to_the_faculty"/>    <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                            ${faculty.nameUk}
                        <#else >
                            ${faculty.nameEn}
                        </#if></div>
                    <div class="card-body">
                        <form action="/applicant/submit_request" method="post" >

                            <div class="form-group row">
                                <label for="first_name"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "profile.edit.first_name"/></label>
                                <div class="col-md-6">
                                    ${applicant.applicantProfile.firstName}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="last_name"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "profile.edit.last_name"/> </label>
                                <div class="col-md-6">
                                    ${applicant.applicantProfile.lastName}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "profile.edit.email"/></label>
                                <div class="col-md-6">
                                    ${applicant.applicantProfile.email}
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="address"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "profile.edit.address"/> </label>
                                <div class="col-md-6">
                                    ${applicant.applicantProfile.address}
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="phone_number"
                                       class="col-md-4 col-form-label text-md-right"> <@spring.message "profile.edit.phoneNumber"/></label>
                                <div class="col-md-6">
                                    ${applicant.applicantProfile.phoneNumber}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject1Grade"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "applicant.admission.requests.grade.for"/>
                                    <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                                        ${faculty.getRequiredSubject1Uk()}
                                    <#else >
                                        ${faculty.getRequiredSubject1En()}
                                    </#if>
                                </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject1Grade"
                                           class="form-control ${(requiredSubject1GradeError??)?string('is-invalid', '')}"
                                           name="requiredSubject1Grade"
                                           value="<#if requiredSubject1Grade??>${requiredSubject1Grade}</#if>"
                                           autofocus/>
                                    <#if requiredSubject1GradeError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject1GradeError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject2Grade"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "applicant.admission.requests.grade.for"/>
                                    <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                                        ${faculty.getRequiredSubject2Uk()}
                                    <#else >
                                        ${faculty.getRequiredSubject2En()}
                                    </#if>
                                </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject2Grade"
                                           class="form-control ${(requiredSubject2GradeError??)?string('is-invalid', '')}"
                                           name="requiredSubject2Grade"
                                           value="<#if requiredSubject2Grade??>${requiredSubject2Grade}</#if>"/>
                                    <#if requiredSubject2GradeError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject2GradeError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject3Grade"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "applicant.admission.requests.grade.for"/>
                                    <#if springMacroRequestContext.getLocale().toString()?contains("uk")>
                                        ${faculty.getRequiredSubject3Uk()}
                                    <#else >
                                        ${faculty.getRequiredSubject3En()}
                                    </#if>
                                </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject3Grade"
                                           class="form-control ${(requiredSubject3GradeError??)?string('is-invalid', '')}"
                                           name="requiredSubject3Grade"
                                           value="<#if requiredSubject3Grade??>${requiredSubject3Grade}</#if>"/>
                                    <#if requiredSubject3GradeError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject3GradeError}
                                        </div>
                                    </#if>
                                </div>
                            </div>



                            <input type="hidden" id="applicantId" name="applicantId" value="${applicant.id}"/>
                            <input type="hidden" id="facultyId" name="facultyId" value="${faculty.id}"/>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <@spring.message "faculty.submit_request"/>
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
<br>
<br>
</body>
</html>