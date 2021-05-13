<#import "../fragments/error.ftl" as error/>
<!doctype html>
<html lang="en">
<head>
    <#include "../fragments/bootsrap-links.ftl"/>

    <title>Applicant Edit</title>
</head>
<body>

<#include "../fragments/nav-bar.ftl" />
<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><@spring.message "applicant.edit"/></div>
                    <div class="card-body">
                        <@error.error/>
                        <form action="/admin/applicant/edit/${applicant.getId()}" method="POST">
                            <div class="form-group row">
                                <label for="totalCapacity"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "profile.edit.username"/>
                                    :</label>
                                <div class="col-md-6 my-2">
                                    ${applicant.username}
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="requiredSubject1"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "profile.edit.email"/>
                                    : </label>
                                <div class="col-md-6 my-2">
                                    ${applicant.applicantProfile.email}
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="requiredSubject2"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "applicant.role"/>
                                    : </label>
                                <div class="form-group">
                                    <select class="custom-select" name="role" required>
                                        <option selected="${applicant.getRole().name()}" value="USER">USER</option>
                                        <option value="ADMIN">ADMIN</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="applicantStatus"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "applicant.admission.requests.status"/>
                                    : </label>
                                <div class="form-group">
                                    <select class="custom-select" name="applicantStatus" required>
                                        <option value="ACTIVE">ACTIVE</option>
                                        <option value="BLOCKED">BLOCKED</option>
                                    </select>
                                </div>
                            </div>


                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <@spring.message "profile.edit.save"/>
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

