package com.ohgiraffers.midnight.controller;

import com.ohgiraffers.midnight.config.JwtUtil;
import com.ohgiraffers.midnight.dto.UserRegistRequestDTO;
import com.ohgiraffers.midnight.entity.User;
import com.ohgiraffers.midnight.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserContorller {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public String test() {
        return "Hello";
    }

    @GetMapping("/duplicate")
    @ResponseBody
    public ResponseEntity<?> duplicate(@RequestParam String username) {
        User foundUser = userService.findUserByUserName(username);
        if (foundUser != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public String signup(UserRegistRequestDTO userRegistRequestDTO) {
        userService.registUser(userRegistRequestDTO);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes rttr) {
        User foundUser = userService.findUserByUserName(username);
        if(foundUser.getPassword().equals(password)) {
            String token = jwtUtil.generateToken(username);
            session.setAttribute("username", username);
            session.setAttribute("token", token);
            rttr.addFlashAttribute("successMessage", username + "님, 환영합니다.");
            return "redirect:/";
        }
        rttr.addFlashAttribute("failMessage", "로그인에 실패하셨습니다 다시 로그인해주세요");
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        jwtUtil.invalidateToken((String) session.getAttribute("token"));
        session.removeAttribute("username");
        session.removeAttribute("token");
        return "redirect:/";
    }
}
