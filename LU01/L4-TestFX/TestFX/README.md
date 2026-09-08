# TestFX

## Junit

JUnit is for testing methods.

## Java FX

JavaFX is for testing GUI.

- [James Docs](https://ihccjavaii.github.io/docs/junit/testfx.html)

Run the following command to run the tests:

```bash
mvn test
```

### JavaFX vs JUnit

| JavaFX | JUnit |
| ------ | ----- |

## Create the Project

```bash
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-simple
# Run the Project
mvn clean javafx:run
# Run the Tests
mvn test
```

Make a folder in `src/TextFX/java/org/example` called `MainTests.java`.
Make a folder in `src/TextFX/resources` an put `deluxe-double.png` in it.

Add testing to `pom.xml`:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testfx</groupId>
    <artifactId>testfx-junit5</artifactId>
    <version>4.0.16</version>
    <scope>test</scope>
</dependency>
```

Then make a testing class in `src/TextFX/
