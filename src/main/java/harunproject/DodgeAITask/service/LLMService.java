package harunproject.DodgeAITask.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LLMService {

    private final WebClient webClient;
    private final ObjectMapper mapper = new ObjectMapper();

    // 🔥 application.properties se key aayegi
    @Value("${groq.api.key}")
    private String apiKey;

    public LLMService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .build();
    }

    public String askLLM(String userQuery) {

        try {
            String prompt = """
            You are a data assistant.

            Answer ONLY based on dataset context:
            Orders → Deliveries → Invoices → Payments

            If question is unrelated, say:
            "This system only supports dataset queries."

            Question:
            """ + userQuery;

            String response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue("""
                    {
                      "model": "llama3-8b-8192",
                      "messages": [
                        {"role": "user", "content": "%s"}
                      ]
                    }
                    """.formatted(prompt))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // 🔥 Safe parsing (no crash 💀)
            JsonNode json = mapper.readTree(response);

            JsonNode choices = json.get("choices");
            if (choices != null && choices.size() > 0) {
                return choices
                        .get(0)
                        .get("message")
                        .get("content")
                        .asText();
            }

            return "⚠️ No response from LLM";

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error calling LLM";
        }
    }
}