package budgetAnalyzer;

import java.util.List;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.request.ThinkMode;
import io.github.ollama4j.models.response.OllamaResult;

public final class AIHandler {
    public static final String MODEL_NAME = "budget-coach:latest";

    public static String createReport(List<Transaction> transactions) throws Exception {
        Ollama ollama = new Ollama("http://127.0.0.1:11434/");
        ollama.setRequestTimeoutSeconds(300);

        OllamaResult result = ollama.generate(
                OllamaGenerateRequest.builder()
                        .withModel(MODEL_NAME)
                        .withThink(ThinkMode.DISABLED)
                        .withPrompt(createPrompt(transactions))
                        .build(),
                null);

        return result.getResponse();
    }

    private static String createPrompt(List<Transaction> transactions) {
        var prompt = new StringBuilder();
        prompt.append("Create a budget report from these transactions.\n\n");
        prompt.append("description,amount,category,date\n");

        for (var transaction : transactions) {
            prompt.append(transaction.toCsvRow(transaction)).append("\n");
        }
        return prompt.toString();
    }
}
