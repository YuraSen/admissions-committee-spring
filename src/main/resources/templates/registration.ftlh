<#import "fragments/error.ftl" as error/>
<!doctype html>
<html lang="en">
<head>
    <#include "fragments/bootsrap-links.ftl"/>

    <title>Login</title>
</head>
<body>

<#include "fragments/nav-bar-log-reg.ftl" />
<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><@spring.message "login.registration"/></div>
                    <div class="card-body">
                        <@error.error/>
                        <form action="/api/applicant/registration" method="POST" enctype="multipart/form-data">
                            <div class="form-group row">
                                <label for="username" class="col-md-4 col-form-label text-md-right"><@spring.message "profile.edit.username"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                                           value="<#if applicantDTO??>${applicantDTO.username}</#if>" name="username"/>
                                    <#if usernameError??>
                                        <div class="invalid-feedback">
                                            ${usernameError}
                                        </div>
                                    </#if>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><@spring.message "login.password"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password"
                                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                                           name="password"/>

                                    <#if passwordError??>
                                        <div class="invalid-feedback">
                                            ${passwordError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="firstName"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.first_name"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="firstName"
                                           class="form-control ${(firstNameError??)?string('is-invalid', '')}"
                                           name="firstName"
                                           value="<#if applicantProfileDTO??>${applicantProfileDTO.firstName}</#if>"/>
                                    <#if firstNameError??>
                                        <div class="invalid-feedback">
                                            ${firstNameError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="lastName"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.last_name"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="lastName"
                                           class="form-control ${(lastNameError??)?string('is-invalid', '')}"
                                           name="lastName"
                                           value="<#if applicantProfileDTO??> ${applicantProfileDTO.lastName}</#if>">

                                    <#if lastNameError??>
                                        <div class="invalid-feedback">
                                            ${lastNameError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.email"/> </label>
                                <div class="col-md-6">
                                    <input type="email" id="email_address"
                                           class="form-control ${(emailError??)?string('is-invalid', '')}" name="email"
                                           value="<#if applicantProfileDTO??> ${applicantProfileDTO.email}</#if>"/>

                                    <#if emailError??>
                                        <div class="invalid-feedback">
                                            ${emailError}
                                        </div>
                                    </#if>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="address"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.address"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="address"
                                           class="form-control ${(addressError??)?string('is-invalid', '')}"
                                           name="address"
                                           value="<#if applicantProfileDTO??> ${applicantProfileDTO.address}</#if>"/>

                                    <#if addressError??>
                                        <div class="invalid-feedback">
                                            ${addressError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="city"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.city"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="city"
                                           class="form-control ${(cityError??)?string('is-invalid', '')}" name="city"
                                           value="<#if applicantProfileDTO??> ${applicantProfileDTO.city}</#if>"/>

                                    <#if cityError??>
                                        <div class="invalid-feedback">
                                            ${cityError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="region"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.region"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="region"
                                           class="form-control ${(regionError??)?string('is-invalid', '')}"
                                           name="region"
                                           value="<#if applicantProfileDTO??> ${applicantProfileDTO.region}</#if>">

                                    <#if regionError??>
                                        <div class="invalid-feedback">
                                            ${regionError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="school"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.school"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="school"
                                           class="form-control ${(schoolError??)?string('is-invalid', '')}"
                                           name="school"
                                           value="<#if applicantProfileDTO??> ${applicantProfileDTO.school}</#if>">

                                    <#if schoolError??>
                                        <div class="invalid-feedback">
                                            ${schoolError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="phoneNumber"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "reg.detail.phoneNumber"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="phoneNumber"
                                           class="form-control ${(phoneNumberError??)?string('is-invalid', '')}"
                                           name="phoneNumber"
                                           value="<#if applicantProfileDTO??> ${applicantProfileDTO.phoneNumber}</#if>">

                                    <#if phoneNumberError??>
                                        <div class="invalid-feedback">
                                            ${phoneNumberError}
                                        </div>
                                    </#if>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="file"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "file.certificate"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="file" id="file"
                                           class="form-control"
                                           name="file"
                                    />
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <@spring.message "login.registration"/>
                                </button>
                                <a href="/" class="btn btn-link">
                                    <@spring.message "admin.check_request.back"/>
                                </a>
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

