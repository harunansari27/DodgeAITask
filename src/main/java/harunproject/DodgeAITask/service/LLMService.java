package harunproject.DodgeAITask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class LLMService {

    private final WebClient webClient = WebClient.create("https://api.groq.com/openai/v1");

    @Value("${groq.api.key}")
    private String API_KEY;

    public String generateSQL(String userQuery) {

        String prompt = """
        You are a PostgreSQL SQL generator.

        Rules:
        - Only generate valid SQL
        - Table: invoice
        - Columns: id, invoice_number, customer_name, amount
        - Return only SQL ending with ;

        User Query:
        """ + userQuery;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama3-8b-8192");
        requestBody.put("temperature", 0);

        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));

        try {
            Map response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            Map choice = (Map) ((List) response.get("choices")).get(0);
            Map messageObj = (Map) choice.get("message");

            String content = (String) messageObj.get("content");

            return extractSQL(content);

        } catch (Exception e) {
            e.printStackTrace();
            return "SELECT * FROM invoice;";
        }
    }

    private String extractSQL(String content) {
        try {
            content = content.trim();
            String upper = content.toUpperCase();

            int start = upper.indexOf("SELECT");
            int end = upper.indexOf(";");

            if (start != -1 && end != -1) {
                return content.substring(start, end + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "SELECT * FROM invoice;";
    }
}