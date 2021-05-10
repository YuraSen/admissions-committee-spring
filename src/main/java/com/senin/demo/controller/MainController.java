package com.senin.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }


    @GetMapping("/auth/login")
    public String login(Model model, HttpServletRequest request,
                        @RequestParam(value = "error", required = false) Boolean error) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        if (nonNull(error)) {
            HttpSession session = request.getSession(false);
            String errorMessage = null;
            if (nonNull(session)) {
                AuthenticationException authenticationException = (AuthenticationException) session
                        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                if (nonNull(authenticationException)) {
                    if (authenticationException instanceof BadCredentialsException) {
                        errorMessage = (String) bundle.getObject("badCredentials");
                    }
                    if (authenticationException instanceof LockedException) {
                        errorMessage = (String) bundle.getObject("disabled");
                    }
                }
            }
            model.addAttribute("errorMessage", errorMessage);
            return "login";

        }
        return "login";
    }
    @GetMapping("/auth/success")
    public String getSuccessPage() {
        return "success";
    }

}
