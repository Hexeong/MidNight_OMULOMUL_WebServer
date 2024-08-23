package com.ohgiraffers.midnight.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.ohgiraffers.midnight.config.Base64Util.decodeBase64;

@Service
public class AiCallService {
    private final WebClient webClient;

    @Autowired
    public AiCallService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build(); // WebClient 인스턴스 생성
    }

    public Mono<Map<String, Object>> callAiEvaluation(Map<String, Object> result, String username, String token) {

        String url = "http://192.168.1.52:8080/result/" + username;
        return webClient.post().uri(url).header("token", token)
                .bodyValue(result)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
//
//    public Mono<Map<String, Object>> callAiEvaluationFix(Map<String, Object> result, String username, String token) {
//        String url = "http://192.168.1.52:8080/result/" + username;
//
//        // base64로 인코딩된 음성 파일 가져오기
//        String base64Audio = (String) result.get(username);
//        byte[] decodedFile = Base64.getDecoder().decode(base64Audio);
//
//        // JSON 데이터 변환
//        result.remove(username); // username 키는 제거하여 JSON 데이터로 변환
//        ByteArrayResource jsonResource = new ByteArrayResource(convertMapToJson(result).getBytes()) {
//            @Override
//            public String getFilename() {
//                return "data.json"; // JSON 파일 이름 설정
//            }
//        };
//
//        // MultiValueMap을 사용하여 Multipart 요청 구성
//        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//        body.add("file", new ByteArrayResource(decodedFile) {
//            @Override
//            public String getFilename() {
//                return "audio.wav"; // 파일 이름 설정
//            }
//        });
//        body.add("json", jsonResource); // JSON 데이터 추가
//
//
//        return webClient.post()
//                .uri(url)
//                .header("token", token)
//                .contentType(MediaType.MULTIPART_FORM_DATA) // Multipart 요청 설정
//                .body(BodyInserters.fromMultipartData(body))
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
//    }
//
//    private String convertMapToJson(Map<String, Object> map) {
//        // JSON 변환 로직 (Jackson 사용 예시)
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.writeValueAsString(map);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("JSON 변환 실패", e);
//        }
//    }
}

