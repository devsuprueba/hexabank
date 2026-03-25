# Dockerfile for ms-client
# Follows the project F19 dockerization spec: lightweight Java 21 image,
# copy built JAR from build/libs, expose port and run the application.

FROM openjdk:21-jdk-slim

LABEL org.opencontainers.image.title="hexabank-ms-client"
LABEL org.opencontainers.image.licenses="MIT"

WORKDIR /app

# Allow overriding the jar at build time
ARG JAR_FILE=build/libs/*.jar

# Copy the application jar produced by Gradle into the image
COPY ${JAR_FILE} app.jar

# Expose default application port
EXPOSE 8080

# Allow passing JVM options via environment variable without editing the image
ENV JAVA_OPTS=""

# Run the jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
