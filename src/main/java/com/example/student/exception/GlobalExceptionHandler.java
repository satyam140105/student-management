package com.example.student.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleValidationException(
            ConstraintViolationException ex,
            Model model) {

        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception ex,
            Model model) {

        model.addAttribute("errorMessage",
                "Something went wrong: " + ex.getMessage());

        return "error";
    }
}