# 1. First Exploration of SpringBoot's Principle



**Automatic configuration:**

* spring-boot-dependencies: core dependencies are in parent project
* We do not need to indicate the version when we add springboot dependencies, because of the existence of version repository in parent project



**Starter**

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```

* it's actually the launch scene of SpringBoot
* e.g. Spring-boot-starter-web will import all dependencies for web environment
* Springboot makes all functional scenarios as starters
* We only need to find the corresponding `starter` for what function we want to use



**Main program**

```java
@SpringBootApplication
public class Springboot01HelloworldApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }
}
```



* `@SpringBootApplication` marks the current program as a SpringBoot application
  * Contains `@SpringBootConfiguration` - configuration of SpringBoot, which contains `@Configuration` - configuration of Spring, which contains `@Component` - a component of Spring
  * Contains `@EnableAutoConfiguration` - automatic configuration, which contains `@AutoConfigurationPackage` that `@Import({Registrar.class})`, and `@Import({AutoConfigurationImportSelector.class})`, which has the following code:

```java
List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
```

we can find this method is 

```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
    List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
    Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
    return configurations;
}
```

`META-INF/spring.factories` - core file of automatic configuration



**All configurations are scanned and loaded when SpringBoot starts. All configuration classes of `spring.factories` are contained, but they only work when their corresponding `starter` is included in the configuration file.**

