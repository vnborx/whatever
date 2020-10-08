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



# 2. Spring Boot Configuration Files



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



# 3. Web Development Using Spring Boot



We can use the following ways to invoke static resources:

* webjars (not recommended): `localhost:8080/webjars/`
* find in classpath/public, classpath/static, classpath/resources: `localhost:8080/`
  * priority: resources > static (default) > public
* change the default path: `spring.mvc.static-path-pattern=/xxx/`



Pages under `resources/templates` can only be jump to through controller, which needs the following dependency:

```xml
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring5</artifactId>
</dependency>
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-java8time</artifactId>
</dependency>
```



**Custom icon (version 2.2.x):** put icon under the folder `static`, and add `<link rel="icon" href="favicon.ico" >` between `<head></head>` tags of `index.html`.



**Thymeleaf**

XML namespace must be used as:

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
  ...
</html>
```

- Simple expressions:
  - Variable Expressions: `${...}`
  - Selection Variable Expressions: `*{...}`
  - Message Expressions: `#{...}`
  - Link URL Expressions: `@{...}`
  - Fragment Expressions: `~{...}`
- Literals
  - Text literals: `'one text'`, `'Another one!'`,…
  - Number literals: `0`, `34`, `3.0`, `12.3`,…
  - Boolean literals: `true`, `false`
  - Null literal: `null`
  - Literal tokens: `one`, `sometext`, `main`,…
- Text operations:
  - String concatenation: `+`
  - Literal substitutions: `|The name is ${name}|`
- Arithmetic operations:
  - Binary operators: `+`, `-`, `*`, `/`, `%`
  - Minus sign (unary operator): `-`
- Boolean operations:
  - Binary operators: `and`, `or`
  - Boolean negation (unary operator): `!`, `not`
- Comparisons and equality:
  - Comparators: `>`, `<`, `>=`, `<=` (`gt`, `lt`, `ge`, `le`)
  - Equality operators: `==`, `!=` (`eq`, `ne`)
- Conditional operators:
  - If-then: `(if) ? (then)`
  - If-then-else: `(if) ? (then) : (else)`
  - Default: `(value) ?: (defaultvalue)`

All these features can be combined and nested:

```html
'User is of type ' + (${user.isAdmin()} ? 'Administrator' : (${user.type} ?: 'Unknown'))
```



All Thymeleaf attributes define a numeric precedence, which establishes the order in which they are executed in the tag. This order is:

| Order | Feature                         | Attributes                                 |
| :---- | :------------------------------ | :----------------------------------------- |
| 1     | Fragment inclusion              | `th:insert `th:replace`                    |
| 2     | Fragment iteration              | `th:each`                                  |
| 3     | Conditional evaluation          | `th:if` `th:unless` `th:switch` `th:case`  |
| 4     | Local variable definition       | `th:object` `th:with`                      |
| 5     | General attribute modification  | `th:attr` `th:attrprepend` `th:attrappend` |
| 6     | Specific attribute modification | `th:value` `th:href` `th:src` `...`        |
| 7     | Text (tag body modification)    | `th:text` `th:utext`                       |
| 8     | Fragment specification          | `th:fragment`                              |
| 9     | Fragment removal                | `th:remove`                                |



Things can be retrieved in html by:

`<div th:text="${msg}"></div>`

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <link th:href="@{css/example.css}" rel="stylesheet" >
</head>
<body>
<div th:text="${msg}"></div>
<h3 th:each="user: ${users}" th:text="${user}"></h3>
</body>
</html>
```



**i18n (internationalization)**

* We need to config i18n files
* A component (bean) `LocaleResolver` can be written for changing the language of web pages, which also needs to be injected into spring using `@Bean`.



**Extract common page components**

1. `th:fragment="navbar"`
2. `th:replace"~{common/common::sidebar')`
3. arguments can be passed like `th:replace"~{common/common::sidebar(active='list.html')`



**CRUD (create, retrieve, update, delete)**

1. Retrieve

```html
<!-- Example of retrieving all data -->
<table class="table">
	<thead>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>email</th>
      <th>gender</th>
      <th>department</th>
      <th>birth</th>
		</tr>
	</thead>
	<tbody>
    <!-- Traversal -->
		<tr th:each="emp:${emps}">
			<td>[[${emp.getId()}]]</td>
			<td th:text="${emp.getName()}"></td>
			<td th:text="${emp.getEmail()}"></td>
      <!-- Transform gender data -->
			<td th:text="${emp.getGender()==0?'Female':'Male'}"></td>
    	<td th:text="${emp.department.getName()}"></td> 
      <!-- Format date -->
    	<td th:text="${#dates.format(emp.getBirth(), 'dd/MMM/yyyy')}"></td>
    	<td>
    		<button class="btn btn-sm btn-primary">Edit</button>
    		<button class="btn btn-sm btn-danger">Delete</button>
    	</td>
		</tr>
	</tbody>
</table>
```

2. Create

   1. Submit by button
   2. Jump to the page for creating a new entry
   3. Creation succeeded
   4. Jump back to the list page

   **Something to notice:**

   * The default receiving format of date is split by '/' (dd/MM/yyyy), if we want to change it as '-', we need to set `spring.mvc.date-format=MM-dd-yyyy`.
   * If a object contains another object, e.g. employee has a field 'department', as we cannot directly add a department object on frond-end, we can add one field of the object being contained like **(Note 'name' must match this field)**:

   ```html
   <select class="form-control" name="department.id">
       <option th:each="dept:${departments}" th:text="${dept.getName()}" th:value="${dept.getId()}"></option>
   </select>
   ```

   ```java
   public void add(Employee employee) {
       if (employee.getId() == null)
           employee.setId(initId++);
   
       employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
       employees.put(employee.getId(), employee);
   }
   ```

3. 

