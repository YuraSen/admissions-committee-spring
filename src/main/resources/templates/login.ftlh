<#import "fragments/error.ftl" as error>
<#import "/spring.ftl" as spring/>
<html>
<head>

    <meta charset="utf-8">
    <title><@spring.message "login.login_page"/></title>
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
                    <div class="card-header"><@spring.message "login.user_login"/></div>
                    <div class="card-body">

                       <@error.error/>

                        <form action="/auth/login" method="post" >
                            <div class="form-group row">
                                <label for="username" class="col-md-4 col-form-label text-md-right"><@spring.message "login.username"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="username" class="form-control" name="username" placeholder="<@spring.message "login.username"/>"
                                           required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><@spring.message "login.password"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" placeholder="<@spring.message "login.password"/>" required >
                                </div>
                            </div>


                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <@spring.message "login.login"/>
                                </button>
                                <a href="/registration" class="btn btn-link">
                                    <@spring.message "login.registration"/>
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

