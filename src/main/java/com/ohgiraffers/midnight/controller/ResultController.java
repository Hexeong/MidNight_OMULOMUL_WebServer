package com.ohgiraffers.midnight.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/result")
public class ResultController {
    @PostMapping("/{username}")
    public ResponseEntity<?> registSimulationResult(@RequestBody Map<String, Object> result, @PathVariable String username) {
        System.out.println("username = " + username);
        System.out.println("result = " + result);

        // TODO:: service 호출

        return ResponseEntity.ok().build();
    }
}
