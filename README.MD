# starboard

A small but expandable java setting library.

# How to import

## Gradle

```groovy
repositories {
    maven {
        url 'https://jitpack.io/'
    }
}

dependencies {
    implementation('com.github.nkosmos:starboard:VERSION')
}
```

## Maven

no.

# Getting started

## Declarations

### Groups

Groups are a way of... well grouping setting together. They can be declared as such:

```java
public class MyClass {

    private final Group defaultGroup = new Group("Default Group", this);

}
```

Groups can also have parent/child groups:

```java
public class MyOtherClass {

    private final Group parentGroup = new Group("Root Group", this);
    private final Group childGroup = new Group("Subgroup", this, parentGroup);

}
```

### Setting declaration

Settings can be declared under groups, and capture their value type as such:

```java
public class AnotherClass {

    private final Group defaultGroup = new Group("Default Group", this);

    private final Setting<String> nameSetting = new Setting<>(defaultGroup, "Name", "Tommy");
    private final Setting<Integer> ageSetting = new Setting<>(defaultGroup, "Age", 18);

}
```

## Constraints

### Value constraints

TODO

### Visibility constraints

TODO

# Credits

This project was based on the [LeafClient/Konfigurate](https://github.com/LeafClient/Konfigurate) kotlin library
by [Shyrogan](https://github.com/Shyrogan). Thanks to him for letting us use their structure and parts of their code!

# License

This project is provided free-of-charge under
the [MIT License](https://github.com/nkosmos/starboard/blob/master/LICENSE).