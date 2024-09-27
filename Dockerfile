FROM amazoncorretto:17
COPY target/*.jar wallet.jar
ENTRYPOINT ["java", "-jar","/wallet.jar"]