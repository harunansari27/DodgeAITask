package harunproject.DodgeAITask.controller;

import org.springframework.web.bind.annotation.*;
import harunproject.DodgeAITask.service.ChatService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public Object chat(@RequestBody Map<String, String> body) {
        String query = body.get("query");
        return chatService.processQuery(query);
    }
}