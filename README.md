# javafx-cdi-bootstrap
This library allows you to use Contexts and Dependency Injection (CDI 3.0) in your JavaFX application.

[![maven-build](https://github.com/fuinorg/javafx-cdi-bootstrap/actions/workflows/maven.yml/badge.svg)](https://github.com/fuinorg/javafx-cdi-bootstrap/actions/workflows/maven.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.fuin/javafx-cdi-bootstrap/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.fuin/javafx-cdi-bootstrap/)
[![Apache 2.0 License](http://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Java Development Kit 17](https://img.shields.io/badge/JDK-17-green.svg)](https://openjdk.java.net/projects/jdk/17/)

## Simple example
See [test](test) module for an example that also has a [TestFX](https://github.com/TestFX/TestFX) class: [FxCdiExampleAppTest.java](test/src/test/java/org/fuin/fxcdibootstrap/FxCdiExampleAppTest.java)

## Real world example
If you want an example application that shows how to implement the most important parts of a real world JavaFX application,
see [javafx-cdi-example](https://github.com/fuinorg/javafx-cdi-example).

## Getting Started (manually)

First make sure, you have added `javafx-cdi-bootstrap` to your classpath. 
If you are using Maven you can archive this by adding the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>org.fuin</groupId>
    <artifactId>javafx-cdi-bootstrap-library</artifactId>
    <version>3.0.0</version>
</dependency>
```

If not already present, add a CDI 3.0 implementation (Weld implementation for example):

```xml
<dependency>
    <groupId>org.jboss.weld.se</groupId>
    <artifactId>weld-se-core</artifactId>
    <version>5.1.2.Final</version>
</dependency>
```

Then add the following `beans.xml` file to your META-INF directory, to enable automatic bean discovery:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="https://jakarta.ee/xml/ns/jakartaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/beans_4_0.xsd"
       version="4.0" bean-discovery-mode="all">
</beans>
```

After that, simply create a class that extends `FxCdiApplication`. 
It is very similar to the standard `Application` class from JavaFX core and can be used equally. 
It also provides a main-method to start the CDI container and your JavaFX application.

```java
@ApplicationScoped
public class FxCdiExampleApp extends FxCdiApplication {

    @Inject
    @Bundle("fxml/application")
    private FXMLLoader fxmlLoader;

    @Override
    public void start(final Stage stage, final Application.Parameters parameters) throws Exception {
        final Parent mainWindow = fxmlLoader.load(getClass().getResourceAsStream("/fxml/application.fxml"));
        final Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        FxCdiApplicationLoader.launch(FxCdiApplicationLoader.class, args);
    }

}
```

As you can see, the `FXMLLoader` is already being injected by CDI. 
The `@Bundle` annotation assigns the provided `ResourceBundle` to the injected `FXMLLoader`. 
An instance of the controller class defined in the FXML file will automatically be created and managed within the CDI context.

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.Button?>

<VBox fx:controller="org.fuin.fxcdibootstrap.example.FxCdiExampleController" 
      xmlns="http://javafx.com/javafx/21" xmlns:fx="http://javafx.com/fxml/1">
    <children>
        <Label fx:id="labelOne" text="%label-one-text" />
        <Button fx:id="buttonOne" text="%button-one-text" onAction="#onButtonOneClick" />
    </children>
</VBox>
```

From now on, you are fully CDI enabled. You can write your own producers, inject everything into controllers and even use CDI events.

```java
public class FxCdiExampleController {

    @FXML
    private Label labelOne;

    @FXML
    private Button buttonOne;

    @Inject
    @Bundle("fxml/application")
    private ResourceBundle bundle; // CDI injected source bundle

    @Inject
    private Event<FooEvent> fooEvent;

    public void onBarEvent(@Observes Event<BarEvent> event) {
        // Do something useful with event received
    }

    @FXML
    public void onButtonOneClick(ActionEvent event) {
        final String text = bundle.getString("button-one-clicked");
        buttonOne.setText(text);
        // Emit CDI event
        fooEvent.fire(new FooEvent("Hello!"));
    }

}
```

