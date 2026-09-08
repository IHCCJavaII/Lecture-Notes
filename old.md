## Install

Change dnf to apt if you are on debian/ubuntu.

```bash
sudo dnf install java maven
```

That's it :)

## Java Console Start

https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

```bash
# Create a simple Java project
mvn archetype:generate -DgroupId=org.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
# change directory
cd my-app
# Build and run the project
mvn compile exec:java -Dexec.mainClass="org.example.App"
```

Change Compiler Version to 25 (or whatever version you are using) in the pom.xml file.

## JavaFX Start

Use Mavan to create a JavaFX project using the OpenJFX archetype.
This will set up a basic JavaFX application for you.
Name the project whatever you like.

```bash
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-simple
mvn clean javafx:run
```

The -D flags are parameters you are passing to that blueprint:

- `archetypeGroupId=org.openjfx`: Tells Maven who created the blueprint. In this case, it’s the official OpenJFX team.

- `archetypeArtifactId=javafx-archetype-simple`: This is the specific "model" of the blueprint. This one sets up a basic "Hello World" JavaFX project. There are others (like javafx-archetype-fxml) if you want to use XML for your UI.
- `archetypeVersion=0.0.6`: This is the version of the blueprint itself, ensuring you get the most up-to-date project structure.a

> Might not need that last one. s

## Ask james about this:

```
Define value for property 'groupId':
Define value for property 'artifactId':
Define value for property 'version' 1.0-SNAPSHOT:
Define value for property 'package' : s
```

## Start the Program

```bash
mvn clean javafx:run
```
