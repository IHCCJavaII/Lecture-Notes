# Into to Databases in Java

We will be using a simple Java application to demonstrate how to connect to a database, execute queries.

## Setting Up the Database

We will be using MySQL using Docker/Podman

```bash
docker run -d --name mysql-ducks -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=duck_db mysql:latest
```

> You can change password

## Create the Java Application

```bash
mvn archetype:generate -DgroupId=org.example -DartifactId=ruber-duck -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
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