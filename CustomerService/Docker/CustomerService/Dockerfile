FROM azul/zulu-openjdk:17

# ADD CustomerService-0.0.3-SNAPSHOT.jar CustomerService.jar

LABEL maintainer="use azul OpenJDKN"


COPY CustomerService-0.0.3-SNAPSHOT.jar CustomerService.jar

ENTRYPOINT ["java", "-jar", "CustomerService.jar"]
