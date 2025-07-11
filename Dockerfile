# Stage 1: Build the application
# Using a JDK image for the build stage. Eclipse Temurin is a good choice for production.
FROM eclipse-temurin:17-jdk-jammy AS build

# Set the working directory inside the container
WORKDIR /app

# Install Maven
# The jammy base is Ubuntu-based, so apt-get is used.
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copy the Maven project files (pom.xml) to download dependencies
# This step is optimized to use Docker's layer caching. If pom.xml doesn't change,
# the dependencies won't be re-downloaded.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code
COPY src ./src

# Build the Spring Boot application
# -DskipTests: Skips running tests to speed up the build in Docker.
# -Dspring-boot.repackage.skip: Prevents Maven from creating the executable JAR in this stage,
# as we'll use the 'BOOT-INF/lib' and 'BOOT-INF/classes' directly for a slimmer image.
RUN mvn clean package -DskipTests

# Stage 2: Create the final, slim production image
# Using a JRE-only image for the runtime. Alpine Linux is very small.
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8080

# Copy the built application JAR from the 'build' stage
# This copies only the necessary parts for a slim image:
# - BOOT-INF/lib: Contains all the application's dependencies.
# - BOOT-INF/classes: Contains your application's compiled classes.
# - META-INF: Contains manifest information.
# - org: Contains Spring Boot loader classes.
COPY --from=build /app/target/mangalfera-0.0.1-SNAPSHOT.jar BOOT-INF/lib/
COPY --from=build /app/target/mangalfera-0.0.1-SNAPSHOT.jar BOOT-INF/classes/
COPY --from=build /app/target/mangalfera-0.0.1-SNAPSHOT.jar META-INF/
COPY --from=build /app/target/mangalfera-0.0.1-SNAPSHOT.jar org/

# Define the entrypoint for the application using the Spring Boot JAR's internal structure.
# This is the recommended way for Spring Boot 2.x+ applications for faster startup.
# Adjust memory settings (-Xmx, -Xms) as needed for your production environment.
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xmx512m", "-jar", "BOOT-INF/lib/mangalfera-0.0.1-SNAPSHOT.jar"]
