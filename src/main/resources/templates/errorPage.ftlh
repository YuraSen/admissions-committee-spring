<html>
<#import "/spring.ftl" as spring/>
<#include "fragments/security.ftlh"/>
<head>
    <#include "fragments/bootsrap-links.ftl"/>
    <title>Title</title>
</head>
<body>

<br>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="alert alert-primary" role="alert">

                <h3><@spring.message "errorPage.error"/></h3>
                <h5>${errorMessage}</h5>
                <br>
                <#if isAdmin>
                    <form method="get" action="/admin/workspace">
                        <button type="submit" class="btn btn-primary">
                            <@spring.message "admin.check_request.back"/>
                        </button>
                    </form>
                <#else >
                    <form method="get" action="/faculties">
                        <button type="submit" class="btn btn-primary">
                            <@spring.message "admin.check_request.back"/>
                        </button>
                    </form>
                </#if>

            </div>
        </div>
    </div>
</div>
</body>

</html>
