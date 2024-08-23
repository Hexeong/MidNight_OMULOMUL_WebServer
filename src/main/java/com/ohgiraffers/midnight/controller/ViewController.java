package com.ohgiraffers.midnight.controller;

import com.ohgiraffers.midnight.entity.Result;
import com.ohgiraffers.midnight.service.ResultService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ViewController {
    private final ResultService resultService;

    public ViewController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public String index() {
        return "main/index";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        List<Result> resultList = resultService.getAllResultsByUserName(username);
        model.addAttribute(resultList);
        return "main/mypage";
    }

    @GetMapping("/user/login")
    public String loginView(HttpSession session, Model model) {
        if (session.getAttribute("failMessage") != null) {
            String failMessage = session.getAttribute("failMessage").toString();
            session.removeAttribute("failMessage");
            System.out.println("failMessage = " + failMessage);
            model.addAttribute("failMessage", failMessage);
        }
        return "user/login";
    }

    @GetMapping("/user/signup")
    public String signupView() {
        return "user/signup";
    }

    @GetMapping("/result/{resultNo}")
    public String certificate(@PathVariable("resultNo") int resultNo, Model model) {
        System.out.println("resultNo = " + resultNo);
        Result foundResult = resultService.findResultByResultNo(resultNo);
        model.addAttribute("result", foundResult);
        System.out.println("foundResult = " + foundResult);
        return "main/result";
    }
}
