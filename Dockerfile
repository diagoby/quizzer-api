# Start from JDK base image as `builder`
FROM adoptopenjdk:11-jdk-hotspot AS builder

# Set the current working directory inside the image
WORKDIR /build

# Copy sources to the working directory
COPY ./ ./

# Build Maven app
RUN ./mvnw package -DskipTests

# Start from JDK base image
FROM adoptopenjdk:11-jre-hotspot

COPY --from=builder /build/target/quizzer-api-*.jar app.jar

# Set the port number and expose it
ARG port=3333
EXPOSE $port

# Set image entry point
ENTRYPOINT ["java", "-jar"]
CMD ["./app.jar"]
