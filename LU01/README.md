# LU01 JavaFx

Get started learning about the JavaFX library and how to use it by following along with this guide and reading the tutorial linked inside. We will be building some examples out in-class as well.

- [IHCC Java II JavaFX Guide](https://ihccjavaii.github.io/docs/general-topics/javafx.html)
- [Oracle JavaFX Tutorial](https://dev.java/learn/javafx/)

## How to Install JavaFX

We will use Maven to create our JavaFX projects. Maven is a build automation tool used to manage dependencies and build Java projects. It simplifies the process of adding libraries like JavaFX to your project.

### Prerequisites

Make sure you have Java Installed.

```cmd
# Install Java on Windows using winget
winget install -e --id Oracle.JDK.26
# Check that Java is installed
java -version
```

Maven isn't available via winget (don't you love windows?), so you will need to install [chocolatey](https://chocolatey.org/install) first. Then you can install Maven via chocolatey:

```cmd
# Install Maven using chocolatey
choco install maven
```

Or on Linux:

```bash
# replace apt with your package manager (e.g., dnf)
sudo apt install java maven
```

Restart your command prompt window.
After restart. In a **new** Command Prompt window, check that Maven is available:

```bash
mvn -v
```

### Create and Build the JavaFX Project

1. **Generate Project via Maven Archetype:** Use OpenJFX Official Archetype.
   Run the following command in Command Prompt to generate a JavaFX project structure:


Windows

```cmd
mvn archetype:generate "-DarchetypeGroupId=org.openjfx" "-DarchetypeArtifactId=javafx-archetype-simple"
```


Linux

```bash
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-simple
```

- Name the groupId, artifactId, and version as you like. Default version is 1.0-SNAPSHOT (just press enter).

Now that your project is created, navigate to the project directory and run it.

```bash
# Change directory to your project folder
cd my-javafx-app
# Run the project
mvn javafx:run
# run with clean  if you want to make sure everything is built from scratch
# mvn clean javafx:run
```

## Create Console App via Maven

[Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

```bash
# Create a simple Java project
mvn archetype:generate -DgroupId=org.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
# change directory
cd my-app
# Build and run the project
mvn compile exec:java -Dexec.mainClass="org.example.App"
```

## Getting Started with TestFX

Read through this guide to learn how to get started with TestFX, which allows you to unit test JavaFX projects

- [IHCC TestFX Guide](https://ihccjavaii.github.io/docs/junit/testfx.html)

## How to use images with JavaFX

[Read the official docs](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html) to see an example of using the ImageView class to load Images onto your JavaFX pane.

## Pushing large files to GitHub

This may help some of you if you're having trouble pushing your project because of the error "remote end hung up unexpectedly".

This happens when the amount of data you are trying to push is larger than your Git's default buffer size.

You can increase your buffer size to fix this:

```bash
git config --global http.postBuffer 524288000
```

You may also run into an error if your images are larger than GitHub's supported push limit. If that's the case, you really need to compress your images because that's too large, yo.
