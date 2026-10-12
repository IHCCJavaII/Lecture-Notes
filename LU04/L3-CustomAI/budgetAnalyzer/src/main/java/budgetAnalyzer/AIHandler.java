package budgetAnalyzer;

import java.util.List;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.request.ThinkMode;
import io.github.ollama4j.models.response.OllamaResult;

public final class AIHandler {
    public static String MODEL_NAME = "budget-coach:latest";

    public static String createReport(List<Transaction> transactions) throws Exception {
        Ollama ollama = new Ollama("http://localhost:11434");
        ollama.setRequestTimeoutSeconds(300);

        OllamaResult result = ollama.generate(
                OllamaGenerateRequest.builder()
                        .withModel(MODEL_NAME)
                        .withThink(ThinkMode.DISABLED)
                        .withPrompt(generatePrompt(transactions))
                        .build(),
                null);
        return result.getResponse();
    }

    private static String generatePrompt(List<Transaction> transactions) {
        var prompt = new StringBuilder();
        for (var t : transactions) {
            // Without ToString: Object:Transaction @43343920
            prompt.append(t.toString());
        }
        return prompt.toString();
    }
}