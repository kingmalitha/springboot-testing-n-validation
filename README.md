## DATA VALIDATION - REST API

### WHY?
- Data Integrity
- Preventing Attacks
- Error Prevention
- User Experience (UX)
- Performance
- Business Logic Compliance

### HOW?

1. Add Spring Validation Dependency to pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

2. Where to add validation? Validation need to be done at the first point of entry to the application. In this case, it is the DTO level.(Data Transfer Object)

3. How to add Validation? Adding annotations to the DTO class.

- `@NotEmpty` : Checks if the field is not null and not empty. Length needs to be greater than zero. Accept whitespaces (Type: String, Collection, Map, Array)
-  `@Email` : Checks if the field is a valid email address. (Type: String)
- `@Future` : Checks if the field is a date in the future. (Type: Date, Time)
- `@NotBlank` : Checks if the field is not null and not empty without whitespaces. (Type: String)
- `@Pattern` : Checks if the field matches the given regular expression. (Type: String)
- `@Size` : Checks if the field size is between the given min and max values.

There are more annotations available. You can check them out from the official documentation.

4. How to handle Exceptions: Displaying some kind of response/error message to the  end user. (Better User Experience) 

Here is an example of handling `MethodArgumentNotValidException` exception. We need to use `@ExceptionHandler` annotation to handle exceptions. Otherwise, Spring will throw a default error message to the end user. It is not a good user experience.
```java
public class StudentController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
```

Sample Response:
```json
{
    "name": "Name is mandatory",
    "email": "Email is mandatory",
    "age": "Age must be between 18 and 99"
}
```

## TESTING - SPRING BOOT

- Spring Boot provides utilities and annotations for testing applications.
- Test support is provided by two modules
  - `spring-boot-test` : Core items
  - `spring-boot-test-autoconfigure` : Auto-configuration for tests
  - `spring-boot-starter-test` imports:
    - Spring Boot test modules
    - JUnit, AssertJ, Hamcrest and no of other useful libraries

- A Spring Boot application is a Spring ApplicationContext
- Spring Boot provides a `@SpirngBootTest` annotation when you need Spring Boot features during tests.

### IMPORTANCE OF TESTING
- Quality Assurance
- Regression Testing
- Documentation
- Code Maintainability
- Refactoring Confidence
- Collaboration
- Continuous Integration/Continuous Development (CI/CD)
- Reduced Debugging Time
- Scalability
- Security

### TYPES OF TESTING
- Unit Testing
- Integration Testing
- Functional Testing
- End-to-End Testing

- `BeforeEach`: This method is executed before each test method. It is used to prepare the test environment (e.g., read input data, initialize the class).
- `AfterEach`: This method is executed after each test method. It is used to clean up the test environment (e.g., delete temporary data, restore defaults). It can also save memory by cleaning up expensive memory structures.
- `BeforeAll`: This method is executed once before any of the test methods in the test class. It is used to perform time intensive activities, for example, to connect to a database. Methods marked with this annotation need to be defined as static to work with JUnit.
- `AfterAll`: This method is executed once after all the test methods in the test class. It is used to perform clean-up activities, for example, to disconnect from a database. Methods annotated with this annotation need to be defined as static to work with JUnit.




### MOCKITO

- Mockito is a mocking framework for Java. 
- It allows us to create mock objects for a class and stub/mock some methods of it. 
- It is specially useful when you want to isolate specific class for unit testing without worrying about its dependencies.

![mokito.png](/markdown_images/mokito.png)

In this example, in order to test the `StudentService` class, we need to mock the `StudentRepository` class and `StudentMapper` class. 

We can do that by using `@Mock` annotation. Then, we need to inject the mocked object to the `StudentService` class by using `@InjectMocks` annotation. Finally, we need to initialize the mocked objects by using `MockitoAnnotations.initMocks(this)` method.

