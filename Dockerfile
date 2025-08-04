FROM maven:3-eclipse-temurin-17-noble AS build
COPY . .
RUN mvn clean package -DskipTests

FROM  eclipse-temurin:17-alpine
COPY --from=build /target/*.jar expense-tracker-ui.jar
ENTRYPOINT ["java", "-jar", "expense-tracker-ui.jar"]