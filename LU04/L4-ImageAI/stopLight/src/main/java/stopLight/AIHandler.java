package stopLight;

import java.io.File;
import java.util.List;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.request.ThinkMode;
import io.github.ollama4j.models.response.OllamaResult;

public final class AIHandler {
    public static final String MODEL_NAME = "llava-phi3";

    public static String analyzeImage(String imagePath) throws Exception {
        Ollama ollama = new Ollama("http://127.0.0.1:11434/");
        ollama.setRequestTimeoutSeconds(300);

        OllamaResult result = ollama.generate(
                OllamaGenerateRequest.builder()
                        .withModel(MODEL_NAME)
                        .withThink(ThinkMode.DISABLED)
                        .withPrompt("You are given an image of a traffic light. "
                                + "Which single light is illuminated: red, yellow, or green? "
                                + "Answer with exactly one word (red, yellow, or green) and nothing else. "
                                + "Do not return coordinates, numbers, or explanations.")
                        .withImages(List.of(new File(imagePath)))
                        .build(),
                null);

        return result.getResponse().trim();
    }

}
