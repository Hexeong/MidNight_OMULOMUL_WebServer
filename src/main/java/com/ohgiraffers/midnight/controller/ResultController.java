package com.ohgiraffers.midnight.controller;

import com.ohgiraffers.midnight.config.JwtUtil;
import com.ohgiraffers.midnight.service.AiCallService;
import com.ohgiraffers.midnight.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/result")
public class ResultController {
    private final AiCallService aiCallService;
    private final JwtUtil jwtUtil;
    private final ResultService resultService;

    public ResultController(AiCallService aiCallService, JwtUtil jwtUtil, ResultService resultService) {
        this.aiCallService = aiCallService;
        this.jwtUtil = jwtUtil;
        this.resultService = resultService;
    }

    @PostMapping("/{username}")
    @ResponseBody
    public Mono<Map<String, Object>> callAiService(
            @RequestHeader(value = "token") String token,
            @PathVariable String username,
            @RequestBody Map<String, Object> result) {
        if (!jwtUtil.validateToken(token, username)) return null;

        System.out.println("result = " + result);

        List<Map<String, Object>> resultValues = (List<Map<String, Object>>) result.get(username);
        Map<String, Object> resultValue = resultValues.get(0);

        return aiCallService.callAiEvaluation(resultValue, username, token);
    }

    @PostMapping("/ai/{username}")
    public ResponseEntity<?> registResult(
            @RequestHeader(value = "token") String token,
            @PathVariable String username,
            @RequestBody Map<String, Object> result) {

        if (!jwtUtil.validateToken(token, username)) return ResponseEntity.badRequest().build();

        System.out.println("token = " + token);
        resultService.registResult(result, username);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/info/{username}")
    public ResponseEntity<?> getResult(
            @RequestHeader(value = "token") String token,
            @PathVariable String username) {

        if (!jwtUtil.validateToken(token, username)) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(resultService.findRecentResult());
    }
}
