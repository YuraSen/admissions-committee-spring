<#include "security.ftlh"/>

<#import "/spring.ftl" as spring/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="/faculties"><@spring.message "navbar.Admission_Board_App"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <div class="nav-item dropdown">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown"><@spring.message "navbar.Change_Language"/>
                        <b class="caret"></b></a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a class="dropdown-item"
                           href="${springMacroRequestContext.requestUri}?lang=en"><@spring.message "navbar.English"/></a>
                        <a class="dropdown-item"
                           href="${springMacroRequestContext.requestUri}?lang=uk"><@spring.message "navbar.Ukrainian"/></a>
                    </div>

                </li>
                <li class="nav-item">
                    <form class="form-inline ml-2 mr-2 my-2 my-lg-0" action="/admin/applicant" method="get">
                        <button class="btn btn-primary"
                                type="submit"><@spring.message "navbar.Admin_workspace_applicant"/></button>
                    </form>
                </li>
                <li class="nav-item">
                    <form class="form-inline ml-2 mr-2 my-2 my-lg-0" action="/admin/workspace" method="get">
                        <button class="btn btn-primary"
                                type="submit"><@spring.message "navbar.Admin_workspace_faculty"/></button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
    <#if know>

        <div class="nav-item dropdown">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                                        data-toggle="dropdown"><@spring.message "navbar.Account"/> ${name}
                        <b class="caret"></b></a>
                    <div class="dropdown-menu dropdown-menu-right">

                        <form action="/api/applicant/profile" method="get">
                            <button class="dropdown-item" type="submit"><@spring.message "navbar.my_profile"/></button>
                        </form>
                        <form action="/applicant/applicant_requests" method="get">
                            <button class="dropdown-item" type="submit"><@spring.message "navbar.my_requests"/></button>
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

</nav>




