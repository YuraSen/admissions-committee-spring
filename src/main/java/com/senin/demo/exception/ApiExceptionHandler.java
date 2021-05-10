package com.senin.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = com.training.admissions.exception.CandidateAlreadyExistsException.class)
    public ModelAndView handleCandidateAlreadyExistsException(com.training.admissions.exception.CandidateAlreadyExistsException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/registration");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(value = com.training.admissions.exception.CandidateNotFoundException.class)
    public ModelAndView handleCandidateNotFoundException(com.training.admissions.exception.CandidateNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/errorPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(value = FacultyAlreadyExistsException.class)
    public ModelAndView handleFacultyAlreadyExistsException(FacultyAlreadyExistsException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/create-faculty");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

//    @ExceptionHandler(value = RequestAlreadyExistsException.class)
//    public ModelAndView handleRequestAlreadyExistsException(RequestAlreadyExistsException ex) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/errorPage");
//        Locale locale = LocaleContextHolder.getLocale();
//        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
//        modelAndView.addObject("errorMessage",bundle.getObject("request.already.exists.error"));
//        return modelAndView;
//    }

    @ExceptionHandler(value = com.training.admissions.exception.RequestNotFoundException.class)
    public ModelAndView handleRequestNotFoundException(com.training.admissions.exception.RequestNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/errorPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(value = FacultyNotFoundException.class)
    public ModelAndView handleFacultyNotFoundException(FacultyNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/errorPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(value = com.training.admissions.exception.StatementCreationException.class)
    public ModelAndView handleStatementCreationException(com.training.admissions.exception.StatementCreationException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/errorPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

}
