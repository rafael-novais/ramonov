FROM amazoncorretto:25-alpine

####################
# Stage 1: Build
####################

WORKDIR /src

COPY pom.xml ./
RUN --mount=type=cache,target=/root/.m2 true
COPY src ./src
# Remova --mount acima se seu Docker não suportar; é só um cache.
RUN ./mvnw -v >/dev/null 2>&1 || (apt-get update && apt-get install -y maven)
RUN mvn -B -DskipTests package

####################
# Stage 2: Runtime
####################
FROM amazoncorretto:25-alpine
WORKDIR /app

# Copia o jar gerado (Maven)
COPY /target/*.jar /app/app.jar
# Para Gradle, seria:
# COPY --from=build /src/build/libs/*.jar /app/app.jar

ENV SPRING_PROFILES_ACTIVE=local \
    TZ=America/Sao_Paulo \
    JAVA_OPTS=""

EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=20s --retries=3 \
  CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
