package harunproject.DodgeAITask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class JsonLoaderService {

    private final ObjectMapper mapper = new ObjectMapper();

    // 🔥 ADD THIS METHOD (missing tha 💀)
    public List<Map<String, Object>> loadFolderFromResources(String folderName) {

        List<Map<String, Object>> allData = new ArrayList<>();

        try {
            ClassLoader classLoader = getClass().getClassLoader();

            File folder = new File(
                Objects.requireNonNull(
                    classLoader.getResource("data/" + folderName)
                ).getFile()
            );

            File[] files = folder.listFiles((dir, name) -> name.endsWith(".jsonl"));

            if (files == null) {
                System.out.println("❌ No files found in " + folderName);
                return allData;
            }

            for (File file : files) {
                System.out.println("📂 Reading: " + file.getName());

                allData.addAll(loadJsonl(file.getAbsolutePath()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allData;
    }

    // 🔥 JSONL parser
    public List<Map<String, Object>> loadJsonl(String filePath) {

        List<Map<String, Object>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                Map<String, Object> obj = mapper.readValue(line, Map.class);

                data.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}