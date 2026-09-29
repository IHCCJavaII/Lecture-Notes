# Java + Ollama

## Install Ollama

<!-- TODO display the model thinking in real time -->
<!-- TODO display the model name as a fx label -->

You have two paths to install Ollama:

- [Install Ollama on your local machine](https://ollama.com/download)
- [Install Ollama on Docker](https://hub.docker.com/r/ollama/ollama)
    - [Install Docker](https://docs.docker.com/get-docker/)

## Install a Model

Only thing different from the local installation is that you don't need to go into the container.

```bash
# Install Ollama on Docker
docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama

# List and note the port number
docker ps
# Go into the Ollama container
docker exec -it ollama bash

# Install the model
ollama pull ollama pull qwen3.5:2b
ollama run qwen3.5:2b

# Ask it a question

#Exit
/exit
exit
```

> Swap `docker` with `podman` if you are using podman instead of docker. (What I recommend for Linux/Mac users)

```bash
podman run -d \
  -v ollama:/root/.ollama \
  -p 11434:11434 \
  --name ollama \
  -e OLLAMA_HOST=0.0.0.0 \
  ollama/ollama
```

## Build and Run the Java Application

```bash
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-simple
mvn clean javafx:run
```

- [Library we will use](https://ollama4j.github.io/ollama4j/)
    - [Get it though maven](https://github.com/ollama4j/ollama4j?tab=readme-ov-file#for-maven)

> Before running the application, make sure that Ollama is running!!

Delete teh model-info.java file or add the following code to it:

```java
module slopbot {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.net.http;
    requires ollama4j;

    exports slopbot;
}
```

If the model won't stop thinking, tomorrow we will make a custom model what will fit our needs.
