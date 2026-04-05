package com.fitness.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent")
            .build();

    public String getRecommendation(String prompt) {

        try {
            // 🔹 Request Body
            Map<String, Object> request = new HashMap<>();

            Map<String, Object> part = new HashMap<>();
            part.put("text", prompt);

            Map<String, Object> content = new HashMap<>();
            content.put("parts", List.of(part));

            request.put("contents", List.of(content));

            // 🔹 API Call
            Map response = webClient.post()
                    .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                    .header("Content-Type", "application/json")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            // 🔹 Extract response
            List candidates = (List) response.get("candidates");
            Map first = (Map) candidates.get(0);

            Map contentMap = (Map) first.get("content");
            List parts = (List) contentMap.get("parts");

            Map textPart = (Map) parts.get(0);

            return textPart.get("text").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI error: " + e.getMessage();
        }
    }
}