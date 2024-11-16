FROM azul/zulu-openjdk:17
LABEL maintainer="use azul OpenJDK"
COPY target/CustomerStubService-0.0.3-SNAPSHOT.jar CustomerStubService.jar
ENTRYPOINT ["java", "-jar", "CustomerStubService.jar"]

