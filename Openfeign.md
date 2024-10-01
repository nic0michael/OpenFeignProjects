## OpenFeign

### Spring Cloud OpenFeign
**Version:** 4.0.6

This project provides OpenFeign integrations for Spring Boot applications, featuring autoconfiguration and binding to the Spring Environment.

---

#### 1. Declarative REST Client: Feign
Feign simplifies the creation of web service clients through a declarative approach, using interfaces and annotations. It supports both Feign and JAX-RS annotations, along with pluggable encoders and decoders. Spring Cloud enhances Feign by integrating with Spring MVC annotations, Eureka, CircuitBreaker, and LoadBalancer for load-balanced HTTP clients.

##### 1.1. How to Include Feign
Include Feign using the starter: `spring-cloud-starter-openfeign`. 
Example Spring Boot application:

```java
@SpringBootApplication
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

Example `StoreClient` interface with various HTTP methods.

The `@FeignClient` annotation includes a client name for load balancing and allows specifying a URL. If using Eureka, the service is resolved through the service registry.

##### 1.1.1. Attribute Resolution Mode
Feign client attributes are resolved eagerly by default. To enable lazy resolution, set `spring.cloud.openfeign.lazy-attributes-resolution` to true.

---

#### 1.2. Overriding Feign Defaults
Feign clients form named ensembles, which can be customized using the `@FeignClient` annotation. Additional configurations can be defined in a separate class without needing the `@Configuration` annotation. 

You can also override the default context using the `contextId` attribute and utilize placeholders in the `name` and `url` attributes.

Default beans provided by Spring Cloud OpenFeign include `Decoder`, `Encoder`, `Logger`, and `Client`, among others. For custom HTTP clients, ensure the necessary dependencies are included and configure using relevant properties.

Starting with version 4, Apache HttpClient 4 is no longer supported.

Custom configurations can be applied through `application.yml` or by specifying default configurations in `@EnableFeignClients`.

##### 1.2.1. SpringEncoder Configuration
The provided `SpringEncoder` sets the charset based on content types, which can be customized by configuring charset behavior through properties.

--- 

### 1.3. Timeout Handling
Timeouts can be configured for both default and named Feign clients using two parameters: `connectTimeout`, which prevents blocking due to long server processing, and `readTimeout`, which applies from the connection establishment time until a response is received. If the server is unavailable, the client may encounter a connection error or fallback, potentially influenced by low `connectTimeout` settings and DNS lookup delays.

---

### 1.4. Creating Feign Clients Manually
For advanced customization, Feign Clients can be created using the Feign Builder API. The example shows how to configure two Feign Clients with the same interface, each with different request interceptors. The `FeignClientsConfiguration.class` provides default settings, and the autowired `Contract` bean supports Spring MVC annotations.

---

### 1.5. Feign Spring Cloud CircuitBreaker Support
If Spring Cloud CircuitBreaker is included and `spring.cloud.openfeign.circuitbreaker.enabled=true`, Feign methods will be wrapped with a circuit breaker. To disable this for specific clients, create a prototype-scoped Feign Builder. The naming pattern for circuit breakers can be customized using a `CircuitBreakerNameResolver` bean.

---

### 1.6. Configuring CircuitBreakers With Configuration Properties
CircuitBreakers can be configured using properties. For example, enable circuit breakers for a Feign client named `DemoClient` and configure minimum call numbers and timeout durations in the `application.yml`. To revert to the old naming convention, set `spring.cloud.openfeign.circuitbreaker.alphanumeric-ids.enabled` to false.

---

### 1.7. Feign Spring Cloud CircuitBreaker Fallbacks
CircuitBreaker fallbacks provide alternative code paths when a circuit is open or errors occur. To enable fallbacks for a Feign client, specify the fallback class in the `@FeignClient` annotation. The fallback implementation must be a Spring bean. For more control, use the `fallbackFactory` attribute to access the cause that triggered the fallback.

---

### 1.8. Feign and @Primary
When multiple Feign client beans exist in the ApplicationContext, Spring’s `@Autowired` may fail due to ambiguity. To resolve this, all Feign instances are marked as `@Primary` by default. To disable this behavior, set the `primary` attribute of `@FeignClient` to false.

--- 

### 1.9. Feign Inheritance Support
Feign supports boilerplate APIs using single-inheritance interfaces, allowing for grouping of common operations into base interfaces. Example interfaces can be extended and implemented by both server and client components, but `@FeignClient` interfaces should not be shared between server and client.

---

### 1.10. Feign Request/Response Compression
Feign supports GZIP compression for requests and responses. You can enable compression by configuring specific properties in the application, allowing for selective compression of specific media types and enforcing a minimum request size. Note that when using `OkHttpClient`, compression is disabled if content-encoding or accept-encoding headers are present.

---

### 1.11. Feign Logging
Feign logging is customizable, with various levels of detail available: `NONE`, `BASIC`, `HEADERS`, and `FULL`. You can configure logging behavior per Feign client in the application, using different log levels to suit the needs of the system.

---

### 1.12. Feign Capability Support
Feign capabilities allow for the modification of core Feign components. By registering custom `Capability` beans, you can extend or decorate clients with additional functionality. For example, Micrometer support can be added through these capabilities.

---

### 1.13. Micrometer Support
Feign clients can be made observable by Micrometer if certain conditions are met, such as having the `feign-micrometer` dependency and appropriate configuration. Micrometer support can be enabled or disabled for all clients or on a per-client basis.

---

### 1.14. Feign Caching
If `@EnableCaching` is used, a `CachingCapability` bean is registered to recognize caching annotations like `@Cacheable`. This allows caching of Feign client responses, which can also be disabled through configuration properties.

---

### 1.15. Feign @QueryMap Support
Feign supports `@SpringQueryMap` for passing query parameters via POJOs or maps. Custom `QueryMapEncoder` beans can be implemented to control the query parameter map generation process.

---

### 1.16. HATEOAS Support
If your project uses Spring HATEOAS or Spring Data REST, Feign HATEOAS support is enabled by default. Feign clients can serialize and deserialize HATEOAS representation models such as `EntityModel` and `CollectionModel`.

---

### 1.17. Spring @MatrixVariable Support
Feign supports the `@MatrixVariable` annotation, allowing clients to pass matrix variables as path segments in URLs. Both the variable name and the path segment placeholder must match to avoid ambiguity.

---

### 1.18. Feign CollectionFormat Support
Feign supports the `@CollectionFormat` annotation, which allows you to control how collections are serialized in requests. For example, using `feign.CollectionFormat.CSV` can change the default behavior to serialize collections as comma-separated values.

---

### 1.19. Reactive Support
OpenFeign does not support reactive clients like Spring WebClient at this time. For reactive support, you can use `feign-reactive` until native support is available in OpenFeign.

---

### 1.19.1. Early Initialization Errors
Using Feign clients during the early stages of application initialization is discouraged. You may encounter errors if Feign clients are initialized too early. A workaround is to use `ObjectProvider` when autowiring your Feign clients.

---

### 1.20. Spring Data Support
If Jackson Databind and Spring Data Commons are available, automatic conversion for `Page` and `Sort` objects will be added. To disable this behavior, you can set the property `spring.cloud.openfeign.autoconfiguration.jackson.enabled=false`.

---

### 1.21. Spring @RefreshScope Support
Feign clients can support refreshable properties, such as timeouts and URLs, when `@RefreshScope` is enabled. To enable refresh behavior, set `spring.cloud.openfeign.client.refresh-enabled=true`. Avoid using `@RefreshScope` directly on `@FeignClient` interfaces.

---

### 1.22. OAuth2 Support
OAuth2 can be enabled for Feign clients by adding the `spring-boot-starter-oauth2-client` dependency and setting `spring.cloud.openfeign.oauth2.enabled=true`. Feign clients will automatically obtain OAuth2 tokens for requests using the `OAuth2AccessTokenInterceptor`.

---

### 1.23. Transform the Load-Balanced HTTP Request
You can customize load-balanced Feign requests by implementing `LoadBalancerFeignRequestTransformer`. This allows you to modify the request with headers or other transformations based on the service instance.

---

### 1.24. X-Forwarded Headers Support
Support for `X-Forwarded-Host` and `X-Forwarded-Proto` headers can be enabled by setting the property `spring.cloud.loadbalancer.x-forwarded.enabled=true`.

---

### 1.25. Supported Ways To Provide URL To A Feign Client
URLs can be provided to Feign clients in various ways, including:
- Specified in the `@FeignClient` annotation.
- Provided in configuration properties (`application.yml`).
- Resolved from the service name with load balancing.

If `spring.cloud.openfeign.client.refresh-enabled=true` is set, URLs provided in the configuration can be refreshed.

---
