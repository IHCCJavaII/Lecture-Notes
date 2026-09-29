# Project

## Install Model

Install the model.
If you find a new model, you can swap it out.

```bash
docker exec -it ollama bash
ollama pull llava-phi3
```

## Create JavaFX Project

- Create a JavaFX project.
- Add the Dependencies on the `pom.xml` file.
- Add edit the `module-info.java` file.
- Grab reference images and save them to Downloads folder.

```java
module stopLight {
    requires javafx.controls;
    requires java.net.http;
    requires ollama4j;
    exports stopLight;
}
```

## My favorite Output

![Image Given](red-light.jpeg)

> A car moving on the road at high speed hits a person standing beside the road while lighting from traffic signal is red. The car rams into the person leading to injury. The court of law holds the owner and driver responsible for their negligence. The defense lawyer objects that it was unreasonable for his client to expect the pedestrian not to walk in front of a moving vehicle on road when he had a green signal.
