# spring-smart-specification

# How to use

Example of use:
```java
    jpaExampleRepository.count(JpaExampleSpecification.builder()
                .nameContains("Zul")
                .timestampLessThan(LocalDateTime.of(2000, 01, 02, 03, 04))
                .build());
```

Application:
```java
@SpringBootApplication(scanBasePackageClasses = SpringSmartSpecificationApplication.class)
public class SpringSmartSpecificationTestApplication {
```

Entity:
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JpaExample {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime timestamp;
    private Boolean active;

}
```

Repository:
```java
@Repository
public interface JpaExampleRepository
        extends JpaRepository<JpaExample, Long>, JpaSmartSpecificationRepository<JpaExample> {

}
```

Specification:
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JpaExampleSpecification implements SmartSpecification<JpaExample> {

    private Long id;
    private String nameContains;
    private String nameContainsIgnoreCase;
    private Long childId;
    private LocalDateTime timestampLessThan;
    private LocalDateTime timestampGreaterThan;

}

```

# Support for mongo
Repository:
```java
@Repository
public interface MongoExampleRepository
        extends MongoRepository<JpaExample, Long>, MongoSmartSpecificationRepository<JpaExample> {

}
```

# Projections

Projection:
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JpaExampleProjection {

    private Long id;
    private String name;
    private Long ownerId;

    @Value("#{target.owner.fullName}")
    private String ownerName;

}
```

Example of use:
```java
specification = JpaExampleProjection.builder()
                .nameContains("Zul")
                .build();
jpaExampleRepository.findAllProjectedBy(JpaExampleProjection.class, specification);
```

# Maven
```maven
<dependency>
  <groupId>io.github.zul</groupId>
  <artifactId>spring-smart-specification</artifactId>
  <version>1.0.0</version>
</dependency>
```