## Getting Started with Maven

https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

```bash
# Create a simple Java project
mvn archetype:generate -DgroupId=org.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
# change directory
cd my-app
# Build and run the project
mvn compile exec:java -Dexec.mainClass="org.example.App"
```

