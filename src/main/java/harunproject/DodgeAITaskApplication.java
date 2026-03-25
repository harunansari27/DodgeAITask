package harunproject;

import harunproject.DodgeAITask.service.DataIngestionService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DodgeAITaskApplication {

    private final DataIngestionService dataIngestionService;

    // 🔥 constructor injection (best practice)
    public DodgeAITaskApplication(DataIngestionService dataIngestionService) {
        this.dataIngestionService = dataIngestionService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DodgeAITaskApplication.class, args);
    }

    // 🔥 App start hote hi run hoga
    @PostConstruct
    public void init() {
        System.out.println("🚀 Starting Data Load...");

        dataIngestionService.loadCoreData();  // ✅ correct method

        System.out.println("✅ Data Load Completed");
    }
}