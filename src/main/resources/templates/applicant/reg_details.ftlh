<#import "../fragments/error.ftl" as error/>
<#import "/spring.ftl" as spring/>
<!doctype html>
<html lang="en">
<head>
    <#include "../fragments/bootsrap-links.ftl"/>

    <title>Registration details</title>
</head>
<body>

<#include "../fragments/nav-bar-log-reg.ftl" />
<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><@spring.message "reg.detail.please_fill_your_profile"/></div>
                    <div class="card-body">
                        <@error.error/>
                        <form action="/api/applicant/registration/profile" method="POST">
                            <input type="hidden" id="applicant_username" class="form-control" name="applicant_username"
                                   value="${applicant_username}">
                            <input type="hidden" id="applicant_password" class="form-control" name="applicant_password"
                                   value="${applicant_password}">
                            <div class="form-group row">
                                <label for="firstName" class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.first_name"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="firstName" class="form-control" name="firstName"
                                           required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="lastName" class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.last_name"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="lastName" class="form-control" name="lastName" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.email"/> </label>
                                <div class="col-md-6">
                                    <input type="email" id="email_address" class="form-control" name="email" required>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="address" class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.address"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="address" class="form-control" name="address" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="city" class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.city"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="city" class="form-control" name="city" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="region" class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.region"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="region" class="form-control" name="region" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="school" class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.school"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="school" class="form-control" name="school" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="phoneNumber" class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.phoneNumber"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="phoneNumber" class="form-control" name="phoneNumber" required>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Register
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

