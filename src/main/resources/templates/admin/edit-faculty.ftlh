<#import "../fragments/error.ftl" as error/>
<!doctype html>
<html lang="en">
<head>
    <#include "../fragments/bootsrap-links.ftl"/>

    <title>Faculty Edit</title>
</head>
<body>

<#include "../fragments/nav-bar.ftl" />
<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><@spring.message "admin.edit_faculty"/></div>
                    <div class="card-body">
                        <#if validError??>
                        <div class="alert alert-primary" role="alert">
                            ${validError}
                        </div>
                        <#else >
                        </#if>

                        <form action="/admin/faculty/edit/${faculty.getId()}" method="POST">


                            <div class="form-group row">
                                <label for="nameEn"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.name.en"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="nameEn"
                                           class="form-control ${(nameEnError??)?string('is-invalid', '')}"
                                           name="nameEn"
                                           value="<#if faculty??>${faculty.nameEn}</#if>" autofocus/>
                                    <#if nameEnError??>
                                        <div class="invalid-feedback">
                                            ${nameEnError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="nameUk"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.name.uk"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="nameUk"
                                           class="form-control ${(nameUkError??)?string('is-invalid', '')}"
                                           name="nameUk"
                                           value="<#if faculty??>${faculty.nameUk}</#if>" autofocus>
                                    <#if nameUkError??>
                                        <div class="invalid-feedback">
                                            ${nameUkError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="descriptionEn"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.description.en"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="descriptionEn"
                                           class="form-control ${(descriptionEnError??)?string('is-invalid', '')}"
                                           name="descriptionEn"
                                           value="<#if faculty??>${faculty.descriptionEn}</#if>" autofocus>
                                    <#if descriptionEnError??>
                                        <div class="invalid-feedback">
                                            ${descriptionEnError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="descriptionUk"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.description.uk"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="descriptionUk"
                                           class="form-control ${(descriptionUkError??)?string('is-invalid', '')}"
                                           name="descriptionUk"
                                           value="<#if faculty??>${faculty.descriptionUk}</#if>">
                                    <#if descriptionUkError??>
                                        <div class="invalid-feedback">
                                            ${descriptionUkError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="budgetCapacity"
                                       class="col-md-4 col-form-label text-md-right"> <@spring.message "faculty.budget_capacity"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="budgetCapacity"
                                           class="form-control ${(budgetCapacityError??)?string('is-invalid', '')}"
                                           name="budgetCapacity"
                                           value="<#if faculty.budgetCapacity??>${faculty.budgetCapacity}</#if>"/>

                                    <#if budgetCapacityError??>
                                        <div class="invalid-feedback">
                                            ${budgetCapacityError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="totalCapacity"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.total_capacity"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="totalCapacity"
                                           class="form-control  ${(totalCapacityError??)?string('is-invalid', '')}"
                                           name="totalCapacity"
                                           value="<#if faculty.totalCapacity??>${faculty.totalCapacity}</#if>"/>


                                    <#if totalCapacityError??>
                                        <div class="invalid-feedback">
                                            ${totalCapacityError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="requiredSubject1En"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.required_subject1_en"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject1En"
                                           class="form-control ${(requiredSubject1EnError??)?string('is-invalid', '')}"
                                           name="requiredSubject1En"
                                           value="<#if faculty??>${faculty.requiredSubject1En}</#if>">
                                    <#if requiredSubject1EnError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject1EnError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject1Uk"
                                       class="col-md-4 col-form-label text-md-right"> <@spring.message "faculty.required_subject1_uk"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject1Uk"
                                           class="form-control ${(requiredSubject1UkError??)?string('is-invalid', '')}"
                                           name="requiredSubject1Uk"
                                           value="<#if faculty??>${faculty.requiredSubject1Uk}</#if>"/>
                                    <#if requiredSubject1UkError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject1UkError}
                                        </div>
                                    </#if>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="requiredSubject2En"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.required_subject2_en"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject2En"
                                           class="form-control ${(requiredSubject2EnError??)?string('is-invalid', '')}"
                                           name="requiredSubject2En"
                                           value="<#if faculty??>${faculty.requiredSubject2En}</#if>"/>
                                    <#if requiredSubject2EnError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject3EnError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject2Uk"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.required_subject2_uk"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject2Uk"
                                           class="form-control ${(requiredSubject2UkError??)?string('is-invalid', '')}"
                                           name="requiredSubject2Uk"
                                           value="<#if faculty??>${faculty.requiredSubject2Uk}</#if>"/>
                                    <#if requiredSubject2UkError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject2UkError}
                                        </div>
                                    </#if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="requiredSubject3En"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.required_subject3_en"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject3En"
                                           class="form-control${(requiredSubject3EnError??)?string('is-invalid', '')}"
                                           name="requiredSubject3En"
                                           value="<#if faculty??>${faculty.requiredSubject3En}</#if>"/>
                                    <#if requiredSubject3EnError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject3EnError}
                                        </div>
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject3Uk"
                                       class="col-md-4 col-form-label text-md-right"><@spring.message "faculty.required_subject3_uk"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject3UK"
                                           class="form-control ${(requiredSubject3UkError??)?string('is-invalid', '')}"
                                           name="requiredSubject3Uk"
                                           value="<#if faculty??>${faculty.requiredSubject3Uk}</#if>"/>
                                    <#if requiredSubject3UkError??>
                                        <div class="invalid-feedback">
                                            ${requiredSubject3UkError}
                                        </div>
                                    </#if>
                                </div>
                            </div>


                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <@spring.message "profile.edit.save"/>
                                </button>
                            </div>
                            <input type="hidden" id="admissionOpen" class="form-control"
                                   name="admissionOpen" value="${faculty.admissionOpen?string}">
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

