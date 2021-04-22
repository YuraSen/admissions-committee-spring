<#import "fragments/error-login.ftl" as error>
<html>
<head>

    <meta charset="utf-8">
    <title>Admission board app login </title>
    <#include "fragments/bootsrap-links.ftl"/>

</head>
<body>
<#include "fragments/nav-bar-log-reg.ftl" />
<br>
<main class="login-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">User Login</div>
                    <div class="card-body">

                       <@error.error_login/>

                        <form action="/auth/login" method="post" >
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="form-group row">
                                <label for="username" class="col-md-4 col-form-label text-md-right">Username </label>
                                <div class="col-md-6">
                                    <input type="text" id="username" class="form-control" name="username"
                                           required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>


                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Login
                                </button>
                                <a href="/registration" class="btn btn-link">
                                    Register
                                </a>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
</body>
</html>

