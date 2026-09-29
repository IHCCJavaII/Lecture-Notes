package stopLight;

import java.util.List;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.request.ThinkMode;
import io.github.ollama4j.models.response.OllamaResult;

public final class AIHandler {
    public static final String MODEL_NAME = "ollama run llava-phi3";

    public static String analyzeImage(String imageURL) throws Exception {
        Ollama ollama = new Ollama("http://127.0.0.1:11434/");
        ollama.setRequestTimeoutSeconds(300);

        OllamaResult result = ollama.generate(
                OllamaGenerateRequest.builder()
                        .withModel(MODEL_NAME)
                        .withThink(ThinkMode.DISABLED)
                        .withPrompt("")
                        .build(),
                null);

        return result.getResponse();
    }

}
