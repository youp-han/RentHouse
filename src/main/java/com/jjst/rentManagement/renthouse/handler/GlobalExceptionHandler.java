package com.jjst.rentManagement.renthouse.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleTypeMismatchException(MethodArgumentTypeMismatchException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Invalid input provided: " + ex.getValue());
        return "error/error"; // Render the error page directly
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "An unexpected error occurred: " + ex.getMessage());
        return "error/error"; // Render the error page directly
    }

}
