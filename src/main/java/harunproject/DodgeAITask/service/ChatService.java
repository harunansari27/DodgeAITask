package harunproject.DodgeAITask.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    private final LLMService llmService;
    private final QueryExecutionService queryExecutionService;

    public ChatService(LLMService llmService, QueryExecutionService queryExecutionService) {
        this.llmService = llmService;
        this.queryExecutionService = queryExecutionService;
    }

    public Object processQuery(String userQuery) {

        // Guardrail
        if (!userQuery.toLowerCase().contains("invoice")) {
            return "This system only answers dataset-related queries.";
        }

        // Step 1: LLM → SQL
        String sql = llmService.generateSQL(userQuery);

        // Step 2: Execute SQL
        List<Map<String, Object>> result = queryExecutionService.executeQuery(sql);

        return result;
    }
}