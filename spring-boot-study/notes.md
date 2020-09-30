# 1. First Exploration of Spring Boot's Principle



**Automatic configuration:**

* spring-boot-dependencies: core dependencies are in parent project
* We do not need to indicate the version when we add Spring Boot dependencies, because of the existence of version repository in parent project



**Starter**

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```

* it's actually the launch scene of Spring Boot
* e.g. Spring-boot-starter-web will import all dependencies for web environment
* Spring Boot makes all functional scenarios as starters
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



* `@SpringBootApplication` marks the current program as a Spring Boot application
  * Contains `@SpringBootConfiguration` - configuration of Spring Boot, which contains `@Configuration` - configuration of Spring, which contains `@Component` - a component of Spring
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



**All configurations are scanned and loaded when Spring Boot starts. All configuration classes of `spring.factories` are contained, but they only work when their corresponding `starter` is included in the configuration file.**



# 2. Spring Boot configuration file



Spring Boot uses a global configuration file, which has a **fixed** name.

* application.properties
  * Syntax: key=value
* application.yaml (.yml) (Recommended)
  * Syntax: key: value (a blank space must exists)

Usage of configuration file: modifies the default setting of Spring Boot, which are set automatically by Spring Boot.



**YAML** - YAML Ain't Markup Language

Syntax of YAML file: (**Indenting is very important as it represents a hierarchical relationship**)

```yaml
# Normal key-value pair
name: Acacia

# Object
student:
  name: Acacia
  age: 22

# One-line object
student: {name: Acacia, age: 22}

# Array
pets:
  - cat
  - dog
  - pig

# One-line array
pets: [cat, dog, pig]
```



**YAML can directly assign value to entity classes,** with the following annotation given in entity classes.

```java
@ConfigurationProperties(prefix = "xxx")
```

And it supports loose binding, e.g. lastName can match last-name (`@Value` doesn't support this feature).



**Config locations are searched in reverse order.** By default, the configured locations are `classpath:/,classpath:/config/,file:./,file:./config/*/,file:./config/`. The resulting search order is the following (classpath means `resources` file in Spring Boot):

1. `file:./config/`
2. `file:./config/*/`
3. `file:./`
4. `classpath:/config/`
5. `classpath:/`



**Multi-Environment Configuration**

We may have `application.properties`, `application-dev.properties` , and `application-test.properties` under the same directory. The active configuration file can be choose by adding the following code to `application.properties`:

```properties
spring.profiles.active=dev
```

Note here we **only use** `dev` instead of `application-dev.properties`.



YAML further improves this feature. Mutiple configuration files are unnecessary for YAML, instead, we can write multi-environment configuration in a single file, like

```yaml
server.port: 8081
spring.profiles.active: dev
---
server.port: 8082
spring.profiles: dev
---
server.port: 8083
spring.profiles: test

# port 8082 will be opened
```
