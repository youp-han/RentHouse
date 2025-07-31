package com.jjst.rentManagement.renthouse.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    //home
    @GetMapping("/")
    public String home() {
        return "index";
    }

    //login
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //logout
    @GetMapping("/logout")
    public String logout(){
        return "/";
    }

    //error
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("statusCode", statusCode);
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "페이지를 찾을 수 없습니다.");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorMessage", "서버 내부 오류가 발생했습니다.");
            } else {
                model.addAttribute("errorMessage", "오류가 발생했습니다.");
            }
        }
        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage.toString());
        }
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (exception != null) {
            model.addAttribute("exception", exception.toString());
        }
        return "error/error";
    }

}

