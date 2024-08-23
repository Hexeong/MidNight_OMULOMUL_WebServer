package com.ohgiraffers.midnight.controller;

import com.ohgiraffers.midnight.config.JwtUtil;
import com.ohgiraffers.midnight.dto.UserRegistRequestDTO;
import com.ohgiraffers.midnight.entity.User;
import com.ohgiraffers.midnight.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/login/token")
    public ResponseEntity<?> getToken(
            @RequestBody Map<String, Object> user,
            HttpSession session,
            RedirectAttributes rttr) {

        System.out.println("user = " + user);
        User foundUser = userService.findUserByUserName(user.get("username").toString());
        if (foundUser == null) return ResponseEntity.badRequest().build();

        if(foundUser.getPassword().equals(user.get("password").toString())) {
            String token = jwtUtil.generateToken(user.get("username").toString());
            Map<String, Object> body = new HashMap<>();
            body.put("token", token);
            body.put("username", user.get("username").toString());

            return ResponseEntity.ok(body);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        jwtUtil.invalidateToken((String) session.getAttribute("token"));
        session.removeAttribute("username");
        session.removeAttribute("token");
        return "redirect:/";
    }
}
