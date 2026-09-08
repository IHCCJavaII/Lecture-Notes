# Console Menu App with Database Integration

## Setting Up the Database

We will be using MySQL using Docker/Podman

```bash
docker run -d --name movies_db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=movies_db mysql:latest
```

> You can change password

## Create the Java Application

```bash
mvn archetype:generate -DgroupId=org.example -DartifactId=movies -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
# Run the app
mvn compile exec:java -Dexec.mainClass="org.example.App"
```

### Add MySQL Connector Dependency

Open the `pom.xml` file and add the following dependency inside the `<dependencies>` section:

Maven: https://mvnrepository.com/artifact/com.mysql/mysql-connector-j

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.6.0</version>
    <scope>compile</scope>
</dependency>
```

## Add Dotenv Dependency

```xml
    <dependency>
      <groupId>io.github.cdimascio</groupId>
      <artifactId>dotenv-java</artifactId>
      <version>3.2.0</version>
    </dependency>
```

Make a `.env` file in the root of your project.
