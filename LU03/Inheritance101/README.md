# LU03 Inheritance

Inheritance is a fundamental concept in object-oriented programming (OOP) that allows a class to inherit properties and behaviors (methods) from another class.

- [Java Docs: Inheritance](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)

## Slides

[Inheritance Slides](slides.html)

## Create the Project

```bash
mvn archetype:generate -DgroupId=org.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

Run the project with the following command:

```bash
mvn compile exec:java -Dexec.mainClass="org.example.App"
```

## Inheritance Diagram

<!-- Product -> Laptop -->
<!-- Product -> YogaMat --> -->

```mermaid
classDiagram
    class Product {
        <<abstract>>
        -String name
        -double price
        +Product(String name, double price)
        +String getName()
        +double getPrice()
    }

    class Laptop {
        -String brand
        -int ramSize
        +Laptop(String name, double price, String brand, int ramSize)
        +String getBrand()
        +int getRamSize()
    }

    class YogaMat {
        -String color
        -double thickness
        +YogaMat(String name, double price, String color, double thickness)
        +String getColor()
        +double getThickness()
    }

    Product <|-- Laptop
    Product <|-- YogaMat
```
