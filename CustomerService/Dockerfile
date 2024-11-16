FROM azul/zulu-openjdk:17
LABEL maintainer="use azul OpenJDK"
COPY target/CustomerService-0.0.3-SNAPSHOT.jar CustomerService.jar
ENTRYPOINT ["java", "-jar", "CustomerService.jar"]

